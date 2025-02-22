package requirements;

import org.apache.commons.io.FilenameUtils;
import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import java.io.*;

public class mimeType {

    // Check for mimetype using ODF Toolkit
    public boolean Check_ODFToolkit(String filepath, boolean verbose) throws Exception {
        boolean mimeType = false;
        boolean mimeType1 = false;
        boolean mimeType2 = false;
        String mimeTypeStr1 = "";
        String mimeTypeStr2 = "";
        String extension = FilenameUtils.getExtension(filepath).toLowerCase();

        // Perform check
        OdfSpreadsheetDocument spreadsheet = OdfSpreadsheetDocument.loadDocument(filepath);

        // Read the "mimetype" file in root of package
        InputStream stream1 = spreadsheet.getPackage().getInputStream("mimetype");
        BufferedReader reader1 = new BufferedReader(new InputStreamReader(stream1));
        mimeTypeStr1 = reader1.readLine();
        reader1.close();
        if(extension.equals("fods") && mimeTypeStr1.equals("application/vnd.oasis.opendocument.spreadsheet")) {
            mimeType1 = true;
        }
        if(extension.equals("ods") && mimeTypeStr1.equals("application/vnd.oasis.opendocument.spreadsheet")) {
            mimeType1 = true;
        }
        if(extension.equals("ots") && mimeTypeStr1.equals("application/vnd.oasis.opendocument.spreadsheet-template")) {
            mimeType1 = true;
        }

        // Read the manifest.xml file in META-INF folder
        InputStream stream2 = spreadsheet.getPackage().getInputStream("META-INF/manifest.xml");

        if(extension.equals("fods") && mimeTypeStr2.equals("application/vnd.oasis.opendocument.spreadsheet")) {
            mimeType2 = true;
        }
        if(extension.equals("ods") && mimeTypeStr2.equals("application/vnd.oasis.opendocument.spreadsheet")) {
            mimeType2 = true;
        }
        if(extension.equals("ots") && mimeTypeStr2.equals("application/vnd.oasis.opendocument.spreadsheet-template")) {
            mimeType2 = true;
        }

        if (mimeType1 && mimeType2)
            mimeType = true;

        if (!mimeType) {
            System.out.println("CHECK ODS_3: Incorrect mimetype detected");
            if (verbose) {
                if (!mimeType1)
                    System.out.println("CHECK ODS_3 VERBOSE: MimeType value in ODS package file \"mimetype\" does not match file extension");
                if (!mimeType2)
                    System.out.println("CHECK ODS_3 VERBOSE: MimeType value in ODS package file \"META-INF/manifest.xml\" does not match file extension");
            }
        }
        return mimeType;
    }

    // Change mimetype using ODF Toolkit
    public boolean Change_ODFToolkit(String filepath, boolean verbose) throws Exception {
        boolean mimeType = false;
        boolean mimeType1 = false;
        boolean mimeType2 = false;
        String mimeTypeStr1 = null;
        String mimeTypeStr2 = null;
        String extension = FilenameUtils.getExtension(filepath).toLowerCase();

        return mimeType;
    }
}
