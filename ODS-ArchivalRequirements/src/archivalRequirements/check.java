package archivalRequirements;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import java.io.*;
import java.util.*;

public class check {

    // Class for creating list of checks
    public class checkList {
        // Class data types
        boolean content = false;
        int dataConnections = 0;
        int externalCellReferences = 0;
        int RTDFunctions = 0;
        int printerSettings = 0;
        int embeddedObjects = 0;
        int externalObjects = 0;
        boolean absolutePath = false;
        boolean activeSheet = false;
        boolean embedFonts = false;

        // Method for class data types
        public checkList(boolean hasContent, int conns, int cellrefs, int rtd, int printers, int embedobjs, int extobjs, boolean absolutePath, boolean activeSheet, boolean embedFonts) {
            this.content = hasContent;
            this.dataConnections = conns;
            this.externalCellReferences = cellrefs;
            this.RTDFunctions = rtd;
            this.printerSettings = printers;
            this.embeddedObjects = embedobjs;
            this.externalObjects = extobjs;
            this.absolutePath = absolutePath;
            this.activeSheet = activeSheet;
            this.embedFonts = embedFonts;
        }
    }

    // Perform check of archival requirements on OpenDocument Spreadsheets using ODF Toolkit
    public List<checkList> Check_ODFToolkit(String filepath) throws Exception {
        // Create list to return
        List<checkList> results = new ArrayList<>();

        // CONTENT
        content Content = new content();
        boolean content = Content.Check_ODFToolkit(filepath);

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

        // EMBEDDED FONTS
        embedFonts embedFonts = new embedFonts();
        boolean hasEmbedFonts = embedFonts.Check_ODFToolkit(filepath);

        // Add to list and return it
        results.add(new checkList(content, conns, extCellRefs, rtdFunctions, printers, embedObjs, extObjs, hasAbsolutePath, hasActivesheet, hasEmbedFonts));
        return results;
    }

    // Perform check of archival requirements on OOXML spreadsheets using Apache POI
    public List<checkList> Check_ApachePOI(String filepath) throws IOException, OpenXML4JException {
        // Create list to return
        List<checkList> results = new ArrayList<>();

        // CELL VALUES
        content cellValue = new content();
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

        // EMBEDDED FONTS - NOT RELEVANT FOR OOXML - CAN IGNORE
        boolean hasEmbedFonts = false;

        // Add to list and return it
        results.add(new checkList(hasCellValue, conns, extCellRefs, rtdFunctions, printers, embedObjs, extObjs, hasAbsolutePath, hasActivesheet, hasEmbedFonts));

        return results;
    }
}
