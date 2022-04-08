package testproject3;

import junit.framework.TestCase;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;

import project3.lex.Constant;
import project3.lex.Identifier;
import project3.lex.Lex;
import project3.lex.TokenType;
import project3.three.ParserException;
import project3.three.Predicate;
import project3.three.Head;

/**
 * HeadTest is a JUnit test for class Head
 */
public class HeadTest
    extends TestCase
{
    /**
     * Constructs a test case for the test specified in the name argument.
     */
    public HeadTest (String name)
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


    //Since the Head constructor just calls the FactList constructor, and
    //we have tested the FactList constructor, we won't test the Head
    //constructor here.

    /**
     * Test canProve(Predicate);
     */
    public void testCanProve(){
        try{
        	System.out.print("HeadTest ");
            Identifier idA = new Identifier("A", 1);
            Identifier idB = new Identifier("B", 1);
            Identifier idC = new Identifier("C", 1);

            Constant c1 = new Constant("Id1", 1);
            Constant c2 = new Constant("Id2", 1);
            Constant c3 = new Constant("Id3", 1);

            Predicate s1 = createPredicate("A('Id1')");
            Predicate s2 = createPredicate("A('Id2')");

            Predicate s3 = createPredicate("B('Id1', 'Id2')");
            Predicate s4 = createPredicate("B('Id2','Id3')");

            Predicate s5 = createPredicate("C('Id1','Id2','Id3')");
            Predicate s6 = createPredicate("C('Id3','Id2','Id1')");

            Predicate bad1 = createPredicate("A(Id1)");
            Predicate bad2 = createPredicate("A(Id1, 'Id2')");
            Predicate bad3 = createPredicate("A('Id1', Id2)");
            Predicate bad4 = createPredicate("A(Id1, Id2)");
            Predicate bad5 = createPredicate("C(Id2,'Id3','Id1')");
            Predicate bad6 = createPredicate("C('Id2',Id3,'Id1')");
            Predicate bad7 = createPredicate("C('Id2','Id3',Id1)");
            Predicate bad8 = createPredicate("C('Id2',Id3,Id1)");
            Predicate bad9 = createPredicate("C(Id2,'Id3',Id1)");
            Predicate bad10 = createPredicate("C(Id2,Id3,'Id1')");
            Predicate bad11 = createPredicate("C(Id2,Id3,Id1)");

            Predicate[] badOnes =
                {bad1, bad2, bad3, bad4, bad5, bad6, bad7, bad8, bad9, bad10,
                 bad11};

            //=================================================================
            
            Head h = createHead("A(A)");

            try{
                h.unify(null); 
                assertTrue(false);
            }catch(AssertionError e){
                assertTrue(true);
            };

            try{
                h.unify(s3); 
                assertTrue(false);
            }catch(AssertionError e){
                assertTrue(true);
            };

            assertTrue(checkAllBad(h, badOnes));

            HashMap<Identifier,Constant> map = h.unify(s1);

            assertTrue(map.get(idA).equals(c1));
            assertFalse(map.get(idA).equals(c2));
            assertTrue(map.get(idB) == null);

            map = h.unify(s2);
            assertFalse(map.get(idA).equals(c1));
            assertTrue(map.get(idA).equals(c2));
            assertTrue(map.get(idB) == null);

            //=================================================================

            h = createHead("A(A, B)");

            try{
                h.unify(null); 
                assertTrue(false);
            }catch(AssertionError e){
                assertTrue(true);
            };

            try{
                h.unify(s1); 
                assertTrue(false);
            }catch(AssertionError e){
                assertTrue(true);
            };

            try{
                h.unify(s5); 
                assertTrue(false);
            }catch(AssertionError e){
                assertTrue(true);
            };

            assertTrue(checkAllBad(h, badOnes));

            map = h.unify(s3);
            assertTrue(map.get(idA).equals(c1));
            assertTrue(map.get(idB).equals(c2));
            assertFalse(map.get(idA).equals(c2));
            assertFalse(map.get(idB).equals(c1));
            assertTrue(map.get(idC) == null);

            map = h.unify(s4);
            assertTrue(map.get(idA).equals(c2));
            assertTrue(map.get(idB).equals(c3));
            assertFalse(map.get(idA).equals(c3));
            assertFalse(map.get(idB).equals(c2));
            assertTrue(map.get(idC) == null);
            
            //=================================================================

            h = createHead("A(A, B, C)");

            try{
                h.unify(null); 
                assertTrue(false);
            }catch(AssertionError e){
                assertTrue(true);
            };

            try{
                h.unify(s1); 
                assertTrue(false);
            }catch(AssertionError e){
                assertTrue(true);
            };

            try{
                h.unify(s3); 
                assertTrue(false);
            }catch(AssertionError e){
                assertTrue(true);
            };

            assertTrue(checkAllBad(h, badOnes));

            map = h.unify(s5);
            assertTrue(map.get(idA).equals(c1));
            assertTrue(map.get(idB).equals(c2));
            assertTrue(map.get(idC).equals(c3));
            assertFalse(map.get(idA).equals(c2));
            assertFalse(map.get(idA).equals(c3));
            assertFalse(map.get(idB).equals(c1));
            assertFalse(map.get(idB).equals(c3));
            assertFalse(map.get(idC).equals(c1));
            assertFalse(map.get(idC).equals(c2));

            map = h.unify(s6);
            assertTrue(map.get(idA).equals(c3));
            assertTrue(map.get(idB).equals(c2));
            assertTrue(map.get(idC).equals(c1));
            assertFalse(map.get(idA).equals(c1));
            assertFalse(map.get(idA).equals(c2));
            assertFalse(map.get(idB).equals(c1));
            assertFalse(map.get(idB).equals(c3));
            assertFalse(map.get(idC).equals(c2));
            assertFalse(map.get(idC).equals(c3));

        	System.out.println("PASSED");
        }catch(ParserException e){
             System.out.println(
                 "ERROR: in HeadTest::testCanProve()\n" +
                 e.getMessage());
        };
    }

    /**
     * Test matches(Predicate);
     */
    public void testMatches(){
        try{
            Identifier idA = new Identifier("A", 1);
            Identifier idB = new Identifier("B", 1);
            Identifier idC = new Identifier("C", 1);

            Constant c1 = new Constant("Id1", 1);
            Constant c2 = new Constant("Id2", 1);
            Constant c3 = new Constant("Id3", 1);

            Predicate s1 = createPredicate("A('Id1')");
            Predicate s2 = createPredicate("A('Id2','Id3')");
            Predicate s3 = createPredicate("A('Id4','Id5','Id6')");
            Predicate s4 = createPredicate("B('Id7')");;
            Predicate s5 = createPredicate("B('Id8','Id9')");;
            Predicate s6 = createPredicate(
                "B('Id10','Id20','Id30')");;

            //=================================================================

            Head h = createHead("A(A)");

            assertTrue(h.matches(s1));
            assertFalse(h.matches(s2));
            assertFalse(h.matches(s3));
            assertFalse(h.matches(s4));
            assertFalse(h.matches(s5));
            assertFalse(h.matches(s6));


            //=================================================================

            h = createHead("A(A, B)");
            assertFalse(h.matches(s1));
            assertTrue(h.matches(s2));
            assertFalse(h.matches(s3));
            assertFalse(h.matches(s4));
            assertFalse(h.matches(s5));
            assertFalse(h.matches(s6));

            //=================================================================

            h = createHead("A(A, B, C)");
            assertFalse(h.matches(s1));
            assertFalse(h.matches(s2));
            assertTrue(h.matches(s3));
            assertFalse(h.matches(s4));
            assertFalse(h.matches(s5));
            assertFalse(h.matches(s6));

        }catch(ParserException e){
             System.out.println(
                 "ERROR: in HeadTest::testMatches()\n" +
                 e.getMessage());
        };
    }

    ///////////////////
    static boolean debug = false;
    static String eoln = System.getProperty("line.separator");

    private Head createHead(String s)
        throws ParserException
    {
        StringReader sr = new StringReader(s);
        Lex lex = new Lex(sr);
        return new Head(lex);
    }

    private Predicate createPredicate(String s)
        throws ParserException
    {
        StringReader sr = new StringReader(s);
        Lex lex = new Lex(sr);
        return new Predicate(lex);
    }

    private boolean checkAllBad(Head h, Predicate[] badOnes){
        boolean result = true;
        for(int i = 0; i < badOnes.length && result; i++){
            try{
                h.unify(badOnes[i]); 
                result = false;
            }catch(AssertionError e){
            };
        };
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
     * Usage: java HeadTest
     */
    public static void main (String[] args)
    {
        /* junit.textui.TestRunner will write the test results to stdout. */
        junit.textui.TestRunner.run (HeadTest.class);

        /* junit.swingui.TestRunner will display the test results in JUnit's
           swing interface. */
        //junit.swingui.TestRunner.run (HeadTest.class);
    }
}
