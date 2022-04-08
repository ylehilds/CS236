package project3.lex;

import java.io.StringReader;

import project3.three.ParserException;

/**
 * Logically Lex is a sequence of tokens, called "tokens", 
 * where we can look only at the front token or "tokens[0]".
 *
 * <p>
 * Syntax:
 * <table>
 *   <tr>
 *     <td><b>Lex</b></td>
 *     <td>&rarr;</td>
 *     <td><b>Token</b>+</td>
 *   </tr>
 *   <tr>
 *     <td><b>Token</b></td>
 *     <td>&rarr;</td>
 *     <td><i>Identifier</i>+ | <i>Constant</i> | <b>Keyword</b> |
 *         <b>Symbol</b> | <b>Whitespace</b> | <i>EOF</i> | <b>Undefined</b>
 *     </td>
 *   </tr>
 *   <tr>
 *     <td><i>Identifier</i></td>
 *     <td>&rarr;</td>
 *     <td><b>Letter</b> [ <b>Letter</b> | <b>Digit</b> ]* </td>
 *   </tr>
 *   <tr>
 *     <td><i>Constant</i></td>
 *     <td>&rarr;</td>
 *     <td>"'" [<b>Non-QuoteCharacter</b>*
 *             [["'""'"]+ <b>Non-QuoteCharacter</b>*]* "'"
 *     </td>
 *   </tr>
 *   <tr>
 *     <td><b>Keyword</b></td>
 *     <td>&rarr;</td>
 *     <td>"Schemes" | "Facts" | "Rules" | "Queries"</td>
 *   </tr>
 *   <tr>
 *     <td><b>Symbol</b></td>
 *     <td>&rarr;</td>
 *     <td>":" | ":-" | "(" | ")" | "," | "." | "?" </td>
 *   </tr>
 *   <tr>
 *     <td valign=top><b>Whitespace</b></td>
 *     <td valign=top>&rarr;</td>
 *     <td>Horizontal_Tab | Line_Feed | Vertical_Tab | Form_Feed | <br>
 *         Carriage_Return | File_Separator | Group_Separator | <br>
 *         Record_Separator | Unit_Separator
 *     </td>
 *   </tr>
 *   <tr>
 *     <td valign=top><b>Undefined</b></td>
 *     <td valign=top>&rarr;</td>
 *     <td><i>Any character not defined above</i> | <br>
 *         <i>A "-" without a previous ":"</i> | <br>
 *         "'" [<b>Non-QuoteCharacter</b>*
 *             [["'""'"]+ <b>Non-QuoteCharacter</b>*]* <i>EOF</i> 
 *     </td>
 *   </tr>
 * </table>
 * <pre>
 * Domain Definition:
 *     tokens : Sequence&lt;Token&gt;
 *
 * Instance Invariant:
 *     |tokens| &gt;= 1 AND
 *     tokens[|tokens|-1].getTokenType() = TokenType.EOF
 * </pre>
 *
 * Last Modified: 10 Jan 2006
 *
 * @author Scott N. Woodfield
 * @version 1.0
 * @see <a href="../../srchtml/lex/Lex.html">Source Code</a>
 */
public class Lex
{
//Domain Implementation
    // We implement tokens by storeing tokens[0..|tokens-1|] in the variable
    // file and storing tokens[0] in the one token buffer "token".

    /**
     * The "File" we will be reading characters from.  The "File" implements
     * Lex by holding <i>tokens</i>[1..|<i>tokens</i>|-1].
     * @see <a href="../../srchtml/lex/Lex.html#file">Source Code</a>
     */
    protected File         file  = null;

    /**
     * The front token or <i>tokens</i>[0].
     * @see <a href="../../srchtml/lex/Lex.html#token">Source Code</a>
     */
    protected Token        token = null;

//Constructors
    /**
     * The default constructor which reads characters from standard input.
     *
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *       first token is syntatically correct
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       file = new File() <br/>
     *       token = token created by popFront()
     *     </td>
     *     <td valign=top>
     *       AND <br/>
     *       &nbsp;
     *     </td>
     *   </tr>
     * </table>
     *
     * @throws ParserException if we find an invalid character
     * @see <a href="../../srchtml/lex/Lex.html#defaultConstructor">Source Code</a>
     */
    public Lex()
        throws ParserException
    {
        file = new File();
        popFront();
    }

