package archivalRequirements;

import java.io.*;
import org.apache.commons.cli.*;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;

public class application {

	public static void main(String[] args) throws Exception, ParseException, IOException {

		// Inform user of beginning of application
		System.out.println("ODS-ArchivalRequirements v1.0");
		System.out.println("@Asbjørn Skødt, web: https://github.com/Asbjoedt/ODS-ArchivalRequirements");

		//define argument options
		Options options = new Options();

		Option check = new Option("che", "check", false, "Check spreadsheet for archival requirements");
		options.addOption(check);

		Option change = new Option("cha", "change", false, "Change spreadsheet according to archival requirements");
		options.addOption(change);

		Option validate = new Option("val", "validate", false, "Validate OpenDocument Spreadsheets file format standard");
		options.addOption(validate);

		Option delete = new Option("del", "delete", false, "Delete original input spreadsheet file");
		options.addOption(delete);

		Option recurse = new Option("rec", "recurse", false, "Include subdirectories in input folder");
		options.addOption(recurse);

		Option convert = Option.builder("con").longOpt("convert")
				.argName("convert")
				.hasArg()
				.required(false)
				.desc("Convert spreadsheet, set extension for output file format").build();
		options.addOption(convert);

		Option input_filepath = Option.builder("inp").longOpt("inputfilepath")
				.argName("inputfilepath")
				.hasArg()
				.required(false)
				.desc("Set spreadsheet input filepath").build();
		options.addOption(input_filepath);

		Option output_filepath = Option.builder("out").longOpt("outputfilepath")
				.argName("outputfilepath")
				.hasArg()
				.required(false)
				.desc("Set spreadsheet output filepath").build();
		options.addOption(output_filepath);

		Option input_folder = Option.builder("inf").longOpt("inputfolder")
				.argName("inputfolder")
				.hasArg()
				.required(false)
				.desc("Set spreadsheet input folder path").build();
		options.addOption(input_folder);

		Option output_folder = Option.builder("ouf").longOpt("outputfolder")
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
		boolean parsed_check = false;
		boolean parsed_change = false;
		boolean parsed_validate = false;
		boolean parsed_delete = false;
		boolean parsed_recurse = false;
		String parsed_convert = null;
		String parsed_input_filepath = null;
		String parsed_output_filepath = null;
		String parsed_input_folder = null;
		String parsed_output_folder = null;

		try {
			cmd = parser.parse(options, args);
			if (cmd.hasOption("che")) {
				parsed_check = true;
			}
			if (cmd.hasOption("cha")) {
				parsed_change = true;
			}
			if (cmd.hasOption("val")) {
				parsed_validate = true;
			}
			if (cmd.hasOption("del")) {
				parsed_delete = true;
			}
			if (cmd.hasOption("con")) {
				parsed_convert = cmd.getOptionValue("convert");
			}
			if (cmd.hasOption("inp")) {
				parsed_input_filepath = cmd.getOptionValue("inputfilepath");
			}
			if (cmd.hasOption("out")) {
				parsed_output_filepath = cmd.getOptionValue("outputfilepath");
			}
			if (cmd.hasOption("inf")) {
				parsed_input_folder = cmd.getOptionValue("inputfolder");
			}
			if (cmd.hasOption("ouf")) {
				parsed_output_folder = cmd.getOptionValue("outputfolder");
			}
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			helper.printHelp("Usage:", options);
			System.exit(0);
		}

		// Inform user of inputs
		System.out.println("YOUR INPUT");
		System.out.println("Methods: " + "Convert " + parsed_convert + ", Check " + parsed_check + ", Change " + parsed_change + ", Validate " + parsed_validate);
		if (parsed_input_filepath != null) {
			System.out.println("Input filepath: " + parsed_input_filepath);
		}
		if (parsed_output_filepath != null) {
			System.out.println("Output filepath: " + parsed_output_filepath);
		}
		if (parsed_input_folder != null) {
			System.out.println("Input folder: " + parsed_input_folder);
		}
		if (parsed_output_folder != null) {
			System.out.println("Output folder: " + parsed_output_folder);
		}

		// Correct outputs if outputs are identical to inputs or null
		if (parsed_input_filepath == parsed_output_filepath || parsed_output_filepath == null) {
			parsed_output_filepath = parsed_input_filepath;
		}
		if (parsed_input_folder == parsed_output_folder || parsed_output_folder == null) {
			parsed_output_folder = parsed_input_folder;
		}

		// Check I/O of user inputs
		IO IO = new IO();
		if (parsed_input_filepath != null && parsed_input_folder == null) {
			IO.CheckFilepathIO(parsed_input_filepath, parsed_output_filepath);
		}
		else if (parsed_input_folder != null && parsed_input_filepath == null) {
			IO.CheckFolderIO(parsed_input_folder, parsed_output_folder);
		}
		else {
			throw new IOException("No input filepath or input folder has been set");
		}

		// Perform operations
		System.out.println("PERFORM OPERATIONS ON INPUT");
		Operations OperateOn = new Operations();
		if (parsed_input_filepath != null && parsed_input_folder == null) {
			OperateOn.Filepath(parsed_input_filepath, parsed_output_filepath, parsed_convert, parsed_check, parsed_change, parsed_validate, parsed_delete);
		}
		else if (parsed_input_folder != null && parsed_input_filepath == null) {
			OperateOn.Folder(parsed_input_folder, parsed_output_folder, parsed_recurse, parsed_convert, parsed_check, parsed_change, parsed_validate, parsed_delete);
		}

		// Inform user of end of application
		System.out.println("APPLICATION FINISHED");
	}
}
