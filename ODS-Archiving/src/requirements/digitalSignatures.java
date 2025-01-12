package requirements;

import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import org.odftoolkit.odfdom.pkg.OdfFileDom;
import org.odftoolkit.odfdom.pkg.OdfPackage;
import org.odftoolkit.odfdom.pkg.OdfPackageDocument;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.InputStream;

public class digitalSignatures {

    // Check for digital signatures using ODF Toolkit
    public int Check_ODFToolkit(String input, boolean verbose) throws Exception {
        int digsigs = 0;

        try {
            // Load the ODS package
            OdfPackage odfPackage = OdfPackage.loadPackage(input);
            System.out.println("Files in the ODF package:");
            for (String file : odfPackage.getFilePaths()) {
                System.out.println(file);
                }

            // Access the META-INF/documentsignatures.xml file
            InputStream signatureStream = odfPackage.getInputStream("META-INF/documentsignatures.xml");

            if (signatureStream != null) {
                // Read the content of the documentsignatures.xml
                String signatureContent = new String(signatureStream.readAllBytes());
                System.out.println("Contents of META-INF/documentsignatures.xml:");
                System.out.println(signatureContent);

                // Close the input stream
                signatureStream.close();
            } else {
                System.out.println("META-INF/documentsignatures.xml not found in the package.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Perform check
        OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(input);
        OdfPackage odfPackage = spreadsheet.getPackage();

        try {
            OdfFileDom digsigFile = spreadsheet.getFileDom("META-INF/documentsignatures.xml");
            if(digsigFile  != null) {
                System.out.println("lol");
            }
            NodeList signatures = digsigFile.getElementsByTagName("document-signatures");
            if(signatures.getLength() > 0) {
                for (int i = 0; i < signatures.getLength(); i++) {
                    Node signature = signatures.item(i);
                    System.out.println(signature.getNodeValue());
                    digsigs++;
                }
            }

            OdfPackageDocument signaturesFile = spreadsheet.getFileDom("META-INF/documentsignatures.xml").getDocument();
            if(signaturesFile  != null) {
                System.out.println("lol2");
            }

            InputStream digsigStream = odfPackage.getInputStream("META-INF/manifest.xml");
            if (digsigStream != null) {
                String digsigContent = new String(digsigStream.readAllBytes());
                System.out.println(digsigContent);

                // Close the input stream
                digsigStream.close();
            } else {
                System.out.println("META-INF/manifest.xml not found in the package.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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