    /**
     * The constructor which reads characters from a file.
     *
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *       first token is syntatically correct
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       file = new File(fileName) <br/>
     *       token = token created by popFront()
     *     </td>
     *     <td valign=top>
     *       AND <br/>
     *       &nbsp;
     *     </td>
     *   </tr>
     * </table>
     *
     * @param fileName the name of the file from which we will read
     *                 characters.
     * @see <a href="../../srchtml/lex/Lex.html#StdConstructor">Source Code</a>
     */
    public Lex(String fileName)
        throws ParserException
    {
        file = new File(fileName);
        popFront();
    }

    /**
     * The constructor which reads characters from a StringReader.
     * Used primarily for testing.
     *
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *       first token is syntatically correct
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       file = new File(stringReader) <br/>
     *       token = token created by popFront()
     *     </td>
     *     <td valign=top>
     *       AND <br/>
     *       &nbsp;
     *     </td>
     *   </tr>
     * </table>
     *
     * @param stringReader the <b>StringReader</b> from which we will read
     *                     characters.
     * @see <a href="../../srchtml/lex/Lex.html#TestConstructor">Source Code</a>
     */
    public Lex(StringReader stringReader)
        throws ParserException
    {
        file = new File(stringReader);
        popFront();
    }


//Queries
    /**
     * Returns the front or current token or <b>tokens</b>[0].
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
     *       result = token
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     * </table>
     * @return the front <b>Token</b>.
     * @see <a href="../../srchtml/lex/Lex.html#front">Source Code</a>
     */
    public Token front()
    {
        return token;
    }
   

//Commands
    /**
     * Logically this removes the front token (<b>tokens</b>[0]) from
     * <b>tokens</b>. 
     *
     * <p>
     * From an implementation point of view, we look at file.front() to
     * determine which kind of token we are going to create next and then
     * create it.  Notice that unlike project 1, we ignore whitespace.  Also,
     * if we find illegal characters, we throw an excpetion rather than return
     * a token.  Also, there are several tokens which were legal in projects
     * 1 and 2 but are now illegal.  See the definition of
     * {@link TokenType TokenType} for a list of these token types.
     *
     * <p>
     * <font size = 1>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <font size=3><b>Precondition</b></font>:
     *     </td>
     *     <td valign=bottom>
     *       After skipping whitespace, we are at the beginning of a valid
     *       token.
     *     </td>
     *     <td valign=bottom>
     *     </td>
     *   </tr>
     *   <tr><td>&nbsp</td></tr>
     *   <tr>
     *     <td valign=top>
     *       <font size = 3><b>Logical Postcondition</b></font>:
     *     </td>
     *     <td valign=bottom>
     *       |<i>tokens</i>|  = 1 &rArr;
     *         (<i>tokens</i> = <i>tokens'</i> AND
     *          <i>tokens</i>[0].getTokenType() = TokenType.EOF)<br/>
     *       |<i>tokens</i>| &ne; 1 &rArr; <i>tokens</i> =
     *         <i>tokens'</i>[1..|<i>tokens'</i>|-1]
     *     </td>
     *     <td valign=top>
     *       AND </br>
     *       <br/>
     *     </td>
     *   </tr>
     *   <tr><td>&nbsp</td></tr>
     *   <tr>
     *     <td valign=middle>
     *       <font size=3><b>Implementation Postcondition</b></font>:
     *     </td>
     *     <td valign=bottom>
     *       file.front() = "'" &rArr; token = getConstant()              <br/>
     *     </td>
     *     <td valign=bottom>
     *       AND
     *     </td>
     *   </tr>
     *   <tr>
     *     <td>
     *       &nbsp;
     *     </td>
     *     <td valign=top>
     *     isLetter(file.front()) &rArr; token = getIdentifierOrKeyword() <br/>
     *     </td>
     *     <td valign=top>
     *       AND
     *     </td>
     *   </tr>
     *   <tr>
     *     <td>
     *       &nbsp;
     *     </td>
     *     <td valign=top>
     *     file.front() = ',' &rArr; token =
     *         (new (TokenType.COMMA, ",", File'.getLineNumber()) AND
     *          file = file'.popFront())                                  <br/>
     *     </td>
     *     <td valign=top>
     *       AND
     *     </td>
     *   </tr>
     *   <tr>
     *     <td>
     *       &nbsp;
     *     </td>
     *     <td valign=top>
     *     file.front() = '.' &rArr; token =
     *         (new (TokenType.PERIOD, ".", File'.getLineNumber()) AND
     *          file = file'.popFront())                                  <br/>
     *     </td>
     *     <td valign=top>
     *       AND
     *     </td>
     *   </tr>
     *   <tr>
     *     <td>
     *       &nbsp;
     *     </td>
     *     <td valign=top>
     *     file.front() = '(' &rArr; token =
     *         (new (TokenType.LEFT_PAREN, "(", File'.getLineNumber()) AND
     *          file = file'.popFront())                                  <br/>
     *     </td>
     *     <td valign=top>
     *       AND
     *     </td>
     *   </tr>
     *   <tr>
     *     <td>
     *       &nbsp;
     *     </td>
     *     <td valign=top>
     *     file.front() = ')' &rArr; token =
     *         (new (TokenType.RIGHT_PAREN, ")", File'.getLineNumber()) AND
     *          file = file'.popFront())                                  <br/>
     *     </td>
     *     <td valign=top>
     *       AND
     *     </td>
     *   </tr>
     *   <tr>
     *     <td>
     *       &nbsp;
     *     </td>
     *     <td valign=top>
     *     file.front() = '?' &rArr; token =
     *         (new (TokenType.Q_MARK, "?", File'.getLineNumber()) AND
     *          file = file'.popFront())                                  <br/>
     *     </td>
     *     <td valign=top>
     *       AND
     *     </td>
     *   </tr>
     *   <tr>
     *     <td>
     *       &nbsp;
     *     </td>
     *     <td valign=top>
     *     file.front() = ':' &rArr; 
     *         (file[1] = '-' &rArr; token = 
     *            &nbsp;&nbsp;&nbsp;
     *            (new (TokenType.COLON_DASH, ":-", File'.getLineNumber()) AND
     *             file = file'.popFront().popFront()) <br/>
     *          file[1] &ne; '-' &rArr; token = 
     *            &nbsp;&nbsp;&nbsp;
     *            (new (TokenType.COLON, ":", File'.getLineNumber()) AND
     *             file = file'.popFront()) 
     *         )                                                          <br/>
     *     </td>
     *     <td valign=top>
     *       AND
     *     </td>
     *   </tr>
     *   <tr>
     *     <td>
     *       &nbsp;
     *     </td>
     *     <td valign=top>
     *     file.front() = File.EOF &rArr;
     *         new (TokenType.EOF, "", File'.getLineNumber())
     *     </td>
     *     <td valign=top>
     *       AND
     *     </td>
     *   </tr>
     * </table>
     * </font> 
     *
     * @throws ParserException When we find an invalid character or
     *                         unterminated string.
     * @see <a href="../../srchtml/lex/Lex.html#popFront">Source Code</a>
     */
    public void popFront()
        throws ParserException
    {
        skipWhiteSpace();
        if(file.front() == file.EOF){  
            token = new Token(TokenType.EOF, "", file.getLineNumber());
        } else if(file.front() == '\''){
            token = getConstant();
        } else if(Character.isLetter(file.front())){
            token = getIdentifierOrKeyword();
        } else if(file.front() == ':'){ //process ":-" or ":'
            int line = file.getLineNumber();
            file.popFront();
            if(file.front() == '-'){
                file.popFront();
                token = new Token(TokenType.COLON_DASH, ":-", line);
            } else {
                token = new Token(TokenType.COLON, ":", line);
            };
        } else { //process single character tokens
            int line = file.getLineNumber();
            switch(file.front()){
                case ',': token = new Token(TokenType.COMMA, ",", line);
                          break;
                case '.': token = new Token(TokenType.PERIOD, ".", line);
                          break;
                case '(': token = new Token(TokenType.LEFT_PAREN,"(",line);
                          break;
                case ')': token = new Token(TokenType.RIGHT_PAREN,")",
                                             line);
                          break;
                case '?': token = new Token(TokenType.Q_MARK, "?", line);
                          break;
                default:  throw new ParserException(
                             "Error on line " + line + ".\n" +
                              "Invalid character, '" + file.front() + 
                              "', found.");
            };
            file.popFront();
        };
    }

//Auxillary Methods
    /**
     * Skips all of the white space at the beginning of "file".
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
     *       <i>file</i> = <i>file'</i>[n..|<i>file'</i>|-1] <br/>
     *       &forall; (0 &lt;= i AND i &lt; n &rArr; isWhitespace(file'[i]))
     *       <br/>
     *       &not; isWhitespace(file'[n])
     *     </td>
     *     <td valign=top>
     *       AND <br/>
     *       AND <br/>
     *       &nbsp;
     *     </td>
     *   </tr>
     * </table>
     * @see <a href="../../srchtml/lex/Lex.html#skipWhitespace">Source Code</a>
     */
    protected void skipWhiteSpace()
    {
        while(Character.isWhitespace(file.front())){
            file.popFront();
        };
    }


