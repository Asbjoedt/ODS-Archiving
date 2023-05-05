package ODSArchiving;

import java.util.*;

public class change {

    // Class for creating list of changes
    public class changeList {

        // Class data types
        int dataConnections = 0;
        int externalCellReferences = 0;
        int RTDFunctions = 0;
        int embeddedObjects = 0;
        int externalObjects = 0;
        int printerSettings = 0;
        boolean activeSheet = false;
        boolean metadata = false;
        boolean loadReadOnly = false;

        // Method for class data types
        public changeList(int conns, int cellrefs, int rtd, int embedsobjs, int extobjs,  int printers, boolean activeSheet, boolean metadata, boolean loadReadOnly) {
            this.dataConnections = conns;
            this.externalCellReferences = cellrefs;
            this.RTDFunctions = rtd;
            this.embeddedObjects = embedsobjs;
            this.externalObjects = extobjs;
            this.printerSettings = printers;
            this.activeSheet = activeSheet;
            this.metadata = metadata;
            this.loadReadOnly = loadReadOnly;
        }
    }

    // Perform check of archival requirements on OpenDocument Spreadsheets using ODF Toolkit
    public List<changeList> Change_ODFToolkit(String filepath) throws Exception {
        // Create list to return
        List<changeList> results = new ArrayList<>();

        // DATA CONNECTIONS
        dataConnections DataConnections = new dataConnections();
        int conns = DataConnections.Change_ODFToolkit(filepath);

        // EXTERNAL CELL REFERENCES
        externalCellReferences ExternalCellReferences = new externalCellReferences();
        int extCellRefs = ExternalCellReferences.Change_ODFToolkit(filepath);

        // RTD FUNCTIONS
        RTDFunctions RTDFunctions = new RTDFunctions();
        int rtdFunctions = RTDFunctions.Change_ODFToolkit(filepath);

        // EMBEDDED OBJECTS
        embeddedObjects EmbeddedObjects = new embeddedObjects();
        int embedObjs = EmbeddedObjects.Change_ODFToolkit(filepath);

        // EXTERNAL OBJECTS
        externalObjects ExternalObjects = new externalObjects();
        int extObjs = ExternalObjects.Change_ODFToolkit(filepath);

        // PRINTER SETTINGS
        printerSettings PrinterSettings = new printerSettings();
        int printers = PrinterSettings.Change_ODFToolkit(filepath);

        // ACTIVE SHEET
        activeSheet ActiveSheet = new activeSheet();
        boolean hasActivesheet = ActiveSheet.Change_ODFToolkit(filepath);

        // METADATA
        metadata Metadata = new metadata();
        boolean metadata = Metadata.Change_ODFToolkit(filepath);

        // LOADREADONLY
        loadReadOnly LoadReadOnly = new loadReadOnly();
        boolean loadReadOnly = LoadReadOnly.Change_ODFToolkit(filepath);

        // Add to list and return it
        results.add(new changeList(conns, extCellRefs, rtdFunctions, embedObjs, extObjs, printers, hasActivesheet, metadata, loadReadOnly));
        return results;
    }
}
