package general;

//import org.odftoolkit.odfvalidator.ODFValidator;
//import org.odftoolkit.odfvalidator.result.ValidationResult;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class validate {

    // Validate OpenDocument Spreadsheets using ODF Validator
    public boolean Validate_ODFValidator(String filepath, String path_to_jar, boolean verbose) throws Exception {
        boolean valid = false;

        // Documentation says these two commands are necessary
        String optionOne = "-Djavax.xml.validation.SchemaFactory:<http://relaxng.org/ns/structure/1.0>=org.iso_relax.verifier.jaxp.validation.RELAXNGSchemaFactoryImpl";
        String optionTwo = "-Dorg.iso_relax.verifier.VerifierFactoryLoader=com.sun.msv.verifier.jarv.FactoryLoaderImpl";

        if (path_to_jar != null) {
            // Set arguments
            ProcessBuilder Validation = new ProcessBuilder (
                    "javaw", "-jar",
                    optionOne,
                    optionTwo,
                    path_to_jar,
                    filepath);

            // Start validation
            Validation.redirectErrorStream(true);
            Process process = Validation.start();

            // Handle errors
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null)
                if (verbose) {
                    System.out.println("VALIDATE: " + line);
                }

            // Handle and return exit codes
            int exitCode = process.waitFor();
            System.out.println("VALIDATE: ODF Validator exit code " + exitCode);
            if (exitCode == 0)
            {
                System.out.println("VALIDATE: File format is valid.");
                valid = true;
            }
            else if (exitCode == 1)
            {
                System.out.println("VALIDATE: File format validation could not be completed");
            }
            else if (exitCode == 2)
            {
                System.out.println("VALIDATE: File format is invalid");
            }
            else {
                System.out.println("VALIDATE: File format is invalid");
            }
        }
        else {
            System.out.println("VALIDATE: ODF Validator jar file was not found - Validation is interrupted");
        }
        return valid;

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
    }
}
