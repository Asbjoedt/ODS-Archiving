package general;

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
            writeHeader(f, "Input filepath;" + "Output filepath;" + "Data connections;" + "External cell refs;" + "RTD functions" + "External objects;" + "Embedded objects;"+ "Content exists;" + "Macros;" + "LoadReadOnly;"+ "Printer settings;" + "Metadata;" + "Hyperlinks;" + "Embedded fonts;"+ "Active sheet;" + "Digital signatures");
        }

        // Parse checklist
        int dataConns = result.get(0).dataConnections;
        int extCellRefs = result.get(0).externalCellReferences;
        int rtdFunctions = result.get(0).RTDFunctions;
        int extObjs = result.get(0).externalObjects;
        int embedObjs = result.get(0).embeddedObjects;
        boolean content = result.get(0).content;
        int macros = result.get(0).macros;
        boolean loadReadOnly = result.get(0).loadReadOnly;
        int printers = result.get(0).printerSettings;
        boolean metadata = result.get(0).metadata;
        int hyperlinks = result.get(0).hyperlinks;
        boolean embedFonts = result.get(0).embeddedFonts;
        boolean activeSheet = result.get(0).activeSheet;
        int digsigs = result.get(0).digitalSignatures;

        // Append new line to existing CSV file
        writeLine(f, input_filepath + ";" + output_filepath + ";" + dataConns + ";" + extCellRefs + ";" + rtdFunctions + ";" + extObjs + ";" + embedObjs + ";" + content + ";" + macros + ";" + loadReadOnly + ";" + printers + ";" + metadata + ";" + hyperlinks + ";" + embedFonts + ";" + activeSheet + ";" + digsigs);
    }

    // Report on the success of changing
    public void ReportChange(String input_filepath, String output_filepath, String output_folder, List<change.changeList> result) throws IOException {
        // Create CSV file object
        String pathToCSV = output_folder + "/log_change.csv";
        File f = new File(pathToCSV);

        // Create header if the file is new
        if(f.length() == 0) {
            writeHeader(f, "Input filepath;" + "Output filepath;" + "Data connections removed;" + "RTD functions removed" + "External cell refs removed;" + "External objects removed;" + "Embedded objects removed;" + "Macros removed;" + "LoadReadOnly set to true;"+ "Printer settings removed;" + "Creator metadata removed;" + "Embedded fonts;"+ "Active sheet set to first;" + "Digital signatures removed");
        }

        // Parse changelist
        int dataConns = result.get(0).dataConnections;
        int extCellRefs = result.get(0).externalCellReferences;
        int rtdFunctions = result.get(0).RTDFunctions;
        int extObjs = result.get(0).externalObjects;
        int embedObjs = result.get(0).embeddedObjects;
        int macros = result.get(0).macros;
        boolean loadReadOnly = result.get(0).loadReadOnly;
        int printers = result.get(0).printerSettings;
        boolean metadata = result.get(0).metadata;
        boolean embedFonts = result.get(0).embeddedFonts;
        boolean activeSheet = result.get(0).activeSheet;
        int digsigs = result.get(0).digitalSignatures;

        // Append new line to existing CSV file
        writeLine(f, input_filepath + ";" + output_filepath + ";" + dataConns + ";" + extCellRefs + ";" + rtdFunctions + ";" + extObjs + ";" + embedObjs + ";" + macros + ";" + loadReadOnly + ";" + printers + ";" + metadata + ";" + embedFonts + ";" + activeSheet + ";" + digsigs);

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
