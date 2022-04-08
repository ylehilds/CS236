package testparser;

import junit.framework.TestCase;

import java.io.StringReader;
import project3.lex.Lex;
import project3.lex.Token;
import project3.lex.TokenType;
import project3.three.Node;
import project3.three.ParserException;

/**
 * NodeTest is a JUnit test for class Node
 */
public class NodeTest
    extends TestCase
{
    /**
     * Constructs a test case for the test specified in the name argument.
     */
    public NodeTest (String name)
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
            x.checkFor(null, TokenType.EOF);
            assertTrue(false);
        }catch(AssertionError e){
            assertTrue(true);
        }catch(ParserException e){
            assertTrue(false);
        };

        try{
            StringReader sr = new StringReader("Person");
            Lex lex = new Lex(sr);
            x.checkFor(lex, TokenType.EOF);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        try{
            StringReader sr = new StringReader("Person");
            Lex lex = new Lex(sr);
            assertTrue(x.checkFor(lex, null) == null);
            Token t = x.checkFor(lex, TokenType.ID);
            assertTrue(t.getValue().equals("Person"));
            assertTrue(lex.front().getTokenType() == TokenType.EOF);
        }catch(ParserException e){
            System.err.println("ERROR in NodeTest::test1:\n"+
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

    private class ExtendedNode extends Node{};

   /*
     * Uncomment this variable declaration and add any necessary initialization
     * arguments for it in the setUp() method.
     */
     private ExtendedNode x = new ExtendedNode();

    /**
     * Utility main method. This will the test cases defined in this class.
     *
     * Usage: java NodeTest
     */
    public static void main (String[] args)
    {
        /* junit.textui.TestRunner will write the test results to stdout. */
        junit.textui.TestRunner.run (NodeTest.class);

        /* junit.swingui.TestRunner will display the test results in JUnit's
           swing interface. */
        //junit.swingui.TestRunner.run (NodeTest.class);
    }
}
