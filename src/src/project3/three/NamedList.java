package project3.three;

import java.util.ArrayList;
import java.util.Iterator;

import project3.lex.Identifier;
import project3.lex.Lex;
import project3.lex.Token;
import project3.lex.TokenType;

/**
 * A <b>List</b> with an associated name.
 *
 * <p>
 * Six of the lists in the grammar are very similar.  The lists are:
 * <b>SchemeList</b>s, <b>FactList</b>s, <b>Fact</b>, <b>Scheme</b>, and
 * <b>Predicate</b>.  They all start with an <i>Identifier</i>, followed by a
 * parenthesized list of nodes of various types.  Since these lists all have
 * COMMA separators, we overload the <i>getSeparator(Lex)</i> method from
 * <b>List</b> to return a comma.
 *
 * <p>
 * There are four properties that we need to define for a NamedList:
 * <ol>
 *    <li>
 *    A <b>NamedList</b> contains a list of elements of type <b>T</b> which is
 *    a specialization of <b>Node</b>.  The list is implemented as an
 *    <b>ArrayList<Node></b>.  The actual type of object stored in the list
 *    is determined by the <i>createNode(Lex)</i> method which must insure
 *    that the node is an instant of <b>T</b>. This method is not defined here
 *    and must be overloaded in a specialization of <b>NamedList</b>.
 *    </li>
 *
 *    <li>
 *    To determine whether to parse another element of type <b>T</b> and add it
 *    to the list we need to know whether lex.front() is in the first of
 *    <b>T</b>.  This is implemented by the method
 *    <i>isInFirstOfListElement(Lex)</i>.  By default, this returns true if 
 *    lex.front() <i>instanceof</i> <i>Identifier</i>.
 *    </li>
 * 
 *    <li>
 *    A <b>NamedList</b> is a list of one-or-more elements.  This is the default
 *    value of a list and defined in <i>List::getLengthConstraint()</i>.
 *    We do NOT override it here.
 *    </li>
 * 
 *    <li>
 *    The elements of a <b>NamedList</b>'s list are separated by commas.
 *    This is defined in <i>getSeparator()</i> below.
 *    </li>
 *</ol>
 *
 * <pre>
 * Domain Definition
 *    name : Identifier name
 *    list : Sequence&lt;<b>T</b>&gt;
 *      -- where <b>T</b> is either <b>Scheme</b>, <b>Fact</b>, <b>Rule</b>, or <b>Query</b>
 *
 * Instance Invariant:
 *     name &ne; null AND
 *     |name| &gt; 0   AND
 *     |nodes| &gt; 0
 * </pre>
 *
 * Last Modified: 21 Feb 2006
 *
 * @author Scott N. Woodfield
 * @version 1.0
 * @see
 *   <a href=
 *      "../../srchtml/project3/NamedList.html"
 *      >Source Code
 *   </a>
 */
