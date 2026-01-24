package general;

import requirements.*;
import java.util.*;

public class check {

    // Class for creating list of checks
    public static class checkList {
        // Class data types
        boolean mimeType = false;
        int dataConnections = 0;
        int externalCellReferences = 0;
        int RTDFunctions = 0;
        int externalObjects = 0;
        int embeddedObjects = 0;
        boolean content = false;
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
        public checkList(boolean mimeType, int dataConns, int cellrefs, int rtd, int extobjs, int embedobjs, boolean hasContent, int macros, boolean embedFonts, boolean settingsDOM, boolean metadata, int hyperlinks, boolean previewImage, int printers, boolean loadReadOnly, boolean activeSheet, int digitalSignatures) {
            this.mimeType = mimeType;
            this.dataConnections = dataConns;
            this.externalCellReferences = cellrefs;
            this.RTDFunctions = rtd;
            this.externalObjects = extobjs;
            this.embeddedObjects = embedobjs;
            this.content = hasContent;
            this.macros = macros;
            this.embeddedFonts = embedFonts;
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
    public List<checkList> CheckODFToolkit(String filepath, String conformance, boolean verbose) throws Exception {
        // Create list and data types to return
        List<checkList> results = new ArrayList<>();
        boolean mimeType = false;
        int dataConns = 0;
        int extCellRefs = 0;
        int rtdFunctions = 0;
        int extObjs = 0;
        int embedObjs = 0;
        boolean content = false;
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
            mimeType = MimeType.Check_ODFToolkit(filepath, verbose);

            // DATA CONNECTIONS
            dataConnections DataConnections = new dataConnections();
            dataConns = DataConnections.Check_ODFToolkit(filepath, verbose);

            // EXTERNAL CELL REFERENCES
            externalCellReferences ExternalCellReference = new externalCellReferences();
            extCellRefs = ExternalCellReference.Check_ODFToolkit(filepath, verbose);

            // RTD FUNCTIONS
            RTDFunctions RTDFunctions = new RTDFunctions();
            rtdFunctions = RTDFunctions.Check_ODFToolkit(filepath, verbose);

            // EXTERNAL OBJECTS
            externalObjects ExternalObjects = new externalObjects();
            extObjs = ExternalObjects.Check_ODFToolkit(filepath, verbose);

            // EMBEDDED OBJECTS
            embeddedObjects EmbeddedObjects = new embeddedObjects();
            embedObjs = EmbeddedObjects.Check_ODFToolkit(filepath, verbose);
        }
        if (conformance.equals("should") || conformance.equals("may") || conformance.equals("experimental")) {
            // CONTENT
            contentExists ContentExists = new contentExists();
            content = ContentExists.Check_ODFToolkit(filepath);

            // MACROS
            macros Macros = new macros();
            macros = Macros.Check_ODFToolkit(filepath, verbose);

            // EMBEDDED FONTS
            embeddedFonts EmbeddedFonts = new embeddedFonts();
            embeddedFonts = EmbeddedFonts.Check_ODFToolkit(filepath, verbose);
        }
        if (conformance.equals("may") || conformance.equals("experimental")) {
            // SETTINGSDOM FILE
            settingsDOM SettingsDOM = new settingsDOM();
            settingsDOM = SettingsDOM.Check_ODFToolkit(filepath);

            // CREATOR METADATA
            metadata Metadata = new metadata();
            metadata = Metadata.Check_ODFToolkit(filepath, verbose);

            // CELL HYPERLINKS
            hyperlinks Hyperlinks = new hyperlinks();
            hyperlinks = Hyperlinks.Check_ODFToolkit(filepath, verbose);

            // PREVIEW IMAGE
            previewImage PreviewImage = new previewImage();
            previewImage = PreviewImage.Check_ODFToolkit(filepath, verbose);
        }
        if (conformance.equals("experimental")) {
            // PRINTER SETTINGS
            printerSettings PrinterSettings = new printerSettings();
            printers = PrinterSettings.Check_ODFToolkit(filepath, verbose);

            // LOADREADONLY ATTRIBUTE
            loadReadonly LoadReadOnly = new loadReadonly();
            loadReadOnly = LoadReadOnly.Check_ODFToolkit(filepath, verbose);

            // ACTIVE SHEET
            activeSheet ActiveSheet = new activeSheet();
            activeSheet = ActiveSheet.Check_ODFToolkit(filepath, verbose);
        }
        if (conformance.equals("dna")) {
            // DATA CONNECTIONS
            dataConnections DataConnections = new dataConnections();
            dataConns = DataConnections.Check_ODFToolkit(filepath, verbose);

            // EXTERNAL CELL REFERENCES
            externalCellReferences ExternalCellReference = new externalCellReferences();
            extCellRefs = ExternalCellReference.Check_ODFToolkit(filepath, verbose);

            // RTD FUNCTIONS
            RTDFunctions RTDFunctions = new RTDFunctions();
            rtdFunctions = RTDFunctions.Check_ODFToolkit(filepath, verbose);

            // EXTERNAL OBJECTS
            externalObjects ExternalObjects = new externalObjects();
            extObjs = ExternalObjects.Check_ODFToolkit(filepath, verbose);

            // EMBEDDED OBJECTS
            embeddedObjects EmbeddedObjects = new embeddedObjects();
            embedObjs = EmbeddedObjects.Check_ODFToolkit(filepath, verbose);

            // CONTENT
            contentExists ContentExists = new contentExists();
            content = ContentExists.Check_ODFToolkit(filepath);

            // MACROS
            macros Macros = new macros();
            macros = Macros.Check_ODFToolkit(filepath, verbose);

            // DIGITAL SIGNATURES
            digitalSignatures DigitalSignatures = new digitalSignatures();
            digitalSignatures = DigitalSignatures.Check_ODFToolkit(filepath, verbose);
        }

        // Add info from checks to list and return it
        results.add(new checkList(mimeType, dataConns, extCellRefs, rtdFunctions, extObjs, embedObjs, content, macros, embeddedFonts, settingsDOM, metadata, hyperlinks, previewImage, printers, loadReadOnly, activeSheet, digitalSignatures));
        return results;
    }
}
