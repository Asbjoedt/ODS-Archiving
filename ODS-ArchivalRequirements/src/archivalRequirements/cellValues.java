package archivalRequirements;

import java.io.File;
import java.io.FileInputStream;
import org.odftoolkit.odfdom.doc.*;
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

		try
		{
			FileInputStream file = new FileInputStream(new File(filepath));

			//Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			//Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			//Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext())
			{
				Row row = rowIterator.next();
				//For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();

				while (cellIterator.hasNext())
				{
					Cell cell = cellIterator.next();
					//Check the cell type and format accordingly
					switch (cell.getCellType())
					{
						case Cell.CELL_TYPE_NUMERIC:
							System.out.print(cell.getNumericCellValue() + "t");
							break;
						case Cell.CELL_TYPE_STRING:
							System.out.print(cell.getStringCellValue() + "t");
							break;
					}
				}
				System.out.println("");
			}
			file.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return hasCellValue;
	}
}
