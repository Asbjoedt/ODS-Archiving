package general;

import org.apache.commons.io.*;
import java.lang.ProcessBuilder;
import java.io.*;

public class convert {

    // Convert spreadsheet to .ods file format using LibreOffice
    public boolean ConvertLibreOffice(String input_filepath, String output_filepath, boolean archival_package) throws IOException, InterruptedException {
        String output_folder = new File(output_filepath).getParent();
        String converted_filepath = output_folder + "\\" + FilenameUtils.getBaseName(input_filepath) + ".ods";

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

        // ExitCode always gives 0 = success, it does not work as response code
        // Therefore we check if the new file exists and parse it to a boolean
        boolean success = false;
        File file = new File(converted_filepath);
        if (file.exists() && !file.isDirectory()) {
            success = true;
        }

        // Rename the file to "1.ods" if archival package
        if (archival_package) {
            File output_file = new File(converted_filepath);
            output_file.renameTo(new File(output_filepath));
        }

        // Inform user and return exit code
        if (success) {
            System.out.println("CONVERT: Spreadsheet converted to OpenDocument Spreadsheets (.ods) file format");
            System.out.println("CONVERT: Spreadsheet saved to: " + output_filepath);
        }
        else
            System.out.println("CONVERT ERROR: Conversion of spreadsheet to OpenDocument Spreadsheets (.ods) file format failed");
        return success;
    }
}