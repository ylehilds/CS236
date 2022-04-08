package answerParser;

import java.util.Iterator;
import java.util.HashSet;

public class QueryAnswer
{
//Domain Implementation
    protected String query;
    protected String answer;
    protected HashSet<Line> lines;

    protected int answerTotal = 0;
    
//Constructors
    public QueryAnswer(String str, Int i){
        getQuery(str, i);
        getAnswer(str, i);
        getLines(str, i);
    }

//Queries
    public static boolean anotherQueryAnswer(String str, int index){
          Int i = new Int(index);
          skipWhitespace(str, i);
          int j = i.index;
          while(j < str.length() && str.charAt(j) != '?'){
              j++;
          };
          boolean result = j < str.length() && str.charAt(j) == '?';
          return result;
    };

    public int hashCode(){
        long result = query.hashCode() + answer.hashCode();
        Iterator<Line> iter = lines.iterator();
        while(iter.hasNext()){
            result += iter.next().hashCode();
        };
        return (int)result;
    };

    public boolean equals(Object o){
        boolean result = o != null && o instanceof QueryAnswer;
        if(result){
            QueryAnswer queryAnswer = (QueryAnswer)o;
            result = query.equals(queryAnswer.query) &&
                     answer.equals(queryAnswer.answer) &&
                     lines.size() == queryAnswer.lines.size();
            Iterator<Line> iter = lines.iterator();
            while(result && iter.hasNext()){
                result = lines.contains(iter.next());
            }; 
        };
        return result;
    }

    public String toString(){
        StringBuffer result = new StringBuffer();
        result.append(query + " " + answer + ( ( answerTotal == 0 )? "\n" : "(" + answerTotal + ")\n" ) );
        Iterator<Line> iter = lines.iterator();
        while(iter.hasNext()){
            result.append(iter.next().toString());
        };
        return result.toString();
    }

//Commands

//Auxillary Methods and Constants
    protected void getQuery(String str, Int i){
        StringBuffer newQuery = new StringBuffer();
        //Here we assume that the first '?' we come to will be immediately after
        //the Query.
        while(i.index < str.length() && str.charAt(i.index) != '?'){
            skipWhitespace(str, i);
            if(i.index < str.length() && str.charAt(i.index) != '?'){
                newQuery.append(str.charAt(i.index));
                i.index += 1;
            };
        };
        if(i.index < str.length() && str.charAt(i.index) == '?'){
            newQuery.append("?");
            i.index += 1;
        };
        query = newQuery.toString();
    }

    protected void getAnswer(String str, Int i){
        skipWhitespace(str, i);
        if(i.index < str.length()){
            if(str.charAt(i.index) == 'n' || str.charAt(i.index) == 'N'){
                answer = "No";
                i.index +=2;
            } else if(str.charAt(i.index) == 'y' || str.charAt(i.index) == 'Y'){
                answer = "Yes";
                i.index +=3;

                if( str.charAt( i.index ) == '(' ){
                	getAnswerTotal(str, i);
                }
                i.index = str.indexOf( "\n" , i.index );
                
            } else {
                answer = "";
            };
        };
    }

    protected void getAnswerTotal(String str, Int i){
    	i.index++;
    	
    	String intStr = "0";
    	while( Character.isDigit( str.charAt( i.index ) ) ){
    		intStr += str.charAt( i.index );
    		i.index++;
    	}
    	
    	answerTotal = Integer.parseInt( intStr );
    	
    	if( str.charAt( i.index ) == ')' ) i.index++;
    }
    
    protected void getLines(String str, Int i){
        lines = new HashSet<Line>();
        while(Line.atBeginningOfLine(str, i.index)){
            lines.add(new Line(str, i));
        };
    }

    public static void skipWhitespace(String str, Int i){
        while(i.index < str.length() &&
              Character.isWhitespace(str.charAt(i.index)))
        {
            i.index += 1;
        };
    };
}
