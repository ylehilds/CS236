//REQUIRED CLASS
package project2;

import project1.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class RuleList {
	private static List rules;
	private String name;
	
	private LexicalAnalyzer lex;
	
	public RuleList(LexicalAnalyzer lex) throws IOException
	{
		this.lex = lex;
		lex.nextToken();
		DatalogProgram.CommentCheck();
		rules = new ArrayList();
		if (lex.getToken().getType()==Enums.TokenType.RULES)
			rules.add(new Rule(lex));
		RuleListTail();
		
	}
	public void RuleListTail() throws IOException
	{
		
		if (lex.getToken().getType() == Enums.TokenType.ID)
		{
			rules.add(new Rule(lex));
			lex.nextToken();
			DatalogProgram.CommentCheck();
			RuleListTail();
		}
		else return;
		 
	}
	public String toString()
	{
		String wordList="";
	
//			wordList="Rules(" + rules.size() + "):\n";
		
		
		for (int i=0; i < rules.size(); i++)
		{
			wordList+="  "+rules.get(i)+"\n";
		}
		return wordList;	
	}
	
	public static List getRules() {
		return rules;
	}
	//public List getList(){
	///	return rules;
	//}
}

