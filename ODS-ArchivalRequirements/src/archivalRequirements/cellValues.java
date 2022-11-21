package archivalRequirements;

import java.net.URI;
import org.odftoolkit.odfdom.*;

public class cellValues {

	// class represents user-defined exception
	class UserDefinedException extends Exception
	{
		public UserDefinedException(String str)
		{
			// Calling constructor of parent Exception
			super(str);
		}
	}

	public Boolean check(String filepath) throws Exception {
		
		Boolean hasCellValue = false;

		var spreadsheet = org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument.loadDocument(filepath);
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
}
