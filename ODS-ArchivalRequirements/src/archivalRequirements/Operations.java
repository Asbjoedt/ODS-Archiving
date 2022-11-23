package archivalRequirements;

import org.apache.commons.io.*;

public class Operations {
    // Perform operations on input filepath
    public void Filepath(String input_filepath, String output_filepath, boolean convert, boolean check, boolean change, boolean validate, boolean delete) throws Exception {

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
                    Perform.ConvertToODS_LibreOffice(input_filepath, filepath);
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
                    Perform.ConvertToXLSX_LibreOffice(input_filepath, filepath);
                }
                if (check == true) {
                    check Perform = new check();
                    Perform.Check_ApachePOI(input_filepath);
                }
                if (change == true) {
                    change Perform = new change();
                    Perform.Change_ApachePOI(input_filepath);
                }
                // Perform user-chosen operations
                if (convert == true) {
                    convert Perform = new convert();
                    Perform.ConvertToODS_LibreOffice(input_filepath, filepath);
                }
                if (validate == true) {
                    validation Perform = new validation();
                    Perform.Validation_ODFToolkit(input_filepath);
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
                    Perform.Check_ApachePOI(input_filepath);
                }
                if (change == true) {
                    change Perform = new change();
                    Perform.Change_ApachePOI(input_filepath);
                }
                if (convert == true) {
                    convert Perform = new convert();
                    Perform.ConvertToODS_LibreOffice(input_filepath, filepath);
                }
                if (validate == true) {
                    validation Perform = new validation();
                    Perform.Validation_ODFToolkit(input_filepath);
                }
                break;
        }
        if (delete == true) {
            IO IO = new IO();
            IO.DeleteInputFile(input_filepath);
        }

    }

    // Perform operations on input folder
    public void Folder(String input_folder, String output_folder, boolean recurse, boolean convert, boolean check, boolean change, boolean validate, boolean delete) throws Exception {

    }
}
