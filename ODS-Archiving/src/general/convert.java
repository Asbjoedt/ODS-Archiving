package general;

import org.apache.commons.io.*;
import java.lang.ProcessBuilder;
import java.io.*;

public class convert {

    // Convert spreadsheet to .ods file format using LibreOffice
    public int ConvertLibreOffice(String input_filepath, String output_filepath, String rename, boolean archival_package) throws IOException, InterruptedException {
        String output_folder = new File(output_filepath).getParent();
        String original_output_folder = output_folder;
        String converted_filepath = output_folder + "\\" + FilenameUtils.getBaseName(input_filepath) + ".ods";

        // Create subdir to use if renaming
        String outdir = FilenameUtils.getFullPathNoEndSeparator(output_filepath) + "\\.tempconvert";
        File subdir = new File(outdir);
        if (rename != null) {
            // Create and use subdir
            subdir.mkdir();
            output_folder = outdir;
        }

        // Set arguments
        ProcessBuilder Conversion = new ProcessBuilder (
                IO.LibreOffice_path,
                "--headless",
                "--convert-to",
                "ods",
                "--outdir",
                output_folder,
                input_filepath);

        // Start conversion
        Process process = Conversion.start();
        int exitCode = process.waitFor();

        // Rename file, move file and delete subdir, if rename is set
        if (rename != null) {
            // Create renamed filepath
            String new_filepath = original_output_folder + "\\" + rename + ".ods";

            // Move and rename file
            File output_file = new File(converted_filepath);
            output_file.renameTo(new File(new_filepath));

            // Delete subdir
            subdir.delete();
        }
        else if (archival_package) {
            // Rename file to 1.ods
            File output_file = new File(converted_filepath);
            output_file.renameTo(new File(output_filepath));
        }

        // Inform user and return exit code
        System.out.println("CONVERT: Spreadsheet converted to OpenDocument Spreadsheets (.ods) file format");
        return exitCode;
    }
}