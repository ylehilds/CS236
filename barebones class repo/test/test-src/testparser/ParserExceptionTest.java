package testparser;

import junit.framework.TestCase;

import project3.three.ParserException;
import project3.lex.Token;
import project3.lex.TokenType;

/**
 * ParserExceptionTest is a JUnit test for class ParserException
 */
public class ParserExceptionTest
    extends TestCase
{
    /**
     * Constructs a test case for the test specified in the name argument.
     */
    public ParserExceptionTest (String name)
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
     * Test everything
     */
    public void test1()
    {
        try{
        Token token1 = new Token(TokenType.STRING,"abc",5);
        Token token2 = new Token(TokenType.STRING,"abc",5);
        x = new ParserException(token1);  
        assertTrue(token2.equals(x.getToken()));
        assertTrue(x.toString().equals(
             "Parser Error for " + token1.toString()));

        x = new ParserException("test");
        assertTrue(x.getToken() == null);
        assertTrue(x.toString() == "test");
        }catch(ParserException e){
            System.err.println("ERROR in ParserExceptionTest::test1:\n"+
                               "    Should not get here, error is\n" +
                               e.toString());    
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
     private ParserException x;

    /**
     * Utility main method. This will the test cases defined in this class.
     *
     * Usage: java ParserExceptionTest
     */
    public static void main (String[] args)
    {
        /* junit.textui.TestRunner will write the test results to stdout. */
        junit.textui.TestRunner.run (ParserExceptionTest.class);

        /* junit.swingui.TestRunner will display the test results in JUnit's
           swing interface. */
        //junit.swingui.TestRunner.run (ParserExceptionTest.class);
    }
}
