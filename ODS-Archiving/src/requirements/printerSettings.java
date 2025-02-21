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
        Node node = settingsDom.getFirstChild().getFirstChild().getLastChild();
        if (node != null) {
            NodeList childNodes = node.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node childNode = childNodes.item(i);
                String attributeName = childNode.getAttributes().item(0).getNodeValue();
                if (attributeName.equals("PrinterName")) {
                    if (childNode.getTextContent() != null) {
                        String printer_name = childNode.getTextContent();
                        printers++;
                        if (verbose)
                            System.out.println("CHECK ODS_EXP VERBOSE: Printer with name \"" + printer_name + "\" in settings.xml detected");
                    }
                }
            }
        }
        spreadsheet.close();

        // Inform user and return number
        if (printers > 0)
            System.out.println("CHECK ODS_EXP: " + printers + " printers detected");
        return printers;
    }

    // Remove printer settings using ODF Toolkit
    public int Change_ODFToolkit(String filepath, boolean verbose) throws Exception {
        int printers = 0;

        // Perform change
        OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(filepath);
        OdfSettingsDom settingsDom = spreadsheet.getSettingsDom();
        Node node = settingsDom.getFirstChild().getFirstChild().getLastChild();
        if (node != null) {
            NodeList childNodes = node.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node childNode = childNodes.item(i);
                String attributeName = childNode.getAttributes().item(0).getNodeValue();
                if (attributeName.equals("PrinterName")) {
                    if (childNode.getTextContent() != null) {
                        printers++;
                        if (verbose)
                            System.out.println("CHANGE ODS_EXP VERBOSE: Printer with name \"" + childNode.getTextContent() + "\" in settings.xml was removed");
                        node.removeChild(childNode);
                        spreadsheet.save(filepath);
                    }
                }
            }
        }
        spreadsheet.close();

        // Inform user and return number
        if (printers > 0)
            System.out.println("CHANGE ODS_EXP: " + printers + " printers removed");
        return printers;
    }
}
