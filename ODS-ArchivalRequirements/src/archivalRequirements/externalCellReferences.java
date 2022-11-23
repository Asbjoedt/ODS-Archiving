package archivalRequirements;

import java.io.*;
import org.apache.commons.io.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class externalCellReferences {

    // Check for external cell references using ODF Toolkit
    public int Check_ODFToolkit(String filepath) {
        int extCellRefs = 0;

        return extCellRefs;
    }

    // Remove external cell references using ODF Toolkit
    public int Change_ODFToolkit(String filepath) {
        int extCellRefs = 0;

        return extCellRefs;
    }

    // Check for external cell references using Apache POI
    public int Check_ApachePOI(String filepath) throws IOException {
        int extCellRefs = 0;

        // Find spreadsheet and create workbook instance
        FileInputStream fileInput = new FileInputStream(new File(filepath));
        Operations Fetch = new Operations();
        Workbook wb = Fetch.workbookType(filepath, fileInput);

        // Iterate each sheet, row and cell
        for (int i = 0; i < wb.getNumberOfSheets(); i++) {
            Sheet sheet = wb.getSheetAt(i);
            for (Row row : sheet) {
                for (Cell cell : row) {
                    if (cell.getCellType() == CellType.FORMULA) {
                        if (cell.getCellFormula().startsWith("='") || cell.getCellFormula().startsWith("[")) {
                            extCellRefs++;
                        }
                    }
                }
            }
        }
        wb.close();
        fileInput.close();

        // Inform user and return number
        if (extCellRefs > 0) {
            System.out.println(extCellRefs + " external cell references detected");
        }
        return extCellRefs;
    }

    // Remove external cell references using Apache POI
    public int Change_ApachePOI(String filepath) throws IOException {
        int extCellRefs = 0;

        // Find spreadsheet and create workbook instance
        FileInputStream fileInput = new FileInputStream(new File(filepath));
        Workbook wb;
        String extension = FilenameUtils.getExtension(filepath).toLowerCase();
        if (extension == "xls" || extension == "xla" || extension == "xlt") {
            wb = new HSSFWorkbook(fileInput);
        }
        else {
            wb = new XSSFWorkbook(fileInput);
        }

        // Iterate each sheet, row and cell
        for (int i = 0; i < wb.getNumberOfSheets(); i++) {
            Sheet sheet = wb.getSheetAt(i);
            for (Row row : sheet) {
                for (Cell cell : row) {
                    if (cell.getCellType() == CellType.FORMULA) {
                        if (cell.getCellFormula().startsWith("='") || cell.getCellFormula().startsWith("[")) {
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
                            extCellRefs++;
                        }
                    }
                }
            }
        }
        // Save and close file
        FileOutputStream fileOutput = new FileOutputStream(new File(filepath));
        wb.write(fileOutput);
        wb.close();
        fileOutput.close();
        fileInput.close();

        // Inform user and return number
        if (extCellRefs > 0) {
            System.out.println(extCellRefs + " external cell references removed");
        }
        return extCellRefs;
    }
}
