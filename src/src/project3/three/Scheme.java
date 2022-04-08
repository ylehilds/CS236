package project3.three;

import project3.lex.Lex;
import project3.lex.TokenType;
import project3.lex.Identifier;

/**
 * A scheme is a named list of Constants. All of the identifiers in the
 * list must be unique.
 *
 * <p>
 * Syntax: <b>Scheme</b> -&rarr; <i>Identifier</i>
 *             "(" <i>Identifier</i> ["," <i>Identifier</i>]* ")"
 *
 *
 * There are four properties that we need to define for a <b>Scheme</b>:<br>
 *
 * <ol>
 *    <li>
 *    A <b>Scheme</b> contains a list of identifiers.  It is implemented as an
 *    <b>ArrayList<Node></b>.  We enforce that fact that is is a list of
 *    identifiers by only creating elements for the list using the
 *    <i>createNode(Lex)</i> method.  The <i>createNode(Lex)</i> method below
 *    creates only identifiers. A <i>Identifier</i> is an instance of type
 *    <b>Node</b> so it can be added to an <b>ArrayList<Node></b>.
 *    </li>
 *
 *    <li>
 *    To determine whether to parse another <i>Identifier</i> and add it to the
 *    list we need to know whether lex.front() is in the first of
 *    <i>Identifier</i>.  This is the default value of a list.  It is
 *    defined in the method <i>List::isInFirstOfListElement(Lex)</i>.
 *    We do NOT override it here.
 *    </li>
 * 
 *    <li>
 *    A <b>Scheme</b> is a list of one-or-more elements.  This is the default
 *    value of a list and defined in <i>List::getLengthConstraint</i>.
 *    We do NOT override it here.
 *    </li>
 * 
 *    <li>
 *    The elements of a <b>Scheme</b>'s identifier list are separated by commas.
 *    This is defined in <i>NamedList.getSeparator()</i>.  We do NOT override
 *    it here.
 *    </li>
 * </ol>
 *
 * <pre>
 * Domain Definition
 *    schemeName    : Identifier schemeName
 *    attributeList : Sequence&lt;<i>Identifier</i>&gt;
 *
 * Instance Invariant:
 *    schemeName &ne; null  AND
 *    |schemeName| &gt; 0    AND
 *    |atrributeList| &gt; 0 AND
 *    &forall; <sub>i</sub> (0 &lt;= i AND i &lt; |attributeList| &rArr;
 *        &forall; <sub>j</sub>(0 &lt;= j AND j &lt; |attributeList| &rArr;
 *            i &ne; j &rArr; attributeList[i] &ne; attributeList[j]))
 *    
 * </pre>
 *
 * Last Modified: 21 Feb 2006
 *
 * @author Scott N. Woodfield
 * @version 1.0
 * @see
 *   <a href=
 *      "../../srchtml/project3/Scheme.html"
 *      >Source Code
 *   </a>
 */
public class Scheme
    extends NamedList
{
//Domain Implementation
//  Identifier name       -- inherited from NamedList, impliments attributeName
//  ArrayList<Node> nodes -- inherited from List through NamedList,
//                        -- implements attributeList

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
     *       AND
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       &nbsp;
     *     </td>
     *     <td valign=top>
     *       all of the attribute names are unique
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
     *       &forall; <sub>i</sub> (0 &lt;= i AND i &lt; |attributeList| &rArr;
     *       &forall; <sub>j</sub>(0 &lt;= j AND j &lt; |attributeList| &rArr;
     *            i &ne; j &rArr; attributeList[i] &ne; attributeList[j]))
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @throws ParserException
     *
     * @param lex the lexical analyzer we will create a <b>Scheme</b> from.
     * @see
     *   <a href=
     *      "../../srchtml/project3/Scheme.html#LexConstructor"
     *      >Source Code
     *   </a>
     */
    public Scheme(Lex lex)
        throws ParserException
    {
        super(lex);

        boolean allUnique = true;
        for(int i = 0; allUnique && i < nodes.size()-1; i++){
            for(int j = i + 1; allUnique && j < nodes.size(); j++){
                allUnique = ! (nodes.get(i).equals(nodes.get(j)));
            };
        };
        if(!allUnique)
            throw new ParserException(
               "ERROR in Scheme::Scheme(Lex)\n" +
               "    All of the identifiers in the scheme declaration must " +
               "be unique.");
    }

//Queries

//Commands

//Auxillary Methods and Constants
    //Methods defined in List that are overridden to define a Scheme.

    /**
     * Creates a <b>Node</b> of type <i>Identifier</i>.
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
     *       result = checkFor(lex, TokenType.ID).postCondition
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @throws ParserException
     *
     * @param lex the lexical analyzer we will create an <i>Identifier</i>
     *              from.
     *
     * @return a <b>Node</b> which is really an <i>Identifier</i>
     *         (An <i>Identifier</i> is a special type of <b>Node</b>).
     * @see
     *   <a href=
     *      "../../srchtml/project3/Scheme.html#createNode"
     *      >Source Code
     *   </a>
     */
    protected Node createNode(Lex lex)
        throws ParserException
    {
        return checkFor(lex, TokenType.ID);
    }
}
