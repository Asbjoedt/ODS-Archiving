package general;

import org.apache.commons.io.FilenameUtils;
import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IO {

    // Globally accessible variable for path to LibreOffice
    public static String LibreOffice_path = null;

    // Collective method for checking availability of input and output filepaths
    public void CheckFilepathIO(String input_filepath, String output_filepath, boolean convert) throws IOException {
        // Check if file exists
        CheckExistence(input_filepath);

        // Check for accepted input file formats
        CheckExtension(input_filepath, convert);

        // Check IO of output folder and output filepath
        CheckOutputIO(input_filepath, output_filepath);
    }

    // Method for checking if the file exists and is not encrypted or corrupted
    public void CheckExistence(String input_filepath) throws IOException {
        // Check if input filepath exists
        File input_file = new File(input_filepath);
        if (!input_file.exists() && !input_file.isDirectory()) {
            throw new IOException("ERROR: Input file does not exist");
        }
    }

    // Method for checking the correct file format extensions
    public void CheckExtension(String input_filepath, boolean convert) throws IOException {
        // Check for accepted input file format extensions for conversion
        String input_extension = FilenameUtils.getExtension(input_filepath).toLowerCase();
        switch (input_extension) {

            case "fods":
            case "numbers":
            case "ods":
            case "ots":
            case "xls":
            case "xlt":
            case "xlsm":
            case "xlsx":
            case "xltm":
            case "xltx":
                // Do nothing
                break;

            default:
                throw new IOException("CHECK ODS_3: Input filepath does not have an accepted file format extension");
        }

        // Check for accepted input file format extensions if NOT conversion
        if (!convert && input_extension.equals("fods"))
            throw new IOException("ERROR: Input extension .fods is currently only supported if --convert is selected");
        if (!convert && !input_extension.equals("ods") && !input_extension.equals("ots"))
            throw new IOException("CHECK ODS_3: Input filepath does not have an accepted file format extension. Select --convert");
    }

    // Method for checking IO of output folder and files
    public void CheckOutputIO(String input_filepath, String output_filepath) throws IOException {
        // Check if output directory exists
        String parent = FilenameUtils.getFullPathNoEndSeparator(output_filepath);
        File directory = new File(parent);
        if (!directory.exists()) {
            throw new IOException("ERROR: Output directory does not exist");
        }

        // Check if output file already exists
        if (!input_filepath.equals(output_filepath)) {
            File output_file = new File(output_filepath);
            if (output_file.exists()) {
                throw new IOException("ERROR: Output file already exists");
            }
        }
    }

    // Method for checking if the file exists and is not encrypted or corrupted
    public void CheckBasicReadability(String input_filepath) throws IOException {
        // Check if input filepath exists
        File input_file = new File(input_filepath);

        // Check for file protection and corruption
        boolean readable = input_file.canRead();
        boolean writeable = input_file.canWrite();
        if (!readable) {
            throw new IOException("CHECK ODS_1: File cannot be processed e.g. is encrypted or corrupted");
        }
        if (!writeable) {
            throw new IOException("CHECK ODS_1: File cannot be processed e.g. is encrypted or corrupted");
        }
    }

    // Method for checking if the file can be processed by ODF Toolkit
    public void CheckODFToolkitIO(String input_filepath) throws IOException {
        try {
            OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(input_filepath);
        }
        catch (Exception e) {
            throw new IOException("ERROR: ODF Toolkit cannot process the file: " + e.getMessage());
        }
    }

    // Method for checking availability of input folder
    public void CheckInputFolderIO(String input_folder) throws IOException {
        // Check if input directory exists
        File inputfolder = new File(input_folder);
        if (!inputfolder.exists()) {
            throw new IOException("ERROR: Input directory does not exist");
        }

        // Check for input directory protection
        boolean inputfolder_readable = inputfolder.canRead();
        boolean inputfolder_writeable = inputfolder.canWrite();
        if (!inputfolder_readable) {
            throw new IOException("ERROR: Input folder cannot be processed e.g. has password protection");
        }
        if (!inputfolder_writeable) {
            throw new IOException("ERROR: Input folder cannot be processed e.g. has password protection");
        }
    }

    // Method for checking availability of output folder
    public void CheckOutputFolderIO(String output_folder) throws IOException {
        // Check if output directory exists
        File outputfolder = new File(output_folder);
        if (!outputfolder.exists()) {
            throw new IOException("ERROR: Output directory does not exist");
        }

        // Check for output directory protection
        boolean outputfolder_readable = outputfolder.canRead();
        boolean outputfolder_writeable = outputfolder.canWrite();
        if (!outputfolder_readable) {
            throw new IOException("ERROR: Output folder cannot be processed e.g. has encryption");
        }
        if (!outputfolder_writeable) {
            throw new IOException("ERROR: Output folder cannot be processed e.g. has encryption");
        }
    }

    // Method for checking if LibreOffice is installed, in which dir and setting the dir path
    public void CheckLibreOfficeIO() throws IOException, InterruptedException {

        // Check for operating system and define search paths for LibreOffice
        String search_app_name = null;
        String[] search_array = null;
        String operating_system = System.getProperty("os.name").replaceAll("\\s.*","");
        if(operating_system.equals("Windows")) {
            search_app_name = "scalc.exe";
            search_array = new String[] {
                    "C:\\Program Files\\LibreOffice",
                    "C:\\Program Files (x86)\\LibreOffice",
            };
        }
        else {
            search_app_name = "scalc";
            search_array = new String[] {
                    "/var/lib/flatpak/app/org.libreoffice.LibreOffice/"
            };
        }

        // Search filesystem for LibreOffice Calc
        for (String search_dir : search_array) {
            Path startingDir = Paths.get(search_dir);
            String filenameToFind = search_app_name;

            try {
                LibreOffice_path = Files.walk(startingDir)
                        .filter(path -> path.getFileName().toString().equals(filenameToFind))
                        .map(Path::toString)
                        .findFirst()
                        .orElse(null);

                if (LibreOffice_path != null)
                    return;
            } catch (UncheckedIOException e) {
                continue;
            }
        }

        // If path cannot be found throw exception
        if(LibreOffice_path == null)
            throw new IOException("LibreOffice cannot be found.");
    }
}
