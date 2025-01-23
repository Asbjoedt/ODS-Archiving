package requirements;

import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import org.odftoolkit.odfdom.pkg.OdfFileDom;
import org.odftoolkit.odfdom.pkg.OdfPackage;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.InputStream;

public class digitalSignatures {

    // Check for digital signatures using ODF Toolkit
    public int Check_ODFToolkit(String input, boolean verbose) throws Exception {
        int digsigs = 0;

        // Perform check
        OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(input);
        OdfPackage odfPackage = spreadsheet.getPackage();

        // Access the META-INF/documentsignatures.xml file
        InputStream signatureStream = odfPackage.getInputStream("META-INF/documentsignatures.xml");

        if (signatureStream != null) {
            // Read the content of the documentsignatures.xml
            String signatureContent = new String(signatureStream.readAllBytes());
            System.out.println("Contents of META-INF/documentsignatures.xml:");
            System.out.println(signatureContent);

            // Close the input stream
            signatureStream.close();
        }

        OdfFileDom digsigFile = spreadsheet.getFileDom("META-INF/documentsignatures.xml");
        if(digsigFile  != null) {

        }
        NodeList signatures = digsigFile.getElementsByTagName("document-signatures");
        if(signatures.getLength() > 0) {
            for (int i = 0; i < signatures.getLength(); i++) {
                Node signature = signatures.item(i);
                System.out.println(signature.getNodeValue());
                digsigs++;
            }
        }
        spreadsheet.close();

        // Inform user and return number
        if (digsigs > 0)
            System.out.println("CHECK ODS_DNA_1: " + digsigs + " digital signatures detected");
        return digsigs;
    }

    // Remove digital signatures using ODF Toolkit
    public int Change_ODFToolkit(String filepath, boolean verbose) throws Exception {
        int digsigs = 0;

        // Perform change
        OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(filepath);


        spreadsheet.save(filepath);
        spreadsheet.close();

        // Inform user and return number
        if (digsigs > 0)
            System.out.println("CHANGE ODS_DNA_1: " + digsigs + " digital signatures removed");
        return digsigs;
    }
}
