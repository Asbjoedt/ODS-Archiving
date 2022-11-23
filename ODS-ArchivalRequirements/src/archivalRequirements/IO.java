package archivalRequirements;

import org.apache.commons.io.*;
import java.io.*;

public class IO {

    // Method for checking availability of input and output filepaths
    public void CheckFilepathIO(String input_filepath, String output_filepath) throws IOException {

        // Check if input filepath exists
        File input_file = new File(input_filepath);
        if (!input_file.exists() && !input_file.isDirectory()) {
            throw new IOException("Input file does not exist");
        }

        // Check if output directory exists
        String parent = input_file.getParent();
        File directory = new File(parent);
        if (!directory.exists()) {
            throw new IOException("Output directory does not exist");
        }

        // Check if output filepath has .ods file format extension
        File output_file = new File(output_filepath);
        String output_extension = FilenameUtils.getExtension(output_filepath).toLowerCase();
        if (!"ods".equals(output_extension) && !output_file.isDirectory()) {
            throw new IOException("Output filepath does not have accepted file format extension .ods");
        }

        // Check for file protection and corruption
        boolean readable = input_file.canRead();
        boolean writeable = input_file.canWrite();
        if (!readable) {
            throw new IOException("File cannot be processed e.g. has password protection, is corrupt");
        }
        if (!writeable) {
            throw new IOException("File cannot be processed e.g. has password protection, is corrupt");
        }

        // Check for accepted file format extensions
        String input_extension = FilenameUtils.getExtension(input_filepath).toLowerCase();
        switch (input_extension) {

            case "fods":
            case "ods":
            case "ots":
            case "xla":
            case "xls":
            case "xlt":
            case "xlam":
            case "xlsm":
            case "xlsx":
            case "xltm":
            case "xltx":
                // Do nothing
                break;

            default:
                throw new IOException("Input filepath does not have an accepted file format extension");
        }
    }

    // Method for checking availability of input and output folders
    public void CheckFolderIO(String input_folder, String output_folder) throws IOException {

        // Check if input directory exists
        File inputfolder = new File(input_folder);
        if (!inputfolder.exists()) {
            throw new IOException("Input directory does not exist");
        }

        // Check if output directory exists
        File outputfolder = new File(output_folder);
        if (!outputfolder.exists()) {
            throw new IOException("Output directory does not exist");
        }

        // Check for input directory protection
        boolean inputfolder_readable = inputfolder.canRead();
        boolean inputfolder_writeable = inputfolder.canWrite();
        if (!inputfolder_readable) {
            throw new IOException("Input folder cannot be processed e.g. has password protection");
        }
        if (!inputfolder_writeable) {
            throw new IOException("Input folder cannot be processed e.g. has password protection");
        }

        // Check for output directory protection
        boolean outputfolder_readable = inputfolder.canRead();
        boolean outputfolder_writeable = inputfolder.canWrite();
        if (!outputfolder_readable) {
            throw new IOException("Output folder cannot be processed e.g. has password protection");
        }
        if (!outputfolder_writeable) {
            throw new IOException("Output folder cannot be processed e.g. has password protection");
        }
    }

    // Method for copying a file
    public void CopyFile(String input_filepath, String output_filepath) throws IOException {
        File input_file = new File(input_filepath);
        File output_file = new File(output_filepath);
        FileUtils.copyFile(input_file, output_file);
    }

    // Method for deleting input file
    public void DeleteInputFile(String input_filepath) {
        File del = new File(input_filepath);
        boolean success = del.delete();
        if (success == true) {
            System.out.println("Input file was deleted");
        } else {
            System.out.println("Input file was NOT deleted");
        }
    }
}
