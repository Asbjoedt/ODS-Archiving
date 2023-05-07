package general;

import requirements.*;
import java.util.*;

public class change {

    // Class for creating list of changes
    public class changeList {
        // Class data types
        int externalCellReferences = 0;
        int RTDFunctions = 0;
        int embeddedObjects = 0;
        int externalObjects = 0;
        int macros = 0;
        boolean loadReadOnly = false;
        int printerSettings = 0;
        boolean metadata = false;
        int hyperlinks = 0;
        boolean embeddedFonts = false;
        boolean activeSheet = false;

        // Method for class data types
        public changeList(int cellrefs, int rtd, int embedsobjs, int extobjs, int macros, boolean loadReadOnly, int printers, boolean metadata, int hyperlinks, boolean embeddedFonts, boolean activeSheet) {
            this.externalCellReferences = cellrefs;
            this.RTDFunctions = rtd;
            this.embeddedObjects = embedsobjs;
            this.externalObjects = extobjs;
            this.macros = macros;
            this.loadReadOnly = loadReadOnly;
            this.printerSettings = printers;
            this.metadata = metadata;
            this.hyperlinks = hyperlinks;
            this.loadReadOnly = loadReadOnly;
            this.embeddedFonts = embeddedFonts;
            this.activeSheet = activeSheet;
        }
    }

    // Perform check of archival requirements on OpenDocument Spreadsheets using ODF Toolkit
    public List<changeList> Change_ODFToolkit(String filepath, String compliance) throws Exception {
        // Create list to return
        List<changeList> results = new ArrayList<>();
        int extCellRefs = 0;
        int rtdFunctions = 0;
        int embedObjs = 0;
        int extObjs = 0;
        int macros = 0;
        boolean loadReadOnly = false;
        int printers = 0;
        boolean metadata = false;
        int hyperlinks = 0;
        boolean embeddedFonts = false;
        boolean activeSheet = false;

        // Perform checks based on compliance
        if (compliance.equals("must") || compliance.equals("should") || compliance.equals("may") || compliance.equals("test")) {
            // EXTERNAL CELL REFERENCES
            externalCellReferences ExternalCellReference = new externalCellReferences();
            extCellRefs = ExternalCellReference.Change_ODFToolkit(filepath);

            // RTD FUNCTIONS
            RTDFunctions RTDFunctions = new RTDFunctions();
            rtdFunctions = RTDFunctions.Change_ODFToolkit(filepath);

            // EMBEDDED OBJECTS
            embeddedObjects EmbeddedObjects = new embeddedObjects();
            embedObjs = EmbeddedObjects.Change_ODFToolkit(filepath);

            // EXTERNAL OBJECTS
            externalObjects ExternalObjects = new externalObjects();
            extObjs = ExternalObjects.Change_ODFToolkit(filepath);
        }
        if (compliance.equals("should") || compliance.equals("may") || compliance.equals("test")) {
            // MACROS
            macros Macros = new macros();
            macros = Macros.Change_ODFToolkit(filepath);

            // LOADREADONLY
            loadReadOnly LoadReadOnly = new loadReadOnly();
            loadReadOnly = LoadReadOnly.Change_ODFToolkit(filepath);
        }
        if (compliance.equals("may") || compliance.equals("test")) {
            // PRINTER SETTINGS
            printerSettings PrinterSettings = new printerSettings();
            printers = PrinterSettings.Change_ODFToolkit(filepath);

            // METADATA
            metadata Metadata = new metadata();
            metadata = Metadata.Change_ODFToolkit(filepath);

            // HYPERLINKS
            hyperlinks Hyperlinks = new hyperlinks();
            hyperlinks = Hyperlinks.Change_ODFToolkit(filepath);
        }
        if (compliance.equals("test")) {
            // EMBEDDED FONTS
            embeddedFonts EmbeddedFonts = new embeddedFonts();
            embeddedFonts = EmbeddedFonts.Change_ODFToolkit(filepath);

            // ACTIVE SHEET
            activeSheet ActiveSheet = new activeSheet();
            activeSheet = ActiveSheet.Change_ODFToolkit(filepath);
        }

        // Add to list and return it
        results.add(new changeList(extCellRefs, rtdFunctions, embedObjs, extObjs, macros, loadReadOnly, printers, metadata, hyperlinks, embeddedFonts, activeSheet));
        return results;
    }
}
