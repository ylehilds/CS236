package testparser;

import junit.framework.TestCase;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import project3.lex.Argument;
import project3.lex.Constant;
import project3.lex.Lex;
import project3.lex.Token;
import project3.lex.Identifier;
import project3.lex.TokenType;
import project3.three.Node;
import project3.three.ParserException;
import project3.three.List;

/**
 * ListTest is a JUnit test for class List
 */
public class ListTest
    extends TestCase
{
    /**
     * Constructs a test case for the test specified in the name argument.
     */
    public ListTest (String name)
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
     * Test the default constructor. 
     */
    public void testDefaultConstructor()
    {
        x = new ExtendedList1();
        assertTrue(x.getNodes().size() == 0);
    }

    /**
     * Test createList, getNodes, toString, for default methods.
     * Default methods assume a list as one or more elements, each element
     * is an Identifier, and there are no separator.
     */
    public void testCreateList1(){
        try{
            //With the given assumptions, parsing an empty list should fail.
            try{
                StringReader sr = new StringReader("");
                Lex lex = new Lex(sr);
                ExtendedList1 list = new ExtendedList1();
                list.createList(lex);
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            StringReader sr = new StringReader("Name");
            Lex lex = new Lex(sr);
            ExtendedList1 list = new ExtendedList1();
            list.createList(lex);
            ArrayList<Node> nodes = list.getNodes();
            assertTrue(nodes.size() == 1);
            assertTrue(nodes.get(0) instanceof Identifier);
            assertTrue(((Token)nodes.get(0)).getValue().equals("Name"));
            assertTrue(list.toString().equals("Name"));

            sr = new StringReader("Name,");
            lex = new Lex(sr);
            list = new ExtendedList1();
            list.createList(lex);
            nodes = list.getNodes();
            assertTrue(nodes.size() == 1);
            assertTrue(nodes.get(0) instanceof Identifier);
            assertTrue(((Token)nodes.get(0)).getValue().equals("Name"));
            assertTrue(list.toString().equals("Name"));

            sr = new StringReader("Name1 Name2 Name3");
            lex = new Lex(sr);
            list = new ExtendedList1();
            list.createList(lex);
            nodes = list.getNodes();
            assertTrue(nodes.size() == 3);
            assertTrue(nodes.get(0) instanceof Identifier);
            assertTrue(nodes.get(1) instanceof Identifier);
            assertTrue(nodes.get(2) instanceof Identifier);
            assertTrue(((Token)nodes.get(0)).getValue().equals("Name1"));
            assertTrue(((Token)nodes.get(1)).getValue().equals("Name2"));
            assertTrue(((Token)nodes.get(2)).getValue().equals("Name3"));
            assertTrue(list.toString().equals("Name1Name2Name3"));

            sr = new StringReader("Name1 Name2 Name3)");
            lex = new Lex(sr);
            list = new ExtendedList1();
            list.createList(lex);
            nodes = list.getNodes();
            assertTrue(nodes.size() == 3);
            assertTrue(nodes.get(0) instanceof Identifier);
            assertTrue(nodes.get(1) instanceof Identifier);
            assertTrue(nodes.get(2) instanceof Identifier);
            assertTrue(((Token)nodes.get(0)).getValue().equals("Name1"));
            assertTrue(((Token)nodes.get(1)).getValue().equals("Name2"));
            assertTrue(((Token)nodes.get(2)).getValue().equals("Name3"));
            assertTrue(list.toString().equals("Name1Name2Name3"));
        }catch(ParserException e){
            System.out.println("ERROR in ListTest.testCreateList1\n" +
                               "    should not get here.\n" +
                               "    error = " + e.getMessage());
        };
    }

    /**
     * Test createList, getNodes, toString
     * Default methods are overridden so that a list has zero or more elements,
     * each element is an Argument, and the comma is separator. Also test the
     * copy constructor.
     */
    public void testCreateList2(){
        try{
            //An Empty List
            StringReader sr = new StringReader("");
            Lex lex = new Lex(sr);
            ExtendedList2 list = new ExtendedList2();
            list.createList(lex);
            assertTrue(list.getNodes().size() == 0);
            assertTrue(list.size() == 0);
            ExtendedList2 list2 = new ExtendedList2(list);
            assertTrue(list2.getNodes().size() == 0);
            Iterator iter = list.iterator();
            assertFalse(iter.hasNext());
            

            //list of size one
            sr = new StringReader("'abc'");
            lex = new Lex(sr);
            list = new ExtendedList2();
            list.createList(lex);
            ArrayList<Node> nodes = list.getNodes();
            assertTrue(nodes.size() == 1);
            assertTrue(list.size() == 1);
            assertTrue(nodes.get(0) instanceof Argument);
            assertTrue(((Token)nodes.get(0)).getValue().equals("'abc'"));
            assertTrue(list.toString().equals("'abc'"));
            list2 = new ExtendedList2(list);
            nodes = list2.getNodes();
            assertTrue(nodes.size() == 1);
            assertTrue(list2.size() == 1);
            assertTrue(nodes.get(0) instanceof Argument);
            assertTrue(((Token)nodes.get(0)).getValue().equals("'abc'"));
            assertTrue(list2.toString().equals("'abc'"));
            iter = list.iterator();
            assertTrue(iter.hasNext());
            iter.next();
            assertFalse(iter.hasNext());

            //No comma is separating them
            sr = new StringReader("'abc' 'xyz'");
            lex = new Lex(sr);
            list = new ExtendedList2();
            list.createList(lex);
            nodes = list.getNodes();
            assertTrue(nodes.size() == 1);
            assertTrue(list.size() == 1);
            assertTrue(nodes.get(0) instanceof Node);
            assertTrue(((Token)nodes.get(0)).getValue().equals("'abc'"));
            assertTrue(list.toString().equals("'abc'"));
            list2 = new ExtendedList2(list);
            nodes = list2.getNodes();
            assertTrue(nodes.size() == 1);
            assertTrue(list2.size() == 1);
            assertTrue(nodes.get(0) instanceof Node);
            assertTrue(((Token)nodes.get(0)).getValue().equals("'abc'"));
            assertTrue(list2.toString().equals("'abc'"));
            iter = list.iterator();
            assertTrue(iter.hasNext());
            iter.next();
            assertFalse(iter.hasNext());

            sr = new StringReader("Name1, 'abc',\n Name3");
            lex = new Lex(sr);
            list = new ExtendedList2();
            list.createList(lex);
            nodes = list.getNodes();
            assertTrue(nodes.size() == 3);
            assertTrue(list.size() == 3);
            assertTrue(nodes.get(0) instanceof Identifier);
            assertTrue(nodes.get(1) instanceof Constant);
            assertTrue(nodes.get(2) instanceof Identifier);
            assertTrue(((Token)nodes.get(0)).getValue().equals("Name1"));
            assertTrue(((Token)nodes.get(1)).getValue().equals("'abc'"));
            assertTrue(((Token)nodes.get(2)).getValue().equals("Name3"));
            assertTrue(list.toString().equals("Name1,'abc',Name3"));
            list2 = new ExtendedList2(list);
            nodes = list2.getNodes();
            assertTrue(nodes.size() == 3);
            assertTrue(list2.size() == 3);
            assertTrue(nodes.get(0) instanceof Identifier);
            assertTrue(nodes.get(1) instanceof Constant);
            assertTrue(nodes.get(2) instanceof Identifier);
            assertTrue(((Token)nodes.get(0)).getValue().equals("Name1"));
            assertTrue(((Token)nodes.get(1)).getValue().equals("'abc'"));
            assertTrue(((Token)nodes.get(2)).getValue().equals("Name3"));
            assertTrue(list2.toString().equals("Name1,'abc',Name3"));
            iter = list.iterator();
            assertTrue(iter.hasNext());
            iter.next();
            assertTrue(iter.hasNext());
            iter.next();
            assertTrue(iter.hasNext());
            iter.next();
            assertFalse(iter.hasNext());
        }catch(ParserException e){
            System.out.println("ERROR in ListTest.testCreateList2\n" +
                               "    should not get here.\n" +
                               "    error = " + e.getMessage());
        };
    }
    ///////////////////
    static boolean debug = false;

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

    private class ExtendedList1 extends List{
        public Node createNode(Lex lex) throws ParserException
        {
            Node result = lex.front();
            if(!(result instanceof Identifier))throw new ParserException("");
            lex.popFront();
            return result;
        };
    };

    private class ExtendedList2 extends List{
        public ExtendedList2(){
            super();
        }


        public ExtendedList2(List list){
            super(list);
        }

        public Node createNode(Lex lex) throws ParserException
        {
            Node result = lex.front();
            if(!(result instanceof Argument))throw new ParserException("");
            lex.popFront();
            return result;
        }

        protected Token getSeparator(){
            return Token.COMMA;
        }

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
     private ExtendedList1 x; 
     private ExtendedList2 y; 

    /**
     * Utility main method. This will the test cases defined in this class.
     *
     * Usage: java ListTest
     */
    public static void main (String[] args)
    {
        /* junit.textui.TestRunner will write the test results to stdout. */
        junit.textui.TestRunner.run (ListTest.class);

        /* junit.swingui.TestRunner will display the test results in JUnit's
           swing interface. */
        //junit.swingui.TestRunner.run (ListTest.class);
    }
}
