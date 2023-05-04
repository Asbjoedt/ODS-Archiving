package main;

import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
//import org.odftoolkit.odfvalidator.ODFValidator;
//import org.odftoolkit.odfvalidator.result.ValidationResult;

public class validate {

    // Validate OpenDocument Spreadsheets using ODF Validator
    public boolean Validate_ODFValidator(String filepath) throws Exception {
        boolean valid = false;

        System.out.println("VALIDATE: Validation of OpenDocument Spreadsheets are currently not supported");

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
