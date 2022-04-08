package project3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import project3.lex.Argument;
import project3.lex.Constant;
import project3.lex.Identifier;
import project3.lex.Lex;
import project3.lex.Token;

import project3.three.*;

/**
 * A list of <b>Predicate</b>s.
 * 
 * <p>
 * Syntax: <b>PredicateList</b> -&rarr;<br>
 *             <b>Predicate</b> ["," <b>Predicate</b> ]*
 *
 * <p>
 * There are four properties that we need to define for a
 * <b>PredicateList</b>:
 *
 * <ol>
 *    <li>
 *    A <b>PredicateList</b> contains a list of <b>Predicate</b>.
 *    It is implemented as an <b>ArrayList<Node></b>.  We enforce the fact that
 *    it is a list of <b>Predicate</b> by only creating elements for the
 *    list using the <i>createNode(Lex)</i> method.  The <i>createNode(Lex)</i>
 *    method below creates only <b>Predicate</b>s. A <b>Predicate</b> is an
 *    instance of type <b>Node</b> so it can be added to an ArrayList<Node>.
 *    </li>
 *
 *    <li>
 *    To determine whether to parse another <b>Predicate</b> and add it
 *    to the list we need to know whether lex.front() is in the first of
 *    <b>Predicate</b>.  This is implemented by the method
 *    <i>isInFirstOfListElement(Lex)</i>.  The first of <b>Predicate</b>
 *    is {<i>Identifier</i>}.  This is the default value and is defined in
 *    the <i>List::isInFirstOfListElement(Lex)</i>.   Thus, we do NOT override
 *    it here.
 *    </li>
 * 
 *    <li>
 *    A <b>PredicateList</b> is a list of one-or-more elements.  This is
 *    the default value of a list and defined in
 *    <i>List::getLengthConstraint()</i>. We do NOT override it here.
 *    </li>
 * 
 *    <li>
 *    The elements of a <b>PredicateList</b> are separated by commas.
 *    This is defined in <i>getSeparator()</i> below.
 *    </li>
 * </ol>
 *
 * <pre>
 * Domain Definition
 *     predicateList       : Sequence&lt;<b>Predicate</b>&gt;
 *     variableInformation : VariableInformation 
 *
 * Instance Invariant:
 *     |predicateList| &gt; 0 AND
 *     variableInformation &ne; null
 * </pre>
 *
 * Last Modified: 21 Feb 2006
 *
 * @author Scott N. Woodfield
 * @version 1.0
 * @see
 *   <a href=
 *      "../srchtml/project3/PredicateList.html"
 *      >Source Code
 *   </a>
 */
