package syntax_analyzer;

import java.util.ArrayList;
import java.util.HashMap;

public class Syntax_analyzer {
	//STR-table
	private HashMap<Integer, HashMap<String, String>> slrTable;
	private ArrayList<String> symbolTable;
	private ArrayList<Production> production;


	Syntax_analyzer(HashMap<Integer, HashMap<String, String>> slrTable, ArrayList<String> symbolTable, ArrayList<Production> production){
		this.slrTable = slrTable;
		this.symbolTable = symbolTable;
		this.production = production;
	}
}