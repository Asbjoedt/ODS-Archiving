package archivalRequirements;

import java.util.*;

public class change {

    // Class data types
    boolean cellValuesExist = false;
    int dataConnections = 0;
    int externalCellReferences = 0;
    int RTDFunctions = 0;
    int printerSettings = 0;
    int embeddedObjects = 0;
    int externalObjects = 0;
    boolean absolutePath = false;

    // Perform check of archival requirements
    public List<change> Change(String filepath) {
        // Create list to return
        List<change> results = new ArrayList<>();

        // DATA CONNECTIONS
        dataConnections dataConnection = new dataConnections();
        int conns = dataConnection.Change(filepath);

        // EXTERNAL CELL REFERENCES
        externalCellReferences externalCellReference = new externalCellReferences();
        int extCellRefs = externalCellReference.Change(filepath);

        // RTD FUNCTIONS
        RTDFunctions RTDFunction = new RTDFunctions();
        int rtdFunctions = RTDFunction.Change(filepath);

        // PRINTER SETTINGS
        printerSettings printersetting = new printerSettings();
        int printers = printersetting.Change(filepath);

        // EMBEDDED OBJECTS
        embeddedObjects embeddedObject = new embeddedObjects();
        int embedObj = embeddedObject.Change(filepath);

        // EXTERNAL OBJECTS
        externalObjects externalObject = new externalObjects();
        int extObj = externalObject.Change(filepath);

        // ABSOLUTE PATH
        absolutePath absPath = new absolutePath();
        boolean hasAbsolutePath = absPath.Change(filepath);

        // Add to list and return it
        results.add(new change(cellValuesExist = hasCellValue, conns, extCellRefs, rtdFunctions, printers, embedObj, extObj, hasAbsolutePath));
        return results;
    }
}
