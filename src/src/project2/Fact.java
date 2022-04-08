//REQUIRED CLASS
package project2;

import project1.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class Fact {
	private String name;
	private List list;
	
	public Fact(LexicalAnalyzer lex) throws IOException
	{
		if (lex.getToken().getType() == Enums.TokenType.RULES)
		{
			if (lex.nextToken().getType()== Enums.TokenType.COLON)
			DatalogProgram.CommentCheck(); //new
			return;
		}
		if (lex.getToken().getType() != Enums.TokenType.ID)
		{
			throw new InputMismatchException();
		}
		name = lex.getToken().getValue();
		lex.nextToken();
		DatalogProgram.CommentCheck(); //new
		list = new ArrayList();
		if (lex.getToken().getType() != Enums.TokenType.LEFT_PAREN)
		{
			throw new InputMismatchException();
		}
		
		do {
			lex.nextToken();
			DatalogProgram.CommentCheck(); //new
			if (lex.getToken().getType()==Enums.TokenType.RIGHT_PAREN)
				throw new InputMismatchException();
			list.add(lex.getToken().getValue());
			lex.nextToken();
			DatalogProgram.CommentCheck(); //new
		}while(lex.getToken().getType()== Enums.TokenType.COMMA);
		if (lex.getToken().getType() != Enums.TokenType.RIGHT_PAREN)
		{
			throw new InputMismatchException();
		}
		lex.nextToken();
		DatalogProgram.CommentCheck(); //new
		if (lex.getToken().getType() != Enums.TokenType.PERIOD)
		{
			throw new InputMismatchException();
		}
	}
	public List getList()
	{
		return list;
	}
	public String getName()
	{
		return name;
	}
	public String toString ()
	{
		String var=name +"(";
		for (int i=0; i< list.size(); i++)
		{
			var+= "'"+list.get(i)+"'";
			if(i<list.size()-1)
			{
				var+=",";
			}
		}
		var+=")";
		//System.out.println(var);
		return  var+".";
	}
}
