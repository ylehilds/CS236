package answerParser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashSet;

public class Line
{
//Domain Implementation
    protected HashSet<Sub> subs;
//Constructors
    public Line(String str, Int i){
        subs = new HashSet<Sub>();
        if(Sub.atBeginningOfSub(str, i.index)){
            subs.add(new Sub(str, i));
            while(Sub.anotherSub(str, i)){
                i.index += 1; //skipping the ','
                subs.add(new Sub(str, i));
            };
        };
    }
//Queries
    public static boolean atBeginningOfLine(String str, int i){
        return Sub.atBeginningOfSub(str, i);
    }

    public int hashCode(){
        long result = 0;
        Iterator<Sub> iter = subs.iterator();
        while(iter.hasNext()){
            result += iter.next().hashCode();
        };
        return (int)result;
    }

    public boolean equals(Object o){
        boolean result = o != null && o instanceof Line;
        if(result){
            Line line = (Line)o;
            result = line.subs.size() == subs.size();
            Iterator<Sub> iter = subs.iterator();
            while(result && iter.hasNext()){
                result = subs.contains(iter.next());
            };
        };
        return result;
    }

    public String toString(){
        StringBuffer result = new StringBuffer();
        result.append( "  " );
        Iterator<Sub> iter = subs.iterator();
        if(iter.hasNext()){
            result.append(iter.next().toString());
            while(iter.hasNext()){
                result.append(", ");
                result.append(iter.next().toString());
            };
        };
        result.append("\n");
        return result.toString();
    };
}
