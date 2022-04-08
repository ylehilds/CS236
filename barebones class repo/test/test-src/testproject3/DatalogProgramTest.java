package testproject3;

import junit.framework.TestCase;

import project3.three.DatalogProgram;

/**
 * DatalogProgramTest is a JUnit test for class DatalogProgram
 */
public class DatalogProgramTest
    extends TestCase
{
    /**
     * Constructs a test case for the test specified in the name argument.
     */
    public DatalogProgramTest (String name)
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

    //The DatalogProgram has been tested in QueryTest.java
    public void testNothing(){
    	System.out.println("DatalogProgramTest PASSED");
    };


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
