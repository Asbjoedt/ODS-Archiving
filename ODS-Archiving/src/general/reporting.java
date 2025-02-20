package general;

import general.check;

import java.io.*;
import java.util.List;

public class reporting {

    // Report on the success of conversion
    public void ReportConversion(String input_filepath, String output_filepath, String output_folder, int result) throws IOException {
        // Create CSV file object
        String pathToCSV = output_folder + "/log_conversion.csv";
        File f = new File(pathToCSV);

        // Create header if the file is new
        if(f.length() == 0) {
            writeHeader(f, "Input filepath;" + "Output filepath;" + "Conversion success");
        }

        // Parse result to boolean
        boolean parsed_result = result == 0;

        // Append new line to existing CSV file
        writeLine(f, input_filepath + ";" + output_filepath + ";" + parsed_result);
    }

    // Report on the success of checking
    public void ReportCheck(String input_filepath, String output_filepath, String output_folder, List<check.checkList> result) throws IOException {
        // Create CSV file object
        String pathToCSV = output_folder + "/log_check.csv";
        File f = new File(pathToCSV);

        // Create header if the file is new
        if(f.length() == 0) {
            writeHeader(f, "Input filepath;" + "Output filepath;" + "Data connections;" + "External cell refs;" + "External objects;" + "Embedded objects;"+ "Content exists;" + "Macros;" + "LoadReadOnly;"+ "Printer settings;" + "Metadata;" + "Hyperlinks;" + "Embedded fonts;"+ "Active sheet;" + "SettingsDOM;" + "Digital signatures");
        }

        // Parse checklist
        //dataConns, extCellRefs, rtdFunctions, extObjs, embedObjs, content, macros, loadReadOnly, printers, metadata, hyperlinks, embeddedFonts, activeSheet, settingsDOM, digitalSignatures

        for (var field : result) {
            //System.out.println(field.dataConnections);
        }

        // Append new line to existing CSV file
        writeLine(f, input_filepath + ";" + output_filepath + ";" + result);
    }

    // Report on the success of changing
    public void ReportChange(String input_filepath, String output_filepath, String output_folder, List<change.changeList> result) throws IOException {

    }

    // Report on the success of validation
    public void ReportValidation(String input_filepath, String output_filepath, String output_folder, boolean result) throws IOException {
        // Create CSV file object
        String pathToCSV = output_folder + "/log_validation.csv";
        File f = new File(pathToCSV);

        // Create header if the file is new
        if(f.length() == 0) {
            writeHeader(f, "Input filepath;" + "Output filepath;" + "Validation success");
        }

        // Append new line to existing CSV file
        writeLine(f, input_filepath + ";" + output_filepath + ";" + result);
    }

    // Method for writing first line of the CSV report file
    private void writeHeader(File f, String header) throws FileNotFoundException {
        PrintWriter headerCSV = new PrintWriter(f);
        headerCSV.println(header);
        headerCSV.close();
    }

    // Method for writing a new line to the CSV report file
    private void writeLine(File f, String line) throws IOException {
        PrintWriter appendCSV = new PrintWriter(new FileWriter(f, true));
        appendCSV.println(line);
        appendCSV.close();
    }
}
