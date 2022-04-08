package project3.three;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import project3.lex.Constant;
import project3.lex.Identifier;

/**
 * The information about each variable in a <b>Query</b>.
 *
 * <p>
 * <pre>
 * Domain Definition
 *     map : Map&lt;Identifier, LocationsAndValue&gt; 
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
 *      "../../srchtml/project3/QueryVariableInformation.html"
 *      >Source Code
 *   </a>
 */
public class QueryVariableInformation
    extends VariableInformation
{
//Domain Implementation
//  Map<Identifier, Locations> map -- inherited from VariableInformation
//       -- since a LocationsAndValue is a Locations, we can store at
//       -- LocationsAndValue in the map.

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
     *     map <i>instanceof</i> HashMap&lt;Identifier, LocationsAndValue&gt; 
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
     *      "../../srchtml/project3/QueryVariableInformation.html#DefaultConst"
     *      >Source Code
     *   </a>
     */
    public QueryVariableInformation()
    {
        super();
    } 

//Queries
    /**
     * Gets the value of the given variable.
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
     *       map.contains(variable) &rArr; 
     *                    result = map[variable].getValue()
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
     *       &not; map.contains(variable)  &rArr; result = null
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @param variable the variable we are going to get a value for.
     *
     * @return the constant associated with the indicated variable.
     * @see
     *   <a href=
     *      "../../srchtml/project3/QueryVariableInformation.html#getValueFor"
     *      >Source Code
     *   </a>
     */
    public Constant getValueFor(Identifier variable)
    {
        assert variable != null;

        Constant result = null;
        if(map.containsKey(variable)){
            result = ((LocationsAndValue)map.get(variable)).getValue();
        };

        return result;
    }

    /**
     * Returns an array of <i>Constant</i>s, x, such that <i>Constant</i> x[i]
     * corresponds to the variable getVariables()[i].
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
     *       |result| = |getVariables()|
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
     *       &forall;<sub>i</sub> (0 &lt;= i AND i &lt; |getVariables()| &rArr;
     *                    result[i] = map.get(getVariables()[i]).getValue())
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @return an array of all constants that have been substituted for the
     *         free variables in this <b>Query</b>.
     * @see
     *   <a href=
     *      "../../srchtml/project3/QueryVariableInformation.html#getSolution"
     *      >Source Code
     *   </a>
     */
    public Constant[] getSolution()
    {
        Constant[] result = new Constant[map.size()];
        int i = 0;
        Iterator<Identifier> iter = getVariableIterator();
        while(iter.hasNext()){
            result[i] = ((LocationsAndValue)map.get(iter.next())).getValue();
            i++;
        };
        return result;
    }

//Commands
    /**
     * Sets the value for the given variable.
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
     *       map.get(variable).getValue() = value
     *     </td>
     *     <td valign=top>
     *       AND
     *     </td>
     *   </tr>
     * </table>
     *
     * @param variable the name of the variable we are going to make
     *                   substitutions for.
     * @param value the constant we are going to replace the variable(s) with.
     * @see
     *   <a href=
     *      "../../srchtml/project3/QueryVariableInformation.html#setValueFor"
     *      >Source Code
     *   </a>
     */
    public void setValueFor(Identifier variable, Constant value)
    {
        assert variable != null && value != null;

        LocationsAndValue locationsAndValue = null;
        if(map.containsKey(variable)){
            locationsAndValue = (LocationsAndValue)map.get(variable);
        } else {
            locationsAndValue = (LocationsAndValue)createLocations();
            map.put(variable, locationsAndValue);
        }
        locationsAndValue.setValue(value);
    }

//Auxillary Methods and Constants
    /**
     * Creates an empty LocationsAndValue. This method overrides the
     * corresponding method in <b>PredicateList</b>. The original returned
     * a <b>Locations</b> which records every location of a variable in a
     * <b>PredicateList</b> (a <b>Query</b> is a special kind of
     * <b>PredicateList</b>).  In addition, a <b>Query</b> can record the
     * the current value of a variable. Thus this method returns an object
     * that has no <b>Locations</b> and no <i>Constant</i> associated with any
     * variable.
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
     * @return an empty <b>LocationsAndValue</b>.
     * @see
     *   <a href=
     *      "../../srchtml/project3/QueryVariableInformation.html#createLocations"
     *      >Source Code
     *   </a>
     */
    protected Locations createLocations()
    {
        return new LocationsAndValue();
    }
}
