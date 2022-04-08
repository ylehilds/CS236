package testproject3;

import answerParser.*;
import junit.framework.TestCase;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import project3.lex.*;
import project3.three.*;
import project3.PredicateList;

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
         * should be qaced in the setUp() method instead.
         */
    }

    //=========================================================================
    //Test Constructors
    //=========================================================================
    /**
     * Test Multiple QueryList.
     */
    public void testMultipleQueryList(){
        try{
            DatalogProgram dp;
            QueryList q;
            StringBuffer strBuffer;
            Answers answers1;
            Answers answers2;

            dp = createDatalogProgram(
                "Schemes: A(X,Y)\n" +
                "         B(X)\n"   +
                "         C(X)\n"   +
                "         E(X)\n"   +
                "Facts:\n" +
                "    A('1','2').\n" +
                "    A('2','2').\n" +
                "    A('3','3').\n" +
                "    B('1').\n" +
                "    B('2').\n" +
                "    B('3').\n" +
                "    C('1').\n" +
                "    C('2').\n" +
                "    C('3').\n" +
                "Rules:\n" +
                "    R(X) :- B(X).\n" +
                "Queries:\n" +
                "    R('1')?" +
                "    R('5')?" 
              );
            Project3.datalogProgram = dp;
            q = dp.getQueryList();
            strBuffer = new StringBuffer();
            q.evaluate(strBuffer);
            answers1 = new Answers(strBuffer.toString());
            answers2 = new Answers(
              "R('1')? Yes\n\n" +
              "R('5')? No\n\n" 
            );

            dp = createDatalogProgram(
                "Schemes: A(X,Y)\n" +
                "         B(X)\n"   +
                "         C(X)\n"   +
                "         E(X)\n"   +
                "Facts:\n" +
                "    A('1','2').\n" +
                "    A('2','2').\n" +
                "    A('3','3').\n" +
                "    B('1').\n" +
                "    B('2').\n" +
                "    B('3').\n" +
                "    C('1').\n" +
                "    C('2').\n" +
                "    C('3').\n" +
                "Rules:\n" +
                "    R(X) :- B(X).\n" +
                "Queries:\n" +
                "    R('1')?" +
                "    R('5')?" +
                "    R('3')?"
              );
            Project3.datalogProgram = dp;
            q = dp.getQueryList();
            strBuffer = new StringBuffer();
            q.evaluate(strBuffer);
            answers1 = new Answers(strBuffer.toString());
            answers2 = new Answers(
              "R('1')? Yes\n\n" +
              "R('5')? No\n\n" +
              "R('3')? Yes\n\n" 
              );
            assertTrue(answers1.equals(answers2));

            dp = createDatalogProgram(
                "Schemes: A(X,Y)\n" +
                "         B(X)\n"   +
                "         C(X)\n"   +
                "         E(X)\n"   +
                "Facts:\n" +
                "    A('1','2').\n" +
                "    A('2','2').\n" +
                "    A('3','3').\n" +
                "    B('1').\n" +
                "    B('2').\n" +
                "    B('3').\n" +
                "    C('1').\n" +
                "    C('2').\n" +
                "    C('3').\n" +
                "Rules:\n" +
                "    R(X) :- B(X).\n" +
                "Queries:\n" +
                "    A(X,Y)?" +
                "    R(X)?" +
                "    C(X)?"
              );
            Project3.datalogProgram = dp;
            q = dp.getQueryList();
            strBuffer = new StringBuffer();
            q.evaluate(strBuffer);
            answers1 = new Answers(strBuffer.toString());
            answers2 = new Answers(
              "A(X,Y)? Yes\n" +
              "  Y = '2', X = '2'\n" +
              "  Y = '2', X = '1'\n" +
              "  Y = '3', X = '3'\n\n" +
              "R(X)? Yes\n" +
              "  X = '2'\n" +
              "  X = '1'\n" +
              "  X = '3'\n\n" +
              "C(X)? Yes\n"  +
              "  X = '2'\n" +
              "  X = '1'\n" +
              "  X = '3'\n\n"
              );
            assertTrue(answers1.equals(answers2));
            
        	System.out.print( "QueryListTest " );
        	System.out.println( "PASSED" );
        	
        }catch(ParserException e){
             System.out.println(
                 "ParserException ERROR: in QueryTest::" +
                 "testEvaluateAndRecurseForFacts()\n" +
                 e.getMessage());
        }catch(Exception e){
             System.out.println(
                 "Exception ERROR: in " +
                 "QueryTest::testEvaluateAndRecurseForFacts()\n" +
                 e.getMessage());
        };
    }


    ///////////////////
    static boolean debug = false;
    static String eoln = System.getProperty("line.separator");

    private DatalogProgram createDatalogProgram(String s)
        throws ParserException, Exception
    {
        StringReader sr = new StringReader(s);
        Lex lex = new Lex(sr);
        return new DatalogProgram(lex);
    }

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
