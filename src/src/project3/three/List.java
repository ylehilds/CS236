package project3.three;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import project3.lex.*;

/**
 * The generalized <b>List</b> class.
 *
 * <pre>
 * Domain Definition:
 *     nodes : SequenceOf&lt;Node&gt;
 *
 * Instance Invariant:
 *     nodes &ne; null -- though it may be empty
 * </pre>
 *
 * Last Modified: 21 Feb 2006
 *
 * @author Scott N. Woodfield
 * @version 1.0
 * @see
 *   <a href=
 *      "../../srchtml/project3/List.html"
 *      >Source Code
 *   </a>
 */
abstract public class List
    extends Node
{
// Domain Implementation
    /**
     * The sequence is implemented an an arraylist.
     * @see
     *   <a href=
     *      "../../srchtml/project3/List.html#nodes"
     *      >Source Code
     *   </a>
     */
    protected ArrayList<Node> nodes;

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
     *       nodes <i>instanceof</i> ArrayList&lt;Node&gt;
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
             |nodes| == 0
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     * @see
     *   <a href=
     *      "../../srchtml/project3/List.html#DefaultConstructor"
     *      >Source Code
     *   </a>
     */
    public List()
    {
        nodes = new ArrayList<Node>();
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
     *       list &ne; null
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       |nodes| = |list.nodes| 
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
     *       &forall;<sub>i</sub> (0 &lt;= i AND i &lt; |nodes| &rArr;
     *                    nodes[i].equals(list.nodes[i] AND
     *                    nodes[i &ne; list.nodes[i]
     *                   )
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @param list the <b>List</b> we are going to copy.
     * @see
     *   <a href=
     *      "../../srchtml/project3/List.html#CopyConstructor"
     *      >Source Code
     *   </a>
     */
    public List(List list)
    {
        nodes = new ArrayList<Node>(list.nodes);
    }

    /**
     * CreateList is a general list constructor. It is customized in a
     * subclass by possibly overridding the methods:
     * 
     * <ul>
     *     <li>
     *     createNode(Lex) -- Should be overridden in every specialization.
     *     By defining it to create only instances of <b>X</b> (<b>X</b> must
     *     be a specialization of <b>Node</b>), then we can restrict the
     *     specialization of <b>List</b> to be a list of <b>X</b>.
     *     </li>
     *
     *     <li>
     *     getSeparator(Lex) -- When parsing a list, some lists are separated
     *     by commas and some are not.  This method defines whether it is comma
     *     separated or not.
     *     </li>
     *
     *     <li>
     *     isInFirstOfListElement(Lex) -- Different lists must start with
     *     different kinds of tokens (e.g. some must start with an
     *     <b>Identifier</b> and others may start with <b>Constant</b>s).
     *     This method defines what kind of <b>Token</b> a list must start
     *     with.
     *     </li>
     *
     *     <li>
     *     getLengthConstraint(Lex) -- Some lists are lists are lists of 0 or
     *     more elements.  Other lists are lists of 1 or more elements.  This
     *     is overridden to define which type of list we are woking with.
     *     </li>
     * </ul>
     * <p>    
     *     See their descriptions below.
     * <p>    
     * Normally, this code would go in the <i>List(Lex)</i> constructor, but,
     * in the subclasses this code needs to be executed after doing some
     * processing.  In a method we can do some processing and then call the
     * same method in the superclass.  But, with a constructor we must call
     * the constructor in the superclass first.  We are unable do any
     * processing or checking first. So, we have the constructor call a method 
     * method that does some processing and then calls <i>createList(Lex)</i>
     * method.  In some sense, <i>createList(Lex)</i> is the constructor, for
     * the class <b>List</b>.<p>
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
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *         a list of Nodes is created according to the 4 methods
     *               identified.
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @throws ParserException
     *
     * @param lex the lexical analyzer we will create the <b>List</b> from.j
     * @see
     *   <a href=
     *      "../../srchtml/project3/List.html#LexConstructor"
     *      >Source Code
     *   </a>
     */
    public void createList(Lex lex)
        throws ParserException
    {
        if(lex == null)throw new ParserException(
            "ERROR in List::createList(Lex)\n" +
            "    lex cannot be null");
          
        nodes = new ArrayList<Node>();
        boolean anotherNode = checkForFirstMemberOfTheList(lex);
        while(anotherNode){
            nodes.add(createNode(lex));
            anotherNode = checkForAnotherMemberOfTheList(lex);
        };
    }

//Queries
    /**
     * Converts the list to a string.  The method <i>getSeparator()</i>
     * defines the separator used in this list.  It returns null if there is no
     * separator.  It is overridden in a subclass if the subclass has a
     * separator, normally the comma.
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
     *       return a string representation of this list.
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @return the string representation of a <b>List</b>.
     * @see
     *   <a href=
     *      "../../srchtml/project3/List.html#toString"
     *      >Source Code
     *   </a>
     */
    public String toString()
    {
        StringBuffer result = new StringBuffer();
        for(int i = 0; i < nodes.size(); i++){
            result.append(nodes.get(i).toString());
            if(i < nodes.size() - 1){
                Token token = getSeparator();
                result.append(token == null ? "" : token.getValue());
            };
        };
        return result.toString();
    }

    /**
     * The nodes getter for a List. Mostly used in testing.
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
     *       result = nodes
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @return the implementation of a <b>List</b>.
     * @see
     *   <a href=
     *      "../../srchtml/project3/List.html#getNodes"
     *      >Source Code
     *   </a>
     */
    public ArrayList<Node> getNodes()
    {
        return nodes;
    }

    /**
     * Returns the lengths of the list.
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
     *       result = |nodes|
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @return the number of nodes in the <b>List</b>.
     * @see
     *   <a href=
     *      "../../srchtml/project3/List.html#size"
     *      >Source Code
     *   </a>
     */
    public int size()
    {
        return nodes.size();
    }

    /**
     * Returns an iterator for the nodes
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
     *       result = nodes.iterator()
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @return an iterator over the <b>List</b>
     * @see
     *   <a href=
     *      "../../srchtml/project3/List.html#iterator"
     *      >Source Code
     *   </a>
     */
    public Iterator<Node> iterator()
    {
        return nodes.iterator();
    }


//Commands -- none

//Auxillary Methods and Constants
    /**
     * Checks to see if there is a first member of the list.  Lists come in two
     * forms: list of length 1 or more and lists of length 0 or more.  The most
     * common is a list of 1 or more members.  If a list has 1 or more members,
     * then this method should be a method that is constant and always returns
     * true.  This is the default implementation of this method.  
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
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       getListConstraint = ONE_OR_MORE &rArr; result = true
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
     *       getListConstraint = ZERO_OR_MORE &rArr;
     *           result = checkForAnotherMemberOfTheList(lex)
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @throws ParserException
     *
     * @param lex the lexical analyzer we will check to for a first member of
     *        the <b>List</b>
     *
     * @return whether there must be a first member of the list.
     * @see
     *   <a href=
     *      "../../srchtml/project3/List.html#checkForFirstMemberOfTheList"
     *      >Source Code
     *   </a>
     */
    protected boolean checkForFirstMemberOfTheList(Lex lex)
        throws ParserException
    {
        //The only method to call this method should be createList(Lex).  In
        //that method we check to see if lex is null and, if so, throw an
        //exception.  Thus, lex should never be null.
        assert lex != null;

        boolean result = false;
        switch(getLengthConstraint()){
            case ONE_OR_MORE:
                result = true;
                break;
            case ZERO_OR_MORE:
                result = isInFirstOfListElement(lex);
                break;
        };
        return result;
    }

    /**
     * Determines whether the next token is of the proper Type.  We typically
     * use this method to determine whether we are to continue parsing the
     * elements of a list.  In most cases, we check to see if the next token
     * is an <i>Identifier</i>.  Thus, this is the default behavior.  In another
     * case we check to see if the token is an Argument.  In lists where there
     * is a separator, we check for the separator, and, if it exists,
     * consume it.
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
     *       getSeparator() == null &rArr; result = isInFirstOfListElement(lex)
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
     *       getSeparator() &ne; null &rArr;
     *         (lex.front().getTokenType() == getSeparator().getTokenType() AND
     *                        lex = lex'.popFront())
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @throws ParserException
     *
     * @param lex the lexical analyzer we will check to see if there is
     *              another member of the <b>List</b>
     *
     * @return whether there is a nother member of the list.
     * @see
     *   <a href=
     *      "../../srchtml/project3/List.html#checkForAnotherMemberOfTheList"
     *      >Source Code
     *   </a>
     */
    protected boolean checkForAnotherMemberOfTheList(Lex lex)
        throws ParserException
    {
        //The only method to call this method should be createList(Lex).  In
        //that method we check to see if lex is null and, if so, throw an
        //exception.  Thus, lex should never be null.
        assert lex != null;

        boolean result = false;
        Token separator = getSeparator();
        if(separator == null){ //this means there is no separator
            result = isInFirstOfListElement(lex);
        } else {
            result = lex.front().getTokenType() == separator.getTokenType();
            if(result){
                lex.popFront();
            };
        };
        return result;
    }

    /**
     * These are the different types of length contraints we can impose on
     * a list.
     * @see
     *   <a href=
     *      "../../srchtml/project3/List.html#LengthConstraint"
     *      >Source Code
     *   </a>
     */
    protected enum LengthConstraint {ONE_OR_MORE, ZERO_OR_MORE};

    // ------------------------------------------------------------------------
    // The methods to be overridden in a specialization
    // ------------------------------------------------------------------------

    /**
     * This actually creates the node of the proper type.  Almost every
     * specialization of this list creates a member of a different type,
     * <b>T</b>.  But, <b>T</b> must be a subclass of <b>Node</b>.  This method
     * is overridden to create a node of a the proper type.  Usually it looks
     * like this:
     * <p>
     *     &nbsp;&nbsp;&nbsp;return new T(lex);
     *
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *        the front part of the input file is a syntatically correct
     *               representation of an instance of <b>T<b>.
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       result = the new instance of <b>T</b>, and <b>Lex</b> have been
     *                updated appropriately.
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     * @see
     *   <a href=
     *      "../../srchtml/project3/List.html#createNode"
     *      >Source Code
     *   </a>
     */
    abstract protected Node createNode(Lex lex) throws ParserException;


    /**
     * If there is a separator, it returns a token representing the separator,
     * otherwise it returns null.  By default it returns null.  It needs to be
     * overridden for <b>FactList</b>, <b>RuleList</b>, and <b>QueryList</b>.  
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
     *        hasSeparator &rArr; result = appropriate separator
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
     *       &not; has Separator &rArr; result = null
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @return The separator between elements of the list. null means
     *         there is no separator.
     * @see
     *   <a href=
     *      "../../srchtml/project3/List.html#getSeparator"
     *      >Source Code
     *   </a>
     */
    protected Token getSeparator()
    {
        return null;
    }

    /**
     * If we have a list of elements of type <b>T</b>, this determines if
     * lex.front() is in the first of <b>T</b>.  Normally there is a set of
     * token types associated with a the first of of type <b>T</b>, but, in
     * this grammar the set only has a single type, usually, <b>Identifier</b>. 
     * Thus, the default check is to check for an <b>Identifer</b>.  In
     * <b>Predicate</b>s we override this method and check for <b>Argument</b>s.
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
     *       result = lex.front() <i>instanceof</> <b>Identifier</b>
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @param lex we check lex[0] to see if it is of the appropriate type.
     *
     * @return wheter lex[0] is in the first of <i>T</i> for this
     *         <b>List</b>.
     * @see
     *   <a href=
     *      "../../srchtml/project3/List.html#isInFirstOfListElement"
     *      >Source Code
     *   </a>
     */
    protected boolean isInFirstOfListElement(Lex lex)
    {
        // The method checkForAnotherMemberOfTheList(Lex) is the only
        // method that calls this method.  checkForAnotherMemberOfTheList(Lex)
        // assumes, and we have proved, the the program as it currently stands
        // will not allow lex to be null at this point.  Thus, we make the
        // assertion.
        assert lex != null ;

        return lex.front() instanceof Identifier;
    }

    /**
     * If the list must have one or more members, then return ONE_OR_MORE, else
     * return ZERO_OR_MORE.  By default we assume that a list must have one
     * or more elements.  It is possible to have lists with other contraints
     * on their lengths (e.g. 2 or more).  We have designed this so we
     * can change the enumerated type and change this method to accomodate other
     * contraints.  However, for this project, as it stands, we need only
     * return ONE_OR_MORE or ZERO_OR_MORE.
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
     *       the list must have one or more elements &rArr;
     *                   result = LengthConstraint.ONE_OR_MORE
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
     *        the list need not have one or more elements &rArr;
     *            result = LengthConstraint.ZERO_OR_MORE
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @return a <b>LengthConstraint</b> indicating wheter this <b>List</b>
     *         has one or more members or zero or more members.
     * @see
     *   <a href=
     *      "../../srchtml/project3/List.html#getLengthConstraint"
     *      >Source Code
     *   </a>
     */
    protected LengthConstraint getLengthConstraint()
    {
        return LengthConstraint.ONE_OR_MORE;
    }
}
