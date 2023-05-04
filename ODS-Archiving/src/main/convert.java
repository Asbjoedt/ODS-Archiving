package main;

import org.apache.commons.io.*;
import java.lang.ProcessBuilder;
import java.io.*;
import java.nio.file.*;

public class convert {

    // Convert spreadsheet to .ods file format using LibreOffice
    public int Convert_LibreOffice(String input_filepath, String output_filepath) throws IOException, InterruptedException {
        File output_file = new File(output_filepath);
        String output_folder = output_file.getParent();

        // Set arguments
        ProcessBuilder Conversion = new ProcessBuilder (
                "C:\\Program Files\\LibreOffice\\program\\scalc.exe",
                "--headless",
                "--convert-to",
                "ods",
                "--outdir",
                output_folder,
                input_filepath);

        // Start conversion
        Process process = Conversion.start();
        int exitCode = process.waitFor();

        // Rename file, if set
        String output_filename = FilenameUtils.getBaseName(output_filepath);
        String input_filename = FilenameUtils.getBaseName(input_filepath);
        Path old_filename = Paths.get(output_file.getParent() + "\\" + input_filename + ".ods");
        String new_filename = output_file.getParent() + "\\" + output_filename + ".ods";
        if (!output_filename.equals(input_filename)) {
            Files.move(old_filename, old_filename.resolveSibling(new_filename));
            if (!output_file.exists()) {
                throw new IOException("Converted spreadsheet could not be renamed to output filepath");
            }
        }

        // Inform user and return exit code
        System.out.println("CONVERT: Spreadsheet converted to .ods file format");
        return exitCode;
    }
}
