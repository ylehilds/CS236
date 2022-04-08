package project3.three;

import java.util.ArrayList;
import java.util.Iterator;

import project3.lex.Constant;
import project3.lex.Identifier;

/**
 * For every query we keep of list a solutions that make the query true.
 * In this case we represent a solution as an array of constants. Thus we have
 * a sequence of arrays.
 *
 * <p>
 * <pre>
 * Domain Definition:
 *     variables     : Identifier[] 
 *     setOfSolution : Set<Conststant[]>
 *
 * Instance Invariant:
 *     |variables| &gt; 1 AND
 *     &forall;<sub>i</sub> (0 &lt;= i AND i &lt; |setOfSolutions| &rArr;
 *         |setOfSolutions.get(i)| == |variables| AND
 *          &forall;<sub>j</sub> (&lt;= j AND j &lt; |setOfSolutions.get(i)| &rArr;
 *              setOfSolutions.get(i).get(j) &ne; null))
 * </pre>
 *
 * Last Modified: 21 Feb 2006
 *
 * @author Scott N. Woodfield
 * @version 1.0
 * @see
 *   <a href=
 *      "../../srchtml/project3/SetOfSolutions.html"
 *      >Source Code
 *   </a>
 */
public class SetOfSolutions
{
//Domain Implementation
    /**
     * Implementation of variables.
     * @see
     *   <a href=
     *      "../../srchtml/project3/SetOfSolutions.html#variables"
     *      >Source Code
     *   </a>
     */
    protected Identifier[] variables = null;

    /**
     * Implementation of setOfSolutions.
     * @see
     *   <a href=
     *      "../../srchtml/project3/SetOfSolutions.html#setOfSolutions"
     *      >Source Code
     *   </a>
     */
    protected ArrayList<Constant[]> setOfSolutions = null;

//Constructor
    /**
     * Create an empty <b>SetOfSolutions</b>
     *
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *       variables &ne; null 
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
     *       &forall;<sub>i</sub> (0 &lt;= i AND i &lt; |variables| &rArr;
     *           (variables[i] &ne; null)
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       this.variables = variables 
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
     *       setOfSolutions = new ArrayList&lt;<i>Constant</i>[]&gt;()
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @param variables the list of variables from which we will create a
     *                    <b>SetOfSolutions</b>
     * @see
     *   <a href=
     *      "../../srchtml/project3/SetOfSolutions.html#PrimaryConstructor"
     *      >Source Code
     *   </a>
     */
    public SetOfSolutions(Identifier[] variables)
    {
        assert variables != null;

        boolean foundNull = false;
        for(int i = 0; i < variables.length && !foundNull; i++){
            foundNull = variables[i] == null;
        };
        assert !foundNull;

        this.variables = variables;
        setOfSolutions = new ArrayList<Constant[]>();
    }

//Query
    /**
     * Convert a <b>SetOfSolutions</b> to a string.
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
     *        |setOfSolutions| = 0 &rArr; result = " No\n"
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
     *       |setOfSolutions| &ne; 0 &rArr; 
     *       result = &sum; 0 &lt;= i &lt; |setOfSolutions|
     *              (printSolutions(result, setOfSolutions[i]) + "\n"
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @return the string representation of a <b>SetOfSolutions</b>
     * @see
     *   <a href=
     *      "../../srchtml/project3/SetOfSolutions.html#toString"
     *      >Source Code
     *   </a>
     */
    public String toString()
    {
        StringBuffer result = new StringBuffer();

        if(setOfSolutions.size() == 0){
            result.append(" No\n");
        } else {
            result.append(" Yes("+setOfSolutions.size()+")\n");
            Iterator<Constant[]> solutionsIterator =
                setOfSolutions.iterator();
            while(solutionsIterator.hasNext()){
                printSolutions(result, solutionsIterator.next());
            };
        };
        return result.toString();
    }

//Commands
    /**
     * Add a solution to the <b>SetOfSolutions</b>.
     *
     * Postcondition: setOfSolutions.contains(solution);
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *       solution &ne; null
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
     *         |solution| = |variables|
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       setOfSolutions = setOfSolutions &cup; {solution}
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @param solution the solution to be added to the list of solutions.
     * @see
     *   <a href=
     *      "../../srchtml/project3/SetOfSolutions.html#addSolution"
     *      >Source Code
     *   </a>
     */
    public void addSolution(Constant[] solution)
    {
        assert solution != null &&
               solution.length == variables.length; 

        if(!setOfSolutions.contains(solution)){
            setOfSolutions.add(solution);
        };
    }

//Auxillary Methods and Constants
    /**
     * Convert an array of solutions to a string.
     *
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *        result &ne; null
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       |solutions| = 0 &rArr; result = result' + "\n"
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
     *       |solutions| &ne; 0 &rArr; <br>
     *         &nbsp;&nbsp;&nbsp;result = result' +
     *         &nbsp;&nbsp;&nbsp;variables[0] + " = " + solutions[i] + <br>
     *         &nbsp;&nbsp;&nbsp;&sum; 1 &lt;= i &lt; |variables|
     *             (", " + variables[i] + " = " + solutions[i]) + <br>
     *         &nbsp;&nbsp;&nbsp;"\n"
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @param result the string buffer we will store the result in.
     * @param solution the solution we will print.
     * @see
     *   <a href=
     *      "../../srchtml/project3/SetOfSolutions.html#printSolutions"
     *      >Source Code
     *   </a>
     */
    protected void printSolutions(StringBuffer result, Constant[] solution)
    {
        assert result != null; 

        if(solution.length > 0){
            result.append("  " + variables[0] + "=" + solution[0]);

            int i = 1;        
            while(i < variables.length){
                result.append(", " + variables[i] + "=" + solution[i]);
                i++;
            };
            result.append('\n');
        };
    }

    /**
     * Returns whether or not the <b>SetOfSolutions</b> contains the
     * solution.
     *
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *       solution &ne; null 
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
     *       |solution| = |variables|
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *         result = &exist;<sub>i</sub> (0 &lt;= i AND i &lt;
     *             |setOfSolutions| &rArr; setOfSolutions[i] = solution)
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @param solution we want to check to see if this solution is in
     *                   this <b>SetOfSolutions</b>
     *
     * @return whether the solution is in this <b>SetOfSolutions</b>
     * @see
     *   <a href=
     *      "../../srchtml/project3/SetOfSolutions.html#contains"
     *      >Source Code
     *   </a>
     */
    private boolean contains(Constant[] solution)
    {
        assert solution != null &&
               solution.length == variables.length;

        boolean result = false;
        Iterator<Constant[]> iter = setOfSolutions.iterator();
        while(iter.hasNext() && !result){
            Constant[] constants = iter.next();
            boolean same = true;
            for(int i = 0; i < solution.length && same; i++){
                same = solution[i].equals(constants[i]);
            };
            result = same;
        };

        return result;
    }
}
