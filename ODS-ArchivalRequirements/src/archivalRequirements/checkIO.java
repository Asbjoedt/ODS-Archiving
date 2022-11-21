package archivalRequirements;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import org.odftoolkit.*;

public class checkIO {
    public String Filepath(String input_filepath, String output_filepath) throws IOException {
        String filepath = "";

        // Check if input filepath exists
        File f = new File(input_filepath);
        if (!f.exists() && !f.isDirectory()) {
            throw new IOException("File does not exist");
        }

        // Check if output directory exists


        // Check for file protection
        try {
            OdfSpreadsheetDocument spreadsheet = (OdfSpreadsheetDocument) org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument.loadDocument(input_filepath);
        } catch (IOException) {
            throw new IOException("File cannot be read e.g. has password protection, is corrupt");
        }

        // Extract input filepath extension for switch
        String input_extension = input_filepath.substring(input_filepath.lastIndexOf('.') + 1);
        // Check for accepted file format extension
        switch (input_extension.toLowerCase()) {

            case "fods":
            case "ods":
            case "ots":
                // Do nothing
                break;

            default:
                throw new IOException("Input filepath does not have an accepted file format extension. Accepted file format extensions are: .ods, .ots and .fods");
        }

        // Copy file, if output filepath is set
        if (output_filepath == null) {
            filepath = copyFile(input_filepath, output_filepath);
        }
        // Else use input filepath for operations
        else if (output_filepath != null) {
            filepath = input_filepath;
        }

        return filepath;
    }

    public String CopyFile(String input_filepath, String output_filepath) throws IOException {

        // Copy file
        File copied = new File(input_filepath);
        FileUtils.copyFile(original, copied);

        // Check for correct copying
        assertThat(copied).exists();
        assertThat(Files.readAllLines(original.toPath())
                .equals(Files.readAllLines(copied.toPath())));

        // Return copied filepath,if successful
        return output_filepath;
    }
}
