package project3.three;

import java.util.ArrayList;
import java.util.Iterator;

import project3.lex.Argument;
import project3.lex.Constant;
import project3.lex.Identifier;
import project3.lex.Lex;
import project3.lex.TokenType;
import project3.lex.Argument;

/**
 * A <b>Predicate</b> is a named list of <b>Argument</b>s.  An argument can be
 * either an <i>Identifier</i> or a <i>Constant</i>.
 *
 * <p>
 * Syntax: <b>Predicate</b> &rarr;
 *              <i>Identifier</i> "(" <b>Argument</b> [ "," <b>Argument</b> ]*
 *
 * <p>
 * There are four properties that we need to define for a <b>Predicate</b>
 *
 * <ol>
 *    <li>
 *    A <b>Predicate</b> contains a list of arguments.  It is implemented
 *    as an <b>ArrayList<Node></b>.  We enforce the fact that is is a list of
 *    arguments by only creating elements for the list using the
 *    <i>createNode(Lex)</i> method.  The <i>createNode(Lex)</i> method below
 *    creates only arguments. An <b>Argument</b> is an instance of type
 *    <b>Node</b> so it can be added to an ArrayList<Node>.
 *    </li>
 *
 *    <li>
 *    To determine whether to parse another <b>Argument</b> and add it to the
 *    list we need to know whether lex.front() is in the first of
 *    <b>Argument</b>.  This is implemented by the method
 *    <i>isInFirstOfListElement(Lex)</i> below.
 *    </li>
 * 
 *    <li>
 *    A <b>Predicate</b> is a list of one-or-more elements.  This is the
 *    default value of a list and defined in <i>List::getLengthConstraint()</i>.
 *    We do NOT override it here.
 *    </li>
 * 
 *    <li>
 *    The elements of a <b>Predicate</b>'s constant list are separated by 
 *    commas. This is defined in <i>NamedList::getSeparator()</i>.  We do NOT
 *    override it here.
 *    </li>
 * </ol>
 *
 * <pre>
 * Domain Definition:
 *    predicateName : Identifier 
 *    argumentList  : Sequence&lt;<b>Argument</b>&gt;
 *
 * Instance Invariant:
 *     predicateName &ne; null  AND
 *     |predicateName| &gt; 0 AND
 *     |argumentList| &gt; 0
 *
 * </pre>
 *
 * Last Modified: 21 Feb 2006
 *
 * @author Scott N. Woodfield
 * @version 1.0
 * @see
 *   <a href=
 *      "../../srchtml/project3/Predicate.html"
 *      >Source Code
 *   </a>
 */
