package archivalRequirements;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;

import java.io.*;
import java.util.*;

public class check {

    // Class for creating list of checks
    public class checkList {
        // Class data types
        boolean cellValuesExist = false;
        int dataConnections = 0;
        int externalCellReferences = 0;
        int RTDFunctions = 0;
        int printerSettings = 0;
        int embeddedObjects = 0;
        int externalObjects = 0;
        boolean absolutePath = false;
        boolean activeSheet = false;

        // Method for class data types
        public checkList(boolean cellvalues, int conns, int cellrefs, int rtd, int printers, int embedsobjs, int extobjs, boolean absolutePath, boolean activeSheet) {
            this.cellValuesExist = cellvalues;
            this.dataConnections = conns;
            this.externalCellReferences = cellrefs;
            this.RTDFunctions = rtd;
            this.printerSettings = printers;
            this.embeddedObjects = embedsobjs;
            this.externalObjects = extobjs;
            this.absolutePath = absolutePath;
            this.activeSheet = activeSheet;
        }
    }

    // Perform check of archival requirements on OpenDocument Spreadsheets using ODF Toolkit
    public List<checkList> Check_ODFToolkit(String filepath) throws Exception {
        // Create list to return
        List<checkList> results = new ArrayList<>();

        // CELL VALUES
        cellValues cellValue = new cellValues();
        Boolean hasCellValue = cellValue.Check_ODFToolkit(filepath);

        // DATA CONNECTIONS
        dataConnections dataConnection = new dataConnections();
        int conns = dataConnection.Check_ODFToolkit(filepath);

        // EXTERNAL CELL REFERENCES
        externalCellReferences externalCellReference = new externalCellReferences();
        int extCellRefs = externalCellReference.Check_ODFToolkit(filepath);

        // RTD FUNCTIONS
        RTDFunctions RTDFunction = new RTDFunctions();
        int rtdFunctions = RTDFunction.Check_ODFToolkit(filepath);

        // PRINTER SETTINGS
        printerSettings printersetting = new printerSettings();
        int printers = printersetting.Check_ODFToolkit(filepath);

        // EMBEDDED OBJECTS
        embeddedObjects embeddedObject = new embeddedObjects();
        int embedObjs = embeddedObject.Check_ODFToolkit(filepath);

        // EXTERNAL OBJECTS
        externalObjects externalObject = new externalObjects();
        int extObjs = externalObject.Check_ODFToolkit(filepath);

        // ABSOLUTE PATH
        absolutePath absPath = new absolutePath();
        boolean hasAbsolutePath = absPath.Check_ODFToolkit(filepath);

        // ACTIVE SHEET
        activeSheet activeSheet = new activeSheet();
        boolean hasActivesheet = activeSheet.Check_ODFToolkit(filepath);

        // Add to list and return it
        results.add(new checkList(hasCellValue, conns, extCellRefs, rtdFunctions, printers, embedObjs, extObjs, hasAbsolutePath, hasActivesheet));
        return results;
    }

    // Perform check of archival requirements on OOXML spreadsheets using Apache POI
    public List<checkList> Check_ApachePOI(String filepath) throws IOException, OpenXML4JException {
        // Create list to return
        List<checkList> results = new ArrayList<>();

        // CELL VALUES
        cellValues cellValue = new cellValues();
        Boolean hasCellValue = cellValue.Check_ApachePOI(filepath);

        // DATA CONNECTIONS
        dataConnections dataConnection = new dataConnections();
        int conns = dataConnection.Check_ApachePOI(filepath);

        // EXTERNAL CELL REFERENCES
        externalCellReferences externalCellReference = new externalCellReferences();
        int extCellRefs = externalCellReference.Check_ApachePOI(filepath);

        // RTD FUNCTIONS
        RTDFunctions RTDFunction = new RTDFunctions();
        int rtdFunctions = RTDFunction.Check_ApachePOI(filepath);

        // PRINTER SETTINGS
        printerSettings printersetting = new printerSettings();
        int printers = printersetting.Check_ApachePOI(filepath);

        // EMBEDDED OBJECTS
        embeddedObjects embeddedObject = new embeddedObjects();
        int embedObjs = embeddedObject.Check_ApachePOI(filepath);

        // EXTERNAL OBJECTS
        externalObjects externalObject = new externalObjects();
        int extObjs = externalObject.Check_ApachePOI(filepath);

        // ABSOLUTE PATH
        absolutePath absPath = new absolutePath();
        boolean hasAbsolutePath = absPath.Check_ApachePOI(filepath);

        // ACTIVE SHEET
        activeSheet activeSheet = new activeSheet();
        boolean hasActivesheet = activeSheet.Check_ApachePOI(filepath);

        // Add to list and return it
        results.add(new checkList(hasCellValue, conns, extCellRefs, rtdFunctions, printers, embedObjs, extObjs, hasAbsolutePath, hasActivesheet));

        return results;
    }
}
