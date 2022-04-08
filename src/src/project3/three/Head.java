package project3.three;

import java.util.Iterator;
import java.util.HashMap;
import java.util.HashSet;

import project3.lex.Constant;
import project3.lex.Identifier;
import project3.lex.Lex;
import project3.lex.Token;
import project3.lex.TokenType;

/**
 * The <b>Head</b> of a <b>Rule</b>.
 *
 * <p>
 * <pre>
 * Syntax: <b>Head</b> &rarr; <b>Scheme</b>
 *
 * Domain:
 *     name           : Identifier
 *     identifierList : Sequence&lt;Idenitifer&gt;
 *
 * Instance Constraint:
 *     -- The identifers are unique.  Inherited from <b>Scheme</b>
 * </pre>
 *
 * Last Modified: 21 Feb 2006
 * @author Scott N. Woodfield
 * @version 1.0
 * @see
 *   <a href=
 *      "../../srchtml/project3/Head.html"
 *      >Source Code
 *   </a>
 */
public class Head
    extends Scheme
{
//Domain Implementation
//  Identifier name       -- inherited indirectly from List
//  ArrayList<Node> nodes -- inherited indirectly from List,
//                        -- this implements Sequence<Identifier>.
//                        -- Scheme guarantees that every element in the
//                        -- list is an Identifier

//Constructor
    /**
     * The Lex based constructor for a Head.
     *
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *         <b>Scheme</b>.Precondition
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       <b>Scheme</b>.Precondition
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     * @see
     *   <a href=
     *      "../../srchtml/project3/Head.html#LexConstructor"
     *      >Source Code
     *   </a>
     */
    public Head(Lex lex)
        throws ParserException
    {
        super(lex);
    }

//Queries
    /**
     * Creates a map of the variables in nodes to the constants in predicate.
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
     *       AND
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       &nbsp;
     *     </td>
     *     <td valign=top>
     *       |predicate| = |nodes|
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
     *       &forall;<sub>i</sub> (0 &lt;= i AND i &lt; |predicate| &rArr;
     *           (predicate[i] <i>instanceof</i> <b>Constant</b>))
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       |result| = |nodes|
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
     *       &forall;<sub>n</sub> (
     *           &forall;<sub>i</sub> (
     *               (result.getKeys().contains(n) AND
     *                    nodes[i] = n &rArr; predicate[i] = result.get(n))))
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @param predicate the predicate we will unify with this <b>Head</b>.
     *
     * @return a mapping from each of the variables in the head to the
     *         constants in predicate.
     * @see
     *   <a href=
     *      "../../srchtml/project3/Head.html#unify"
     *      >Source Code
     *   </a>
     */
    public HashMap<Identifier, Constant> unify(Predicate predicate)
    {
        assert predicate != null;
        assert predicate.size() == this.size();
        assert allConstants(predicate);

        HashMap<Identifier, Constant> result =
            new HashMap<Identifier, Constant>();

        for(int i = 0; i < nodes.size(); i++){
            result.put((Identifier)nodes.get(i),
                       (Constant)predicate.getNodes().get(i));            
        };

        return result;
    }

    /**
     * Checks to see if this <b>Head</b> matches the given <b>PredicateList</b>.
     *
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *       predicateList &ne; null
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       result = (name = predicateList.name AND |this| = |predicateList|)
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @param predicate the predicate we are going to try and match.
     *
     * @return whether the predicates match or not.
     * @see
     *   <a href=
     *      "../../srchtml/project3/Head.html#matches"
     *      >Source Code
     *   </a>
     */
    public boolean matches(Predicate predicate)
    {
        boolean result = name.equals(predicate.getName()) &&
                         nodes.size() == predicate.getNodes().size();
        return result;
    }

//Commands

//Auxiallary Methods and Constants
    /**
     * Check to see if all of the arguments in the predicate are constants.
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
     *       result = &forall; <sub>i</sub>
     *         (0 &lt;= i AND i &lt; |predicate| &rArr;
     *                    predicate[i] <i>instanceof</i> <b>Constant</b>)
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @param predicate the predicate we are going to examine to see if it
     *                    contains all constants.
     *
     * @return whether the provided predicate contains all constants.
     * @see
     *   <a href=
     *      "../../srchtml/project3/Head.html#allConstants"
     *      >Source Code
     *   </a>
     */
    private boolean allConstants(Predicate predicate)
    {
        assert predicate != null;
        boolean result = true;
        Iterator iter = predicate.iterator();
        while(iter.hasNext() && result){
            result = iter.next() instanceof Constant;
        };
        return result;
    }
}
