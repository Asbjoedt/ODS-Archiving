package archivalRequirements;

import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;

public class externalObjects {

    // Check for external object references using ODF Toolkit
    public int Check_ODFToolkit(String filepath) {
        int extObjs = 0;

        return extObjs;
    }

    // Remove external object references using ODF Toolkit
    public int Change_ODFToolkit(String filepath) {
        int extObjs = 0;

        return extObjs;
    }

    // Check for external object references using Apache POI
    public int Check_ApachePOI(String filepath) throws IOException {
        int extObjs = 0;

        FileInputStream fileInput = new FileInputStream(filepath);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInput);



        // Save and close file
        workbook.close();
        fileInput.close();

        // Inform user and return number
        if (extObjs > 0) {
            System.out.println(extObjs + " external objects detected");
        }
        return extObjs;
    }

    // Remove external object references using Apache POI
    public int Change_ApachePOI(String filepath) throws IOException {
        int extObjs = 0;

        FileInputStream fileInput = new FileInputStream(filepath);
        Workbook workbook = new XSSFWorkbook(fileInput);



        // Save and close file
        FileOutputStream fileOutput = new FileOutputStream(filepath);
        workbook.write(fileOutput);
        workbook.close();
        fileOutput.close();
        fileInput.close();

        // Inform user and return number
        if (extObjs > 0) {
            System.out.println(extObjs + " external objects detected");
        }
        return extObjs;
    }
}
