package main;

import org.apache.commons.cli.*;
import org.apache.commons.io.FilenameUtils;
import java.io.IOException;

public class application {

	// Main method of the application
	public static void main(String[] args) throws Exception, ParseException, IOException {

		// Inform user of beginning of application
		System.out.println("ODS-Archiving v1.0.0");
		System.out.println("@Asbjørn Skødt, web: https://github.com/Asbjoedt/ODS-Archiving");

		//define argument options
		Options options = new Options();

		Option convert = new Option("con", "convert", false, "Convert spreadsheet to .ods file format");
		options.addOption(convert);

		Option check = new Option("che", "check", false, "Check spreadsheet for archival requirements");
		options.addOption(check);

		Option change = new Option("cha", "change", false, "Change spreadsheet according to archival requirements");
		options.addOption(change);

		Option validate = new Option("val", "validate", false, "Validate OpenDocument Spreadsheets file format standard");
		options.addOption(validate);

		Option recurse = new Option("rec", "recurse", false, "Include subdirectories in input folder");
		options.addOption(recurse);

		Option input_file = Option.builder("inp").longOpt("inputfile")
				.argName("inputfile")
				.hasArg()
				.required(false)
				.desc("Set spreadsheet input file").build();
		options.addOption(input_file);

		Option input_folder = Option.builder("inf").longOpt("inputfolder")
				.argName("inputfolder")
				.hasArg()
				.required(false)
				.desc("Set spreadsheet input folder path").build();
		options.addOption(input_folder);

		Option output_folder = Option.builder("out").longOpt("outputfolder")
				.argName("outputfolder")
				.hasArg()
				.required(false)
				.desc("Set spreadsheet output folder path").build();
		options.addOption(output_folder);

		// define argument parser
		CommandLine cmd;
		CommandLineParser parser = new DefaultParser();
		HelpFormatter helper = new HelpFormatter();

		// Parse arguments
		boolean parsed_convert = false;
		boolean parsed_check = false;
		boolean parsed_change = false;
		boolean parsed_validate = false;
		boolean parsed_recurse = false;
		String parsed_input_file = null;
		String parsed_input_folder = null;
		String parsed_output_folder = null;

		try {
			cmd = parser.parse(options, args);
			if (cmd.hasOption("con")) {
				parsed_convert = true;
			}
			if (cmd.hasOption("che")) {
				parsed_check = true;
			}
			if (cmd.hasOption("cha")) {
				parsed_change = true;
			}
			if (cmd.hasOption("val")) {
				parsed_validate = true;
			}
			if (cmd.hasOption("inp")) {
				parsed_input_file = cmd.getOptionValue("inputfile");
			}
			if (cmd.hasOption("inf")) {
				parsed_input_folder = cmd.getOptionValue("inputfolder");
			}
			if (cmd.hasOption("out")) {
				parsed_output_folder = cmd.getOptionValue("outputfolder");
			}
			// Check if both input filepath and input folder are set and throw exception
			if (parsed_input_file != null && parsed_input_folder != null) {
				throw new ParseException("Both input filepath and input folder are set");
			}
			// Check if either input filepath or input folder are not set and throw exception
			if (parsed_input_file == null && parsed_input_folder == null) {
				throw new ParseException("Input filepath or input folder are NOT set");
			}
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			helper.printHelp("Usage:", options);
			System.exit(0);
		}

		// Inform user of inputs
		System.out.println("YOUR INPUT");
		System.out.println("Methods: " + "Convert " + parsed_convert + ", Check " + parsed_check + ", Change " + parsed_change + ", Validate " + parsed_validate);
		if (parsed_input_file != null) {
			System.out.println("Input file: " + parsed_input_file);
		}
		if (parsed_input_folder != null) {
			System.out.println("Input folder: " + parsed_input_folder);
		}
		if (parsed_output_folder != null) {
			System.out.println("Output folder: " + parsed_output_folder);
		}

		// Set output folder to input folder, if output folder is not set by user
		if (parsed_output_folder == null && parsed_input_folder != null) {
			if (!parsed_input_folder.endsWith("\\")) {
				parsed_input_folder = parsed_input_folder + "\\";
			}
			parsed_output_folder = parsed_input_folder;
		}
		if (parsed_output_folder == null && parsed_input_file != null) {
			parsed_output_folder = FilenameUtils.getPath(parsed_input_file);
		}

		// Create output filepath
		String parsed_output_filepath = null;
		if (parsed_input_file != null) {
			parsed_output_filepath = parsed_output_folder + FilenameUtils.getName(parsed_input_file);
		}

		// Check I/O of user inputs
		IO IO = new IO();
		if (parsed_input_file != null && parsed_input_folder == null) {
			IO.CheckFilepathIO(parsed_input_file, parsed_output_filepath);
		}
		else if (parsed_input_folder != null && parsed_input_file == null) {
			IO.CheckFolderIO(parsed_input_folder, parsed_output_folder);
		}
		else {
			throw new IOException("No input filepath or input folder has been set");
		}

		// Perform operations
		System.out.println("PERFORMING OPERATIONS ON INPUT");
		operations OperateOn = new operations();
		if (parsed_input_file != null && parsed_input_folder == null) {
			OperateOn.Filepath(parsed_input_file, parsed_output_filepath, parsed_convert, parsed_check, parsed_change, parsed_validate);
		}
		else if (parsed_input_folder != null && parsed_input_file == null) {
			OperateOn.Folder(parsed_input_folder, parsed_output_folder, parsed_recurse, parsed_convert, parsed_check, parsed_change, parsed_validate);
		}

		// Inform user of end of application
		System.out.println("APPLICATION FINISHED");
	}
}
