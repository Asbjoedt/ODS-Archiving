package requirements;

import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class mimeType {

    // Check for mimetype using ODF Toolkit
    public boolean Check_ODFToolkit(String filepath, boolean verbose) throws Exception {
        boolean mimeType = false;
        boolean mimeType1 = false;
        boolean mimeType2 = false;
        String extension = FilenameUtils.getExtension(filepath).toLowerCase();

        // Perform check of mimetype file in root
        ZipFile zipFile1 = new ZipFile(filepath);
        ZipEntry zipEntry1 = zipFile1.getEntry("mimetype");
        if (zipEntry1 != null) {
            InputStream zipStream1 = zipFile1.getInputStream(zipEntry1);
            BufferedReader reader1 = new BufferedReader(new InputStreamReader(zipStream1));
            String mimetype = reader1.readLine();

            if (extension.equals("fods") || extension.equals("ods")) {
                if (mimetype.equals("application/vnd.oasis.opendocument.spreadsheet"))
                    mimeType1 = true;
            }

            if (extension.equals("ots")) {
                if (mimetype.equals("application/vnd.oasis.opendocument.spreadsheet-template"))
                    mimeType1 = true;
            }
        }

        // Perform check of META-INF/manifest.xml file
        ZipFile zipFile2 = new ZipFile(filepath);
        ZipEntry zipEntry2 = zipFile1.getEntry("META-INF/manifest.xml");
        if (zipEntry2 != null) {
            InputStream zipStream2 = zipFile1.getInputStream(zipEntry2);
            BufferedReader reader2 = new BufferedReader(new InputStreamReader(zipStream2));
            if (extension.equals("fods") || extension.equals("ods")) {
                String pattern = "manifest:media-type=\"application/vnd.oasis.opendocument.spreadsheet\"";
                Predicate<String> predicate = line -> line.contains(pattern);
                boolean match = reader2.lines().anyMatch(predicate);
                if (match) {
                    mimeType2 = true;
                }
            }
            if (extension.equals("ots")) {
                String pattern = "manifest:media-type=\"application/vnd.oasis.opendocument.spreadsheet-template\"";
                Predicate<String> predicate = line -> line.contains(pattern);
                boolean match = reader2.lines().anyMatch(predicate);
                if (match) {
                    mimeType2 = true;
                }
            }
        }

        if (mimeType1 && mimeType2)
            mimeType = true;

        if (!mimeType) {
            System.out.println("CHECK ODS_3: Incorrect MIME type detected");
            if (verbose) {
                if (!mimeType1)
                    System.out.println("CHECK ODS_3 VERBOSE: MIME type in ODS package file \"mimetype\" does not match file extension");
                if (!mimeType2)
                    System.out.println("CHECK ODS_3 VERBOSE: MIME type in ODS package file \"META-INF/manifest.xml\" does not match file extension");
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
