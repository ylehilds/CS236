package answerParser;

import java.util.ArrayList;
import java.util.Iterator;

public class Answers
{
//Domain Implementation
    protected ArrayList<QueryAnswer> queryAnswers;

//Constructor
    public Answers(String str){
        queryAnswers = new ArrayList<QueryAnswer>();
        Int i = new Int(0);
        while(QueryAnswer.anotherQueryAnswer(str, i.index)){
            queryAnswers.add(new QueryAnswer(str, i));
        };
    };

//Queries
    public boolean equals(Object o){
        boolean result = o != null && o instanceof Answers;
        if(result){
            Answers answers = (Answers)o;
            result = queryAnswers.size() == answers.queryAnswers.size();

            Iterator<QueryAnswer> iter1 = queryAnswers.iterator();        
            Iterator<QueryAnswer> iter2 = answers.queryAnswers.iterator();
            while(result && iter1.hasNext()){
                result = iter1.next().equals(iter2.next());
            };
        };
        return result;
    }

    public String toString(){
        StringBuffer result = new StringBuffer();
        Iterator<QueryAnswer> iter = queryAnswers.iterator();        
        while(iter.hasNext()){
            result.append(iter.next().toString());
            result.append("\n");
        };
        return result.toString();
    };
}
