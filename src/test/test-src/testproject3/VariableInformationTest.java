package testproject3;


import junit.framework.TestCase;

import java.util.HashSet;
import java.util.Iterator;

import project3.lex.Constant;
import project3.lex.Identifier;
import project3.lex.TokenType;
import project3.three.ParserException;
import project3.three.VariableInformation;
import project3.three.Location;

/**
 * VariableInformationTest is a JUnit test for class VariableInformation
 */
public class VariableInformationTest
    extends TestCase
{
    /**
     * Constructs a test case for the test specified in the name argument.
     */
    public VariableInformationTest (String name)
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
     * Test VariableInformation(lex)
     */
    public void testVariableInformationConstructor(){
        VariableInformation v = new VariableInformation();
        assertTrue(v.getVariables().length == 0);
    }

    /**
     * Test addLocationFor(Identifier, Location) and
     * getLocationsFor(Identifier).
     */
    public void testAddLocationForAndGetLocationsFor(){
    	try{
            Identifier var1 = new Identifier("A", 1);
            Identifier var2 = new Identifier("B", 1);

            Location loc1 = new Location(0,0);
            Location loc2 = new Location(0,1);
            Location loc3 = new Location(0,2);
            Location loc4 = new Location(1,0);
            Location loc5 = new Location(1,1);
            Location loc6 = new Location(1,2);
            
            VariableInformation v = new VariableInformation();

            try{
                v.addLocationFor(var1, null);
                assertTrue(false);
            }catch(AssertionError e){
                assertTrue(true);
            };

            try{
                v.addLocationFor(null, loc1);
                assertTrue(false);
            }catch(AssertionError e){
                assertTrue(true);
            };

            try{
                v.addLocationFor(null, null);
                assertTrue(false);
            }catch(AssertionError e){
                assertTrue(true);
            };

            try{
                v.getLocationsFor(null);
                assertTrue(false);
            }catch(AssertionError e){
                assertTrue(true);
            };

            assertTrue(v.getLocationsFor(var1) == null);

            v.addLocationFor(var1, loc1);
            Iterator<Location> iter = v.getLocationsFor(var1);
            assertTrue(iter.hasNext());
            assertTrue(iter.next() == loc1);
            assertFalse(iter.hasNext());

            assertTrue(v.getLocationsFor(var2) == null);

            v.addLocationFor(var1, loc2);
            v.addLocationFor(var1, loc3);
            HashSet<Location> set = new HashSet<Location>();
            iter = v.getLocationsFor(var1);
            while(iter.hasNext()){
               set.add(iter.next());
            };
            assertTrue(set.size() == 3);
            assertTrue(set.contains(loc1));
            assertTrue(set.contains(loc2));
            assertTrue(set.contains(loc3));

            assertTrue(v.getLocationsFor(var2) == null);

            v.addLocationFor(var2, loc4);
            set = new HashSet<Location>();
            iter = v.getLocationsFor(var1);
            while(iter.hasNext()){
               set.add(iter.next());
            };
            assertTrue(set.size() == 3);
            assertTrue(set.contains(loc1));
            assertTrue(set.contains(loc2));
            assertTrue(set.contains(loc3));
            iter = v.getLocationsFor(var2);
            assertTrue(iter.hasNext());
            assertTrue(iter.next() == loc4);
            assertFalse(iter.hasNext());

            v.addLocationFor(var2, loc5);
            v.addLocationFor(var2, loc6);
            set = new HashSet<Location>();
            iter = v.getLocationsFor(var1);
            while(iter.hasNext()){
               set.add(iter.next());
            };
            assertTrue(set.size() == 3);
            assertTrue(set.contains(loc1));
            assertTrue(set.contains(loc2));
            assertTrue(set.contains(loc3));
            set = new HashSet<Location>();
            iter = v.getLocationsFor(var2);
            while(iter.hasNext()){
               set.add(iter.next());
            };
            assertTrue(set.size() == 3);
            assertTrue(set.contains(loc4));
            assertTrue(set.contains(loc5));
            assertTrue(set.contains(loc6));
        }catch(ParserException e){
             System.out.println(
                 "ERROR: in VariableInformationTest::" +
                 "testAddLocationForAndGetLocationFor()\n" +
                 e.getMessage());
        };
    };

    /**
     * Test remove(Identifier)
     */
    public void testRemove(){
        try{
            Identifier var1 = new Identifier("A", 1);
            Identifier var2 = new Identifier("B", 1);
            Identifier var3 = new Identifier("C", 1);
            Identifier var4 = new Identifier("D", 1);

            Location loc1 = new Location(0,0);
            Location loc2 = new Location(0,1);
            Location loc3 = new Location(0,2);
            
            VariableInformation v = new VariableInformation();

            assertTrue(v.getLocationsFor(var1) == null);

            v.addLocationFor(var1, loc1);
            assertTrue(v.getLocationsFor(var1) != null);
            assertTrue(v.getLocationsFor(var2) == null);

            v.addLocationFor(var2, loc2);
            assertTrue(v.getLocationsFor(var2) != null);
            assertTrue(v.getLocationsFor(var3) == null);

            v.addLocationFor(var3, loc3);
            assertTrue(v.getLocationsFor(var3) != null);
            assertTrue(v.getLocationsFor(var4) == null);

            v.remove(var2);
            assertTrue(v.getLocationsFor(var1) != null);
            assertTrue(v.getLocationsFor(var2) == null);
            assertTrue(v.getLocationsFor(var3) != null);

            v.remove(var1);
            assertTrue(v.getLocationsFor(var1) == null);
            assertTrue(v.getLocationsFor(var2) == null);
            assertTrue(v.getLocationsFor(var3) != null);

            v.remove(var3);
            assertTrue(v.getLocationsFor(var1) == null);
            assertTrue(v.getLocationsFor(var2) == null);
            assertTrue(v.getLocationsFor(var3) == null);

        	System.out.print( "VariableInformationTest " );
        	System.out.println( "PASSED" );
            
        }catch(ParserException e){
             System.out.println(
                 "ERROR: in VariableInformationTest::" +
                 "testAddLocationForAndGetLocationFor()\n" +
                 e.getMessage());
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
     * Usage: java VariableInformationTest
     */
    public static void main (String[] args)
    {
        /* junit.textui.TestRunner will write the test results to stdout. */
        junit.textui.TestRunner.run (VariableInformationTest.class);

        /* junit.swingui.TestRunner will display the test results in JUnit's
           swing interface. */
        //junit.swingui.TestRunner.run (VariableInformationTest.class);
    }
}
