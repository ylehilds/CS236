package testparser;

import junit.framework.TestCase;

import java.io.StringReader;
import java.util.ArrayList;
import project3.lex.Lex;
import project3.three.Node;
import project3.three.ParserException;
import project3.three.FactList;
import project3.three.Fact;

/**
 * FactListTest is a JUnit test for class FactList
 */
public class FactListTest
    extends TestCase
{
    /**
     * Constructs a test case for the test specified in the name argument.
     */
    public FactListTest (String name)
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
     * Test FactList constructor and toString.
     */
    public void testFactList(){
        try{
            try{
                StringReader sr = new StringReader("");
                Lex lex = new Lex(sr);
                FactList factList = new FactList(lex);
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            try{
                StringReader sr = new StringReader("Facts");
                Lex lex = new Lex(sr);
                FactList factList = new FactList(lex);
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            try{
                StringReader sr = new StringReader("Facts : Id1");
                Lex lex = new Lex(sr);
                FactList factList = new FactList(lex);
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            try{
                StringReader sr = new StringReader("Facts : X(Y).");
                Lex lex = new Lex(sr);
                FactList factList = new FactList(lex);
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            try{
                StringReader sr = new StringReader("Facts : X('Y')");
                Lex lex = new Lex(sr);
                FactList factList = new FactList(lex);
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            StringReader sr = new StringReader("Facts : ");
            Lex lex = new Lex(sr);
            FactList factList = new FactList(lex);
            ArrayList<Node> nodes = factList.getNodes();
            assertTrue(nodes.size() == 0);
            assertTrue(factList.toString().equals("Facts:" + eoln));

            sr = new StringReader("Facts : Name('A').");
            lex = new Lex(sr);
            factList = new FactList(lex);
            nodes = factList.getNodes();
            assertTrue(nodes.size() == 1);
            assertTrue(nodes.get(0) instanceof Fact);
            assertTrue(factList.toString().equals(
                "Facts:" + eoln + "Name('A')."));

            sr = new StringReader("FACTS: Name('A','B'). City('X','Y','Z').");
            lex = new Lex(sr);
            factList = new FactList(lex);
            nodes = factList.getNodes();
            assertTrue(nodes.size() == 2);
            assertTrue(nodes.get(0) instanceof Fact);
            assertTrue(nodes.get(1) instanceof Fact);
            assertTrue(factList.toString().equals(
                "FACTS:" + eoln + "Name('A','B').City('X','Y','Z')."));

            sr = new StringReader(
                "FACTS: Name1('A'). Name2('B'). Name3('C'). Facts");
            lex = new Lex(sr);
            factList = new FactList(lex);
            nodes = factList.getNodes();
            assertTrue(nodes.size() == 3);
            assertTrue(nodes.get(0) instanceof Fact);
            assertTrue(nodes.get(1) instanceof Fact);
            assertTrue(nodes.get(2) instanceof Fact);
            assertTrue(factList.toString().equals(
                "FACTS:" + eoln +
                "Name1('A').Name2('B').Name3('C')."));
        }catch(ParserException e){
            System.out.println(
                "ERROR in FactListTest.testCreateFactList1\n" +
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
     private FactList x; 

    /**
     * Utility main method. This will the test cases defined in this class.
     *
     * Usage: java FactListTest
     */
    public static void main (String[] args)
    {
        /* junit.textui.TestRunner will write the test results to stdout. */
        junit.textui.TestRunner.run (FactListTest.class);

        /* junit.swingui.TestRunner will display the test results in JUnit's
           swing interface. */
        //junit.swingui.TestRunner.run (FactListTest.class);
    }
}
