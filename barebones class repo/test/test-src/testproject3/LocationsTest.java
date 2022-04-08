package testproject3;


import junit.framework.TestCase;

import java.util.Iterator;

import project3.lex.Constant;
import project3.lex.TokenType;
import project3.three.ParserException;
import project3.three.Locations;
import project3.three.Location;

/**
 * LocationsTest is a JUnit test for class Locations
 */
public class LocationsTest
    extends TestCase
{
    /**
     * Constructs a test case for the test specified in the name argument.
     */
    public LocationsTest (String name)
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
     * Test Locations(lex)
     */
    public void testLocationsConstructor(){
        Locations l = new Locations();
        assertTrue(l.iterator().hasNext() == false);
    }

    /**
     * Test addLocation(Constant) and iterator()
     */
    public void testAddLocationAndGetLocations(){
        try{
            Locations l = new Locations();
            l.addLocation(null);
            assertTrue(false);
        }catch(AssertionError e){
            assertTrue(true);
        };

        Location loc1 = new Location(0,0);
        Location loc2 = new Location(0,0);
        Location loc3 = new Location(1,0);
        Location loc4 = new Location(1,1);

        Locations l = new Locations();
        l.addLocation(loc1);
        Iterator<Location> iter = l.iterator();
        assertTrue(iter.hasNext());
        Location loc = iter.next();
        assertTrue(loc == loc1);
        assertTrue(loc != loc2);
        assertFalse(iter.hasNext());

        l.addLocation(loc2);
        iter = l.iterator();
        assertTrue(iter.hasNext());
        loc = iter.next();
        assertTrue(loc == loc1);
        assertTrue(loc != loc2);
        assertFalse(iter.hasNext());

        l.addLocation(loc3);
        iter = l.iterator();
        assertTrue(iter.hasNext());
        loc = iter.next();
        assertTrue(loc.equals(loc1) || loc.equals(loc3));
        assertTrue(loc != loc2);
        assertTrue(iter.hasNext());
        loc = iter.next();
        assertTrue(loc.equals(loc1) || loc.equals(loc3));
        assertFalse(iter.hasNext());

        l.addLocation(loc4);
        iter = l.iterator();
        assertTrue(iter.hasNext());
        loc = iter.next();
        assertTrue(loc.equals(loc1) || loc.equals(loc3) || loc.equals(loc4));
        assertTrue(loc != loc2);
        assertTrue(iter.hasNext());
        loc = iter.next();
        assertTrue(loc.equals(loc1) || loc.equals(loc3) || loc.equals(loc4));
        assertTrue(iter.hasNext());
        loc = iter.next();
        assertTrue(loc.equals(loc1) || loc.equals(loc3) || loc.equals(loc4));
        assertFalse(iter.hasNext());
        
    	System.out.print( "LocationsTest " );
    	System.out.println( "PASSED" );
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
     * Usage: java LocationsTest
     */
    public static void main (String[] args)
    {
        /* junit.textui.TestRunner will write the test results to stdout. */
        junit.textui.TestRunner.run (LocationsTest.class);

        /* junit.swingui.TestRunner will display the test results in JUnit's
           swing interface. */
        //junit.swingui.TestRunner.run (LocationsTest.class);
    }
}
