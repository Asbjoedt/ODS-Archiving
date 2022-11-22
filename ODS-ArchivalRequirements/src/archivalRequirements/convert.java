package archivalRequirements;

import java.lang.ProcessBuilder;
import java.io.File;

public class convert {
    public void ConvertToODS_LibreOffice(String input_filepath) {

        Map<String, String> env = Conversion.environment();
        env.put("VAR1", "myValue");
        env.remove("OTHERVAR");
        env.put("VAR2", env.get("VAR1") + "suffix");
        Conversion.directory("myDir");

        File file = new File(input_filepath);
        String output_folder = file.getParent();

        ProcessBuilder Conversion = new ProcessBuilder ("C:\\Program Files\\LibreOffice\\program\\scalc.exe", "--headless", "--convert-to ods", input_filepath, "--outdir " + output_folder);
        Map<String, String> env = Conversion.environment();
        Process process = Conversion.start();
        process.waitFor();
    }
}
