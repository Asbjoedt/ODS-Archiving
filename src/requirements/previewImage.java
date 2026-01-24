package requirements;

import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import java.io.InputStream;

public class previewImage {

    // Check for preview image using ODF Toolkit
    public boolean Check_ODFToolkit(String filepath, boolean verbose) throws Exception {
        boolean previewImage = false;

        // Perform check
        OdfSpreadsheetDocument spreadsheet = OdfSpreadsheetDocument.loadDocument(filepath);
        InputStream thumbnail = spreadsheet.getPackage().getInputStream("Thumbnails/thumbnail.png");

        if (thumbnail != null) {
            previewImage = true;
            thumbnail.close();
        }

        spreadsheet.close();

        // Inform user and return number
        if (previewImage)
            System.out.println("CHECK ODS_13: Preview image (thumbnail) detected");
        return previewImage;
    }

    // Change preview image using ODF Toolkit
    public boolean Change_ODFToolkit(String filepath, boolean verbose) throws Exception {
        boolean previewImage = false;

        // Perform change
        OdfSpreadsheetDocument spreadsheet = OdfSpreadsheetDocument.loadDocument(filepath);
        InputStream thumbnail = spreadsheet.getPackage().getInputStream("Thumbnails/thumbnail.png");

        if (thumbnail != null) {
            thumbnail.close();
            spreadsheet.removeDocument("Thumbnails/thumbnail.png"); // DOES NOT WORK
            spreadsheet.save(filepath);
            previewImage = true;
        }

        spreadsheet.close();

        // Inform user and return number
        if (previewImage)
            System.out.println("CHANGE ODS_13: Preview image (thumbnail) removed");

        return previewImage;
    }
}
