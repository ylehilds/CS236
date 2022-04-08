package testlex;

import junit.framework.TestCase;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;

import project3.lex.Constant;
import project3.lex.Token;
import project3.lex.TokenType;
import project3.three.ParserException;

/**
 * ConstantTest is a JUnit test for Constant
 */
public class ConstantTest
    extends TestCase
{
    /**
     * Constructs a test case for the test specified in the name argument.
     */
    public ConstantTest (String name)
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
     * Test Constructor: Constant(TokenType, String, lineNumber)
     */
    public void testStandardConstructor(){
        try{
            constant  = new Constant("abc", 1);
            assertTrue(constant.getTokenType() == TokenType.STRING);
            assertTrue(constant.getValue().equals("'abc'"));
            assertTrue(constant.getLineNumber() == 1);
            assertTrue(constant.toString().equals("'abc'"));
        }catch(ParserException e){
            System.err.println(
                "ERROR in ConstantTest::testStandardConstructor\n" +
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
                Token token = new Token(TokenType.ID, "string", 1);
                constant = new Constant(token);
                assertTrue(false);
            }catch(AssertionError e){
                assertTrue(true);
            };

            try{
                Token token = new Token(TokenType.COLON, ":", 1);
                constant = new Constant(token);
                assertTrue(false);
            }catch(AssertionError e){
                assertTrue(true);
            };

            constant  = new Constant("abc", 1);
            Constant otherConstant = new Constant(constant);
            assertTrue(constant.equals(otherConstant));
            assertTrue(constant.getValue() != otherConstant.getValue());
            assertTrue(otherConstant.toString().equals("'abc'"));
        }catch(ParserException e){
            System.err.println(
                "ERROR in ConstantTest::testCopyConstructor\n" +
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
     private Constant constant;

    /**
     * Utility main method. This will the test cases defined in this class.
     *
     * Usage: java Project2Test
     */
    public static void main (String[] args)
    {
        /* junit.textui.TestRunner will write the test results to stdout. */
        junit.textui.TestRunner.run (ConstantTest.class);

        /* junit.swingui.TestRunner will display the test results in JUnit's
           swing interface. */
        //junit.swingui.TestRunner.run (Project2Test.class);
    }
}
