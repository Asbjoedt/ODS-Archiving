# ODS-ArchivalRequirements
A Java console application, easy to integrate in workflows to convert spreadsheet to .ods file format, check if the spreadsheet is compliant with archival requirements, change it to be compliant and validate the OpenDocument Spreadsheets file format standard.

* For more information, see repository **[CLISC](https://github.com/Asbjoedt/CLISC)**

## Methods
The application requires input and output filepaths as arguments and you then have to choose one or more optional arguments for processing the filepaths.
```
--inputfilepath (required, spreadsheet to perform operations on)
--outputfilepath (optional, if not set, filepath is identical to input filepath)
--check (optional, checks for archival requirements)
--change (optional, changes data according to archival requirements)
--convert (optional, converts spreadsheet to .ods)
--validate (optional, validates OpenDocument Spreadsheets file format standard)
```

Example of usage:

```
--check --change --convert --validate --inputfilepath "C:\Users\%USERNAME%\Desktop\TestSpreadsheet.xlsx" --outputfilepath "C:\Users\%USERNAME%\Desktop\TestSpreadsheet.ods"
```
Or shorter:
```
-che -cha -con -val -inp "C:\TestSpreadsheet.xlsx" -out "C:\TestSpreadsheet.ods"
```

## Dependencies
* [Apache POI](https://poi.apache.org/): Apache POI is used for checking and changing Office Open XML file formats before conversion to .ods file format
* [LibreOffice](https://www.libreoffice.org/): LibreOffice is used for background conversion of spreadsheets to .ods file format. You must therefore have the program installed.
* [ODF Toolkit](https://odftoolkit.org/): The ODF Toolkit includes a number of subcomponents with separate copyright notices and license terms. Your use of these subcomponents is subject to the terms and conditions of the licenses listed in their [LICENSE](https://github.com/tdf/odftoolkit/blob/master/LICENSE) file. Copyright ownership information can be found in their [NOTICE](https://github.com/tdf/odftoolkit/blob/master/NOTICE) file.

