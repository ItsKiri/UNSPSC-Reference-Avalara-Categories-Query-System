# UNSPSC-Reference-Avalara-Categories-Query-System
This project is built for Avalara, Inc. to query UNSPSC® reference with Avalara goods

To run this query system, you need to get UNSPSC_English.csv and Avalara_goods_and_services.csv. Pleases ask Avalara, Inc. for necessary files, then:

Step 1: Place files UNSPSC and Avalara at one level above the system directory.

Step 2: Open terminal on mac or command prompt on windows.

Step 3: Navigate to the directory of the program.

Step 4: Open the project in your favorite code editor (IntelliJ / Eclipse)

Step 5: In order for the files to be read, use the following paths:

	Mac:
		UNSPSC: ../UNSPSC_English.csv
		Avalara: ../Avalara_goods_and_services.csv
		Result: ../resultFinal.csv

	Windows:
		UNSPSC: ..\\UNSPSC_English.csv
		Avalara: ..\\Avalara_goods_and_services.csv
		Result: ..\\resultFinal.csv

Step 4: Type "javac *.java" => this will compile the project

Step 5: Type "java Project" => This will run the project 

Step 6: Check the resultFinal.csv at one level above the system directory.

To the run the system, you can use the following IDE:
1. IntelliJ (Community Edition) => https://www.jetbrains.com/idea/download/ - section=mac
2. Eclipse => https://www.eclipse.org/ide/
