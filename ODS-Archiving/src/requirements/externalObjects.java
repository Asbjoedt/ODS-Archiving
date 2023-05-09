package requirements;

import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import org.odftoolkit.odfdom.doc.table.OdfTable;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.List;

public class externalObjects {

    // Check for external object references using ODF Toolkit
    public int Check_ODFToolkit(String filepath, boolean verbose) throws Exception {
        int extObjs = 0;

        // Perform check
        OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(filepath);
        List<OdfTable> tables = spreadsheet.getSpreadsheetTables();
        for (OdfTable table : tables) {
            NodeList nodeList = table.getOdfElement().getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeName().equals("table:table-source")) {
                    extObjs++;
                    if (verbose) {
                        System.out.println("CHECK VERBOSE ODS_4: Sheet: " + table.getTableName() + ", Object reference: " + node.getTextContent());
                    }
                }
            }
        }
        spreadsheet.close();

        // Inform user and return number
        if (extObjs > 0) {
            System.out.println("CHECK ODS_4: " + extObjs + " external objects detected");
        }
        return extObjs;
    }

    // Remove external object references using ODF Toolkit
    public int Change_ODFToolkit(String filepath, boolean verbose) throws Exception {
        int extObjs = 0;

        // Perform change
        OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(filepath);
        List<OdfTable> tables = spreadsheet.getSpreadsheetTables();
        for (OdfTable table : tables) {
            NodeList nodeList = table.getOdfElement().getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeName().equals("table:table-source")) {
                    tables.remove(table);
                    spreadsheet.save(filepath);
                }
            }
        }
        spreadsheet.close();

        // Inform user and return number
        if (extObjs > 0) {
            System.out.println("CHANGE ODS_4: " + extObjs + " external objects removed");
        }
        return extObjs;
    }
}
