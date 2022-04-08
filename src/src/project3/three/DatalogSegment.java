package project3.three;

import project3.lex.Lex;
import project3.lex.TokenType;
import project3.lex.Token;

/**
 * A <b>DatalogSegment</b> is an abstract classs that is differentiated from
 * <b>List</b> by having a name. This class does not override any of the
 * methods defined in the <b>List</b> class.  Overriding will be done in the
 * specializations.
 *
 * <p>
 * <pre>
 * Domain Definition:
 *     nodes : Sequence&lt;<b>Node</b>&gt; nodes -- inherited from List
 *     name  : String name
 * </pre>
 *
 * Last Modified: 21 Feb 2006
 * @author Scott N. Woodfield
 * @version 1.0
 * @see
 *   <a href=
 *      "../../srchtml/project3/DatalogSegment.html"
 *      >Source Code
 *   </a>
 */
abstract public class DatalogSegment
    extends List
{
//Domain Implementations
//  ArrayList<Node> nodes; -- inherited from List

    /**
     * Every <b>DatalogSegment</b> has one of the following four names:
     * <i>Schemes</i>, <i>Facts</i>, <i>Rules</i>, or <i>Queries</i>.  
     * The name is set when the specialization is cretaed.  This name is
     * used for printing purposes.
     * @see
     *   <a href=
     *      "../../srchtml/project3/DatalogSegment.html#name"
     *      >Source Code
     *   </a>
     */
    protected String name;

//Constructor
    /**
     * Creates a DatalogSegment.
     *
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *       lex &ne; null <br/>
     *       lex.front().getTokenType() = expedtedTokenType <br/>
     *       lex.popFront().front().getTokenType() = TokenType.COMMA <br/>
     *       everything thereafter must be syntatically correct.
     *     </td>
     *     <td valign=top>
     *       AND <br/>
     *       AND <br/>
     *       &nbsp;
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       name = lex'.front().getValue() <br/>
     *       createList(lex'[1..|lex'|-1].Postcondition
     *     </td>
     *     <td valign=top>
     *       AND <br/>
     *       &nbsp;
     *     </td>
     *   </tr>
     * </table>
     *
     * @param lex the lexical analyzer we will read tokens from.
     * @param expectedTokenType the type of the token we expect to see at
     *                            lex[0]
     *
     * @throws ParserException
     * @see
     *   <a href=
     *      "../../srchtml/project3/DatalogSegment.html#StdConstructor"
     *      >Source Code
     *   </a>
     */
    public DatalogSegment(Lex lex, TokenType expectedTokenType)
        throws ParserException 
    {
        Token token = checkFor(lex, expectedTokenType);
        name = token.getValue();
        checkFor(lex, TokenType.COLON);
        createList(lex);
    }

//Queries
    /**
     * Creates a lexical version of a DatalogSegment.
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
     *       result = name + ":" + super.toString()
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @return The String representation of this DatalogSegment.
     * @see
     *   <a href=
     *      "../../srchtml/project3/DatalogSegment.html#toString"
     *      >Source Code
     *   </a>
     */
    public String toString()
    {
        StringBuffer result = new StringBuffer();
        result.append(name);
        result.append(":"); 
        result.append(System.getProperty("line.separator"));
        result.append(super.toString());
        return result.toString();
    }
}
