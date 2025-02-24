package general;

import org.apache.commons.io.*;
import java.io.*;
import java.util.*;

public class operations {

    // Perform operations on input filepath
    public void Filepath(String input_filepath, String output_filepath, String output_folder, boolean convert, boolean check, boolean change, boolean validate, String conformance, boolean report, boolean verbose, boolean archival_package) throws Exception {
        if (convert) {
            convert Perform = new convert();
            boolean result = Perform.ConvertLibreOffice(input_filepath, output_filepath, archival_package);

            // Report conversion
            if(report) {
                reporting Report = new reporting();
                Report.ReportConversion(input_filepath, output_filepath, output_folder, result);
            }
        }
        if (check) {
            check Perform = new check();
            List<check.checkList> result = Perform.CheckODFToolkit(output_filepath, conformance, verbose);

            // Report on checking
            if(report) {
                reporting Report = new reporting();
                Report.ReportCheck(input_filepath, output_filepath, output_folder, result);
            }
        }
        if (change) {
            change Perform = new change();
            List<change.changeList> result = Perform.ChangeODFToolkit(output_filepath, conformance, verbose);

            // Report on changing
            if(report) {
                reporting Report = new reporting();
                Report.ReportChange(input_filepath, output_filepath, output_folder, result);
            }
        }
        if (validate) {
            validate Perform = new validate();
            boolean result = Perform.ValidateOPFValidator(output_filepath, conformance, verbose);

            // Report on validation
            if(report) {
                reporting Report = new reporting();
                Report.ReportValidation(input_filepath, output_filepath, output_folder, result);
            }
        }
    }

    // Perform operations on input folder
    public void Folder(String input_folder, String output_folder, boolean recurse, boolean convert, boolean check, boolean change, boolean validate, String conformance, boolean report, boolean verbose, boolean archival_package) throws Exception {

        // Enumerate files in folder based on extension and optionally recursively
        File inputfolder = new File(input_folder);
        String[] extensions = { "fods", "numbers", "gsheet", "ods", "ots", "xla", "xls", "xlt", "xlsb", "xlsx", "xlsm", "xltm", "xltx", "xlam" };
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
                    output_filepath = Create.ArchiveSpreadsheet(input_filepath, output_folder, archival_package);
                else
                    output_filepath = Create.OutputFilepath(input_filepath, output_folder, archival_package);

                // Check availability of filepath
                IO IO = new IO();
                IO.CheckFilepathIO(input_filepath, output_filepath, convert);
                IO.CheckBasicReadability(input_filepath);

                // Copy spreadsheet under certain conditions for conversion, change or archival package
                if (!convert && change)
                    Create.CopySpreadsheet(input_filepath, output_filepath);

                // Perform operations on each spreadsheet
                Filepath(input_filepath, output_filepath, output_folder, convert, check, change, validate, conformance, report, verbose, archival_package);
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
