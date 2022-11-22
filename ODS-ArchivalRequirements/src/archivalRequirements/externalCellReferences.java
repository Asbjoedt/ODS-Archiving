package archivalRequirements;

import java.io.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class externalCellReferences {

    // Check for external cell references using ODF Toolkit
    public int Check_ODFToolkit(String filepath) {
        int extCellRefs = 0;

        return extCellRefs;
    }

    // Remove external cell references using ODF Toolkit
    public int Change_ODFToolkit(String filepath) {
        int extCellRefs = 0;

        return extCellRefs;
    }

    // Check for external cell references using Apache POI
    public int Check_ApachePOI(String filepath) throws IOException {
        int extCellRefs = 0;

        FileInputStream file = new FileInputStream(new File(filepath));
        Workbook wb = new XSSFWorkbook(file);

        // Iterate each sheet, row and cell
        for (int i = 0; i < wb.getNumberOfSheets(); i++) {
            Sheet sheet = wb.getSheetAt(i);
            for (Row row : sheet) {
                for (Cell cell : row) {
                    if (cell.getCellType() == CellType.FORMULA) {
                        if (cell.getCellFormula().substring(0, 2) == "='") {
                            extCellRefs++;
                        }
                    }
                }
            }
        }
        // Inform user and return number
        if (extCellRefs > 0) {
            System.out.println(extCellRefs + " external cell references detected");
        }
        return extCellRefs;
    }

    // Remove external cell references using Apache POI
    public int Change_ApachePOI(String filepath) {
        int extCellRefs = 0;

        return extCellRefs;
    }
}
