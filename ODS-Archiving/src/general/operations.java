package general;

import org.apache.commons.io.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class operations {

    // Perform operations on input filepath
    public void Filepath(String input_filepath, String output_filepath, boolean convert, boolean check, boolean change, String validate, String rename, String compliance, boolean verbose) throws Exception {

        // If not convert but change, then copy spreadsheet to output filepath
        if (!convert && change)
            CopySpreadsheet(input_filepath, output_filepath);

        // If not convert and not change, then use same input filepath for output filepath
        if (!convert && !change)
            output_filepath = input_filepath;

        // Perform operations
        if (convert) {
            general.convert Perform = new convert();
            Perform.Convert_LibreOffice(input_filepath, output_filepath, rename);
        }
        if (check) {
            general.check Perform = new check();
            Perform.Check_ODFToolkit(output_filepath, compliance, verbose);
        }
        if (change) {
            general.change Perform = new change();
            Perform.Change_ODFToolkit(output_filepath, compliance, verbose);
        }
        if (validate != null) {
            general.validate Perform = new validate();
            Perform.Validate_ODFValidator(output_filepath, validate, verbose);
        }
    }

    // Perform operations on input folder
    public void Folder(String input_folder, String output_folder, boolean recurse, boolean convert, boolean check, boolean change, String validate, String rename, String compliance, boolean verbose, boolean archivalpackage) throws Exception {

        // Enumerate files in folder based on extension and optionally recursively
        File inputfolder = new File(input_folder);
        String[] extensions = {"fods", "numbers", "ods", "ots", "xls", "xla", "xlt", "xlsx", "xlsm", "xltm", "xltx", "xlam"};
        Collection<File> enumeration = FileUtils.listFiles(inputfolder, extensions, recurse);
        
        // Iterate files in enumeration
        for (File spreadsheet : enumeration)
        {
            // Set input and output filepaths
            String input_filepath = spreadsheet.getAbsolutePath();
            String output_filepath;
            if (archivalpackage)
                output_filepath = ArchiveSpreadsheet(input_filepath, output_folder);
            else
                output_filepath = output_folder + "\\" + FilenameUtils.getBaseName(input_filepath)  + ".ods";

            try {
                // inform user of filepath to be operated on
                System.out.println("---");
                System.out.println("FILE: " + input_filepath);

                // Check IO of the filepaths
                IO IO = new IO();
                IO.CheckFilepathIO(input_filepath, output_filepath, convert);

                // Perform operations on each spreadsheet
                Filepath(input_filepath, output_filepath, convert, check, change, validate, rename, compliance, verbose);
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
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

    // Create subfolder for each file in docCollection and copy original spreadsheet
    public String ArchiveSpreadsheet(String input_filepath, String output_folder) throws IOException {
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
        String output_filepath = path + "\\" + FilenameUtils.getName(input_filepath);
        CopySpreadsheet(input_filepath, output_filepath);

        // Set and return output filepath
        return output_filepath = path + "\\1.ods";
    }

    // Copy spreadsheet
    public void CopySpreadsheet(String input_filepath, String output_filepath) throws IOException {
        File input_file = new File(input_filepath);
        File output_file = new File(output_filepath);
        FileUtils.copyFile(input_file, output_file);
    }
}
