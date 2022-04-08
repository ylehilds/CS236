package testparser;

import junit.framework.TestCase;

import java.io.StringReader;
import java.util.ArrayList;
import project3.lex.Argument;
import project3.lex.Constant;
import project3.lex.Lex;
import project3.lex.Identifier;
import project3.lex.Token;
import project3.lex.TokenType;
import project3.three.Node;
import project3.three.ParserException;
import project3.three.Predicate;

/**
 * PredicateTest is a JUnit test for class Predicate
 */
public class PredicateTest
    extends TestCase
{
    /**
     * Constructs a test case for the test specified in the name argument.
     */
    public PredicateTest (String name)
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
     * Test Predicate(lex)
     */
    public void testPredicateConstructor(){
        try{
            try{
                StringReader sr = new StringReader("");
                Lex lex = new Lex(sr);
                ExtendedPredicate predicate =
                    new ExtendedPredicate(lex);
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            try{
                StringReader sr = new StringReader("==(A,B,C");
                Lex lex = new Lex(sr);
                ExtendedPredicate predicate =
                    new ExtendedPredicate(lex);
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            try{
                StringReader sr = new StringReader("A(");
                Lex lex = new Lex(sr);
                ExtendedPredicate predicate =
                    new ExtendedPredicate(lex);
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            try{
                StringReader sr = new StringReader("A()");
                Lex lex = new Lex(sr);
                ExtendedPredicate predicate =
                    new ExtendedPredicate(lex);
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            try{
                StringReader sr = new StringReader("A(A B)");
                Lex lex = new Lex(sr);
                ExtendedPredicate predicate =
                    new ExtendedPredicate(lex);
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            try{
                StringReader sr = new StringReader("A('A' B)");
                Lex lex = new Lex(sr);
                ExtendedPredicate predicate =
                    new ExtendedPredicate(lex);
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            try{
                StringReader sr = new StringReader("A(X,)");
                Lex lex = new Lex(sr);
                ExtendedPredicate predicate =
                    new ExtendedPredicate(lex);
                assertTrue(false);
            }catch(ParserException e){
                assertTrue(true);
            };

            StringReader sr = new StringReader("Apple(Id1)");
            Lex lex = new Lex(sr);
            ExtendedPredicate predicate =
                new ExtendedPredicate(lex);
            ArrayList<Node> nodes = predicate.getNodes();
            assertTrue(nodes.get(0) instanceof Argument);
            assertTrue(((Token)nodes.get(0)).getValue().equals("Id1"));
            assertTrue(predicate.getName().getValue().equals("Apple"));
            assertTrue(predicate.toString().equals("Apple(Id1)"));
            //assertTrue(predicate.hashCode() == 4542588);

            sr = new StringReader("Apple('Id1')");
            lex = new Lex(sr);
            predicate = new ExtendedPredicate(lex);
            nodes = predicate.getNodes();
            assertTrue(nodes.get(0) instanceof Argument);
            assertTrue(((Token)nodes.get(0)).getValue().equals("'Id1'"));
            assertTrue(predicate.getName().getValue().equals("Apple"));
            assertTrue(predicate.toString().equals("Apple('Id1')"));
            //assertTrue(predicate.hashCode() == 25247522);

            sr = new StringReader("M(Id1,\n Id2)");
            lex = new Lex(sr);
            predicate = new ExtendedPredicate(lex);
            nodes = predicate.getNodes();
            assertTrue(nodes.size() == 2);
            assertTrue(nodes.get(0) instanceof Argument);
            assertTrue(nodes.get(1) instanceof Argument);
            assertTrue(((Token)nodes.get(0)).getValue().equals("Id1"));
            assertTrue(((Token)nodes.get(1)).getValue().equals("Id2"));
            assertTrue(predicate.getName().getValue().equals("M"));
            assertTrue(predicate.toString().equals("M(Id1,Id2)"));
            //assertTrue(predicate.hashCode() == 9085177);

            sr = new StringReader("M(Id1,\n 'Id2')");
            lex = new Lex(sr);
            predicate = new ExtendedPredicate(lex);
            nodes = predicate.getNodes();
            assertTrue(nodes.size() == 2);
            assertTrue(nodes.get(0) instanceof Argument);
            assertTrue(nodes.get(1) instanceof Argument);
            assertTrue(((Token)nodes.get(0)).getValue().equals("Id1"));
            assertTrue(((Token)nodes.get(1)).getValue().equals("'Id2'"));
            assertTrue(predicate.getName().getValue().equals("M"));
            assertTrue(predicate.toString().equals("M(Id1,'Id2')"));
            //assertTrue(predicate.hashCode() == 29790111);

            sr = new StringReader("M('Id1',\n Id2)");
            lex = new Lex(sr);
            predicate = new ExtendedPredicate(lex);
            nodes = predicate.getNodes();
            assertTrue(nodes.size() == 2);
            assertTrue(nodes.get(0) instanceof Argument);
            assertTrue(nodes.get(1) instanceof Argument);
            assertTrue(((Token)nodes.get(0)).getValue().equals("'Id1'"));
            assertTrue(((Token)nodes.get(1)).getValue().equals("Id2"));
            assertTrue(predicate.getName().getValue().equals("M"));
            assertTrue(predicate.toString().equals("M('Id1',Id2)"));
            //assertTrue(predicate.hashCode() == 29790111);

            sr = new StringReader("M('Id1',\n 'Id2')");
            lex = new Lex(sr);
            predicate = new ExtendedPredicate(lex);
            nodes = predicate.getNodes();
            assertTrue(nodes.size() == 2);
            assertTrue(nodes.get(0) instanceof Argument);
            assertTrue(nodes.get(1) instanceof Argument);
            assertTrue(((Token)nodes.get(0)).getValue().equals("'Id1'"));
            assertTrue(((Token)nodes.get(1)).getValue().equals("'Id2'"));
            assertTrue(predicate.getName().getValue().equals("M"));
            assertTrue(predicate.toString().equals("M('Id1','Id2')"));
            //assertTrue(predicate.hashCode() == 50495045);

            sr = new StringReader("List(Name1,\n Name2,\n Name3)");
            lex = new Lex(sr);
            predicate = new ExtendedPredicate(lex);
            nodes = predicate.getNodes();
            assertTrue(nodes.size() == 3);
            assertTrue(nodes.get(0) instanceof Argument);
            assertTrue(nodes.get(1) instanceof Argument);
            assertTrue(nodes.get(2) instanceof Argument);
            assertTrue(((Token)nodes.get(0)).getValue().equals("Name1"));
            assertTrue(((Token)nodes.get(1)).getValue().equals("Name2"));
            assertTrue(((Token)nodes.get(2)).getValue().equals("Name3"));
            assertTrue(predicate.getName().getValue().equals("List"));
            assertTrue(predicate.toString().equals(
               "List(Name1,Name2,Name3)"));
            //assertTrue(predicate.hashCode() == 238504743);

            sr = new StringReader("List(Name1,\n Name2,\n 'Name3')");
            lex = new Lex(sr);
            predicate = new ExtendedPredicate(lex);
            nodes = predicate.getNodes();
            assertTrue(nodes.size() == 3);
            assertTrue(nodes.get(0) instanceof Argument);
            assertTrue(nodes.get(1) instanceof Argument);
            assertTrue(nodes.get(2) instanceof Argument);
            assertTrue(((Token)nodes.get(0)).getValue().equals("Name1"));
            assertTrue(((Token)nodes.get(1)).getValue().equals("Name2"));
            assertTrue(((Token)nodes.get(2)).getValue().equals("'Name3'"));
            assertTrue(predicate.getName().getValue().equals("List"));
            assertTrue(predicate.toString().equals(
               "List(Name1,Name2,'Name3')"));
            //assertTrue(predicate.hashCode() == 259209677);

            sr = new StringReader("List(Name1,\n 'Name2',\n Name3)");
            lex = new Lex(sr);
            predicate = new ExtendedPredicate(lex);
            nodes = predicate.getNodes();
            assertTrue(nodes.size() == 3);
            assertTrue(nodes.get(0) instanceof Argument);
            assertTrue(nodes.get(1) instanceof Argument);
            assertTrue(nodes.get(2) instanceof Argument);
            assertTrue(((Token)nodes.get(0)).getValue().equals("Name1"));
            assertTrue(((Token)nodes.get(1)).getValue().equals("'Name2'"));
            assertTrue(((Token)nodes.get(2)).getValue().equals("Name3"));
            assertTrue(predicate.getName().getValue().equals("List"));
            assertTrue(predicate.toString().equals(
               "List(Name1,'Name2',Name3)"));
            //assertTrue(predicate.hashCode() == 259209677);

            sr = new StringReader("List(Name1,\n 'Name2',\n 'Name3')");
            lex = new Lex(sr);
            predicate = new ExtendedPredicate(lex);
            nodes = predicate.getNodes();
            assertTrue(nodes.size() == 3);
            assertTrue(nodes.get(0) instanceof Argument);
            assertTrue(nodes.get(1) instanceof Argument);
            assertTrue(nodes.get(2) instanceof Argument);
            assertTrue(((Token)nodes.get(0)).getValue().equals("Name1"));
            assertTrue(((Token)nodes.get(1)).getValue().equals("'Name2'"));
            assertTrue(((Token)nodes.get(2)).getValue().equals("'Name3'"));
            assertTrue(predicate.getName().getValue().equals("List"));
            assertTrue(predicate.toString().equals(
               "List(Name1,'Name2','Name3')"));
            //assertTrue(predicate.hashCode() == 279914611);

            sr = new StringReader("List('Name1',\n Name2,\n Name3)");
            lex = new Lex(sr);
            predicate = new ExtendedPredicate(lex);
            nodes = predicate.getNodes();
            assertTrue(nodes.size() == 3);
            assertTrue(nodes.get(0) instanceof Argument);
            assertTrue(nodes.get(1) instanceof Argument);
            assertTrue(nodes.get(2) instanceof Argument);
            assertTrue(((Token)nodes.get(0)).getValue().equals("'Name1'"));
            assertTrue(((Token)nodes.get(1)).getValue().equals("Name2"));
            assertTrue(((Token)nodes.get(2)).getValue().equals("Name3"));
            assertTrue(predicate.getName().getValue().equals("List"));
            assertTrue(predicate.toString().equals(
               "List('Name1',Name2,Name3)"));
            //assertTrue(predicate.hashCode() == 259209677);

            sr = new StringReader("List('Name1',\n Name2,\n 'Name3')");
            lex = new Lex(sr);
            predicate = new ExtendedPredicate(lex);
            nodes = predicate.getNodes();
            assertTrue(nodes.size() == 3);
            assertTrue(nodes.get(0) instanceof Argument);
            assertTrue(nodes.get(1) instanceof Argument);
            assertTrue(nodes.get(2) instanceof Argument);
            assertTrue(((Token)nodes.get(0)).getValue().equals("'Name1'"));
            assertTrue(((Token)nodes.get(1)).getValue().equals("Name2"));
            assertTrue(((Token)nodes.get(2)).getValue().equals("'Name3'"));
            assertTrue(predicate.getName().getValue().equals("List"));
            assertTrue(predicate.toString().equals(
               "List('Name1',Name2,'Name3')"));
            //assertTrue(predicate.hashCode() == 279914611);

            sr = new StringReader("List('Name1',\n 'Name2',\n Name3)");
            lex = new Lex(sr);
            predicate = new ExtendedPredicate(lex);
            nodes = predicate.getNodes();
            assertTrue(nodes.size() == 3);
            assertTrue(nodes.get(0) instanceof Argument);
            assertTrue(nodes.get(1) instanceof Argument);
            assertTrue(nodes.get(2) instanceof Argument);
            assertTrue(((Token)nodes.get(0)).getValue().equals("'Name1'"));
            assertTrue(((Token)nodes.get(1)).getValue().equals("'Name2'"));
            assertTrue(((Token)nodes.get(2)).getValue().equals("Name3"));
            assertTrue(predicate.getName().getValue().equals("List"));
            assertTrue(predicate.toString().equals(
               "List('Name1','Name2',Name3)"));
            //assertTrue(predicate.hashCode() == 279914611);

            sr = new StringReader("List('Name1',\n 'Name2',\n 'Name3')");
            lex = new Lex(sr);
            predicate = new ExtendedPredicate(lex);
            nodes = predicate.getNodes();
            assertTrue(nodes.size() == 3);
            assertTrue(nodes.get(0) instanceof Argument);
            assertTrue(nodes.get(1) instanceof Argument);
            assertTrue(nodes.get(2) instanceof Argument);
            assertTrue(((Token)nodes.get(0)).getValue().equals("'Name1'"));
            assertTrue(((Token)nodes.get(1)).getValue().equals("'Name2'"));
            assertTrue(((Token)nodes.get(2)).getValue().equals("'Name3'"));
            assertTrue(predicate.getName().getValue().equals("List"));
            assertTrue(predicate.toString().equals(
               "List('Name1','Name2','Name3')"));
            //assertTrue(predicate.hashCode() == 300619545);
        }catch(ParserException e){
            System.out.println(
                "ERROR in PredicateTest.testPredicateConstructor\n"+
                "    should not get here.\n" +
                "    error = " + e.getMessage());
        };
    }

    public void testCopyConstructor(){
        try{
            StringReader sr = new StringReader("Apple(Id1)");
            Lex lex = new Lex(sr);
            ExtendedPredicate predicate =
                new ExtendedPredicate(lex);
            ExtendedPredicate predicate2 =
                new ExtendedPredicate(predicate);
            assertTrue(predicate.equals(predicate2));
            assertFalse(predicate == predicate2);

            sr = new StringReader("Apple('Id1')");
            lex = new Lex(sr);
            predicate = new ExtendedPredicate(lex);
            predicate2 = new ExtendedPredicate(predicate);
            assertTrue(predicate.equals(predicate2));
            assertFalse(predicate == predicate2);

            sr = new StringReader("M(Id1,\n Id2)");
            lex = new Lex(sr);
            predicate = new ExtendedPredicate(lex);
            predicate2 = new ExtendedPredicate(predicate);
            assertTrue(predicate.equals(predicate2));
            assertFalse(predicate == predicate2);

            sr = new StringReader("M(Id1,\n 'Id2')");
            lex = new Lex(sr);
            predicate = new ExtendedPredicate(lex);
            predicate2 = new ExtendedPredicate(predicate);
            assertTrue(predicate.equals(predicate2));
            assertFalse(predicate == predicate2);

            sr = new StringReader("M('Id1',\n Id2)");
            lex = new Lex(sr);
            predicate = new ExtendedPredicate(lex);
            predicate2 = new ExtendedPredicate(predicate);
            assertTrue(predicate.equals(predicate2));
            assertFalse(predicate == predicate2);

            sr = new StringReader("M('Id1',\n 'Id2')");
            lex = new Lex(sr);
            predicate = new ExtendedPredicate(lex);
            predicate2 = new ExtendedPredicate(predicate);
            assertTrue(predicate.equals(predicate2));
            assertFalse(predicate == predicate2);

            sr = new StringReader("List(Name1,\n Name2,\n Name3)");
            lex = new Lex(sr);
            predicate = new ExtendedPredicate(lex);
            predicate2 = new ExtendedPredicate(predicate);
            assertTrue(predicate.equals(predicate2));
            assertFalse(predicate == predicate2);

            sr = new StringReader("List(Name1,\n Name2,\n 'Name3')");
            lex = new Lex(sr);
            predicate = new ExtendedPredicate(lex);
            predicate2 = new ExtendedPredicate(predicate);
            assertTrue(predicate.equals(predicate2));
            assertFalse(predicate == predicate2);

            sr = new StringReader("List(Name1,\n 'Name2',\n Name3)");
            lex = new Lex(sr);
            predicate = new ExtendedPredicate(lex);
            predicate2 = new ExtendedPredicate(predicate);
            assertTrue(predicate.equals(predicate2));
            assertFalse(predicate == predicate2);

            sr = new StringReader("List(Name1,\n 'Name2',\n 'Name3')");
            lex = new Lex(sr);
            predicate = new ExtendedPredicate(lex);
            predicate2 = new ExtendedPredicate(predicate);
            assertTrue(predicate.equals(predicate2));
            assertFalse(predicate == predicate2);

            sr = new StringReader("List('Name1',\n Name2,\n Name3)");
            lex = new Lex(sr);
            predicate = new ExtendedPredicate(lex);
            predicate2 = new ExtendedPredicate(predicate);
            assertTrue(predicate.equals(predicate2));
            assertFalse(predicate == predicate2);

            sr = new StringReader("List('Name1',\n Name2,\n 'Name3')");
            lex = new Lex(sr);
            predicate = new ExtendedPredicate(lex);
            predicate2 = new ExtendedPredicate(predicate);
            assertTrue(predicate.equals(predicate2));
            assertFalse(predicate == predicate2);

            sr = new StringReader("List('Name1',\n 'Name2',\n Name3)");
            lex = new Lex(sr);
            predicate = new ExtendedPredicate(lex);
            predicate2 = new ExtendedPredicate(predicate);
            assertTrue(predicate.equals(predicate2));
            assertFalse(predicate == predicate2);

            sr = new StringReader("List('Name1',\n 'Name2',\n 'Name3')");
            lex = new Lex(sr);
            predicate = new ExtendedPredicate(lex);
            predicate2 = new ExtendedPredicate(predicate);
            assertTrue(predicate.equals(predicate2));
            assertFalse(predicate == predicate2);
        }catch(ParserException e){
            System.out.println(
                "ERROR in PredicateTest.testCopyConstructor\n"+
                "    should not get here.\n" +
                "    error = " + e.getMessage());
        };
    }

    public void testSet(){
        try{
            Constant c = new Constant("i", 20);
            Constant d = new Constant("j", 21);
            Constant e = new Constant("k", 22);

            StringReader sr = new StringReader("Apple(Id1)");
            Lex lex = new Lex(sr);
            ExtendedPredicate predicate =
                new ExtendedPredicate(lex);
            assertTrue(predicate.get(0).getValue().equals("Id1"));
            predicate.set(0, c);
            assertTrue(predicate.get(0) == c);
            predicate.set(0, d);
            assertTrue(predicate.get(0) == d);
            predicate.set(0, e);
            assertTrue(predicate.get(0) == e);
            predicate.set(0, c);
            assertTrue(predicate.get(0) == c);

            try{
                sr = new StringReader("Apple(Id1)");
                lex = new Lex(sr);
                predicate = new ExtendedPredicate(lex);
                predicate.set(-100, c);
                assertTrue(false);
            }catch(Exception f){
                assertTrue(true);
            };

            try{
                sr = new StringReader("Apple(Id1)");
                lex = new Lex(sr);
                predicate = new ExtendedPredicate(lex);
                predicate.set(-1, c);
                assertTrue(false);
            }catch(Exception f){
                assertTrue(true);
            };

            try{
                sr = new StringReader("Apple(Id1)");
                lex = new Lex(sr);
                predicate = new ExtendedPredicate(lex);
                predicate.set(1, c);
                assertTrue(false);
            }catch(Exception f){
                assertTrue(true);
            };

            try{
                sr = new StringReader("Apple(Id1)");
                lex = new Lex(sr);
                predicate = new ExtendedPredicate(lex);
                predicate.set(100, c);
                assertTrue(false);
            }catch(Exception f){
                assertTrue(true);
            };

            sr = new StringReader("M(Id1,\n Id2)");
            lex = new Lex(sr);
            predicate = new ExtendedPredicate(lex);
            assertTrue(predicate.get(0).getValue().equals("Id1"));
            assertTrue(predicate.get(1).getValue().equals("Id2"));
            predicate.set(0, c);
            predicate.set(1, d);
            assertTrue(predicate.get(0) == c);
            assertTrue(predicate.get(1) == d);
            predicate.set(0,e);
            assertTrue(predicate.get(0) == e);

            try{
                sr = new StringReader("M(Id1, Id2)");
                lex = new Lex(sr);
                predicate = new ExtendedPredicate(lex);
                predicate.set(-100, c);
                assertTrue(false);
            }catch(Exception f){
                assertTrue(true);
            };

            try{
                sr = new StringReader("M(Id1, Id2)");
                lex = new Lex(sr);
                predicate = new ExtendedPredicate(lex);
                predicate.set(-1, c);
                assertTrue(false);
            }catch(Exception f){
                assertTrue(true);
            };

            try{
                sr = new StringReader("M(Id1, Id2)");
                lex = new Lex(sr);
                predicate = new ExtendedPredicate(lex);
                predicate.set(2, c);
                assertTrue(false);
            }catch(Exception f){
                assertTrue(true);
            };

            try{
                sr = new StringReader("M(Id1, Id2)");
                lex = new Lex(sr);
                predicate = new ExtendedPredicate(lex);
                predicate.set(99, c);
                assertTrue(false);
            }catch(Exception f){
                assertTrue(true);
            };

            sr = new StringReader("List(Name1,\n Name2,\n Name3)");
            lex = new Lex(sr);
            predicate = new ExtendedPredicate(lex);
            assertTrue(predicate.get(0).getValue().equals("Name1"));
            assertTrue(predicate.get(1).getValue().equals("Name2"));
            assertTrue(predicate.get(2).getValue().equals("Name3"));
            predicate.set(0, c);
            predicate.set(1, d);
            predicate.set(2, e);
            assertTrue(predicate.get(0) == c);
            assertTrue(predicate.get(1) == d);
            assertTrue(predicate.get(2) == e);

            try{
                sr = new StringReader("List(Name1,\n Name2,\n Name3)");
                lex = new Lex(sr);
                predicate = new ExtendedPredicate(lex);
                predicate.set(-100, c);
                assertTrue(false);
            }catch(Exception f){
                assertTrue(true);
            };

            try{
                sr = new StringReader("List(Name1,\n Name2,\n Name3)");
                lex = new Lex(sr);
                predicate = new ExtendedPredicate(lex);
                predicate.set(-1, c);
                assertTrue(false);
            }catch(Exception f){
                assertTrue(true);
            };

            try{
                sr = new StringReader("List(Name1,\n Name2,\n Name3)");
                lex = new Lex(sr);
                predicate = new ExtendedPredicate(lex);
                predicate.set(3, c);
                assertTrue(false);
            }catch(Exception f){
                assertTrue(true);
            };

            try{
                sr = new StringReader("List(Name1,\n Name2,\n Name3)");
                lex = new Lex(sr);
                predicate = new ExtendedPredicate(lex);
                predicate.set(1000, c);
                assertTrue(false);
            }catch(Exception f){
                assertTrue(true);
            };

        }catch(ParserException e){
            System.out.println(
                "Parser ERROR in PredicateTest.testSet\n"+
                "    should not get here.\n" +
                "    error = " + e.getMessage());
        }catch(Exception e){
            System.out.println(
                "ERROR in PredicateTest.testSet\n"+
                "    should not get here.\n" +
                "    error = " + e.getMessage());
        };
    }

    /**
     * Test allConstants()
     */
    public void testAllConstants(){
        try{
            StringReader sr = new StringReader("Apple(Id1)");
            Lex lex = new Lex(sr);
            ExtendedPredicate predicate =
                new ExtendedPredicate(lex);
            assertFalse(predicate.allConstants());
           

            sr = new StringReader("Apple('Id1')");
            lex = new Lex(sr);
            predicate = new ExtendedPredicate(lex);
            assertTrue(predicate.allConstants());

            sr = new StringReader("M(Id1,\n Id2)");
            lex = new Lex(sr);
            predicate = new ExtendedPredicate(lex);
            assertFalse(predicate.allConstants());

            sr = new StringReader("M(Id1,\n 'Id2')");
            lex = new Lex(sr);
            predicate = new ExtendedPredicate(lex);
            assertFalse(predicate.allConstants());

            sr = new StringReader("M('Id1',\n Id2)");
            lex = new Lex(sr);
            predicate = new ExtendedPredicate(lex);
            assertFalse(predicate.allConstants());

            sr = new StringReader("M('Id1',\n 'Id2')");
            lex = new Lex(sr);
            predicate = new ExtendedPredicate(lex);
            assertTrue(predicate.allConstants());

            sr = new StringReader("List(Name1,\n Name2,\n Name3)");
            lex = new Lex(sr);
            predicate = new ExtendedPredicate(lex);
            assertFalse(predicate.allConstants());

            sr = new StringReader("List(Name1,\n Name2,\n 'Name3')");
            lex = new Lex(sr);
            predicate = new ExtendedPredicate(lex);
            assertFalse(predicate.allConstants());

            sr = new StringReader("List(Name1,\n 'Name2',\n Name3)");
            lex = new Lex(sr);
            predicate = new ExtendedPredicate(lex);
            assertFalse(predicate.allConstants());

            sr = new StringReader("List(Name1,\n 'Name2',\n 'Name3')");
            lex = new Lex(sr);
            predicate = new ExtendedPredicate(lex);
            assertFalse(predicate.allConstants());

            sr = new StringReader("List('Name1',\n Name2,\n Name3)");
            lex = new Lex(sr);
            predicate = new ExtendedPredicate(lex);
            assertFalse(predicate.allConstants());

            sr = new StringReader("List('Name1',\n Name2,\n 'Name3')");
            lex = new Lex(sr);
            predicate = new ExtendedPredicate(lex);
            assertFalse(predicate.allConstants());

            sr = new StringReader("List('Name1',\n 'Name2',\n Name3)");
            lex = new Lex(sr);
            predicate = new ExtendedPredicate(lex);
            assertFalse(predicate.allConstants());

            sr = new StringReader("List('Name1',\n 'Name2',\n 'Name3')");
            lex = new Lex(sr);
            predicate = new ExtendedPredicate(lex);
            assertTrue(predicate.allConstants());
        }catch(ParserException e){
            System.out.println(
                "Parser ERROR in PredicateTest.allConstants\n"+
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

    private class ExtendedPredicate extends Predicate{
        public ExtendedPredicate(Lex lex)
            throws ParserException
        {
            super(lex);
        }

        public ExtendedPredicate(ExtendedPredicate predicate)
            throws ParserException
        {
            super(predicate);
        }

        public Identifier getName(){
            return name;
        };

        public Argument get(int i){
            return (Argument)nodes.get(i);
        };

    }

   /*
     * Uncomment this variable declaration and add any necessary initialization
     * arguments for it in the setUp() method.
     */
     private ExtendedPredicate x;

    /**
     * Utility main method. This will the test cases defined in this class.
     *
     * Usage: java PredicateTest
     */
    public static void main (String[] args)
    {
        /* junit.textui.TestRunner will write the test results to stdout. */
        junit.textui.TestRunner.run (PredicateTest.class);

        /* junit.swingui.TestRunner will display the test results in JUnit's
           swing interface. */
        //junit.swingui.TestRunner.run (PredicateTest.class);
    }
}
