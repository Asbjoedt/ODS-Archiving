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
```
Folder method
```
--inputfolder "<folder>" (optional, folder to enumerate for input spreadsheets)
--recurse (optional, set if subfolders should be included)
```
> **Note:** Do not leave a trailing "\\" on Windows or "/" on Linux in folder paths. If you do, you will get the error: "Missing required option: cof".

**Set output folder**

*You only need to set output folder if you want to convert spreadsheet to .ods file format or change data according to archival requirements.*

```
--outputfolder "<folder>" (optional)
```
> **Note:** Do not leave a trailing "\\" on Windows or "/" on Linux in folder paths. If you do, you will get the error: "Missing required option: cof".

**Choose operation methods**

```
--convert (optional, converts spreadsheet to .ods file format using LibreOffice)
--check (optional, checks for archival requirements)
--change (optional, changes data to comply with archival requirements)
--validate (optional, validates ODF file format standard)
--conformance <option> (required, sets conformance level for archival requirements. Options are: 
                        "must", "should", "may", "experimental" (all previous levels plus 
                        requirements still under evaluation) and "dna" (conforms to 
                        the preservation profile of the Danish National Archives).
--report (optional, reports the results of operations to a CSV file)
--verbose (optional, outputs to console the detailed results of operations)
--archivalpackage (optional, creates and saves spreadsheets to an archivable folder)
```
**Examples**

In your terminal change directory to the folder where you have the ```ODS-Archiving.jar``` file.

Filepath usage
```
java -jar ODS-Archiving.jar --convert --check --change --validate --inputfilepath "C:\Spreadsheet.xlsx" --outputfolder "C:\AnyFolder" --conformance "experimental" --report --verbose
```
Folder usage
```
java -jar ODS-Archiving.jar --convert --check --change --validate --inputfolder "C:\FolderOne" --recurse --outputfolder "C:\FolderTwo" --conformance "experimental" --report --verbose --archivalpackage
```

## Conformance

The conformance parameter is a required parameter. It determines the level of requirements to be compliant with. To find information on the associated requirements for each level, read [a forked version of the OPF Spreadsheets Preservation Specification](https://github.com/Asbjoedt/sheets-preservation-spec/blob/main/Draft%20v1.0/Specification.md#41-opendocument-spreadsheets). The levels and associated requirements in the specification are:
* **must**
  * No external data i.e. data connections, cell references, RealTimeData functions or objects
  * No embedded objects
* **should** - includes "must" and following requirements
  * No macros
  * Embedded fonts
  * Content exists
* **may** - includes "must", "should" and following requirements
  * Creator metadata
  * SettingsDOM file
  * Cell hyperlinks
  * Preview image

The conformance levels are embedded in each other so that the "all" conformance parameter performs "must", "should" and "may" requirements from the specification, "normal" performs "must" and "should" requirements and "minimal" performs only "must" requirements.

Some requirements are under consideration (embedded fonts, active sheet and settingsDOM). These can be enabled in the ODS Archiving application through this all-inclusive festivitas level:
* **experimental** - includes "must", "should", "may" and following requirements
  * First sheet is active sheet
  * Set to read-only
  * No printer settings

I was the main writer of the original specification for the OPF Spreadsheets Preservation Specification when I worked at the Danish National Archives (DNA). Since my departure, tongue-in-cheek I quote Godfather and say "look how they massacred my boy" because it has changed decidedly. I therefore continue my own app implementation of the original "Draft v1.0" by using the above-mentioned forked version of the specification. However, I do want to support the Danish National Archives' changes to the specification as they appear now in [Draft v1.9](https://github.com/openpreserve/sheets-preservation-spec/blob/main/Draft%20v1.0/Specification.md#1-introduction). Therefore, I provide a conformance level for using this version of the spec:
* **dna**
  * May have external data i.e. data connections, cell references, RealTimeData functions or objects
  * May have embedded objects
  * Content exists
  * No macros
  * No digital signatures

## Sample Data
You can download a zipped folder with spreadsheets sample data, "SampleData.zip", from this location:
* [Download Sample Data](https://github.com/Asbjoedt/CLISC/tree/master/Docs)

## Dependencies

The application uses the following software.
* [LibreOffice](https://www.libreoffice.org/): LibreOffice is used for background conversion of spreadsheets to .ods file format. You must therefore have the program installed.
* [ODF Toolkit](https://odftoolkit.org/): The ODF Toolkit includes a number of subcomponents with separate copyright notices and license terms. Your use of these subcomponents is subject to the terms and conditions of the licenses listed in their [LICENSE](https://github.com/tdf/odftoolkit/blob/master/LICENSE) file. Copyright ownership information can be found in their [NOTICE](https://github.com/tdf/odftoolkit/blob/master/NOTICE) file. ODF Toolkit is used for checking and changing the content of spreadsheets.
* [Open Preservation Foundation ODF Validator](https://github.com/openpreserve/odf-validator): OPF ODF Validator is used for validating the OpenDocument Spreadsheets file format Standard.
