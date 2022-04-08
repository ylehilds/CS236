package testparser;

import junit.framework.TestCase;

import java.io.StringReader;
import java.util.ArrayList;
import project3.lex.Lex;
import project3.three.Node;
import project3.three.ParserException;
import project3.three.SchemeList;
import project3.three.Scheme;

/**
 * SchemeListTest is a JUnit test for class SchemeList
 */
public class SchemeListTest
    extends TestCase
{
    /**
     * Constructs a test case for the test specified in the name argument.
     */
    public SchemeListTest (String name)
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
     * Test SchemeList constructor and toString.
     */
    public void testSchemeList(){
        try{
            try{
                StringReader sr = new StringReader("");
                Lex lex = new Lex(sr);
                SchemeList schemeList = new SchemeList(lex);
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            try{
                StringReader sr = new StringReader("Schemes");
                Lex lex = new Lex(sr);
                SchemeList schemeList = new SchemeList(lex);
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            try{
                StringReader sr = new StringReader("Schemes :");
                Lex lex = new Lex(sr);
                SchemeList schemeList = new SchemeList(lex);
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            try{
                StringReader sr = new StringReader("Schemes : Id1");
                Lex lex = new Lex(sr);
                SchemeList schemeList = new SchemeList(lex);
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };


            StringReader sr = new StringReader("Schemes : Name(A)");
            Lex lex = new Lex(sr);
            SchemeList schemeList = new SchemeList(lex);
            ArrayList<Node> nodes = schemeList.getNodes();
            assertTrue(nodes.size() == 1);
            assertTrue(nodes.get(0) instanceof Scheme);
            assertTrue(schemeList.toString().equals(
                "Schemes:" + eoln + "Name(A)"));

            sr = new StringReader("SCHEMES: Name(A,B) City(X,Y,Z)");
            lex = new Lex(sr);
            schemeList = new SchemeList(lex);
            nodes = schemeList.getNodes();
            assertTrue(nodes.size() == 2);
            assertTrue(nodes.get(0) instanceof Scheme);
            assertTrue(nodes.get(1) instanceof Scheme);
            assertTrue(schemeList.toString().equals(
                "SCHEMES:" + eoln + "Name(A,B)City(X,Y,Z)"));

            sr = new StringReader("SCHEMES: Name1(A) Name2(B) Name3(C) Facts");
            lex = new Lex(sr);
            schemeList = new SchemeList(lex);
            nodes = schemeList.getNodes();
            assertTrue(nodes.size() == 3);
            assertTrue(nodes.get(0) instanceof Scheme);
            assertTrue(nodes.get(1) instanceof Scheme);
            assertTrue(nodes.get(2) instanceof Scheme);
            assertTrue(schemeList.toString().equals(
                "SCHEMES:" + eoln + "Name1(A)Name2(B)Name3(C)"));
        }catch(ParserException e){
            System.out.println(
                "ERROR in SchemeListTest.testCreateSchemeList1\n" +
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
     private SchemeList x; 

    /**
     * Utility main method. This will the test cases defined in this class.
     *
     * Usage: java SchemeListTest
     */
    public static void main (String[] args)
    {
        /* junit.textui.TestRunner will write the test results to stdout. */
        junit.textui.TestRunner.run (SchemeListTest.class);

        /* junit.swingui.TestRunner will display the test results in JUnit's
           swing interface. */
        //junit.swingui.TestRunner.run (SchemeListTest.class);
    }
}
