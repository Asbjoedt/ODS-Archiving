package archivalRequirements;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;

import java.io.IOException;
import java.util.*;

public class change {

    // Class for creating list of changes
    public class changeList {

        // Class data types
        int dataConnections = 0;
        int externalCellReferences = 0;
        int RTDFunctions = 0;
        int printerSettings = 0;
        int embeddedObjects = 0;
        int externalObjects = 0;
        boolean absolutePath = false;
        boolean activeSheet = false;

        // Method for class data types
        public changeList(int conns, int cellrefs, int rtd, int printers, int embedsobjs, int extobjs, boolean abspath, boolean activeSheet) {
            this.dataConnections = conns;
            this.externalCellReferences = cellrefs;
            this.RTDFunctions = rtd;
            this.printerSettings = printers;
            this.embeddedObjects = embedsobjs;
            this.externalObjects = extobjs;
            this.absolutePath = abspath;
            this.activeSheet = activeSheet;
        }
    }

    // Perform check of archival requirements on OpenDocument Spreadsheets using ODF Toolkit
    public List<changeList> Change_ODFToolkit(String filepath) throws Exception {
        // Create list to return
        List<changeList> results = new ArrayList<>();

        // DATA CONNECTIONS
        dataConnections dataConnection = new dataConnections();
        int conns = dataConnection.Change_ODFToolkit(filepath);

        // EXTERNAL CELL REFERENCES
        externalCellReferences externalCellReference = new externalCellReferences();
        int extCellRefs = externalCellReference.Change_ODFToolkit(filepath);

        // RTD FUNCTIONS
        RTDFunctions RTDFunction = new RTDFunctions();
        int rtdFunctions = RTDFunction.Change_ODFToolkit(filepath);

        // PRINTER SETTINGS
        printerSettings printersetting = new printerSettings();
        int printers = printersetting.Change_ODFToolkit(filepath);

        // EMBEDDED OBJECTS
        embeddedObjects embeddedObject = new embeddedObjects();
        int embedObjs = embeddedObject.Change_ODFToolkit(filepath);

        // EXTERNAL OBJECTS
        externalObjects externalObject = new externalObjects();
        int extObjs = externalObject.Change_ODFToolkit(filepath);

        // ABSOLUTE PATH
        absolutePath absPath = new absolutePath();
        boolean hasAbsolutePath = absPath.Change_ODFToolkit(filepath);

        // ACTIVE SHEET
        activeSheet activeSheet = new activeSheet();
        boolean hasActivesheet = activeSheet.Change_ODFToolkit(filepath);

        // Add to list and return it
        results.add(new changeList(conns, extCellRefs, rtdFunctions, printers, embedObjs, extObjs, hasAbsolutePath, hasActivesheet));
        return results;
    }

    // Perform change of archival requirements on OOXML spreadsheets using Apache POI
    public List<changeList> Change_ApachePOI(String filepath) throws IOException, OpenXML4JException {
        // Create list to return
        List<changeList> results = new ArrayList<>();

        // DATA CONNECTIONS
        dataConnections dataConnection = new dataConnections();
        int conns = dataConnection.Change_ApachePOI(filepath);

        // EXTERNAL CELL REFERENCES
        externalCellReferences externalCellReference = new externalCellReferences();
        int extCellRefs = externalCellReference.Change_ApachePOI(filepath);

        // RTD FUNCTIONS
        RTDFunctions RTDFunction = new RTDFunctions();
        int rtdFunctions = RTDFunction.Change_ApachePOI(filepath);

        // PRINTER SETTINGS
        printerSettings printersetting = new printerSettings();
        int printers = printersetting.Change_ApachePOI(filepath);

        // EMBEDDED OBJECTS
        embeddedObjects embeddedObject = new embeddedObjects();
        int embedObjs = embeddedObject.Change_ApachePOI(filepath);

        // EXTERNAL OBJECTS
        externalObjects externalObject = new externalObjects();
        int extObjs = externalObject.Change_ApachePOI(filepath);

        // ABSOLUTE PATH
        absolutePath absPath = new absolutePath();
        boolean hasAbsolutePath = absPath.Change_ApachePOI(filepath);

        // ACTIVE SHEET
        activeSheet activeSheet = new activeSheet();
        boolean hasActivesheet = activeSheet.Change_ApachePOI(filepath);

        // Add to list and return it
        results.add(new changeList(conns, extCellRefs, rtdFunctions, printers, embedObjs, extObjs, hasAbsolutePath, hasActivesheet));
        return results;
    }
}
