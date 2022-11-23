package archivalRequirements;

import org.apache.commons.io.*;
import java.io.*;
import java.util.*;

public class Operations {

    // Perform operations on input filepath
    public void Filepath(String input_filepath, String output_filepath, String convert, boolean check, boolean change, boolean validate, boolean delete) throws Exception {
        IO IO = new IO();

        // Get file format extension output filepath
        String input_extension = FilenameUtils.getExtension(input_filepath).toLowerCase();
        String output_extension = FilenameUtils.getExtension(output_filepath).toLowerCase();

        // Perform convert operations, if selected
        if (convert != null) {
            convert Perform = new convert();
            Perform.Convert_LibreOffice(input_filepath, output_filepath, output_extension);
        }
        // If not selected, copy to output filepath if it is set
        if (convert == null) {
            if (output_filepath != null && output_filepath != input_filepath && output_extension.equals(input_extension)) {
                IO.CopyFile(input_filepath, output_filepath);
            }
            else if (output_filepath != null && output_filepath != input_filepath && !output_extension.equals(input_extension)) {
                throw new IOException("Spreadsheet must be converted to designated output extension");
            }
        }

        // Perform operations based on file format extension
        switch (output_extension) {

            case "fods":
            case "ods":
            case "ots":
                if (check == true) {
                    check Perform = new check();
                    Perform.Check_ODFToolkit(output_filepath);
                }
                if (change == true) {
                    change Perform = new change();
                    Perform.Change_ODFToolkit(output_filepath);
                }
                if (validate == true) {
                    validation Perform = new validation();
                    Perform.Validation_ODFToolkit(output_filepath);
                }
                break;

            case "xls":
            case "xla":
            case "xlt":
                if (check == true) {
                    check Perform = new check();
                    Perform.Check_ApachePOI(output_filepath);
                }
                if (change == true) {
                    change Perform = new change();
                    Perform.Change_ApachePOI(output_filepath);
                }
                if (validate == true) {
                    System.out.println("Validation of legacy Excel file formats is not supported");
                }
                break;

            case "xlsx":
            case "xlsm":
            case "xltm":
            case "xltx":
            case "xlam":
                // Perform user-chosen operations
                if (check == true) {
                    check Perform = new check();
                    Perform.Check_ApachePOI(output_filepath);
                }
                if (change == true) {
                    change Perform = new change();
                    Perform.Change_ApachePOI(output_filepath);
                }
                if (validate == true) {
                    System.out.println("Validation of OOXML file formats is not supported");
                }
                break;
        }
        // Delete original file, if selected
        if (delete == true) {
            IO.DeleteInputFile(input_filepath);
        }
    }

    // Perform operations on input folder
    public void Folder(String input_folder, String output_folder, boolean recurse, String convert, boolean check, boolean change, boolean validate, boolean delete) throws Exception {

        // Enumerate files in folder based on extension and optionally recursively
        File inputfolder = new File(input_folder);
        String[] extensions = {"fods", "ods", "ots", "xls", "xla", "xlt", "xlsx", "xlsm", "xltm", "xltx", "xlam"};
        Collection<File> enumeration = FileUtils.listFiles(inputfolder, extensions, recurse);
        
        // Iterate files in enumeration
        for (File spreadsheet : enumeration)
        {
            // Do something
        }
    }
}
