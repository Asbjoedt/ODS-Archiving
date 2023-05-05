package ODSArchiving;

public class hyperlinks {

    // Check for hyperlinks using ODF Toolkit
    public int Check_ODFToolkit(String filepath) throws Exception {
        int hyperlinks = 0;


        // Inform user and return number
        if (hyperlinks > 0) {
            System.out.println("CHECK: " + hyperlinks + " hyperlinks detected");
        }
        return hyperlinks;
    }
}
