package requirements;

import java.util.List;
import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import org.odftoolkit.odfdom.doc.table.OdfTable;
import org.odftoolkit.odfdom.doc.table.OdfTableRow;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class externalCellReferences {

    // Check for external cell references using ODF Toolkit
    public int Check_ODFToolkit(String filepath, boolean verbose) throws Exception {
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
                            if (verbose)
                                System.out.println("CHECK ODS_5 VERBOSE: External cell reference detected. Sheet: " + table.getTableName() + ", Cell: unknown, Reference: " + formulaNode.getTextContent());
                        }
                    }
                }
            }
        }
        spreadsheet.close();

        // Inform user and return number
        if (extCellRefs > 0)
            System.out.println("CHECK ODS_5: " + extCellRefs + " external cell references detected");
        return extCellRefs;
    }

    // Remove external cell references using ODF Toolkit
    public int Change_ODFToolkit(String filepath, boolean verbose) throws Exception {
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
                            //extCellRefs++;
                            //cell.removeAttributeNS(OdfDocumentNamespace.OF.getUri(), "table:formula");
                            //spreadsheet.save(filepath);
                        }
                    }
                }
            }
        }
        spreadsheet.close();

        // Inform user and return number
        if (extCellRefs > 0)
            System.out.println("CHANGE ODS_5: " + extCellRefs + " external cell references removed");
        return extCellRefs;
    }
}
