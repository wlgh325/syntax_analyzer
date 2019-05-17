package syntax_analyzer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

	public static void main(String[] args) throws IOException{
		// File path
		String productionFile = "./src/Docs/Transition.txt";
		String slrTableExcelFile = "./src/Docs/SLRTableFile2.xls";
		
		// Declare transition table and production class
		String productionFile = "./docs/Transition.txt";
		String symbolTableFile = args[0];
		String slrTableExcelFile = "./docs/SLRTableFile.xls";

		// Read excel file which contains SLR table
		ReadTableFile readTableFile = new ReadTableFile(slrTableExcelFile);
		readTableFile.readExcel();	
		
		// Read production and symbol table
		ReadProduction readProduction = new ReadProduction(); 		
		readProduction.readProduction(productionFile);	
		readProduction.readSymbolTableFile(symbolTableFile);	
		readTableFile.readExcel();	//read excel file which contains information symbol table
		
		// Store information to analyze syntax
		ArrayList<SymbolTable> symbolTable = readProduction.getSymbolTable();
		ArrayList<Production> production = readProduction.getProductionList();
		HashMap<Integer, HashMap<String, String>> slrTable = readTableFile.getSLRTable();
		
		// Do SLR parsing
		for(int i =0; i <6; i++) {
			String symbolTableFile = "./src/Docs/test"+i+".c.out";
			readProduction.readSymbolTableFile(symbolTableFile);
			ArrayList<String> symbolTable = readProduction.getSymbolTable();
			
			
			Syntax_analyzer syntaxAnalyzer = new Syntax_analyzer(slrTable, symbolTable, production);
			syntaxAnalyzer.analyze();
			symbolTable.clear();
			
		}
	}
}
