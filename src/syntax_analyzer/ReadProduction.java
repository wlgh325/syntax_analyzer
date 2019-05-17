package syntax_analyzer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadProduction{

	// Total number of production
	private final int production_NUM = 29;
	
	// Store production and symbol table
	private ArrayList<Production> production_arr = new ArrayList();
	private ArrayList<SymbolTable> symbolTable = new ArrayList();
	
	void readProduction(String productionFileName) throws IOException{
		File file = new File(productionFileName);
        
        // Declare Input stream
        FileReader filereader = new FileReader(file);

        // Declare read buffer
        BufferedReader bufReader = new BufferedReader(filereader);
        
        String line = "";
        Production trashProduction = new Production(null,null);
        this.production_arr.add(trashProduction);	// To start with 1 index, add null production instance
        
        // Remove trash buffer in first
        int singlech = filereader.read();
        
        while((line = bufReader.readLine()) != null){
        	String[] split_str;
        	split_str = line.split("→");	// split lhs and rhs of production
        	
        	Production production = new Production();

        	production.setLHS(split_str[0]);	//store the lhs
        	
        	String[] temp_second;
        	temp_second = split_str[1].split(" ");
        	production.setRHS(temp_second);	//store the rhs
        	this.production_arr.add(production);
        }
        
        bufReader.close();
	}
	
	void readSymbolTableFile(String file) throws IOException{
		File Symbolfile = new File(file);
	        
			// Declare Input stream
	        FileReader filereader = new FileReader(Symbolfile);

	        // Declare read buffer
	        BufferedReader bufReader = new BufferedReader(filereader);
	        
	        String line = "";
	        
	        while((line = bufReader.readLine()) != null){
	        	String[] symbol_temp;
	        	symbol_temp = line.split("\t");
	        	String [] temp_str = new String[] {symbol_temp[0],symbol_temp[1]};
	        	
	        	SymbolTable temp = new SymbolTable(temp_str,Integer.parseInt(symbol_temp[2]),Integer.parseInt(symbol_temp[3]));
	        	symbolTable.add(temp);
	        	
	        }
	        SymbolTable temp = new SymbolTable(new String[] {"ENDMARKER",null} ,-1,-1) ;
	        symbolTable.add(temp);
	        bufReader.close();
	}
	
	//////////////
	///Getter/////
	//////////////
	ArrayList<Production> getProductionList() {
		return production_arr;
	}
	
	ArrayList<SymbolTable> getSymbolTable() {
		return symbolTable;
	}
}