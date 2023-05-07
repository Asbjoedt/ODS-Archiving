package requirements;

import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import org.odftoolkit.odfdom.dom.OdfMetaDom;
import org.w3c.dom.Node;

public class metadata {

    // Check for metadata using ODF Toolkit
    public boolean Check_ODFToolkit(String filepath) throws Exception {
        boolean metadata = false;

        // Perform check
        OdfSpreadsheetDocument spreadsheet = OdfSpreadsheetDocument.loadDocument(filepath);
        Node creator = spreadsheet.getMetaDom().getElementsByTagName("meta:initial-creator").item(0);
        if (creator != null) {
            metadata = true;
        }
        spreadsheet.close();

        // Inform user and return boolean
        if (metadata) {
            System.out.println("CHECK: Metadata detected");
        }
        return metadata;
    }

    // Change metadata using ODF Toolkit
    public boolean Change_ODFToolkit(String filepath) throws Exception {
        boolean metadata = false;

        // Perform change
        OdfSpreadsheetDocument spreadsheet = OdfSpreadsheetDocument.loadDocument(filepath);
        OdfMetaDom metaDom = spreadsheet.getMetaDom();
        Node creator = metaDom.getElementsByTagName("meta:initial-creator").item(0);
        if (creator.hasChildNodes()) {
            metaDom.removeChild(creator);
            metadata = true;
        }
        spreadsheet.save(filepath);
        spreadsheet.close();

        // Inform user and return boolean
        if (metadata) {
            System.out.println("CHANGE: Metadata removed");
        }
        return metadata;
    }
}
