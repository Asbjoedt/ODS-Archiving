package archivalRequirements;

import java.io.*;
import java.util.List;
import org.odftoolkit.odfdom.doc.*;
import org.odftoolkit.odfdom.doc.table.OdfTable;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class cellValues {

	// class represents user-defined exception
	class UserDefinedException extends Exception {
		public UserDefinedException(String str) {
			// Calling constructor of parent Exception
			super(str);
		}
	}

	// Check if cell values exist using ODF Toolkit
	public boolean Check_ODFToolkit(String filepath) throws Exception {
		boolean hasCellValue = false;

		OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(filepath);
		List<OdfTable> tables = spreadsheet.getSpreadsheetTables();

		for (OdfTable table : tables) {
			int count = table.getRowCount();
			if (count > 0) {
				hasCellValue = true;
			}
		}

		spreadsheet.close();

		// throw exception if no cell values exist or return
		if (hasCellValue == false) {
			throw new UserDefinedException("Spreadsheet has no cell values");
		}
		return hasCellValue;
	}

	// Check if cell values exist using Apache POI
	public Boolean Check_ApachePOI(String filepath) throws Exception {
		boolean hasCellValue = false;

		// Find spreadsheet and create workbook instance
		FileInputStream fileInput = new FileInputStream(new File(filepath));
		Operations Fetch = new Operations();
		Workbook wb = Fetch.workbookType(filepath, fileInput);

		// Iterate each sheet, row and cell
		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			Sheet sheet = wb.getSheetAt(i);
			for (Row row : sheet) {
				for (Cell cell : row) {
					if (cell.getCellType() != CellType.BLANK) {
						hasCellValue = true;
						wb.close();
						fileInput.close();
						return hasCellValue;
					}
				}
			}
		}
		wb.close();
		fileInput.close();

		// Throw exception if no cell values
		if (hasCellValue == false) {
			throw new UserDefinedException("Spreadsheet has no cell values");
		}
		return hasCellValue;
	}
}
