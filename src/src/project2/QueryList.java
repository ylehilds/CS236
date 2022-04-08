//REQUIRED CLASS
package project2;

import project1.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;


public class QueryList {
	private static List query;
	private String name;
	
	private LexicalAnalyzer lex;
	public QueryList(LexicalAnalyzer lex) throws IOException
	{
		
		this.lex = lex;
		
		lex.nextToken();
		DatalogProgram.CommentCheck();
		query = new ArrayList();
		if (lex.getToken().getType()==Enums.TokenType.ID )
			query.add(new Query(lex));
		//System.out.println("***" + lex.getToken());
		queryListTail();
	}
	public void queryListTail()throws IOException
	{
		if (lex.getToken().getType()==Enums.TokenType.Q_MARK)
			  lex.nextToken();
		DatalogProgram.CommentCheck();
		 // lex.nextToken();
		  //System.out.println(lex.getToken());
		if (lex.getToken().getType() == Enums.TokenType.ID)
		{
			query.add(new Query(lex));
			//lex.nextToken();
			queryListTail();
		}
		else if (lex.getToken().getType() != Enums.TokenType.EOF)
		{
			throw new InputMismatchException();	
		}
	}
	public String getName()
	{
		return name;
	}
	public String toString()
	{
		String wordList="";
		if (query.size()==0)
		{
//			wordList="Queries()\n";
		}
		else
		{
//			wordList="Queries(" + query.size() + "):\n";
		}
		for (int i=0; i < query.size(); i++)
		{
			wordList+="  "+query.get(i)+"\n";
		}
		return wordList;	
	}
	public int size()
	{
		return query.size();
	}
	public static List getQueries() {
		return query;
	}
}
