package requirements;

import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import org.odftoolkit.odfdom.dom.OdfMetaDom;
import org.w3c.dom.Node;

public class metadata {

    // Check for metadata using ODF Toolkit
    public boolean Check_ODFToolkit(String filepath, boolean verbose) throws Exception {
        boolean metadata = false;

        // Perform check
        OdfSpreadsheetDocument spreadsheet = OdfSpreadsheetDocument.loadDocument(filepath);

        Node creatorInitial = spreadsheet.getMetaDom().getElementsByTagName("meta:initial-creator").item(0);
        if (creatorInitial != null) {
            metadata = true;
            if (verbose)
                System.out.println("CHECK ODS_10 VERBOSE: Attribute \"meta:initial-creator\" detected");
        }
        Node creatorDC = spreadsheet.getMetaDom().getElementsByTagName("dc:creator").item(0);
        if (creatorDC != null) {
            metadata = true;
            if (verbose)
                System.out.println("CHECK ODS_10 VERBOSE: Attribute \"dc:creator\" detected");
        }

        spreadsheet.close();

        // Inform user and return boolean
        if (metadata)
            System.out.println("CHECK ODS_10: Creator metadata detected");
        return metadata;
    }

    // Change metadata using ODF Toolkit
    public boolean Change_ODFToolkit(String filepath, boolean verbose) throws Exception {
        boolean metadata = false;

        // Perform change
        OdfSpreadsheetDocument spreadsheet = OdfSpreadsheetDocument.loadDocument(filepath);
        OdfMetaDom metaDom = spreadsheet.getMetaDom();

        Node creatorInitial = metaDom.getElementsByTagName("meta:initial-creator").item(0);
        if (creatorInitial != null) {
            Node parentNode = creatorInitial.getParentNode();
            parentNode.removeChild(creatorInitial);
            System.out.println("CHECK ODS_10 VERBOSE: Attribute \"meta:initial-creator\" was removed");
            metadata = true;
        }
        Node creatorDC = metaDom.getElementsByTagName("dc:creator").item(0);
        if (creatorDC != null) {
            Node parentNode = creatorDC.getParentNode();
            parentNode.removeChild(creatorDC);
            System.out.println("CHECK ODS_10 VERBOSE: Attribute \"dc:creator\" was removed");
            metadata = true;
        }

        if (metadata)
            spreadsheet.save(filepath);
        spreadsheet.close();

        // Inform user and return boolean
        if (metadata)
            System.out.println("CHANGE ODS_10: Creator metadata removed");
        return metadata;
    }
}
