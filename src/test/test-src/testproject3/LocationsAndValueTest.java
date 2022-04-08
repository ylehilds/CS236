package testproject3;


import junit.framework.TestCase;

import java.util.Iterator;

import project3.lex.Constant;
import project3.lex.TokenType;
import project3.three.ParserException;
import project3.three.LocationsAndValue;
import project3.three.Location;

/**
 * LocationsAndValueTest is a JUnit test for class LocationsAndValue
 */
public class LocationsAndValueTest
    extends TestCase
{
    /**
     * Constructs a test case for the test specified in the name argument.
     */
    public LocationsAndValueTest (String name)
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
     * Test LocationsAndValue(lex)
     */
    public void testLocationsAndValueConstructor(){
        LocationsAndValue l = new LocationsAndValue();
        assertTrue(l.iterator().hasNext() == false);
        assertTrue(l.getValue() == null);
    }

    /**
     * Test setValue(Constant) and getValue()
     */
    public void testSetAndGetValue(){
    	System.out.print( "LocationsAndValueTest " );
        try{
            try{
                LocationsAndValue l = new LocationsAndValue();
                l.setValue(null);
                assertTrue(false);
            }catch(AssertionError e){
                assertTrue(true);
            };

            LocationsAndValue l = new LocationsAndValue();
            assertTrue(l.getValue() == null);

            Constant c = new Constant("A", 1);
            l.setValue(c);
            assertTrue(l.getValue() == c);
            Constant d = new Constant("A", 1);
            l.setValue(d);
            assertTrue(l.getValue() != c);
            assertTrue(l.getValue() == d);
            assertTrue(l.getValue().equals(c));
            Constant e = new Constant("B", 2);
            l.setValue(e);
            assertTrue(l.getValue() == e);
            assertTrue(l.getValue() != d);
            assertTrue(!l.getValue().equals(c));
            
        	System.out.println( "PASSED" );
        }catch(ParserException e){
            System.out.println(
                "ERROR: should not get here in LocationsAndValueTest::"+
                "testSetAndGetValue\n" + e.getMessage());
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

    /**
     * Utility main method. This will the test cases defined in this class.
     *
     * Usage: java LocationsAndValueTest
     */
    public static void main (String[] args)
    {
        /* junit.textui.TestRunner will write the test results to stdout. */
        junit.textui.TestRunner.run (LocationsAndValueTest.class);

        /* junit.swingui.TestRunner will display the test results in JUnit's
           swing interface. */
        //junit.swingui.TestRunner.run (LocationsAndValueTest.class);
    }
}
