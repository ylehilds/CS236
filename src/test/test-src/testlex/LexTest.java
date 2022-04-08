package testlex;

import junit.framework.TestCase;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;

import project3.lex.Lex;
import project3.lex.TokenType;
import project3.three.ParserException;

/**
 * LexTest is a JUnit test for Lex
 */
public class LexTest
    extends TestCase
{
    /**
     * Constructs a test case for the test specified in the name argument.
     */
    public LexTest (String name)
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
     * Test FileConstructor.
     */
    public void testFileConstructor(){
        try{
        lex = new Lex("test/test-src/testlex/in4.txt");
        assertTrue(lex.front().getTokenType() == TokenType.SCHEMES);
        }catch(ParserException e){
            System.out.println("ERROR in LexTest::testFileConstructor()\n" +
                "Should not have gotten here but the error is\n" +
                e.getMessage());
        };
    };

    /**
     * Test StringConstructor plus front() and popFront()
     */
    public void testStringConstructor(){
        try{
            stringReader = new StringReader("Schemes ; A");
            lex = new Lex(stringReader);
            lex.popFront();
            lex.popFront();
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        try{
            stringReader = new StringReader(": 'Schemes ; A");
            lex = new Lex(stringReader);
            lex.popFront();
            lex.popFront();
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        try{
        stringReader = new StringReader("");
        lex = new Lex(stringReader);
        assertTrue(lex.front().getTokenType() == TokenType.EOF);

        stringReader = new StringReader("'abc',");
        lex = new Lex(stringReader);
        lex.popFront();
        assertTrue(lex.front().getTokenType() == TokenType.COMMA);

        stringReader = new StringReader(
            "Schemes : A(X,Youth2):-,?. Facts Queries Rules 'String'");
        lex = new Lex(stringReader);
        assertTrue(lex.front().getTokenType() == TokenType.SCHEMES);
        lex.popFront();
        assertTrue(lex.front().getTokenType() == TokenType.COLON);
        lex.popFront();
        assertTrue(lex.front().getTokenType() == TokenType.ID &&
               lex.front().getValue().equals("A"));
        lex.popFront();
        assertTrue(lex.front().getTokenType() == TokenType.LEFT_PAREN);
        lex.popFront();
        assertTrue(lex.front().getTokenType() == TokenType.ID &&
               lex.front().getValue().equals("X"));
        lex.popFront();
        assertTrue(lex.front().getTokenType() == TokenType.COMMA);
        lex.popFront();
        assertTrue(lex.front().getTokenType() == TokenType.ID &&
               lex.front().getValue().equals("Youth2"));
        lex.popFront();
        assertTrue(lex.front().getTokenType() == TokenType.RIGHT_PAREN); 
        lex.popFront();
        assertTrue(lex.front().getTokenType() == TokenType.COLON_DASH); 
        lex.popFront();
        assertTrue(lex.front().getTokenType() == TokenType.COMMA); 
        lex.popFront();
        assertTrue(lex.front().getTokenType() == TokenType.Q_MARK); 
        lex.popFront();
        assertTrue(lex.front().getTokenType() == TokenType.PERIOD); 
        lex.popFront();
        assertTrue(lex.front().getTokenType() == TokenType.FACTS); 
        lex.popFront();
        assertTrue(lex.front().getTokenType() == TokenType.QUERIES); 
        lex.popFront();
        assertTrue(lex.front().getTokenType() == TokenType.RULES); 
        lex.popFront();
        assertTrue(lex.front().getTokenType() == TokenType.STRING &&
               lex.front().getValue().equals("'String'")
              ); 
        lex.popFront();
        assertTrue(lex.front().getTokenType() == TokenType.EOF); 

        stringReader = new StringReader("A B 'this\nand that' :-");
        lex = new Lex(stringReader);
        lex.popFront();
        lex.popFront();
        assertTrue(lex.front().getTokenType() == TokenType.STRING &&
               lex.front().getValue().equals("'this\nand that'")
              ); 
        lex.popFront();
        assertTrue(lex.front().getTokenType() == TokenType.COLON_DASH &&
               lex.front().getLineNumber() == 2);

        stringReader = new StringReader("A B '\nand that' :-");
        lex = new Lex(stringReader);
        lex.popFront();
        lex.popFront();
        assertTrue(lex.front().getTokenType() == TokenType.STRING &&
               lex.front().getValue().equals("'\nand that'") &&
               lex.front().getLineNumber() == 1
              ); 

        }catch(ParserException e){
            System.out.println("ERROR in LexTest::testStringConstructor()\n" +
                "Should not have gotten here but the error is\n" +
                e.getMessage());
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
     private Lex lex;
     private StringReader stringReader;

    /**
     * Utility main method. This will the test cases defined in this class.
     *
     * Usage: java Project2Test
     */
    public static void main (String[] args)
    {
        /* junit.textui.TestRunner will write the test results to stdout. */
        junit.textui.TestRunner.run (LexTest.class);

        /* junit.swingui.TestRunner will display the test results in JUnit's
           swing interface. */
        //junit.swingui.TestRunner.run (Project2Test.class);
    }
}
