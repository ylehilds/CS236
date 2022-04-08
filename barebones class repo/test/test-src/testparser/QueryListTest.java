package testparser;

import junit.framework.TestCase;

import java.io.StringReader;
import java.util.ArrayList;
import project3.lex.Lex;
import project3.three.Node;
import project3.three.ParserException;
import project3.three.QueryList;
import project3.three.Query;

/**
 * QueryListTest is a JUnit test for class QueryList
 */
public class QueryListTest
    extends TestCase
{
    /**
     * Constructs a test case for the test specified in the name argument.
     */
    public QueryListTest (String name)
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
     * Test QueryList constructor and toString.
     */
    public void testQueryList(){
        try{
            try{
                StringReader sr = new StringReader("");
                Lex lex = new Lex(sr);
                QueryList queryList = new QueryList(lex);
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            try{
                StringReader sr = new StringReader("Queries");
                Lex lex = new Lex(sr);
                QueryList queryList = new QueryList(lex);
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            try{
                StringReader sr = new StringReader("Queries :");
                Lex lex = new Lex(sr);
                QueryList queryList = new QueryList(lex);
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            try{
                StringReader sr = new StringReader("Queries : Id1");
                Lex lex = new Lex(sr);
                QueryList queryList = new QueryList(lex);
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            try{
                StringReader sr = new StringReader("Queries : Name(A)");
                Lex lex = new Lex(sr);
                QueryList queryList = new QueryList(lex);
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };


            StringReader sr = new StringReader("Queries : Name(A)?");
            Lex lex = new Lex(sr);
            QueryList queryList = new QueryList(lex);
            ArrayList<Node> nodes = queryList.getNodes();
            assertTrue(nodes.size() == 1);
            assertTrue(nodes.get(0) instanceof Query);
            assertTrue(queryList.toString().equals(
                "Queries:" + eoln + "Name(A)? No\n"));

            sr = new StringReader("QUERIES: Name('A',B)?\n City(X,'Y',Z)?");
            lex = new Lex(sr);
            queryList = new QueryList(lex);
            nodes = queryList.getNodes();
            assertTrue(nodes.size() == 2);
            assertTrue(nodes.get(0) instanceof Query);
            assertTrue(nodes.get(1) instanceof Query);
            assertTrue(queryList.toString().equals(
                "QUERIES:" + eoln + "Name('A',B)? No\nCity(X,'Y',Z)? No\n"));

            sr = new StringReader(
                "QUERIES: Name1(A)? Name2('B')? Name3('C')?");
            lex = new Lex(sr);

            queryList = new QueryList(lex);
            nodes = queryList.getNodes();
            assertTrue(nodes.size() == 3);
            assertTrue(nodes.get(0) instanceof Query);
            assertTrue(nodes.get(1) instanceof Query);
            assertTrue(nodes.get(2) instanceof Query);
            assertTrue(queryList.toString().equals(
                "QUERIES:" + eoln +
                "Name1(A)? No\nName2('B')? No\nName3('C')? No\n")); 

        }catch(ParserException e){
            System.out.println(
                "ERROR in QueryListTest.testQueryList\n" +
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
     private QueryList x; 

    /**
     * Utility main method. This will the test cases defined in this class.
     *
     * Usage: java QueryListTest
     */
    public static void main (String[] args)
    {
        /* junit.textui.TestRunner will write the test results to stdout. */
        junit.textui.TestRunner.run (QueryListTest.class);

        /* junit.swingui.TestRunner will display the test results in JUnit's
           swing interface. */
        //junit.swingui.TestRunner.run (QueryListTest.class);
    }
}
