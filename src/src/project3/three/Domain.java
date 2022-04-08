package project3.three;

import java.util.HashSet;
import java.util.Iterator;

import project3.lex.Constant;

/**
 * The domain (set of valid values or constants) for a datalog program.
 * This is a singleton.
 *
 * <p>
 * <pre>
 * Domain Definition:
 *     domain: Setof&lt;<i>Constant</i>&gt; 
 * </pre>
 *
 * Last Modified: 21 Feb 2006
 *
 * @author Scott N. Woodfield
 * @version 1.0
 * @see
 *   <a href=
 *      "../../srchtml/project3/Domain.html"
 *      >Source Code
 *   </a>
 */
public class Domain
{
//Domain Implementations
    /**
     * The set of constants in the domain.
     * @see
     *   <a href=
     *      "../../srchtml/project3/Domain.html#domain"
     *      >Source Code
     *   </a>
     */
    public static HashSet<Constant> domain = new HashSet<Constant>();

//Constructors

//Queries
    /**
     * Returns an iterator over the domain.
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
     *       result = domain.iterator()
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @return the iterator for the domain.
     * @see
     *   <a href=
     *      "../../srchtml/project3/Domain.html#iterator"
     *      >Source Code
     *   </a>
     */
    public static Iterator<Constant> iterator()
    {
        return domain.iterator();
    }

    /**
     * Returns wheter or not the domain is empty.
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
     *       result = |domain| = 0
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @return an indicater as to whether the domain is empty or not.
     * @see
     *   <a href=
     *      "../../srchtml/project3/Domain.html#isEmpty"
     *      >Source Code
     *   </a>
     */
    public static boolean isEmpty()
    {
        return domain.isEmpty();
    }

//Commands
    /**
     * Adds a constant to the set.
     *
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
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
     *       domain = domain' &cup; {value}
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @param value the <i>Constant</i> we will add to the <b>Domain</b>.
     * @see
     *   <a href=
     *      "../../srchtml/project3/Domain.html#add"
     *      >Source Code
     *   </a>
     */
    public static void add(Constant value)
    {
        assert value != null;
        domain.add(value);
    }

    /**
     * Makes the domain empty. Primarily used for testing.
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
     *       |domain| = 0
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     * @see
     *   <a href=
     *      "../../srchtml/project3/Domain.html#reset"
     *      >Source Code
     *   </a>
     */
    public static void reset()
    {
        domain = new HashSet<Constant>();
    }
}
