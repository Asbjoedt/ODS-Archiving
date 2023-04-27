package archivalRequirements;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import org.odftoolkit.odfdom.dom.OdfSettingsDom;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPathConstants;
import java.io.*;

public class activeSheet {

    // Check for absolute path to local directory using ODF Toolkit
    public boolean Check_ODFToolkit(String filepath) throws Exception {
        boolean activeSheet = false;

        OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(filepath);
        OdfSettingsDom settingsDom = spreadsheet.getSettingsDom();

        NodeList nodeList = settingsDom.getChildNodes();
        if (nodeList != null && nodeList.getLength() > 0) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node instanceof Element) {
                    if ("ActiveTable".equals(node.getNodeName())) {
                        String activeSheetName = node.getNodeValue();
                        System.out.println(activeSheetName);
                    }
                }
            }
        }
        spreadsheet.close();




        if (activeSheet) {
            System.out.println("CHECK: Active sheet was detected");
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
            System.out.println("CHANGE: Active sheet was changed");
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
            System.out.println("CHECK: Active sheet was detected");
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
            System.out.println("CHANGE: Active sheet was changed");
        }
        return  activeSheet;
    }
}
