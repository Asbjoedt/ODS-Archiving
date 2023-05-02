package archivalRequirements;

import java.io.*;
import org.odftoolkit.odfdom.doc.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.odftoolkit.odfdom.dom.OdfContentDom;
import org.odftoolkit.odfdom.dom.OdfMetaDom;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class content {

	// Check if cell values or objects exist using ODF Toolkit
	public boolean Check_ODFToolkit(String filepath) throws Exception {
		boolean hasContent = false;
		boolean hasCellValues = false;
		boolean hasObjects = false;

		// Perform check
		OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(filepath);
		Node node = spreadsheet.getMetaDom().getElementsByTagName("meta:document-statistic").item(0);
		NamedNodeMap currentAttributes = node.getAttributes();
		var cellCount = currentAttributes.getNamedItem("meta:cell-count").getNodeValue();
		var objectsCount = currentAttributes.getNamedItem("meta:object-count").getNodeValue();
		if (Integer.parseInt(cellCount) > 0) {
			hasCellValues = true;
		}
		if (Integer.parseInt(objectsCount) > 0) {
			hasObjects = true;
		}
		spreadsheet.close();

		// Inform user and return boolean
		if (!hasCellValues && !hasObjects) {
			System.out.println("CHECK: Cell values or objects NOT detected");
		}
		if (hasCellValues || hasObjects) {
			hasContent = true;
		}
		return hasContent;
	}

	// Check if cell values or objects exist using Apache POI
	public boolean Check_ApachePOI(String filepath) throws IOException {
		boolean hasCellValue = false;

		// Find spreadsheet and create workbook instance
		FileInputStream fileInput = new FileInputStream(filepath);
		XSSFWorkbook workbook = new XSSFWorkbook(fileInput);

		// Iterate each sheet, row and cell
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			Sheet sheet = workbook.getSheetAt(i);
			for (Row row : sheet) {
				for (Cell cell : row) {
					if (cell.getCellType() != CellType.BLANK) {
						hasCellValue = true;
						workbook.close();
						fileInput.close();
						return hasCellValue;
					}
				}
			}
		}
		workbook.close();
		fileInput.close();

		return hasCellValue;
	}
}
