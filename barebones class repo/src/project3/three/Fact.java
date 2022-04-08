package project3.three;

import project3.lex.Lex;
import project3.lex.Token;
import project3.lex.TokenType;
import project3.lex.Constant;

/**
 * A <b>Fact</b> is a <b>NamedList</b> of <i>Constant</i>s.
 *
 * <p>
 * Syntax: <b>Fact</b> &rarr; <i>Identifier</i> "(" <i>Constant</i>
 * ["," <i>Constant</i>]* ")" "."
 *
 * <p>
 * There are four properties that we need to define for a Fact:
 * <br/>
 * <ol>
 *    <li>
 *    A Fact contains a list of constants.  It is implemented as an
 *    <b>ArrayList&lt;Node&gt;</b>.  We enforce that fact that is is a list of
 *    constants by only creating elements for the list using the
 *    <i>createNode(Lex)</i> method.  The <i>createNode(Lex)</i> method below
 *    creates only constants. A <b>Constant</b> is an instance of type
 *    <b>Node</b> so it can be added to an ArrayList&lt;Node&gt;.
 *    </li>
 *
 *    <li>
 *    To determine whether to parse another <i>Constant</i> and add it to the
 *    list we need to know whether lex.front() is in the first of
 *    <i>Constant</i>.  This is implemented by the method
 *    <i>isInFirstOfListElement(Lex)</i> below.
 *    </li>
 * 
 *    <li>
 *    A <b>Fact</b> is a list of one-or-more elements.  This is the default
 *    value of a list and defined in <i>List::getLengthConstraint</i>.
 *    We do NOT override it here.
 *    </li>
 * 
 *    <li>
 *    The elements of a Fact's constant list are separated by commas.
 *    This is defined in <i>NamedList.getSeparator()</i>.  We do NOT override
 *    it here.
 *    </li>
 * </ol>
 *
 * <pre>
 * Domain Definition
 *    attributeName : <i>Identifier </i>
 *    constantList  : Sequence&lt;<i>Constant</i>&gt;
 *
 * Instance Invariant:
 *     factName &ne; null AND
 *     |factName| &gt; 0 AND
 *     |atrributeList| &gt; 0
 *
 * </pre>
 * Last Modified: 21 Feb 2006
 * @author Scott N. Woodfield
 * @version 1.0
 * @see
 *   <a href=
 *      "../../srchtml/project3/Fact.html"
 *      >Source Code
 *   </a>
 */
public class Fact
    extends NamedList
{
//Domain Implementation
//  Identifier name -- inherited from NamedList, impliments attributeName
//  ArrayList&lt;Node&gt; nodes -- inherited from NamedList from List,
//                        -- implements constantList

//Constructors
    /**
     * The standard constructor from lex.
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
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       NamedList::NamedList(Lex).Postcondition 
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
     *       &forall <sub>x</sub> (nodes.contains(x) &rArr; Domain.contains(x))
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @throws ParserException
     *
     * @param lex the lexical analyzer we will read from.
     * @see
     *   <a href=
     *      "../../srchtml/project3/Fact.html#LexConstructor"
     *      >Source Code
     *   </a>
     */
    public Fact(Lex lex)
        throws ParserException
    {
        super(lex);
        checkFor(lex, TokenType.PERIOD);

        for(Node node : nodes){
            Domain.add((Constant)node);
        };
    }

//Queries
    /**
     * Converts a <b>Fact</b> to a String.
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
     *       result = super.toString() + "."
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @return the string representation of a <b>Fact</b>
     * @see
     *   <a href=
     *      "../../srchtml/project3/Fact.html#toString"
     *      >Source Code
     *   </a>
     */
    public String toString()
    {
        return super.toString() + ".";
    }

//Commands

//Auxillary Methods and Constants
    //Methods defined in List that are overridden to define a Fact.
    /**
     * Creates a node that is a constant.
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
     *       lex.front() <i>instanceof</i> Constant
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       result = checkFor(lex, TokenType.ID).postCondition
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @throws ParserException
     *
     * @param lex the lexical analyzer we will create a <b>Node</b> from.
     *
     * @return the new node created from lex.
     * @see
     *   <a href=
     *      "../../srchtml/project3/Fact.html#createNode"
     *      >Source Code
     *   </a>
     */
    protected Node createNode(Lex lex)
        throws ParserException
    {
        //The lexical analyzer returns a Constant with TokenType == STRING
        return checkFor(lex, TokenType.STRING);
    }

    /**
     * This defines the first of a <b>Fact</b>, which is a <i>Constant</i>.
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
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       result = lex.front() <i>instanceof</i> Constant
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
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @param lex the lexical analyzer we will check to see if lex[0] is a 
     *        <i>Constant</i>.
     *
     * @return whether lex[0] is a constant.
     * @see
     *   <a href=
     *      "../../srchtml/project3/Fact.html#isInFirstElement"
     *      >Source Code
     *   </a>
     */
    protected boolean isInFirstOfListElement(Lex lex)
    {
        assert lex != null;

        return lex.front() instanceof Constant;
    }
}
