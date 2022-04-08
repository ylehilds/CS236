package project3.three;

import project3.lex.Constant;
import project3.lex.Identifier;
import project3.lex.Lex;
import project3.lex.Token;
import project3.lex.TokenType;

import project3.PredicateList;

/**
 * A <b>Query</b> in the datalog program.
 *
 * <pre>
 * Syntax: <b>Query</b> &rarr; <b>Predicate</b> "?"
 *
 * Domain Definition:
 *    predicates : <b>Predicate</b>; 
 *
 *    setOfSolutions : <b>SetOfSolutions</b>
 *    -- A list of arrays of constants that represent the valid substitutions
 *    -- for the variables in the variable list. There is a one-to-one
 *    -- correspondence between the constants in an array and the variables in
 *    -- the query.
 *
 *    variableInformation: <b>QueryVariableInformation</b>
 *    -- The set of variables in the Query, and, for each variable the 
 *    -- corresponding locations in the Query and current value of that
 *    -- variable.  
 *
 *    variableIterator : Iterator<Identifier>
 *    -- An iterator over the variableInformation.  Inherited from 
 *    -- <b>PredicateList</b>
 *
 *    Instance Invariant:
 *        |predicates| = 1
 * </pre>
 *
 * Last Modified: 21 Feb 2006
 *
 * @author Scott N. Woodfield
 * @version 1.0
 * @see
 *   <a href=
 *      "../../srchtml/project3/Query.html"
 *      >Source Code
 *   </a>
 */
