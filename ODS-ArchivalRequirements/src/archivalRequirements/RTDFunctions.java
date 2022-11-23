package archivalRequirements;

import java.io.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class RTDFunctions {

    // Check for RTD functions using ODF Toolkit
    public int Check_ODFToolkit(String filepath) {
        int rtdfunctions = 0;

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

        FileInputStream file = new FileInputStream(new File(filepath));
        Workbook wb = new XSSFWorkbook(file);

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
        // Inform user and return number
        if (rtdfunctions > 0) {
            System.out.println(rtdfunctions + " RTD functions detected");
        }

        return rtdfunctions;
    }

    // Remove RTD functions using Apache POI
    public int Change_ApachePOI(String filepath) throws IOException {
        int rtdfunctions = 0;

        FileInputStream file = new FileInputStream(new File(filepath));
        Workbook wb = new XSSFWorkbook(file);

        // Iterate each sheet, row and cell
        for (int i = 0; i < wb.getNumberOfSheets(); i++) {
            Sheet sheet = wb.getSheetAt(i);
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
        // Inform user and return number
        if (rtdfunctions > 0) {
            System.out.println(rtdfunctions + " RTD functions removed");
        }

        return rtdfunctions;
    }
}
