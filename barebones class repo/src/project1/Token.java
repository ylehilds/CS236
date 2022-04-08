//REQUIRED CLASS
package project1;

public class Token
{

	//Domain

	    private String value;

	    private Enums.TokenType type;

	    private int lineNumber;

	//Constructors

	    public Token(){}

		public Token(String val, int lineNum)
		{
			value = val;
			lineNumber = lineNum;
		}
		
		 public Token(Enums.TokenType t, String val, int lineNum){
	    	type = t;
	    	value = val;
	    	lineNumber = lineNum;
	    }
	    /*public Token(TokenType t, String val, int lineNum){
	    	type = t;
	    	value = val;
	    	lineNumber = lineNum;
	    }*/

	//Access Methods

	    public String getValue(){
	    	return value;
	    }

	    public Enums.TokenType getType(){
	    	return type;
	    }

	    public int getLineNumber(){
	    	return lineNumber;
	    }

	//Update Methods

	    public void setValue(String val){}

	    public void setType(Enums.TokenType t){}

	    public void setLineNumber(int lineNum){}

	//Print Method

	    public String toString(){
	    	return "("+this.getType() + "," + "\""+this.getValue() +
	    			"\""+ "," + this.getLineNumber()+")";

}
/*	public enum TokenType {

	    COLON, COLON_DASH, COMMA, LEFT_PAREN, PERIOD, Q_MARK, RIGHT_PAREN, EQ, NE,

	    GT, GE, LT, LE, MULTIPLY, ADD, STRING, SCHEMES, FACTS, RULES, QUERIES, ID,

	    EOF, UNDEFINED, COMMENT;
	    	

}
*/

}