package archivalRequirements;

import org.apache.commons.cli.*;

public class application {

	public static void main(String[] args) throws Exception {
		// Parse arguments
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
				.required(true)
				.desc("Set spreadsheet output filepath").build();
		options.addOption(output_filepath);

		String function = args[0];
		String filepath = args[1];
		String extension = filepath.substring(filepath.lastIndexOf('.') + 1);
		System.out.println("YOUR INPUT: " + function + " filepath: " + filepath);
		
		switch(extension.toLowerCase()) {
		
			case "fods":
			case "ods":
			case "ots":
				// CELL VALUES
				cellValues cellValue = new cellValues();
				Boolean hasCellValue = cellValue.check(filepath);
			
				// DATA CONNECTIONS
				dataConnections dataConnection = new dataConnections();
				int conns = dataConnection.check(filepath);

				if (function == "CheckChange" && conns > 0) {
					dataConnection.change(filepath);
				}
				
				break;
				
				default:
					System.out.println("--> File format is not an accepted extension");
		}
	}
}
