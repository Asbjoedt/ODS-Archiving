package archivalRequirements;

import org.apache.commons.cli.*;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;

public class application {

	public static void main(String[] args) throws Exception, ParseException, IOException {

		//define argument options
		Options options = new Options();
		Option check = new Option("che", "check", false, "Check spreadsheet for archival requirements");
		options.addOption(check);
		Option change = new Option("cha", "change", false, "Change spreadsheet according to archival requirements");
		options.addOption(change);
		Option convert = new Option("con", "convert", false, "Convert spreadsheet to .ods file format");
		options.addOption(convert);
		Option validate = new Option("val", "validate", false, "Validate OpenDocument Spreadsheets file format standard");
		options.addOption(validate);
		Option delete = new Option("del", "delete", false, "Delete original input spreadsheet file");
		options.addOption(delete);
		Option input_filepath = Option.builder("inp").longOpt("inputfilepath")
				.argName("inputfilepath")
				.hasArg()
				.required(true)
				.desc("Set spreadsheet input filepath").build();
		options.addOption(input_filepath);
		Option output_filepath = Option.builder("out").longOpt("outputfilepath")
				.argName("outputfilepath")
				.hasArg()
				.required(false)
				.desc("Set spreadsheet output filepath").build();
		options.addOption(output_filepath);

		// define argument parser
		CommandLine cmd;
		CommandLineParser parser = new DefaultParser();
		HelpFormatter helper = new HelpFormatter();

		// Parse arguments
		boolean parsed_check = false;
		boolean parsed_change = false;
		boolean parsed_convert = false;
		boolean parsed_validate = false;
		boolean parsed_delete = false;
		String parsed_input_filepath = "";
		String parsed_output_filepath = "";
		try {
			cmd = parser.parse(options, args);
			if (cmd.hasOption("che")) {
				parsed_check = true;
			}
			if (cmd.hasOption("cha")) {
				parsed_change = true;
			}
			if (cmd.hasOption("con")) {
				parsed_convert = true;
			}
			if (cmd.hasOption("val")) {
				parsed_validate = true;
			}
			if (cmd.hasOption("del")) {
				parsed_delete = true;
			}
			if (cmd.hasOption("inp")) {
				parsed_input_filepath = cmd.getOptionValue("inputfilepath");
			}
			if (cmd.hasOption("out")) {
				parsed_output_filepath = cmd.getOptionValue("outputfilepath");
			}
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			helper.printHelp("Usage:", options);
			System.exit(0);
		}

		// Inform user of inputs
		System.out.println("YOUR INPUT");
		System.out.println("Methods: " + "Convert: " + parsed_convert + "Check: " + parsed_check + ", Change: " + parsed_change + ", Validate: " + parsed_validate);
		System.out.println("Input filepath: " + parsed_input_filepath);
		System.out.println("Output filepath: " + parsed_output_filepath);
		System.out.println("PERFORM OPERATIONS ON INPUT");

		// Check I/O of user inputs
		checkIO IO = new checkIO();
		String filepath = IO.Filepath(parsed_input_filepath, parsed_output_filepath);

		// Perform operations based on file format extension
		String input_extension = FilenameUtils.getExtension(parsed_input_filepath).toLowerCase();
		switch (input_extension) {

			case "fods":
			case "ods":
			case "ots":
				// Perform user-chosen operations
				if (parsed_convert == true) {
					convert Perform = new convert();
					Perform.ConvertToODS_LibreOffice(filepath, filepath);
				}
				if (parsed_check == true) {
					check Perform = new check();
					Perform.Check_ODFToolkit(filepath);
				}
				if (parsed_change == true) {
					change Perform = new change();
					Perform.Change_ODFToolkit(filepath);
				}
				if (parsed_validate == true) {
					validation Perform = new validation();
					Perform.Validation_ODFToolkit(filepath);
				}
				break;

			case "xls":
			case "xla":
			case "xlt":
				// Perform user-chosen operations
				if (parsed_convert == true) {
					convert Perform = new convert();
					Perform.ConvertToXLSX_LibreOffice(filepath, filepath);
				}
				if (parsed_check == true) {
					check Perform = new check();
					Perform.Check_ApachePOI(filepath);
				}
				if (parsed_change == true) {
					change Perform = new change();
					Perform.Change_ApachePOI(filepath);
				}
				// Perform user-chosen operations
				if (parsed_convert == true) {
					convert Perform = new convert();
					Perform.ConvertToODS_LibreOffice(filepath, filepath);
				}
				if (parsed_validate == true) {
					validation Perform = new validation();
					Perform.Validation_ODFToolkit(filepath);
				}
				break;

			case "xlsx":
			case "xlsm":
			case "xltm":
			case "xltx":
			case "xlam":
				// Perform user-chosen operations
				if (parsed_check == true) {
					check Perform = new check();
					Perform.Check_ApachePOI(filepath);
				}
				if (parsed_change == true) {
					change Perform = new change();
					Perform.Change_ApachePOI(filepath);
				}
				if (parsed_convert == true) {
					convert Perform = new convert();
					Perform.ConvertToODS_LibreOffice(filepath, filepath);
				}
				if (parsed_validate == true) {
					validation Perform = new validation();
					Perform.Validation_ODFToolkit(filepath);
				}
				break;
		}
	}
}
