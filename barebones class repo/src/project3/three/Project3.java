package project3.three;

import project3.lex.Lex;

/**
 * The root of Project 3.
 *
 * <pre>
 * Domain Definition:
 *     datalogProgram : DatalogProgram
 * </pre>
 *
 * Last Modified: 21 Feb 2006
 *
 * @author Scott N. Woodfield
 * @version 1.0
 * @see
 *   <a href=
 *      "../../srchtml/project3/Project3.html"
 *      >Source Code
 *   </a>
 */
public class Project3
{
//Domain Implementation
    /**
     * The entire datalog program.
     * @see
     *   <a href=
     *      "../../srchtml/project3/Project3.html#datalogProgram"
     *      >Source Code
     *   </a>
     */
    public static DatalogProgram datalogProgram = null;
//Main Routine
    /**
     * The main routine.  All it does is call body.  We do this so we
     * can call "body" from the test routines in the test harnass.
     *
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *       The datalog program to be read in is syntatically correct.
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       The result of all the queries are printed to the screen.
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @param args the command line arguments.
     * @see
     *   <a href=
     *      "../../srchtml/project3/Project3.html#main"
     *      >Source Code
     *   </a>
     */
    public static void main(String[] args)
    {
        System.out.println(body(args));
    }

    /**
     * The real main routine.
     *
     * We've set up the main routine this way so we can test it from junit.
     * Junit calls a routine.  It is difficult to use junit to test an
     * application by calling the "main" routine.
     *
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *        The input file is syntatically correct.
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       The results of all the queries are printed out.
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @param args the command line arguments.
     *
     * @return a string representing the answers to the queries.
     * @see
     *   <a href=
     *      "../../srchtml/project3/Project3.html#body"
     *      >Source Code
     *   </a>
     */
    public static String body(String[] args)
    {
        String result = null;
        try{
            Lex lex = null;
            if(args.length == 0){
                lex = new Lex();
            } else {
                lex = new Lex(args[0]);
            };
        
            datalogProgram = new DatalogProgram(lex);
            StringBuffer strBuffer = new StringBuffer();
            datalogProgram.evaluateQueryList(strBuffer);
            result = strBuffer.toString();
        }catch(ParserException p){
            result = p == null ? null : p.toString();   
            System.out.println(p);
        }catch(Exception p){
            result = p == null ? null : p.toString();   
        };
        return result;
    }
}
