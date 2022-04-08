package project3.three;

import project3.lex.Lex;
import project3.lex.TokenType;

/**
 * A list of <b>Query</b>.
 *
 * <p>
 * Syntax: <b>QueryList</b> -&rarr; "Queries" ":" <b>Query</b>+
 * 
 * <p>
 * There are four properties that we need to define for a <b>QueryList</b>:
 *
 * <p>
 * <ol>
 *    <li>
 *    A <b>QueryList</b> contains a list of <b>Query</b>s.  It is
 *    implemented as an <b>ArrayList&lt;Node&gt;</b>.  We enforce the fact
 *    that it is a list of <b>Query</b> by only creating elements for the
 *    list using the <i>createNode(Lex)</i> method.  The <i>createNode(Lex)</i>
 *    method below creates only <b>Query</b>s. A <b>Query</b> is a
 *    specialization of <b>Node</b> so it can be added to a
 *    <b>ArrayList&lt;Node&gt;</b>Lj.
 *    </li>
 *
 *    <li>
 *    To determine whether to parse another <b>Query</b> and add it to the
 *    list we need to know whether lex.front() is in the first of
 *    <b>Query</b>.  This is implemented by the method
 *    <i>isInFirstOfListElement(Lex)</i>.  The default implementation defined in
 *    <b>List</b> checks to see if <i>lex.front()</i> is an instance of
 *    <i>Identifier</i>.  We do NOT override it here.
 *    </li>
 * 
 *    <li>
 *    A <b>QueryList</b> is a list of one-or-more elements.  This is the
 *    default value of a list and defined in <i>List::getLengthConstraint()</i>.
 *    We do NOT override it here.
 * 
 *    <li>
 *    The elements of a <b>QueryList</b>'s are NOT separated by anything.
 *    This is defined in <i>List.getSeparator()</i>.  We do NOT override
 *    it here.
 *    </li>
 * <ol>
 *
 * <pre>
 * Domain Definition:
 *     queryList : Sequence&lt;<b>Query</b>&gt;
 * 
 * Instance Invariant:
 *     |queryList| &gt;= 1
 * </pre>
 *
 * Last Modified: 21 Feb 2006
 *
 * @author Scott N. Woodfield
 * @version 1.0
 * @see
 *   <a href=
 *      "../../srchtml/project3/QueryList.html"
 *      >Source Code
 *   </a>
 */
public class QueryList
    extends DatalogSegment
{
//Domain Implementation
//  ArrayList<Nodes> nodes; -- implements Sequence<Query>
//                          -- inherited from List through DatalogSegment.

//Constructors
    /**
     * Creates a QueryList from the tokens at the front of lex.
     *
     * Postcondition:
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
     *       &exist;<sub>i</sub> (ValidQueryList(lex[0..i]) AND
     *         lex[i+1].getTokenType() = TokenType.EOF)
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *        super.Postcondition
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       &nbsp;
     *     </td>
     *     <td valign=top>
     *       name = "Queries"
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @throws ParserException
     *
     * @param lex - the lexical analyzer we will create the <b>QueryList</b>
     *              from.
     * @see
     *   <a href=
     *      "../../srchtml/project3/QueryList.html#LexConstructor"
     *      >Source Code
     *   </a>
     */
    public QueryList(Lex lex)
        throws ParserException
    {
        super(lex, TokenType.QUERIES);
    }

//Queries
    /**
     * Evaluating a list of queries to see if they are true, and, if so,
     * show all substitutions of the <i>Constant</i>s for the variables,
     * that made it true. The result is store in the strBuffer.
     *
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *       strBuffer &ne; null
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *        strBuffer = the results of evaluating each of the queries.
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     * @see
     *   <a href=
     *      "../../srchtml/project3/QueryList.html#evaluate"
     *      >Source Code
     *   </a>
     */
    public void evaluate(StringBuffer strBuffer)
        throws Exception
    {
        assert strBuffer != null;

        for(Node node : nodes){
            Query query = (Query)node;
            query.evaluate(strBuffer);
        };
    }

    /**
     * Gets the <i>i</i>th query from the list of queries.
     *
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *       0 &lt;= i &lt; |nodes|
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       result = queries[i]
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @param i - an index into the list of queries indicating which query you
     *            want
     *
     * @return the <i>i</i>th query in the list.
     * @see
     *   <a href=
     *      "../../srchtml/project3/QueryList.html#getQuery"
     *      >Source Code
     *   </a>
     */
    public Query getQuery(int i)
    {
        assert i >= 0 && i < nodes.size();

        return (Query)nodes.get(i);
    }

//Commands

//Auxillary Methods and Constants
    // Methods defined in List that need to be overridden here.

    /**
     * Creates and returns a Query which is a specialization of Node.
     *
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *       Query::Query(Lex).Precondition
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       Query:: Query(Lex).Postcondition
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
     *       result = new Query(lex)
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @throws ParserException
     *
     * @param lex - the lexical analyzer we will create a <b>Query</b> from.
     *
     * @return the <b>Query</b> we created (which is a special type of
     *         <b>Node</b>).
     * @see
     *   <a href=
     *      "../../srchtml/project3/QueryList.html#createNode"
     *      >Source Code
     *   </a>
     */
    protected Node createNode(Lex lex)
        throws ParserException
    {
        return new Query(lex);
    }
}
