package archivalRequirements;

import org.apache.commons.io.*;
import java.lang.ProcessBuilder;
import java.io.*;

public class convert {

    // Convert spreadsheet to .ods file format using LibreOffice
    public int Convert_LibreOffice(String input_filepath, String output_filepath, String output_extension) throws IOException, InterruptedException {

        File output_file = new File(output_filepath);
        String output_folder = output_file.getParent();

        ProcessBuilder Conversion = new ProcessBuilder (
                "C:\\Program Files\\LibreOffice\\program\\scalc.exe",
                "--headless",
                "--convert-to",
                output_extension,
                "--outdir",
                output_folder,
                input_filepath);

        Process process = Conversion.start();
        int exitCode = process.waitFor();

        // Rename file, if set
        File input_file = new File(input_filepath);
        String output_filename = output_file.getName();
        String new_filename = output_file.getParent() + input_file.getName() + output_extension;
        System.out.println(new_filename);
        File newfilename = new File(new_filename);
        String input_filename = input_file.getName();
        if (!output_filename.equals(input_filename)) {
            output_file.renameTo(newfilename);
        }

        return exitCode;
    }
}
