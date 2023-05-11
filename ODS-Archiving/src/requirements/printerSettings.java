package requirements;

import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import org.odftoolkit.odfdom.dom.OdfSettingsDom;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class printerSettings {

    // Check for printer settings using ODF Toolkit
    public int Check_ODFToolkit(String filepath, boolean verbose) throws Exception {
        int printers = 0;

        // Perform check
        OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(filepath);
        OdfSettingsDom settingsDom = spreadsheet.getSettingsDom();
        Node firstNode = settingsDom.getFirstChild();
        Node secondNode = firstNode.getFirstChild();
        Node thirdNode = secondNode.getLastChild();
        if (thirdNode != null) {
            NodeList fourthNode = thirdNode.getChildNodes();
            for (int i = 0; i < fourthNode.getLength(); i++) {
                Node theNode = fourthNode.item(i);
                String attributeName = theNode.getAttributes().item(0).getNodeValue();
                if (attributeName.equals("PrinterName")) {
                    if (theNode.getTextContent() != null) {
                        System.out.println("CHECK ODS_9 VERBOSE: Printer with name " + theNode.getTextContent() + " in settings.xml detected");
                        printers++;
                    }
                }
            }
        }
        spreadsheet.close();

        // Inform user and return number
        if (printers > 0) {
            System.out.println("CHECK ODS_9: " + printers + " printers detected");
        }
        return printers;
    }

    // Remove printer settings using ODF Toolkit
    public int Change_ODFToolkit(String filepath, boolean verbose) throws Exception {
        int printers = 0;

        // Perform change
        OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(filepath);
        OdfSettingsDom settingsDom = spreadsheet.getSettingsDom();
        Node thirdNode = settingsDom.getFirstChild().getFirstChild().getLastChild();
        if (thirdNode != null) {
            NodeList fourthNode = thirdNode.getChildNodes();
            for (int i = 0; i < fourthNode.getLength(); i++) {
                Node theNode = fourthNode.item(i);
                String attributeName = theNode.getAttributes().item(0).getNodeValue();
                if (attributeName.equals("PrinterName")) {
                    if (theNode.getTextContent() != null) {
                        printers++;
                        System.out.println(theNode.getTextContent());
                        settingsDom.removeChild(theNode);
                    }
                }
                if (attributeName.equals("PrinterPaperFromSetup")) {
                    if (theNode.getTextContent() != null) {
                        settingsDom.removeChild(theNode);
                    }
                }
                if (attributeName.equals("PrinterSetup")) {
                    if (theNode.getTextContent() != null) {
                        settingsDom.removeChild(theNode);
                    }
                }
            }
            spreadsheet.save(filepath);
        }
        spreadsheet.close();

        // Inform user and return number
        if (printers > 0) {
            System.out.println("CHANGE ODS_9: " + printers + " printers removed");
        }
        return printers;
    }
}
