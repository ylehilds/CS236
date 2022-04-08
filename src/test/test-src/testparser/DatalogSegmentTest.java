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
import project3.three.DatalogSegment;

/**
 * DatalogSegmentTest is a JUnit test for class DatalogSegment
 */
public class DatalogSegmentTest
    extends TestCase
{
    /**
     * Constructs a test case for the test specified in the name argument.
     */
    public DatalogSegmentTest (String name)
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
     * Test createDatalogSegment, getNodes, toString, for default methods.
     * Default methods assume a datalogSegment as one or more elements,
     * each element is an Identifier, and there are no separator.
     */
    public void testCreateDatalogSegment1(){
        try{
            try{
                StringReader sr = new StringReader("");
                Lex lex = new Lex(sr);
                ExtendedDatalogSegment1 datalogSegment =
                    new ExtendedDatalogSegment1(lex, TokenType.SCHEMES);
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            try{
                StringReader sr = new StringReader("Schemes");
                Lex lex = new Lex(sr);
                ExtendedDatalogSegment1 datalogSegment =
                    new ExtendedDatalogSegment1(lex, TokenType.SCHEMES);
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            try{
                StringReader sr = new StringReader("Facts :");
                Lex lex = new Lex(sr);
                ExtendedDatalogSegment1 datalogSegment =
                    new ExtendedDatalogSegment1(lex, TokenType.FACTS);
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            StringReader sr = new StringReader("FACTS : Id1");
            Lex lex = new Lex(sr);
            ExtendedDatalogSegment1 datalogSegment =
                new ExtendedDatalogSegment1(lex, TokenType.FACTS);
            assertFalse(datalogSegment.getName().equals("Facts"));
            assertTrue(datalogSegment.toString().equals("FACTS:" + eoln +
                "Id1"));

            sr = new StringReader("Schemes : Name");
            lex = new Lex(sr);
            datalogSegment =
                new ExtendedDatalogSegment1(lex, TokenType.SCHEMES);
            ArrayList<Node> nodes = datalogSegment.getNodes();
            assertTrue(nodes.size() == 1);
            assertTrue(nodes.get(0) instanceof Identifier);
            assertTrue(((Token)nodes.get(0)).getValue().equals("Name"));
            assertTrue(datalogSegment.getName().equals("Schemes"));
            assertTrue(datalogSegment.toString().equals(
                "Schemes:" + eoln + "Name"));

            sr = new StringReader("Rules: Name,");
            lex = new Lex(sr);
            datalogSegment = new ExtendedDatalogSegment1(lex, TokenType.RULES);
            nodes = datalogSegment.getNodes();
            assertTrue(nodes.size() == 1);
            assertTrue(nodes.get(0) instanceof Identifier);
            assertTrue(((Token)nodes.get(0)).getValue().equals("Name"));
            assertTrue(datalogSegment.getName().equals("Rules"));
            assertTrue(datalogSegment.toString().equals(
                "Rules:" + eoln + "Name"));

            sr = new StringReader("QUERIES: Name1 Name2 Name3");
            lex = new Lex(sr);
            datalogSegment =
                new ExtendedDatalogSegment1(lex, TokenType.QUERIES);
            nodes = datalogSegment.getNodes();
            assertTrue(nodes.size() == 3);
            assertTrue(nodes.get(0) instanceof Identifier);
            assertTrue(nodes.get(1) instanceof Identifier);
            assertTrue(nodes.get(2) instanceof Identifier);
            assertTrue(((Token)nodes.get(0)).getValue().equals("Name1"));
            assertTrue(((Token)nodes.get(1)).getValue().equals("Name2"));
            assertTrue(((Token)nodes.get(2)).getValue().equals("Name3"));
            assertTrue(datalogSegment.getName().equals("QUERIES"));
            assertTrue(datalogSegment.toString().equals(
              "QUERIES:" + eoln + "Name1Name2Name3"));

            sr = new StringReader("Facts: Name1 Name2 Name3)");
            lex = new Lex(sr);
            datalogSegment = new ExtendedDatalogSegment1(lex, TokenType.FACTS);
            nodes = datalogSegment.getNodes();
            assertTrue(nodes.size() == 3);
            assertTrue(nodes.get(0) instanceof Identifier);
            assertTrue(nodes.get(1) instanceof Identifier);
            assertTrue(nodes.get(2) instanceof Identifier);
            assertTrue(((Token)nodes.get(0)).getValue().equals("Name1"));
            assertTrue(((Token)nodes.get(1)).getValue().equals("Name2"));
            assertTrue(((Token)nodes.get(2)).getValue().equals("Name3"));
            assertTrue(datalogSegment.getName().equals("Facts"));
            assertTrue(datalogSegment.toString().equals(
              "Facts:" + eoln + "Name1Name2Name3"));
        }catch(ParserException e){
            System.out.println(
                "ERROR in DatalogSegmentTest.testCreateDatalogSegment1\n" +
                "    should not get here.\n" +
                "    error = " + e.getMessage());
        };
    }

    /**
     * Test createDatalogSegment, getNodes, toString
     * Default methods are overridden so that a datalogSegment has zero or more
     * elements, each element is an Argument, and the comma is separator. Also
     * test the copy constructor.
     */
    public void testCreateDatalogSegment2(){
        try{
            //An Empty DatalogSegment
            StringReader sr = new StringReader("Schemes:");
            Lex lex = new Lex(sr);
            ExtendedDatalogSegment2 datalogSegment =
                new ExtendedDatalogSegment2(lex, TokenType.SCHEMES);
            assertTrue(datalogSegment.getNodes().size() == 0);
            assertTrue(datalogSegment.getName().equals("Schemes"));
            assertTrue(datalogSegment.toString().equals("Schemes:" + eoln));

            //datalogSegment of size one
            sr = new StringReader("Facts : 'abc'");
            lex = new Lex(sr);
            datalogSegment = new ExtendedDatalogSegment2(lex, TokenType.FACTS);
            ArrayList<Node> nodes = datalogSegment.getNodes();
            assertTrue(nodes.size() == 1);
            assertTrue(nodes.get(0) instanceof Argument);
            assertTrue(((Token)nodes.get(0)).getValue().equals("'abc'"));
            assertTrue(datalogSegment.getName().equals("Facts"));
            assertTrue(datalogSegment.toString().equals(
            "Facts:" + eoln + "'abc'"));

            //No comma is separating them
            sr = new StringReader("Rules: 'abc' 'xyz'");
            lex = new Lex(sr);
            datalogSegment = new ExtendedDatalogSegment2(lex, TokenType.RULES);
            nodes = datalogSegment.getNodes();
            assertTrue(nodes.size() == 1);
            assertTrue(nodes.get(0) instanceof Node);
            assertTrue(((Token)nodes.get(0)).getValue().equals("'abc'"));
            assertTrue(datalogSegment.getName().equals("Rules"));
            assertTrue(datalogSegment.toString().equals(
            "Rules:" + eoln + "'abc'"));

            sr = new StringReader(":-: Name1, 'abc',\n Name3");
            lex = new Lex(sr);
            datalogSegment =
                new ExtendedDatalogSegment2(lex, TokenType.COLON_DASH);
            nodes = datalogSegment.getNodes();
            assertTrue(nodes.size() == 3);
            assertTrue(nodes.get(0) instanceof Identifier);
            assertTrue(nodes.get(1) instanceof Constant);
            assertTrue(nodes.get(2) instanceof Identifier);
            assertTrue(((Token)nodes.get(0)).getValue().equals("Name1"));
            assertTrue(((Token)nodes.get(1)).getValue().equals("'abc'"));
            assertTrue(((Token)nodes.get(2)).getValue().equals("Name3"));
            assertTrue(datalogSegment.getName().equals(":-"));
            assertTrue(datalogSegment.toString().equals(
              ":-:" + eoln + "Name1,'abc',Name3"));
            
        }catch(ParserException e){
            System.out.println("ERROR in DatalogSegmentTest.testCreateDatalogSegment2\n" +
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

    private class ExtendedDatalogSegment1 extends DatalogSegment{
        public ExtendedDatalogSegment1(Lex lex, TokenType tokenType)
            throws ParserException
        {
            super(lex, tokenType);
        }

        public Node createNode(Lex lex) throws ParserException
        {
            Node result = lex.front();
            if(!(result instanceof Identifier))throw new ParserException("");
            lex.popFront();
            return result;
        };

        public String getName(){
            return name;
        };
    };

    private class ExtendedDatalogSegment2 extends DatalogSegment{
        public ExtendedDatalogSegment2(Lex lex, TokenType tokenType)
            throws ParserException
        {
            super(lex, tokenType);
        }

        public Node createNode(Lex lex) throws ParserException
        {
            Node result = lex.front();
            if(!(result instanceof Argument))throw new ParserException("");
            lex.popFront();
            return result;
        }

        public String getName(){
            return name;
        };

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
     private ExtendedDatalogSegment1 x; 
     private ExtendedDatalogSegment2 y; 

    /**
     * Utility main method. This will the test cases defined in this class.
     *
     * Usage: java DatalogSegmentTest
     */
    public static void main (String[] args)
    {
        /* junit.textui.TestRunner will write the test results to stdout. */
        junit.textui.TestRunner.run (DatalogSegmentTest.class);

        /* junit.swingui.TestRunner will display the test results in JUnit's
           swing interface. */
        //junit.swingui.TestRunner.run (DatalogSegmentTest.class);
    }
}
