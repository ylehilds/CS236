//REQUIRED CLASS
package project1;
import java.util.Map;
	import java.util.HashMap;
	import java.io.BufferedReader;
	import java.io.File;
	import java.io.FileReader;
	import java.io.IOException;
	//import project1.Token;
	
	public class LexicalAnalyzer {
	private Map<String, Enums.TokenType> map;
	private char temp;	 
	private FileReader reader;
	private BufferedReader buff;
	private int lineNumber = 1;
	private int tokenCount;
	private Token token;
//public class LexicalAnalyzer
//{

public LexicalAnalyzer (File file)
		{
			mapConstructor();
			try
			{
			reader = new FileReader(file);
			buff = new BufferedReader(reader);
			} catch (IOException e)
			{
				System.out.println(e.getMessage());
			}
		}
		
		public Token nextToken () throws IOException
		{
			temp = (char) buff.read();
			 while (temp=='\r')
				{
					temp = (char) buff.read();
				}
			 while (temp=='\n')
			{
				lineNumber++;
				temp = (char) buff.read();
			}
			while(Character.isWhitespace(temp))
				{
					temp = (char) buff.read();
					if (temp=='\n')
					{
						break;
					}
				}
			
			if(Character.getType(temp)==0) 
			{
				token = new Token(Enums.TokenType.EOF,"", lineNumber);
				//token = new Token (TokenType.EOF,"", lineNumber);
				tokenCount++;
				buff.close();
				//countPrint(tokenCount);
			}
			
			else if (temp=='\n')
			{
				lineNumber++;
				
				nextToken();
			}
			
			else if (Character.isLetter(temp))
			{
				LetterIdentifier(temp);
			}
			else if (temp=='\'')
			{
				StringChecker(temp);
			}
			//new stuff
			else if (temp=='#')
			{
				commentCheck(temp);
			}
			else
			{
				symbolsCheck(temp);
			}
			return token;
		}
		public void countPrint(int tokenCount)
		{
			System.out.println(token.toString());
			System.out.print("Total Tokens = " + tokenCount);
		}
		
		//new stuff:
		public Token commentCheck(char temp) throws IOException
		{
			int lineNumberChanging=lineNumber;
			String word="#|";
			String comment="";
			String comment2="";
			comment += temp;
			buff.mark(2);
			temp = (char)buff.read();
			
			comment2 += comment + temp;
		if (comment2.startsWith(word))
		{
			//temp = (char)buff.read();
			boolean condition =true;
			while (condition)
			{
				buff.mark(2);
				temp = (char)buff.read();
				
				if (temp=='\n')
				{
					lineNumber++;
				}
				else if(Character.getType(temp)==0) 
				{
					condition = false;
					unidentifiedSymbol(comment2, lineNumberChanging);	
				}
				else if (temp=='|')
				{
					//buff.mark(2);
					temp = (char)buff.read();
					if (temp=='#')
					{
						condition = false;
						buff.reset();
						temp = (char)buff.read();
						comment2+=temp;
						temp = (char)buff.read();
						//comment2+=temp;
						
						//temp = (char)buff.read();
					}
					else
					{
						buff.reset();
					}
				}
			
					//buff.mark(2);
					//temp = (char)buff.read();
					
						comment2+=temp;
					
				}
			
		}
		else 
		{
			comment2="#";
			buff.reset();
			//temp = (char)buff.read();
			//if (temp!='\r')
			//{
			//	comment2+=temp;
			//}
			boolean condition =true;
			while (condition)
			{
				temp = (char)buff.read();
				//comment2+=temp;
				
				if (temp=='\n')
				{
					lineNumber++;
					condition = false;
					//temp = (char)buff.read();
				}
				else if(Character.getType(temp)==0) 
				{
					condition = false;
					unidentifiedSymbol(comment2, lineNumberChanging);	
				}	
				
				else if (temp!='\r' && temp !='\n')
				{
					//buff.reset();
					comment2+=temp;
				}
			}
		}
		 if(Character.getType(temp)!=0) 
			{
				token = new Token (Enums.TokenType.COMMENT, comment2, lineNumberChanging);
				//System.out.println(token.toString());
				
				//getToken();
				tokenCount++;
			}
		return token;
		}
		
		public Token symbolsCheck(char temp) throws IOException
		{
			//Token token;
			String symbol="";
			String symbol2="";
			symbol += temp;
			buff.mark(2);
			symbol2 += symbol + (char)buff.read();
		
				if (map.containsKey(symbol2))
				{
					token = new Token (map.get(symbol2),symbol2, lineNumber);
					//getToken();
					//System.out.println(token.toString());
					tokenCount++;
					//return token;
					//nextToken();
				}
				else if (map.containsKey(symbol))  
				{
					buff.reset();
					token = new Token (map.get(symbol),symbol, lineNumber);
					//System.out.println(token.toString());
					//getToken();
					tokenCount++;
					//return token;
					//nextToken();
				}
			
				else 
				{
					token = new Token (Enums.TokenType.UNDEFINED,symbol, lineNumber);
					//System.out.println(token.toString());
					//getToken();
					tokenCount++;
					buff.reset();
					//return token;
					//nextToken();
								}
				return token;
		}
		public Token StringChecker(char temp) throws IOException
		{
		int tempLineNumber = lineNumber;
		String word="";
		boolean condition =true;
		while (condition)
		{
			temp = (char)buff.read();
			buff.mark(2);
			char quoteCheck1 = temp;
			char quoteCheck2 = (char) buff.read();
			if (quoteCheck1 == '\'' && quoteCheck2=='\'')
			{
				word += quoteCheck1;
			}
			else{ buff.reset(); 
				
				if (quoteCheck1 == '\'' && quoteCheck2!='\'')
			{
				condition= false;
			}
			
				else if (temp=='\n') 
			{
				lineNumber++;
				word+= temp;
			}
		
//			temp  = (char)buff.read();
				else if(Character.getType(temp)==0) 
			{
				condition = false;
				unidentifiedSymbol(word, tempLineNumber);	
			}
			else word+=temp;
		}
		}
		 if(Character.getType(temp)!=0) 
		{
			token = new Token (Enums.TokenType.STRING, word, tempLineNumber);
			//System.out.println(token.toString());
			
			//getToken();
			tokenCount++;
		}
	return token;
		//nextToken();
		} 
		public void unidentifiedSymbol(String word, int tempLineNumber) throws IOException
		{
		String check="#";
			if (word.startsWith(check))
			{
				token = new Token (Enums.TokenType.UNDEFINED, word, tempLineNumber);
				tokenCount++;
			}
			else
			{
			word = word.replaceAll("'", "''");
			word = "'"+word;
			token = new Token (Enums.TokenType.UNDEFINED, word, tempLineNumber);
			//System.out.println(token.toString());
			
			//getToken();
			tokenCount++;
			//return token;
			}
		}
		public Token LetterIdentifier(char temp) throws IOException
		{
			String word="";
			while (Character.isLetterOrDigit(temp))
			{
				word+= temp;
				buff.mark(2);
				temp = (char)buff.read();	
			}
			buff.reset();   
			
				if (word.equals("Queries"))
				{
					token = new Token(Enums.TokenType.QUERIES, word, lineNumber);
					//System.out.println(token.toString());
					//getToken();
					tokenCount++;
					//nextToken();
				}
				else if (word.equals("Facts"))
				{
					token = new Token(Enums.TokenType.FACTS, word, lineNumber);
					//System.out.println(token.toString());
					//getToken();
					tokenCount++;
					//nextToken();
				}
				else if (word.equals("Rules"))
				{
					token = new Token(Enums.TokenType.RULES, word, lineNumber);
					//System.out.println(token.toString());
					//getToken();
					tokenCount++;
					//nextToken();
				}
				else if (word.equals("Schemes"))
				{
					token = new Token(Enums.TokenType.SCHEMES, word, lineNumber);
					//System.out.println(token.toString());
					//getToken();
					tokenCount++;
					//nextToken();
				}
				else 
				{
					token = new Token(Enums.TokenType.ID, word, lineNumber);
					//System.out.println(token.toString());
					//getToken();
					tokenCount++;
					//nextToken();
				}
				return token;
			} 
		public void mapConstructor()
		{
			map = new HashMap<String, Enums.TokenType>();
			map.put(":", Enums.TokenType.COLON);
			map.put(":-", Enums.TokenType.COLON_DASH);
			map.put(",", Enums.TokenType.COMMA);
			map.put("(", Enums.TokenType.LEFT_PAREN);
			map.put(".", Enums.TokenType.PERIOD);
			map.put("?", Enums.TokenType.Q_MARK);
			map.put(")", Enums.TokenType.RIGHT_PAREN);
			map.put("=", Enums.TokenType.EQ);
			map.put("!=", Enums.TokenType.NE);
			map.put(">", Enums.TokenType.GT);
			map.put(">=", Enums.TokenType.GE);
			map.put("<", Enums.TokenType.LT);
			map.put("<=", Enums.TokenType.LE);
			map.put("*", Enums.TokenType.MULTIPLY);
			map.put("+", Enums.TokenType.ADD);
		}
		public Token getToken()
		{
			return token;
		}
		
 

}



//}
