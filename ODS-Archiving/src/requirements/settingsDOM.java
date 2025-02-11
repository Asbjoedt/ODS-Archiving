package requirements;

import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import org.odftoolkit.odfdom.dom.OdfSettingsDom;

public class settingsDOM {

    // Check if settings.xml exists using ODF Toolkit
    public boolean Check_ODFToolkit(String filepath) throws Exception {
        boolean settingsDOM = false;

        // Perform check
        OdfSpreadsheetDocument spreadsheet = OdfSpreadsheetDocument.loadDocument(filepath);
        OdfSettingsDom settingsDom = spreadsheet.getSettingsDom();
        if (settingsDom != null)
            settingsDOM = true;
        spreadsheet.close();

        if (settingsDOM) {
            System.out.println("CHECK ODS_EXPERIMENTAL: Package file settings.xml detected");
        }
        return settingsDOM;
    }

    // Change if settings.xml exists using ODF Toolkit
    public boolean Change_ODFToolkit(String filepath) throws Exception {
        boolean settingsDOM = false;

        // Perform change
        OdfSpreadsheetDocument spreadsheet = OdfSpreadsheetDocument.loadDocument(filepath);
        OdfSettingsDom settingsDom = spreadsheet.getSettingsDom();
        if (settingsDom != null) {
            settingsDOM = true;
            spreadsheet.removeDocument(settingsDom.getPackagePath());
            spreadsheet.save(filepath);
        }
        spreadsheet.close();

        if (settingsDOM) {
            System.out.println("CHANGE ODS_EXPERIMENTAL: Package file settings.xml was removed");
        }
        return settingsDOM;
    }
}