abstract public class NamedList
    extends List
{
//Domain Implementation
    // ArrayList<Node> nodes -- implements list
    //                          inherited from List

    /**
     * The identifier at the front of a named list.  Conceptually it is 
     * the name of a scheme.
     * @see
     *   <a href=
     *      "../../srchtml/project3/NamedList.html#name"
     *      >Source Code
     *   </a>
     */
    protected Identifier name;

//Constructors
    /**
     * Default constructor.
     *
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *       List::List().Precondition
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       List::List().Postcondition
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     * @see
     *   <a href=
     *      "../../srchtml/project3/NamedList.html#DefaultConstructor"
     *      >Source Code
     *   </a>
     */
    public NamedList()
    {
        super();
    }

    /**
     * The primary constructor.  It gets the name, removes the left parenthesis,
     * gets the list, and then removes the right parenthesis.
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
     *       lex.front().getTokenType() = TokenType.ID 
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
     *       lex[1].getTokenType() = TokenType.LEFT_PAREN 
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
     *       createList(lex.popFront().popFront()).precondition 
     *       the token right after the list is a ")" 
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       name = list.front() 
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
     *       createList(lex.popFront().popFront()).postCondition 
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
     *       list.front() is the Token immediately after the named lis.
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @param lex the lexical analyzer we will create a <b>NamedList</b> from.
     *
     * @throws ParserException
     * @see
     *   <a href=
     *      "../../srchtml/project3/NamedList.html#LexConstructor"
     *      >Source Code
     *   </a>
     */
    public NamedList(Lex lex)
        throws ParserException
    {
        name = (Identifier)checkFor(lex, TokenType.ID);
        checkFor(lex, TokenType.LEFT_PAREN);
        createList(lex);
        checkFor(lex, TokenType.RIGHT_PAREN);
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
     *       namedList &ne; null 
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
     *       name = new Token(namedList.name)
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @throws ParserException
     *
     * @param namedList the <b>NamedList</b> we will create a copy from.
     * @see
     *   <a href=
     *      "../../srchtml/project3/NamedList.html#CopyConstructor"
     *      >Source Code
     *   </a>
     */
    public NamedList(NamedList namedList)
        throws ParserException
    {
        super(namedList);
        name = new Identifier(namedList.name);
    }

//Queries
    /**
     * Converts the NamedList to a String.
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
     *       result = name + "(" + super.toString() + ")" 
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @return a String which represents the <b>NamedList</b>
     * @see
     *   <a href=
     *      "../../srchtml/project3/NamedList.html#toString"
     *      >Source Code
     *   </a>
     */
    public String toString()
    {
        StringBuffer result = new StringBuffer();
        result.append(name.getValue().toString());
        result.append("(");
        result.append(super.toString());
        result.append(")");
        return result.toString();
    }

    /**
     * The equals operator.
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
     *       result = object &ne; null
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
     *       object <i>instanceof</i> NamedList &rArr;
     *         &nbsp;&nbsp;&nbsp;|object.nodes| = |nodes| AND <br>
     *         &nbsp;&nbsp;&nbsp;&forall;<sub>i</sub>
     *           (0 &lt;= i AND i &lt; |nodes| &rArr;
     *           object.nodes[i].equals(nodes[i]))
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @param object the object we will see whether or not this object is
     *        equal to.
     *
     * @return whether this object is equal to the given object.
     * @see
     *   <a href=
     *      "../../srchtml/project3/NamedList.html#toString"
     *      >Source Code
     *   </a>
     */
    public boolean equals(Object object)
    {
        boolean result = object != null && object instanceof NamedList;
        if(result){
            NamedList namedList = (NamedList)object;
            result = name.equals(namedList.name) &&
                     nodes.size() == namedList.nodes.size();
            Iterator<Node> iter1 = nodes.iterator();
            Iterator<Node> iter2 = namedList.nodes.iterator();
            while(result && iter1.hasNext() && iter2.hasNext()){
                result = iter1.next().equals(iter2.next());
            };
        };
        return result;
    }

    /**
     * Getter for the name.
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
     *       result = name
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @return name The name of the <b>NamedList</b>L
     * @see
     *   <a href=
     *      "../../srchtml/project3/NamedList.html#getName"
     *      >Source Code
     *   </a>
     */
    public Identifier getName()
    {
        return name;
    }

//Commands

//Auxillary Methods and Constants
    //-------------------------------------------------------------------------
    //Methods of the class List that need to be overridden.
    //-------------------------------------------------------------------------
    /**
     * Returns the token Token.COMMA.  The general list, by default, assumes
     * that there are no separators between elements in a list.  But, all
     * NamedLists have commas between elements of the list.  To have the
     * <i>createList</i> method in the class <i>List</i> properly parse a
     * named list, we must override the <i>getSeparator()</i> method to
     * return Token.Comma.
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
     *       result = Token.COMMA
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @return Token In this case the COMMA token.
     * @see
     *   <a href=
     *      "../../srchtml/project3/NamedList.html#getSeparator"
     *      >Source Code
     *   </a>
     */
    protected Token getSeparator()
    {
        return Token.COMMA;
    }
}