public class Query
    extends PredicateList
{
//Domain Implementation
    // VariableInformation variableInformation
    //   -- inherited from PredicateList. But, we assign an instance of
    //   -- QueryVariableInformation to it.  A QueryVariableInformation inherits
    //   -- everything from VariableInformation plus it includes the current
    //   -- value for a variable.
    //    
    // Iterator<Identifier> variableIterator -- inherited from PredicateList

    /**
     * A list of <b>Solution</b>s where a <b>Solution</b> is an array of
     * <i>Constant</i>s such that the constants in the <b>Solution</b> have a
     * one-to-one correspondence with the variables in the query.
     * @see
     *   <a href=
     *      "../../srchtml/project3/Query.html#setOfSolutions"
     *      >Source Code
     *   </a>
     */
    protected SetOfSolutions setOfSolutions = null;

//Constructors
    /**
     * The lex-based constructor used to create a Query from the lexical
     * analyzer.
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
     *       <b>PredicateList</b>.Precondition 
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       &nbsp;
     *     </td>
     *     <td valign=top>
     *       |<i>nodes</i>| = 1
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *         <b>PredicateList</b>.Postcondition 
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
     *         setOfSolutions =
     *            new SetOfSolutions(variableInformation.getVariables())
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @throws ParserException
     *
     * @param lex the lexical analyzer we will read from to create the
     *              <b>Query</b>
     * @see
     *   <a href=
     *      "../../srchtml/project3/Query.html#LexConstructor"
     *      >Source Code
     *   </a>
     */
    public Query(Lex lex)
        throws ParserException
    {
        super(lex);

        checkFor(lex, TokenType.Q_MARK);

        if(nodes.size() != 1)
            throw new ParserException(
                "Error while parsing Query on or before line " +
                lex.front().getLineNumber() + ".\n" +
                "A Query can have only one simple predicate");

        setOfSolutions =
            new SetOfSolutions(
                ((QueryVariableInformation)variableInformation).getVariables());
    }

//Queries
    /**
     * Evaluates the query for all combinations of constants from the domain,
     * for all variables in the query.
     *
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *       PredicateList.evaluate().Precondition 
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       PredicateList.evaluate().PostCondition 
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
     *       strBuffer = strBuffer + toString()
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @throws ParserException
     *
     * @param strBuffer the buffer in which we store the result.
     *
     * @return true iff we found a substitution for the variables in the
     *         <b>Query</b>
     * @see
     *   <a href=
     *      "../../srchtml/project3/Query.html#evaluate"
     *      >Source Code
     *   </a>
     */
    public boolean evaluate(StringBuffer strBuffer)
        throws ParserException
    {
       //We copy <i>this</i> query as a PredicateList so that when apply
       //"toString()" to it we will get the "toString()" method of
       //PredicateList rather than the "toString()" method of Query.
        copy = new PredicateList(this);

        boolean result = super.evaluate();
        strBuffer.append(toString());
        return result;
    }

    /**
     * Returns the lexical form of the query plus the results of the query.
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
     *     copy = null &rArr;
     *         reuslt = super.toString() + setOfSolutions.toString() + "\n"
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
     *       copy &ne; null &rArr;
     *         reuslt = copy.toString() + setOfSolutions.toString() + "\n"
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @return the lexical representation of the <b>Query</b>
     * @see
     *   <a href=
     *      "../../srchtml/project3/Query.html#toString"
     *      >Source Code
     *   </a>
     */
    public String toString()
    {
        StringBuffer result = new StringBuffer();
        if(copy == null){
            result.append(super.toString());
        } else {
            result.append(copy.toString());
        };
        result.append("?");
        result.append(setOfSolutions);
        //result.append("\n");
        return result.toString();
    }

//Commands
    /**
     * Saves all of the valid solutions.
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
     *       setOfSolutions =
     *          setOfSolutions' + variableInformation.getSolution()
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     * @see
     *   <a href=
     *      "../../srchtml/project3/Query.html#saveResult"
     *      >Source Code
     *   </a>
     */
    protected void saveResult()
    {
        setOfSolutions.addSolution(
            ((QueryVariableInformation)variableInformation).getSolution());
    }

    /**
     * Everyplace in the predicate list where <i>variable</i> appears, it is
     * replaced by the given value and we remember what that value was so
     * we can print it later.
     *
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *       variable &ne; null
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
     *        Everyplace where variable appeared in the predicateList
     *        it has been replaced by the given value.
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @throws ParserException
     *
     * @param variable all variables with this name are changed to the given
     *                   value.
     * @param value the value we are going to change the variable(s) to.
     * @see
     *   <a href=
     *      "../../srchtml/project3/Query.html#setVariableToValue"
     *      >Source Code
     *   </a>
     */
    public void setVariableToValue(Identifier variable, Constant value)
        throws ParserException
    {
        super.setVariableToValue(variable, value);
        //assert variable != null || value != null; -- checked in superclass
        ((QueryVariableInformation)variableInformation).
            setValueFor(variable, value);
    }

//Auxillary Methods and  Constants
    /**
     * A copy of the orginal query.  As we evaluate a query, we modify it.
     * This copy is used to print the original query.  We store the copy of
     * a <b>Query</b> as a <b>PredicateList</b> so that when we apply the
     * "toString()" method we use the "toString()" method of
     * <b>PredicateList</b> rather than that of <b>Query</b>.  We make this
     * protected so we can access it during testing.
     * @see
     *   <a href=
     *      "../../srchtml/project3/Query.html#copy"
     *      >Source Code
     *   </a>
     */
    protected PredicateList copy = null;


    /**
     * Setup the <b>PredicateList</b> with all information as to a variable
     * and its locations in the predicate list. So that we can later 
     * store the values substituted for variables we use a 
     * <b>QueryVariableInformation</b> rather then <b>VariableInformation</b>.
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
     *       variableInformation <i>instanceof</i>
     *         <b>QueryVariableInformation</b>
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
     *       setUpVariableLocationMapping.Postcondition
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     * @see
     *   <a href=
     *      "../../srchtml/project3/Query.html#initializeVariableInformation"
     *      >Source Code
     *   </a>
     */
    protected void initializeVariableInformation()
    {
        variableInformation = new QueryVariableInformation();
        setUpVariableLocationMapping();
    }

    /**
     * Should we continue finding all combinations of constants?  If we are
     * recursing over a predicate list, then we can stop the first time
     * we find a true one,  if this is a query, then we never stop.  Since this
     * is a query we never stop.
     *
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *       None. Ignore validSolutionFound. 
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       result = true
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @param validSolutionFound indicates whether or not a valid
     *                             solution was found.  In this case we
     *                             don't care but in <b>PredicateList</b> we do.
     *
     * @return true always -- for <b>Query</b>s we try all possible
     *                        combinations.
     * @see
     *   <a href=
     *      "../../srchtml/project3/Query.html#keepOnGoing"
     *      >Source Code
     *   </a>
     */
    protected boolean keepOnGoing(boolean validSolutionFound)
    {
        return true;
    }
}

