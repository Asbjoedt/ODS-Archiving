# ODS-ArchivalRequirements
A Java console application, easy to integrate in workflows to convert spreadsheet to .ods file format, check if the spreadsheet is compliant with archival requirements, change it to be compliant and validate the OpenDocument Spreadsheets file format standard.

* For more information, see repository **[CLISC](https://github.com/Asbjoedt/CLISC)**

## How to use
Start the application in your console with your chosen arguments.

The application requires input filepath or input folder as argument, and then you have to choose one or more optional arguments for processing the filepath.

**Choose input/output method**

Filepath method
```
--inputfilepath "<filepath>" (optional, spreadsheet to process)
--outputfilepath "<filepath>" (optional, if not set, filepath is identical to input filepath)
```
Folder method
```
--inputfolder "<folder>" (optional, folder to enumerate for input spreadsheets)
--outputfolder "<folder>" (optional, if not set, folder is identical to input folder)
--recurse (optional, set if subdirectories should be included)
```

**Choose operation methods**
```
--check (optional, checks for archival requirements)
--change (optional, changes data according to archival requirements)
--convert <extension> (optional, converts spreadsheet to set file format using LibreOffice)
--validate (optional, validates OpenDocument Spreadsheets file format standard)
--delete (optional, if you want to delete the original input spreadsheet)
```
**Examples**

Filepath usage
```
java -jar <PATH>\ODS-ArchivalRequirements.jar --convert ods --check --change --validate --inputfilepath "C:\Spreadsheet.xlsx" --outputfilepath "C:\AnyFolder\ThisIsFun.ods"
```
Or shorter
```
java -jar <PATH>\ODS-ArchivalRequirements.jar -con ods -che -cha -val -inp "C:\Spreadsheet.xlsx" -out "C:\Spreadsheet.ods"
```

## Dependencies
The application uses the following software.
* [Apache POI](https://poi.apache.org/): Apache POI is used for checking and changing Office Open XML file formats before conversion to .ods file format. No installation required.
* [LibreOffice](https://www.libreoffice.org/): LibreOffice is used for background conversion of spreadsheets to .ods file format. You must therefore have the program installed.
* [ODF Toolkit](https://odftoolkit.org/): The ODF Toolkit includes a number of subcomponents with separate copyright notices and license terms. Your use of these subcomponents is subject to the terms and conditions of the licenses listed in their [LICENSE](https://github.com/tdf/odftoolkit/blob/master/LICENSE) file. Copyright ownership information can be found in their [NOTICE](https://github.com/tdf/odftoolkit/blob/master/NOTICE) file.

