package main;

import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import org.odftoolkit.odfdom.dom.OdfSettingsDom;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class activeSheet {

    // Check if first sheet is active sheet using ODF Toolkit
    public boolean Check_ODFToolkit(String filepath) throws Exception {
        boolean activeSheet = false;

        // Perform check
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
            System.out.println("CHECK: Active sheet NOT first sheet was detected");
        }
        return activeSheet;
    }

    // Change if first sheet is not active sheet using ODF Toolkit
    public boolean Change_ODFToolkit(String filepath) throws Exception {
        boolean activeSheet = false;

        // Perform change
        OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(filepath);

        spreadsheet.save(filepath);
        spreadsheet.close();

        if (activeSheet) {
            System.out.println("CHANGE: Active sheet was changed");
        }
        return  activeSheet;
    }
}
