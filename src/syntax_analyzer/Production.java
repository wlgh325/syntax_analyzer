package syntax_analyzer;

public class Production {
	private String lhs;	// Left hand side of Production
	private String [] rhs;	// Right hand side of Production
	
	
	// default Constructor
	Production(){
		
	}
		
	// Constructor
	Production(String lhs, String [] rhs){
		this.lhs = lhs;
		this.rhs = rhs;
	}
	
	//////////////
	///Getter/////
	//////////////
	String getLHS() {
		return this.lhs;
	}
	
	String[] getRHS() {
		return this.rhs;
	}
	
	
	/////////////
	///Setter////
	/////////////
	void setLHS(String lhs) {
		this.lhs = lhs;
	}
	
	void setRHS(String[] rhs) {
		this.rhs = rhs;
	}
}
