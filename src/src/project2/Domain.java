//REQUIRED CLASS
package project2;

import project1.*;

import java.io.IOException;
import java.util.Set;
import java.util.Iterator;
import java.util.TreeSet;
public class Domain {
private int count;
private int i=0;
private Set tree;

	public Domain(LexicalAnalyzer lex2) throws IOException
	{
		tree = new TreeSet();
		Token test;
		do
		{
			test = lex2.nextToken();
			DatalogProgram.CommentCheck(); //new
			if (lex2.getToken().getType()== Enums.TokenType.STRING)
			{
				tree.add(lex2.getToken().getValue());
			}
			//System.out.println(test);
			count++;
		} while (!test.getType().equals(Enums.TokenType.EOF));
		
		Iterator it = tree.iterator();
		if (tree.size()==0)
		{
			System.out.println("Domain()");
		}
		else System.out.println("Domain("+tree.size()+"):");
			while(it.hasNext())
			{
				System.out.println("  '"+it.next()+"'");
			}
	}
	
}
