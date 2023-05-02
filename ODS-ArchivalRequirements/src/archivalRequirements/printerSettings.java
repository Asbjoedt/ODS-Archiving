package archivalRequirements;

import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import org.odftoolkit.odfdom.dom.OdfSettingsDom;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class printerSettings {

    // Check for printer settings using ODF Toolkit
    public int Check_ODFToolkit(String filepath) throws Exception {
        int printers = 0;

        // Perform check
        OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(filepath);
        OdfSettingsDom settingsDom = spreadsheet.getSettingsDom();
        Node firstNode = settingsDom.getFirstChild();
        Node secondNode = firstNode.getFirstChild();
        Node thirdNode = secondNode.getLastChild();
        NodeList fourthNode = thirdNode.getChildNodes();
        for (int i = 0; i < fourthNode.getLength(); i++) {
            Node theNode = fourthNode.item(i);
            String attributeName = theNode.getAttributes().item(0).getNodeValue();
            if (attributeName.equals("PrinterName")) {
                if (theNode.getTextContent() != null) {
                    printers++;
                }
            }
        }
        spreadsheet.close();

        // Inform user and return number
        if (printers > 0) {
            System.out.println("CHECK: " + printers + " printers detected");
        }
        return printers;
    }

    // Remove printer settings using ODF Toolkit
    public int Change_ODFToolkit(String filepath) throws Exception {
        int printers = 0;

        // Perform change
        OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(filepath);
        OdfSettingsDom settingsDom = spreadsheet.getSettingsDom();
        Node firstNode = settingsDom.getFirstChild();
        Node secondNode = firstNode.getFirstChild();
        Node thirdNode = secondNode.getLastChild();
        NodeList fourthNode = thirdNode.getChildNodes();
        for (int i = 0; i < fourthNode.getLength(); i++) {
            Node theNode = fourthNode.item(i);
            String attributeName = theNode.getAttributes().item(0).getNodeValue();
            if (attributeName.equals("PrinterName")) {
                if (theNode.getTextContent() != null) {
                    printers++;
                    theNode.setTextContent("");
                }
            }
            if (attributeName.equals("PrinterSetup")) {
                if (theNode.getTextContent() != null) {
                    theNode.setTextContent("");
                }
            }
        }
        spreadsheet.close();

        // Inform user and return number
        if (printers > 0) {
            System.out.println("CHANGE: " + printers + " printers removed");
        }
        return printers;
    }

    // Check for printer settings using Apache POI
    public int Check_ApachePOI(String filepath) {
        int printers = 0;



        // Inform user and return number
        if (printers > 0) {
            System.out.println("CHECK: " + printers + " detected");
        }
        return printers;
    }

    // Remove printer settings using Apache POI
    public int Change_ApachePOI(String filepath) {
        int printers = 0;



        // Inform user and return number
        if (printers > 0) {
            System.out.println("CHANGE: " + printers + " removed");
        }
        return printers;
    }
}
