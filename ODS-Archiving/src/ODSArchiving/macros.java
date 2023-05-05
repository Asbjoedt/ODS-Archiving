package ODSArchiving;

import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import org.odftoolkit.odfdom.pkg.manifest.OdfManifestDom;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class macros {

    // Check for macros using ODF Toolkit
    public int Check_ODFToolkit(String filepath) throws Exception {
        int macros = 0;

        // Perform check
        OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(filepath);
        OdfManifestDom manifestDom = spreadsheet.getPackage().getManifestDom();
        NodeList fileEntries = manifestDom.getFirstChild().getChildNodes();
        for (int i = 0; i < fileEntries.getLength(); i++) {
            Node fileEntry = fileEntries.item(i);
            if (fileEntry.getNodeName().equals("manifest:file-entry")) {
                if (fileEntry.getAttributes().getNamedItem("manifest:full-path").getNodeValue().startsWith("Basic")) {
                    macros++;
                }
            }
        }

        // Inform user and return number
        if (macros > 0) {
            System.out.println("CHECK: " + macros + " macros detected");
        }
        return macros;
    }
}
