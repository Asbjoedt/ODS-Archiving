# ODS-ArchivalRequirements
A Java console application, easy to integrate in workflows to check if an OpenDocument spreadsheet is compliant with archiving requirements, change it to be compliant and validate the spreadsheet.

* For more information, see repository **[CLISC](https://github.com/Asbjoedt/CLISC)**

## Methods
The application accepts input and output filepaths as arguments and you then have to choose one or more of the following methods as arguments.
```
--check
--change
--validate
```

An example implementation:

```
--check --change --validate --inputfilepath "C:\Users\%USERNAME%\Desktop\TestSpreadsheet.ods" --outputfilepath "C:\Users\%USERNAME%\Desktop\TestSpreadsheet.ods"
```
Or shorter:
```
-che -cha -val -inp "C:\TestSpreadsheet.ods" -out "C:\TestSpreadsheet.ods"
```

## Dependencies
* [ODF Toolkit](https://odftoolkit.org/): The ODF Toolkit includes a number of subcomponents with separate copyright notices and license terms. Your use of these subcomponents is subject to the terms and conditions of the licenses listed in their [LICENSE](https://github.com/tdf/odftoolkit/blob/master/LICENSE) file. Copyright ownership information can be found in their [NOTICE](https://github.com/tdf/odftoolkit/blob/master/NOTICE) file.

