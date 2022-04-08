package project3.three;

/**
 * Defines the location of an argument in a simple predicate of a
 * predicate list.
 *
 * <pre>
 * Domain Definition:
 *     predicateIndex      : int
 *     locationInPredicate : int
 *
 * Intstance Invariant:
 *     predicateIndex &gt;= 0 AND
 *     locationInPredicate &gt;= 0
 * </pre>
 *
 * Last Modified: 21 Feb 2006
 *
 * @author Scott N. Woodfield
 * @version 1.0
 * @see
 *   <a href=
 *      "../../srchtml/project3/Location.html"
 *      >Source Code
 *   </a>
 */
public class Location
{
//Domain Implementation
    /**
     * The index into the list of <b>Predicate</b>.
     * @see
     *   <a href=
     *      "../../srchtml/project3/Location.html#predicateIndex"
     *      >Source Code
     *   </a>
     */
    protected int predicateIndex;

    /**
     * The index into the list of <b>Argument</b>s of the identified
     * <b>Predicate</b>.
     * @see
     *   <a href=
     *      "../../srchtml/project3/Location.html#locationInPredicate"
     *      >Source Code
     *   </a>
     */
    protected int locationInPredicate;

//Constructors
    /**
     * Creates a <b>Location</b> using the two input parameters.
     * 
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *       predicateIndex &gt;= 0 
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
     *       locationInPredicate &gt;= 0
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       this.predicateIndex = predicateIndex 
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
     *       this.locationInPredicate = locationInPredicate
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @param predicateIndex an integer value that identifies a
     *                         <b>Predicate</b> in the <b>PredicateList</b>.
     * @param locationInPredicate an integer value that identifies the 
     *                              location of an <b>Argument</b> in the
     *                              <b>PredicateList</b>
     * @see
     *   <a href=
     *      "../../srchtml/project3/Location.html#StandardConstructor"
     *      >Source Code
     *   </a>
     */
    public Location(int predicateIndex, int locationInPredicate)
    {
         assert predicateIndex >= 0 && locationInPredicate >= 0;
         this.predicateIndex = predicateIndex;
         this.locationInPredicate = locationInPredicate;
    }

//Queries
    /**
     * Returns the predicate index.
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
     *       result = predicateIndex
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @return an index into a list of <b>Predicate</b>.
     * @see
     *   <a href=
     *      "../../srchtml/project3/Location.html#getPredicateIndex"
     *      >Source Code
     *   </a>
     */
    public int getPredicateIndex()
    {
        return predicateIndex;
    }

    /**
     * Returns the location in the predicate.
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
     *       result = locationInPredicate
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @return an index to a <b>Argument</b> in the <b>Predicate</b>.
     * @see
     *   <a href=
     *      "../../srchtml/project3/Location.html#getLocationInPredicate"
     *      >Source Code
     *   </a>
     */
    public int getLocationInPredicate()
    {
        return locationInPredicate;
    }

    /**
     * Determines wheter two Locations are equal.  Used in a hash table.
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
     *       result =  o &ne; null
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
     *       o <i>instanceof</i> <b>Location</b> 
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
     *       predicateIndex = o.predicateIndex 
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
     *       locationInPredicate = o.locationInPredicate.
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @param o the object we compare this location too.
     *
     * @return whether or not the this location is equal to "o".
     * @see
     *   <a href=
     *      "../../srchtml/project3/Location.html#equals"
     *      >Source Code
     *   </a>
     */
    public boolean equals(Object o)
    {
        boolean result = o != null && o instanceof Location;
        if(result){
            Location location = (Location)o;
            result = predicateIndex == location.predicateIndex &&
                     locationInPredicate == location.locationInPredicate;
        };
        return result;
    }

    /**
     * Returns the hashCode for a location.
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
     *       result = predicateIndex * OFFSET+ locationInPredicate
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
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @return the hash code for this location.
     * @see
     *   <a href=
     *      "../../srchtml/project3/Location.html#hashCode"
     *      >Source Code
     *   </a>
     */
    public int hashCode()
    {
        return predicateIndex * OFFSET + locationInPredicate;
    }

//Commands

//Auxillary Constants and Methods
    /**
     * An offset number to be used when computing the hashCode. To form the
     * hashCode we don't want to just add the numbers together.  If we did,
     * there would probably be several collisions.  To overcome that we
     * need to offset the predicateIndex by some amount approximately equal
     * to the number of variables in a predicate.
     * @see
     *   <a href=
     *      "../../srchtml/project3/Location.html#offset"
     *      >Source Code
     *   </a>
     */
    private final int OFFSET = 73;
}
