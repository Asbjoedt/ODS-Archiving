package general;

import org.apache.commons.io.FilenameUtils;

public class application {

	// Main method of the application
	public static void main(String[] args) throws Exception {
		// Inform user of beginning of application
		System.out.println("ODS Archiving v1.0.0");
		System.out.println("@Asbjørn Skødt, web: https://github.com/Asbjoedt/ODS-Archiving");
		System.out.println("---");

		// Parse arguments
		parameters Parse = new parameters();
		Parse.ArgParser(args);

		boolean convert = parameters.p_convert;
		boolean check = parameters.p_check;
		boolean change = parameters.p_change;
		boolean validate = parameters.p_validate;
		boolean recurse = parameters.p_recurse;
		boolean verbose = parameters.p_verbose;
		boolean archival_package = parameters.p_archival_package;
		String input_file = parameters.p_input_file;
		String output_file = parameters.p_output_file;
		String input_folder = parameters.p_input_folder;
		String output_folder = parameters.p_output_folder;
		String rename = parameters.p_rename;
		String conformance = parameters.p_conformance;

		// Inform user of inputs
		System.out.println("YOUR INPUT");
		System.out.println("OPTIONS: " + "convert " + convert + ", check " + check + ", change " + change + ", validate " + validate + ", conformance " + conformance + ", verbose " + verbose + ", archivalpackage " + archival_package);
		if (input_file != null)
			System.out.println("INPUT FILE: " + input_file);
		if (rename != null)
			System.out.println("RENAME OUTPUT FILE: " + rename + ".ods");
		if (input_folder != null)
			System.out.println("INPUT FOLDER: " + input_folder);
		if (output_folder != null)
			System.out.println("OUTPUT FOLDER: " + output_folder);
		System.out.println("---");

		// Perform operations
		System.out.println("PERFORMING OPERATIONS ON INPUT");
		create Create = new create();
		IO IO = new IO();
		operations OperateOn = new operations();
		if (input_file != null) {
			// If chosen, create archival package
			if (archival_package)
				output_folder = Create.ArchivalPackage(output_folder);

			// Set output filepath
			output_file = Create.OutputFilepath(input_file, output_folder, rename, archival_package);

			// Check I/O of user inputs
			IO.CheckFilepathIO(input_file, output_file, convert);

			// Operate on user input
			OperateOn.Filepath(input_file, output_file, convert, check, change, validate, rename, conformance, verbose, archival_package);
		}
		else if (input_folder != null) {
			// Set output folder, if only input folder is set
			if (output_folder == null)
				output_folder = input_folder;

			// Check I/O of user inputs
			IO.CheckFolderIO(input_folder, output_folder);

			// If chosen, create archival package
			if (archival_package)
				output_folder = Create.ArchivalPackage(output_folder);

			// Operate on user input
			OperateOn.Folder(input_folder, output_folder, recurse, convert, check, change, validate, rename, conformance, verbose, archival_package);
		}

		// Inform user of end of application
		System.out.println("---");
		System.out.println("APPLICATION FINISHED");
	}
}
