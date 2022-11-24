package archivalRequirements;

import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;

public class embeddedObjects {

    // Check for embedded objects using ODF Toolkit
    public int Check_ODFToolkit(String filepath) throws Exception {
        int embedObjs = 0;

        OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(filepath);

        return  embedObjs;
    }

    // Remove embedded objects using ODF Toolkit
    public int Change_ODFToolkit(String filepath) throws Exception {
        int embedObjs = 0;

        OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(filepath);

        return  embedObjs;
    }

    // Check for embedded objects using Apache POI
    public int Check_ApachePOI(String filepath) {
        int embedObjs = 0;

        return  embedObjs;
    }

    // Remove embedded objects using Apache POI
    public int Change_ApachePOI(String filepath) {
        int embedObjs = 0;

        return  embedObjs;
    }
}
