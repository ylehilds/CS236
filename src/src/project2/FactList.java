//REQUIRED CLASS
package project2;

import project1.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FactList {
	private static List facts;
	private String name;
	private LexicalAnalyzer lex;

	public FactList(LexicalAnalyzer LexicalAnalyzer) throws IOException
	{
		this.lex = LexicalAnalyzer;
		lex.nextToken();
		DatalogProgram.CommentCheck();
		setFacts(new ArrayList());
		if (lex.getToken().getType()==Enums.TokenType.STRING)
			getFacts().add(new Fact(lex));
		factsListTail();
		
		//System.out.println(facts);
	}
	public void factsListTail() throws IOException
	{
		
		if (lex.getToken().getType() == Enums.TokenType.ID)
		{
			getFacts().add(new Fact(lex));
			lex.nextToken();
			DatalogProgram.CommentCheck();
			factsListTail();
		}
		else return;
	}
	public String toString()
	{
		String wordList="";

		
//			wordList="Facts(" + facts.size() + "):\n";
		
		for (int i=0; i < getFacts().size(); i++)
		{
			wordList+="  "+getFacts().get(i)+"\n";
		}
		return wordList;	
	}
	public void setFacts(List facts) {
		this.facts = facts;
	}
	public static List getFacts() {
		return facts;
	}
}

