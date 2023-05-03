package main;

import org.apache.commons.io.*;
import java.io.*;
import java.util.*;

public class operations {

    // Perform operations on input filepath
    public void Filepath(String input_filepath, String output_filepath, boolean convert, boolean check, boolean change, boolean validate) throws Exception {
        IO IO = new IO();

        // Get file format extension output filepath
        String input_extension = FilenameUtils.getExtension(input_filepath).toLowerCase();
        String output_extension = FilenameUtils.getExtension(output_filepath).toLowerCase();

        // Perform convert operations, if selected
        if (convert) {
            convert Perform = new convert();
            Perform.Convert_LibreOffice(input_filepath, output_filepath);
        }
        // If not selected, copy to output filepath if it is set
        if (convert == false) {
            if (output_filepath != null && !output_filepath.equals(input_filepath) && output_extension.equals(input_extension)) {
                IO.CopyFile(input_filepath, output_filepath);
            }
            else if (output_filepath != null && !output_filepath.equals(input_filepath) && !output_extension.equals(input_extension)) {
                throw new IOException("You must convert spreadsheet to designated output extension. Use argument --convert " + output_extension);
            }
        }

        if (check) {
            check Perform = new check();
            Perform.Check_ODFToolkit(output_filepath);
        }
        if (change) {
            change Perform = new change();
            Perform.Change_ODFToolkit(output_filepath);
        }
        if (validate) {
            main.validate Perform = new validate();
            Perform.Validate_OPFODFValidator(output_filepath);
        }
    }

    // Perform operations on input folder
    public void Folder(String input_folder, String output_folder, boolean recurse, boolean convert, boolean check, boolean change, boolean validate) throws Exception {

        // Enumerate files in folder based on extension and optionally recursively
        File inputfolder = new File(input_folder);
        String[] extensions = {"fods", "ods", "ots", "xls", "xla", "xlt", "xlsx", "xlsm", "xltm", "xltx", "xlam"};
        Collection<File> enumeration = FileUtils.listFiles(inputfolder, extensions, recurse);
        
        // Iterate files in enumeration
        for (File spreadsheet : enumeration)
        {
            // Find input and output filepaths
            String input_filepath = spreadsheet.getAbsolutePath();
            String output_filepath = output_folder + "\\" + spreadsheet.getName();

            // Perform operations on each spreadsheet
            Filepath(input_filepath, output_filepath, convert, check, change, validate);
        }
    }
}
