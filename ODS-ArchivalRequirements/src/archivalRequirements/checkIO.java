package archivalRequirements;

import org.apache.commons.io.*;
import java.io.*;

public class checkIO {
    public String Filepath(String input_filepath, String output_filepath) throws IOException {
        String filepath = "";

        // Check if input filepath exists
        File file = new File(input_filepath);
        if (!file.exists() && !file.isDirectory()) {
            throw new IOException("Input file does not exist");
        }

        // Check if output directory exists
        String parent = file.getParent();
        File directory = new File(parent);
        if (!directory.exists()) {
            throw new IOException("Output directory does not exist");
        }

        // Check for file protection
        boolean writeable = file.canWrite();
        if (!writeable) {
            throw new IOException("File cannot be read e.g. has password protection, is corrupt");
        }

        // Check for accepted file format extension
        String input_extension = FilenameUtils.getExtension(input_filepath);
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
        if (output_filepath != null) {
            filepath = copyFile(input_filepath, output_filepath);
        }
        // Else use input filepath for operations
        else if (output_filepath == null) {
            filepath = input_filepath;
        }

        return filepath;
    }

    // Method for copying files
    private static String copyFile(String input_filepath, String output_filepath) throws IOException {

        File input_file = new File(input_filepath);
        File output_file = new File(output_filepath);
        FileUtils.copyFile(input_file, output_file);

        return output_filepath;
    }
}
