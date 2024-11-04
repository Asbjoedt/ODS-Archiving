package general;

import org.apache.commons.io.*;
import java.io.*;
import java.util.*;

public class operations {

    // Perform operations on input filepath
    public void Filepath(String input_filepath, String output_filepath, boolean convert, boolean check, boolean change, String validate, String rename, String conformance, boolean verbose, boolean archival_package) throws Exception {

        // If not convert but change, then copy spreadsheet to output filepath
        if (!convert && change) {
            create Create = new create();
            Create.CopySpreadsheet(input_filepath, output_filepath);
        }

        // Perform operations
        if (convert) {
            general.convert Perform = new convert();
            Perform.Convert_LibreOffice(input_filepath, output_filepath, rename, archival_package);
        }
        if (check) {
            general.check Perform = new check();
            Perform.Check_ODFToolkit(output_filepath, conformance, verbose);
        }
        if (change) {
            general.change Perform = new change();
            Perform.Change_ODFToolkit(output_filepath, conformance, verbose);
        }
        if (validate != null) {
            general.validate Perform = new validate();
            Perform.Validate_OPFValidator(output_filepath, conformance, verbose);
        }
    }

    // Perform operations on input folder
    public void Folder(String input_folder, String output_folder, boolean recurse, boolean convert, boolean check, boolean change, String validate, String rename, String conformance, boolean verbose, boolean archival_package) throws Exception {

        // Enumerate files in folder based on extension and optionally recursively
        File inputfolder = new File(input_folder);
        String[] extensions = {"fods", "numbers", "gsheet", "ods", "ots", "xla", "xls", "xlt", "xlsb", "xlsx", "xlsm", "xltm", "xltx", "xlam"};
        Collection<File> enumeration = FileUtils.listFiles(inputfolder, extensions, recurse);
        
        // Iterate files in enumeration
        for (File spreadsheet : enumeration)
        {
            // Set input filepath
            String input_filepath = spreadsheet.getAbsolutePath();

            // inform user of input filepath
            System.out.println("---");
            System.out.println("FILE: " + input_filepath);

            try {
                // Set output filepath
                create Create = new create();
                String output_filepath;
                if (archival_package)
                    output_filepath = Create.ArchiveSpreadsheet(input_filepath, output_folder, rename, archival_package);
                else
                    output_filepath = Create.OutputFilepath(input_filepath, output_folder, rename, archival_package);

                // Check IO of the filepaths
                IO IO = new IO();
                IO.CheckFilepathIO(input_filepath, output_filepath, convert);

                // Perform operations on each spreadsheet
                Filepath(input_filepath, output_filepath, convert, check, change, validate, rename, conformance, verbose, archival_package);
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
