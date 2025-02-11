package requirements;

import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import org.odftoolkit.odfdom.dom.OdfContentDom;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class dataConnections {

    // Check for data connections using ODF Toolkit
    public int Check_ODFToolkit(String filepath, boolean verbose) throws Exception {
        int conns = 0;

        // Perform check
        OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(filepath);
        OdfContentDom contentDom = spreadsheet.getContentDom();
        NodeList nodeList = contentDom.getElementsByTagName("table:database-range");
        if (nodeList != null) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node dataConnection = nodeList.item(i);
                String name = dataConnection.getAttributes().getNamedItem("table:name").getNodeValue();
                String range = dataConnection.getAttributes().getNamedItem("table:target-range-address").getNodeValue();

                conns++;
                if (verbose) System.out.println("CHECK ODS_4 VERBOSE: Data connection detected. Name: " + name + ", Range: " + range);
            }
        }
        spreadsheet.close();

        // Inform user and return number
        if (conns > 0)
            System.out.println("CHECK ODS_4: " + conns + " data connections detected");
        return conns;
    }

    // Remove data connections using ODF Toolkit
    public int Change_ODFToolkit(String filepath, boolean verbose) throws Exception {
        int conns = 0;

        // Perform change
        OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(filepath);
        OdfContentDom contentDom = spreadsheet.getContentDom();
        NodeList nodeList = contentDom.getElementsByTagName("table:database-range");
        if (nodeList != null) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node dataConnection = nodeList.item(i);
                String name = dataConnection.getAttributes().getNamedItem("table:name").getNodeValue();
                String range = dataConnection.getAttributes().getNamedItem("table:target-range-address").getNodeValue();
                System.out.println("CHANGE ODS_4 VERBOSE: Database connection removed. Name: " + name + ", Range: " + range);
                contentDom.removeChild(dataConnection);
                conns++;
            }
            spreadsheet.save(filepath);
        }
        spreadsheet.close();

        // Inform user and return number
        if (conns > 0)
            System.out.println("CHANGE ODS_4: " + conns + " data connections removed");
        return conns;
    }
}
