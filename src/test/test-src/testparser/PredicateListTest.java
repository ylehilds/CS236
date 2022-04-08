package testparser;

import junit.framework.TestCase;

import java.io.StringReader;
import java.util.ArrayList;
import project3.lex.Lex;
import project3.three.Node;
import project3.three.ParserException;
import project3.three.Predicate;

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
     * Test DefaultConstructor(lex)
     */
    public void testDefaultConstructor(){
        ExtendedPredicateList parserPredicateList =
            new ExtendedPredicateList();
        assertTrue(parserPredicateList.getNodes().size() == 0);
    }

    /**
     * Test PredicateList(lex)
     */
    public void testPredicateListConstructor(){
        try{
            try{
                createPPList("");
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            try{
                createPPList("X(A,B,C),");
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            PredicateList parserPredicateList = createPPList(
                "Apple(Id1)");
            ArrayList<Node> nodes = parserPredicateList.getNodes();
            assertTrue(nodes.size() == 1);
            assertTrue(nodes.get(0) instanceof Predicate);
            assertTrue(parserPredicateList.toString().equals("Apple(Id1)"));

            parserPredicateList = createPPList("Apple('Id1'), M(X,Y,'Z')");
            nodes = parserPredicateList.getNodes();
            assertTrue(nodes.size() == 2);
            assertTrue(nodes.get(0) instanceof Predicate);
            assertTrue(nodes.get(1) instanceof Predicate);
            assertTrue(parserPredicateList.toString().equals(
                "Apple('Id1'),M(X,Y,'Z')"));

            parserPredicateList = createPPList(
                "M(Id1,Id2),\nM('Id1'),\nM('Id2')");
            nodes = parserPredicateList.getNodes();
            assertTrue(nodes.size() == 3);
            assertTrue(nodes.get(0) instanceof Predicate);
            assertTrue(nodes.get(1) instanceof Predicate);
            assertTrue(nodes.get(2) instanceof Predicate);
            assertTrue(parserPredicateList.toString().equals(
                "M(Id1,Id2),M('Id1'),M('Id2')"));
        }catch(ParserException e){
            System.out.println(
                "ERROR in PredicateListTest." +
                "testPredicateListConstructor\n"+
                "    should not get here.\n" +
                "    error = " + e.getMessage());
        };
    }

    /**
     * Test copy constructor and equals.
     */
    public void testCopyConstructor(){
        try{
            ExtendedPredicateList parserPredicateList =
                createPPList("Apple(Id1)");
            assertTrue(parserPredicateList.equals(parserPredicateList));
            ExtendedPredicateList parserPredicateList2 =
                new ExtendedPredicateList(parserPredicateList);
            assertTrue(parserPredicateList.equals(parserPredicateList2));
            assertFalse(parserPredicateList.equals(null));
            assertFalse(parserPredicateList.equals(new Integer(1)));
            parserPredicateList2 = createPPList("Apple(Id2)");
            assertFalse(parserPredicateList.equals(parserPredicateList2));
            assertFalse(parserPredicateList2.equals(parserPredicateList));
            parserPredicateList2 = createPPList("Apple('Id1')");
            assertFalse(parserPredicateList.equals(parserPredicateList2));
            assertFalse(parserPredicateList2.equals(parserPredicateList));
            parserPredicateList2 = createPPList("Apfel(Id1)");
            assertFalse(parserPredicateList.equals(parserPredicateList2));
            assertFalse(parserPredicateList2.equals(parserPredicateList));
            parserPredicateList = createPPList("Apfel(Id1)");
            assertTrue(parserPredicateList.equals(parserPredicateList2));
            assertTrue(parserPredicateList2.equals(parserPredicateList));

            parserPredicateList = createPPList("Apple('Id1'), M(X,Y,'Z')");
            assertTrue(parserPredicateList.equals(parserPredicateList));
            parserPredicateList2 =
                new ExtendedPredicateList(parserPredicateList);
            assertTrue(parserPredicateList.equals(parserPredicateList2));
            assertTrue(parserPredicateList2.equals(parserPredicateList));
            parserPredicateList2 = createPPList("Apple('Id2'), M(X,Y,'Z')");
            assertFalse(parserPredicateList.equals(parserPredicateList2));
            assertFalse(parserPredicateList2.equals(parserPredicateList));
            parserPredicateList2 = createPPList("Apple(Id1), M(X,Y,'Z')");
            assertFalse(parserPredicateList.equals(parserPredicateList2));
            assertFalse(parserPredicateList2.equals(parserPredicateList));
            parserPredicateList2 = createPPList("Apple('Id1'), M(Y,Y,'Z')");
            assertFalse(parserPredicateList.equals(parserPredicateList2));
            assertFalse(parserPredicateList2.equals(parserPredicateList));
            parserPredicateList2 = createPPList("Apple('Id1'), M(X,A,'Z')");
            assertFalse(parserPredicateList.equals(parserPredicateList2));
            assertFalse(parserPredicateList2.equals(parserPredicateList));
            parserPredicateList2 = createPPList("Apple('Id1'), M(X,Y,Z)");
            assertFalse(parserPredicateList.equals(parserPredicateList2));
            assertFalse(parserPredicateList2.equals(parserPredicateList));
            parserPredicateList2 = createPPList("Apple('Id1'), N(X,Y,'Z')");
            assertFalse(parserPredicateList.equals(parserPredicateList2));
            assertFalse(parserPredicateList2.equals(parserPredicateList));

            parserPredicateList = createPPList(
                "M(Id1,Id2),\nM('Id1'),\nM('Id2')");
            assertTrue(parserPredicateList.equals(parserPredicateList));
            parserPredicateList2 =
                new ExtendedPredicateList(parserPredicateList);
            assertTrue(parserPredicateList.equals(parserPredicateList2));
            assertTrue(parserPredicateList2.equals(parserPredicateList));
            parserPredicateList2 = createPPList(
                "M(Id1,Id1),\nM('Id1'),\nM('Id2')");
            assertFalse(parserPredicateList.equals(parserPredicateList2));
            assertFalse(parserPredicateList2.equals(parserPredicateList));
            parserPredicateList2 = createPPList(
                "M(Id1,Id2),\nM(Id1),\nM('Id2')");
            assertFalse(parserPredicateList.equals(parserPredicateList2));
            assertFalse(parserPredicateList2.equals(parserPredicateList));
            parserPredicateList2 = createPPList(
                "M(Id1,Id2),\nM('Id1'),\nN('Id2')");
            assertFalse(parserPredicateList.equals(parserPredicateList2));
            assertFalse(parserPredicateList2.equals(parserPredicateList));
            parserPredicateList2 = createPPList(
                "M(Id1,Id2),\nM('Id1'),\nM('Id2', Id3)");
            assertFalse(parserPredicateList.equals(parserPredicateList2));
            assertFalse(parserPredicateList2.equals(parserPredicateList));
        }catch(ParserException e){
            System.out.println(
                "ERROR in PredicateListTest.testCopyConstructor\n"+
                "    should not get here.\n" +
                "    error = " + e.getMessage());
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

    private class ExtendedPredicateList extends PredicateList{
        public ExtendedPredicateList(){ super(); };

        public ExtendedPredicateList(Lex lex)
            throws ParserException
        {
            super(lex);
        }

        public ExtendedPredicateList(
            ExtendedPredicateList parserPredicateList)
            throws ParserException
        {
            super(parserPredicateList);
        }

        public Predicate get(int i){
            return (Predicate)nodes.get(i);
        };

    }

    private ExtendedPredicateList createPPList(String str)
        throws ParserException
    {
        StringReader sr = new StringReader(str);
        Lex lex = new Lex(sr);
        ExtendedPredicateList result =
            new ExtendedPredicateList(lex);
        return result;
    };

   /*
     * Uncomment this variable declaration and add any necessary initialization
     * arguments for it in the setUp() method.
     */
     private ExtendedPredicateList x;

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
