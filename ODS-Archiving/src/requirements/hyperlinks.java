package requirements;

import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import org.odftoolkit.odfdom.doc.table.OdfTable;
import org.odftoolkit.odfdom.doc.table.OdfTableRow;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.List;

public class hyperlinks {

    // Check for hyperlinks using ODF Toolkit
    public int Check_ODFToolkit(String filepath, boolean verbose) throws Exception {
        int hyperlinks = 0;

        // Perform check
        OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(filepath);
        List<OdfTable> tables = spreadsheet.getSpreadsheetTables();
        for (OdfTable table : tables) {
            List<OdfTableRow> rows = table.getRowList();
            for (OdfTableRow row : rows) {
                NodeList cells = row.getOdfElement().getChildNodes();
                for (int i = 0; i < cells.getLength(); i++) {
                    Node cell = cells.item(i);
                    Node pNode = cell.getFirstChild();
                    if (pNode != null) {
                        Node aNode = pNode.getFirstChild();
                        if (aNode != null && aNode.getNodeName().equals("text:a")) {
                            if (aNode.getAttributes().getNamedItem("xlink:href") != null) {
                                hyperlinks++;
                                if (verbose) {
                                    System.out.println("CHECK ODS_11 VERBOSE: Sheet: " + table.getTableName() + ", Cell: unknown, Hyperlink URL: "  + aNode.getAttributes().getNamedItem("xlink:href").getNodeValue());
                                }
                            }
                        }
                    }
                }
            }
        }
        spreadsheet.close();

        // Inform user and return number
        if (hyperlinks > 0) {
            System.out.println("CHECK ODS_11: " + hyperlinks + " hyperlinks detected");
        }
        return hyperlinks;
    }

    // Change hyperlinks using ODF Toolkit
    public int Change_ODFToolkit(String filepath, boolean verbose) throws Exception {
        int hyperlinks = 0;

        // Perform change
        OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(filepath);
        List<OdfTable> tables = spreadsheet.getSpreadsheetTables();
        for (OdfTable table : tables) {
            List<OdfTableRow> rows = table.getRowList();
            for (OdfTableRow row : rows) {
                NodeList cells = row.getOdfElement().getChildNodes();
                for (int i = 0; i < cells.getLength(); i++) {
                    Node cell = cells.item(i);
                    Node pNode = cell.getFirstChild();
                    if (pNode != null) {
                        Node aNode = pNode.getFirstChild();
                        if (aNode != null && aNode.getNodeName().equals("text:a")) {
                            if (aNode.getAttributes().getNamedItem("xlink:href") != null) {

                            }
                        }
                    }
                }
            }
        }
        spreadsheet.save(filepath);
        spreadsheet.close();

        // Inform user and return number
        if (hyperlinks > 0) {
            System.out.println("CHANGE ODS_11: " + hyperlinks + " hyperlinks removed");
        }
        return hyperlinks;
    }
}