    /**
     * Gets a String Token.  A string token begins with a "'" followed by
     * zero or more characters and is terminated by a matching "'".  A "'"
     * can show up inside a string if it is represented as a double single 
     * quote "''".
     *
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *       <i>file</i>.front() = '\'' <br/>
     *       this is the beginning of a terminated string.
     *     </td>
     *     <td valign=top>
     *       AND <br/>
     *       &nbsp;
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       result = new Token(TokenType.STRING,
     *                                   <i>file</i>'[1..n-1],
     *                                   <i>file</i>'.getLineNumber())  <br/>
     *                <i>file</i> = <i>file</i>'[n+1..|<i>file</i>'-1|] <br/>
     *                <i>file</i>'[n] = "'"                             <br/>
     *                Every "'" in <i>file</i>'[1..n-1] must be part of a
     *                double quote
     *     </td>
     *     <td valign=top>
     *       AND <br/>
     *       AND <br/>
     *       AND <br/>
     *       &nbsp;
     *     </td>
     *   </tr>
     * </table>
     *
     * @throws ParserException -- When we find an unterminated String.
     *
     * @return the token containing the string found
     * @see <a href="../../srchtml/lex/Lex.html#getConstant">Source Code</a>
     */
    protected Constant getConstant()
        throws ParserException
    {
        //PreCondition: file.front() == '\''
        int line = file.getLineNumber();
        if(file.front() != '\'')
            throw new ParserException("Error on line " + line + ".\n" +
                                "A string must start with a '.");


        file.popFront(); // throw away the leading quote

        StringBuffer result = new StringBuffer();
        boolean foundEnd = false;
        while(!foundEnd && file.front() != file.EOF){
            if(file.front() == '\''){
                file.popFront();
                if(file.front() == '\''){  //found a legal double quote
                    result.append('\'');
                    file.popFront();
                } else {
                    foundEnd = true;       //found the end of the quote
                };
            } else {
                result.append(file.front());
                file.popFront();
            };
        };
       
        Constant resultToken = null;
        if(foundEnd){
            resultToken = new Constant(result.toString(),line);
        } else {
            throw new ParserException("Error on line " + line + ".\n" +
                                "Unterminated string found");
        };
        return resultToken;
    }

