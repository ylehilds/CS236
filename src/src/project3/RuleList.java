package project3;

import java.sql.Savepoint;
import java.util.HashSet;
import java.util.Iterator;

import project3.lex.Lex;
import project3.lex.TokenType;
import project3.lex.Identifier;

import project3.three.*;

/**
 * A list of <b>Rule</b>.
 *
 * <p>
 * Syntax: <b>RuleList</b> -&rarr; "Rules" ":" <b>Rule</b>*
 *
 * <p>
 * There are four properties that we need to define for a <b>RuleList</b>:
 *
 * <p>
 * <ol>
 *    <li>
 *    A <b>RuleList</b> contains a list of <b>Rule</b>s.  It is
 *    implemented as an <b>ArrayList&lt;Node&gt;</b>.  We enforce the fact
 *    that it is a list of <b>Rule</b> by only creating elements for the
 *    list using the <i>createNode(Lex)</i> method.  The <i>createNode(Lex)</i>
 *    method below creates only <b>Rule</b>s. A <b>Rule</b> is a
 *    specialization of <b>Node</b> so it can be added to a
 *    <b>ArrayList&lt;Node&gt;</b>Lj.
 *    </li>
 *
 *    <li>
 *    To determine whether to parse another <b>Rule</b> and add it to the
 *    list we need to know whether lex.front() is in the first of
 *    <b>Rule</b>.  This is implemented by the method
 *    <i>isInFirstOfListElement(Lex)</i>.  The default implementation defined in
 *    <b>List</b> checks to see if <i>lex.front()</i> is an instance of
 *    <i>Identifier</i>.  We do NOT override it here.
 *    </li>
 * 
 *    <li>
 *    A <b>RuleList</b> is a list of zero-or-more elements.  This is the
 *    defined in the <i>List::getLengthConstraint</i> below.
 *    </li>
 * 
 *    <li>
 *    The elements of a <b>RuleList</b>'s are NOT separated by anything.
 *    This is defined in <i>List.getSeparator()</i>.  We do NOT override
 *    it here.
 *    </li>
 * </ol>
 *
 * <pre>
 * Domain Definition:
 *     ruleList : Sequence&lt;<b>Rule</b>&gt;
 * 
 * Instance Invariant:
 *     |ruleList| &gt;= 0
 * </pre>
 *
 * Last Modified: 21 Feb 2006
 *
 * @author Scott N. Woodfield
 * @version 1.0
 * @see
 *   <a href=
 *      "../srchtml/project3/RuleList.html"
 *      >Source Code
 *   </a>
 */
public class RuleList
    extends DatalogSegment
{

    /**
     *  Determines if we can prove the <b>Predicate</b> correct by at least
     *  one <b>Rule</b> in the list of rules.
     *
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *       predicate &ne; null
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       previouslySeenPredicates.contains(predicate) &rArr; result = false
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
     *       &not; previouslySeenPredicates.contains(predicate) &rArr;
     *         result = &exist;<sub>r</sub> (r &isin; nodes &rArr;
     *           r.prove(predicate))
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @throws ParserException
     *
     * @param predicate the <b>Predicate</b> we are going to try and prove.
     *
     * @return true iff we can prove the <b>Predicate</b> using at least one
     *         <b>Rule</b>.
     * @see
     *   <a href=
     *      "../srchtml/project3/RuleList.html#canProve"
     *      >Source Code
     *   </a>
     */
    public boolean canProve(Predicate predicate) throws ParserException
    {
//System.out.println("Modify RuleList.canProve");
        assert predicate != null;
        boolean result = false;
        if (previouslySeenPredicates.contains(predicate)) // detects infinite recursion by checking with map if it has it already
        {													// if not add it, rule prove, then remove from map.		
        	return false;
        }
        previouslySeenPredicates.add(predicate);
        Iterator<Node> iter = nodes.iterator();
        while(!result && iter.hasNext()){
        	Rule rule = (Rule)iter.next();
        	result = rule.prove(predicate);
        }
        	previouslySeenPredicates.remove(predicate);
        	return result;
    }


  

////////////////////////////////////////////////////////////////////////////////////////
// THE REST OF THE CLASS IS DOWN HERE.
// YOU'LL NEED TO READ ABOUT THE CLASS VARIABLES AND METHODS.
// GOTO THE PROJECT3-API TO READ SOMETHING MORE CLEAR.
////////////////////////////////////////////////////////////////////////////////////////



//Domain Implementation
//  ArrayList<Node> nodes; -- implements Sequence<Rule>
//                         -- inherited from List through DatalogSegment.

//Constructors
    /**
     * Creates a RuleList from the tokens at the front of lex.
     *
     * </pre>
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
     *       &exist;<sub>i</sub> (ValidRuleList(lex[0..i]) AND
     *                         lex[i+1].getTokenType() = TokenType.FACTS 
     *                        )
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       super.Precondition
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
     *       name = "Rules"
     *     </td>
     *     <td valign=top>
     *       AND
     *     </td>
     *   </tr>
     * </table>
     *
     * @throws ParserException
     *
     * @param lex the lexical analyzer we will create the <b>RuleList</b> from.
     * @see
     *   <a href=
     *      "../srchtml/project3/RuleList.html#LexConstructor"
     *      >Source Code
     *   </a>
     */
    public RuleList(Lex lex)
        throws ParserException
    {
        super(lex, TokenType.RULES);
    }

//Commands

//Auxillary Methods and Constants
    /**
     * A list of predicates that we have seen earlier in the recursion.
     * This helps us detect infinite recursion.
     * @see
     *   <a href=
     *      "../srchtml/project3/RuleList.html#previouslySeenPredicates"
     *      >Source Code
     *   </a>
     */
    protected HashSet<Predicate> previouslySeenPredicates =
        new HashSet<Predicate>();   

    // Methods defined in List that need to be overridden here.
    /**
     * Creates and returns a Rule which is a specialization of Node.
     *
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *       Rule::Rule(Lex).Precondition
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       Rule:: Rule(Lex).Postcondition
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
     *       result = new Rule(lex)
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @throws ParserException
     *
     * @param lex the lexical analyzer we will create a <b>Rule</b> from.
     *
     * @return the <b>Rule</b> we created (which is a special type of
     *              <b>Node</b>).
     * @see
     *   <a href=
     *      "../srchtml/project3/RuleList.html#createNode"
     *      >Source Code
     *   </a>
     */
    protected Node createNode(Lex lex)
        throws ParserException
    {
        return new Rule(lex);
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
     * @return a <b>LengthConstraint</b> which in this case is zero or more.
     * @see
     *   <a href=
     *      "../srchtml/project3/RuleList.html#getLengthConstraint"
     *      >Source Code
     *   </a>
     */
    protected LengthConstraint getLengthConstraint()
    {
        return LengthConstraint.ZERO_OR_MORE;
    }
}
