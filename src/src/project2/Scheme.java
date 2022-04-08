//REQUIRED CLASS
package project2;

import project1.*;
import project4.Relation;

import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class Scheme {

	private String name;
	private List schema;
	public Scheme(LexicalAnalyzer lex) throws IOException
	{
		if (lex.getToken().getType() == Enums.TokenType.FACTS)
		{
			throw new InputMismatchException();
		}
		if (lex.getToken().getType() != Enums.TokenType.ID)
		{
			throw new InputMismatchException();
		}
		name = lex.getToken().getValue();
		lex.nextToken();
		DatalogProgram.CommentCheck(); //new
		schema = new ArrayList();
		if (lex.getToken().getType() != Enums.TokenType.LEFT_PAREN)
			throw new InputMismatchException();
		
		do {
			lex.nextToken();
			DatalogProgram.CommentCheck(); //new
			if (lex.getToken().getType()==Enums.TokenType.RIGHT_PAREN)
				throw new InputMismatchException();
			if (lex.getToken().getType()!=Enums.TokenType.ID)
				throw new InputMismatchException();
			schema.add(lex.getToken().getValue());
			lex.nextToken();
			DatalogProgram.CommentCheck(); //new
		}while(lex.getToken().getType()== Enums.TokenType.COMMA);
		//System.out.println(schema);
		if (lex.getToken().getType() != Enums.TokenType.RIGHT_PAREN)
			throw new InputMismatchException();
		//System.out.println(schema);
		}
	public Scheme() {
		name = project2.Predicate.getPredicateName();
		// TODO Auto-generated constructor stub
	}
	public Scheme(String theName) {
		name = theName;
		schema = new ArrayList();
		// TODO Auto-generated constructor stub
	}
	public Scheme(Scheme scheme) {
		// TODO Auto-generated constructor stub
	}
	public Scheme(String name2, ArrayList arrayschema) {
		name = name2;
		schema = arrayschema;
		// TODO Auto-generated constructor stub
	}
	public String getName()
	{
		return name;
	}
	//public String setName(Relation temp, schema <Query> queryschema)
	//{
	//	for (int i=0; i<queryschema.size();i++)
	//	{
	//		temp.getScheme().name = queryschema.get(i).toString();
	//	}
	//	return name;
	//}
	public void setSchema(List scheme){
		schema = scheme;
	}
	public void setName(String name){
		this.name = name;
	}
	public Scheme(Predicate predicate){
			name = predicate.getName();
			schema = new ArrayList(predicate.getSchema());
	}
		public String toString ()
		{
			String var=name +"(";
			for (int i=0; i< schema.size(); i++)
			{
				var+= schema.get(i);
				if(i<schema.size()-1)
				{
					var+=",";
				}
			}
			var+=")";
			return  var;
		}
		public void setSchemeschema(List Theschema)
		{	
			schema = Theschema;
		}
		public List getSchema()
		{
			return schema;
		}
		public int size() {
			// TODO Auto-generated method stub
			return schema.size();
		}
		
	}


