package project3.three;

import project3.lex.Lex;
import project3.lex.TokenType;
import project3.RuleList;

/**
 * The Parser portion of the Datalog Program.
 *
 * <p>
 * Syntax:
 * <table>
 * <tr>
 *   <td><b>DataLogProgram</b></td>
 *   <td>&rarr;</td>
 *   <td><b>SchemeList</b></td>
 * </tr>
 * <tr>
 *   <td>&nbsp;</td>
 *   <td>&nbsp;</td>
 *   <td><b>FactList</b></td>
 * </tr>
 * <tr>
 *   <td>&nbsp;</td>
 *   <td>&nbsp;</td>
 *   <td><b>RuleList</b></td>
 * </tr>
 * <tr>
 *   <td>&nbsp;</td>
 *   <td>&nbsp;</td>
 *   <td><b>QueryList</b></td>
 * </tr>
 * <tr>
 *   <td><b>SchemeList</b></td>
 *   <td>&rarr;</td>
 *   <td>"Schemes" ":" <b>Scheme</b>+</td>
 * </tr>
 * <tr>
 *   <td><b>Scheme</b></td>
 *   <td>&rarr;</td>
 *   <td><i>Identifier</i> "(" <i>Identifier</i> ["," <i>Identifier</i>]* ")"</td>
 * </tr>
 * <tr>
 *   <td><b>FactList</b></td>
 *   <td>&rarr;</td>
 *   <td>"Facts" ":" <b>Fact</b>*</td>
 * </tr>
 * <tr>
 *   <td><b>Fact</b></td>
 *   <td>&rarr;</td>
 *   <td><i>Identifier</i> "(" <i>Constant</i> ["," <i>Constant</i>]* ")" "."</td>
 * </tr>
 * <tr>
 *   <td><b>RuleList</b></td>
 *   <td>&rarr;</td>
 *   <td>"Rules" ":" <b>Rule</b>*</td>
 * </tr>
 * <tr>
 *   <td><b>Rule</b></td>
 *   <td>&rarr;</td>
 *   <td><b>Head</b> ":-" <b>PredicateList</b> "."</td>
 * </tr>
 * <tr>
 *   <td><b>Head</b></td>
 *   <td>&rarr;</td>
 *   <td><b>Scheme</b></td>
 * </tr>
 * <tr>
 *   <td><b>PredicateList</b></td>
 *   <td>&rarr;</td>
 *   <td><b>Predicate</b> ["," <b>Predicate</b> ]*</td>
 * </tr>
 * <tr>
 *   <td><b>Predicate</b></td>
 *   <td>&rarr;</td>
 *   <td><i>Identifier</i> "(" <b>Argument</b> [ "," <b>Argument</b> ]* ")"</td>
 * </tr>
 * <tr>
 *   <td><b>Argument</b></td>
 *   <td>&rarr;</td>
 *   <td><i>Identifier</i> | <i>Constant</i></td>
 * </tr>
 * <tr>
 *   <td><b>QueryList</b></td>
 *   <td>&rarr;</td>
 *   <td>"Queries" ":" <b>Query<b>*</td>
 * </tr>
 * <tr>
 *   <td><b>Query</b></td>
 *   <td>&rarr;</td>
 *   <td><b>Predicate</b> "?"</td>
 * </tr>
 * <table>
 *
 * <pre>
 * Domain Definition:
 *     schemeList: SchemeList
 *     factList  : FactList
 *     ruleList  : RuleList
 *     queryList : QueryList
 * <pre>
 * Last Modified: 21 Feb 2006
 * @author Scott N. Woodfield
 * @version 1.0
 * @see <a href="../../srchtml/project3/DatalogProgram.html">Source Code</a>
 */
