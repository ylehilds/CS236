package project3.lex;

import project3.three.Node;
import project3.three.ParserException;

/**
 * A <i>Constant</i> token. This is not needed for assignment 1, but is
 * included in the <i>lex</i> package to make it easier to read the code
 * in project 3.
 *
 * <p>
 * <pre>
 * Sytnax: "'" <b>Non-QuoteCharacter</b>* [["'" "'"]+
 *             <b>Non-QuoteCharacter</b>]* "'"
 * </pre>
 *
 * <p>
 * <pre>
 * Domain Definition:
 *     type       : TokenType
 *     value      : String
 *     lineNumber : int
 *
 * Instance Invariant:
 *     type = TokenType.STRING
 * </pre>
 *
 * Last Modified: 10 Jan 2006
 *
 * @author Scott N. Woodfield
 * @version 1.0
 * @see <a href="../../srchtml/lex/Constant.html">Source Code</a>
 */
public class Constant 
    extends Argument
{
//Constants
//  TokenType type       -- inherited from Token through Argument
//  String    value      -- inherited from Token through Argument
//  int       lineNumber -- inherited from Token through Argument

//Domain Implementation -- Inherited from Token

//Constructors
    /**
     * Constructs a <i>Constant</i> from a type, value, and lineNumber
     *
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *       Token.Precondition
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       Token.Postcondition
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @param value the value of the constant.
     * @param lineNumber the line number this token was found on.
     *
     * @throws ParserException
     * @see <a href="../../srchtml/lex/Constant.html#StdConstructor">Source Code</a>
     */
    public Constant(String value, int lineNumber)
        throws ParserException
    {
        super(TokenType.STRING, value, lineNumber);
    }

    /**
     * Constructs a <i>Constant</i> from a <b>Token</b>.
     *
     * <table>
     * <p>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *       token <i>instanceof</i> <b>Constant</b>
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       this.equals(token)
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @param token the constant <b>Token</b> we will copy from.
     * @see <a href="../../srchtml/lex/Constant.html#CopyConstructor">Source Code</a>
     */
    public Constant(Token token)
        throws ParserException
    {
        super(token);
        assert token instanceof Constant;
    }

//Queries
    /**
     * Convert the constant to a string.
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
     *       result =  "'" + value + "'"
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     * @return the lexical representation of the <b>Constant</b>.
     * @see <a href="../../srchtml/lex/Constant.html#toString">Source Code</a>
     */
    public String toString()
    {
         return "'" + value + "'";
    }
}
