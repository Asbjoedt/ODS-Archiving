package archivalRequirements;

import org.apache.commons.cli.*;
import java.io.IOException;

public class application {

	public static void main(String[] args) throws Exception, ParseException, IOException {

		//define argument options
		Options options = new Options();
		Option check = new Option("che", "check", false, "Check spreadsheet for archival requirements");
		options.addOption(check);
		Option change = new Option("cha", "change", false, "Change spreadsheet according to archival requirements");
		options.addOption(change);
		Option validate = new Option("val", "validate", false, "Validate spreadsheet for archival requirements and file format standard");
		options.addOption(validate);
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
		boolean parsed_validate = false;
		String parsed_input_filepath = "";
		String parsed_output_filepath = "";
		try {
			cmd = parser.parse(options, args);
			if(cmd.hasOption("che")) {
				parsed_check = true;
			}
			if(cmd.hasOption("cha")) {
				parsed_change = true;
			}
			if(cmd.hasOption("val")) {
				parsed_validate = true;
			}
			if (cmd.hasOption("inp")) {
				parsed_input_filepath = cmd.getOptionValue("inputfilepath");
			}
			if (cmd.hasOption("out")) {
				parsed_output_filepath = cmd.getOptionValue("outputfilepath");
			}
			else if (!cmd.hasOption("out")) {
				parsed_output_filepath = parsed_input_filepath;
			}
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			helper.printHelp("Usage:", options);
			System.exit(0);
		}

		// Inform user of inputs
		System.out.println("YOUR INPUT");
		System.out.println("Methods: " + "Check: " + parsed_check + ", Change: " + parsed_change + ", Validate: " + parsed_validate);
		System.out.println("Input filepath: " + parsed_input_filepath);
		System.out.println("Output filepath: " + parsed_output_filepath);
		System.out.println("PERFORM OPERATIONS ON INPUT");

		// Extract input filepath extension for switch
		String input_extension = parsed_input_filepath.substring(parsed_input_filepath.lastIndexOf('.') + 1);

		// Perform check, if true
		if (parsed_check == true) {
			switch (input_extension.toLowerCase()) {

				case "fods":
				case "ods":
				case "ots":
					// Check for protection
					try {

					}
					catch (IOException) {
						throw new IOException("File cannot be read e.g. has password protection, is corrupt");
					}

					// Copy file, if output filepath is set
					String filepath = "";
					if (parsed_output_filepath == null) {
						filepath = copyFile(parsed_input_filepath, parsed_output_filepath);
					}
					// Else use input filepath for operations
					else if (parsed_output_filepath != null) {
						filepath = parsed_input_filepath;
					}

					// Perform user-chosen operations
					if (parsed_check == true) {
						check Perform = new check();
						Perform.Check(filepath);
					}
					if (parsed_change == true) {
						change Perform = new change();
						Perform.Change(filepath);
					}
					if (parsed_validate == true) {
						validation Perform = new validation();
						Perform.Validation(filepath);
					}
					break;

				default:
					throw new IOException("Input filepath does not have an accepted file format extension. Accepted file format extensions are: .ods, .ots and .fods");
			}
		}
	}
}
