package requirements;

import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import org.odftoolkit.odfdom.dom.OdfContentDom;
import org.w3c.dom.Node;

public class digitalSignatures {

    // Check for digital signatures using ODF Toolkit
    public int Check_ODFToolkit(String input, boolean verbose) throws Exception {
        int digsigs = 0;

        // Perform check
        OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(input);
        Node node = spreadsheet.getMetaDom().getElementsByTagName("document-signatures").item(0);

        spreadsheet.close();


        // Inform user and return number
        if (digsigs > 0)
            System.out.println("CHECK ODS_DNA_1: " + digsigs + " digital signatures detected");
        return digsigs;
    }

    // Remove digital signatures using ODF Toolkit
    public int Change_ODFToolkit(String filepath, boolean verbose) throws Exception {
        int digsigs = 0;

        // Perform change
        OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(filepath);
        OdfContentDom contentDom = spreadsheet.getContentDom();
        spreadsheet.save(filepath);
        spreadsheet.close();

        // Inform user and return number
        if (digsigs > 0)
            System.out.println("CHANGE ODS_DNA_1: " + digsigs + " digital signatures removed");
        return digsigs;
    }
}
