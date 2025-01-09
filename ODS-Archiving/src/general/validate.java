package general;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import org.openpreservation.messages.Message;
import org.openpreservation.odf.validation.Profile;
import org.openpreservation.odf.validation.ProfileResult;
import org.openpreservation.odf.validation.ValidationReport;
import org.openpreservation.odf.validation.Validator;
import org.openpreservation.odf.validation.rules.Rules;

public class validate {

    // Validate OpenDocument Spreadsheets using Open Preservation Foundation Validator
    // Reference: https://odf.openpreservation.org/developer/
    public boolean Validate_OPFValidator(String filepath, String conformance, boolean verbose) throws Exception {
        boolean valid = false;

        // Validate using OPF ODF Validator
        Validator validate = new Validator();
        ValidationReport validity = validate.validateSpreadsheet(new File(filepath));
        valid = validity.isValid();

        // Perform checks based on compliance except DNA profile
        if (conformance.equals("all") || conformance.equals("normal") || conformance.equals("minimal") || conformance.equals("experimental")) {
            // Inform user of all messages
            if (verbose) {
                if (validity.getErrors() != null) {
                    for (Message message : validity.getMessages()) {
                        System.out.println("VALIDATE VERBOSE: " + message.getId() + ", " + message.getSeverity() + ", " + message.getMessage());
                    }
                }
            }

            // Inform user of only error messages
            if (!verbose) {
                if (validity.getErrors() != null) {
                    for (Message message : validity.getErrors()) {
                        if (message.getSeverity().toString().equals("ERROR"))
                            System.out.println("VALIDATE: " + message.getId() + ", " + message.getSeverity() + ", " + message.getMessage());
                    }
                }
            }
        }

        // Performs validation based on DNA profile conformance
        if (conformance.equals("dna")) {
            Profile dnaProfile = Rules.getDnaProfile();
            ProfileResult dnaResult = dnaProfile.check(validity);
            ValidationReport dnaReport = dnaResult.getValidationReport();

            // Inform user of all messages
            if (verbose) {
                if (dnaReport.getErrors() != null) {
                    for (Message message : dnaReport.getMessages()) {
                        System.out.println("VALIDATE DNA PROFILE VERBOSE: " + message.getId() + ", " + message.getSeverity() + ", " + message.getMessage());
                    }
                }
            }

            // Inform user of only error messages
            if (!verbose) {
                if (dnaReport.getErrors() != null) {
                    for (Message message : dnaReport.getMessages()) {
                        if (message.getSeverity().toString().equals("ERROR")) {
                            System.out.println("VALIDATE DNA PROFILE: " + message.getId() + ", " + message.getSeverity() + ", " + message.getMessage());
                        }
                    }
                }
            }
        }

        // Return and inform user of boolean validity
        if(valid)
            System.out.println("VALIDATE: Spreadsheet is valid");
        else
            System.out.println("VALIDATE: Spreadsheet is invalid");
        return valid;
    }

    // Validate OpenDocument Spreadsheets using ODF Jar Validator
    public boolean Validate_ODFJarValidator(String filepath, String path_to_jar, boolean verbose) throws Exception {
        boolean valid = false;

        // Validate using ODF Jar validator
        Validator validate = new Validator();
        validate.validateSpreadsheet(new File(filepath));

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
            while ((line = reader.readLine()) != null) {
                if (verbose)
                    System.out.println("VALIDATE ODS_2 VERBOSE: " + line);
            }

            // Handle and return exit codes
            int exitCode = process.waitFor();
            System.out.println("VALIDATE ODS_2 VERBOSE: ODF Validator exit code " + exitCode);
            if (exitCode == 0)
            {
                System.out.println("VALIDATE ODS_2: File format is valid.");
                valid = true;
            }
            else if (exitCode == 1)
                System.out.println("VALIDATE ODS_2: File format validation could not be completed");
            else if (exitCode == 2)
                System.out.println("VALIDATE ODS_2: File format is invalid");
        }
        else {
            System.out.println("VALIDATE ODS_2: ODF Validator jar file was not found - Validation is interrupted");
        }
        return valid;
    }

/*    // Validate OpenDocument Spreadsheets using ODF library Validator
    public boolean Validate_ODFLibraryValidator(String filepath, String path_to_jar, boolean verbose) throws Exception {
        boolean valid = false;

        // Validate using ODF library validator
        OdfSpreadsheetDocument spreadsheet = OdfSpreadsheetDocument.loadDocument(filepath);
        ODFValidator validator = new ODFValidator();
        ValidationResult result = validator.validate(spreadsheet);

        // Inform user and return boolean
        if (result.hasErrors()) {
            System.out.println("VALIDATE: The spreadsheet file is invalid.");
            System.out.println("VALIDATE: " + result.toString());
        } else {
            valid = true;
            System.out.println("VALIDATE: The spreadsheet file is valid.");
        }
        return valid;
    }*/
}
