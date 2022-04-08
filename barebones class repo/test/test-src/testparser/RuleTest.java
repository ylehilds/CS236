package testparser;

import junit.framework.TestCase;
import java.io.StringReader;
import java.util.Iterator;
import java.util.ArrayList;

import project3.lex.Constant;
import project3.lex.TokenType;
import project3.lex.Lex;
import project3.three.Rule;
import project3.three.ParserException;

/**
 * RuleTest is a JUnit test for class Rule
 */
public class RuleTest
    extends TestCase
{
    /**
     * Constructs a test case for the test specified in the name argument.
     */
    public RuleTest(String name)
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
            StringReader sr = new StringReader("A(X) :- B(X).");
            Lex lex = new Lex(sr);
            Rule rule = new Rule(lex);
            assertTrue(rule.toString().equals("A(X):-B(X)."));

            sr = new StringReader("A(X,Y) :- B(X,Z), C(Z,Y).");
            lex = new Lex(sr);
            rule = new Rule(lex);
            assertTrue(rule.toString().equals("A(X,Y):-B(X,Z),C(Z,Y)."));

            sr = new StringReader("A(X,Y) :- B(X,'Z'),C('Z',Y),D(X).");
            lex = new Lex(sr);
            rule = new Rule(lex);
            assertTrue(rule.toString().equals(
                "A(X,Y):-B(X,'Z'),C('Z',Y),D(X)."));
        }catch(ParserException e){
            System.err.println("ERROR in Rule::test1()\n" +
                               "Should not be able to get here" + e);
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

    /**
     * Utility main method. This will the test cases defined in this class.
     *
     * Usage: java RuleTest
     */
    public static void main (String[] args)
    {
        /* junit.textui.TestRunner will write the test results to stdout. */
        junit.textui.TestRunner.run (RuleTest.class);

        /* junit.swingui.TestRunner will display the test results in JUnit's
           swing interface. */
        //junit.swingui.TestRunner.run (RuleTest.class);
    }
}
