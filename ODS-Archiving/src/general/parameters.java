package general;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class parameters {

    // Data types
    public static boolean p_convert = false;
    public static boolean p_check = false;
    public static boolean p_change = false;
    public static boolean p_validate = false;
    public static boolean p_recurse = false;
    public static boolean p_verbose = false;
    public static boolean p_archival_package = false;
    public static String p_input_file = null;
    public static String p_output_file = null;
    public static String p_input_folder = null;
    public static String p_output_folder = null;
    public static String p_rename = null;
    public static String p_conformance = null;

    // Parse the arguments of the user
    public static void ArgParser(String[] args) {
        //define argument options
        Options options = new Options();

        Option convert = new Option("con", "convert", false, "Convert spreadsheet to .ods file format");
        options.addOption(convert);

        Option check = new Option("che", "check", false, "Check spreadsheet for archival requirements");
        options.addOption(check);

        Option change = new Option("cha", "change", false, "Change spreadsheet according to archival requirements");
        options.addOption(change);

        Option validate = new Option("val", "validate", false, "Validate spreadsheet according to ODF file format standard");
        options.addOption(validate);

        Option recurse = new Option("rec", "recurse", false, "Include subdirectories in input folder");
        options.addOption(recurse);

        Option verbose = new Option("ver", "verbose", false, "Output detailed information");
        options.addOption(verbose);

        Option archivalpackage = new Option("arc", "archivalpackage", false, "Package output spreadsheets in an archivable data package");
        options.addOption(archivalpackage);

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

        Option conformance = Option.builder("cof").longOpt("conformance")
                .argName("conformance")
                .hasArg()
                .required(true)
                .desc("Set level of conformance for archival requirements. Options: all, normal, minimal, experimental or dna").build();
        options.addOption(conformance);

        // define argument parser
        CommandLine cmd;
        CommandLineParser parser = new DefaultParser();
        HelpFormatter helper = new HelpFormatter();

        // Parse arguments
        try {
            cmd = parser.parse(options, args);
            if (cmd.hasOption("con"))
                p_convert = true;
            if (cmd.hasOption("che"))
                p_check = true;
            if (cmd.hasOption("cha"))
                p_change = true;
            if (cmd.hasOption("val"))
                p_validate = true;
            if (cmd.hasOption("rec"))
                p_recurse = true;
            if (cmd.hasOption("ver"))
                p_verbose = true;
            if (cmd.hasOption("arc"))
                p_archival_package = true;
            if (cmd.hasOption("inp"))
                p_input_file = cmd.getOptionValue("inputfile");
            if (cmd.hasOption("inf"))
                p_input_folder = cmd.getOptionValue("inputfolder");
            if (cmd.hasOption("out"))
                p_output_folder = cmd.getOptionValue("outputfolder");
            if (cmd.hasOption("ren"))
                p_rename = cmd.getOptionValue("rename");
            if (cmd.hasOption("cof"))
                p_conformance = cmd.getOptionValue("conformance").toLowerCase();

            // In the following, throw parse errors under certain conditions
            if (p_check || p_change)
                if (!p_conformance.equals("all") && !p_conformance.equals("normal") && !p_conformance.equals("minimal") && !p_conformance.equals("experimental") && !p_conformance.equals("dna"))
                    throw new ParseException("PARSE ERROR: Compliance is not \"all\", \"normal\", \"minimal\", \"experimental\" or \"dna\"");
            // Check if both input filepath and input folder are set, then throw exception
            if (p_input_file != null && p_input_folder != null)
                throw new ParseException("PARSE ERROR: Both input filepath and input folder are set");
            // Check if either input filepath or input folder are not set, then throw exception
            if (p_input_file == null && p_input_folder == null)
                throw new ParseException("PARSE ERROR: Input filepath or input folder are NOT set");
            // Check if convert or change are set, but output folder is NOT, then throw exception
            if (p_convert || p_change)
                if (p_output_folder == null)
                    throw new ParseException("PARSE ERROR: Output folder is NOT set");
            // Check if convert or change are NOT set, but output folder is, then throw exception
            if (!p_convert && !p_change)
                if (p_output_folder != null)
                    throw new ParseException("PARSE ERROR: Output folder is set but convert or change is not chosen. Remove output folder");
            // Check if input folder is NOT set but archival package is, then throw exception
            if (p_input_folder == null && p_archival_package)
                throw new ParseException("PARSE ERROR: You must input a folder to create an archival package. You are inputting a file");
            // Check if archival package is selected but convert or change is NOT, then throw exception
            if (p_archival_package && !p_convert && !p_change)
                throw new ParseException("PARSE ERROR: You must select convert or change method, if you select archivalpackage");
            // Check if archival package and rename is selected at the same time, then throw exception
            if (p_archival_package && p_rename != null)
                throw new ParseException("PARSE ERROR: You cannot use rename method when using archivalpackage method");

        } catch (ParseException e) {
            System.out.println(" ");
            System.out.println(e.getMessage());
            System.out.println(" ");
            helper.printHelp(" ", options);
            System.exit(0);
        }
    }
}
