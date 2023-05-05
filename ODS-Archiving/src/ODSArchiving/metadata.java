package ODSArchiving;

import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class metadata {

    // Check for metadata using ODF Toolkit
    public boolean Check_ODFToolkit(String filepath) throws Exception {
        boolean metadata = false;

        // Perform check
        OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(filepath);
        Node cdd = spreadsheet.getMetaDom().getElementById("office:meta").getFirstChild();
        System.out.println(cdd);

        NodeList childNodes = spreadsheet.getMetaDom().getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node childNode = childNodes.item(i);
            System.out.println(childNode.getNodeName());
        }
        spreadsheet.close();

        // Inform user and return boolean
        if (metadata) {
            System.out.println("CHECK: Metadata detected");
        }
        return metadata;
    }
}
