package requirements;

import java.util.List;
import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import org.odftoolkit.odfdom.doc.table.OdfTable;
import org.odftoolkit.odfdom.doc.table.OdfTableRow;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class RTDFunctions {

    // Check for RTD functions using ODF Toolkit
    public int Check_ODFToolkit(String filepath, boolean verbose) throws Exception {
        int rtdfunctions = 0;

        // Perform check
        OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(filepath);
        List<OdfTable> tables = spreadsheet.getSpreadsheetTables();
        for (OdfTable table : tables) {
            List<OdfTableRow> rows = table.getRowList();
            for (OdfTableRow row : rows) {
                NodeList cells = row.getOdfElement().getChildNodes();
                for (int i = 0; i < cells.getLength(); i++) {
                    Node cell = cells.item(i);
                    if (cell.hasChildNodes()) {
                        Node childNode = cell.getFirstChild();
                        if (childNode.getTextContent().startsWith(" =RTD") || childNode.getTextContent().startsWith("=RTD")) {
                            rtdfunctions++;
                            if (verbose)
                                System.out.println("CHECK ODS_5 VERBOSE: RealTimeData reference detected. Sheet: " + table.getTableName() + ", Cell: unknown, Reference:" + childNode.getTextContent());
                        }
                    }
                }
            }
        }
        spreadsheet.close();

        // Inform user and return number
        if (rtdfunctions > 0)
            System.out.println("CHECK ODS_5: " + rtdfunctions + " RTD functions detected");
        return rtdfunctions;
    }

    // Remove RTD functions using ODF Toolkit
    public int Change_ODFToolkit(String filepath, boolean verbose) throws Exception {
        int rtdfunctions = 0;

        // Perform change
        OdfSpreadsheetDocument spreadsheet =  OdfSpreadsheetDocument.loadDocument(filepath);
        List<OdfTable> tables = spreadsheet.getSpreadsheetTables();
        for (OdfTable table : tables) {
            List<OdfTableRow> rows = table.getRowList();
            for (OdfTableRow row : rows) {
                NodeList cells = row.getOdfElement().getChildNodes();
                for (int i = 0; i < cells.getLength(); i++) {
                    Node cell = cells.item(i);
                    if (cell.hasChildNodes()) {
                        Node childNode = cell.getFirstChild();
                        if (childNode.getTextContent().startsWith(" =RTD") || childNode.getTextContent().startsWith("=RTD")) {

                            // Do something
                            // rtdfunctions++;
                            //spreadsheet.save(filepath);
                        }
                    }
                }
            }
        }
        spreadsheet.close();

        // Inform user and return number
        if (rtdfunctions > 0)
            System.out.println("CHANGE ODS_5: " + rtdfunctions + " RTD functions removed");
        return rtdfunctions;
    }
}