    /**
     * Create an identifier Token (if it is actually a keyword, convert it
     * to a keyword).  The key words are: "Schemes", "Facts", "Rules", and
     * "Queries".  Keywords are case insensitive.
     *
     * @return the identifier or keyword found
     *
     * <p>
     * <table>
     *   <tr>
     *     <td valign=top>
     *       <b>Precondition</b>:
     *     </td>
     *     <td valign=top>
     *       isLetter(<i>tokens</i>[0])
     *     </td>
     *     <td valign=top>
     *     </td>
     *   </tr>
     *   <tr>
     *     <td valign=top>
     *       <b>Postcondition</b>:
     *     </td>
     *     <td valign=top>
     *       <i>file</i> = <i>file'</i>[n..|<i>file'</i>|-1]      <br/>
     *     </td>
     *     <td>
     *       AND
     *     </td>
     *   </tr>
     *   <tr>
     *     <td>&nbsp;</td>
     *     <td>
     *       &forall;<sub>i</sub> (1 &lt;= i AND i &lt; n &rArr;
     *           isLetterOrDigit(<i>file</i>[i])
     *     </td>
     *     <td>
     *       AND
     *     </td>
     *   </tr>
     *   <tr>
     *     <td>&nbsp;</td>
     *     <td>
     *       &not; isLetterOrDigit(<i>file</i>[n])                  <br/>
     *     </td>
     *     <td>
     *       AND
     *     </td>
     *   </tr>
     *   <tr>
     *     <td>&nbsp;</td>
     *     <td>
     *       str = <i>file'</i>[0..n-1]                           <br/>
     *     </td>
     *     <td>
     *       AND
     *     </td>
     *   </tr>
     *   <tr>
     *     <td>&nbsp;</td>
     *     <td>
     *       line = file'.getLineNumber()                         <br/>
     *     </td>
     *     <td>
     *       AND
     *     </td>
     *   </tr>
     *   <tr>
     *     <td>&nbsp;</td>
     *     <td>
     *       str.equalsIgnoreCase("Schemes") &rArr;
     *       result = new Token(TokenType.SCHEMES, str, line) <br/>
     *     </td>
     *     <td>
     *       AND
     *     </td>
     *   </tr>
     *   <tr>
     *     <td>&nbsp;</td>
     *     <td>
     *       str.equalsIgnoreCase("Facts") &rArr;
     *       result = new Token(TokenType.FACTS, str, line)   <br/>
     *     </td>
     *     <td>
     *       AND
     *     </td>
     *   </tr>
     *   <tr>
     *     <td>&nbsp;</td>
     *     <td>
     *       str.equalsIgnoreCase("Rules") &rArr;
     *       result = new Token(TokenType.RULES, str, line)   <br/>
     *     </td>
     *     <td>
     *       AND
     *     </td>
     *   </tr>
     *   <tr>
     *     <td>&nbsp;</td>
     *     <td>
     *       str.equalsIgnoreCase("Queries") &rArr;
     *       result = new Token(TokenType.QUERIES, str, line) <br/>
     *     </td>
     *     <td>
     *       AND
     *     </td>
     *   </tr>
     *   <tr>
     *     <td>&nbsp;</td>
     *     <td>
     *       otherwise &rArr;
     *       result = new Token(TokenType.ID, str, line) 
     *     </td>
     *   </tr>
     * </table>
     * @see <a href="../../srchtml/lex/Lex.html#getId">Source Code</a>
     */
    protected Token getIdentifierOrKeyword()
        throws ParserException
    {

        //The string may cover many lines, but, the lineNumber of the token
        //is where the token starts. So, we get the lineNumber before we do
        //anything else.
        int line = file.getLineNumber();

        StringBuffer result = new StringBuffer();
        while(Character.isLetterOrDigit(file.front())){
            result.append(file.front());
            file.popFront();
        };
            
        String value = result.toString(); 

        //This checks to see if the identifier is actually a key word.:wq
        Token resultToken = null;
        if(value.equalsIgnoreCase("Schemes")){
            resultToken = new Token(TokenType.SCHEMES, value, line);
        } else if(value.equalsIgnoreCase("Facts")){
            resultToken = new Token(TokenType.FACTS, value, line);
        } else if(value.equalsIgnoreCase("Rules")){
            resultToken = new Token(TokenType.RULES, value, line);
        } else if(value.equalsIgnoreCase("Queries")){
            resultToken = new Token(TokenType.QUERIES, value, line);
        } else {
            resultToken = new Identifier(value, line);
        };

        return resultToken;
    }
}
