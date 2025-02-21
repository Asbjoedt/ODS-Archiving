package general;

import requirements.*;
import java.util.*;

public class change {

    // Class for creating list of changes
    public static class changeList {
        // Class data types
        int dataConnections = 0;
        int externalCellReferences = 0;
        int RTDFunctions = 0;
        int externalObjects = 0;
        int embeddedObjects = 0;
        int macros = 0;
        boolean loadReadOnly = false;
        int printerSettings = 0;
        boolean metadata = false;
        int hyperlinks = 0;
        boolean embeddedFonts = false;
        boolean activeSheet = false;
        boolean settingsDOM = false;
        int digitalSignatures = 0;

        // Method for class data types
        public changeList(int dataConns, int cellrefs, int rtd, int extobjs, int embedsobjs, int macros, boolean loadReadOnly, int printers, boolean metadata, int hyperlinks, boolean embeddedFonts, boolean activeSheet, boolean settingsDOM, int digitalSignatures) {
            this.dataConnections = dataConns;
            this.externalCellReferences = cellrefs;
            this.RTDFunctions = rtd;
            this.externalObjects = extobjs;
            this.embeddedObjects = embedsobjs;
            this.macros = macros;
            this.loadReadOnly = loadReadOnly;
            this.printerSettings = printers;
            this.metadata = metadata;
            this.hyperlinks = hyperlinks;
            this.embeddedFonts = embeddedFonts;
            this.activeSheet = activeSheet;
            this.settingsDOM = settingsDOM;
            this.digitalSignatures = digitalSignatures;
        }
    }

    // Perform check of archival requirements on OpenDocument Spreadsheets using ODF Toolkit
    public List<changeList> ChangeODFToolkit(String filepath, String conformance, boolean verbose) throws Exception {
        // Create list to return
        List<changeList> results = new ArrayList<>();
        int dataConns = 0;
        int extCellRefs = 0;
        int rtdFunctions = 0;
        int extObjs = 0;
        int embedObjs = 0;
        int macros = 0;
        boolean loadReadOnly = false;
        int printers = 0;
        boolean metadata = false;
        int hyperlinks = 0;
        boolean embeddedFonts = false;
        boolean activeSheet = false;
        boolean settingsDOM = false;
        int digitalSignatures = 0;

        // Perform checks based on compliance
        if (conformance.equals("must") || conformance.equals("should") || conformance.equals("may") || conformance.equals("experimental") || conformance.equals("dna")) {
            // DATA CONNECTIONS
            dataConnections DataConnections = new dataConnections();
            dataConns = DataConnections.Change_ODFToolkit(filepath, verbose);

            // EXTERNAL CELL REFERENCES
            externalCellReferences ExternalCellReference = new externalCellReferences();
            extCellRefs = ExternalCellReference.Change_ODFToolkit(filepath, verbose);

            // RTD FUNCTIONS
            RTDFunctions RTDFunctions = new RTDFunctions();
            rtdFunctions = RTDFunctions.Change_ODFToolkit(filepath, verbose);

            // EXTERNAL OBJECTS
            externalObjects ExternalObjects = new externalObjects();
            extObjs = ExternalObjects.Change_ODFToolkit(filepath, verbose);

            // MACROS
            macros Macros = new macros();
            macros = Macros.Change_ODFToolkit(filepath, verbose);
        }
        if (conformance.equals("should") || conformance.equals("may") || conformance.equals("experimental")) {
            // EMBEDDED OBJECTS
            embeddedObjects EmbeddedObjects = new embeddedObjects();
            embedObjs = EmbeddedObjects.Change_ODFToolkit(filepath, verbose);

            // LOADREADONLY
            loadReadonly LoadReadOnly = new loadReadonly();
            loadReadOnly = LoadReadOnly.Change_ODFToolkit(filepath, verbose);
        }
        if (conformance.equals("may") || conformance.equals("experimental")) {
            // PRINTER SETTINGS
            printerSettings PrinterSettings = new printerSettings();
            printers = PrinterSettings.Change_ODFToolkit(filepath, verbose);

            // CREATOR METADATA
            metadata Metadata = new metadata();
            metadata = Metadata.Change_ODFToolkit(filepath, verbose);

/*            // HYPERLINKS
            hyperlinks Hyperlinks = new hyperlinks();
            hyperlinks = Hyperlinks.Change_ODFToolkit(filepath, verbose);*/
        }
        if (conformance.equals("experimental")) {
            // EMBEDDED FONTS
            embeddedFonts EmbeddedFonts = new embeddedFonts();
            embeddedFonts = EmbeddedFonts.Change_ODFToolkit(filepath, verbose);

            // ACTIVE SHEET
            activeSheet ActiveSheet = new activeSheet();
            activeSheet = ActiveSheet.Change_ODFToolkit(filepath, verbose);

/*            // SETTINGSDOM
            settingsDOM SettingsDOM = new settingsDOM();
            settingsDOM = SettingsDOM.Change_ODFToolkit(filepath);*/
        }
        if (conformance.equals("dna")) {
            // DIGITAL SIGNATURES
            digitalSignatures DigitalSignatures = new digitalSignatures();
            digitalSignatures = DigitalSignatures.Change_ODFToolkit(filepath, verbose);
        }

        // Add to list and return it
        results.add(new changeList(dataConns, extCellRefs, rtdFunctions, extObjs, embedObjs, macros, loadReadOnly, printers, metadata, hyperlinks, embeddedFonts, activeSheet, settingsDOM, digitalSignatures));
        return results;
    }
}
