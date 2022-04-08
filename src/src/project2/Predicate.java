//REQUIRED CLASS
package project2;

import project1.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.InputMismatchException;
public class Predicate {
	private static String predicateName;
	private String name = predicateName;
	private List schema;
	private String symbol="";
public Predicate(LexicalAnalyzer lex) throws IOException
	{ 
	
		name = lex.getToken().getValue();
		lex.nextToken();
		DatalogProgram.CommentCheck(); //new
		schema = new ArrayList();
		if (lex.getToken().getType() != Enums.TokenType.LEFT_PAREN)
			throw new InputMismatchException();
		
		do {
			lex.nextToken();
			DatalogProgram.CommentCheck(); //new
			if (lex.getToken().getType()==Enums.TokenType.ID )
			{	
				schema.add(new Parameter(lex.getToken().getValue(),null));
				lex.nextToken();
				DatalogProgram.CommentCheck(); //new
				if (lex.getToken().getType()==Enums.TokenType.COMMA)
				{
					schema.add(new Parameter(lex.getToken().getValue(),null));
					continue;
				}
				else if (lex.getToken().getType()==Enums.TokenType.STRING)
				{

					schema.add(new Parameter(lex.getToken().getValue(),null));
					lex.nextToken();
					DatalogProgram.CommentCheck(); //new
					if (lex.getToken().getType()==Enums.TokenType.COMMA)
					{
						schema.add(new Parameter(lex.getToken().getValue(),null));
						continue;
					}
				}
			}else if (lex.getToken().getType()==Enums.TokenType.STRING)
			{
				schema.add(new Parameter(null, lex.getToken().getValue()));
				lex.nextToken();
				DatalogProgram.CommentCheck(); //new
				if (lex.getToken().getType()==Enums.TokenType.COMMA)
				{
					schema.add(new Parameter(lex.getToken().getValue(),null));
					continue;
				}
			}
			if (lex.getToken().getType()==Enums.TokenType.RIGHT_PAREN)
			{
				//schema.add(new Parameter(lex.getToken().getValue(),null));
				continue;
			}
			//lex.nextToken(); 
	if (lex.getToken().getType()==Enums.TokenType.RIGHT_PAREN)
			{
				break;
			}		
				
			expressionRecursive(lex);
			
			if (lex.getToken().getType() == Enums.TokenType.LEFT_PAREN || lex.getToken().getType() == Enums.TokenType.LT || lex.getToken().getType() == Enums.TokenType.LE
				||lex.getToken().getType() == Enums.TokenType.EQ || lex.getToken().getType() == Enums.TokenType.NE ||
				lex.getToken().getType() == Enums.TokenType.GE || lex.getToken().getType() == Enums.TokenType.GT
			)
				{
				symbol = lex.getToken().getValue();
				schema.add(new Parameter(lex.getToken().getValue(),null));
				lex.nextToken();	
				DatalogProgram.CommentCheck(); //new
					if (lex.getToken().getType() == Enums.TokenType.COMMA)
					{
						schema.add(new Parameter(lex.getToken().getValue(), null));
						continue;
					}
					if (lex.getToken().getType() != Enums.TokenType.LEFT_PAREN){
						throw new InputMismatchException();
					}
					schema.add(new Parameter(lex.getToken().getValue(),null));
					lex.nextToken();
					DatalogProgram.CommentCheck(); //new
					if (lex.getToken().getType() != Enums.TokenType.ID 
							&& lex.getToken().getType() != Enums.TokenType.STRING)
					{
						throw new InputMismatchException();
					}
					if (lex.getToken().getType() == Enums.TokenType.ID)
					{
						schema.add(new Parameter(lex.getToken().getValue(), null));
					}
						else schema.add(new Parameter(null,lex.getToken().getValue()));
					lex.nextToken();
					DatalogProgram.CommentCheck(); //new
					if (lex.getToken().getType() != Enums.TokenType.COMMA)
					{
						throw new InputMismatchException();
					}
					schema.add(new Parameter(lex.getToken().getValue(), null));
					lex.nextToken();
					DatalogProgram.CommentCheck(); //new
					if (lex.getToken().getType() != Enums.TokenType.ID 
							&& lex.getToken().getType() != Enums.TokenType.STRING)
					{
						throw new InputMismatchException();
					}
					if (lex.getToken().getType() == Enums.TokenType.STRING)
					{
						schema.add(new Parameter(null,lex.getToken().getValue()));
					}
						else schema.add(new Parameter(lex.getToken().getValue(), null));
					//schema.add(new Parameter(null, lex.getToken().getValue()));
					lex.nextToken();
					DatalogProgram.CommentCheck(); //new
					if (lex.getToken().getType() != Enums.TokenType.RIGHT_PAREN){
					throw new InputMismatchException();
				
					}
					schema.add(new Parameter(lex.getToken().getValue(),null));
					lex.nextToken();
					DatalogProgram.CommentCheck(); //new
					if (lex.getToken().getType() == Enums.TokenType.COMMA)
						schema.add(new Parameter(lex.getToken().getValue(),null));
				}
//		if (lex.getToken().getType() == Enums.TokenType.RIGHT_PAREN)
//				break;
		
		}while(lex.getToken().getType()== Enums.TokenType.COMMA);
		//System.out.println(schema);
		if (lex.getToken().getType() != Enums.TokenType.RIGHT_PAREN && lex.getToken().getType() != Enums.TokenType.PERIOD)
			throw new InputMismatchException();
		
		}
	
