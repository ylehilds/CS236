//REQUIRED CLASS
package project2;

import project1.*;
import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;

public class DatalogProgram
{
		private static SchemeList schemes;
		private static FactList facts;
		private static RuleList rules;
		private static QueryList queries;
		private Domain domain;
		private static LexicalAnalyzer lex;  
		private LexicalAnalyzer lex2;
		private String allRules="";
		public DatalogProgram(File file) throws IOException
		{			
			try {
				lex = new LexicalAnalyzer(file);
				lex2 = new LexicalAnalyzer(file);
				//while(lex.nextToken().getType()!=Enums.TokenType.EOF)	
				//{
					//System.out.println(lex.getToken());
				//}
				
				lex.nextToken();
				CommentCheck(); //new
				SchemesCheck();
								//Facts check
				if (lex.getToken().getType() != Enums.TokenType.FACTS)
				{
					throw new InputMismatchException();
				}
				
				lex.nextToken();
				CommentCheck(); //new
				if (lex.getToken().getType() != Enums.TokenType.COLON)
				{
					throw new InputMismatchException();
				}
				
				facts = new FactList(lex);
				//System.out.print(facts/*this*/);
				
				//Rule check
				if (lex.getToken().getType() != Enums.TokenType.RULES)
				{
					throw new InputMismatchException();
				}
				lex.nextToken(); // colon check
				CommentCheck(); //new
				if (lex.getToken().getType() != Enums.TokenType.COLON)
				{
					throw new InputMismatchException();
				}
				rules = new RuleList(lex);
				//System.out.print(rules/*this*/);
				
				//lex.nextToken(); // colon check
				if (lex.getToken().getType() != Enums.TokenType.QUERIES)
				{
					throw new InputMismatchException();
				}
				lex.nextToken(); // colon check
				CommentCheck(); //new
				if (lex.getToken().getType() != Enums.TokenType.COLON)
				{
					throw new InputMismatchException();
				}
				queries = new QueryList(lex);
				//System.out.print(queries/*this*/);
				
				if (lex.getToken().getType()!=Enums.TokenType.EOF)
					throw new InputMismatchException();
//				System.out.println("Success!");
				allRules+=schemes+ ""+facts+ rules + queries;
//				System.out.print(allRules);
//				domain = new Domain(lex2);
		
			} catch (InputMismatchException e) {
				// TODO Auto-generated catch block
				System.out.println("Failure!");
				System.out.print("  "+lex.getToken().toString());
			}
		}
		public void SchemesCheck() throws IOException
		{
			if (lex.getToken().getType() != Enums.TokenType.SCHEMES)//schemes check
				throw new InputMismatchException();

			lex.nextToken();//colon check
			CommentCheck(); //new
			if (lex.getToken().getType() != Enums.TokenType.COLON)
				throw new InputMismatchException();
			
			schemes = new SchemeList(lex);
			//System.out.print(schemes/*this*/);
			

		}
		
		public static void CommentCheck() throws IOException
		{
			while(lex.getToken().getType() == Enums.TokenType.COMMENT)
			{
				lex.nextToken();
			}
		}
		
		
		public static List FactsListP4() 
		{
			return FactList.getFacts();
		}
		public static List RulesListP4()
		{
			return RuleList.getRules();
		}
		public static List QueriesListP4()
		{
			return  QueryList.getQueries();
		}
		
		public static List SchemesListP4()
		{
			return SchemeList.getSchemes();
		}
		
		//Constructors …
		//Access Methods …
		//Update Methods …

}
