package general;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.zeroturnaround.zip.ZipUtil;

public class create {

    // Create output filepath for file method
    public String OutputFilepath(String input_file, String output_folder, boolean archivalpackage) {
        String output_file;
        if (archivalpackage)
            output_file = output_folder + "\\1.ods";
        else if (output_folder != null)
            output_file = output_folder + "\\" + FilenameUtils.getBaseName(input_file) + ".ods";
        else
            output_file = input_file; // Must be created if only the methods check or validate are chosen
        return output_file;
    }

    // Create archival package with docCollection
    public String ArchivalPackage(String folder) {
        String packageName = folder + "\\ODSArchivingPackage";

        // Create package
        Path path = Paths.get(packageName);
        if (!Files.exists(path))
            new File(path.toString()).mkdir();
        else {
            int n = 0;
            while (Files.exists(path)) {
                n++;
                path = Paths.get(packageName + n);
            }
            new File(path.toString()).mkdir();
        }

        // Create docCollection
        String docCol = path + "\\docCollection";
        new File(docCol).mkdir();

        // Return path to archival package
        return path.toString();
    }

    // Zips the archival package
    public void ZipArchivalPackage(String output_folder) throws IOException {
        ZipUtil.pack(new File(output_folder), new File(output_folder + ".zip"));
    }

    // Create subfolder for a spreadsheet in docCollection and copy original spreadsheet
    public String ArchiveSpreadsheet(String input_filepath, String output_folder, boolean archivalpackage) throws IOException {
        // Create subfolder in docCollection
        String subPath = output_folder + "\\docCollection\\";
        int n = 1;
        Path path = Paths.get(subPath + n);
        while (Files.exists(path)) {
            n++;
            path = Paths.get(subPath + n);
        }
        new File(path.toString()).mkdir();

        // Copy original spreadsheet
        create Create = new create();
        String output_filepath = path + "\\OrgFile_" + FilenameUtils.getName(input_filepath);
        Create.CopySpreadsheet(input_filepath, output_filepath);

        // Set and return new output filepath to be used in conversion
        output_filepath = Create.OutputFilepath(input_filepath, path.toString(), archivalpackage);
        return output_filepath;
    }

    // Create copy of spreadsheet
    public void CopySpreadsheet(String input_filepath, String output_filepath) throws IOException {
        File input_file = new File(input_filepath);
        File output_file = new File(output_filepath);
        FileUtils.copyFile(input_file, output_file);

        // Inform user of copied file
        System.out.println("COPY: Spreadsheet saved to: " + output_filepath);
    }
}
