package testproject3;


import junit.framework.TestCase;

import java.io.StringReader;
import java.util.HashSet;
import java.util.Iterator;

import project3.lex.Constant;
import project3.lex.Identifier;
import project3.lex.Lex;
import project3.lex.TokenType;
import project3.three.ParserException;
import project3.three.Predicate;
import project3.three.FactList;
import project3.three.Location;
import project3.three.FactList;

/**
 * FactListTest is a JUnit test for class FactList
 */
public class FactListTest
    extends TestCase
{
    /**
     * Constructs a test case for the test specified in the name argument.
     */
    public FactListTest (String name)
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


    //Since the FactList constructor just calls the FactList constructor, and
    //we have tested the FactList constructor, we won't test the FactList
    //constructor here.

    /**
     * Test canProve(Predicate);
     */
    public void testCanProve(){
        try{
            System.out.print( "FactListTest " );
            Predicate s1 = createPredicate("A(Id1)");
            Predicate s2 = createPredicate("A('Id1')");
            Predicate s3 = createPredicate("A('Id2')");
            Predicate s4 = createPredicate("B('Id1', Id2)");
            Predicate s5 = createPredicate("B(Id1, 'Id2')");
            Predicate s6 = createPredicate("B('Id1', 'Id2')");
            Predicate s7 = createPredicate("C('Id1','Id2','Id3')");
            Predicate s8 = createPredicate("C('Id1',Id2,'Id3')");
            Predicate s9 = createPredicate("B('Id2','Id3')");
            Predicate s10 = createPredicate("C('Id2','Id3','Id1')");

            Predicate t1 = createPredicate("D('Id1')");
            Predicate t2 = createPredicate("D('Id2')");
            Predicate t3 = createPredicate("E('Id1', 'Id2')");
            Predicate t4 = createPredicate("F('Id1','Id2','Id3')");

            Predicate[] sp1 = {s1, s2, s3, s4, s5, s6, s7, s8, s9, s10};
            Predicate[] sp2 = {t1, t2, t3, t4};
            
            FactList f = createFactList("Facts:");
            int[] aNone = {};
            assertTrue(check(f, sp1, aNone));
            assertTrue(check(f, sp2, aNone));

            f = createFactList("Facts: A('Id1').");
            int[] a1 = {1};
            assertTrue(check(f, sp1, a1));
            assertTrue(check(f, sp2, aNone));

            f = createFactList("Facts: B('Id1').");
            assertTrue(check(f, sp1, aNone));
            assertTrue(check(f, sp2, aNone));

            f = createFactList("Facts: B('Id1', 'Id2').");
            int[] a5 = {5};
            assertTrue(check(f, sp1, a5));
            assertTrue(check(f, sp2, aNone));

            f = createFactList("Facts: B('Id2', 'Id3').");
            int[] a8 = {8};
            assertTrue(check(f, sp1, a8));
            assertTrue(check(f, sp2, aNone));

            f = createFactList("Facts: C('Id1', 'Id2').");
            assertTrue(check(f, sp1, aNone));
            assertTrue(check(f, sp2, aNone));

            f = createFactList("Facts: C('Id1', 'Id2', 'Id3').");
            int[] a6 = {6};
            assertTrue(check(f, sp1, a6));
            assertTrue(check(f, sp2, aNone));

            f = createFactList("Facts: A('Id1', 'Id2', 'Id3').");
            assertTrue(check(f, sp1, aNone));
            assertTrue(check(f, sp2, aNone));

            f = createFactList("Facts: A('Id1').A('Id2').");
            int[] a1_2 = {1,2};
            assertTrue(check(f, sp1, a1_2));
            assertTrue(check(f, sp2, aNone));

            f = createFactList("Facts: A('Id1').A('Id4').");
            assertTrue(check(f, sp1, a1));
            assertTrue(check(f, sp2, aNone));

            f = createFactList("Facts: A('Id4').A('Id2').");
            int[] a2 = {2};
            assertTrue(check(f, sp1, a2));
            assertTrue(check(f, sp2, aNone));

            f = createFactList("Facts: A('Id4').A('Id5').");
            assertTrue(check(f, sp1, aNone));
            assertTrue(check(f, sp2, aNone));

            f = createFactList("Facts: A('Id1').A('Id2').A('Id3').");
            assertTrue(check(f, sp1, a1_2));
            assertTrue(check(f, sp2, aNone));

            f = createFactList("Facts: B('Id1','Id2').B('Id2', 'Id3').");
            int[] a5_8 = {5, 8};
            assertTrue(check(f, sp1, a5_8));
            assertTrue(check(f, sp2, aNone));

            f = createFactList("Facts: B('Id1','Id2').B('Id1', 'Id3').");
            assertTrue(check(f, sp1, a5));
            assertTrue(check(f, sp2, aNone));

            f = createFactList("Facts: B('Id1','Id4').B('Id2', 'Id3').");
            assertTrue(check(f, sp1, a8));
            assertTrue(check(f, sp2, aNone));

            f = createFactList("Facts: B('Id1','Id4').B('Id1', 'Id3').");
            assertTrue(check(f, sp1, aNone));
            assertTrue(check(f, sp2, aNone));

            f = createFactList("Facts: B('Id1','Id2')."+
                                   "B('Id2','Id3')."+
                                   "B('I1', 'Id3').");
            assertTrue(check(f, sp1, a5_8));
            assertTrue(check(f, sp2, aNone));

            f = createFactList("Facts: B('Id1','Id2')."+
                                   "B('I1', 'Id3')."+
                                   "B('Id2','Id3').");
            assertTrue(check(f, sp1, a5_8));
            assertTrue(check(f, sp2, aNone));

            f = createFactList("Facts: B('Id1','Id3')."+
                                   "B('Id1', 'Id2')."+
                                   "B('Id2','Id3').");
            assertTrue(check(f, sp1, a5_8));
            assertTrue(check(f, sp2, aNone));

            f = createFactList("Facts: B('Id1','Id3')."+
                                   "B('Id2', 'Id3')."+
                                   "B('Id1','Id2').");
            assertTrue(check(f, sp1, a5_8));
            assertTrue(check(f, sp2, aNone));

            f = createFactList("Facts: C('Id2','Id3','Id1').");
            int[] a9 = {9};
            assertTrue(check(f, sp1, a9));
            assertTrue(check(f, sp2, aNone));

            f = createFactList("Facts: C('Id2','Id3','Id1')." +
                                   "C('Id1','Id2','Id3')."+
                                   "C('Id1','Id3','Id3').");
            int[] a6_9 = {6,9};
            assertTrue(check(f, sp1, a6_9));
            assertTrue(check(f, sp2, aNone));

            f = createFactList("Facts: C('Id2','Id3','Id1')." +
                                   "C('Id1','Id3','Id3')."+
                                   "C('Id1','Id2','Id3').");
            assertTrue(check(f, sp1, a6_9));
            assertTrue(check(f, sp2, aNone));

            f = createFactList("Facts: C('Id1','Id3','Id3')." +
                                   "C('Id2','Id3','Id1')."+
                                   "C('Id1','Id2','Id3').");
            assertTrue(check(f, sp1, a6_9));
            assertTrue(check(f, sp2, aNone));

            f = createFactList("Facts: C('Id1','Id4','Id3')." +
                                   "C('Id2','Id4','Id1')."+
                                   "C('Id1','Id2','Id3').");
            assertTrue(check(f, sp1, a6));
            assertTrue(check(f, sp2, aNone));

            f = createFactList("Facts: C('Id1','Id4','Id3')." +
                                   "C('Id1','Id2','Id3')." +
                                   "C('Id2','Id4','Id1').");

            f = createFactList("Facts: C('Id1','Id2','Id3')." +
                                   "C('Id1','Id4','Id3')." +
                                   "C('Id2','Id4','Id1').");
            System.out.println( "PASSED" );
        }catch(ParserException e){
             System.out.println(
                 "ERROR: in FactListTest::testCanProve()\n" +
                 e.getMessage());
        };
    }

    ///////////////////
    static boolean debug = false;
    static String eoln = System.getProperty("line.separator");


    private boolean check(FactList f, Predicate[] sa, int[] whichIsTrue){
        boolean result = true;
        int index = 0;
        for(int i = 0; i < sa.length && result; i++){
            result = f.canProve(sa[i]); 
            if(index < whichIsTrue.length && i == whichIsTrue[index]){
                index++;
            } else {
                result = !result;
            };
        };
        return result;
    };

    private FactList createFactList(String s)
        throws ParserException
    {
        StringReader sr = new StringReader(s);
        Lex lex = new Lex(sr);
        return new FactList(lex);
    }

    private Predicate createPredicate(String s)
        throws ParserException
    {
        StringReader sr = new StringReader(s);
        Lex lex = new Lex(sr);
        return new Predicate(lex);
    }

    protected int count(Iterator<Object> i){
        int result = 0;
        while(i.hasNext()){
            result++;
            i.next();
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
     * Usage: java FactListTest
     */
    public static void main (String[] args)
    {
        /* junit.textui.TestRunner will write the test results to stdout. */
        junit.textui.TestRunner.run (FactListTest.class);

        /* junit.swingui.TestRunner will display the test results in JUnit's
           swing interface. */
        //junit.swingui.TestRunner.run (FactListTest.class);
    }
}
