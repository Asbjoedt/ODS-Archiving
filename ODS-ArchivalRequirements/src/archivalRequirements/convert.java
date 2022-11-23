package archivalRequirements;

import java.io.IOException;
import java.lang.ProcessBuilder;
import java.io.File;

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
        return exitCode;
    }
}
