package testlex;

import junit.framework.TestCase;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;

import project3.lex.Token;
import project3.lex.TokenType;
import project3.three.ParserException;

/**
 * TokenTest is a JUnit test for Token
 */
public class TokenTest
    extends TestCase
{
    /**
     * Constructs a test case for the test specified in the name argument.
     */
    public TokenTest (String name)
    {
        super (name);
        /*
         * This constructor should not be modified. Any initialization code
         * should be placed in the setUp() method instead.
         */
    }

    //=========================================================================
    //Test Constructors
    //=========================================================================


    /**
     * Test Constructor: Token(TokenType, String)
     */
    public void testTokenTypeStringConstructor(){
        assertTrue(Token.COMMA.getTokenType() == TokenType.COMMA);
        assertTrue(Token.COMMA.getValue().equals(","));
        assertTrue(Token.COMMA.getLineNumber() == -1);
    }

    /**
     * Test Constructor: Token(TokenType, String, int).  Also tests
     * getTokenType(), getValue(), getLineNumber(), 
     * checkConstructorPrecondition(TokenType, String, int), and
     * allTheRestMustBeLettersOrDigits(String)
     */
    public void testTokenTypeStringStringLengthConstructor(){
        //Testing throwing of exception for violation of precondition
        //   type != null
        try{
            token = new Token(null, "", 1);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        //Testing throwing of exception for violation of precondition
        //   type != EOF => value != null
        try{
            token = new Token(TokenType.STRING, null, 1);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        try{
            token = new Token(TokenType.SCHEMES, null, 1);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        try{
            token = new Token(TokenType.FACTS, null, 1);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        try{
            token = new Token(TokenType.RULES, null, 1);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        try{
            token = new Token(TokenType.QUERIES, null, 1);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        try{
            token = new Token(TokenType.COLON_DASH, null, 1);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        try{
            token = new Token(TokenType.COLON, null, 1);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        try{
            token = new Token(TokenType.COMMA, null, 1);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        try{
            token = new Token(TokenType.PERIOD, null, 1);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        try{
            token = new Token(TokenType.LEFT_PAREN, null, 1);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        try{
            token = new Token(TokenType.RIGHT_PAREN, null, 1);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        try{
            token = new Token(TokenType.Q_MARK, null, 1);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };


        //Testing throwing of exception for violation of precondition
        //   type != EOF and type != STRING => |value| > 0

        try{
            token = new Token(TokenType.SCHEMES, null, 1);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        try{
            token = new Token(TokenType.FACTS, null, 1);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        try{
            token = new Token(TokenType.RULES, null, 1);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        try{
            token = new Token(TokenType.QUERIES, null, 1);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        try{
            token = new Token(TokenType.COLON_DASH, null, 1);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        try{
            token = new Token(TokenType.COLON, null, 1);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        try{
            token = new Token(TokenType.COMMA, null, 1);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        try{
            token = new Token(TokenType.PERIOD, null, 1);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        try{
            token = new Token(TokenType.LEFT_PAREN, null, 1);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        try{
            token = new Token(TokenType.RIGHT_PAREN, null, 1);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        try{
            token = new Token(TokenType.Q_MARK, null, 1);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };
        //Testing throwing of exception for violation of precondition
        //   type == SCHEMES => value == "Schemes" -- ignoring case
        try{
            token = new Token(TokenType.SCHEMES, "scheme", 1);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        //Testing throwing of exception for violation of precondition
        //   type == FACTS => value == "Facts" -- ignoring case
        try{
            token = new Token(TokenType.FACTS, "Fact", 1);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        //Testing throwing of exception for violation of precondition
        //   type == RULES => value == "Rules" -- ignoring case
        try{
            token = new Token(TokenType.RULES, "Rule", 1);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        //Testing throwing of exception for violation of precondition
        //   type == QUERIES => value == "Queries" -- ignoring case
        try{
            token = new Token(TokenType.QUERIES, "Query", 1);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        //Testing throwing of exception for violation of precondition
        //   type == COLON_DASH => value == ":-" -- ignoring case
        try{
            token = new Token(TokenType.COLON_DASH, ":", 1);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        try{
            token = new Token(TokenType.COLON_DASH, "-", 1);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        try{
            token = new Token(TokenType.COLON_DASH, "::-", 1);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        //Testing throwing of exception for violation of precondition
        //   type == COLON => value == ":" -- ignoring case
        try{
            token = new Token(TokenType.COLON, "a", 1);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        try{
            token = new Token(TokenType.COLON, "::", 1);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        //Testing throwing of exception for violation of precondition
        //   type == COMMA => value == "," -- ignoring case
        try{
            token = new Token(TokenType.COMMA, ";", 1);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        //Testing throwing of exception for violation of precondition
        //   type == PERIOD => value == "." -- ignoring case
        try{
            token = new Token(TokenType.PERIOD, ",", 1);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        //Testing throwing of exception for violation of precondition
        //   type == LEFT_PAREN => value == "(" -- ignoring case
        try{
            token = new Token(TokenType.LEFT_PAREN, ")", 1);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        try{
            token = new Token(TokenType.LEFT_PAREN, "[", 1);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        try{
            token = new Token(TokenType.LEFT_PAREN, "{", 1);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        try{
            token = new Token(TokenType.LEFT_PAREN, "9", 1);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        //Testing throwing of exception for violation of precondition
        //   type == RIGHT_PAREN => value == ")" -- ignoring case
        try{
            token = new Token(TokenType.RIGHT_PAREN, "(", 1);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        try{
            token = new Token(TokenType.RIGHT_PAREN, "]", 1);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        try{
            token = new Token(TokenType.RIGHT_PAREN, "}", 1);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        try{
            token = new Token(TokenType.RIGHT_PAREN, "0", 1);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        //Testing throwing of exception for violation of precondition
        //   type == Q_MARK => value == "?" -- ignoring case
        try{
            token = new Token(TokenType.Q_MARK, "!", 1);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        //Testing throwing of exception for violation of precondition
        //   lineNunmber >= 1
        try{
            token = new Token(TokenType.Q_MARK, "?", 0);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        try{
            token = new Token(TokenType.Q_MARK, "?", -1);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        //Testing throwing of exception for violation of precondition
        //   type = ID => the length is > 0, the first character must be a
        //   letter and all of the rest must be letters or digits.
        try{
            token = new Token(TokenType.ID, "", -1);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        try{
            token = new Token(TokenType.ID, "1", -1);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        try{
            token = new Token(TokenType.ID, "a?", -1);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        try{
            token = new Token(TokenType.ID, "a1b2.", -1);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };


        try{ //all of the follow can but should not throw exceptions.
             //Thus we wrap them in a try block, but, shoud never use it.

        //Testing EOF constructor
        token = new Token(TokenType.EOF, null, 2);
        assertTrue(token.getTokenType() == TokenType.EOF);
        assertTrue(token.getValue().equals(""));
        assertTrue(token.getLineNumber() == 2);

        token = new Token(TokenType.EOF, "cat", 50);
        assertTrue(token.getTokenType() == TokenType.EOF);
        assertTrue(token.getValue().equals(""));
        assertTrue(token.getLineNumber() == 50);

        //Testing the STRING constructor
        token = new Token(TokenType.STRING, "", 30);
        assertTrue(token.getTokenType() == TokenType.STRING);
        assertTrue(token.getValue().equals("''"));
        assertTrue(token.getLineNumber() == 30);

        token = new Token(TokenType.STRING, "'a'", 30);
        assertTrue(token.getTokenType() == TokenType.STRING);
        assertTrue(token.getValue().equals("''a''"));
        assertTrue(token.getLineNumber() == 30);

        //Testing the ID constructor
        token = new Token(TokenType.ID, "a", 3);
        assertTrue(token.getTokenType() == TokenType.ID);
        assertTrue(token.getValue().equals("a"));
        assertTrue(token.getLineNumber() == 3);

        token = new Token(TokenType.ID, "A123456789", 3);
        assertTrue(token.getTokenType() == TokenType.ID);
        assertTrue(token.getValue().equals("A123456789"));
        assertTrue(token.getLineNumber() == 3);

        token = new Token(TokenType.ID, "z2", 3);
        assertTrue(token.getTokenType() == TokenType.ID);
        assertTrue(token.getValue().equals("z2"));
        assertTrue(token.getLineNumber() == 3);

        //Testing SCHEMES constructor
        token = new Token(TokenType.SCHEMES, "schemes", 1);
        assertTrue(token.getTokenType() == TokenType.SCHEMES);
        assertTrue(token.getValue().equals("schemes"));
        assertTrue(token.getLineNumber() == 1);

        token = new Token(TokenType.SCHEMES, "SCHEMES", 1);
        assertTrue(token.getTokenType() == TokenType.SCHEMES);
        assertTrue(token.getValue().equals("SCHEMES"));
        assertTrue(token.getLineNumber() == 1);

        token = new Token(TokenType.SCHEMES, "Schemes", 1);
        assertTrue(token.getTokenType() == TokenType.SCHEMES);
        assertTrue(token.getValue().equals("Schemes"));
        assertTrue(token.getLineNumber() == 1);

        //Testing FACTS constructor
        token = new Token(TokenType.FACTS, "facts", 1);
        assertTrue(token.getTokenType() == TokenType.FACTS);
        assertTrue(token.getValue().equals("facts"));
        assertTrue(token.getLineNumber() == 1);

        token = new Token(TokenType.FACTS, "FACTS", 1);
        assertTrue(token.getTokenType() == TokenType.FACTS);
        assertTrue(token.getValue().equals("FACTS"));
        assertTrue(token.getLineNumber() == 1);

        token = new Token(TokenType.FACTS, "Facts", 1);
        assertTrue(token.getTokenType() == TokenType.FACTS);
        assertTrue(token.getValue().equals("Facts"));
        assertTrue(token.getLineNumber() == 1);

        //Testing RULES constructor
        token = new Token(TokenType.RULES, "rules", 1);
        assertTrue(token.getTokenType() == TokenType.RULES);
        assertTrue(token.getValue().equals("rules"));
        assertTrue(token.getLineNumber() == 1);

        token = new Token(TokenType.RULES, "RULES", 1);
        assertTrue(token.getTokenType() == TokenType.RULES);
        assertTrue(token.getValue().equals("RULES"));
        assertTrue(token.getLineNumber() == 1);

        token = new Token(TokenType.RULES, "RULEs", 1);
        assertTrue(token.getTokenType() == TokenType.RULES);
        assertTrue(token.getValue().equals("RULEs"));
        assertTrue(token.getLineNumber() == 1);

        //Testing QUERIES constructor
        token = new Token(TokenType.QUERIES, "queries", 1);
        assertTrue(token.getTokenType() == TokenType.QUERIES);
        assertTrue(token.getValue().equals("queries"));
        assertTrue(token.getLineNumber() == 1);

        token = new Token(TokenType.QUERIES, "QUERIES", 1);
        assertTrue(token.getTokenType() == TokenType.QUERIES);
        assertTrue(token.getValue().equals("QUERIES"));
        assertTrue(token.getLineNumber() == 1);

        token = new Token(TokenType.QUERIES, "Queries", 1);
        assertTrue(token.getTokenType() == TokenType.QUERIES);
        assertTrue(token.getValue().equals("Queries"));
        assertTrue(token.getLineNumber() == 1);

        //Testing COLON_DASH constructor
        token = new Token(TokenType.COLON_DASH, ":-", 1);
        assertTrue(token.getTokenType() == TokenType.COLON_DASH);
        assertTrue(token.getValue().equals(":-"));
        assertTrue(token.getLineNumber() == 1);

        //Testing COLON constructor
        token = new Token(TokenType.COLON, ":", 1);
        assertTrue(token.getTokenType() == TokenType.COLON);
        assertTrue(token.getValue().equals(":"));
        assertTrue(token.getLineNumber() == 1);

        //Testing COMMA constructor
        token = new Token(TokenType.COMMA, ",", 1);
        assertTrue(token.getTokenType() == TokenType.COMMA);
        assertTrue(token.getValue().equals(","));
        assertTrue(token.getLineNumber() == 1);

        //Testing PERIOD constructor
        token = new Token(TokenType.PERIOD, ".", 1);
        assertTrue(token.getTokenType() == TokenType.PERIOD);
        assertTrue(token.getValue().equals("."));
        assertTrue(token.getLineNumber() == 1);

        //Testing LEFT_PAREN constructor
        token = new Token(TokenType.LEFT_PAREN, "(", 1);
        assertTrue(token.getTokenType() == TokenType.LEFT_PAREN);
        assertTrue(token.getValue().equals("("));
        assertTrue(token.getLineNumber() == 1);

        //Testing RIGHT_PAREN constructor
        token = new Token(TokenType.RIGHT_PAREN, ")", 1);
        assertTrue(token.getTokenType() == TokenType.RIGHT_PAREN);
        assertTrue(token.getValue().equals(")"));
        assertTrue(token.getLineNumber() == 1);

        //Testing Q_MARK constructor
        token = new Token(TokenType.Q_MARK, "?", 1);
        assertTrue(token.getTokenType() == TokenType.Q_MARK);
        assertTrue(token.getValue().equals("?"));
        assertTrue(token.getLineNumber() == 1);

        }catch(ParserException e){
            System.err.println("ERROR in TokenTest::" +
                "testTokenTypeStringStringLengthConstructor(" +
                "TokenType,String,int)\n" +
                "Should NOT be able to get here");
        };
    }

    /**
     * Test Copy Constructor: Token(Token).
     */
    public void testCopyConstructor(){
      
        Token copy;
        //The copy constructor should throw a ParserException if the token
        //to be copied is null.
        try{
            copy = new Token(null);
            assertTrue(false);
        }catch(AssertionError e){
            assertTrue(true);
        };

        try{ //to catch the expception that should never be thrown
        //Test copy of EOF
        token = new Token(TokenType.EOF, "", 1);
        copy = new Token(token);
        assertTrue(token.getTokenType() == copy.getTokenType());
        assertTrue(token.getValue().equals(copy.getValue()));
        assertTrue(token.getValue() != copy.getValue());
        assertTrue(token.getLineNumber() == copy.getLineNumber());

        //Test copy of STRING
        token = new Token(TokenType.STRING, "", 2);
        copy = new Token(token);
        assertTrue(token.getTokenType() == copy.getTokenType());
        assertTrue(token.getValue().equals(copy.getValue()));
        assertTrue(token.getValue() != copy.getValue());
        assertTrue(token.getLineNumber() == copy.getLineNumber());

        token = new Token(TokenType.STRING, "'", 2);
        copy = new Token(token);
        assertTrue(token.getTokenType() == copy.getTokenType());
        assertTrue(token.getValue().equals(copy.getValue()));
        assertTrue(token.getValue() != copy.getValue());
        assertTrue(token.getLineNumber() == copy.getLineNumber());

        token = new Token(TokenType.STRING, "'a'", 2);
        copy = new Token(token);
        assertTrue(token.getTokenType() == copy.getTokenType());
        assertTrue(token.getValue().equals(copy.getValue()));
        assertTrue(token.getValue() != copy.getValue());
        assertTrue(token.getLineNumber() == copy.getLineNumber());

        //Test copy of IDENTIFIER
        token = new Token(TokenType.ID, "a", 2);
        copy = new Token(token);
        assertTrue(token.getTokenType() == copy.getTokenType());
        assertTrue(token.getValue().equals(copy.getValue()));
        assertTrue(token.getValue() != copy.getValue());
        assertTrue(token.getLineNumber() == copy.getLineNumber());

        token = new Token(TokenType.ID, "a1b2", 2);
        copy = new Token(token);
        assertTrue(token.getTokenType() == copy.getTokenType());
        assertTrue(token.getValue().equals(copy.getValue()));
        assertTrue(token.getValue() != copy.getValue());
        assertTrue(token.getLineNumber() == copy.getLineNumber());

        //Test copy of SCHEMES (we assume if this works it works for
        //FACTS, RULES, and QUERIES
        token = new Token(TokenType.SCHEMES, "schemes", 2);
        copy = new Token(token);
        assertTrue(token.getTokenType() == copy.getTokenType());
        assertTrue(token.getValue().equals(copy.getValue()));
        assertTrue(token.getValue() != copy.getValue());
        assertTrue(token.getLineNumber() == copy.getLineNumber());

        //Test copy of COLON_DASH
        token = new Token(TokenType.COLON_DASH, ":-", 2);
        copy = new Token(token);
        assertTrue(token.getTokenType() == copy.getTokenType());
        assertTrue(token.getValue().equals(copy.getValue()));
        assertTrue(token.getValue() != copy.getValue());
        assertTrue(token.getLineNumber() == copy.getLineNumber());

        //Test copy of COLON
        token = new Token(TokenType.COLON, ":", 2);
        copy = new Token(token);
        assertTrue(token.getTokenType() == copy.getTokenType());
        assertTrue(token.getValue().equals(copy.getValue()));
        assertTrue(token.getValue() != copy.getValue());
        assertTrue(token.getLineNumber() == copy.getLineNumber());

        //Test copy of COMMA
        token = new Token(TokenType.COMMA, ",", 2);
        copy = new Token(token);
        assertTrue(token.getTokenType() == copy.getTokenType());
        assertTrue(token.getValue().equals(copy.getValue()));
        assertTrue(token.getValue() != copy.getValue());
        assertTrue(token.getLineNumber() == copy.getLineNumber());

        //Test copy of PERIOD
        token = new Token(TokenType.PERIOD, ".", 2);
        copy = new Token(token);
        assertTrue(token.getTokenType() == copy.getTokenType());
        assertTrue(token.getValue().equals(copy.getValue()));
        assertTrue(token.getValue() != copy.getValue());
        assertTrue(token.getLineNumber() == copy.getLineNumber());

        //Test copy of LEFT_PAREN
        token = new Token(TokenType.LEFT_PAREN, "(", 2);
        copy = new Token(token);
        assertTrue(token.getTokenType() == copy.getTokenType());
        assertTrue(token.getValue().equals(copy.getValue()));
        assertTrue(token.getValue() != copy.getValue());
        assertTrue(token.getLineNumber() == copy.getLineNumber());

        //Test copy of RIGHT_PAREN
        token = new Token(TokenType.RIGHT_PAREN, ")", 2);
        copy = new Token(token);
        assertTrue(token.getTokenType() == copy.getTokenType());
        assertTrue(token.getValue().equals(copy.getValue()));
        assertTrue(token.getValue() != copy.getValue());
        assertTrue(token.getLineNumber() == copy.getLineNumber());

        //Test copy of Q_MARK
        token = new Token(TokenType.Q_MARK, "?", 2);
        copy = new Token(token);
        assertTrue(token.getTokenType() == copy.getTokenType());
        assertTrue(token.getValue().equals(copy.getValue()));
        assertTrue(token.getValue() != copy.getValue());
        assertTrue(token.getLineNumber() == copy.getLineNumber());

        }catch(ParserException e){
            System.err.println("In TokenTest::testCopyConstructor()\n" +
                "the following error should not occur\n" +
                e.getMessage());
        };
    }

    /**
     * Test the equals operator.
     */
    public void testEquals(){
        Token otherToken;
        Token differentToken;

        //So we can catch an exception that should never be thrown.
        try{
        token = new Token(TokenType.COMMA, ",", 1);
        otherToken = new Token(TokenType.COMMA, ",", 2);
        differentToken = new Token(TokenType.PERIOD, ".", 2);
       
        assertTrue(Token.COMMA.equals(Token.COMMA));
        assertTrue(Token.COMMA.equals(token));
        assertTrue(token.equals(Token.COMMA));
        assertTrue(token.equals(otherToken));
        assertTrue(otherToken.equals(token));
        assertFalse(differentToken.equals(token));
        assertFalse(token.equals(null));
        assertFalse(token.equals("abc"));

        token = new Token(TokenType.STRING, "", 1);
        otherToken = new Token(TokenType.STRING, "", 2);
        assertTrue(token.equals(otherToken));
        assertTrue(otherToken.equals(token));
        otherToken = new Token(TokenType.STRING, "a", 2);
        assertFalse(token.equals(otherToken));
        assertFalse(otherToken.equals(token));
        assertTrue(otherToken.equals(otherToken));
        token = new Token(TokenType.STRING, "abc", 2);
        assertFalse(token.equals(otherToken));
        assertFalse(otherToken.equals(token));
        assertTrue(otherToken.equals(otherToken));
        
        token = new Token(TokenType.ID, "z", 1);
        otherToken = new Token(TokenType.ID, "z", 2);
        assertTrue(token.equals(otherToken));
        assertTrue(otherToken.equals(token));
        otherToken = new Token(TokenType.ID, "za", 2);
        assertFalse(token.equals(otherToken));
        assertFalse(otherToken.equals(token));
        assertTrue(otherToken.equals(otherToken));
        token = new Token(TokenType.ID, "zabc", 2);
        assertFalse(token.equals(otherToken));
        assertFalse(otherToken.equals(token));
        assertTrue(otherToken.equals(otherToken));

        otherToken = new Token(TokenType.STRING, "zabc", 2);
        assertFalse(token.equals(otherToken));
        assertFalse(otherToken.equals(token));

        }catch(ParserException e){
            System.err.println("In TokenTest::testEquals()\n" +
                "the following error should not occur\n" +
                e.getMessage());
        };
    };

    /**
     * Test the hash funtion.  I don't really know how to test it except
     * to see if it always returns the same index.
     */
    public void testHashFunction(){
        Token otherToken;

        try{
            token = new Token(TokenType.ID, "abc", 5);
            otherToken = new Token(TokenType.ID, "abc", 6);
            //assertTrue(token.hashCode() == 28456490);
            assertTrue(token.hashCode() == otherToken.hashCode());

            otherToken = new Token(TokenType.STRING, "abc", 5);
            assertFalse(token.hashCode() == otherToken.hashCode());

            token = new Token(TokenType.Q_MARK, "?", 10);
            otherToken = new Token(TokenType.Q_MARK, "?", 10);
            assertTrue(token.hashCode() == otherToken.hashCode());

            otherToken = new Token(TokenType.STRING, "?", 10);
            assertFalse(token.hashCode() == otherToken.hashCode());

            otherToken = new Token(TokenType.PERIOD, ".", 10);
            assertFalse(token.hashCode() == otherToken.hashCode());
   
        }catch(ParserException e){
        };
    };

    ///////////////////
    static boolean debug = false;

    /**
     * Used to set up the test. This method is called by JUnit before each of
     * the tests are executed.
     */
    protected void setUp()
    {
        /* Add any necessary initialization code here (e.g., open a socket). */
    }

    /**
     * Used to clean up after the test. This method is called by JUnit after
     * each of the tests has been completed.
     */
    protected void tearDown()
    {
        /* Add any necessary cleanup code here (e.g., close a socket). */
    }

   /*
     * Uncomment this variable declaration and add any necessary initialization
     * arguments for it in the setUp() method.
     */
     private Token token;

    /**
     * Utility main method. This will the test cases defined in this class.
     *
     * Usage: java Project2Test
     */
    public static void main (String[] args)
    {
        /* junit.textui.TestRunner will write the test results to stdout. */
        junit.textui.TestRunner.run (TokenTest.class);

        /* junit.swingui.TestRunner will display the test results in JUnit's
           swing interface. */
        //junit.swingui.TestRunner.run (Project2Test.class);
    }
}
