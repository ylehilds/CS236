package testparser;

import junit.framework.TestCase;

import java.io.StringReader;
import java.util.ArrayList;
import project3.lex.Argument;
import project3.lex.Constant;
import project3.lex.Lex;
import project3.lex.Token;
import project3.lex.Identifier;
import project3.lex.TokenType;
import project3.three.Node;
import project3.three.ParserException;
import project3.three.NamedList;

/**
 * NamedListTest is a JUnit test for class NamedList
 */
public class NamedListTest
    extends TestCase
{
    /**
     * Constructs a test case for the test specified in the name argument.
     */
    public NamedListTest (String name)
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
     * Test default constructor.
     */
    public void testDefaultConstructor(){
        ExtendedNamedList1 e = new ExtendedNamedList1();
        assertTrue(e.getName() == null);
        assertTrue(e.getNodes().size() == 0);
    };

    /**
     * Test createNamedList, getNodes, toString, for default methods.
     * Default methods assume a namedList as one or more elements,
     * each element is an Identifier, and there are no separator.
     */
    public void testCreateNamedList1(){
        try{
            try{
                StringReader sr = new StringReader("");
                Lex lex = new Lex(sr);
                ExtendedNamedList1 namedList = new ExtendedNamedList1(lex);
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            try{
                StringReader sr = new StringReader("==(A,B,C");
                Lex lex = new Lex(sr);
                ExtendedNamedList1 namedList = new ExtendedNamedList1(lex);
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            try{
                StringReader sr = new StringReader("A");
                Lex lex = new Lex(sr);
                ExtendedNamedList1 namedList = new ExtendedNamedList1(lex);
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            try{
                StringReader sr = new StringReader("A(");
                Lex lex = new Lex(sr);
                ExtendedNamedList1 namedList = new ExtendedNamedList1(lex);
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            try{
                StringReader sr = new StringReader("A()");
                Lex lex = new Lex(sr);
                ExtendedNamedList1 namedList =
                    new ExtendedNamedList1(lex);
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            try{
                StringReader sr = new StringReader("A(A B)");
                Lex lex = new Lex(sr);
                ExtendedNamedList1 namedList =
                    new ExtendedNamedList1(lex);
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            try{
                StringReader sr = new StringReader("A(X,)");
                Lex lex = new Lex(sr);
                ExtendedNamedList1 namedList =
                    new ExtendedNamedList1(lex);
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            StringReader sr = new StringReader("Apple(Id1)");
            Lex lex = new Lex(sr);
            ExtendedNamedList1 namedList =
                new ExtendedNamedList1(lex);
            ArrayList<Node> nodes = namedList.getNodes();
            assertTrue(nodes.get(0) instanceof Identifier);
            assertTrue(((Token)nodes.get(0)).getValue().equals("Id1"));
            assertTrue(namedList.getName().getValue().equals("Apple"));
            assertTrue(namedList.toString().equals("Apple(Id1)"));

            sr = new StringReader("M(Id1,\n Id2)");
            lex = new Lex(sr);
            namedList =
                new ExtendedNamedList1(lex);
            nodes = namedList.getNodes();
            assertTrue(nodes.size() == 2);
            assertTrue(nodes.get(0) instanceof Identifier);
            assertTrue(nodes.get(1) instanceof Identifier);
            assertTrue(((Token)nodes.get(0)).getValue().equals("Id1"));
            assertTrue(((Token)nodes.get(1)).getValue().equals("Id2"));
            assertTrue(namedList.getName().getValue().equals("M"));
            assertTrue(namedList.toString().equals("M(Id1,Id2)"));

            sr = new StringReader("List(Name1,\n Name2,\n Name3)");
            lex = new Lex(sr);
            namedList =
                new ExtendedNamedList1(lex);
            nodes = namedList.getNodes();
            assertTrue(nodes.size() == 3);
            assertTrue(nodes.get(0) instanceof Identifier);
            assertTrue(nodes.get(1) instanceof Identifier);
            assertTrue(nodes.get(2) instanceof Identifier);
            assertTrue(((Token)nodes.get(0)).getValue().equals("Name1"));
            assertTrue(((Token)nodes.get(1)).getValue().equals("Name2"));
            assertTrue(((Token)nodes.get(2)).getValue().equals("Name3"));
            assertTrue(namedList.getName().getValue().equals("List"));
            assertTrue(namedList.toString().equals("List(Name1,Name2,Name3)"));
        }catch(ParserException e){
            System.out.println(
                "ERROR in NamedListTest.testCreateNamedList1\n" +
                "    should not get here.\n" +
                "    error = " + e.getMessage());
        };
    }

    /**
     * Test createNamedList, getNodes, toString
     * Default methods are overridden so that a namedList has zero or more
     * elements, each element is an Argument, and the comma is separator. Also
     * test the copy constructor.
     */
    public void testCreateNamedList2(){
        try{
            //An Empty NamedList
            StringReader sr = new StringReader("Course()");
            Lex lex = new Lex(sr);
            ExtendedNamedList2 namedList =
                new ExtendedNamedList2(lex);
            assertTrue(namedList.getNodes().size() == 0);
            assertTrue(namedList.getName().getValue().equals("Course"));
            assertTrue(namedList.toString().equals("Course()"));

            //namedList of size one
            sr = new StringReader("Day('abc')");
            lex = new Lex(sr);
            namedList = new ExtendedNamedList2(lex);
            ArrayList<Node> nodes = namedList.getNodes();
            assertTrue(nodes.size() == 1);
            assertTrue(nodes.get(0) instanceof Argument);
            assertTrue(((Token)nodes.get(0)).getValue().equals("'abc'"));
            assertTrue(namedList.getName().getValue().equals("Day"));
            assertTrue(namedList.toString().equals("Day('abc')"));

            sr = new StringReader("XYZ(Name1, 'abc',\n Name3)");
            lex = new Lex(sr);
            namedList =
                new ExtendedNamedList2(lex);
            nodes = namedList.getNodes();
            assertTrue(nodes.size() == 3);
            assertTrue(nodes.get(0) instanceof Identifier);
            assertTrue(nodes.get(1) instanceof Constant);
            assertTrue(nodes.get(2) instanceof Identifier);
            assertTrue(((Token)nodes.get(0)).getValue().equals("Name1"));
            assertTrue(((Token)nodes.get(1)).getValue().equals("'abc'"));
            assertTrue(((Token)nodes.get(2)).getValue().equals("Name3"));
            assertTrue(namedList.getName().getValue().equals("XYZ"));
            assertTrue(namedList.toString().equals("XYZ(Name1,'abc',Name3)"));
            
        }catch(ParserException e){
            System.out.println("ERROR in NamedListTest.testCreateNamedList2\n" +
                               "    should not get here.\n" +
                               "    error = " + e.getMessage());
        };
    }

    /**
     * Test the copy constructor and the equals operator.
     */
    public void testCopyConstructorAndEquals(){
        try{
            StringReader sr = new StringReader("Course()");
            Lex lex = new Lex(sr);
            ExtendedNamedList2 namedList = new ExtendedNamedList2(lex);
            ExtendedNamedList2 namedList2 = new ExtendedNamedList2(namedList);
            assertTrue(namedList.equals(namedList2));
            assertFalse(namedList == namedList2);

            sr = new StringReader("Day('abc')");
            lex = new Lex(sr);
            namedList = new ExtendedNamedList2(lex);
            namedList2 = new ExtendedNamedList2(namedList);
            assertTrue(namedList.equals(namedList2));
            assertFalse(namedList == namedList2);

            sr = new StringReader("XYZ(Name1, 'abc',\n Name3)");
            lex = new Lex(sr);
            namedList = new ExtendedNamedList2(lex);
            namedList2 = new ExtendedNamedList2(namedList);
            assertTrue(namedList.equals(namedList2));
            assertFalse(namedList == namedList2);
            
        }catch(ParserException e){
            System.out.println("ERROR in NamedListTest.testCreateNamedList2\n" +
                               "    should not get here.\n" +
                               "    error = " + e.getMessage());
        };
    }

    /**
     * Test getName.
     */
    public void testGetName(){
        try{
            StringReader sr = new StringReader("Course()");
            Lex lex = new Lex(sr);
            ExtendedNamedList2 namedList = new ExtendedNamedList2(lex);
            assertTrue(namedList.getName().getValue().equals("Course"));

            sr = new StringReader("Day('abc')");
            lex = new Lex(sr);
            namedList = new ExtendedNamedList2(lex);
            assertTrue(namedList.getName().getValue().equals("Day"));

            sr = new StringReader("XYZ(Name1, 'abc',\n Name3)");
            lex = new Lex(sr);
            namedList = new ExtendedNamedList2(lex);
            assertTrue(namedList.getName().getValue().equals("XYZ"));
            
        }catch(ParserException e){
            System.out.println("ERROR in NamedListTest.testCreateNamedList2\n" +
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

    private class ExtendedNamedList1 extends NamedList{
        public ExtendedNamedList1(){
            super();
        }

        public ExtendedNamedList1(Lex lex)
            throws ParserException
        {
            super(lex);
        }

        public Node createNode(Lex lex) throws ParserException
        {
            Node result = lex.front();
            if(!(result instanceof Identifier))throw new ParserException("");
            lex.popFront();
            return result;
        };

        public Identifier getName(){
            return name;
        };
    };

    private class ExtendedNamedList2 extends NamedList{
        public ExtendedNamedList2(Lex lex)
            throws ParserException
        {
            super(lex);
        }

        public ExtendedNamedList2(ExtendedNamedList2 namedList)
            throws ParserException
        {
            super(namedList);
        }

        public Node createNode(Lex lex) throws ParserException
        {
            Node result = lex.front();
            if(!(result instanceof Argument))throw new ParserException("");
            lex.popFront();
            return result;
        }

        public Identifier getName(){
            return name;
        };

        protected boolean isInFirstOfListElement(Lex lex){
            return lex.front() instanceof Argument;
        }

        protected LengthConstraint getLengthConstraint(){
            return LengthConstraint.ZERO_OR_MORE;
        }
    }

   /*
     * Uncomment this variable declaration and add any necessary initialization
     * arguments for it in the setUp() method.
     */
     private ExtendedNamedList1 x; 
     private ExtendedNamedList2 y; 

    /**
     * Utility main method. This will the test cases defined in this class.
     *
     * Usage: java NamedListTest
     */
    public static void main (String[] args)
    {
        /* junit.textui.TestRunner will write the test results to stdout. */
        junit.textui.TestRunner.run (NamedListTest.class);

        /* junit.swingui.TestRunner will display the test results in JUnit's
           swing interface. */
        //junit.swingui.TestRunner.run (NamedListTest.class);
    }
}
