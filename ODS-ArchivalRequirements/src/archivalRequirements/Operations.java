package archivalRequirements;

import org.apache.commons.io.*;

public class Operations {

    // Perform operations on input filepath
    public void Filepath(String input_filepath, String output_filepath, boolean convert, boolean check, boolean change, boolean validate, boolean delete) throws Exception {

        // Copy file, if output filepath is set
        IO IO = new IO();
        if (output_filepath != null && output_filepath == input_filepath) {
            IO.CopyFile(input_filepath, output_filepath);
        }

        // Get file format extension of input filepath
        String input_extension = FilenameUtils.getExtension(input_filepath).toLowerCase();

        // Perform operations based on file format extension
        switch (input_extension) {

            case "fods":
            case "ods":
            case "ots":
                // Perform user-chosen operations
                if (convert == true) {
                    convert Perform = new convert();
                    Perform.ConvertToODS_LibreOffice(input_filepath, output_filepath);
                }
                if (check == true) {
                    check Perform = new check();
                    Perform.Check_ODFToolkit(input_filepath);
                }
                if (change == true) {
                    change Perform = new change();
                    Perform.Change_ODFToolkit(input_filepath);
                }
                if (validate == true) {
                    validation Perform = new validation();
                    Perform.Validation_ODFToolkit(input_filepath);
                }
                break;

            case "xls":
            case "xla":
            case "xlt":
                // Perform user-chosen operations
                if (convert == true) {
                    convert Perform = new convert();
                    Perform.ConvertToXLSX_LibreOffice(input_filepath, output_filepath);
                }
                if (check == true) {
                    check Perform = new check();
                    Perform.Check_ApachePOI(output_filepath);
                }
                if (change == true) {
                    change Perform = new change();
                    Perform.Change_ApachePOI(output_filepath);
                }
                if (convert == true) {
                    convert Perform = new convert();
                    Perform.ConvertToODS_LibreOffice(input_filepath, output_filepath);
                }
                if (validate == true) {
                    validation Perform = new validation();
                    Perform.Validation_ODFToolkit(output_filepath);
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
                if (convert == true) {
                    convert Perform = new convert();
                    Perform.ConvertToODS_LibreOffice(output_filepath, output_filepath);
                }
                if (validate == true) {
                    validation Perform = new validation();
                    Perform.Validation_ODFToolkit(output_filepath);
                }
                break;
        }
        if (delete == true) {
            IO.DeleteInputFile(input_filepath);
        }

    }

    // Perform operations on input folder
    public void Folder(String input_folder, String output_folder, boolean recurse, boolean convert, boolean check, boolean change, boolean validate, boolean delete) throws Exception {

    }
}
