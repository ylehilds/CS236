package project3.lex;

/**
 * An enumeration of the different types of tokens.  Assumes you will use
 * version 1.5 (or higher) of the java compiler.
 *
 * <p>
 * The token types: LT, LE, GT, GE, EQ, NE, MULTIPLY, ADD, COMPARATOR, 
 *                  EXPRESSION, and WHITESPACE 
 *
 * <br/>
 * were needed for projects 1 and 2 but are not in the grammar for project 3.
 *
 * <p>
 * Domain:<br/>
 * &nbsp;&nbsp;{<br/>
 *     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 *     COLON, COLON_DASH, COMMA, EOF, FACTS, ID, LEFT_PAREN, <br/>
 *     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 *     PERIOD, Q_MARK, QUERIES, RIGHT_PAREN, RULES, SCHEMES, STRING <br/>
 * &nbsp;&nbsp;}
 *
 * <p>
 * Last Modified: 10 Jan 2006
 *
 * @author Scott N. Woodfield
 * @version 1.0
 * @see <a href="../../srchtml/lex/TokenType.html">Source Code</a>
 */
public enum TokenType
{
    EOF, ID, SCHEMES, FACTS, RULES, QUERIES, STRING, COLON,
    COLON_DASH, COMMA, LEFT_PAREN, PERIOD, Q_MARK, RIGHT_PAREN, UNDEFINED
}
