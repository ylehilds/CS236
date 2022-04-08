package project3.three;

import project3.lex.Token;

/**
 * A special type of exception for parser problems.
 *
 * <pre>
 * Domain Implementation:
 *     token : Token 
 * </pre>
 *
 * Last Modified: 21 Feb 2006
 *
 * @author Scott N. Woodfield
 * @version 1.0
 * @see
 *   <a href=
 *      "../../srchtml/project3/ParserException.html"
 *      >Source Code
 *   </a>
 */
public class ParserException
    extends Exception
{
//Domain Implementation
    /**
     * A Parser Exception differes from a standard Exception in that it can
     * remember the token that caused the exception.
     * @see
     *   <a href=
     *      "../../srchtml/project3/ParserException.html#token"
     *      >Source Code
     *   </a>
     */
    protected Token token = null;

//Constructors
    /**
     * Creates a ParserException from a Token (which can be null).
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
     *       this.token = token
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     * </pre>
     *
     * @param token the token to be saved.
     * @see
     *   <a href=
     *      "../../srchtml/project3/ParserException.html#TokenConstructor"
     *      >Source Code
     *   </a>
     */
    public ParserException(Token token)
    {
        this.token = token;
    }

    /**
     * Creates a ParserException from a String.  This just calls the same
     * constructor in the Exception super class.
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
     *       super.postcondition
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @param str the error message to be saved.
     * @see
     *   <a href=
     *      "../../srchtml/project3/ParserException.html#StringConstructor"
     *      >Source Code
     *   </a>
     */
    public ParserException(String str)
    {
        super(str);
    }

//Queries
    /**
     * The getter for the token attribute.
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
     *       result = token
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @return the token of this exception.
     * @see
     *   <a href=
     *      "../../srchtml/project3/ParserException.html#getToken"
     *      >Source Code
     *   </a>
     */
    public Token getToken()
    {
        return token;
    }

    /**
     * Converts this Exception to a string.
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
     *       token = null &rArr; result = getMessage()
     *     </td>
     *     <td valign=top>
     *       AND
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       &nbsp;
     *     </td>
     *     <td valign=top>
     *       token &ne; null &rArr; result = "Parser Error for " +
     *                                     token.toString()
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     * </pre>
     *
     * @return the string representation of the parser exception.
     * @see
     *   <a href=
     *      "../../srchtml/project3/ParserException.html#toString"
     *      >Source Code
     *   </a>
     */
    public String toString()
    {
        String result = null;
        if(token == null){
            result = getMessage();
        } else {
            result = "Parser Error for " + token.toString();
        };
        return result;
    }
}
