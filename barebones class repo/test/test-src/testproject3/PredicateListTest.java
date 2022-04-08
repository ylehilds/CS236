package testproject3;

import junit.framework.TestCase;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;

import project3.lex.Constant;
import project3.lex.Identifier;
import project3.lex.Lex;
import project3.lex.TokenType;
import project3.three.Domain;
import project3.three.Node;
import project3.three.ParserException;
import project3.three.Predicate;
import project3.three.Location;
import project3.three.VariableInformation;

import project3.PredicateList;

/**
 * PredicateListTest is a JUnit test for class PredicateList
 */
public class PredicateListTest
    extends TestCase
{
    /**
     * Constructs a test case for the test specified in the name argument.
     */
    public PredicateListTest (String name)
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
     * Test Lex constructor.
     */
    public void testLexConstructor(){
        try{
            Identifier A = new Identifier("A", 1);
            Identifier B = new Identifier("B", 1);
            Identifier C = new Identifier("C", 1);

            Identifier[] idSetA = {A};
            Identifier[] idSetAB = {A,B};
            Identifier[] idSetABC = {A,B,C};

            Location location00= new Location(0,0);
            Location location01= new Location(0,1);
            Location location02= new Location(0,2);
            Location location10= new Location(1,0);
            Location location11= new Location(1,1);
            Location location12= new Location(1,2);
            Location location20= new Location(2,0);
            Location location21= new Location(2,1);
            Location location22= new Location(2,2);

            ExtendedPredicateList pl = createPredicateList("A('A').");
            VariableInformation vinfo = pl.getVariableInformation();
            Identifier[] variables = vinfo.getVariables();
            assertTrue(variables.length == 0);

            pl = createPredicateList("A(A).");
            vinfo = pl.getVariableInformation();
            assertTrue(sameAs(idSetA, vinfo.getVariables()));
            Location[] locs1 = {location00};
            checkLocations(vinfo.getLocationsFor(A), locs1);

            pl = createPredicateList("A(A, B, C).");
            vinfo = pl.getVariableInformation();
            assertTrue(sameAs(idSetABC, vinfo.getVariables()));
            Location[] locs2 = {location00};
            checkLocations(vinfo.getLocationsFor(A), locs2);
            Location[] locs3 = {location01};
            checkLocations(vinfo.getLocationsFor(B), locs3);
            Location[] locs4 = {location02};
            checkLocations(vinfo.getLocationsFor(C), locs4);

            pl = createPredicateList("A(A),B(B).");
            vinfo = pl.getVariableInformation();
            assertTrue(sameAs(idSetAB, vinfo.getVariables()));
            Location[] locs5 = {location00};
            checkLocations(vinfo.getLocationsFor(A), locs5);
            Location[] locs6 = {location10};
            checkLocations(vinfo.getLocationsFor(A), locs6);

            pl = createPredicateList("A(A,B,C),B(B,B,A).");
            vinfo = pl.getVariableInformation();
            assertTrue(sameAs(idSetABC, vinfo.getVariables()));
            Location[] locs7 = {location00, location12};
            checkLocations(vinfo.getLocationsFor(A), locs7);
            Location[] locs8 = {location01, location10, location11};
            checkLocations(vinfo.getLocationsFor(B), locs8);
            Location[] locs9 = {location02};
            checkLocations(vinfo.getLocationsFor(C), locs9);

            pl = createPredicateList("A('A',B,C),B(B,'B',A),A(A,A,A)");
            vinfo = pl.getVariableInformation();
            assertTrue(sameAs(idSetABC, vinfo.getVariables()));
            Location[] locs10 = {location12,location20,location21,location22};
            checkLocations(vinfo.getLocationsFor(A), locs10);
            Location[] locs11 = {location01, location10};
            checkLocations(vinfo.getLocationsFor(B), locs11);
            Location[] locs12 = {location02};
            checkLocations(vinfo.getLocationsFor(C), locs12);
        }catch(ParserException e){
             System.out.println(
                 "ERROR: in PredicateListTest::testLexConstructor()\n" +
                 e.getMessage());
        };
    }

    /**
     * Test copy constructor.
     */
    public void testCopyConstructor(){
        try{
            try{
                ExtendedPredicateList pl1 = null;
                ExtendedPredicateList pl2 = new ExtendedPredicateList(pl1);
                assertTrue(false);
            }catch(AssertionError e){
                assertTrue(true);
            };

            ExtendedPredicateList pl1 = createPredicateList("A('A').");
            ExtendedPredicateList pl2 = new ExtendedPredicateList(pl1);
            assertTrue(pl1.equals(pl2));

            pl1 = createPredicateList("A(A).");
            pl2 = new ExtendedPredicateList(pl1);
            assertTrue(pl1.equals(pl2));

            pl1 = createPredicateList("A(A, B, C).");
            pl2 = new ExtendedPredicateList(pl1);
            assertTrue(pl1.equals(pl2));

            pl1 = createPredicateList("A(A),B(B).");
            pl2 = new ExtendedPredicateList(pl1);
            assertTrue(pl1.equals(pl2));

            pl1 = createPredicateList("A(A,B,C),B(B,B,A).");
            pl2 = new ExtendedPredicateList(pl1);
            assertTrue(pl1.equals(pl2));

            pl1 = createPredicateList("A('A',B,C),B(B,'B',A),A(A,A,A).");
            pl2 = new ExtendedPredicateList(pl1);
            assertTrue(pl1.equals(pl2));
        }catch(ParserException e){
             System.out.println(
                 "ERROR: in PredicateListTest::testCopyConstructor()\n" +
                 e.getMessage());
        };
    };

    /**
     * Test setVariableToValue(Identifier, Constant).
     */
    public void testSetVariableToValue(){
        try{
            Identifier A = new Identifier("A", 1);
            Identifier B = new Identifier("B", 1);
            Identifier C = new Identifier("C", 1);

            Constant c1  = new Constant("1", 1);;
            Constant c2  = new Constant("2", 1);;

            ExtendedPredicateList pl1 = createPredicateList("A('A').");
            pl1.setVariableToValue(A, c1);

            pl1 = createPredicateList("A(A).");
            assertTrue(pl1.getPredicate(0).toString().equals("A(A)"));
            pl1.setVariableToValue(B, c1);
            assertTrue(pl1.getPredicate(0).toString().equals("A(A)"));
            pl1.setVariableToValue(A, c1);
            assertTrue(pl1.getPredicate(0).toString().equals("A('1')"));

            pl1 = createPredicateList("A(A,B,C).");
            assertTrue(pl1.getPredicate(0).toString().equals("A(A,B,C)"));
            pl1.setVariableToValue(A, c1);
            pl1.setVariableToValue(B, c2);
            assertTrue(pl1.getPredicate(0).toString().equals(
                "A('1','2',C)"));
            pl1.setVariableToValue(C, c2);
            assertTrue(pl1.getPredicate(0).toString().equals(
                "A('1','2','2')"));

            pl1 = createPredicateList("A(A,B,A).");
            assertTrue(pl1.getPredicate(0).toString().equals("A(A,B,A)"));
            pl1.setVariableToValue(A, c1);
            assertTrue(pl1.getPredicate(0).toString().equals(
                "A('1',B,'1')"));

            pl1 = createPredicateList("A(A,B,C),X(A,B,A).");
            assertTrue(pl1.getPredicate(0).toString().equals("A(A,B,C)"));
            assertTrue(pl1.getPredicate(1).toString().equals("X(A,B,A)"));
            pl1.setVariableToValue(A, c1);
            assertTrue(pl1.getPredicate(0).toString().equals(
                "A('1',B,C)"));
            assertTrue(pl1.getPredicate(1).toString().equals(
                "X('1',B,'1')"));
            pl1.setVariableToValue(C, c2);
            assertTrue(pl1.getPredicate(0).toString().equals(
                "A('1',B,'2')"));
            assertTrue(pl1.getPredicate(1).toString().equals(
                "X('1',B,'1')"));

            pl1 = createPredicateList("X(B,B,C),Y(B,C,B),Z(C,B,B).");
            assertTrue(pl1.getPredicate(0).toString().equals("X(B,B,C)"));
            assertTrue(pl1.getPredicate(1).toString().equals("Y(B,C,B)"));
            assertTrue(pl1.getPredicate(2).toString().equals("Z(C,B,B)"));
            pl1.setVariableToValue(A, c1);
            assertTrue(pl1.getPredicate(0).toString().equals("X(B,B,C)"));
            assertTrue(pl1.getPredicate(1).toString().equals("Y(B,C,B)"));
            assertTrue(pl1.getPredicate(2).toString().equals("Z(C,B,B)"));
            pl1.setVariableToValue(C, c2);
            assertTrue(pl1.getPredicate(0).toString().equals(
                "X(B,B,'2')"));
            assertTrue(pl1.getPredicate(1).toString().equals(
                "Y(B,'2',B)"));
            assertTrue(pl1.getPredicate(2).toString().equals(
                "Z('2',B,B)"));
            pl1.setVariableToValue(B, c2);
            assertTrue(pl1.getPredicate(0).toString().equals(
                "X('2','2','2')"));
            assertTrue(pl1.getPredicate(1).toString().equals(
                "Y('2','2','2')"));
            assertTrue(pl1.getPredicate(2).toString().equals(
                "Z('2','2','2')"));
           
            
        }catch(ParserException e){
             System.out.println(
                 "ERROR: in PredicateListTest::testSetVariableToValue(" +
                 "Identifier,Constant)\n" +
                 e.getMessage());
        };
    }

    /**
     * Test setVariables(HashMap<Identifier,Constant>).
     */
    public void testSetVariables(){
        try{
            Identifier A = new Identifier("A", 1);
            Identifier B = new Identifier("B", 1);
            Identifier C = new Identifier("C", 1);

            Constant c1  = new Constant("1", 1);;
            Constant c2  = new Constant("2", 1);;
            Constant c3  = new Constant("3", 1);;

            HashMap<Identifier, Constant> map =
                new HashMap<Identifier,Constant>();
            map.put(A, c1);

            ExtendedPredicateList pl1 = createPredicateList("A(A).");
            pl1.setVariables(map);
            assertTrue(pl1.getPredicate(0).toString().equals("A('1')"));

            pl1 = createPredicateList("A(A,B,C).");
            pl1.setVariables(map);
            assertTrue(pl1.getPredicate(0).toString().equals(
                "A('1',B,C)"));

            map.put(B,c2);
            pl1 = createPredicateList("A(A,B,C).");
            pl1.setVariables(map);
            assertTrue(pl1.getPredicate(0).toString().equals(
                "A('1','2',C)"));
            map.put(C,c2);
            pl1.setVariables(map);
            assertTrue(pl1.getPredicate(0).toString().equals(
                "A('1','2','2')"));

            map = new HashMap<Identifier,Constant>();
            map.put(A, c1);
            pl1 = createPredicateList("A(A,B,A).");
            pl1.setVariables(map);
            assertTrue(pl1.getPredicate(0).toString().equals(
                "A('1',B,'1')"));

            pl1 = createPredicateList("A(A,B,C),X(A,B,A).");
            pl1.setVariables(map);
            assertTrue(pl1.getPredicate(0).toString().equals(
                "A('1',B,C)"));
            assertTrue(pl1.getPredicate(1).toString().equals(
                "X('1',B,'1')"));
            map.put(C,c2);
            pl1.setVariables(map);
            assertTrue(pl1.getPredicate(0).toString().equals(
                "A('1',B,'2')"));
            assertTrue(pl1.getPredicate(1).toString().equals(
                "X('1',B,'1')"));

            pl1 = createPredicateList("X(B,B,C),Y(B,C,B),Z(C,B,B).");
            map = new HashMap<Identifier,Constant>();
            map.put(A, c1);
            pl1.setVariables(map);
            assertTrue(pl1.getPredicate(0).toString().equals("X(B,B,C)"));
            assertTrue(pl1.getPredicate(1).toString().equals("Y(B,C,B)"));
            assertTrue(pl1.getPredicate(2).toString().equals("Z(C,B,B)"));
            map.put(C,c2);
            pl1.setVariables(map);
            assertTrue(pl1.getPredicate(0).toString().equals(
                "X(B,B,'2')"));
            assertTrue(pl1.getPredicate(1).toString().equals(
                "Y(B,'2',B)"));
            assertTrue(pl1.getPredicate(2).toString().equals(
                "Z('2',B,B)"));
            map.put(B,c2);
            pl1.setVariables(map);
            assertTrue(pl1.getPredicate(0).toString().equals(
                "X('2','2','2')"));
            assertTrue(pl1.getPredicate(1).toString().equals(
                "Y('2','2','2')"));
            assertTrue(pl1.getPredicate(2).toString().equals(
                "Z('2','2','2')"));

        	System.out.print( "PredicateListTest " );
        	System.out.println( "PASSED" );
            
        }catch(ParserException e){
             System.out.println(
                 "ERROR: in PredicateListTest::testSetVariables(" +
                 "HashMap<Identifier,Constant>))\n" +
                 e.getMessage());
        };
    }

    // Test evaluate() and recurse(). Done in QueryTest

    ///////////////////
    static boolean debug = false;
    static String eoln = System.getProperty("line.separator");

    private class ExtendedPredicateList extends PredicateList{
        public ExtendedPredicateList(Lex lex)throws ParserException{
            super(lex);
        };
        public ExtendedPredicateList(ExtendedPredicateList pl)
            throws ParserException
        {
            super(pl);
        };
        public VariableInformation getVariableInformation(){
            return variableInformation;
        };

        public Predicate getPredicate(int i){
            return (Predicate)nodes.get(i);
        }
    }

    private ExtendedPredicateList createPredicateList(String s)
        throws ParserException
    {
        StringReader sr = new StringReader(s);
        Lex lex = new Lex(sr);
        return new ExtendedPredicateList(lex);
    }

    private boolean sameAs(Identifier[] idSet, Identifier[] variables){
        boolean result = idSet.length == variables.length;
        for(int i = 0; i < idSet.length - 1 && result; i++){
            for(int j = i+1; j < idSet.length && result; j++){
                result = !idSet[i].equals(idSet[j]);
            }
        };
        for(int i = 0; i < idSet.length && result; i++){
            boolean answer = false;
            for(int j = 0; j < variables.length && !answer; j++){
                answer = idSet[i].equals(variables[j]);
            };
            result = answer;
        };
        return result;
    };

    private boolean checkLocations(Iterator<Location> locations,
                                   Location[] locs){
        boolean result = true;
        int count = 0;
        while(locations.hasNext() && result){
            Location l = locations.next();
            boolean found = false;
            for(int i = 0; i < locs.length && !found; i++){
                found = l.equals(locs[i]);
            };
            result = found;
            count++; 
        };
        result = result && count == locs.length;
        return result;
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
     * Usage: java PredicateListTest
     */
    public static void main (String[] args)
    {
        /* junit.textui.TestRunner will write the test results to stdout. */
        junit.textui.TestRunner.run (PredicateListTest.class);

        /* junit.swingui.TestRunner will display the test results in JUnit's
           swing interface. */
        //junit.swingui.TestRunner.run (PredicateListTest.class);
    }
}
