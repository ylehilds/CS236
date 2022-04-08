package project3.three;

import project3.lex.Lex;
import project3.lex.Token;
import project3.lex.TokenType;

/**
 * The parser builds a parse tree of nodes.  The tree is actually built from
 * special types of nodes.  The special types of nodes are: <b>List</b>,
 * <b>DatalogProgram</b>, <b>Rule</b>, and <b>Token</b>.  The other nodes in
 * the program are specializations of <b>List</b>.  This class has no
 * attributes.  It has the method "checkFor" which is used by most of its
 * children.
 *
 * <pre>
 * Domain Implementation:
 *     -- none
 * </pre>
 *
 * Last Modified: 21 Feb 2006
 *
 * @author Scott N. Woodfield
 * @version 1.0
 * @see
 *   <a href=
 *      "../../srchtml/project3/Node.html"
 *      >Source Code
 *   </a>
 */
abstract public class Node
{
    /**
     * checkFor determines if lex.front() has the expected TokenType. If so
     * returns the next token and pops lex.  If not, it throws an exception.
     * This is a method used in almost all classes and is written at this
     * level so we only have to write it once.
     *
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *       lex &ne; null
     *     </td>
     *     <td valign=top>
     *       AND
     *     </td>
     *   <tr>
     *     <td valign=top>
     *       &nbsp;
     *     </td>
     *     <td valign=top>
     *       lex.front() == expectedTokenType
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       expectedTokenType = null &rArr; result = null 
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
     *       expectedTokenType &ne; null &rArr;
     *         result = lex'.front() AND lex = lex'.popFront()
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @throws ParserException
     *
     * @param lex We are going to try and get the next token from lex.
     * @param expectedTokenType The token type that lex[0] must be.
     *
     * @return the next token in <b>Lex</b> if it is of the proper type.
     * @see
     *   <a href=
     *      "../../srchtml/project3/Node.html#checkFor"
     *      >Source Code
     *   </a>
     */
    public Token checkFor(Lex lex, TokenType expectedTokenType)
        throws ParserException
    {
        assert lex != null;

        Token result = null;
 
        if(expectedTokenType == null){
           //do nothing
        } else if(lex.front().getTokenType() == expectedTokenType){
            result = lex.front();
            lex.popFront();
        } else {
            throw new ParserException(lex.front());
        };
        return result;
    }
}
