package project3.three;

import java.util.HashSet;
import java.util.Iterator;

import project3.lex.Constant;

/**
 * For every variable in a predicate list we need to store the locations of
 * where that variable appears (there can be more than one) and store the
 * value for the variable.
 *
 * <pre>
 * Domain Definition:
 *     locations : Set&lt;<b>Location</b>&gt; -- the locations where a variable appears
 *     value     : Constant      -- the value for the variable
 *
 * Instance Invariant:
 *     locations &ne; null
 * </pre>
 *
 * Last Modified: 21 Feb 2006
 *
 * @author Scott N. Woodfield
 * @version 1.0
 * @see
 *   <a href=
 *      "../../srchtml/project3/LocationsAndValue.html"
 *      >Source Code
 *   </a>
 */
public class LocationsAndValue
    extends Locations
{
//Domain Implementation
//  HashSet<Location> locations -- inherited from Locations

    /**
     * The value of a variable. Implements value in the domain definition.
     * @see
     *   <a href=
     *      "../../srchtml/project3/LocationsAndValue.html#value"
     *      >Source Code
     *   </a>
     */
    protected Constant value = null;
    

//Constructors
    /**
     * The default constructor for <b>LocationsAndValue</b>.  
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
     *       value = null
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     * @see
     *   <a href=
     *      "../../srchtml/project3/LocationsAndValue.html#DefaultConstructor"
     *      >Source Code
     *   </a>
     */
    public LocationsAndValue()
    {
        super();
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
             none
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       super.Postcondition()
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
     *       value = locs.value
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @param locs the <b>LocationsAndValue</b> we are going to copy.
     * @see
     *   <a href=
     *      "../../srchtml/project3/LocationsAndValue.html#CopyConstructor"
     *      >Source Code
     *   </a>
     */
    public LocationsAndValue(LocationsAndValue locs)
    {
        super(locs);
        value = locs.value;
    }


//Queries
    /**
     * Returns the value. A getter function for <b>LocationsAndValue</b>.
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
     *       result = value;
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @return the value of this <b>LocationsAndValue</b>
     * @see
     *   <a href=
     *      "../../srchtml/project3/LocationsAndValue.html#getValue"
     *      >Source Code
     *   </a>
     */
    public Constant getValue()
    {
        return value;
    }

//Commands
    /**
     * Sets this.value to value.
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
     *       this.value = value
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @param value the value we will assign to this.value.
     * @see
     *   <a href=
     *      "../../srchtml/project3/LocationsAndValue.html#setValue"
     *      >Source Code
     *   </a>
     */
    public void setValue(Constant value)
    {
        assert value != null;

        this.value = value;
    }
}