public class DatalogProgram
    extends Node
{
//Domain Implementation
    /**
     * The schemeList implementation.
     * @see
     *   <a href=
     *      "../../srchtml/project3/DatalogProgram.html#schemeList">Source Code</a>
     */
    protected SchemeList schemeList;

    /**
     * The factList implementation.
     * @see
     *   <a href=
     *      "../../srchtml/project3/DatalogProgram.html#factList">Source Code</a>
     */
    protected FactList factList;

    /**
     * The ruleList implementation.
     * @see
     *   <a href=
     *      "../../srchtml/project3/DatalogProgram.html#ruleList">Source Code</a>
     */
    protected RuleList ruleList;

    /**
     * The queryList implementation.
     * @see
     *   <a href=
     *      "../../srchtml/project3/DatalogProgram.html#queryList">Source Code</a>
     */
    protected QueryList queryList;

//Constructors
    /**
     * Creates a datalog program from the tokens in the lexical analyzer.
     *
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *       lex &ne; null <br/>
     *       lex is a syntatically valid datalog program
     *     </td>
     *     <td valign=top>
     *       AND <br/>
     *       &nbsp;
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       schemeList = new SchemeList(lex) <br/>
     *       lex' = lex after creating schemeList <br/>
     *       factList   = new FactList(lex')   <br/>
     *       lex'' = lex' after creating schemeList <br/>
     *       ruleList   = new RuleList(lex'')   <br/>
     *       lex''' = lex'' after creating schemeList <br/>
     *       queryList  = new QueryList(lex''')
     *     </td>
     *     <td valign=top>
     *       AND <br/>
     *       AND <br/>
     *       AND <br/>
     *       AND <br/>
     *       AND <br/>
     *       AND <br/>
     *       &nbsp;
     *     </td>
     *   </tr>
     * </table>
     *
     * @throws ParserException
     *
     * @param lex the lexical analyzer we will create a <b>DatalogProgram</b>
     *        from.
     * @see
     *   <a href=
     *      "../../srchtml/project3/DatalogProgram.html#LexConstructor"
     *      >Source Code
     *   </a>
     */
    public DatalogProgram(Lex lex)
        throws ParserException
    {
        Domain.reset();
        schemeList = new SchemeList(lex);
        factList = new FactList(lex);
        ruleList = new RuleList(lex);
        queryList = new QueryList(lex);

        checkFor(lex, TokenType.EOF);
    }

//Queries
    /**
     * Converts the datalog program to a string.  Used for debugging purposes.
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
     *       result = schemeList.toString() + factList.toString() +
     *                ruleList.toString()   + queryList.toString()
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @return the string representation of a <b>DatalogProgram</b>.
     * @see
     *   <a href=
     *      "../../srchtml/project3/DatalogProgram.html#toString"
     *      >Source Code
     *   </a>
     */
    public String toString()
    {
        StringBuffer result = new StringBuffer();
        result.append(schemeList.toString());
        result.append(factList.toString());
        result.append(ruleList.toString());
        result.append(queryList.toString());
        return result.toString();
    }

    /**
     * Returns the factList part of a datalog program. This is the getter for
     * factList.
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
     *       result = factList
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @return the factList of this <b>DatalogProgram</b>
     * @see
     *   <a href=
     *      "../../srchtml/project3/DatalogProgram.html#getFactList"
     *      >Source Code
     *   </a>
     */
    public FactList getFactList()
    {
        return factList;
    }

    /**
     * Returns the ruleList part of a datalog program.
     *
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
     *       result = ruleList
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @return the ruleList of this <b>DatalogProgram</b>
     * @see
     *   <a href=
     *      "../../srchtml/project3/DatalogProgram.html#getRuleList"
     *      >Source Code
     *   </a>
     */
    public RuleList getRuleList()
    {
        return ruleList;
    }

    /**
     * Returns the queryList part of the program, used for primarily for
     * testing.
     *
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
     *       result = queryList
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @return the queryList of this <b>DatalogProgram</b>
     * @see
     *   <a href=
     *      "../../srchtml/project3/DatalogProgram.html#getQueryList"
     *      >Source Code
     *   </a>
     */
    public QueryList getQueryList()
    {
        return queryList;
    }


//Commands
    /**
     * Evaluates all of the queries in the program.
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
     *       the results of all the evaluated queries are stored in
     *       strBuffer.
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     * 
     * @throws Exception
     *
     * @param strBuffer we store the result of evaluating the queryList in the
     *                    strBuffer
     * @see
     *   <a href=
     *      "../../srchtml/project3/DatalogProgram.html#evaluateQueryList"
     *      >Source Code
     *   </a>
     */
    public void evaluateQueryList(StringBuffer strBuffer)
        throws Exception
    {
        assert strBuffer != null;
        queryList.evaluate(strBuffer);
    }

//Auxillary Methods and Constants
}
