package archivalRequirements;

public class printerSettings {

    // Check for printer settings using ODF Toolkit
    public int Check_ODFToolkit(String filepath) {
        int printers = 0;



        // Inform user and return number
        if (printers > 0) {
            System.out.println("CHECK: " + printers + " detected");
        }
        return printers;
    }

    // Remove printer settings using ODF Toolkit
    public int Change_ODFToolkit(String filepath) {
        int printers = 0;



        // Inform user and return number
        if (printers > 0) {
            System.out.println("CHANGE: " + printers + " removed");
        }
        return printers;
    }

    // Check for printer settings using Apache POI
    public int Check_ApachePOI(String filepath) {
        int printers = 0;



        // Inform user and return number
        if (printers > 0) {
            System.out.println("CHECK: " + printers + " detected");
        }
        return printers;
    }

    // Remove printer settings using Apache POI
    public int Change_ApachePOI(String filepath) {
        int printers = 0;



        // Inform user and return number
        if (printers > 0) {
            System.out.println("CHANGE: " + printers + " removed");
        }
        return printers;
    }
}
