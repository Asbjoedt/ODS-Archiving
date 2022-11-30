package archivalRequirements;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import java.io.*;

public class activeSheet {

    // Check for absolute path to local directory using ODF Toolkit
    public boolean Check_ODFToolkit(String filepath) throws Exception {
        boolean activeSheet = false;

        OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(filepath);

        spreadsheet.close();

        if (activeSheet) {
            System.out.println("Active sheet was detected");
        }
        return activeSheet;
    }

    // Remove absolute path to local directory using ODF Toolkit
    public boolean Change_ODFToolkit(String filepath) throws Exception {
        boolean activeSheet = false;

        OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(filepath);

        spreadsheet.save(filepath);
        spreadsheet.close();

        if (activeSheet) {
            System.out.println("Active sheet was changed");
        }
        return  activeSheet;
    }

    // Check for absolute path to local directory using Apache POI
    public boolean Check_ApachePOI(String filepath) throws IOException {
        boolean activeSheet = false;

        // Find spreadsheet and create workbook instance
        FileInputStream fileInput = new FileInputStream(filepath);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInput);

        // Perform check
        int activatedsheet = workbook.getActiveSheetIndex();
        if (activatedsheet > 0) {
            activeSheet = true;
        }

        // Close spreadsheet and return result
        workbook.close();
        fileInput.close();
        if (activeSheet) {
            System.out.println("Active sheet was detected");
        }
        return  activeSheet;
    }

    // Remove absolute path to local directory using Apache POI
    public boolean Change_ApachePOI(String filepath) throws IOException {
        boolean activeSheet = false;

        // Find spreadsheet and create workbook instance
        FileInputStream fileInput = new FileInputStream(new File(filepath));
        XSSFWorkbook workbook = new XSSFWorkbook(fileInput);

        // Perform check and change if not first sheet
        int activatedsheet = workbook.getActiveSheetIndex();
        if (activatedsheet > 0) {
            activeSheet = true;
            workbook.setActiveSheet(0);
        }

        // Save and close file
        FileOutputStream fileOutput = new FileOutputStream(filepath);
        workbook.write(fileOutput);
        workbook.close();
        fileOutput.close();
        fileInput.close();

        // Inform user and return result
        if (activeSheet) {
            System.out.println("Active sheet was changed");
        }
        return  activeSheet;
    }
}
