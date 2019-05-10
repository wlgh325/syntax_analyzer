package syntax_analyzer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

	public static void main(String[] args) throws IOException{
		// File path
		String productionFile = "./src/Docs/Transition.txt";
		String symbolTableFile = "./src/Docs/test.c.out";
		String slrTableExcelFile = "./src/Docs/SLRTableFile.xls";
		
		// Declare transition table and production class
		ReadTableFile readTableFile = new ReadTableFile(slrTableExcelFile);
		ReadProduction readProduction = new ReadProduction(); 
		
		// Read production, symbol table and SLR table
		readProduction.readProduction(productionFile);	
		readProduction.readSymbolTableFile(symbolTableFile);
		readTableFile.readExcel();	//read excel file which contains information symbol table
		
		// Store information to analyze syntax
		ArrayList<String> symbolTable = readProduction.getSymbolTable();
		ArrayList<Production> production = readProduction.getProductionList();
		HashMap<Integer, HashMap<String, String>> slrTable = readTableFile.getSLRTable();
		
		// Do SLR parsing
		Syntax_analyzer syntaxAnalyzer = new Syntax_analyzer(slrTable, symbolTable, production);
		
	}
}
