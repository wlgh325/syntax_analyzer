package syntax_analyzer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Syntax_analyzer {
	// STR-table
	private HashMap<Integer, HashMap<String, String>> slrTable;
	private ArrayList<SymbolTable> symbolTable;
	private ArrayList<Production> production;
	//
	private Stack<Integer> stateStack;

	private int currentState;
	private int nextSymbolIndex;


	Syntax_analyzer(HashMap<Integer, HashMap<String, String>> slrTable, ArrayList<SymbolTable> symbolTable,
			ArrayList<Production> production) {
		this.slrTable = slrTable;
		this.symbolTable = symbolTable;
		this.production = production;
		this.stateStack = new Stack<>();
		this.stateStack.push(2);
		this.currentState = 2;
		this.nextSymbolIndex = 0;
	}

	public void analyze() {
		while (true) {
			if(slrTable.get(this.currentState).get(this.symbolTable.get(this.nextSymbolIndex).getSymbol()[0])==null) {
				//error checking
				System.out.println("Symbol '"+this.symbolTable.get(this.nextSymbolIndex).getSymbol()+", is syntax error" +"line: " + this.symbolTable.get(this.nextSymbolIndex).getLineNum() +", index: "+ this.symbolTable.get(this.nextSymbolIndex).getLineIndex());
				break;
			}
			// Finds a value in the table that matches the state (row) and the next symbol (column).
			String action = slrTable.get(this.currentState).get(this.symbolTable.get(this.nextSymbolIndex).getSymbol()[0]);
			if (action.charAt(0) == 'R') {
				int reduceProductionIndex = Integer.parseInt(action.substring(1));
				int statePopNum;
				if(this.production.get(reduceProductionIndex).getRHS()[0].equals("EPSILON")) {
					statePopNum = 0;
				}else {
					statePopNum = this.production.get(reduceProductionIndex).getRHS().length;
				}
				for (int i = 0; i < statePopNum; i++) {
					this.stateStack.pop();
					this.symbolTable.remove(this.nextSymbolIndex - 1);
					this.nextSymbolIndex--;
				}
				this.nextSymbolIndex++;
				this.currentState = this.stateStack.lastElement();

				String LHS = this.production.get(reduceProductionIndex).getLHS();
				SymbolTable temp = new SymbolTable(new String[] {LHS,null},-1,-1);
				this.symbolTable.add(this.nextSymbolIndex-1,temp);
				if(LHS.equals("S") && this.symbolTable.size() == 2) {
					// end condition
					System.out.println("finish!! your code is accepted.!!");
					break;
				}
				action = this.slrTable.get(this.currentState).get(LHS);
				this.currentState = Integer.parseInt(action);
				this.stateStack.push(this.currentState);

			} else if (action.charAt(0) == 'S') {
				int stateGotoIndex = Integer.parseInt(action.substring(1));

				this.currentState = stateGotoIndex;
				this.stateStack.push(stateGotoIndex);

				this.nextSymbolIndex++;
			}

		}
	}
}