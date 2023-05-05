package ODSArchiving;

public class macros {

    // Check for macros using ODF Toolkit
    public int Check_ODFToolkit(String filepath) throws Exception {
        int macros = 0;


        // Inform user and return number
        if (macros > 0) {
            System.out.println("CHECK: " + macros + " macros detected");
        }
        return macros;
    }
}
