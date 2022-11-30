package archivalRequirements;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;

import java.io.*;

public class embeddedObjects {

    // Check for embedded objects using ODF Toolkit
    public int Check_ODFToolkit(String filepath) throws Exception {
        int embedObjs = 0;

        OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(filepath);

        spreadsheet.getPackage().getDom("content.xml");
        spreadsheet.getContentDom().getDocument();
        spreadsheet.getSettingsDom();

        return  embedObjs;
    }

    // Remove embedded objects using ODF Toolkit
    public int Change_ODFToolkit(String filepath) throws Exception {
        int embedObjs = 0;

        OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(filepath);

        return  embedObjs;
    }

    // Check for embedded objects using Apache POI
    public int Check_ApachePOI(String filepath) throws IOException, OpenXML4JException {
        int embedObjs = 0;

        FileInputStream fileInput = new FileInputStream(filepath);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInput);

        for (PackagePart pPart : workbook.getAllEmbeddedParts()) {
            embedObjs++;
        }

        // Close file
        workbook.close();
        fileInput.close();

        // Inform user and return number
        if (embedObjs> 0) {
            System.out.println(embedObjs + " embedded objects detected");
        }
        return  embedObjs;
    }

    // Remove embedded objects using Apache POI
    public int Change_ApachePOI(String filepath) throws IOException, OpenXML4JException {
        int embedObjs = 0;

        FileInputStream fileInput = new FileInputStream(filepath);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInput);

        for (PackagePart pPart : workbook.getAllEmbeddedParts()) {
            embedObjs++;
            pPart.getPackage().removePart(pPart);
        }

        // Save and close file
        FileOutputStream fileOutput = new FileOutputStream(filepath);
        workbook.write(fileOutput);
        workbook.close();
        fileOutput.close();
        fileInput.close();

        // Inform user and return number
        if (embedObjs> 0) {
            System.out.println(embedObjs + " embedded objects removed");
        }
        return  embedObjs;
    }
}
