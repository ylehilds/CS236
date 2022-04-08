package project3.lex;

import project3.three.Node;
import project3.three.ParserException;

/**
 * A lexical analyzer produces a list of tokens.  This defines a token.
 * A Token consists of a type, value, and lineNumber.  We inherit from
 * <b>project3.Node</b> so that we can add tokens to constant lists,
 * attribute lists, or argument lists.
 *
 * <p>
 * <pre>
 * Domain:
 *      type       : TokenType
 *      value      : String
 *      lineNumber : int
 *
 * Instance Invariant:
 *      type &ne; null AND
 *      value &ne; null AND
 *      type = TokenType.EOF &rArr; value = ""
 * </pre>
 *
 * Last Modified: 2 Mar 2006
 * @author Scott N. Woodfield
 * @version 1.0
 * @see <a href="../../srchtml/lex/Token.html">Source Code</a>
 */
public class Token 
    extends Node
{
//Constants

    /**
     * The COMMA Token.  Used when we need a comma token but don't care about
     * the line number.  This token is currently used in
     * {@link project3.three.NamedList#getSeparator()
     *      project3.three.NamedList.getSeparator()}
     * and in
     * {@link project3.PredicateList#getSeparator()
     *      project3.PredeicateList.getSeparator()}. 
     * @see <a href="../../srchtml/lex/Token.html#COMMA">Source Code</a>
     */
    public static final Token COMMA = new Token(TokenType.COMMA, ",");

//Domain Implementation
    /**
     * The type of the token. Accessed by "getTokenType".  It cannot be modified
     * after the token has been created.
     * @see <a href="../../srchtml/lex/Token.html#type">Source Code</a>
     */
    protected TokenType type  = TokenType.UNDEFINED;

    /**
     * The value of the token.  The sequence of characters from the input stream
     * that were parsed to create this token.  Some tokens with the same
     * token type will always have the same value.  For instance, tokens of
     * type TokenType.SEMICOLON will always have the value ";".  Other tokens,
     * such as strings and identifiers can have different values.  Tokens of
     * type EOF will have the "" or empty string value.
     * @see <a href="../../srchtml/lex/Token.html#value">Source Code</a>
     */
    protected String    value = null;

    /**
     * The line on which the first character of the token was found.
     * @see <a href="../../srchtml/lex/Token.html#lineNumber">Source Code</a>
     */
    protected int    lineNumber = -1;

//Constructors
    /**
     * Constructs a token from a type and value.  Used primarily to create
     * constants.
     *
     * <p>
     * <table>
     *   <tr>
     *     <td> <b>Precondition</b>: </td>
     *     <td> None</td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>PostCondition</b>:
     *     </td>
     *     <td valign=top> 
     *       this.type = type<br/>
     *       this.type = TokenType.EOF &rArr; this.value = ""<br/>
     *       this.type &ne; TokenType.EOF &rArr; this.value = value<br/>
     *       this.lineNumber = -1
     *     </td>
     *     <td valign=top>
     *       AND <br/>
     *       AND <br/>
     *       AND <br/>
     *       &nbsp;
     *     </td>
     *   </tr>
     * </table>
     * @param type the type of the token.  
     * @param value the value of the token.
     * @see <a href="../../srchtml/lex/Token.html#StdConstructor">Source Code</a>
     */
    private Token(TokenType type, String value)
    {
        this.type = type;
        if(type == TokenType.EOF){
           this.value = "";
        } else {
           this.value = value;
        };

        this.lineNumber = -1;
    }

    /**
     * Constructs a token from a type, value, and lineNumber.
     *
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *         <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *       checkConstructorPrecondition()
     *     </td>
     *     <td>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *         <b>PostCondition</b>:
     *     </td>
     *     <td valign=top>
     *       this.type = type <br/>
     *       this.type = TokenType.EOF &rArr; this.value = "" <br/>
     *       this.type &ne; TokenType.EOF &rArr; this.value = value <br/>
     *       this.lineNumber = lineNumber
     *     </td>
     *     <td valign=top>
     *       AND<br/>
     *       AND<br/>
     *       AND<br/>
     *       &nbsp;
     *     </td>
     *   </tr>
     * </table>
     *
     * @param type the type of the token.  
     * @param value the value of the token.
     * @param lineNumber the lineNumber of the token.
     *
     * @throws ParserException
     * @see <a href="../../srchtml/lex/Token.html#StdConstructor2">Source Code</a>
     */
    public Token(TokenType type, String value, int lineNumber)
        throws ParserException 
    {
        checkConstructorPrecondition(type, value, lineNumber);

        this.type = type;
        if(type == TokenType.EOF){
           this.value = "";
        } else {
           this.value = value;
        };

        this.lineNumber = lineNumber;
    }

    /**
     * The copy constructor.
     *
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *         <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *         token &ne; null
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *         <b>PostCondition</b>:
     *     </td>
     *     <td valign=top>
     *       type = token.type <br/>
     *       value = new String(token.value)<br/>
     *       lineNumber = token.lineNumber
     *     </td>
     *     <td valign=top>
     *       AND<br/>
     *       AND<br/>
     *       &nbsp;
     *     </td>
     *   </tr>
     * </table>
     *
     * @param token the token to copy.
     * @see <a href="../../srchtml/lex/Token.html#CopyConstructor">Source Code</a>
     */
    public Token(Token token)
    {
        assert token != null;

        type = token.type;
        value = token.value == null ? null : new String(token.value);
        lineNumber = token.lineNumber;
    }

//Queries
    /**
     *Compares two Tokens to see if they are equal. Notice that we only use
     * the type and value to determine if two tokens are equal. We ignore the
     * line number.
     *
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *       none
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>PostCondition</b>:
     *     </td>
     *     <td valign=top>
     *       result = object &ne; null <br/>
     *       object <i>instanceof</i> Token <br/>
     *       type = object.type <br/>
     *       value = object.value 
     *     </td>
     *     <td valign=top>
     *       AND<br/>
     *       AND<br/>
     *       AND<br/>
     *       &nbsp;
     *     </td>
     *   </tr>
     * </table>
     *
     * @param object the object we are comparing this to
     *
     * @return true iff the objects are equal
     * @see <a href="../../srchtml/lex/Token.html#equals">Source Code</a>
     */
    public boolean equals(Object object)
    {
        boolean result = object != null && object instanceof Token;
        if(result){
            Token token = (Token)object;
            result = type == token.type &&
                     ((value ==  null && token.value == null) ||
                      (value != null && value.equals(token.value))
                     );
        };
        return result;
    }

    /**
     * The getter method for type.
     * 
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *       none
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>PostCondition</b>:
     *     </td>
     *     <td valign=top>
     *       result = type
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @return the type
     * @see <a href="../../srchtml/lex/Token.html#getTokenType">Source Code</a>
     */
    public TokenType getTokenType()
    {
        return type;
    }

    /**
     * The getter method for value.
     * 
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *       none
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>PostCondition</b>:
     *     </td>
     *     <td valign=top>
     *       type = TokenType.STRING &rArr; result = "'" + value + "'" <br/>
     *       type &ne; STRING &rArr; result = value
     *     </td>
     *     <td valign=top>
     *       AND<br/>
     *       &nbsp
     *     </td>
     *   </tr>
     * </table>
     *
     * @return the value
     * @see <a href="../../srchtml/lex/Token.html#getValue">Source Code</a>
     */
    public String getValue()
    {
        String result = null;
        if(type == TokenType.STRING){
             result = '\'' + value + '\'';
        } else {
             result = value;
        };
        return result;
    }

    /**
     * The getter method for lineNumber.
     * 
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *       none
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>PostCondition</b>:
     *     </td>
     *     <td valign=top>
     *       result = lineNumber
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @return the lineNumber
     * @see <a href="../../srchtml/lex/Token.html#getLineNumber">Source Code</a>
     */
    public int getLineNumber()
    {
        return lineNumber;
    }

    /**
     * Returns the string value of this token.
     *
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *       none
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       result = "(" + type + ", \"" + value + ", \")" + lineNumber + ")"
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @return a string representing this token
     * @see <a href="../../srchtml/lex/Token.html#toString">Source Code</a>
     */
    public String toString()
    {
        StringBuffer result = new StringBuffer();
        result.append("(");
        result.append(type);
        result.append(", ");
        result.append(value);
        result.append(", ");
        result.append(lineNumber);
        result.append(")");
        return value;
    }

    /**
     * The hash value for a token.
     * 
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *       none
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       result = value.hashCode()
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @return the hash Value
     * @see <a href="../../srchtml/lex/Token.html#hashCode">Source Code</a>
     */
    public int hashCode()
    {
        return value.hashCode() + type.hashCode();
    }

//Auxillary Methods and Tokens
    /**
     * Checks the precondition of the non-copy constructors.  Since we only
     * check preconditions, the only purpose of this method is to throw an
     * exception if an error is found.  Otherwise it does nothing.
     * 
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *       type &ne; null <br/>
     *       lineNumber &gt;= 1 <br/>
     *       type &ne; TokenType.EOF &rArr; value &ne; null <br/>
     *       type &ne; TokenType.EOF AND type &ne; TokenType.STRING &rArr;
     *            |value| &gt; 0 <br/>
     *       type == TokenType.STRING &rArr; (isLetter(value[0]) <br/>
     *       &forall;<sub>i</sub> (0 &lt;= i AND i &lt; |value| &rArr;
     *           (isLetterOrDigit(value[i]))) <br/>
     *       type == TokenType.SCHEMES &rArr; value.equalsIgnoreCase(
     *           "Schemes") <br/>
     *       type == TokenType.Facts &rArr; value.equalsIgnoreCase("Facts")<br/>
     *       type == TokenType.Rules &rArr; value.equalsIgnoreCase("Rules")<br/>
     *       type == TokenType.Rules &rArr; value.equalsIgnoreCase("Queries")
     *           <br/>
     *       type == TokenType.COLON_DASH &rArr; value = ":-" <br/>
     *       type == TokenType.COLON      &rArr; value = ":"  <br/>
     *       type == TokenType.COMMA      &rArr; value = ","  <br/>
     *       type == TokenType.PERIOD     &rArr; value = "."  <br/>
     *       type == TokenType.LEFT_PAREN &rArr; value = "(" <br/>
     *       type == TokenType.RIGHT_PAREN&rArr; value = ")" <br/>
     *       type == TokenType.Q_MARK     &rArr; value = "?" <br/>
     *       lineNumber &gt;= 1
     *     </td>
     *     <td valign=top>
     *       AND<br/>
     *       AND<br/>
     *       AND<br/>
     *       AND<br/>
     *       AND<br/>
     *       AND<br/>
     *       AND<br/>
     *       AND<br/>
     *       AND<br/>
     *       AND<br/>
     *       AND<br/>
     *       AND<br/>
     *       AND<br/>
     *       AND<br/>
     *       AND<br/>
     *       AND<br/>
     *       AND<br/>
     *       &nbsp;
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>PostCondition</b>:
     *     </td>
     *     <td valign=top>
     *       true
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *  
     * </table>
     *
     * @param type  the token type to be checked.
     * @param value the value of the new token.
     * @param lineNumber the lineNumber of the new token
     *
     * @throws ParserException
     * @see <a href="../../srchtml/lex/Token.html#constructorPrecond">Source Code</a>
     */
    protected void checkConstructorPrecondition(
        TokenType type, String value, int lineNumber)
        throws ParserException
    {
        String errorMessage = null;
        if(type == null){
            errorMessage = "type cannot be null";
        } else if(type != TokenType.EOF && value == null){ 
            errorMessage = "type != TokenType.EOF => value != null";
        } else if(type != TokenType.EOF && type != TokenType.STRING &&
                  value.length() == 0
                 ){ 
            errorMessage = "If the token is not an EOF or String token " +
                            "then |value| > 0";
        } else if(type == TokenType.ID &&
                  !(Character.isLetter(value.charAt(0)) &&
                    allTheRestMustBeLettersOrDigits(value))){
            errorMessage = "type = TokenType.STRING =>\n" +
                            "    the first character in value must be a letter"+
                            " and\n" +
                            "    the rest must be letters or digits";
        } else if(type == TokenType.SCHEMES &&
                  !value.equalsIgnoreCase("Schemes")){
            errorMessage = "type = TokenType.SCHEMES =>\n" +
                            "    value.equalsIgnoreCase(\"Schemes\")";
        } else if(type == TokenType.FACTS &&
                  !value.equalsIgnoreCase("Facts")){ 
            errorMessage = "type = TokenType.FACTS =>\n" +
                            "    value.equalsIgnoreCase(\"Facts\")";
        } else if(type == TokenType.RULES &&
                  !value.equalsIgnoreCase("Rules")){ 
            errorMessage = "type = TokenType.RULES =>\n" +
                            "    value.equalsIgnoreCase(\"Rules\")";
        } else if(type == TokenType.QUERIES &&
                  !value.equalsIgnoreCase("Queries")){ 
            errorMessage = "type = TokenType.QUERIES =>\n" +
                            "    value.equalsIgnoreCase(\"Queries\")";
        } else if(type == TokenType.COLON_DASH && !value.equals(":-")){
            errorMessage = "type = TokenType.COLON_DASH => value = \":-\""; 
        } else if(type == TokenType.COLON && !value.equals(":")){
            errorMessage = "type = TokenType.COLON => value = \":\""; 
        } else if(type == TokenType.COMMA && !value.equals(",")){
            errorMessage = "type = TokenType.COMMA => value = \",\""; 
        } else if(type == TokenType.PERIOD && !value.equals(".")){
            errorMessage = "type = TokenType.PERIOD => value = \".\""; 
        } else if(type == TokenType.LEFT_PAREN && !value.equals("(")){
            errorMessage = "type = TokenType.LEFT_PAREN => value = \"(\""; 
        } else if(type == TokenType.RIGHT_PAREN && !value.equals(")")){
            errorMessage = "type = TokenType.RIGHT_PAREN => value = \")\""; 
        } else if(type == TokenType.Q_MARK && !value.equals("?")){
            errorMessage = "type = TokenType.Q_MARK => value = \"?\""; 
        } else if(lineNumber <= 0){
            errorMessage = "lineNumber must be >= 1";
        };
        if(errorMessage != null)
            throw new ParserException(
               "ERROR in one of the Token::constructor\n" + errorMessage);
    }

    /**
     * Returns true if value[1..|value|-1] are all letters or digits, otherwise
     * it returns false.
     *
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *       none
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       result = value &ne; null <br/>
     *       &forall;<sub>i</sub> (0 &lt; i AND i &lt; |value| &rArr;
     *           isLetterOrDigit(str[i]))
     *     </td>
     *     <td valign=top>
     *       AND <br/>
     *       &nbsp;
     *     </td>
     *   </tr>
     * </table>
     *
     * @param value the string to be inspected.
     *
     * @return whether or not all but the first character are letters and
     *         digits.
     * @see <a href="../../srchtml/lex/Token.html#lettersOrDigits">Source Code</a>
     */
    protected boolean allTheRestMustBeLettersOrDigits(String value)
    {
        boolean result = value != null;

        for(int i = 1; result && i < value.length(); i++){
            result = Character.isLetterOrDigit(value.charAt(i));
        };

        return result;
    }
}
