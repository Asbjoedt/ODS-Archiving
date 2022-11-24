package archivalRequirements;

import org.apache.poi.ss.usermodel.Workbook;
import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import java.io.*;

public class activeSheet {

    // Check for absolute path to local directory using ODF Toolkit
    public boolean Check_ODFToolkit(String filepath) throws Exception {
        boolean activeSheet = false;

        OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(filepath);


        return activeSheet;
    }

    // Remove absolute path to local directory using ODF Toolkit
    public boolean Change_ODFToolkit(String filepath) throws Exception {
        boolean activeSheet = false;

        OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(filepath);

        return  activeSheet;
    }

    // Check for absolute path to local directory using Apache POI
    public boolean Check_ApachePOI(String filepath) throws IOException {
        boolean activeSheet = false;

        // Find spreadsheet and create workbook instance
        FileInputStream fileInput = new FileInputStream(new File(filepath));
        Operations Fetch = new Operations();
        Workbook wb = Fetch.workbookType(filepath, fileInput);

        // Perform check
        int activatedsheet = wb.getActiveSheetIndex();
        if (activatedsheet > 0) {
            activeSheet = true;
        }

        // Close spreadsheet and return result
        wb.close();
        fileInput.close();
        return  activeSheet;
    }

    // Remove absolute path to local directory using Apache POI
    public boolean Change_ApachePOI(String filepath) throws IOException {
        boolean activeSheet = false;

        // Find spreadsheet and create workbook instance
        FileInputStream fileInput = new FileInputStream(new File(filepath));
        Operations Fetch = new Operations();
        Workbook wb = Fetch.workbookType(filepath, fileInput);

        // Perform check and change if not first sheet
        int activatedsheet = wb.getActiveSheetIndex();
        if (activatedsheet > 0) {
            activeSheet = true;
            wb.setActiveSheet(0);
        }

        // Close spreadsheet and return result
        wb.close();
        fileInput.close();
        return  activeSheet;
    }
}
