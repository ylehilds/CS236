package project3.three;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import project3.lex.Constant;
import project3.lex.Identifier;

/**
 * The information about each variable in a <b>PredicateList</b> (and a
 * <b>Query</b> since it is a <b>PredicateList</b>).
 *
 * <pre>
 * Domain Definition
 *     map : Map&lt;Identifier, Locations&gt; 
 *     
 * Instance Invariant:
 *     map &ne; null
 * </pre>
 *
 * Last Modified: 21 Feb 2006
 *
 * @author Scott N. Woodfield
 * @version 1.0
 * @see
 *   <a href=
 *      "../../srchtml/project3/VariableInformation.html"
 *      >Source Code
 *   </a>
 */
public class VariableInformation
{
//Domain Implementation
    /**
     * The implementation of the map as a
     * HashMap&lt;<i>Identifier</i>, <b>Locations</b>&gt;
     * @see
     *   <a href=
     *      "../../srchtml/project3/VariableInformation.html#map"
     *      >Source Code
     *   </a>
     */
    protected HashMap<Identifier, Locations> map = null;

//Constructors
    /**
     * The default constructor.
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
     *       map <i>instanceof</i> HashMap&lt;Identifier, Locations&gt;
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
     *       |map| = 0
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     * @see
     *   <a href=
     *      "../../srchtml/project3/VariableInformation.html#DefaultConstructor"
     *      >Source Code
     *   </a>
     */
    public VariableInformation()
    {
        map = new HashMap<Identifier, Locations>();
    } 

//Queries
    /**
     * Returns an iterator over all locations of the given variable.
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
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       result = map[variable].iterator()
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @param variable the variable we are going to get all the locations for.
     *
     * @return an iterator over all of the <b>Location</b>s associated with
     *         a variable.
     * @see
     *   <a href=
     *      "../../srchtml/project3/VariableInformation.html#getLocationsFor"
     *      >Source Code
     *   </a>
     */
    public Iterator<Location> getLocationsFor(Identifier variable)
    {
        assert variable != null;

        Iterator<Location> result = null;
        if(map.containsKey(variable)){
            result = map.get(variable).iterator();
        };

        return result;
    }

    /**
     * Get an iterator over all of the variables in this set of variable
     * information.
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
     *       result = map.keySet.iterator();
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @return an <b>Iterator</b>&lt;<i>Identifier</i>&gt; over all of the
     *         variables in this set of <b>VariableInformation</b>.
     * @see
     *   <a href=
     *      "../../srchtml/project3/VariableInformation.html#getVariableIterator"
     *      >Source Code
     *   </a>
     */
    public Iterator<Identifier> getVariableIterator()
    {
        return map.keySet().iterator();
    }

    /**
     * Get an array of <b>Identifier</b>s representing all of the variables in
     * this set of variable information.  
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
     *       &forall;<sub>x</sub>
     *          (map.getKeySet().contains(x) &rArr; result.contains(x)
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
     *       &forall;<sub>x</sub> 
     *           (result.contains(x) &rArr; map.getKeySet().contains(x))
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @return an <b>Array</b>&lt;<i>Identifier</i>&gt; over all of the
     *         variables in this set of <b>VariableInformation</b>.
     * @see
     *   <a href=
     *      "../../srchtml/project3/VariableInformation.html#getVariables"
     *      >Source Code
     *   </a>
     */
    public Identifier[] getVariables()
    {
        Identifier[] result = new Identifier[map.size()];
        int i = 0;
        Iterator<Identifier> iter = getVariableIterator();
        while(iter.hasNext()){
            result[i] = iter.next();
            i++;
        };
        return result;
    }

//Commands
    /**
     * Removes the mapping for the given variable.
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
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       &not; map.containsKey(variable)
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @param variable the variable whose information we will remove from the
     *                   list.
     * @see
     *   <a href=
     *      "../../srchtml/project3/VariableInformation.html#remove"
     *      >Source Code
     *   </a>
     */
    public void remove(Identifier variable)
    {
        map.remove(variable);
    }
    
    /**
     * Adds a <b>Location</b> for the given variable.
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
     *       map.get(variable).locations.contains(location)
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @param variable the variable we are going to add a location to.
     * @param location the <b>Location</b> we are going to accociate the
     *                   with the indicated variable.
     * @see
     *   <a href=
     *      "../../srchtml/project3/VariableInformation.html#addLocationFor"
     *      >Source Code
     *   </a>
     */
    public void addLocationFor(Identifier variable, Location location)
    {
        assert variable != null && location != null;

        Locations locations = null;
        if(map.containsKey(variable)){
            locations = map.get(variable);
        } else {
            locations = createLocations();
            map.put(variable, locations);
        }
        locations.addLocation(location);
    }

//Auxillary Methods and Constants
    /**
     * Creates an empty <b>Locations</b>.
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
     *       result = new LocationsAndValue();
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @return an empty set of <b>Locations</b>
     * @see
     *   <a href=
     *      "../../srchtml/project3/VariableInformation.html#createLocations"
     *      >Source Code
     *   </a>
     */
    protected Locations createLocations()
    {
        return new Locations();
    }
}
