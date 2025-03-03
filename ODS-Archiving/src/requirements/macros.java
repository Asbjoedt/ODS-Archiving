package requirements;

import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import org.odftoolkit.odfdom.pkg.manifest.OdfManifestDom;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class macros {

    // Check for macros using ODF Toolkit
    public int Check_ODFToolkit(String filepath, boolean verbose) throws Exception {
        int macros = 0;

        // Perform check
        OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(filepath);
        OdfManifestDom manifestDom = spreadsheet.getPackage().getManifestDom();
        NodeList fileEntries = manifestDom.getFirstChild().getChildNodes();
        for (int i = 0; i < fileEntries.getLength(); i++) {
            Node fileEntry = fileEntries.item(i);
            if (fileEntry.getNodeName().equals("manifest:file-entry")) {
                if (fileEntry.getAttributes().getNamedItem("manifest:full-path").getNodeValue().startsWith("Basic"))
                    macros++;
            }
        }
        spreadsheet.close();

        // Inform user and return number
        if (macros > 0)
            System.out.println("CHECK ODS_8: " + macros + " macros detected");
        return macros;
    }

    // Change macros using ODF Toolkit
    public int Change_ODFToolkit(String filepath, boolean verbose) throws Exception {
        int macros = 0;

        // Perform change
        OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(filepath);
        OdfManifestDom manifestDom = spreadsheet.getPackage().getManifestDom();
        NodeList fileEntries = manifestDom.getFirstChild().getChildNodes();
        for (int i = 0; i < fileEntries.getLength(); i++) {
            Node fileEntry = fileEntries.item(i);
            if (fileEntry.getNodeName().equals("manifest:file-entry")) {
                if (fileEntry.getAttributes().getNamedItem("manifest:full-path").getNodeValue().startsWith("Basic")) {

                }
            }
        }
        spreadsheet.save(filepath);
        spreadsheet.close();

        // Inform user and return number
        if (macros > 0)
            System.out.println("CHANGE ODS_8: " + macros + " macros removed");
        return macros;
    }
}
