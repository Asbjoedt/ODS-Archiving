package archivalRequirements;

import java.net.URI;
import org.odftoolkit.odfdom.*;

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

		OdfSpreadsheetDocument spreadsheet = (OdfSpreadsheetDocument) org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument.loadDocument(filepath);
		var root = spreadsheet.getContentRoot();

		hasCellValue = spreadsheet.getSpreadsheetTables().isEmpty();
		if (hasCellValue == true) {
			hasCellValue = true;
			return hasCellValue;
		}
		if (hasCellValue == false) {
			System.out.println("--> Spreadsheet has no cell values");
		}

		if (hasCellValue == false) {
			throw new UserDefinedException("Spreadsheet has no cell values");
		}
		return hasCellValue;
	}

	// Check if cell values exist using Apache POI
	public Boolean Check_ApachePOI(String filepath) throws Exception {
		boolean hasCellValue = false;

		return hasCellValue;
	}
}
