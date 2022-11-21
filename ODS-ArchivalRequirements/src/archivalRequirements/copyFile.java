package archivalRequirements;

import org.apache.commons.io.FileUtils;
import java.io.IOException;

public class copyFile {

    public String CopyFile(String input_filepath, String output_filepath) throws IOException {

        // Cope file
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
