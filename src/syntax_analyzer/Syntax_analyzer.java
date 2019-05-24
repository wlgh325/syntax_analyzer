package syntax_analyzer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Syntax_analyzer {
	// STR-table
	private HashMap<Integer, HashMap<String, String>> slrTable;
	// symbolTable
	private ArrayList<SymbolTable> symbolTable;
	//production
	private ArrayList<Production> production;
	
	//state Stack 
	private Stack<Integer> stateStack;

	// current state 
	private int currentState;
	
	// nextSymbolIndex is the variable to know next symbol
	private int nextSymbolIndex;


	// constructor
	Syntax_analyzer(HashMap<Integer, HashMap<String, String>> slrTable, ArrayList<SymbolTable> symbolTable,
			ArrayList<Production> production) {
		this.slrTable = slrTable;
		this.symbolTable = symbolTable;
		this.production = production;
		this.stateStack = new Stack<>();
		// start State is 2 so  initialize state Stack and currenState to 2
		this.stateStack.push(2);
		this.currentState = 2;
		// before analyzer start, next Symbol is first element of symbolTable, so initialize nextSymabolIndex to 0
		this.nextSymbolIndex = 0;
	}

	//this function is that analyzer whether tokens in symbol Table matches the syntax 
	public void analyze() {
		while (true) {
			// when a value in the SLR-table that matches the current state (row) and the next symbol (column) is found,
			// if there is no corresponding value, ERROR is generated.
			if(slrTable.get(this.currentState).get(this.symbolTable.get(this.nextSymbolIndex).getSymbol()[0])==null) {
				//error checking
				System.out.println("Symbol '"+this.symbolTable.get(this.nextSymbolIndex).getSymbol()+", is syntax error" +"line: " + this.symbolTable.get(this.nextSymbolIndex).getLineNum() +", index: "+ this.symbolTable.get(this.nextSymbolIndex).getLineIndex());
				break;
			}
			//  but if the value is exist, save the value to String variable 'action'
			String action = slrTable.get(this.currentState).get(this.symbolTable.get(this.nextSymbolIndex).getSymbol()[0]);
			// first element of String 'action' is R or S 
			if (action.charAt(0) == 'R') {
				// if first element of String 'action' is R, this meaning of action is 'reduce X->alpha'
				// save element of String 'action' excepted first element to Integer variable 'reduceProductionIndex' (e.g action is R20,and then reduceProductionIndex is (int)20)
				int reduceProductionIndex = Integer.parseInt(action.substring(1));
				// stagePopNum is the number of non-terminal and terminal in RHS of reduce Production
				int statePopNum;
				//so find a production by reduceProductionIndex. if RHS of the production is EPSILON, stateNum is 0, if not, stateNum is the number of non-terminal and terminal in RHS
				if(this.production.get(reduceProductionIndex).getRHS()[0].equals("EPSILON")) {
					statePopNum = 0;
				}else {
					statePopNum = this.production.get(reduceProductionIndex).getRHS().length;
				}
				// pop element of stateStack as many as statePopNum, also remove element of symbolTable as many as stateNum. 
				for (int i = 0; i < statePopNum; i++) {
					this.stateStack.pop();
					this.symbolTable.remove(this.nextSymbolIndex - 1);
					this.nextSymbolIndex--;
				}
				this.nextSymbolIndex++;
				
				// update currentState to last element value of stateStack
				this.currentState = this.stateStack.lastElement();
				
				// add LHS of reduce production to symbolTable
				String LHS = this.production.get(reduceProductionIndex).getLHS();
				SymbolTable temp = new SymbolTable(new String[] {LHS,null},-1,-1);
				this.symbolTable.add(this.nextSymbolIndex-1,temp);
				
				//if LHS value is S and symbolSize is 2 (a.k.a symbolTable element is [S , ENDMARKER]), analyzer is done
				if(LHS.equals("S") && this.symbolTable.size() == 2) {
					// end condition
					System.out.println("finish!! your code is accepted.!!");
					break;
				}
				
				// find a value in the SLR-table that matches the current state (row) and LHS (column), and do GOTO(currentSate,LHS)
				action = this.slrTable.get(this.currentState).get(LHS);
				this.currentState = Integer.parseInt(action);
				this.stateStack.push(this.currentState);

			} else if (action.charAt(0) == 'S') {
				// if first element of String 'action' is R, this meaning of action is 'shift nextState'
				// save element of String 'action' excepted first element to Integer variable 'stateShiftIndex' (e.g action is S60,and then stateShiftIndex is (int)60) 
				int stateShiftIndex = Integer.parseInt(action.substring(1));

				//push stateShiftIndex to stateStack and update currentState value to stateShiftIndex
				this.currentState = stateShiftIndex;
				this.stateStack.push(stateShiftIndex);
				//and by increasing  nextSymbolIndext value by one, update nextSymbol
				this.nextSymbolIndex++;
			}

		}
	}
}