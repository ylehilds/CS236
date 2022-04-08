package project3.three;

import project3.lex.Lex;
import project3.lex.TokenType;

/**
 * A list of <b>Scheme</b>.<br>
 *
 * <p>
 * Syntax <b>SchemeList</b> -&rarr; "Schemes" ":" <b>Scheme</b>+
 *
 * 
 * <p>
 * There are four properties that we need to define for a <b>SchemeList</b>:
 *
 * <p>
 * <ol>
 *    <li>
 *    A <b>SchemeList</b> contains a list of <b>Scheme</b>s.  It is
 *    implemented as an <b>ArrayList&lt;Node&gt;</b>.  We enforce the fact
 *    that it is a list of <b>Scheme</b> by only creating elements for the
 *    list using the <i>createNode(Lex)</i> method.  The <i>createNode(Lex)</i>
 *    method below creates only <b>Scheme</b>s. A <b>Scheme</b> is a
 *    specialization of <b>Node</b> so it can be added to a
 *    <b>ArrayList&lt;Node&gt;</b>Lj.
 *    </li>
 *
 *    <li>
 *    To determine whether to parse another <b>Scheme</b> and add it to the
 *    list we need to know whether lex.front() is in the first of
 *    <b>Scheme</b>.  This is implemented by the method
 *    <i>isInFirstOfListElement(Lex)</i>.  The default implementation defined in
 *    <b>List</b> checks to see if <i>lex.front()</i> is an instance of
 *    <i>Identifier</i>.  We do NOT override it here.
 *    </li>
 * 
 *    <li>
 *    A <b>SchemeList</b> is a list of one-or-more elements.  This is the
 *    default value of a list and defined in <i>List::getLengthConstraint</i>.
 *    We do NOT override it here.
 *    </li>
 * 
 *    <li>
 *    The elements of a <b>SchemeList</b>'s are NOT separated by anything.
 *    This is defined in <i>List.getSeparator()</i>.  We do NOT override
 *    it here.
 *    </li>
 * </ol>
 *
 * <pre>
 * Domain Definition:
 *     schemeList : Sequence&lt;<b>Scheme</b>&gt;
 * 
 * Instance Invariant:
 *     |schemeList| &gt;= 1
 * </pre>
 *
 * Last Modified: 21 Feb 2006
 *
 * @author Scott N. Woodfield
 * @version 1.0
 * @see
 *   <a href=
 *      "../../srchtml/project3/SchemeList.html"
 *      >Source Code
 *   </a>
 */
public class SchemeList
    extends DatalogSegment
{
//Domain Implementation
//  ArrayList<Nodes> nodes; -- implements Sequence<Scheme>
//                          -- inherited from List through DatalogSegment.

//Constructors
    /**
     * Creates a SchemeList from the tokens at the front of lex.
     *
     * Postcondition:
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
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       &nbsp;
     *     </td>
     *     <td valign=top>
     *       &exist;<sub>i</sub>
     *         (ValidSchemeList(lex[0..i]) AND
     *                         lex[i+1].getTokenType() = TokenType.FACTS)
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       super.Postcondition
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
     *       name = "Schemes"
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @throws ParserException
     *
     * @param lex the lexical analyzer we will create a <b>SchemeList</b>
     *        from.
     * @see
     *   <a href=
     *      "../../srchtml/project3/SchemeList.html#LexConstructor"
     *      >Source Code
     *   </a>
     */
    public SchemeList(Lex lex)
        throws ParserException
    {
        super(lex, TokenType.SCHEMES);
    }

//Queries

//Commands

//Auxillary Methods and Constants
    // Methods defined in List that need to be overridden here.

    /**
     * Creates and returns a Scheme which is a specialization of Node.
     *
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *       Scheme::Scheme(Lex).Precondition
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       Scheme:: Scheme(Lex).Postcondition 
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
     *       result = new Scheme(lex)
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @throws ParserException
     *
     * @param lex the lexical analyzer we will create a <b>Scheme</b> from.
     *
     * @return a <b>Scheme</b> which is a special kind of <b>Node</b>
     * @see
     *   <a href=
     *      "../../srchtml/project3/SchemeList.html#createNode"
     *      >Source Code
     *   </a>
     */
    protected Node createNode(Lex lex)
        throws ParserException
    {
        return new Scheme(lex);
    }
}
