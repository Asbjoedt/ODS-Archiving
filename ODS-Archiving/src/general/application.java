package general;

import java.io.IOException;

public class application {

	// Main method of the application
	public static void main(String[] args) throws Exception {
		// Inform user of beginning of application
		System.out.println("ODS Archiving v1.0.0-BETA");
		System.out.println("@Asbjørn Skødt, web: https://github.com/Asbjoedt/ODS-Archiving");
		System.out.println("------");

		// Parse arguments
		parameters Parse = new parameters();
		Parse.ArgParser(args);
		boolean convert = parameters.p_convert;
		boolean check = parameters.p_check;
		boolean change = parameters.p_change;
		boolean validate = parameters.p_validate;
		boolean recurse = parameters.p_recurse;
		boolean report = parameters.p_report;
		boolean verbose = parameters.p_verbose;
		boolean archival_package = parameters.p_archival_package;
		String input_file = parameters.p_input_file;
		String output_file = parameters.p_output_file;
		String input_folder = parameters.p_input_folder;
		String output_folder = parameters.p_output_folder;
		String conformance = parameters.p_conformance;

		// Inform user of inputs
		System.out.println("YOUR INPUT");
		System.out.println("---");
		System.out.println("OPTIONS: " + "convert " + convert + ", check " + check + ", change " + change + ", validate " + validate + ", conformance " + conformance + ", recurse" + recurse + ", report " + report + ", verbose " + verbose + ", archivalpackage " + archival_package);
		if (input_file != null)
			System.out.println("INPUT FILE: " + input_file);
		if (input_folder != null)
			System.out.println("INPUT FOLDER: " + input_folder);
		if (output_folder != null)
			System.out.println("OUTPUT FOLDER: " + output_folder);
		System.out.println("------");

		// Inform user of performing operations
		System.out.println("PERFORMING OPERATIONS ON INPUT");

		// Create objects
		create Create = new create();
		IO IO = new IO();

		// Check if LibreOffice is installed and set path to executable
		if(convert)
			IO.CheckLibreOfficeIO();

		if (input_file != null)
			FileMethod(input_file, output_file, output_folder, convert, check, change, validate, conformance, report, verbose, archival_package);

		if (input_folder != null) {
			FolderMethod(input_folder, output_folder, recurse, convert, check, change, validate, conformance, report, verbose, archival_package);
		}

		// Inform user of end of application
		System.out.println("---");
		System.out.println("APPLICATION FINISHED");
	}

	public static void FileMethod(String input_file, String output_file, String output_folder, boolean convert, boolean check, boolean change, boolean validate, String conformance, boolean report, boolean verbose, boolean archival_package) throws Exception {
		create Create = new create();
		IO IO = new IO();
		operations OperateOn = new operations();

		// Set output filepath
		output_file = Create.OutputFilepath(input_file, output_folder, archival_package);

		// Check simple I/O of user inputs
		IO.CheckFilepathIO(input_file, output_file, convert);

		// If chosen, create archival package
		if (archival_package)
			output_folder = Create.ArchivalPackage(output_folder);

		// Copy spreadsheet under certain conditions for conversion, change or archival package
		if (!convert && change)
			Create.CopySpreadsheet(input_file, output_file);
		if (!convert && !change && archival_package)
			Create.CopySpreadsheet(input_file, output_file);

		// Check advanced I/O of user inputs
		IO.CheckBasicReadability(input_file);
		IO.CheckODFToolkitIO(input_file);

		// Operate on user input
		OperateOn.Filepath(input_file, output_file, output_folder, convert, check, change, validate, conformance, report, verbose, archival_package);

		// Zip the archival package if selected
		if(archival_package) {
			Create.ZipArchivalPackage(output_folder);
		}
	}

	public static void FolderMethod(String input_folder, String output_folder, boolean recurse, boolean convert, boolean check, boolean change, boolean validate, String conformance, boolean report, boolean verbose, boolean archival_package) throws Exception {
		create Create = new create();
		IO IO = new IO();
		operations OperateOn = new operations();

		// Set output folder, if only input folder is set
		if (output_folder == null)
			output_folder = input_folder;

		// Check folder I/O of user inputs
		IO.CheckInputFolderIO(input_folder);
		IO.CheckOutputFolderIO(output_folder);

		// If chosen, create archival package
		if (archival_package)
			output_folder = Create.ArchivalPackage(output_folder);

		// Operate on user input
		OperateOn.Folder(input_folder, output_folder, recurse, convert, check, change, validate, conformance, report, verbose, archival_package);

		// Zip the archival package if selected
		if(archival_package) {
			Create.ZipArchivalPackage(output_folder);
		}
	}
}
