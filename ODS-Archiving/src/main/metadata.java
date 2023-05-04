package main;

import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class metadata {

    // Check for metadata using ODF Toolkit
    public boolean Check_ODFToolkit(String filepath) throws Exception {
        boolean metadata = false;

        // Perform check
        OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(filepath);
        Node node = spreadsheet.getMetaDom().getElementsByTagName("meta:document-statistic").item(0);
        if (node != null) {
            NamedNodeMap currentAttributes = node.getAttributes();
            var objectsCount = currentAttributes.getNamedItem("meta:object-count").getNodeValue();
            metadata = true;
        }
        spreadsheet.close();

        // Inform user and return boolean
        if (metadata) {
            System.out.println("CHECK: Metadata detected");
        }
        return metadata;
    }
}
