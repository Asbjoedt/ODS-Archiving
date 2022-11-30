package archivalRequirements;

import java.io.*;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import org.odftoolkit.odfdom.doc.table.*;

public class externalCellReferences {

    // Check for external cell references using ODF Toolkit
    public int Check_ODFToolkit(String filepath) throws Exception {
        int extCellRefs = 0;

        OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(filepath);
        List<OdfTable> tables = spreadsheet.getSpreadsheetTables();
        for (OdfTable table : tables) {
            List<OdfTableRow> rows = table.getRowList();
            for (OdfTableRow row : rows) {
                for (int i = 1; i < 2; i++) {
                    OdfTableCell cell = row.getCellByIndex(i);
                    String cellFormula = cell.getFormula();
                    if (cellFormula != null) {
                        if (cellFormula.startsWith("'=") || cellFormula.startsWith("[")) {
                            extCellRefs++;
                        }
                    }
                }
            }
        }
        spreadsheet.close();

        // Inform user and return number
        if (extCellRefs > 0) {
            System.out.println(extCellRefs + " external cell references detected");
        }
        return extCellRefs;
    }

    // Remove external cell references using ODF Toolkit
    public int Change_ODFToolkit(String filepath) throws Exception {
        int extCellRefs = 0;

        OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(filepath);
        List<OdfTable> tables = spreadsheet.getSpreadsheetTables();
        for (OdfTable table : tables) {
            List<OdfTableRow> rows = table.getRowList();
            for (OdfTableRow row : rows) {
                for (int i = 1; i < 2; i++) {
                    OdfTableCell cell = row.getCellByIndex(i);
                    String cellFormula = cell.getFormula();
                    if (cellFormula != null) {
                        if (cellFormula.startsWith("'=") || cellFormula.startsWith("[")) {
                            String savedValue = cell.getStringValue();
                            cell.setFormula(null);
                            cell.setStringValue(savedValue);
                            extCellRefs++;
                        }
                    }
                }
            }
        }
        spreadsheet.save(filepath);
        spreadsheet.close();

        // Inform user and return number
        if (extCellRefs > 0) {
            System.out.println(extCellRefs + " external cell references removed");
        }
        return extCellRefs;
    }

    // Check for external cell references using Apache POI
    public int Check_ApachePOI(String filepath) throws IOException {
        int extCellRefs = 0;

        // Find spreadsheet and create workbook instance
        FileInputStream fileInput = new FileInputStream(filepath);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInput);

        // Iterate each sheet, row and cell
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            Sheet sheet = workbook.getSheetAt(i);
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
        workbook.close();
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
        FileInputStream fileInput = new FileInputStream(filepath);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInput);

        // Iterate each sheet, row and cell
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            Sheet sheet = workbook.getSheetAt(i);
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
                                    }
                                    else {
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
        FileOutputStream fileOutput = new FileOutputStream(filepath);
        workbook.write(fileOutput);
        workbook.close();
        fileOutput.close();
        fileInput.close();

        // Inform user and return number
        if (extCellRefs > 0) {
            System.out.println(extCellRefs + " external cell references removed");
        }
        return extCellRefs;
    }
}
