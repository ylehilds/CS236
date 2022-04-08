package testparser;

import junit.framework.TestCase;
import java.util.Iterator;
import java.util.ArrayList;

import project3.lex.Constant;
import project3.lex.TokenType;
import project3.three.Domain;
import project3.three.ParserException;

/**
 * DomainTest is a JUnit test for class Domain
 */
public class DomainTest
    extends TestCase
{
    /**
     * Constructs a test case for the test specified in the name argument.
     */
    public DomainTest(String name)
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
     * Test everything
     */
    public void test1()
    {
        try{
            ArrayList<Constant> set = new ArrayList<Constant>();
            Iterator<Constant> i = Domain.iterator();
            assertTrue(!i.hasNext());

            Constant c1 = new Constant("a", 1);
            assertTrue(Domain.isEmpty());
            Domain.add(c1);
            assertFalse(Domain.isEmpty());
            set.add(c1);
            i = Domain.iterator();
            assertTrue(i.hasNext());
            Constant x = i.next();
            assertTrue(!i.hasNext());
            assertTrue(x.getTokenType() == TokenType.STRING);
            assertTrue(x.getValue().equals("'a'"));
            assertTrue(x.getLineNumber() == 1);
    
            Constant c2 = new Constant("b", 2);
            Domain.add(c2);
            assertFalse(Domain.isEmpty());
            set.add(c2);
            Constant c3 = new Constant("c", 3);
            Domain.add(c3);
            set.add(c3);
            assertFalse(Domain.isEmpty());
            i = Domain.iterator();
            assertTrue(i.hasNext());
            x = i.next();
            assertTrue(x.getTokenType() == TokenType.STRING);
            assertTrue(set.contains(x));
            set.remove(x);
            assertTrue(i.hasNext());
            x = i.next();
            assertTrue(x.getTokenType() == TokenType.STRING);
            assertTrue(set.contains(x));
            set.remove(x);
            assertTrue(i.hasNext());
            x = i.next();
            assertTrue(x.getTokenType() == TokenType.STRING);
            assertTrue(set.contains(x));
            set.remove(x);
            assertTrue(!i.hasNext());
            assertTrue(set.isEmpty());

            Domain.reset();
            assertTrue(Domain.isEmpty());
        }catch(ParserException e){
            System.err.println("ERROR in Domain::test1()\n" +
                               "Should not be able to get here" + e);
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

   /*
     * Uncomment this variable declaration and add any necessary initialization
     * arguments for it in the setUp() method.
     */

    /**
     * Utility main method. This will the test cases defined in this class.
     *
     * Usage: java DomainTest
     */
    public static void main (String[] args)
    {
        /* junit.textui.TestRunner will write the test results to stdout. */
        junit.textui.TestRunner.run (DomainTest.class);

        /* junit.swingui.TestRunner will display the test results in JUnit's
           swing interface. */
        //junit.swingui.TestRunner.run (DomainTest.class);
    }
}
