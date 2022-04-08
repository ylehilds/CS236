package testproject3;

import junit.framework.TestCase;

import project3.three.Location;

/**
 * LocationTest is a JUnit test for class Location
 */
public class LocationTest
    extends TestCase
{
    /**
     * Constructs a test case for the test specified in the name argument.
     */
    public LocationTest (String name)
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
     * Test Location(lex)
     */
    public void testLocationConstructor(){
        try{
            try{
                Location location = new Location(-1, 0);
                assertTrue(false);
            }catch(AssertionError e){
                assertTrue(true);
            };

            try{
                Location location = new Location(0, -1);
                assertTrue(false);
            }catch(AssertionError e){
                assertTrue(true);
            };

            try{
                Location location = new Location(-1, -1);
                assertTrue(false);
            }catch(AssertionError e){
                assertTrue(true);
            };

            try{
                Location location = new Location(-111, -1);
                assertTrue(false);
            }catch(AssertionError e){
                assertTrue(true);
            };

            try{
                Location location = new Location(-11, -1111);
                assertTrue(false);
            }catch(AssertionError e){
                assertTrue(true);
            };

            try{
                Location location = new Location(-111111, -1111);
                assertTrue(false);
            }catch(AssertionError e){
                assertTrue(true);
            };

            Location location = new Location(0,0);
            assertTrue(location.getPredicateIndex() == 0);
            assertTrue(location.getLocationInPredicate() == 0);
            assertTrue(location.hashCode() == 0);

            location = new Location(1,1);
            assertTrue(location.getPredicateIndex() == 1);
            assertTrue(location.getLocationInPredicate() == 1);
            assertTrue(location.hashCode() == 74);

            location = new Location(0,1);
            assertTrue(location.getPredicateIndex() == 0);
            assertTrue(location.getLocationInPredicate() == 1);
            assertTrue(location.hashCode() == 1);

            location = new Location(1,0);
            assertTrue(location.getPredicateIndex() == 1);
            assertTrue(location.getLocationInPredicate() == 0);
            assertTrue(location.hashCode() == 73);

            location = new Location(10,1);
            assertTrue(location.getPredicateIndex() == 10);
            assertTrue(location.getLocationInPredicate() == 1);
            assertTrue(location.hashCode() == 731);

            location = new Location(1,10);
            assertTrue(location.getPredicateIndex() == 1);
            assertTrue(location.getLocationInPredicate() == 10);
            assertTrue(location.hashCode() == 83);

            location = new Location(5,10);
            assertTrue(location.getPredicateIndex() == 5);
            assertTrue(location.getLocationInPredicate() == 10);
            assertTrue(location.hashCode() == 375);
        }catch(AssertionError e){
            System.out.println(
                "ERROR in LocationTest.testLocationConstructor\n" +
                "    should not get here.\n" +
                "    error = " + e.getMessage());
        };
    }

    /**
     * Test equals.
     */
    public void testEquals(){
        //=====================================================================
        Location location1 = new Location(0,0);
        //=====================================================================

        assertFalse(location1.equals(null));
        assertFalse(location1.equals(1));
        assertFalse(location1.equals(new Integer(5)));

        //=====================================================================

        Location location2 = new Location(0,0);
        assertTrue(location1.equals(location2));
        assertTrue(location2.equals(location1));

        location2 = new Location(1,0);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(15,0);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(0,1);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(0,22);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(1,1);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(8,1);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(1,11);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(9,11);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        //=====================================================================
        location1 = new Location(1,1);
        //=====================================================================

        location2 = new Location(0,0);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(1,0);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(15,0);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(0,1);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(0,22);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(1,1);
        assertTrue(location1.equals(location2));
        assertTrue(location2.equals(location1));

        location2 = new Location(8,1);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(1,11);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(9,11);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        //=====================================================================
        location1 = new Location(1,0);
        //=====================================================================

        location2 = new Location(0,0);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(1,0);
        assertTrue(location1.equals(location2));
        assertTrue(location2.equals(location1));

        location2 = new Location(15,0);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(0,1);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(0,22);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(1,1);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(8,1);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(1,11);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(9,11);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        //=====================================================================
        location1 = new Location(0,1);
        //=====================================================================

        location2 = new Location(0,0);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(1,0);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(15,0);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(0,1);
        assertTrue(location1.equals(location2));
        assertTrue(location2.equals(location1));

        location2 = new Location(0,22);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(1,1);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(8,1);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(1,11);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(9,11);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        //=====================================================================
        location1 = new Location(10,1);
        //=====================================================================

        location2 = new Location(10,1);
        assertTrue(location1.equals(location2));
        assertTrue(location2.equals(location1));

        location2 = new Location(0,0);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(1,0);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(15,0);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(0,1);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(0,22);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(1,1);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(8,1);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(1,11);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(9,11);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        //=====================================================================
        location1 = new Location(1,10);
        //=====================================================================

        location2 = new Location(1,10);
        assertTrue(location1.equals(location2));
        assertTrue(location2.equals(location1));

        location2 = new Location(0,0);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(1,0);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(15,0);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(0,1);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(0,22);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(1,1);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(8,1);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(1,11);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(9,11);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        //=====================================================================
        location1 = new Location(5,10);
        //=====================================================================

        location2 = new Location(5,10);
        assertTrue(location1.equals(location2));
        assertTrue(location2.equals(location1));

        location2 = new Location(0,0);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(1,0);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(15,0);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(0,1);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(0,22);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(1,1);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(8,1);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(1,11);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));

        location2 = new Location(9,11);
        assertFalse(location1.equals(location2));
        assertFalse(location2.equals(location1));
        
    	System.out.print( "LocationTest " );
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
     * Usage: java LocationTest
     */
    public static void main (String[] args)
    {
        /* junit.textui.TestRunner will write the test results to stdout. */
        junit.textui.TestRunner.run (LocationTest.class);

        /* junit.swingui.TestRunner will display the test results in JUnit's
           swing interface. */
        //junit.swingui.TestRunner.run (LocationTest.class);
    }
}
