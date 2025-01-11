package requirements;

import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import org.odftoolkit.odfdom.pkg.OdfPackageDocument;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

public class digitalSignatures {

    // Check for digital signatures using ODF Toolkit
    public int Check_ODFToolkit(String input, boolean verbose) throws Exception {
        int digsigs = 0;

        // Perform check
        OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(input);

        OdfPackageDocument signatures_file = spreadsheet.getFileDom("META-INF/documentsignatures.xml").getDocument();

        NodeList nodes = spreadsheet.getFileDom("META-INF/documentsignatures.xml").getElementsByTagName("document-signatures");
        if(nodes.getLength() > 0) {
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                System.out.println(node.getNodeValue());
                digsigs++;
            }
        }

        InputStream in = spreadsheet.getPackage().getInputStream();
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document document = docBuilder.parse(in);
        NodeList signatures = document.getElementsByTagName("Signature");

        if(signatures.getLength() > 0) {
            for (int i = 0; i < signatures.getLength(); i++) {
                Node node = signatures.item(i);
                System.out.println(node.getNodeValue());
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