public class PredicateList
    extends List
{

    /**
     * Recursively computes every combination of consants for the variables in
     * the predicate list and checks to see if any combination makes all of
     * the predicates in the predicate list true.  A combination of constants
     * that makes evey <b>Predicate</b> in the <b>PredicateList</b> true is
     * called a solution.
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
     *       result =
     *       There exists a combination of constant substitutions, <i>S</i>,
     *       for the variables in <i>variables</i>[i..|<i>variables</i>-1],
     *       such that every predicate in the predicate list is true.
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @throws ParserException
     *
     * @param i the ith variable for which we need to substitute constants
     *            from the domain.
     *
     * @return true iff we found a solution.
     * @see
     *   <a href=
     *      "../srchtml/project3/PredicateList.html#createAllCombinations"
     *      >Source Code
     *   </a>
     */
    protected boolean createAllCombinations(int i)
        throws ParserException
    {
System.out.println("Implement PredicateList.createAllCombinations");
		return true;
    }

    /**
     *  Checks to see if we can prove whether this predicate list is true or
     *  not.
     *
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *       allConstants()
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *        result &rArr;
     *          for every <b>Predicate</b> in this predicate list
     *            there exists a <b>Fact</b> or <b>Rule</b> that can
     *            prove this true.
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
     *       &not; result &rArr;
     *        there exits a  <b>Predicate</b> in this predicate list
     *            such that no <b>Fact</b> or <b>Rule</b> can prove it true.
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @throws ParserException
     *
     * @return true iff every <b>Predicate</b> in this <b>PredicateList</b>
     *              can be shown to be true for for the substitutions we have
     *              made.
     * @see
     *   <a href=
     *      "../srchtml/project3/PredicateList.html#checkToSeeIfTrue"
     *      >Source Code
     *   </a>
     */
  
    protected boolean checkToSeeIfTrue()
        throws ParserException
    {
System.out.println("Implement PredicateList.checkToSeeIfTrue");
		return true;
    }



////////////////////////////////////////////////////////////////////////////////////////
// THE REST OF THE CLASS IS DOWN HERE.
// YOU'LL NEED TO READ ABOUT THE CLASS VARIABLES AND METHODS.
// GOTO THE PROJECT3-API TO READ SOMETHING MORE CLEAR.
////////////////////////////////////////////////////////////////////////////////////////



//Domain Implementation
//  ArrayList<Node> nodes; -- implements Sequence<Predicate>
//                         -- inherited from List through DatalogSegment.
    /**
     * For every unique variable in the predicate list, this records the
     * location or locations for that variable.
     * @see
     *   <a href=
     *      "../srchtml/project3/PredicateList.html#variableInformation"
     *      >Source Code
     *   </a>
     */
    protected VariableInformation variableInformation = null;

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
     *       List::List().Precondition
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       List::List().Postcondition
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     * @see
     *   <a href=
     *      "../srchtml/project3/PredicateList.html#DefaultConstructor"
     *      >Source Code
     *   </a>
     */
    public PredicateList()
    {
        super();
    }
   
    /**
     * Creates a <b>PredicateList</b> from the tokens at the front of lex.
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
     *       &exist;<sub>i</sub> (ValidPredicateList(lex[0..i]) 
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       super.PostCondition()
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
     *       initializeVariableInformation.Postcondition
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @throws ParserException
     *
     * @param lex the lexical analyzer we will create the <b>PredicateList</b>
     *        from.
     * @see
     *   <a href=
     *      "../srchtml/project3/PredicateList.html#LexConstructor"
     *      >Source Code
     *   </a>
     */
    public PredicateList(Lex lex)
        throws ParserException
    {
        createList(lex);
        initializeVariableInformation();
    }

    /**
     * Copy constructor.
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
     *       result.equals(predicateList) 
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
     *       result &ne; predicateList
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @throws ParserException
     *
     * @param predicateList the <b>PredicateList</b> we are going to
     *                              copy.
     * @see
     *   <a href=
     *      "../srchtml/project3/PredicateList.html#copyConstructor"
     *      >Source Code
     *   </a>
     */
    public PredicateList(PredicateList predicateList)
        throws ParserException
    {
        super();
        assert predicateList != null;
        //Need to do a deeper copy here than in the List.  We need to make
        //Make new copies of the Predicates so that when we change them we
        //don't change the originals.
        Iterator<Node> iter = predicateList.nodes.iterator();
        while(iter.hasNext()){
            nodes.add(new Predicate((Predicate)iter.next()));
        };
        initializeVariableInformation();
    }

//Queries
    /**
     * Check to see if two PredicateLists are equal.
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
     *       result = o &ne; null 
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
     *       o <i>instanceof</i> <b>PredicateList</b> 
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
     *       |o| = |this| 
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
     *       &forall;<sub>i</sub> (0 &lt;= i AND i &lt; |o| &rArr;
     *          this[i].equals(o[i]))
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @param o the object we are going to compare this object to.
     *
     * @return whether or not the two objects are equal.
     * @see
     *   <a href=
     *      "../srchtml/project3/PredicateList.html#equals"
     *      >Source Code
     *   </a>
     */
    public boolean equals(Object o)
    {
        boolean result = o != null && o instanceof PredicateList;
        if(result){
            Iterator<Node> iter1 = nodes.iterator();
            Iterator<Node> iter2 = ((PredicateList)o).nodes.iterator();
            while(iter1.hasNext() && iter2.hasNext() && result){
                result = iter1.next().equals(iter2.next());
            };
            result = result && iter1.hasNext() == iter2.hasNext();
        };
        return result;
    }

    /**
     * Evaluate this predicate list.
     *
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *       variableInformation &ne; null
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       result = 
     *         Exists combination of constant substitutions, <i>S</i>, for the
     *         variables in in the predicate list such that when each element of
     *         <i>S</i> is substituted for the corresponging variables in the
     *         predicate list, every predicate in the predicate list is true.
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @throws ParserException
     *
     * @return true iff a valid set of substitutions for free variables in
     *         this <b>PredicateList</b>
     * @see
     *   <a href=
     *      "../srchtml/project3/PredicateList.html#evaluate"
     *      >Source Code
     *   </a>
     */
    public boolean evaluate()
        throws ParserException
    {
        assert variableInformation != null;

        //We could have passed the variableIterator as a parameter in the
        //recursion but choose to make it a variable global to the recursion.
        variables = variableInformation.getVariables();
        return createAllCombinations(0);
    }

//Commands
    /**
     * Set the variables in the predicate list which correspond to the 
     * variables in the head of the rule that contains this predicate list.
     * Set them to the constants that came from unifying the head with a
     * predicate list.
     *
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *       variableValuePairs &ne; null
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       &forall; Variable v in variableValuePairs
     *         (The location in the predicateList where v appears, has been
     *          replaced with the constant in the variableValuePairs that
     *          corresponds to v
     *         )
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @throws ParserException
     *
     * @param variableValuePairs a mapping of variables to constants for every
     *                             variable in <b>Head</b> of <b>Rule</b> this
     *                             <b>PredicateList</b> belongs to.
     * @see
     *   <a href=
     *      "../srchtml/project3/PredicateList.html#setVariables"
     *      >Source Code
     *   </a>
     */
    public void setVariables(HashMap<Identifier, Constant> variableValuePairs)
        throws ParserException
    {
        assert variableValuePairs != null;

        Iterator<Identifier> variableIterator =
            variableValuePairs.keySet().iterator(); 
        while(variableIterator.hasNext()){
            Identifier variable = variableIterator.next();
            setVariableToValue(variable, variableValuePairs.get(variable));
            variableInformation.remove(variable);
        };
    }

    /**
     * Everyplace in the predicate list where variable appears, it is
     * replaced by the given value.
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
     *       Everyplace that variable appeared in the predicateList
     *       it has been replaced by the given value.
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @throws ParserException
     *
     * @param variable the variable in this <b>PredicateList</b> we are going
     *                   to change to "value".
     * @param value the constant we are replace every instance of "variable"
     *                with.
     * @see
     *   <a href=
     *      "../srchtml/project3/PredicateList.html#setVariableToValue"
     *      >Source Code
     *   </a>
     */
    public void setVariableToValue(Identifier variable, Constant value)
        throws ParserException
    {
        assert variable != null || value != null;

        Iterator<Location> locationIter =
            variableInformation.getLocationsFor(variable);
        while(locationIter != null && locationIter.hasNext()){
            Location location = locationIter.next();
            Predicate predicate =
                (Predicate)nodes.get(location.getPredicateIndex());
            predicate.set(location.getLocationInPredicate(), value);
        }
    }

//Auxillary Methods and Constants
    /**
     * As we recursively compute all combinations of constants from the
     * domain, we need to iterate over all all variables in the predicate
     * list, this holds the list of variables.  We place it here so we don't
     * have to pass it as a parameter.
     * @see
     *   <a href=
     *      "../srchtml/project3/PredicateList.html#variables"
     *      >Source Code
     *   </a>
     */
    protected Identifier[] variables = null;

    /**
     * For <b>PredicateList</b> this does nothing.  It is a place holder
     * for something that a query must do when recursing.  That is,
     * if there is a combinations of constants that makes the query true,
     * save the combination as an answer.  But, for a <b>PredicateList</b>
     * we nothing.
     *
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *       true
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       true
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @throws ParserException
     * @see
     *   <a href=
     *      "../srchtml/project3/PredicateList.html#saveResult"
     *      >Source Code
     *   </a>
     */
    protected void saveResult()
        throws ParserException
    {
        //We don't save results when evaluating predicate lists.
        //We will when we evaluate a Query.  This method must be overloaded
        //in the Query class.
    }

    /**
     * Compute and store the locations for every variable in the predicate
     * list.
     *
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *        none
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       For every variable v, at location l in the predicate list,
     *       variableInformation.getLocationsFor(v).contains(l)
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
     *       For every variable v and location l
     *       variableInformation.getLocationsFor(v).contains(l)
     *       &rArr; the predicate list contains variable v at location l
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     * @see
     *   <a href=
     *      "../srchtml/project3/PredicateList.html#setUpVariableLocMapping"
     *      >Source Code
     *   </a>
     */
    protected void setUpVariableLocationMapping()
    {
        int predicateLocation = 0;
        Iterator<Node> nodeIterator = nodes.iterator();
        while(nodeIterator.hasNext()){
            Predicate predicate = (Predicate)nodeIterator.next();
            int argumentLocation = 0;
            Iterator<Node> argumentIterator = predicate.getNodes().iterator();
            while(argumentIterator.hasNext()){
                Argument argument = (Argument)argumentIterator.next();
                if(argument instanceof Identifier){
                    Location location = new Location(predicateLocation,
                                                     argumentLocation);
                    variableInformation.addLocationFor((Identifier)argument,
                                                       location);
                };
                argumentLocation++;
            };
            predicateLocation++;
        };

    }

    /**
     * Setup the <b>PredicateList</b> with all information as to a variable and
     * its locations in the predicate list.  In this case it is all stored
     * in a variable of type <b>VariableInformation</b> which stores all of the
     * locations of a variable in the predicate list.  In <b>Query</b> we will
     * override this method so that all information is stored in a
     * <b>QueryVariableInformation</b> which is a subclass of
     * <b>VariableInformation</b>.  <b>QueryVariableInformatin</b> includes
     * the added information of what the current value is for each variable in
     * the information list.  This latter information is used for deriving and
     * printing the solutions for a <b>Query</b>.
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
     *       variableInformation <i>instanceof</i> <b>VariableInformation</b>
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
     *      "../srchtml/project3/PredicateList.html#initializeVariableInfo"
     *      >Source Code
     *   </a>
     */
    protected void initializeVariableInformation()
    {
        variableInformation = new VariableInformation();
        setUpVariableLocationMapping();
    }

    /**
     * Should we continue finding all combinations of constants,  if we
     * are recursing over a predicate list, then we can stop the first time
     * we find a true one, if this is a query, then we never stop.
     * Since this is a <b>PredicateList</b> we stop when we find the first valid
     * substitution.  We will need to override this in <b>Query</b>.
     *
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *       validSolutionFound is a value that will be used.
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       result = &not; validSolutionFound 
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   </tr>
     * </table>
     *
     * @param validSolutionFound indicates whether or not we found a valid
     *                             solution.
     *
     * @return true iff we did NOT find a valid solution.
     * @see
     *   <a href=
     *      "../srchtml/project3/PredicateList.html#validSolutionFound"
     *      >Source Code
     *   </a>
     */
    protected boolean keepOnGoing(boolean validSolutionFound)
    {
        return !validSolutionFound;
    }

    /**
     * Check to see if all of the arguments of all <b>Predicate</b>s in
     * this <b>PredicateList</b> are constants.
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
     *       result = &forall<sub>x</sub>
     *        (x &isin; predicateList &rArr; x.allConstants())
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
     * @return true iff every <b>Argument</b> in every <b>Predicate</b> of this
     *              <b>PredicateList</b> is a <i>Constant</i>.
     * @see
     *   <a href=
     *      "../srchtml/project3/PredicateList.html#allConstants"
     *      >Source Code
     *   </a>
     */
    protected boolean allConstants()
    {
        boolean result = true;
        Iterator<Node> iter = nodes.iterator();
        while(result && iter.hasNext()){
            result = ((Predicate)iter.next()).allConstants();
        };
        return result;
    }

    //Methods of List that need to be overridden to define a list of
    // one or more Predicate separated by commas.
    /**
     * Creates a <b>Predicate</b>.
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
     *       lex[0..i] contains a <b>Predicate</b>
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       result = new Predicate(lex)
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @throws ParserException
     *
     * @param lex the lexical analyzer we will create the <b>Predicate</b>
     *            from.
     *
     * @return the <b>Predicate</b> we created.
     * @see
     *   <a href=
     *      "../srchtml/project3/PredicateList.html#createNode"
     *      >Source Code
     *   </a>
     */
    protected Node createNode(Lex lex)
        throws ParserException
    {
        return new Predicate(lex);
    }

    /**
     * Returns a separator, in this case Token.COMMA
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
     *       result = Token.COMMA
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @return a Comma token.
     * @see
     *   <a href=
     *      "../srchtml/project3/PredicateList.html#getSeparator"
     *      >Source Code
     *   </a>
     */
    protected Token getSeparator()
    {
        return Token.COMMA;
    }
}
