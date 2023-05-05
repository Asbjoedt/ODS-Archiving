package ODSArchiving;

import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import org.odftoolkit.odfdom.dom.OdfContentDom;

public class dataConnections {

	// Check for data connections using ODF Toolkit
	public int Check_ODFToolkit(String filepath) throws Exception {
		int dataConnections = 0;

		// Perform check
		OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(filepath);
		OdfContentDom content = spreadsheet.getContentDom();


		spreadsheet.close();

		// Inform user and return number
		if (dataConnections > 0) {
			System.out.println("CHECK: " + dataConnections + " data connections detected");
		}
		return dataConnections;
	}

	// Remove data connections using ODF Toolkit
	public int Change_ODFToolkit(String filepath) {
		int dataConnections = 0;



		// Inform user and return number
		if (dataConnections > 0) {
			System.out.println("CHANGE: " + dataConnections + " data connections removed");
		}
		return dataConnections;
	}
}
