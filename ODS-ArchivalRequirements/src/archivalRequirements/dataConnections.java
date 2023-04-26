package archivalRequirements;

import java.io.*;
import java.util.Iterator;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.odftoolkit.odfdom.*;
import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;

public class dataConnections {

	// Check for data connections using ODF Toolkit
	public int Check_ODFToolkit(String filepath) throws Exception {
		int dataConnections = 0;
		

		return dataConnections;
	}

	// Remove data connections using ODF Toolkit
	public int Change_ODFToolkit(String filepath) {
		int dataConnections = 0;


		return dataConnections;
	}

	// Check for data connections using Apache POI
	public int Check_ApachePOI(String filepath) throws IOException {
		int dataConnections = 0;

		FileInputStream fileInput = new FileInputStream(filepath);
		XSSFWorkbook workbook = new XSSFWorkbook(fileInput);

		Iterator<Sheet> iterate = workbook.sheetIterator();
		for (Iterator<Sheet> it = iterate; it.hasNext(); ) {
			Sheet sheet = it.next();


		}

		workbook.close();
		fileInput.close();

		System.out.println("Checking data connections is not supported when using Excel file formats");

		// Inform user and return number
		if (dataConnections > 0) {
			System.out.println("CHECK: " + dataConnections + " data connections detected");
		}
		return dataConnections;
	}

	// Remove data connections using Apache POI
	public int Change_ApachePOI(String filepath) throws IOException {
		int dataConnections = 0;

		System.out.println("Removing data connections is not supported when using Excel file formats");

		// Inform user and return number
		if (dataConnections > 0) {
			System.out.println("CHANGE: " + dataConnections + " data connections removed");
		}
		return dataConnections;
	}
}
