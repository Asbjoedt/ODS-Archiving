package general;

import requirements.*;
import java.util.*;

public class change {

    // Class for creating list of changes
    public static class changeList {
        // Class data types
        boolean mimeType = false;
        int dataConnections = 0;
        int externalCellReferences = 0;
        int RTDFunctions = 0;
        int externalObjects = 0;
        int embeddedObjects = 0;
        int macros = 0;
        boolean embeddedFonts = false;
        boolean settingsDOM = false;
        boolean metadata = false;
        int hyperlinks = 0;
        boolean previewImage = false;
        int printerSettings = 0;
        boolean loadReadOnly = false;
        boolean activeSheet = false;
        int digitalSignatures = 0;

        // Method for class data types
        public changeList(boolean mimeType, int dataConns, int cellrefs, int rtd, int extobjs, int embedsobjs, int macros, boolean embeddedFonts, boolean settingsDOM, boolean metadata, int hyperlinks, boolean previewImage, int printers, boolean loadReadOnly, boolean activeSheet, int digitalSignatures) {
            this.mimeType = mimeType;
            this.dataConnections = dataConns;
            this.externalCellReferences = cellrefs;
            this.RTDFunctions = rtd;
            this.externalObjects = extobjs;
            this.embeddedObjects = embedsobjs;
            this.macros = macros;
            this.embeddedFonts = embeddedFonts;
            this.settingsDOM = settingsDOM;
            this.metadata = metadata;
            this.hyperlinks = hyperlinks;
            this.previewImage = previewImage;
            this.printerSettings = printers;
            this.loadReadOnly = loadReadOnly;
            this.activeSheet = activeSheet;
            this.digitalSignatures = digitalSignatures;
        }
    }

    // Perform check of archival requirements on OpenDocument Spreadsheets using ODF Toolkit
    public List<changeList> ChangeODFToolkit(String filepath, String conformance, boolean verbose) throws Exception {
        // Create list to return
        List<changeList> results = new ArrayList<>();
        boolean mimeType = false;
        int dataConns = 0;
        int extCellRefs = 0;
        int rtdFunctions = 0;
        int extObjs = 0;
        int embedObjs = 0;
        int macros = 0;
        boolean embeddedFonts = false;
        boolean settingsDOM = false;
        boolean metadata = false;
        int hyperlinks = 0;
        boolean previewImage = false;
        int printers = 0;
        boolean loadReadOnly = false;
        boolean activeSheet = false;
        int digitalSignatures = 0;

        // Perform checks based on compliance
        if (conformance.equals("must") || conformance.equals("should") || conformance.equals("may") || conformance.equals("experimental")) {
            // MIMETYPE
            mimeType MimeType = new mimeType();
            mimeType = MimeType.Change_ODFToolkit(filepath, verbose);

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

            // EMBEDDED OBJECTS
            embeddedObjects EmbeddedObjects = new embeddedObjects();
            embedObjs = EmbeddedObjects.Change_ODFToolkit(filepath, verbose);
        }
        if (conformance.equals("should") || conformance.equals("may") || conformance.equals("experimental")) {
            // MACROS
            macros Macros = new macros();
            macros = Macros.Change_ODFToolkit(filepath, verbose);

            // EMBEDDED FONTS
            embeddedFonts EmbeddedFonts = new embeddedFonts();
            embeddedFonts = EmbeddedFonts.Change_ODFToolkit(filepath, verbose);
        }
        if (conformance.equals("may") || conformance.equals("experimental")) {
            // SETTINGSDOM FILE
            settingsDOM SettingsDOM = new settingsDOM();
            settingsDOM = SettingsDOM.Change_ODFToolkit(filepath);

            // CREATOR METADATA
            metadata Metadata = new metadata();
            metadata = Metadata.Change_ODFToolkit(filepath, verbose);

            // CELL HYPERLINKS
            //hyperlinks Hyperlinks = new hyperlinks();
            //hyperlinks = Hyperlinks.Change_ODFToolkit(filepath, verbose);

            // PREVIEW IMAGE
            previewImage PreviewImage = new previewImage();
            previewImage = PreviewImage.Change_ODFToolkit(filepath, verbose);
        }
        if (conformance.equals("experimental")) {
            // PRINTER SETTINGS
            printerSettings PrinterSettings = new printerSettings();
            printers = PrinterSettings.Change_ODFToolkit(filepath, verbose);

            // LOADREADONLY ATTRIBUTE
            loadReadonly LoadReadOnly = new loadReadonly();
            loadReadOnly = LoadReadOnly.Change_ODFToolkit(filepath, verbose);

            // ACTIVE SHEET
            activeSheet ActiveSheet = new activeSheet();
            activeSheet = ActiveSheet.Change_ODFToolkit(filepath, verbose);
        }
        if (conformance.equals("dna")) {
            // MACROS
            macros Macros = new macros();
            macros = Macros.Change_ODFToolkit(filepath, verbose);

            // DIGITAL SIGNATURES
            digitalSignatures DigitalSignatures = new digitalSignatures();
            digitalSignatures = DigitalSignatures.Change_ODFToolkit(filepath, verbose);
        }

        // Add to list and return it
        results.add(new changeList(mimeType, dataConns, extCellRefs, rtdFunctions, extObjs, embedObjs, macros, embeddedFonts, settingsDOM, metadata, hyperlinks, previewImage, printers, loadReadOnly, activeSheet, digitalSignatures));
        return results;
    }
}
