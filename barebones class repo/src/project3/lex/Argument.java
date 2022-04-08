package project3.lex;

import project3.three.Node;
import project3.three.ParserException;

/**
 * An <b>Argument</b> token.  This is not needed for assignment 1, but is
 * included in the <i>lex</i> package to make it easier to read the code
 * in project 3.
 *
 * <p>
 * Syntax : <b>Argument</b> &rarr; <i>Identifier</i> | <i> Constant</i>
 *
 * <p>
 * <pre>
 * Domain Definition:
 *     type       : TokenType
 *     value      : String
 *     lineNumber : Integer
 * 
 * Instance Invariant:
 *     &forall;<sub>x</sub> (x <i>instanceof</i> Argument &rArr;
 *                   x <i>instanceof</i> <i>Identifier</i> OR
 *                   x <i>instanceof</i> <i>Constant</i>
 *         )
 * </pre>
 *
 * Last Modified: 21 Feb 2006
 *
 * @author Scott N. Woodfield
 * @version 1.0
 * @see <a href="../../srchtml/lex/Argument.html">Source Code</a>
 */
abstract public class Argument 
    extends Token
{
//Domain Implementation
    //TokenType type       -- inherited from Token
    //String    value      -- inherited from Token
    //int       lineNumber -- inherited from Token

//Constructors
    /**
     * Constructs an <b>Argument</b> from a type and value.
     *
     * Actually this is only called from either of the two specializations:
     * {@link Identifier#Identifier(String, int) Identifier} or 
     * {@link Constant#Constant(String, int) Constant}. 
     *
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *       super.Precondition
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
     *     </td>
     *   </tr>
     * </table>
     *
     * @param type the type of the new argument.
     * @param value the value of the new argument.
     * @param lineNumber the lineNumber of the new argument.
     *
     * @throws ParserException
     * @see
     *   <a href="../../srchtml/lex/Argument.html#StandardConstructor">Source Code</a>
     */
    protected Argument(TokenType type, String value, int lineNumber)
        throws ParserException 
    {
        super(type, value, lineNumber);
    }

    /**
     * The copy constructor.
     *
     * Actually this is only called from either of the two specializations:
     * {@link Identifier#Identifier(String, int) Identifier} or 
     * {@link Constant#Constant(String, int) Constant}. 
     *
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *       super.Precondition
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
     *     </td>
     * </table>
     *
     * @param token the argument to be copied.
     * @see
     *   <a href="../../srchtml/lex/Argument.html#CopyConstructor">Source Code</a>
     */
    protected Argument(Token token)
    {
        super(token);
    }

//Queries

//Commands
    
    /**
     * Creates a specific copy of the argument depending on the type of the
     * argument.
     *
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *       argument &ne; null
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       argument <i>instanceof</i> <b>Identifier</b> &rArr;
     *           result = new Identifier(argument) <br/>
     *       argument <i>instanceof</i> <b>Constant</b> &rArr;
     *           result = new Constant(argument) <br/>
     *       &not; argument <i>instanceof</i> <b>Constant</b> AND
     *       &not; argument <i>instanceof</i> <b>Identifier</b> &rArr;
     *           result = null
     *     </td>
     *     <td valign=top>
     *       AND <br/>
     *       AND <br/>
     *       &nbsp;
     *     </td>
     *   </tr>
     * </table>
     *
     * @param argument the argument we are to copy.
     * @return a copy of the input parameter.
     * @throws ParserException
     * @see
     *   <a href="../../srchtml/lex/Argument.html#createCopy">Source Code</a>
     */
     static public Argument createCopy(Argument argument)
         throws ParserException
     {
         assert argument != null;
   
         Argument result = null;
         if(argument instanceof Identifier){
             result = new Identifier(argument);
         } else if(argument instanceof Constant){
             result = new Constant((Constant)argument);
         };
         return result;
     }

//Auxiallary Methods and Constants
}
