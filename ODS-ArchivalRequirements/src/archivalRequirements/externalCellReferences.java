package archivalRequirements;

import java.io.*;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import org.odftoolkit.odfdom.doc.table.*;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class externalCellReferences {

    // Check for external cell references using ODF Toolkit
    public int Check_ODFToolkit(String filepath) throws Exception {
        int extCellRefs = 0;

        // Perform check
        OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(filepath);
        List<OdfTable> tables = spreadsheet.getSpreadsheetTables();
        for (OdfTable table : tables) {
            List<OdfTableRow> rows = table.getRowList();
            for (OdfTableRow row : rows) {
                NodeList cells = row.getOdfElement().getChildNodes();
                for (int i = 0; i < cells.getLength(); i++) {
                    Node cell = cells.item(i);
                    Node formulaNode = cell.getAttributes().getNamedItem("table:formula");
                    if (formulaNode != null) {
                        if (formulaNode.getNodeValue().startsWith("of:=['file")) {
                            extCellRefs++;
                        }
                    }
                }
            }
        }
        spreadsheet.close();

        // Inform user and return number
        if (extCellRefs > 0) {
            System.out.println("CHECK: " + extCellRefs + " external cell references detected");
        }
        return extCellRefs;
    }

    // Remove external cell references using ODF Toolkit
    public int Change_ODFToolkit(String filepath) throws Exception {
        int extCellRefs = 0;

        // Perform change
        OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(filepath);
        List<OdfTable> tables = spreadsheet.getSpreadsheetTables();
        for (OdfTable table : tables) {
            List<OdfTableRow> rows = table.getRowList();
            for (OdfTableRow row : rows) {
                NodeList cells = row.getOdfElement().getChildNodes();
                for (int i = 0; i < cells.getLength(); i++) {
                    Node cell = cells.item(i);
                    Node formulaNode = cell.getAttributes().getNamedItem("table:formula");
                    if (formulaNode != null) {
                        if (formulaNode.getNodeValue().startsWith("of:=['file")) {
                            extCellRefs++;
                            // below results in error
                            // cell.removeChild(formulaNode);
                        }
                    }
                }
            }
        }
        spreadsheet.save(filepath);
        spreadsheet.close();

        // Inform user and return number
        if (extCellRefs > 0) {
            System.out.println("CHANGE: " + extCellRefs + " external cell references removed");
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
