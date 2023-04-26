package archivalRequirements;

import java.io.*;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import org.odftoolkit.odfdom.doc.table.OdfTable;

public class RTDFunctions {

    // Check for RTD functions using ODF Toolkit
    public int Check_ODFToolkit(String filepath) throws Exception {
        int rtdfunctions = 0;

        OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(filepath);

        List<OdfTable> tables = spreadsheet.getSpreadsheetTables();

        return rtdfunctions;
    }

    // Remove RTD functions using ODF Toolkit
    public int Change_ODFToolkit(String filepath) {
        int rtdfunctions = 0;

        return rtdfunctions;
    }

    // Check for RTD functions using Apache POI
    public int Check_ApachePOI(String filepath) throws IOException {
        int rtdfunctions = 0;

        FileInputStream fileInput = new FileInputStream(new File(filepath));
        Workbook wb = new XSSFWorkbook(fileInput);

        // Iterate each sheet, row and cell
        for (int i = 0; i < wb.getNumberOfSheets(); i++) {
            Sheet sheet = wb.getSheetAt(i);
            for (Row row : sheet) {
                for (Cell cell : row) {
                    if (cell.getCellType() == CellType.FORMULA) {
                        if (cell.getCellFormula().startsWith("RTD")) {
                            rtdfunctions++;
                        }
                    }
                }
            }
        }
        wb.close();
        fileInput.close();

        // Inform user and return number
        if (rtdfunctions > 0) {
            System.out.println("CHECK:" + rtdfunctions + " RTD functions detected");
        }
        return rtdfunctions;
    }

    // Remove RTD functions using Apache POI
    public int Change_ApachePOI(String filepath) throws IOException {
        int rtdfunctions = 0;

        FileInputStream fileInput = new FileInputStream(filepath);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInput);

        // Iterate each sheet, row and cell
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            Sheet sheet = workbook.getSheetAt(i);
            for (Row row : sheet) {
                for (Cell cell : row) {
                    if (cell.getCellType() == CellType.FORMULA) {
                        if (cell.getCellFormula().startsWith("RTD")) {
                            String cellValue = null;
                            switch (cell.getCellType()) {
                                case BOOLEAN:
                                    cellValue = Boolean.toString(cell.getBooleanCellValue());
                                    break;
                                case NUMERIC:
                                    if (DateUtil.isCellDateFormatted(cell)) {
                                        cellValue = cell.getDateCellValue().toString();
                                    } else {
                                        cellValue = Double.toString(cell.getNumericCellValue());
                                    }
                                    break;
                                case STRING:
                                    cellValue = cell.getStringCellValue();
                                    break;
                                case BLANK:
                                    break;
                                case ERROR:
                                    cellValue = Byte.toString(cell.getErrorCellValue());
                                    break;
                            }
                            cell.removeFormula();
                            cell.setCellValue(cellValue);
                            rtdfunctions++;
                        }
                    }
                }
            }
        }
        // Save and close file
        FileOutputStream fileOutput = new FileOutputStream(new File(filepath));
        workbook.write(fileOutput);
        workbook.close();
        fileOutput.close();
        fileInput.close();

        // Inform user and return number
        if (rtdfunctions > 0) {
            System.out.println("CHANGE:" + rtdfunctions + " RTD functions removed");
        }
        return rtdfunctions;
    }
}
