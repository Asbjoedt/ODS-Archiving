package general;

import general.check;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class reporting {

    // Report on the success of conversion
    public void ReportConversion(String input_filepath, String output_filepath, String output_folder, int result) throws IOException {
        // Create CSV file object
        String pathToCSV = output_folder + "/log_conversion.csv";
        File f = new File(pathToCSV);

        // Create new CSV file if it does not exist
        if(f.length() == 0) {
            File newCSVFile = new File(pathToCSV);
            PrintWriter headerCSV = new PrintWriter(newCSVFile);
            headerCSV.println("Input filepath;" + "Output filepath;" + "Conversion success");
            headerCSV.close();
        }

        // Parse result to boolean
        boolean parsed_result = result == 0;

        // Append new line to existing CSV file
        File existingCSVFile = new File(pathToCSV);
        PrintWriter appendCSV = new PrintWriter(new FileWriter(existingCSVFile, true));
        appendCSV.println(input_filepath + ";" + output_filepath + ";" + parsed_result);
        appendCSV.close();
    }

    // Report on the success of checking
    public void ReportCheck(String input_filepath, String output_filepath, String output_folder, List<check.checkList> result) throws IOException {
        // Create CSV file object
        String pathToCSV = output_folder + "/log_check.csv";
        File f = new File(pathToCSV);

        // Create new CSV file if it does not exist
        if(f.length() == 0) {
            File newCSVFile = new File(pathToCSV);
            PrintWriter headerCSV = new PrintWriter(newCSVFile);
            headerCSV.println("Input filepath;" + "Output filepath;" + "Data connections;" + "External cell refs;" + "External objects;" + "Embedded objects;"+ "Content exists;" + "Macros;" + "LoadReadOnly;"+ "Printer settings;" + "Metadata;" + "Hyperlinks;" + "Embedded fonts;"+ "Active sheet;" + "SettingsDOM;" + "Digital signatures");
            headerCSV.close();
        }

        // Parse checklist
        //dataConns, extCellRefs, rtdFunctions, extObjs, embedObjs, content, macros, loadReadOnly, printers, metadata, hyperlinks, embeddedFonts, activeSheet, settingsDOM, digitalSignatures

        for (var field : result) {
            //System.out.println(field.dataConnections);
        }

        // Append new line to existing CSV file
        File existingCSVFile = new File(pathToCSV);
        PrintWriter appendCSV = new PrintWriter(new FileWriter(existingCSVFile, true));
        appendCSV.println(input_filepath + ";" + output_filepath + ";" + result);
        appendCSV.close();
    }

    // Report on the success of changing
    public void ReportChange(String input_filepath, String output_filepath, String output_folder, List<change.changeList> result) throws IOException {

    }

    // Report on the success of validation
    public void ReportValidation(String input_filepath, String output_filepath, String output_folder, boolean result) throws IOException {
        // Create CSV file object
        String pathToCSV = output_folder + "/log_validation.csv";
        File f = new File(pathToCSV);

        // Create new CSV file if it does not exist
        if(f.length() == 0) {
            File newCSVFile = new File(pathToCSV);
            PrintWriter headerCSV = new PrintWriter(newCSVFile);
            headerCSV.println("Input filepath;" + "Output filepath;" + "Validation success");
            headerCSV.close();
        }

        // Append new line to existing CSV file
        File existingCSVFile = new File(pathToCSV);
        PrintWriter appendCSV = new PrintWriter(new FileWriter(existingCSVFile, true));
        appendCSV.println(input_filepath + ";" + output_filepath + ";" + result);
        appendCSV.close();
    }
}
