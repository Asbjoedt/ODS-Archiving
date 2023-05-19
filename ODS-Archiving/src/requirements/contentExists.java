package requirements;

import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.io.InputStream;

public class contentExists {

	// Check if cell values or objects exist using ODF Toolkit
	public boolean Check_ODFToolkit(String input) throws Exception {
		boolean hasContent = false;
		boolean hasCellValues = false;
		boolean hasObjects = false;

		// Perform check
		OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(input);
		Node node = spreadsheet.getMetaDom().getElementsByTagName("meta:document-statistic").item(0);
		if (node != null) {
			NamedNodeMap currentAttributes = node.getAttributes();
			var cellCount = currentAttributes.getNamedItem("meta:cell-count").getNodeValue();
			var objectsCount = currentAttributes.getNamedItem("meta:object-count").getNodeValue();
			if (Integer.parseInt(cellCount) > 0)
				hasCellValues = true;
			if (Integer.parseInt(objectsCount) > 0)
				hasObjects = true;
		}
		spreadsheet.close();

		// Inform user and return boolean
		if (!hasCellValues && !hasObjects)
			System.out.println("CHECK ODS_6: Cell values or objects NOT detected");
		if (hasCellValues || hasObjects)
			hasContent = true;
		return hasContent;
	}
}
