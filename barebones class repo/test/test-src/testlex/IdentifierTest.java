package testlex;

import junit.framework.TestCase;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;

import project3.lex.Identifier;
import project3.lex.Token;
import project3.lex.TokenType;
import project3.three.ParserException;

/**
 * IdentifierTest is a JUnit test for Identifier
 */
public class IdentifierTest
    extends TestCase
{
    /**
     * Constructs a test case for the test specified in the name argument.
     */
    public IdentifierTest (String name)
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
     * Test Constructor: Identifier(TokenType, String, lineNumber)
     */
    public void testStandardConstructor(){
        try{
            String str = new String("abc");
            variable  = new Identifier(str, 1);
            assertTrue(variable.getTokenType() == TokenType.ID);
            assertTrue(variable.getValue() == str);
            assertTrue(variable.getLineNumber() == 1);
            assertTrue(variable.toString().equals("abc"));
        }catch(ParserException e){
            System.err.println(
                "ERROR in IdentifierTest::testStandardConstructor\n" +
                "    should NOT have happened, error message = \n" +
                e.getMessage());
        };
    }

    /**
     * Test copy constructor.
     */
    public void testCopyConstructor(){
        try{
            try{
                Token token = new Token(TokenType.STRING, "string", 1);
                variable = new Identifier(token);
                assertTrue(false);
            }catch(AssertionError e){
                assertTrue(true);
            };

            try{
                Token token = new Token(TokenType.COLON, ":", 1);
                variable = new Identifier(token);
                assertTrue(false);
            }catch(AssertionError e){
                assertTrue(true);
            };

            variable  = new Identifier("abc", 1);
            Identifier otherIdentifier = new Identifier(variable);
            assertTrue(variable.equals(otherIdentifier));
            assertTrue(variable.getValue() != otherIdentifier.getValue());
            assertTrue(variable.toString().equals("abc"));
        }catch(ParserException e){
            System.err.println(
                "ERROR in IdentifierTest::testCopyConstructor\n" +
                "    should NOT have happened, error message = \n" +
                e.getMessage());
        };
    }


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
     private Identifier variable;

    /**
     * Utility main method. This will the test cases defined in this class.
     *
     * Usage: java Project2Test
     */
    public static void main (String[] args)
    {
        /* junit.textui.TestRunner will write the test results to stdout. */
        junit.textui.TestRunner.run (IdentifierTest.class);

        /* junit.swingui.TestRunner will display the test results in JUnit's
           swing interface. */
        //junit.swingui.TestRunner.run (Project2Test.class);
    }
}
