package testparser;

import junit.framework.TestCase;

import java.io.StringReader;
import java.util.ArrayList;
import project3.lex.Argument;
import project3.lex.Lex;
import project3.lex.Token;
import project3.three.Node;
import project3.three.List;
import project3.three.ParserException;
import project3.three.Predicate;
import project3.three.Query;

import project3.PredicateList;

/**
 * QueryTest is a JUnit test for class Query
 */
public class QueryTest
    extends TestCase
{
    /**
     * Constructs a test case for the test specified in the name argument.
     */
    public QueryTest (String name)
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
     * Test Query(lex)
     */
    public void testQueryConstructor(){
        try{
            try{
                createQuery("");
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            try{
                createQuery("==(A,B,C");
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            try{
                createQuery("A(");
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            try{
                createQuery("A()");
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            try{
                createQuery("A(A B)");
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            try{
                createQuery("A('A' B)");
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            try{
                createQuery("A(X,)");
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            try{
                createQuery("A(X,Y)");
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            StringReader sr = new StringReader("Apple(Id1)?");
            Lex lex = new Lex(sr);
            ExtendedQuery query = new ExtendedQuery(lex);
            assertTrue(query.toString().equals("Apple(Id1)? No\n"));

            query = createQuery("Apple(Id1)?");
            assertTrue(query.getNodes().size() == 1);
            ArrayList<Node> nodes =
                ((Predicate)query.getNodes().get(0)).getNodes();
            assertTrue(nodes.get(0) instanceof Argument);
            assertTrue(((Token)nodes.get(0)).getValue().equals("Id1"));
            assertTrue(query.toString().equals("Apple(Id1)? No\n"));

            query = createQuery("Apple('Id1')?");
            assertTrue(query.getNodes().size() == 1);
            nodes = ((Predicate)query.getNodes().get(0)).getNodes();
            assertTrue(nodes.get(0) instanceof Argument);
            assertTrue(((Token)nodes.get(0)).getValue().equals("'Id1'"));
            assertTrue(query.toString().equals("Apple('Id1')? No\n"));

            query = createQuery("M(Id1,\n Id2)?");
            assertTrue(query.getNodes().size() == 1);
            nodes = ((Predicate)query.getNodes().get(0)).getNodes();
            assertTrue(nodes.size() == 2);
            assertTrue(nodes.get(0) instanceof Argument);
            assertTrue(nodes.get(1) instanceof Argument);
            assertTrue(((Token)nodes.get(0)).getValue().equals("Id1"));
            assertTrue(((Token)nodes.get(1)).getValue().equals("Id2"));
            assertTrue(query.toString().equals(
                "M(Id1,Id2)? No\n"));

            query = createQuery("M(Id1,\n 'Id2')?");
            assertTrue(query.getNodes().size() == 1);
            nodes = ((Predicate)query.getNodes().get(0)).getNodes();
            assertTrue(nodes.size() == 2);
            assertTrue(nodes.get(0) instanceof Argument);
            assertTrue(nodes.get(1) instanceof Argument);
            assertTrue(((Token)nodes.get(0)).getValue().equals("Id1"));
            assertTrue(((Token)nodes.get(1)).getValue().equals("'Id2'"));
            assertTrue(query.toString().equals("M(Id1,'Id2')? No\n"));

            query = createQuery("M('Id1',\n Id2)?");
            assertTrue(query.getNodes().size() == 1);
            nodes = ((Predicate)query.getNodes().get(0)).getNodes();
            assertTrue(nodes.size() == 2);
            assertTrue(nodes.get(0) instanceof Argument);
            assertTrue(nodes.get(1) instanceof Argument);
            assertTrue(((Token)nodes.get(0)).getValue().equals("'Id1'"));
            assertTrue(((Token)nodes.get(1)).getValue().equals("Id2"));
            assertTrue(query.toString().equals(
                "M('Id1',Id2)? No\n"));

            query = createQuery("M('Id1',\n 'Id2')?");
            assertTrue(query.getNodes().size() == 1);
            nodes = ((Predicate)query.getNodes().get(0)).getNodes();
            assertTrue(nodes.size() == 2);
            assertTrue(nodes.get(0) instanceof Argument);
            assertTrue(nodes.get(1) instanceof Argument);
            assertTrue(((Token)nodes.get(0)).getValue().equals("'Id1'"));
            assertTrue(((Token)nodes.get(1)).getValue().equals("'Id2'"));
            assertTrue(query.toString().equals("M('Id1','Id2')? No\n"));

            query = createQuery("List(Name1,\n Name2,\n Name3)?");
            assertTrue(query.getNodes().size() == 1);
            nodes = ((Predicate)query.getNodes().get(0)).getNodes();
            assertTrue(nodes.size() == 3);
            assertTrue(nodes.get(0) instanceof Argument);
            assertTrue(nodes.get(1) instanceof Argument);
            assertTrue(nodes.get(2) instanceof Argument);
            assertTrue(((Token)nodes.get(0)).getValue().equals("Name1"));
            assertTrue(((Token)nodes.get(1)).getValue().equals("Name2"));
            assertTrue(((Token)nodes.get(2)).getValue().equals("Name3"));
            assertTrue(query.toString().equals(
               "List(Name1,Name2,Name3)? No\n"));

            query = createQuery("List(Name1,\n Name2,\n 'Name3')\t?");
            assertTrue(query.getNodes().size() == 1);
            nodes = ((Predicate)query.getNodes().get(0)).getNodes();
            assertTrue(nodes.size() == 3);
            assertTrue(nodes.get(0) instanceof Argument);
            assertTrue(nodes.get(1) instanceof Argument);
            assertTrue(nodes.get(2) instanceof Argument);
            assertTrue(((Token)nodes.get(0)).getValue().equals("Name1"));
            assertTrue(((Token)nodes.get(1)).getValue().equals("Name2"));
            assertTrue(((Token)nodes.get(2)).getValue().equals("'Name3'"));
            assertTrue(query.toString().equals(
               "List(Name1,Name2,'Name3')? No\n"));

            query = createQuery("List(Name1,\n 'Name2',\n Name3)\n?");
            assertTrue(query.getNodes().size() == 1);
            nodes = ((Predicate)query.getNodes().get(0)).getNodes();
            assertTrue(nodes.size() == 3);
            assertTrue(nodes.get(0) instanceof Argument);
            assertTrue(nodes.get(1) instanceof Argument);
            assertTrue(nodes.get(2) instanceof Argument);
            assertTrue(((Token)nodes.get(0)).getValue().equals("Name1"));
            assertTrue(((Token)nodes.get(1)).getValue().equals("'Name2'"));
            assertTrue(((Token)nodes.get(2)).getValue().equals("Name3"));
            assertTrue(query.toString().equals(
               "List(Name1,'Name2',Name3)? No\n"));

            query = createQuery("List(Name1,\n 'Name2',\n 'Name3') ? ");
            assertTrue(query.getNodes().size() == 1);
            nodes = ((Predicate)query.getNodes().get(0)).getNodes();
            assertTrue(nodes.size() == 3);
            assertTrue(nodes.get(0) instanceof Argument);
            assertTrue(nodes.get(1) instanceof Argument);
            assertTrue(nodes.get(2) instanceof Argument);
            assertTrue(((Token)nodes.get(0)).getValue().equals("Name1"));
            assertTrue(((Token)nodes.get(1)).getValue().equals("'Name2'"));
            assertTrue(((Token)nodes.get(2)).getValue().equals("'Name3'"));
            assertTrue(query.toString().equals(
               "List(Name1,'Name2','Name3')? No\n"));

            query = createQuery("List('Name1',\n Name2,\n Name3)?");
            assertTrue(query.getNodes().size() == 1);
            nodes = ((Predicate)query.getNodes().get(0)).getNodes();
            assertTrue(nodes.size() == 3);
            assertTrue(nodes.get(0) instanceof Argument);
            assertTrue(nodes.get(1) instanceof Argument);
            assertTrue(nodes.get(2) instanceof Argument);
            assertTrue(((Token)nodes.get(0)).getValue().equals("'Name1'"));
            assertTrue(((Token)nodes.get(1)).getValue().equals("Name2"));
            assertTrue(((Token)nodes.get(2)).getValue().equals("Name3"));
            assertTrue(query.toString().equals(
               "List('Name1',Name2,Name3)? No\n"));

            query = createQuery("List('Name1',\n Name2,\n 'Name3')?");
            assertTrue(query.getNodes().size() == 1);
            nodes = ((Predicate)query.getNodes().get(0)).getNodes();
            assertTrue(nodes.size() == 3);
            assertTrue(nodes.get(0) instanceof Argument);
            assertTrue(nodes.get(1) instanceof Argument);
            assertTrue(nodes.get(2) instanceof Argument);
            assertTrue(((Token)nodes.get(0)).getValue().equals("'Name1'"));
            assertTrue(((Token)nodes.get(1)).getValue().equals("Name2"));
            assertTrue(((Token)nodes.get(2)).getValue().equals("'Name3'"));
            assertTrue(query.toString().equals(
               "List('Name1',Name2,'Name3')? No\n"));

            query = createQuery("List('Name1',\n 'Name2',\n Name3)?");
            assertTrue(query.getNodes().size() == 1);
            nodes = ((Predicate)query.getNodes().get(0)).getNodes();
            assertTrue(nodes.size() == 3);
            assertTrue(nodes.get(0) instanceof Argument);
            assertTrue(nodes.get(1) instanceof Argument);
            assertTrue(nodes.get(2) instanceof Argument);
            assertTrue(((Token)nodes.get(0)).getValue().equals("'Name1'"));
            assertTrue(((Token)nodes.get(1)).getValue().equals("'Name2'"));
            assertTrue(((Token)nodes.get(2)).getValue().equals("Name3"));
            assertTrue(query.toString().equals(
               "List('Name1','Name2',Name3)? No\n"));

            query = createQuery("List('Name1',\n 'Name2',\n 'Name3')?");
            assertTrue(query.getNodes().size() == 1);
            nodes = ((Predicate)query.getNodes().get(0)).getNodes();
            assertTrue(nodes.size() == 3);
            assertTrue(nodes.get(0) instanceof Argument);
            assertTrue(nodes.get(1) instanceof Argument);
            assertTrue(nodes.get(2) instanceof Argument);
            assertTrue(((Token)nodes.get(0)).getValue().equals("'Name1'"));
            assertTrue(((Token)nodes.get(1)).getValue().equals("'Name2'"));
            assertTrue(((Token)nodes.get(2)).getValue().equals("'Name3'"));
            assertTrue(query.toString().equals(
               "List('Name1','Name2','Name3')? No\n"));
        }catch(ParserException e){
            System.out.println(
                "ERROR in QueryTest.testQueryConstructor\n"+
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

    private class ExtendedQuery extends Query{
        public ExtendedQuery(Lex lex)
            throws ParserException
        {
            super(lex);
        }

        public Argument get(int i){
            return (Argument)((Predicate)nodes.get(0)).getNodes().get(i);
        };

        public void setCopy(Query query)
            throws ParserException
        {
            copy = new PredicateList(query);
        };
    }

    private ExtendedQuery createQuery(String str)
         throws ParserException
    {
        StringReader sr = new StringReader(str);
        Lex lex = new Lex(sr);
        ExtendedQuery result = new ExtendedQuery(lex);
        result.setCopy(result);
        return result;
    };

   /**
     * Uncomment this variable declaration and add any necessary initialization
     * arguments for it in the setUp() method.
     */
     private ExtendedQuery x;

    /**
     * Utility main method. This will the test cases defined in this class.
     *
     * Usage: java QueryTest
     */
    public static void main (String[] args)
    {
        /* junit.textui.TestRunner will write the test results to stdout. */
        junit.textui.TestRunner.run (QueryTest.class);

        /* junit.swingui.TestRunner will display the test results in JUnit's
           swing interface. */
        //junit.swingui.TestRunner.run (QueryTest.class);
    }
}
