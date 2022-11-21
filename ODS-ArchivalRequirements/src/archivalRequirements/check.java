package archivalRequirements;

public class check {
    public void Check(String filepath) throws Exception {
        // CELL VALUES
        cellValues cellValue = new cellValues();
        Boolean hasCellValue = cellValue.Check(filepath);

        // DATA CONNECTIONS
        dataConnections dataConnection = new dataConnections();
        int conns = dataConnection.Check(filepath);

        // EXTERNAL CELL REFERENCES

        // RTD FUNCTIONS

        // PRINTER SETTINGS

        // EMBEDDED OBJECTS

        // EXTERNAL OBJECTS

        // ABSOLUTE PATH
    }
}
