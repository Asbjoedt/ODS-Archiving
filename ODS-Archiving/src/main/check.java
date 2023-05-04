package main;

import java.util.*;

public class check {

    // Class for creating list of checks
    public class checkList {
        // Class data types
        boolean content = false;
        int dataConnections = 0;
        int externalCellReferences = 0;
        int RTDFunctions = 0;
        int embeddedObjects = 0;
        int externalObjects = 0;
        int printerSettings = 0;
        int hyperlinks = 0;
        boolean activeSheet = false;
        boolean embeddedFonts = false;
        boolean loadReadOnly = false;
        int macros = 0;
        boolean metadata = false;

        // Method for class data types
        public checkList(boolean hasContent, int conns, int cellrefs, int rtd, int embedobjs, int extobjs, int printers, int hyperlinks, boolean activeSheet, boolean embedFonts, boolean loadReadOnly, int macros, boolean metadata) {
            this.content = hasContent;
            this.dataConnections = conns;
            this.externalCellReferences = cellrefs;
            this.RTDFunctions = rtd;
            this.embeddedObjects = embedobjs;
            this.externalObjects = extobjs;
            this.printerSettings = printers;
            this.hyperlinks = hyperlinks;
            this.activeSheet = activeSheet;
            this.embeddedFonts = embedFonts;
            this.loadReadOnly = loadReadOnly;
            this.macros = macros;
            this.metadata = metadata;
        }
    }

    // Perform check of archival requirements on OpenDocument Spreadsheets using ODF Toolkit
    public List<checkList> Check_ODFToolkit(String filepath) throws Exception {
        // Create list to return
        List<checkList> results = new ArrayList<>();

        // CONTENT
        contentExists ContentExists = new contentExists();
        boolean content = ContentExists.Check_ODFToolkit(filepath);

        // DATA CONNECTIONS
        dataConnections DataConnection = new dataConnections();
        int conns = DataConnection.Check_ODFToolkit(filepath);

        // EXTERNAL CELL REFERENCES
        externalCellReferences ExternalCellReference = new externalCellReferences();
        int extCellRefs = ExternalCellReference.Check_ODFToolkit(filepath);

        // RTD FUNCTIONS
        RTDFunctions RTDFunctions = new RTDFunctions();
        int rtdFunctions = RTDFunctions.Check_ODFToolkit(filepath);

        // PRINTER SETTINGS
        printerSettings PrinterSettings = new printerSettings();
        int printers = PrinterSettings.Check_ODFToolkit(filepath);

        // EMBEDDED OBJECTS
        embeddedObjects EmbeddedObjects = new embeddedObjects();
        int embedObjs = EmbeddedObjects.Check_ODFToolkit(filepath);

        // EXTERNAL OBJECTS
        externalObjects ExternalObjects = new externalObjects();
        int extObjs = ExternalObjects.Check_ODFToolkit(filepath);

        // ACTIVE SHEET
        activeSheet ActiveSheet = new activeSheet();
        boolean activeSheet = ActiveSheet.Check_ODFToolkit(filepath);

        // EMBEDDED FONTS
        embeddedFonts EmbeddedFonts = new embeddedFonts();
        boolean embeddedFonts = EmbeddedFonts.Check_ODFToolkit(filepath);

        // HYPERLINKS
        hyperlinks Hyperlinks = new hyperlinks();
        int hyperlinks = Hyperlinks.Check_ODFToolkit(filepath);

        // METADATA
        metadata Metadata = new metadata();
        boolean metadata = Metadata.Check_ODFToolkit(filepath);

        // LOADREADONLY
        loadReadOnly LoadReadOnly = new loadReadOnly();
        boolean loadReadOnly = LoadReadOnly.Check_ODFToolkit(filepath);

        // MACROS
        macros Macros = new macros();
        int macros = Macros.Check_ODFToolkit(filepath);

        // Add to list and return it
        results.add(new checkList(content, conns, extCellRefs, rtdFunctions, embedObjs, extObjs, printers, hyperlinks, activeSheet, embeddedFonts, loadReadOnly, macros, metadata));
        return results;
    }
}
