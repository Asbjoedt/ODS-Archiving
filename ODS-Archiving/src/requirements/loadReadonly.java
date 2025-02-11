package requirements;

import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import org.odftoolkit.odfdom.dom.OdfSettingsDom;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class loadReadonly {

    // Check for loadReadOnly using ODF Toolkit
    public boolean Check_ODFToolkit(String filepath, boolean verbose) throws Exception {
        boolean loadReadonly = false;

        // Perform check
        OdfSpreadsheetDocument spreadsheet = OdfSpreadsheetDocument.loadDocument(filepath);
        OdfSettingsDom settingsDom = spreadsheet.getSettingsDom();
        Node thirdNode = settingsDom.getFirstChild().getFirstChild().getLastChild();
        if (thirdNode != null) {
            NodeList fourthNode = thirdNode.getChildNodes();
            for (int i = 0; i < fourthNode.getLength(); i++) {
                Node theNode = fourthNode.item(i);
                String attributeName = theNode.getAttributes().item(0).getNodeValue();
                if (attributeName.equals("LoadReadonly")) {
                    if (theNode.getTextContent().equals("false")) {
                        loadReadonly = true;
                        if (verbose)
                            System.out.println("CHECK ODS_8 VERBOSE: Attribute \"LoadReadonly\" in settings.xml is false");
                    }
                }
            }
        }
        spreadsheet.close();

        // Inform user and return number
        if (loadReadonly)
            System.out.println("CHECK ODS_8: \"LoadReadonly\" NOT detected");
        return loadReadonly;
    }


    // Change loadReadOnly using ODF Toolkit
    public boolean Change_ODFToolkit(String filepath, boolean verbose) throws Exception {
        boolean loadReadonly = false;

        // Perform change
        OdfSpreadsheetDocument spreadsheet = OdfSpreadsheetDocument.loadDocument(filepath);
        OdfSettingsDom settingsDom = spreadsheet.getSettingsDom();
        Node thirdNode = settingsDom.getFirstChild().getFirstChild().getLastChild();
        if (thirdNode != null) {
            NodeList fourthNode = thirdNode.getChildNodes();
            for (int i = 0; i < fourthNode.getLength(); i++) {
                Node theNode = fourthNode.item(i);
                String attributeName = theNode.getAttributes().item(0).getNodeValue();
                if (attributeName.equals("LoadReadonly")) {
                    if (theNode.getTextContent().equals("false")) {
                        theNode.setTextContent("true");
                        loadReadonly = true;
                        spreadsheet.save(filepath);
                    }
                }
            }
        }
        spreadsheet.close();

        // Inform user and return boolean
        if (loadReadonly)
            System.out.println("CHANGE ODS_8: \"LoadReadonly\" was set as true");
        return loadReadonly;
    }
}
