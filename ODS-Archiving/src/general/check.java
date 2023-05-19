package general;

import requirements.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;
import java.util.zip.ZipInputStream;

public class check {

    // Class for creating list of checks
    public class checkList {
        // Class data types
        int dataConnections = 0;
        int externalCellReferences = 0;
        int RTDFunctions = 0;
        int externalObjects = 0;
        int embeddedObjects = 0;
        boolean content = false;
        int macros = 0;
        boolean loadReadOnly = false;
        int printerSettings = 0;
        boolean metadata = false;
        int hyperlinks = 0;
        boolean embeddedFonts = false;
        boolean activeSheet = false;
        boolean settingsDOM = false;

        // Method for class data types
        public checkList(int dataConns, int cellrefs, int rtd, int extobjs, int embedobjs, boolean hasContent, int macros, boolean loadReadOnly, int printers, boolean metadata, int hyperlinks, boolean embedFonts, boolean activeSheet, boolean settingsDOM) {
            this.dataConnections = dataConns;
            this.externalCellReferences = cellrefs;
            this.RTDFunctions = rtd;
            this.externalObjects = extobjs;
            this.embeddedObjects = embedobjs;
            this.content = hasContent;
            this.macros = macros;
            this.loadReadOnly = loadReadOnly;
            this.printerSettings = printers;
            this.metadata = metadata;
            this.hyperlinks = hyperlinks;
            this.embeddedFonts = embedFonts;
            this.activeSheet = activeSheet;
            this.settingsDOM = settingsDOM;
        }
    }

    // Perform check of archival requirements on OpenDocument Spreadsheets using ODF Toolkit
    public List<checkList> Check_ODFToolkit(String filepath, String conformance, boolean verbose) throws Exception {
        // Create list and data types to return
        List<checkList> results = new ArrayList<>();
        int dataConns = 0;
        int extCellRefs = 0;
        int rtdFunctions = 0;
        int extObjs = 0;
        int embedObjs = 0;
        boolean content = false;
        int macros = 0;
        boolean loadReadOnly = false;
        int printers = 0;
        boolean metadata = false;
        int hyperlinks = 0;
        boolean embeddedFonts = false;
        boolean activeSheet = false;
        boolean settingsDOM = false;

        // Set input
        String input = filepath;

        // Perform checks based on compliance
        if (conformance.equals("must") || conformance.equals("should") || conformance.equals("may") || conformance.equals("experimental")) {
            // DATA CONNECTIONS
            dataConnections DataConnections = new dataConnections();
            dataConns = DataConnections.Check_ODFToolkit(input, verbose);

            // EXTERNAL CELL REFERENCES
            externalCellReferences ExternalCellReference = new externalCellReferences();
            extCellRefs = ExternalCellReference.Check_ODFToolkit(input, verbose);

            // RTD FUNCTIONS
            RTDFunctions RTDFunctions = new RTDFunctions();
            rtdFunctions = RTDFunctions.Check_ODFToolkit(input, verbose);

            // EXTERNAL OBJECTS
            externalObjects ExternalObjects = new externalObjects();
            extObjs = ExternalObjects.Check_ODFToolkit(input, verbose);

            // EMBEDDED OBJECTS
            embeddedObjects EmbeddedObjects = new embeddedObjects();
            embedObjs = EmbeddedObjects.Check_ODFToolkit(input, verbose);
        }
        if (conformance.equals("should") || conformance.equals("may") || conformance.equals("experimental")) {
            // CONTENT
            contentExists ContentExists = new contentExists();
            content = ContentExists.Check_ODFToolkit(input);

            // MACROS
            macros Macros = new macros();
            macros = Macros.Check_ODFToolkit(input, verbose);

            // LOADREADONLY
            loadReadonly LoadReadOnly = new loadReadonly();
            loadReadOnly = LoadReadOnly.Check_ODFToolkit(input, verbose);
        }
        if (conformance.equals("may") || conformance.equals("experimental")) {
            // PRINTER SETTINGS
            printerSettings PrinterSettings = new printerSettings();
            printers = PrinterSettings.Check_ODFToolkit(input, verbose);

            // METADATA
            metadata Metadata = new metadata();
            metadata = Metadata.Check_ODFToolkit(input, verbose);

            // HYPERLINKS
            hyperlinks Hyperlinks = new hyperlinks();
            hyperlinks = Hyperlinks.Check_ODFToolkit(input, verbose);
        }
        if (conformance.equals("experimental")) {
            // EMBEDDED FONTS
            embeddedFonts EmbeddedFonts = new embeddedFonts();
            embeddedFonts = EmbeddedFonts.Check_ODFToolkit(input, verbose);

            // ACTIVE SHEET
            activeSheet ActiveSheet = new activeSheet();
            activeSheet = ActiveSheet.Check_ODFToolkit(input, verbose);

            // SETTINGSDOM
            settingsDOM SettingsDOM = new settingsDOM();
            settingsDOM = SettingsDOM.Check_ODFToolkit(input);
        }

        // Add info from checks to list and return it
        results.add(new checkList(dataConns, extCellRefs, rtdFunctions, extObjs, embedObjs, content, macros, loadReadOnly, printers, metadata, hyperlinks, embeddedFonts, activeSheet, settingsDOM));
        return results;
    }
}
