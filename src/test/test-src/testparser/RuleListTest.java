package testparser;

import junit.framework.TestCase;

import java.io.StringReader;
import java.util.ArrayList;
import project3.lex.Lex;
import project3.three.Node;
import project3.three.ParserException;
import project3.three.Rule;

import project3.RuleList;

/**
 * RuleListTest is a JUnit test for class RuleList
 */
public class RuleListTest
    extends TestCase
{
    /**
     * Constructs a test case for the test specified in the name argument.
     */
    public RuleListTest (String name)
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
     * Test RuleList constructor and toString.
     */
    public void testRuleList(){
        try{
            try{
                StringReader sr = new StringReader("");
                Lex lex = new Lex(sr);
                RuleList ruleList = new RuleList(lex);
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            try{
                StringReader sr = new StringReader("Rules");
                Lex lex = new Lex(sr);
                RuleList ruleList = new RuleList(lex);
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            try{
                StringReader sr = new StringReader("Rules : Id1");
                Lex lex = new Lex(sr);
                RuleList ruleList = new RuleList(lex);
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            try{
                StringReader sr = new StringReader("Rules : X(Y)");
                Lex lex = new Lex(sr);
                RuleList ruleList = new RuleList(lex);
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            StringReader sr = new StringReader("Rules : ");
            Lex lex = new Lex(sr);
            RuleList ruleList = new RuleList(lex);
            ArrayList<Node> nodes = ruleList.getNodes();
            assertTrue(nodes.size() == 0);
            assertTrue(ruleList.toString().equals("Rules:" + eoln));

            sr = new StringReader("Rules : Name(A):-X(A).");
            lex = new Lex(sr);
            ruleList = new RuleList(lex);
            nodes = ruleList.getNodes();
            assertTrue(nodes.size() == 1);
            assertTrue(nodes.get(0) instanceof Rule);
            assertTrue(ruleList.toString().equals(
                "Rules:" + eoln + "Name(A):-X(A)."));

            sr = new StringReader(
                "RULES: R(A,Z):-Name(A,B), City('X','Y',Z).\n" +
                       "A(X,Y):-A(Y,X).");
            lex = new Lex(sr);
            ruleList = new RuleList(lex);
            nodes = ruleList.getNodes();
            assertTrue(nodes.size() == 2);
            assertTrue(nodes.get(0) instanceof Rule);
            assertTrue(nodes.get(1) instanceof Rule);
            assertTrue(ruleList.toString().equals(
                "RULES:" + eoln +
                "R(A,Z):-Name(A,B),City('X','Y',Z).A(X,Y):-A(Y,X)."));

            sr = new StringReader(
                "RULES: R(A):-Name1('A').\n" +
                       "S(A):-Name2('B').\n" +
                       "A(A):-Name3('C').");
            lex = new Lex(sr);
            ruleList = new RuleList(lex);
            nodes = ruleList.getNodes();
            assertTrue(nodes.size() == 3);
            assertTrue(nodes.get(0) instanceof Rule);
            assertTrue(nodes.get(1) instanceof Rule);
            assertTrue(nodes.get(2) instanceof Rule);
            assertTrue(ruleList.toString().equals(
                "RULES:" + eoln +
                "R(A):-Name1('A').S(A):-Name2('B').A(A):-Name3('C')."));
        }catch(ParserException e){
            System.out.println(
                "ERROR in RuleListTest.testCreateRuleList1\n" +
                "    should not get here.\n" +
                "    error = " + e.getMessage());
        };
    }

    ///////////////////
    static boolean debug = false;
    static String eoln = System.getProperty("line.separator");

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
     private RuleList x; 

    /**
     * Utility main method. This will the test cases defined in this class.
     *
     * Usage: java RuleListTest
     */
    public static void main (String[] args)
    {
        /* junit.textui.TestRunner will write the test results to stdout. */
        junit.textui.TestRunner.run (RuleListTest.class);

        /* junit.swingui.TestRunner will display the test results in JUnit's
           swing interface. */
        //junit.swingui.TestRunner.run (RuleListTest.class);
    }
}
