package answerParser;

public class Sub
{
//Domain Implementation
    protected String variableName;
    protected String constantValue;
//Constructors
    public Sub(String str, Int i){
        QueryAnswer.skipWhitespace(str, i);
        getIdentifier(str, i);        
        QueryAnswer.skipWhitespace(str, i);
        i.index += 1; //Skip the "="
        QueryAnswer.skipWhitespace(str, i);
        getConstant(str, i);        
    };
//Queries
    public static boolean atBeginningOfSub(String str, int index){
        boolean result = false;
        Int i = new Int(index);
        QueryAnswer.skipWhitespace(str, i);
        if(i.index < str.length() && Character.isLetter(str.charAt(i.index))){
            while(i.index < str.length() &&
                  Character.isLetterOrDigit(str.charAt(i.index)))
            {
                i.index += 1;
            };
            QueryAnswer.skipWhitespace(str, i);
            if(i.index < str.length()){
                result = str.charAt(i.index) == '=';
            };
        }
        return result;
    }

    public int hashCode(){
        long result = variableName.hashCode() + constantValue.hashCode();
        return (int)result;
    }

    public boolean equals(Object o){
        boolean result = o != null && o instanceof Sub;
        if(result){
            Sub sub = (Sub)o;
            result = variableName.equals(sub.variableName) &&
                     constantValue.equals(sub.constantValue);
        };
        return result;
    }

    public static boolean anotherSub(String str, Int i){
        QueryAnswer.skipWhitespace(str, i);
        boolean result = i.index < str.length() &&
                         str.charAt(i.index) == ',';
        return result;
    }

    public String toString(){
        StringBuffer result = new StringBuffer();
        result.append(variableName);
        result.append("=\'");
        result.append(constantValue);
        result.append("\'");
        return result.toString();
    }

//Commands

//Auxillary Methods and Constants

    protected void getIdentifier(String str, Int i){
        StringBuffer result = new StringBuffer();
        while(i.index < str.length() &&
            Character.isLetterOrDigit(str.charAt(i.index)))
        {
            result.append(str.charAt(i.index));
            i.index += 1;
        };
        variableName = result.toString();
    };

    protected void getConstant(String str, Int i){
        StringBuffer result = new StringBuffer();
        i.index += 1; //skip the single quote
        //We are assumeing there are no embedded quotes
        while(i.index < str.length() && str.charAt(i.index) != '\''){
            result.append(str.charAt(i.index));
            i.index += 1;
        };
        i.index +=1; //skip the second single quote
        constantValue = result.toString();
    };
};
