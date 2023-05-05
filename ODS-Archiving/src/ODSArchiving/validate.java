package ODSArchiving;

//import org.odftoolkit.odfvalidator.ODFValidator;
//import org.odftoolkit.odfvalidator.result.ValidationResult;

public class validate {

    // Validate OpenDocument Spreadsheets using ODF Validator
    public boolean Validate_ODFValidator(String filepath, String path_to_jar) throws Exception {
        boolean valid = false;

        if (path_to_jar != null) {
            // Set arguments
            ProcessBuilder Validation = new ProcessBuilder (
                    "javaw", "-jar", path_to_jar,
                    filepath);

            // Start validation
            Process process = Validation.start();

            // Handle results
            int exitCode = process.waitFor();
            System.out.println(exitCode);

            if (exitCode == 0)
            {
                System.out.println("VALIDATE: File format is invalid. Spreadsheet has no cell values");
            }
            else if (exitCode == 1)
            {
                System.out.println("VALIDATE: File format validation could not be completed");
            }
            else if (exitCode == 2)
            {
                System.out.println("VALIDATE: File format is valid");
                valid = true;
            }
            else {
                System.out.println("VALIDATE: File format is invalid");
                System.out.println(process.getOutputStream());
            }
        }
        else {
            System.out.println("VALIDATE: ODF Validator jar file was not found - Validation is interrupted");
        }

/*      OdfSpreadsheetDocument spreadsheet = OdfSpreadsheetDocument.loadDocument(filepath);
        //ODFValidator validator = new ODFValidator();
        //ValidationResult result = validator.validate(spreadsheet);
*/
        // Inform user and return boolean
/*        if (result.hasErrors()) {
            System.out.println("VALIDATE: The spreadsheet file is invalid.");
            System.out.println("VALIDATE: " + result.toString());
        } else {
            valid = true;
            System.out.println("VALIDATE: The spreadsheet file is valid.");
        }*/
        return valid;
    }
}
