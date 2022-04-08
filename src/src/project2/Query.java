//REQUIRED CLASS
package project2;

import project1.*;

import java.io.IOException;
import java.util.InputMismatchException;


public class Query {
	private Predicate query;
	
	public Query(LexicalAnalyzer lex) throws IOException
	{
		if (lex.getToken().getType() == Enums.TokenType.EOF)
		{
			return;
		}
		query = new Predicate(lex);
		
		lex.nextToken();
		DatalogProgram.CommentCheck(); //new
		if (lex.getToken().getType()!=Enums.TokenType.Q_MARK)
			throw new InputMismatchException();
		
	}
	public Query(Predicate predicate){
		query = predicate;
	}
	public String getName()
	{
		return query.getName();
	}

	public Query(Query query){
		this.query = query.getQuery();
	}
	public Predicate getQuery(){
		return query;
	}
	public String toString(){
		return "  "+query.toString();
	}
}
