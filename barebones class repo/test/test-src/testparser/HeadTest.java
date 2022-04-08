package testparser;

import junit.framework.TestCase;

import java.io.StringReader;
import java.util.ArrayList;
import project3.lex.Lex;
import project3.lex.Token;
import project3.lex.Identifier;
import project3.lex.TokenType;
import project3.three.Node;
import project3.three.ParserException;
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


    /**
     * Test Head(lex)
     */
    public void testHeadConstructor(){
        try{
            try{
                StringReader sr = new StringReader("");
                Lex lex = new Lex(sr);
                ExtendedHead head = new ExtendedHead(lex);
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            try{
                StringReader sr = new StringReader("==(A,B,C");
                Lex lex = new Lex(sr);
                ExtendedHead head = new ExtendedHead(lex);
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            try{
                StringReader sr = new StringReader("A");
                Lex lex = new Lex(sr);
                ExtendedHead head = new ExtendedHead(lex);
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            try{
                StringReader sr = new StringReader("A(");
                Lex lex = new Lex(sr);
                ExtendedHead head = new ExtendedHead(lex);
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            try{
                StringReader sr = new StringReader("A()");
                Lex lex = new Lex(sr);
                ExtendedHead head =
                    new ExtendedHead(lex);
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            try{
                StringReader sr = new StringReader("A('a')");
                Lex lex = new Lex(sr);
                ExtendedHead head =
                    new ExtendedHead(lex);
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };


            try{
                StringReader sr = new StringReader("A(A B)");
                Lex lex = new Lex(sr);
                ExtendedHead head =
                    new ExtendedHead(lex);
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            try{
                StringReader sr = new StringReader("A(X,)");
                Lex lex = new Lex(sr);
                ExtendedHead head =
                    new ExtendedHead(lex);
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            StringReader sr = new StringReader("Apple(Id1)");
            Lex lex = new Lex(sr);
            ExtendedHead head = new ExtendedHead(lex);
            ArrayList<Node> nodes = head.getNodes();
            assertTrue(nodes.get(0) instanceof Identifier);
            assertTrue(((Token)nodes.get(0)).getValue().equals("Id1"));
            assertTrue(head.getName().getValue().equals("Apple"));
            assertTrue(head.toString().equals("Apple(Id1)"));

            sr = new StringReader("M(Id1,\n Id2)");
            lex = new Lex(sr);
            head = new ExtendedHead(lex);
            nodes = head.getNodes();
            assertTrue(nodes.size() == 2);
            assertTrue(nodes.get(0) instanceof Identifier);
            assertTrue(nodes.get(1) instanceof Identifier);
            assertTrue(((Token)nodes.get(0)).getValue().equals("Id1"));
            assertTrue(((Token)nodes.get(1)).getValue().equals("Id2"));
            assertTrue(head.getName().getValue().equals("M"));
            assertTrue(head.toString().equals("M(Id1,Id2)"));

            sr = new StringReader("List(Name1,\n Name2,\n Name3)");
            lex = new Lex(sr);
            head = new ExtendedHead(lex);
            nodes = head.getNodes();
            assertTrue(nodes.size() == 3);
            assertTrue(nodes.get(0) instanceof Identifier);
            assertTrue(nodes.get(1) instanceof Identifier);
            assertTrue(nodes.get(2) instanceof Identifier);
            assertTrue(((Token)nodes.get(0)).getValue().equals("Name1"));
            assertTrue(((Token)nodes.get(1)).getValue().equals("Name2"));
            assertTrue(((Token)nodes.get(2)).getValue().equals("Name3"));
            assertTrue(head.getName().getValue().equals("List"));
            assertTrue(head.toString().equals("List(Name1,Name2,Name3)"));
        }catch(ParserException e){
            System.out.println(
                "ERROR in HeadTest.testHeadConstructor\n" +
                "    should not get here.\n" +
                "    error = " + e.getMessage());
        };
    }

    public void testTheCheckForDuplicates(){
        try{
            StringReader sr = new StringReader("Apple(Id1, Id1)");
            Lex lex = new Lex(sr);
            ExtendedHead head = new ExtendedHead(lex);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        try{
            StringReader sr = new StringReader("Apple(Id1, Id2, Id1)");
            Lex lex = new Lex(sr);
            ExtendedHead head = new ExtendedHead(lex);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        try{
            StringReader sr = new StringReader("Apple(A, B, B)");
            Lex lex = new Lex(sr);
            ExtendedHead head = new ExtendedHead(lex);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        try{
            StringReader sr = new StringReader("Apple(A, A, B)");
            Lex lex = new Lex(sr);
            ExtendedHead head = new ExtendedHead(lex);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
        };

        try{
            StringReader sr = new StringReader(
                "Apple(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, B)");
            Lex lex = new Lex(sr);
            ExtendedHead head = new ExtendedHead(lex);
            assertTrue(false);
        }catch(ParserException e){
            assertTrue(true);
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

    private class ExtendedHead extends Head{
        public ExtendedHead(Lex lex)
            throws ParserException
        {
            super(lex);
        }

        public Identifier getName(){
            return name;
        };

    }

   /*
     * Uncomment this variable declaration and add any necessary initialization
     * arguments for it in the setUp() method.
     */
     private ExtendedHead x;

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
