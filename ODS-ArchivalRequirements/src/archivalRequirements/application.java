package archivalRequirements;

public class application {
	
	public static void main(String[] args) {
		
		String function = args[0];
		String filepath = args[1];
		System.out.println(function + " filepath " + filepath);
		
		// CELL VALUES
		cellValues cellValue = new cellValues();
		Boolean hasCellValue = cellValue.check(filepath);
		if (hasCellValue == false) {

		}
		
		// DATA CONNECTIONS
		dataConnections dataConnection = new dataConnections();
		int conns = dataConnection.check(filepath);
		if (function == "CheckChange" && conns > 0) {
			dataConnection.change(filepath);
		}
	}
}
