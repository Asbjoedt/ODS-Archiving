package archivalRequirements;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import org.odftoolkit.odfdom.dom.OdfContentDom;
import org.odftoolkit.odfdom.dom.OdfMetaDom;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.*;

public class embeddedObjects {

    // Check for embedded objects using ODF Toolkit
    public int Check_ODFToolkit(String filepath) throws Exception {
        int embedObjs = 0;

        OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(filepath);
        OdfContentDom contentDom = spreadsheet.getContentDom();
        OdfMetaDom metaDom = spreadsheet.getMetaDom();
        NodeList docStatistics = metaDom.getElementsByTagName("meta:document-statistic");
        for (int i = 0; i < docStatistics.getLength(); i++) {
            Node currentNode = docStatistics.item(i);
            System.out.println(currentNode.getNodeName());
            NamedNodeMap currentAttributes = currentNode.getAttributes();
            for (int ii = 0; ii < currentAttributes.getLength(); ii++) {
                Node currentAttribute = currentAttributes.item(i);
                System.out.println(currentAttributes.getLength());
                if (currentAttribute.getNodeName().equals("meta:object-count")) {
                    String value = currentAttribute.getNodeValue();
                    System.out.println(value);
                    if (value != null || value != "0") {
                        embedObjs = Integer.parseInt(value);
                    }
                }
            }
        }
        spreadsheet.close();

        // Inform user and return number
        if (embedObjs> 0) {
            System.out.println("CHECK: " + embedObjs + " embedded objects detected");
        }
        return  embedObjs;
    }

    // Remove embedded objects using ODF Toolkit
    public int Change_ODFToolkit(String filepath) throws Exception {
        int embedObjs = 0;

        OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(filepath);

        // Inform user and return number
        if (embedObjs> 0) {
            System.out.println("CHANGE: " + embedObjs + " embedded objects removed");
        }
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
            System.out.println("CHECK: " + embedObjs + " embedded objects detected");
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
            System.out.println("CHANGE: " + embedObjs + " embedded objects removed");
        }
        return  embedObjs;
    }
}
