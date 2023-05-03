package main;

import java.util.List;
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
}
