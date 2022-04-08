package testparser;

import junit.framework.TestCase;
import java.io.StringReader;
import java.util.Iterator;
import java.util.ArrayList;

import project3.lex.Lex;
import project3.three.DatalogProgram;
import project3.three.ParserException;

/**
 * DatalogProgramTest is a JUnit test for class DatalogProgram
 */
public class DatalogProgramTest
    extends TestCase
{
    /**
     * Constructs a test case for the test specified in the name argument.
     */
    public DatalogProgramTest(String name)
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
            try{
                StringReader sr = new StringReader("");
                Lex lex = new Lex(sr);
                DatalogProgram p = new DatalogProgram(lex);
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };            

            try{
                StringReader sr = new StringReader(
                    "Facts: " +
                    "Rules: " +
                    "Queries: Q(A)?");
                Lex lex = new Lex(sr);
                DatalogProgram p = new DatalogProgram(lex);
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };            

            try{
                StringReader sr = new StringReader(
                    "Schemes: Q(A)" +
                    "Rules: " +
                    "Queries: Q(A)?");
                Lex lex = new Lex(sr);
                DatalogProgram p = new DatalogProgram(lex);
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };            

            try{
                StringReader sr = new StringReader(
                    "Schemes: Q(A)" +
                    "Facts: " +
                    "Queries: Q(A)?");
                Lex lex = new Lex(sr);
                DatalogProgram p = new DatalogProgram(lex);
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };            

            try{
                StringReader sr = new StringReader(
                    "Schemes: Q(A)" +
                    "Facts: " +
                    "Rules: "); 
                Lex lex = new Lex(sr);
                DatalogProgram p = new DatalogProgram(lex);
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };            

            StringReader sr = new StringReader(
                "Schemes: Q(A)" +
                "Facts: " +
                "Rules: " +
                "Queries: Q('a')?"); 
            Lex lex = new Lex(sr);
            DatalogProgram p = new DatalogProgram(lex);
            assertTrue(p.toString().equals(
                "Schemes:" + eoln +
                "Q(A)Facts:" + eoln +
                "Rules:" + eoln +
                "Queries:" + eoln +
                "Q('a')? No\n"
                ));

            sr = new StringReader(
                "Schemes: Q(A) P(B)\n" +
                "Facts: Q('1').\n" +
                       "Q('2').\n" +
                       "P('a').\n" +
                "Rules: Q(A):-P(A).\n" +
                "Queries: Q('a')?\n" +
                "         P('b')?"); 
            lex = new Lex(sr);
            p = new DatalogProgram(lex);
            assertTrue(p.toString().equals(
                "Schemes:" + eoln +
                "Q(A)P(B)Facts:" + eoln +
                "Q('1').Q('2').P('a').Rules:" + eoln +
                "Q(A):-P(A).Queries:" + eoln +
                "Q('a')? No\n" +
                "P('b')? No\n"
                ));

 
        }catch(ParserException e){
            System.err.println("ERROR in DatalogProgram::test1()\n" +
                               "Should not be able to get here" + e);
        };
    }


    ///////////////////
    static boolean debug = false;
    static private String eoln = System.getProperty("line.separator");

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
     * Usage: java DatalogProgramTest
     */
    public static void main (String[] args)
    {
        /* junit.textui.TestRunner will write the test results to stdout. */
        junit.textui.TestRunner.run (DatalogProgramTest.class);

        /* junit.swingui.TestRunner will display the test results in JUnit's
           swing interface. */
        //junit.swingui.TestRunner.run (DatalogProgramTest.class);
    }
}
