package archivalRequirements;

import org.apache.poi.ss.usermodel.Workbook;
import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class absolutePath {

    // Check for absolute path to local directory using ODF Toolkit
    public boolean Check_ODFToolkit(String filepath) throws Exception {
        boolean absPath = false;

        OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(filepath);
        String absolutePath = spreadsheet.getAbsoluteFilePath(filepath);
        if (absolutePath != null) {
            absPath = true;
            System.out.println("Absolute filepath detected");
        }

        return  absPath;
    }

    // Remove absolute path to local directory using ODF Toolkit
    public boolean Change_ODFToolkit(String filepath) throws Exception {
        boolean absPath = false;

        OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(filepath);
        String absolutePath = spreadsheet.getAbsoluteFilePath(filepath);
        if (absolutePath != null) {
            absPath = true;
            // DO SOMETHING HERE
            //System.out.println("Absolute filepath removed");
        }

        return  absPath;
    }

    // Check for absolute path to local directory using Apache POI
    public boolean Check_ApachePOI(String filepath) throws IOException {
        boolean absPath = false;

        // Find spreadsheet and create workbook instance
        FileInputStream fileInput = new FileInputStream(new File(filepath));
        Operations Fetch = new Operations();
        Workbook wb = Fetch.workbookType(filepath, fileInput);



        wb.close();
        fileInput.close();

        return  absPath;
    }

    // Remove absolute path to local directory using Apache POI
    public boolean Change_ApachePOI(String filepath) {
        boolean absPath = false;

        return  absPath;
    }
}
