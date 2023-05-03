# ODS-ArchivalRequirements

A Java console application, easy to integrate in workflows to convert spreadsheet to .ods file format, check if the spreadsheet is compliant with archival requirements, change it to be compliant and validate the OpenDocument Spreadsheets file format standard.

* For information on .ods archival requirements, see **[OPF Spreadsheets Preservation Specification](https://github.com/opf-labs/Spreadsheets-Preservation-Specification/blob/main/v1.0/Specification.md#41-opendocument-spreadsheets)**
* For more information, see repository **[CLISC](https://github.com/Asbjoedt/CLISC)**

## How to use

Start the application in your console with your chosen arguments.

The application requires input filepath or input folder as argument, and then you have to choose one or more optional arguments for processing the filepath.

**Choose input method**

Filepath method
```
--inputfilepath "<filepath>" (optional, spreadsheet to process)
```
Folder method
```
--inputfolder "<folder>" (optional, folder to enumerate for input spreadsheets)
--recurse (optional, set if subfolders should be included)
```

**Set output folder**

```
--outputfolder "<folder>" (optional, if not set, folder is identical to input folder)
```

**Choose operation methods**

```
--convert (optional, converts spreadsheet to .ods file format using LibreOffice)
--check (optional, checks for archival requirements)
--change (optional, changes data according to archival requirements)
--validate (optional, validates OpenDocument Spreadsheets file format standard)
```
**Examples**

Filepath usage
```
java -jar <PATH>\ODS-ArchivalRequirements.jar --convert --check --change --validate --inputfilepath "C:\Spreadsheet.xlsx" --outputfolder "C:\AnyFolder"
```
Or shorter
```
java -jar <PATH>\ODS-ArchivalRequirements.jar -con -che -cha -val -inp "C:\Spreadsheet.xlsx" -out "C:\AnyFolder"
```
Folder usage
```
java -jar <PATH>\ODS-ArchivalRequirements.jar --convert --check --change --validate --inputfolder "C:\FolderOne" --recurse --outputfolder "C:\FolderTwo"
```
Or shorter
```
java -jar <PATH>\ODS-ArchivalRequirements.jar -con -che -cha -val -inf "C:\FolderOne" -rec -out "C:\FolderTwo"
```

## Dependencies

The application uses the following software.
* [LibreOffice](https://www.libreoffice.org/): LibreOffice is used for background conversion of spreadsheets to .ods file format. You must therefore have the program installed.
* [ODF Toolkit](https://odftoolkit.org/): The ODF Toolkit includes a number of subcomponents with separate copyright notices and license terms. Your use of these subcomponents is subject to the terms and conditions of the licenses listed in their [LICENSE](https://github.com/tdf/odftoolkit/blob/master/LICENSE) file. Copyright ownership information can be found in their [NOTICE](https://github.com/tdf/odftoolkit/blob/master/NOTICE) file.
* [OPF ODF Validator](https://github.com/opf-labs/odf-validator): Open Preservation Foundation's ODF Validator is used for validating OpenDocument Spreadsheet files. No installation required.