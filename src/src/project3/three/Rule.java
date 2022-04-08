package project3.three;

import java.util.HashMap;

import project3.lex.Constant;
import project3.lex.Identifier;
import project3.lex.Lex;
import project3.lex.TokenType;
import project3.PredicateList;

/**
 * A Rule in the datalog program.
 *
 * <p>
 * Syntax: <b>Rule</b> -&rarr; <b>Head</b> ":-" <b>PredicateList</b> "."
 * 
 * <p>
 * <pre>
 * Domain Definition:
 *     head : Head
 *     tail : PredicateList
 *
 * Instance Invariant:
 *     head &ne; null AND
 *     tail &ne; null
 * </pre>
 *
 * Last Modified: 21 Feb 2006
 *
 * @author Scott N. Woodfield
 * @version 1.0
 * @see
 *   <a href=
 *      "../../srchtml/project3/Rule.html"
 *      >Source Code
 *   </a>
 */
public class Rule
    extends Node
{
//Domain Implementation
    /**
     * The head of the <b>Rule</b>.
     * @see
     *   <a href=
     *      "../../srchtml/project3/Rule.html#head"
     *      >Source Code
     *   </a>
     */
    protected Head head;

    /**
     * The tail of the <b>Rule</b>.
     * @see
     *   <a href=
     *      "../../srchtml/project3/Rule.html#tail"
     *      >Source Code
     *   </a>
     */
    protected PredicateList tail;
    
//Constructors
    /**
     * Constructs a <b>Rule</b> from the tokens in the lexical analyzer.
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
     *       &exist;<sub>i</sub>
     *          (&exist;<sub>j</sub> (ValidHead(lex[0..i]) AND
     *                  lex[i+1].getTokenType() == TokenType.COLON_DASH AND
     *                  ValidPredicateList(lex[i+2..j]) AND
     *                  lex[j+1].getTokenType() == TokenType.PERIOD))
     *     </td>
     *     <td>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       &exist;<sub>i</sub>
     *          (&exist;<sub>j</sub> (head = new Head(lex[0..i]) AND
     *              tail = new PredicateList(lex[i+2..j]) AND
     *                  lex[j+1].getTokenType() == TokenType.PERIOD))
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @throws ParserException
     *
     * @param lex the lexical analyzer we will create a <b>Rule</b> from.
     * @see
     *   <a href=
     *      "../../srchtml/project3/Rule.html#LexConstructor"
     *      >Source Code
     *   </a>
     */
    public Rule(Lex lex)
        throws ParserException
    {
        head = new Head(lex);
        checkFor(lex, TokenType.COLON_DASH);
        tail = new PredicateList(lex);
        checkFor(lex, TokenType.PERIOD);
    }

//Queries
    /**
     * Converts the Rule to a lexical representation.
     *
     * </pre>
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
     *       result = head.toString() + ":-" + tail.toString() + "."
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @return the string representation of a <b>Rule</b>
     * @see
     *   <a href=
     *      "../../srchtml/project3/Rule.html#toString"
     *      >Source Code
     *   </a>
     */
    public String toString()
    {
        StringBuffer result = new StringBuffer();
        result.append(head.toString());
        result.append(":-");
        result.append(tail.toString());
        result.append(".");
        return result.toString();
    }

    /**
     * Determines wheter this rule can show that the predicate is true.
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
     *       result = for every variable in the tail that exists in
     *                head we substitutie the corresponding constant
     *                in the predicate and we can find a set of
     *                constants from the domain that make the tail
     *                true, then return true, else return false.
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @throws ParserException
     *
     * @param predicate the predicate (with all constants) we are going to
     *                    test.
     * 
     * @return true iff after substituting all of the constants of the predicate
     *              into the corresponding variables of the head of the rule,
     *              can we find a substitution for the remaining free variables
     *              that make it true.
     * @see
     *   <a href=
     *      "../../srchtml/project3/Rule.html#prove"
     *      >Source Code
     *   </a>
     */
    public boolean prove(Predicate predicate)
        throws ParserException
    {
         assert predicate != null;

         boolean result = false;
         if(head.matches(predicate)){
             PredicateList predicateList = new PredicateList(tail);
             predicateList.setVariables(head.unify(predicate));
             result = predicateList.evaluate();
         };
         return result;
    }

//Commands
}
