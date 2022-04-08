//REQUIRED CLASS
package project2;

import project1.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;


public class SchemeList {
	private static List schemes;
//	private String name;
	
	private LexicalAnalyzer lex;
	
	public SchemeList(LexicalAnalyzer LexicalAnalyzer) throws IOException
	{
		this.lex = LexicalAnalyzer;
		lex.nextToken();
		DatalogProgram.CommentCheck();
		setSchemes(new ArrayList());
	
		if (lex.getToken().getType()==Enums.TokenType.ID)
			getSchemes().add(new Scheme(lex));
		else 
			throw new InputMismatchException();
		SchemeListTail();
		
		//System.out.println(schemes);
	}

	public void SchemeListTail() throws IOException
	{
		if (lex.getToken().getType()==Enums.TokenType.RIGHT_PAREN)
		  lex.nextToken();
		DatalogProgram.CommentCheck();
		if (lex.getToken().getType() == Enums.TokenType.ID)
		{
			getSchemes().add(new Scheme(lex));
			SchemeListTail();
		}
		else	return;
	
		
	}
	public String toString()
	{
		String wordList="";
		
//			wordList="Schemes(" + schemes.size() + "):\n";
		for (int i=0; i < getSchemes().size(); i++)
		{
			wordList+="  "+getSchemes().get(i)+"\n";
		}
		return wordList;	
	}

	public void setSchemes(List schemes) {
		this.schemes = schemes;
	}

	public static List getSchemes() {
		return schemes;
	}
//	public String getName()
//	{
//		return name;
//	}
}
