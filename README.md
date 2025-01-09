# ODS Archiving

[![build](https://github.com/Asbjoedt/ODS-Archiving/actions/workflows/maven.yml/badge.svg)](https://github.com/Asbjoedt/ODS-Archiving/actions/workflows/maven.yml
)

A Java console application, easy to integrate in workflows to convert any spreadsheet to .ods file format, check if the spreadsheet is compliant with archival requirements, change it to be compliant and validate the OpenDocument Spreadsheets file format standard.

* For information on .ods archival requirements, see **[OPF Spreadsheets Preservation Specification](https://github.com/opf-labs/Spreadsheets-Preservation-Specification/blob/main/v1.0/Specification.md#41-opendocument-spreadsheets)**
* For more information, see repository **[CLISC](https://github.com/Asbjoedt/CLISC)**

## How to use

Start the application in your console with your chosen arguments.

The application requires input filepath or input folder as argument, and then you have to choose one or more optional arguments for processing the filepath.

**Choose input method**

Filepath method
```
--inputfile "<filepath>" (optional, spreadsheet to process)
--rename "<new filename>" (optional, if you want to rename the output file. Extension is not necessary)
```
Folder method
```
--inputfolder "<folder>" (optional, folder to enumerate for input spreadsheets)
--recurse (optional, set if subfolders should be included)
```

**Set output folder**

*You only need to set output folder if you want to convert spreadsheet to .ods file format or change data according to archival requirements.*

```
--outputfolder "<folder>" (optional)
```

**Choose operation methods**

```
--convert (optional, converts spreadsheet to .ods file format using LibreOffice)
--check (optional, checks for archival requirements)
--change (optional, changes data according to archival requirements)
--validate (optional, validates ODF file format standard)
--conformance <option> (required, sets conformance level for archival requirements. Options are "all", 
                        "dna" (conforms to the preservation profile of the Danish National Archives), 
                        "normal", "minimal" and "experimental". The latter performs "all" archival 
                        requirements plus those still under evaluation)
--verbose (optional, outputs detailed results of check, change and validate)
--archivalpackage (optional, creates and saves to an archivable folder)
```
**Examples**

In your terminal change directory to the folder where you have the ```ODS-Archiving.jar``` file.

Filepath usage
```
java -jar ODS-Archiving.jar --convert --check --change --validate --inputfilepath "C:\Spreadsheet.xlsx" --outputfolder "C:\AnyFolder" --conformance must --verbose
```
Folder usage
```
java -jar ODS-Archiving.jar --convert --check --change --validate --inputfolder "C:\FolderOne" --recurse --outputfolder "C:\FolderTwo" --conformance must --verbose --archivalpackage
```

## Conformance

The conformance parameter is a required parameter. It determines the level of requirements to be compliant with. To find information on the associated requirements for each level, read [the OPF Spreadsheets Preservation Specification](https://github.com/opf-labs/Spreadsheets-Preservation-Specification/blob/main/v1.0/Specification.md#41-opendocument-spreadsheets). The levels in the specification are:
* **must**
* **should**
* **may**

A level include the requirements from the previous levels e.g. "should" includes all "must" requirements and "may" includes all "must" and "should" requirements. Some requirements are still under consideration (embedded fonts, active sheet and settingsDOM). These can be enabled in the ODS Archiving application through this all-inclusive level:
* **experimental**

## Dependencies

The application uses the following software.
* [LibreOffice](https://www.libreoffice.org/): LibreOffice is used for background conversion of spreadsheets to .ods file format. You must therefore have the program installed.
* [ODF Toolkit](https://odftoolkit.org/): The ODF Toolkit includes a number of subcomponents with separate copyright notices and license terms. Your use of these subcomponents is subject to the terms and conditions of the licenses listed in their [LICENSE](https://github.com/tdf/odftoolkit/blob/master/LICENSE) file. Copyright ownership information can be found in their [NOTICE](https://github.com/tdf/odftoolkit/blob/master/NOTICE) file.
* [Open PReservation Foundation ODF Validator](https://github.com/openpreserve/odf-validator): OPF ODF Validator is used for validating the OpenDocument Spreadsheets file format Standard.
