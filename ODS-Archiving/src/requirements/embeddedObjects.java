package requirements;

import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class embeddedObjects {

    // Check for embedded objects using ODF Toolkit
    public int Check_ODFToolkit(String filepath, boolean verbose) throws Exception {
        int embedObjs = 0;

        // Perform check
        OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(filepath);
        Node node = spreadsheet.getMetaDom().getElementsByTagName("meta:document-statistic").item(0);
        if (node != null) {
            NamedNodeMap currentAttributes = node.getAttributes();
            var objectsCount = currentAttributes.getNamedItem("meta:object-count").getNodeValue();
            embedObjs = Integer.parseInt(objectsCount);
        }
        spreadsheet.close();

        // Inform user and return number
        if (embedObjs > 0)
            System.out.println("CHECK ODS_5: " + embedObjs + " embedded objects detected");
        return  embedObjs;
    }

    // Remove embedded objects using ODF Toolkit
    public int Change_ODFToolkit(String filepath, boolean verbose) throws Exception {
        int embedObjs = 0;

        OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(filepath);

        // Inform user and return number
        if (embedObjs> 0)
            System.out.println("CHANGE ODS_5: " + embedObjs + " embedded objects removed");
        return  embedObjs;
    }
}