	public void expressionRecursive(LexicalAnalyzer lex) throws IOException
	{
		if (lex.getToken().getType() == Enums.TokenType.LEFT_PAREN)
		{
			schema.add(new Parameter(lex.getToken().getValue(),null));
			lex.nextToken();
			DatalogProgram.CommentCheck(); //new
			if (lex.getToken().getType() == Enums.TokenType.LEFT_PAREN)
			{
				expressionRecursive(lex);
			}
			
			while (lex.getToken().getType() != Enums.TokenType.RIGHT_PAREN)
			{
			if (lex.getToken().getType() == Enums.TokenType.LT || lex.getToken().getType() == Enums.TokenType.LE
				||lex.getToken().getType() == Enums.TokenType.EQ || lex.getToken().getType() == Enums.TokenType.NE ||
				lex.getToken().getType() == Enums.TokenType.GE || lex.getToken().getType() == Enums.TokenType.GT)
				schema.add(new Parameter(lex.getToken().getValue(),null));
			else if (lex.getToken().getType() == Enums.TokenType.ID)
				schema.add(new Parameter(lex.getToken().getValue(),null));
			else if (lex.getToken().getType() == Enums.TokenType.STRING)
					schema.add(new Parameter(null,lex.getToken().getValue()));
			else if (lex.getToken().getType() == Enums.TokenType.ADD || lex.getToken().getType() == Enums.TokenType.MULTIPLY)
				schema.add(new Parameter(lex.getToken().getValue(),null));
			else throw new InputMismatchException();
			lex.nextToken();
			DatalogProgram.CommentCheck(); //new
			}
			
		if ( lex.getToken().getType() == Enums.TokenType.RIGHT_PAREN)
			{
				schema.add(new Parameter(lex.getToken().getValue(),null));
				lex.nextToken();
				DatalogProgram.CommentCheck(); //new
			}
			if (lex.getToken().getType()==Enums.TokenType.COMMA)
			{
				schema.add(new Parameter(lex.getToken().getValue(), null));
			}
			
		}
	}
	public String getName()
	{
		return name;
	}
	public String toString(){
		String toReturn = name + "(";
		for(int i=0; i<schema.size(); i++){
			toReturn+=schema.get(i);
			if (i<schema.size()-1){
				toReturn+=",";
			}
		}
		toReturn+= ")";
		return toReturn;
	}

		

		//public Scheme getSchema() {
		//	Scheme schema = new Scheme();
		//	schema.setSchemeschema(schema);
			// TODO Auto-generated method stub
		//	return schema;
		//}
		public List <Parameter> getSchema(){
			return schema;
		}
		
		public void setSchema(List<Parameter> schema){
			this.schema = schema;
		}
		public static String getPredicateName() {
			// TODO Auto-generated method stub
			return predicateName;
		}
		public void setName(String name){
			this.name = name;
		}
		public Predicate(Predicate predicate){
			name = predicate.getName();
			schema = predicate.getSchema();	
		}

	}
	
	//Constructors â€¦
	//Access Methods â€¦
	//Update Methods â€¦


