package requirements;

import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import org.odftoolkit.odfdom.doc.table.OdfTable;
import org.odftoolkit.odfdom.dom.OdfSettingsDom;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class activeSheet {

    // Check if first sheet is active sheet using ODF Toolkit
    public boolean Check_ODFToolkit(String filepath, boolean verbose) throws Exception {
        boolean activeSheet = false;

        // Perform check
        OdfSpreadsheetDocument spreadsheet = OdfSpreadsheetDocument.loadDocument(filepath);
        OdfTable firstTable = spreadsheet.getSpreadsheetTables().get(0);
        OdfSettingsDom settingsDom = spreadsheet.getSettingsDom();
        Node thirdNode = settingsDom.getFirstChild().getFirstChild().getLastChild();
        if (thirdNode != null) {
            NodeList fourthNode = thirdNode.getChildNodes();
            for (int i = 0; i < fourthNode.getLength(); i++) {
                Node theNode = fourthNode.item(i);
                String attributeName = theNode.getAttributes().item(0).getNodeValue();
                if (attributeName.equals("ActiveTable")) {
                    if (!theNode.getTextContent().equals(firstTable.getTableName())) {
                        activeSheet = true;
                        if (verbose) {
                            System.out.println("CHECK ODS_TEST VERBOSE: sheet " + theNode.getTextContent() + " is active");
                        }
                    }
                }
            }
        }
        spreadsheet.close();

        if (activeSheet) {
            System.out.println("CHECK ODS_TEST: Active sheet NOT first sheet was detected");
        }
        return activeSheet;
    }

    // Change if first sheet is not active sheet using ODF Toolkit
    public boolean Change_ODFToolkit(String filepath, boolean verbose) throws Exception {
        boolean activeSheet = false;

        // Perform change
        OdfSpreadsheetDocument spreadsheet = OdfSpreadsheetDocument.loadDocument(filepath);
        OdfTable firstTable = spreadsheet.getSpreadsheetTables().get(0);
        OdfSettingsDom settingsDom = spreadsheet.getSettingsDom();
        Node thirdNode = settingsDom.getFirstChild().getFirstChild().getLastChild();
        if (thirdNode != null) {
            NodeList fourthNode = thirdNode.getChildNodes();
            for (int i = 0; i < fourthNode.getLength(); i++) {
                Node theNode = fourthNode.item(i);
                String attributeName = theNode.getAttributes().item(0).getNodeValue();
                if (attributeName.equals("ActiveTable")) {
                    if (!theNode.getTextContent().equals(firstTable.getTableName())) {
                        activeSheet = true;
                        theNode.setTextContent(firstTable.getTableName());
                        spreadsheet.save(filepath);
                    }
                }
            }
        }
        spreadsheet.close();

        if (activeSheet) {
            System.out.println("CHANGE ODS_TEST: Active sheet was changed");
        }
        return  activeSheet;
    }
}
