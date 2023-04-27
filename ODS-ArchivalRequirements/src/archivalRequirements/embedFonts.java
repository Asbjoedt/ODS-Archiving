package archivalRequirements;

import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import org.odftoolkit.odfdom.dom.OdfSettingsDom;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class embedFonts {

    // Check for embedding of fonts using ODF Toolkit
    public boolean Check_ODFToolkit(String filepath) throws Exception {
        boolean embedFonts = false;
        boolean FirstCheck = false;
        boolean SecondCheck = false;

        OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(filepath);
        OdfSettingsDom settingsDom = spreadsheet.getSettingsDom();
        NodeList nodeList = settingsDom.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node currentNode = nodeList.item(i);
            if (currentNode.getNodeName().equals("EmbedFonts") && currentNode.getNodeValue().equals("false")) {
                System.out.println("ddd");
            }
            if (currentNode.getNodeName().equals("EmbedFonts") && currentNode.getNodeValue().equals("true")) {
                FirstCheck = true;
            }
            if (currentNode.getNodeName().equals("EmbedOnlyUsedFonts") && currentNode.getNodeValue().equals("true")) {
                SecondCheck = true;
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
            System.out.println("CHECK: Embedding of fonts NOT detected");
        }
        return embedFonts;
    }
}
