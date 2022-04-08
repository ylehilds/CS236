//REQUIRED CLASS
package project2;

import project1.*;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;


public class Rule {
	public Predicate leftSide;
	public PredicateList rightSide;
	public Predicate predicate;
	public PredicateList predicateList;
	public Rule(LexicalAnalyzer lex) throws IOException
	{
		if (lex.getToken().getType() == Enums.TokenType.QUERIES)
		{
			if (lex.nextToken().getType()== Enums.TokenType.COLON)
			{
				DatalogProgram.CommentCheck(); //new
				return;
			}
		}
		leftSide = new Predicate(lex);
		lex.nextToken();
		DatalogProgram.CommentCheck(); //new
		if (lex.getToken().getType()!=Enums.TokenType.COLON_DASH)
			throw new InputMismatchException();
		lex.nextToken();
		DatalogProgram.CommentCheck(); //new
		rightSide = new PredicateList(lex);
		if (lex.getToken().getType()!=Enums.TokenType.PERIOD)
			throw new InputMismatchException();
		
		
	}
	//public String toString ()
	//{
	//	return "" + predicate+" :- "+predicateList;
	//}
	public Predicate getLeft(){
		return leftSide;
	}
	public PredicateList getRight(){
		return rightSide;
	}
	public String toString(){
		return "  " + leftSide + " :- " + rightSide;
	}
	
}

