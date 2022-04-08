package project3.lex;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.StringReader;

/**
 * Conceptually we can think of a <b>File</b> as a sequence, <i>s</i>, of
 * <i>n</i> characters where <i>n</i> &gt;= 1.   <i>s</i>[0] is the 'front'
 * character of the <b>File</b>.  <i>s</i>[|<i>s</i>|-1] = File.EOF.
 *
 * <p>
 * A File also contains the current line number.
 *
 * <p>
 * Domain Definition:
 * <table>
 *   <tr>
 *     <td>
 *     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <i>s</i>
 *     </td>
 *     <td>
 *     : Sequence&lt;<b>Character</b>&gt;
 *     </td>
 *   </tr>
 *   <tr>
 *     <td>
 *     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; lineNumber
 *     </td>
 *     <td>
 *     : int
 *     </td>
 *   </tr>
 * </table>
 *    
 * <pre>
 * Instance Invariant:
 *     |<i>s</i>| &gt;= 1 AND <i>s</i>[|<i>s</i>|-1] = File.EOF
 * </pre>
 *
 * Last Modified: 10 Jan 2006
 *
 * @author Scott N. Woodfield
 * @version 1.0
 * @see <a href="../../srchtml/lex/File.html">Source Code</a>
 */
public class File
{
//Constants
    /**
     * The EOF character which is actually a -1. <i>s</i>[|<i>s</i>|-1] is
     * always equal to File.EOF
     */
    public static final char EOF = (char)-1;

//Domain Implementation
    // The domain is implemented as an input stream, "is", that holds
    // ther characters of s, s[1..|s|-1]. The domain also contains a single
    // character buffer, "c", that holds the character s[0].  It is possible for
    // "is" to be empty, but, "c" will always hold some character, even if it is
    // the File.EOF character. The domain also implements the lineNumber as an
    // integer.

    /**
     * The input stream.  It is either standard input or a file.  This
     * holds the characters <i>s</i>[1..|<i>s</i>|-2] + File.EOF.  
     * @see <a href="../../srchtml/lex/File.html#is">Source Code</a>
     */
    protected BufferedReader is = null;

    /**
     * The one character buffer.  c = <i>s</i>[0]. 
     * @see <a href="../../srchtml/lex/File.html#c">Source Code</a>
     */
    protected char c = '\0';

    /**
     * The current line number.
     * @see <a href="../../srchtml/lex/File.html#lineNumber">Source Code</a>
     */
    protected int lineNumber = 1;

//Constructors
    /**
     * The default constructor which sets up a <b>File</b> to be read from
     * standard input.
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
     *       <i>s</i> = System.in + File.EOF <br/>
     *       lineNumber = 1
     *     </td>
     *     <td valign=top>
     *       AND <br/>
     *       &nbsp;
     *     </td>
     *   </tr>
     * </table>
     * @see <a href="../../srchtml/lex/File.html#defaultConstructor">Source Code</a>
    */
    public File()
    {
        is = new BufferedReader(new InputStreamReader(System.in));
        popFront();
        lineNumber = 1;
    }

    /**
     * Creates a new File that reads from the file with the name "fileName".
     * If no such file exists, it reads from standard input.
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
     *       File with fileName exists &rArr;
     *           <i>s</i> = file with file name "fileName" + File.EOF <br/>
     *       File with fileName does not exist &rArr;
     *           <i>s</i> = System.in + File.EOF <br/>
     *       lineNumber = 1
     *     </td>
     *     <td valign=top>
     *       AND <br/>
     *       AND <br/>
     *       &nbsp;
     *     </td>
     *   </tr>
     * </table>
     *
     * @param fileName the name of the file we are to read the input from.
     * @see <a href="../../srchtml/lex/File.html#StdConstructor">Source Code</a>
     */
    public File(String fileName)
    {
        try{
            is = new BufferedReader(new FileReader(fileName));
        }catch(IOException e){
            is = new BufferedReader(new InputStreamReader(System.in));
        };
        popFront();
        lineNumber = 1;
    }

    /**
     * Creates a new File that reads from the given StringReader.
     * Used primarily for testing.
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
     *       stringReader = null &rArr;
     *           <i>s</i> = System.in + File.EOF <br/>
     *       stringReader &ne; null &rArr;
     *           <i>s</i> = stringReader + File.EOF <br/>
     *       lineNumber = 1
     *     </td>
     *     <td valign=top>
     *       AND <br/>
     *       AND <br/>
     *       &nbsp;
     *     </td>
     *   </tr>
     * </table>
     *
     * @param stringReader the reader we will read input from.
     * @see <a href="../../srchtml/lex/File.html#TestConstructor">Source Code</a>
     */
    public File(StringReader stringReader)
    {
        if(stringReader == null){
            is = new BufferedReader(new InputStreamReader(System.in));
        } else {
            is = new BufferedReader(stringReader);
        };
        popFront(); //initializes "c" with the first character of the input file
        lineNumber = 1;
    }

//Query
    /**
     * Returns a copy of <i>s</i>[0],
     * the first or current character in the File.
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
     *       <i>s</i>[0]
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @return <i>s</i>[0]
     * @see <a href="../../srchtml/lex/File.html#front">Source Code</a>
     */
    public char front()
    {
        return c;
    }

    /**
     * Returns a copy of the lineNumber.
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
     *       result = lineNumber
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     *
     * @return the lineNumber
     * @see <a href="../../srchtml/lex/File.html#lineNumber">Source Code</a>
     */
    public int getLineNumber()
    {
        return lineNumber;
    }

//Command
    /**
     * If |<i>s</i>| > 1 <i>popFront</i> removes the front character or
     * <i>s</i>[0] from <i>s</i>, otherwise it does nothing.
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
     *       |<i>s</i>| = 1 &rArr; <i>s</i> = <i>s'</i> <br/>
     *       |<i>s</i>| &gt; 1 &rArr;
     *            <i>s</i> = <i>s'</i>[1..|<i>s'</i>-1|]
     *     </td>
     *     <td valign=top>
     *       AND <br/>
     *       &nbsp;
     *     </td>
     *   </tr>
     * </table>
     * @see <a href="../../srchtml/lex/File.html#popFront">Source Code</a>
     */
    public void popFront()
    {
        if(c == '\n'){
           lineNumber++;
        };

        if(c != EOF){
            try{
                c = (char)is.read();
            }catch(IOException e){
                c = EOF;
            };
        };
    }
}
