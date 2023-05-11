package general;

import org.apache.commons.cli.*;
import org.apache.commons.io.FilenameUtils;
import java.io.IOException;

public class application {

	// Main method of the application
	public static void main(String[] args) throws Exception, ParseException, IOException {

		// Inform user of beginning of application
		System.out.println("ODS Archiving v1.0.0");
		System.out.println("@Asbjørn Skødt, web: https://github.com/Asbjoedt/ODS-Archiving");

		//define argument options
		Options options = new Options();

		Option convert = new Option("con", "convert", false, "Convert spreadsheet to .ods file format");
		options.addOption(convert);

		Option check = new Option("che", "check", false, "Check spreadsheet for archival requirements");
		options.addOption(check);

		Option change = new Option("cha", "change", false, "Change spreadsheet according to archival requirements");
		options.addOption(change);

		Option recurse = new Option("rec", "recurse", false, "Include subdirectories in input folder");
		options.addOption(recurse);

		Option verbose = new Option("ver", "verbose", false, "Output detailed information");
		options.addOption(verbose);

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

		Option rename = Option.builder("ren").longOpt("rename")
				.argName("rename")
				.hasArg()
				.required(false)
				.desc("Set new name of output file").build();
		options.addOption(rename);

		Option validate = Option.builder("val").longOpt("validate")
				.argName("validate")
				.hasArg()
				.required(false)
				.desc("Set path to ODF Validator jar to validate OpenDocument Spreadsheets file format").build();
		options.addOption(validate);

		Option conformance = Option.builder("cof").longOpt("conformance")
				.argName("conformance")
				.hasArg()
				.required(false)
				.desc("Set level of conformance for archival requirements").build();
		options.addOption(conformance);

		// define argument parser
		CommandLine cmd;
		CommandLineParser parser = new DefaultParser();
		HelpFormatter helper = new HelpFormatter();

		// Parse arguments
		boolean parsed_convert = false;
		boolean parsed_check = false;
		boolean parsed_change = false;
		boolean parsed_recurse = false;
		boolean parsed_verbose = false;
		String parsed_validate = null;
		String parsed_input_file = null;
		String parsed_output_file = null;
		String parsed_input_folder = null;
		String parsed_output_folder = null;
		String parsed_rename = null;
		String parsed_conformance = null;

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
			if (cmd.hasOption("ver")) {
				parsed_verbose = true;
			}
			if (cmd.hasOption("val")) {
				parsed_validate = cmd.getOptionValue("validate");
			}
			if (cmd.hasOption("cof")) {
				parsed_conformance = cmd.getOptionValue("conformance").toLowerCase();
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
			if (cmd.hasOption("ren")) {
				parsed_rename = cmd.getOptionValue("rename");
			}
			// Check accepted compliance levels
			if (parsed_check || parsed_change) {
				if (parsed_conformance == null || !parsed_conformance.equals("must") && !parsed_conformance.equals("should") && !parsed_conformance.equals("may") && !parsed_conformance.equals("experimental")) {
					throw new ParseException("PARSE ERROR: Compliance is not \"must\", \"should\", \"may\" or \"experimental\"");
				}
			}
			// Check if both input filepath and input folder are set, then throw exception
			if (parsed_input_file != null && parsed_input_folder != null) {
				throw new ParseException("PARSE ERROR: Both input filepath and input folder are set");
			}
			// Check if either input filepath or input folder are not set, then throw exception
			if (parsed_input_file == null && parsed_input_folder == null) {
				throw new ParseException("PARSE ERROR: Input filepath or input folder are NOT set");
			}
			// Check if convert or change are set, but output folder is NOT, then throw exception
			if (parsed_convert || parsed_change) {
				if (parsed_output_folder == null) {
					throw new ParseException("PARSE ERROR: Output folder is NOT set");
				}
			}
			// Check if convert or change are NOT set, but output folder is, then throw exception
			if (!parsed_convert && !parsed_change) {
				if (parsed_output_folder != null) {
					throw new ParseException("PARSE ERROR: Output folder is set but convert or change is not chosen. Remove output folder");
				}
			}
		} catch (ParseException e) {
			System.out.println(" ");
			System.out.println(e.getMessage());
			System.out.println(" ");
			helper.printHelp(" ", options);
			System.exit(0);
		}

		// Only to be used as long as validation requires path to jar file
		boolean doValidation = false;
		if (parsed_validate != null) {
			doValidation = true;
		}
		// Only to be used as long as validation requires path to jar file

		// Inform user of inputs
		System.out.println("YOUR INPUT");
		System.out.println("Methods: " + "convert " + parsed_convert + ", check " + parsed_check + ", change " + parsed_change + ", validate " + doValidation + ", conformance " + parsed_conformance + ", verbose " + parsed_verbose);
		if (parsed_input_file != null) {
			System.out.println("Input file: " + parsed_input_file);
		}
		if (parsed_rename != null) {
			System.out.println("Rename output file: " + parsed_rename + ".ods");
		}
		if (parsed_input_folder != null) {
			System.out.println("Input folder: " + parsed_input_folder);
		}
		if (parsed_output_folder != null) {
			System.out.println("Output folder: " + parsed_output_folder);
		}

		// Create output filepath, if file method is chosen
		if (parsed_input_file != null) {
			parsed_output_file = parsed_output_folder + "\\" + FilenameUtils.getBaseName(parsed_input_file) + ".ods";
		}
		if (parsed_input_file != null && parsed_rename != null) {
			parsed_output_file = parsed_output_folder + "\\" + parsed_rename + ".ods";
		}
		if (parsed_input_file != null && parsed_output_folder == null) {
			parsed_output_file = parsed_input_file;
		}

		// Check I/O of user inputs
		IO IO = new IO();
		if (parsed_input_file != null) {
			IO.CheckFilepathIO(parsed_input_file, parsed_output_file);
		}
		else if (parsed_input_folder != null) {
			IO.CheckFolderIO(parsed_input_folder, parsed_output_folder);
		}
		else {
			throw new IOException("No input filepath or input folder has been set");
		}

		// Perform operations
		System.out.println("PERFORMING OPERATIONS ON INPUT");
		operations OperateOn = new operations();
		if (parsed_input_file != null) {
			OperateOn.Filepath(parsed_input_file, parsed_output_file, parsed_convert, parsed_check, parsed_change, parsed_validate, parsed_rename, parsed_conformance, parsed_verbose);
		}
		else if (parsed_input_folder != null) {
			OperateOn.Folder(parsed_input_folder, parsed_output_folder, parsed_recurse, parsed_convert, parsed_check, parsed_change, parsed_validate, parsed_rename, parsed_conformance, parsed_verbose);
		}

		// Inform user of end of application
		System.out.println("APPLICATION FINISHED");
	}
}
