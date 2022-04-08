//REQUIRED CLASS - KEEP THIS FILE IN THE 'project1' DIRECTORY WITH 'package project1'
//IMPLEMENT THE 'body' METHOD. MAKE AN INSTANCE OF 'LexicalAnalyzer' and return an ouput string
//DO NOT ADD METHODS

package project1;

import java.io.File;
import java.io.IOException;

public class Project1
{
        public static String body(String[] args){
		int count=0;
		File input;
		//try{
                input = new File(args[0]);
			//	} catch(IOException e)
				//{
				//System.out.println("Problem");
				//}
				
		LexicalAnalyzer analizer = new LexicalAnalyzer(input);
		Token test=null;
		String output="";
		do
		{
		try
		{
			output+=test = analizer.nextToken();
			output+="\n";
			//System.out.println(test);
			count++;
		} catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
		} while (!test.getType().equals(Enums.TokenType.EOF));
		//System.out.println("Total Tokens = " + count);
		output+="Total Tokens = " + count;
		//System.out.println(output);
                return output;
	}
}
