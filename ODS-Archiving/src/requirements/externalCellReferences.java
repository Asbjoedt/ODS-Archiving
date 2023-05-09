package requirements;

import java.util.List;
import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import org.odftoolkit.odfdom.doc.table.OdfTable;
import org.odftoolkit.odfdom.doc.table.OdfTableRow;
import org.odftoolkit.odfdom.doc.table.OdfTableCell;
import org.odftoolkit.odfdom.dom.OdfDocumentNamespace;
import org.odftoolkit.odfdom.pkg.OdfElement;
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
                            if (verbose) {
                                System.out.println("CHECK VERBOSE ODS_4: Sheet: " + table.getTableName() + ", Cell: unknown, External cell reference: " + formulaNode);
                            }
                        }
                    }
                }
            }
        }
        spreadsheet.close();

        // Inform user and return number
        if (extCellRefs > 0) {
            System.out.println("CHECK ODS_4: " + extCellRefs + " external cell references detected");
        }
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
        if (extCellRefs > 0) {
            System.out.println("CHANGE ODS_4: " + extCellRefs + " external cell references removed");
        }
        return extCellRefs;
    }
}
