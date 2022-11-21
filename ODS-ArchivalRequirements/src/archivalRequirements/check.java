package archivalRequirements;

import java.util.*;

public class check {

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
    public List<check> Check(String filepath) throws Exception {
        // Create list to return
        check che = new check();
        List<check> results = new ArrayList<>();

        // CELL VALUES
        cellValues cellValue = new cellValues();
        Boolean hasCellValue = cellValue.Check(filepath);

        // DATA CONNECTIONS
        dataConnections dataConnection = new dataConnections();
        int conns = dataConnection.Check(filepath);

        // EXTERNAL CELL REFERENCES
        externalCellReferences externalCellReference = new externalCellReferences();
        int extCellRefs = externalCellReference.Check(filepath);

        // RTD FUNCTIONS
        RTDFunctions RTDFunction = new RTDFunctions();
        int rtdFunctions = RTDFunction.Check(filepath);

        // PRINTER SETTINGS
        printerSettings printersetting = new printerSettings();
        int printers = printersetting.Check(filepath);

        // EMBEDDED OBJECTS
        embeddedObjects embeddedObject = new embeddedObjects();
        int embedObjs = embeddedObject.Check(filepath);

        // EXTERNAL OBJECTS
        externalObjects externalObject = new externalObjects();
        int extObjs = externalObject.Check(filepath);

        // ABSOLUTE PATH
        absolutePath absPath = new absolutePath();
        boolean hasAbsolutePath = absPath.Check(filepath);

        // Add to list and return it
        results.add(new check(che.cellValuesExist = hasCellValue, cellValuesExist = hasCellValue, conns, extCellRefs, rtdFunctions, printers, embedObjs, extObjs, hasAbsolutePath));
        return results;
    }
}
