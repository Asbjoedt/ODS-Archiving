package archivalRequirements;

import org.apache.poi.ss.usermodel.*;

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
    public int Check_ApachePOI(String filepath) {
        int extCellRefs = 0;

        FileInputStream file = new FileInputStream(new File(filepath));
        Workbook wb = new XSSFWorkbook(file);
        Sheet sheet1 = wb.getSheetAt(0);
        for (Row row : sheet1) {
            for (Cell cell : row) {
                if (cell.getCellType() == CellType.FORMULA) {
                    if (cell.getCellFormula().lastIndexOf(1) == "'") {
                        extCellRefs++;
                    }
                }
            }
        }
        return extCellRefs;
    }

    // Remove external cell references using Apache POI
    public int Change_ApachePOI(String filepath) {
        int extCellRefs = 0;

        return extCellRefs;
    }
}
