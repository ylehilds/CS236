package project3.three;

import java.util.HashSet;
import java.util.Iterator;

import project3.lex.Constant;

/**
 * For every variable in a predicate list we need to store the locations of
 * where that variable appears (there can be more than one).
 *
 * <pre>
 * Domain Definition:
 *     locations : Set&lt;<b>Location</b>&gt; -- the locations where a variable appears
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
 *      "../../srchtml/project3/Locations.html"
 *      >Source Code
 *   </a>
 */
public class Locations
{
//Domain Implementation
    /**
     * The set of locations of a variable.  Implements locations in the
     * domain definition.
     * @see
     *   <a href=
     *      "../../srchtml/project3/Locations.html#locations"
     *      >Source Code
     *   </a>
     */
    protected HashSet<Location> locations = null;

//Constructors
    /**
     * The default constructor for <b>Locations</b>.  
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
     *       locations = new HashSet<Location>()
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
     *       |locations| = 0 
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     * @see
     *   <a href=
     *      "../../srchtml/project3/Locations.html#DefaultConstructor"
     *      >Source Code
     *   </a>
     */
    public Locations()
    {
        locations = new HashSet<Location>();
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
     *       locs = null &rArr; locations = new HashSet() 
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
     *       locs &ne; null &rArr; locations = new HashSet(locs.locations)
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @param locs the <b>Locations</b> we are going to make a copy of.
     * @see
     *   <a href=
     *      "../../srchtml/project3/Locations.html#CopyConstructor"
     *      >Source Code
     *   </a>
     */
    public Locations(Locations locs)
    {
        if(locs == null){
            locations = new HashSet<Location>();
        } else {
            locations = new HashSet<Location>(locs.locations);
        };
    }

//Queries
    /**
     * Returns an iterator over locations.
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
     *       result = locations.iterator()
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @return an iterator over all of the <b>Location</b>s of this
     *         <b>Locations</b>.
     * @see
     *   <a href=
     *      "../../srchtml/project3/Locations.html#iterator"
     *      >Source Code
     *   </a>
     */
    public Iterator<Location> iterator()
    {
        return locations.iterator();
    }

//Commands
    /**
     * Adds a location to locations.
     * 
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *       location &ne; null
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       locations.contains(location)
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @param location the location we are going to add to this
     *                   <b>Locations</b>
     * @see
     *   <a href=
     *      "../../srchtml/project3/Locations.html#addLocation"
     *      >Source Code
     *   </a>
     */
    public void addLocation(Location location)
    {
        assert location != null;

        locations.add(location);
    }
}