public class Predicate
    extends NamedList
{
//Domain Implementation
    // Identifier name     -- inherited from NamedList
    //                     -- implements predicateName
    // ArrayList<Argument> -- inherited from List
    //                        implements Sequence<Argument>


//Constructors
    /**
     * Constructs a <b>Predicate</b> from the lexical analyzer.
     *
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *       NamedList::NamedList(Lex).Precondition
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
     *       &forall;<sub>x</sub>
     *         (nodes.contains(x) &rArr; x <i>instanceof</i> <b>Argument</b>)
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @throws ParserException
     *
     * @param lex the lexical analyzer we will create a <b>Predicate</b>
     *        from.
     * @see
     *   <a href=
     *      "../../srchtml/project3/Predicate.html#LexConstructor"
     *      >Source Code
     *   </a>
     */
    public Predicate(Lex lex)
        throws ParserException
    {
        super(lex);
    }

    /**
     * The copy constructor.
     *
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *       NamedList::NamedList(NamedList).Precondition
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       NamedList::NamedList(NamedList).Postcondition 
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @throws ParserException
     *
     * @param predicate the <b>Predicate</b> we will copy.
     * @see
     *   <a href=
     *      "../../srchtml/project3/Predicate.html#CopyConstructor"
     *      >Source Code
     *   </a>
     */
    public Predicate(Predicate predicate)
        throws ParserException
    {
        super();
        Iterator<Node> iter = predicate.nodes.iterator();
        while(iter.hasNext()){
            nodes.add(Argument.createCopy((Argument)iter.next()));
        };
        name = new Identifier(predicate.name);
    }

//Queries
    /**
     * Checks to see if all of the arguments are <i>Identifier</i>s.
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
     *       result = &forall;<sub>x</sub>  (x &isin; nodes &rArr; 
     *                x <i>instanceof</i> <i>Constant</i>)
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @return whether all of the nodes are <i>Constant</i>s.
     * @see
     *   <a href=
     *      "../../srchtml/project3/Predicate.html#allConstants"
     *      >Source Code
     *   </a>
     */
    public boolean allConstants()
    {
        boolean result = true;
        Iterator<Node> iter = nodes.iterator();
        while(result && iter.hasNext()){
            result = iter.next() instanceof Constant;
        };
        return result;
    }

    /**
     * Returns a hash code for this predicate.
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
     *        result = (int) &sum; 0 &lt;= i &lt; |nodes| (nodes[i].hashCode)
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @return the hash code for this <b>Predicate</b>
     * @see
     *   <a href=
     *      "../../srchtml/project3/Predicate.html#hashCode"
     *      >Source Code
     *   </a>
     */
    public int hashCode()
    {
        long sum = 0;
        Iterator<Node> iter = nodes.iterator();
        while(iter.hasNext()){
            sum += (long)((Argument)iter.next()).hashCode();
        };
        int result = (int)sum;
        return result;
    }

//Commands
    /**
     * Sets the value of the <b>Argument</b> at location <i>index</i> to value.
     *
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *       index &gt; 0 
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
     *       index &lt; nodes.size()
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
     *       value &ne; null
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       |nodes| = |nodes'|
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
     *       nodes[0..index-1] = nodes'[0..index-1]
     *     </td>
     *     <td valign=top>
     *       AND
     *     </td>
     *   <tr>
     *     <td valign=top>
     *       &nbsp;
     *     </td>
     *     <td valign=top>
     *       nodes[index+1..|nodes|-1] = nodes'[index+1..|nodes'|]
     *     </td>
     *     <td valign=top>
     *       AND
     *     </td>
     *   <tr>
     *     <td valign=top>
     *       &nbsp;
     *     </td>
     *     <td valign=top>
     *       nodes[index] = value
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @param index an index into the list of <b>Argument</b>s.
     * @param value the value we are to save at location "index".
     * @see
     *   <a href=
     *      "../../srchtml/project3/Predicate.html#set"
     *      >Source Code
     *   </a>
     */
    public void set(int index, Constant value)
    {
         nodes.set(index, value);
    }


//Auxillary Methods and Constants
    //Methods that need to be overridden to define a Predicate

    /**
     * Creates an argument.
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
     *       lex.front() <i>instanceof</i> Argument
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       result = lex.front() 
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
     *       |lex| = |lex'| - 1 
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
     *       lex = lex'[1..|lex'|-1]
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @throws ParserException
     *
     * @param lex the lexical analyzer we will get an <b>Argument</b> from.
     *
     * @return the <b>Argument</b> we just created.
     * @see
     *   <a href=
     *      "../../srchtml/project3/Predicate.html#createNode"
     *      >Source Code
     *   </a>
     */
    protected Node createNode(Lex lex)
        throws ParserException
    {
        if(!(lex.front() instanceof Argument))throw new ParserException(
            "ERROR in Predicate::createNode(Lex)\n" +
            "   the lex.front() must be either a identifier or a constant");
        
        Node result = lex.front();
        lex.popFront();
        return result;
    }

    /**
     * Checks to see if lex.front() is an <i>Identifier</i> or <i>Constant</i>.
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
     *       result = lex.front() <i>instanceof</i> <b>Argument</b>
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @param lex the lexical analyzer we will check to see if lex[0] is in
     *              the first of <b>Argument</b>.
     *
     * @return whether lex[0] is in the first of <b>Argument</b>.
     * @see
     *   <a href=
     *      "../../srchtml/project3/Predicate.html#isInFirstOfListElement"
     *      >Source Code
     *   </a>
     */
    protected boolean isInFirstOfListElement(Lex lex)
    {
        assert lex != null;

        return lex.front() instanceof Argument;
    }
}
