package project3.lex;

import project3.three.Node;
import project3.three.ParserException;

/**
 * An <i>Identifier</i> token.  This is not needed for assignment 1, but is
 * included in the <i>lex</i> package to make it easier to read the code
 * in project 3.
 *
 * <p>
 * Syntax: <b>Letter</b> [<b>Letter</b> | <b>Digit</b> ]*
 *
 * <p>
 * <pre>
 * Domain Definition:
 *     type       : TokenType -- inherited from Token
 *     value      : String    -- inherited from Token
 *     lineNumber : int       -- inherited from Token
 *
 * Instance Invariant:
 *     type = TokenType.ID
 * </pre>
 * 
 * Last Modified: 21 Feb 2006
 *
 * @author Scott N. Woodfield
 * @version 1.0
 * @see <a href="../../srchtml/lex/Identifier.html">Source Code</a>
 */
public class Identifier 
    extends Argument
{
//Domain Implementation
//  TokenType type       -- inherited from Token through Argument
//  String    value      -- inherited from Token through Argument
//  int       lineNumber -- inherited from Token through Argument

//Constructors
    /**
     * Constructs an <i>Identifier</i> from a value and lineNumber.
     *
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *       <b>Token</b>.Precondition
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       <b>Token</b>.Postcondition(TokenType.ID, value, lineNumber)
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @param value the value of the <i>Identifier</i>.
     * @param lineNumber the line number of the <i>Identifier</i>.
     *
     * @throws ParserException
     * @see <a href="../../srchtml/lex/Identifier.html#StdConstructor">Source Code</a>
     */
    public Identifier(String value, int lineNumber)
        throws ParserException 
    {
        super(TokenType.ID, value, lineNumber);
    }

    /**
     * Constructs an <i>Identifier</i> from another <i>Identifier</i>.
     *
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *       token <i>instanceof</i> <b>Identifier</b>
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
     * @see
     *     <a href="../../srchtml/lex/Identifier.html#CopyConstructor">Source Code</a>
     */
    public Identifier(Token token)
    {
        super(token);
        assert token instanceof Identifier;
    }

//Queries
    /**
     * Convert the Identifier to a string.
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
     *       result = value
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     * return the lexical representation of the <b>Identifier</b>
     * @see
     *     <a href="../../srchtml/lex/Identifier.html#toString">Source Code</a>
     */
    public String toString()
    {
        return value;
    }
}
