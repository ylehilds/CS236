package project3.three;

import java.util.ArrayList;
import java.util.Iterator;

import project3.lex.Lex;
import project3.lex.TokenType;

/**
 * A list of <b>Fact</b>.
 *
 * <p>
 * Syntax: <b>FactList</b> &raar; "Facts" ":" <b>Fact</b>*
 * 
 * <p>
 * There are four properties that we need to define for a <b>FactList</b>:
 * <br/>
 * <ol>
 *    <li>
 *    A <b>FactList</b> contains a list of <b>Fact</b>s.  It is
 *    implemented as an <b>ArrayList&lt;Node&gt;</b>.  We enforce the fact
 *    that it is a list of <b>Fact</b> by only creating elements for the
 *    list using the <i>createNode(Lex)</i> method.  The <i>createNode(Lex)</i>
 *    method below creates only <b>Fact</b>s. A <b>Fact</b> is a
 *    specialization of <b>Node</b> so it can be added to a
 *    <b>ArrayList&lt;Node&gt;</b>Lj.
 *    </li>
 *
 *    <li>
 *    To determine whether to parse another <b>Fact</b> and add it to the
 *    list we need to know whether lex.front() is in the first of
 *    <b>Fact</b>.  This is implemented by the method
 *    <i>isInFirstOfListElement(Lex)</i>.  The default implementation defined in
 *    <b>List</b> checks to see if <i>lex.front()</i> is an instance of
 *    <i>Identifier</i>.  We do NOT override it here.
 *    </li>
 * 
 *    <li>
 *    A <b>FactList</b> is a list of zero-or-more elements.  This is defined
 *    in the <i>List::getLengthConstraint</i> below.
 *    </li>
 * 
 *    <li>
 *    The elements of a <b>FactList</b>'s are NOT separated by anything.
 *    This is defined in <i>List.getSeparator()</i>.  We do NOT override
 *    it here.
 *    </li>
 * </ol>
 *
 * <pre>
 * Domain Definition:
 *     factList : Set&lt;<b>Fact</b>&gt;
 * 
 * Instance Invariant:
 *     |factList| &gt;= 0
 * </pre>
 *
 * Last Modified: 21 Feb 2006
 * @author Scott N. Woodfield
 * @version 1.0
 * @see
 *   <a href=
 *      "../../srchtml/project3/FactList.html"
 *      >Source Code
 *   </a>
 */
public class FactList
    extends DatalogSegment
{
//Domain Implementation
//  ArrayList<Node> nodes; -- implements Set<Fact>
//                         -- inherited from List through DatalogSegment.

//Constructors
    /**
     * Creates a FactList from the tokens at the front of lex.
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
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       &nbsp;
     *     </td>
     *     <td valign=top>
     *       &exist; <sub>i</sub> (ValidFactList(lex[0..i]) 
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
     *       lex[i+1].getTokenType() = TokenType.RULES 
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *         super.PostCondition() 
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
     *       name = "Facts"
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @throws ParserException
     *
     * @param lex the lexical analyzer we will create a <b>Fact</b> from.
     * @see
     *   <a href=
     *      "../../srchtml/project3/FactList.html#LexConstructor"
     *      >Source Code
     *   </a>
     */
    public FactList(Lex lex)
        throws ParserException
    {
        super(lex, TokenType.FACTS); 
    }

//Queries
    /**
     * Returns true if and only if there exists a fact in this list of facts
     * that is equal to the <b>Predicate</b> parameter.
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
     *       result = &exist; <sub>f</sub> (nodes.contains(f) AND f = predicate)
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     * @see
     *   <a href=
     *      "../../srchtml/project3/FactList.html#canProve"
     *      >Source Code
     *   </a>
     */
    public boolean canProve(Predicate predicate)
    {
        boolean result = false;
        Iterator<Node> iter = nodes.iterator();
        while(!result && iter.hasNext()){
            Fact fact = (Fact)iter.next();
            result = fact.equals(predicate);
        };
        return result;
    }

//Commands

//Auxillary Methods and Constants
    // Methods defined in List that need to be overridden here.

    /**
     * Creates and returns a Fact which is a specialization of Node.
     *
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *       Fact(Lex).Precondition
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       Fact::Fact(Lex).Postcondition 
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
     *       result = new Fact(lex)
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @throws ParserException
     * @param lex the lexical Analyzer we will create a <b>Fact</b> from.
     * @return a <b>Fact</b>.
     * @see
     *   <a href=
     *      "../../srchtml/project3/FactList.html#canProve"
     *      >Source Code
     *   </a>
     */
    public Node createNode(Lex lex)
        throws ParserException
    {
        return new Fact(lex);
    }

    /**
     * Returns LengthConstraint.ZERO_OR_MORE.
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
     *       result = LengthConstraint.ZERO_OR_MORE
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @return a LengthConstraint indicating is zero or more.
     * @see
     *   <a href=
     *      "../../srchtml/project3/FactList.html#getLengthConstraint"
     *      >Source Code
     *   </a>
     */
    protected LengthConstraint getLengthConstraint()
    {
        return LengthConstraint.ZERO_OR_MORE;
    }
}
