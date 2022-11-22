package archivalRequirements;

import java.io.IOException;
import java.lang.ProcessBuilder;
import java.io.File;

public class convert {

    // Convert spreadsheet to .ods file format using LibreOffice
    public void ConvertToODS_LibreOffice(String input_filepath, String output_filepath) throws IOException, InterruptedException {

        File output_file = new File(output_filepath);
        String output_folder = output_file.getParent();

        ProcessBuilder Conversion = new ProcessBuilder ("C:\\Program Files\\LibreOffice\\program\\scalc.exe", "--headless", "--convert-to ods", input_filepath, "--outdir " + output_folder);
        Process process = Conversion.start();
        process.waitFor();
    }

    // Convert spreadsheet to .xlsx file format using LibreOffice
    public void ConvertToXLSX_LibreOffice(String input_filepath, String output_filepath) throws IOException, InterruptedException {

        File output_file = new File(output_filepath);
        String output_folder = output_file.getParent();

        ProcessBuilder Conversion = new ProcessBuilder ("C:\\Program Files\\LibreOffice\\program\\scalc.exe", "--headless", "--convert-to ods", input_filepath, "--outdir " + output_folder);
        Process process = Conversion.start();
        process.waitFor();
    }
}
