package requirements;

import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import org.odftoolkit.odfdom.dom.OdfSettingsDom;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class embeddedFonts {

    // Check for embedding of fonts using ODF Toolkit
    public boolean Check_ODFToolkit(String filepath, boolean verbose) throws Exception {
        boolean embedFonts = false;
        boolean FirstCheck = false;
        boolean SecondCheck = false;

        // Perform check
        OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(filepath);
        OdfSettingsDom settingsDom = spreadsheet.getSettingsDom();
        Node thirdNode = settingsDom.getFirstChild().getFirstChild().getLastChild();
        if (thirdNode != null) {
            NodeList fourthNode = thirdNode.getChildNodes();
            for (int i = 0; i < fourthNode.getLength(); i++) {
                Node theNode = fourthNode.item(i);
                String attributeName = theNode.getAttributes().item(0).getNodeValue();
                if (attributeName.equals("EmbedFonts")) {
                    if (theNode.getTextContent().equals("true")) {
                        FirstCheck = true;
                        if (verbose) {
                            System.out.println("CHECK VERBOSE: Attribute \"EmbedFonts\" in settings.xml is false");
                        }
                    }
                }
                if (attributeName.equals("EmbedOnlyUsedFonts")) {
                    if (theNode.getTextContent().equals("true")) {
                        SecondCheck = true;
                        if (verbose) {
                            System.out.println("CHECK VERBOSE: Attribute \"EmbedOnlyUsedFonts\" in settings.xml is false");
                        }
                    }
                }
            }
        }
        spreadsheet.close();

        // Change boolean according to checks
        if (!FirstCheck) {
            embedFonts = true;
        }
        else if (!SecondCheck) {
            embedFonts = true;
        }

        // Inform user and return number
        if (embedFonts) {
            System.out.println("CHECK: Embedding of fonts was NOT detected");
        }
        return embedFonts;
    }

    // Change embedding of fonts using ODF Toolkit
    public boolean Change_ODFToolkit(String filepath) throws Exception {
        boolean embedFonts = false;
        boolean FirstCheck = false;
        boolean SecondCheck = false;

        // Perform change
        OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(filepath);
        OdfSettingsDom settingsDom = spreadsheet.getSettingsDom();
        Node thirdNode = settingsDom.getFirstChild().getFirstChild().getLastChild();
        if (thirdNode != null) {
            NodeList fourthNode = thirdNode.getChildNodes();
            for (int i = 0; i < fourthNode.getLength(); i++) {
                Node theNode = fourthNode.item(i);
                String attributeName = theNode.getAttributes().item(0).getNodeValue();
                if (attributeName.equals("EmbedFonts")) {
                    if (theNode.getTextContent().equals("true")) {
                        FirstCheck = true;
                    }
                }
                if (attributeName.equals("EmbedOnlyUsedFonts")) {
                    if (theNode.getTextContent().equals("true")) {
                        SecondCheck = true;
                    }
                }
            }
        }
        spreadsheet.close();

        // Change boolean according to checks
        if (!FirstCheck) {
            embedFonts = true;
        }
        else if (!SecondCheck) {
            embedFonts = true;
        }

        // Inform user and return boolean
        if (embedFonts) {
            System.out.println("CHANGE: This application does not support embedding of fonts - Process manually");
        }
        return embedFonts;
    }
}
