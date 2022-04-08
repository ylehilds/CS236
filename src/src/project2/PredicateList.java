package project2;

import project1.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PredicateList {
	public List predicates;
	public String name;
	
	private LexicalAnalyzer lex;
	
	public PredicateList(LexicalAnalyzer lex) throws IOException
	{
		this.lex = lex;
		
		//lex.nextToken();
		predicates = new ArrayList();
		
		predicates.add(new Predicate(lex));
		PredicatesListTail();
	}
	public void PredicatesListTail() throws IOException
	{
		lex.nextToken();
		DatalogProgram.CommentCheck();
		//System.out.println(lex.nextToken());
		if (lex.getToken().getType() == Enums.TokenType.COMMA)
		{
			lex.nextToken();
			DatalogProgram.CommentCheck();
			predicates.add(new Predicate(lex));
			PredicatesListTail();
		}
	}
	public List getList(){
		return predicates;
	}
	public String toString()
	{
		String wordList="";
		for (int i=0; i < predicates.size(); i++)
		{
			wordList+=predicates.get(i);
			if (i<predicates.size()-1)
			wordList+=",";
		}
		return wordList+".";	
	}
}
