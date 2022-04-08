package testproject3;

import java.util.ArrayList;
import junit.framework.TestCase;

import project3.lex.Constant;
import project3.lex.Identifier;
import project3.lex.TokenType;

import project3.three.ParserException;

import project3.three.SetOfSolutions;


/**
 * SetOfSolutionsTest is a JUnit test for class SetOfSolutions
 */
public class SetOfSolutionsTest
    extends TestCase
{
    /**
     * Constructs a test case for the test specified in the name argument.
     */
    public SetOfSolutionsTest (String name)
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
     * Test SetOfSolutions(lex)
     */
    public void testSetOfSolutionsConstructor(){
        try{
            try{
                ExtendedSOS l = new ExtendedSOS(null);
                assertTrue(false);
            }catch(AssertionError e){
                assertTrue(true);
            };


            try{
                Identifier[] ids1 = {null};
                ExtendedSOS l = new ExtendedSOS(ids1);
                assertTrue(false);
            }catch(AssertionError e){
                assertTrue(true);
            };

            Identifier id1 = new Identifier("A", 1);
            Identifier id2 = new Identifier("B", 1);
            Identifier id3 = new Identifier("C", 1);

            try{
                Identifier[] ids1 = {id1, id2, null, id3};
                ExtendedSOS l = new ExtendedSOS(ids1);
                assertTrue(false);
            }catch(AssertionError e){
                assertTrue(true);
            };

            Identifier[] ids1 = {};
            Identifier[] ids2 = {id1};
            Identifier[] ids3 = {id1, id2, id3};

    
            ExtendedSOS l = new ExtendedSOS(ids1);
            assertTrue(l.getVars() == ids1);
            assertTrue(l.getVars().length == 0);

            l = new ExtendedSOS(ids2);
            assertTrue(l.getVars() == ids2);
            assertTrue(l.getVars().length == 1);
            assertTrue(l.getVars()[0] == id1);

            l = new ExtendedSOS(ids3);
            assertTrue(l.getVars() == ids3);
            assertTrue(l.getVars().length == 3);
            assertTrue(l.getVars()[0] == id1);
            assertTrue(l.getVars()[1] == id2);
            assertTrue(l.getVars()[2] == id3);

        }catch(ParserException e){
            System.out.println(
                "Parser ERROR in SetOfSolutionsTest." +
                "testSetOfSolutionsConstructor\n" +
                "    should not get here.\n" +
                "    error = " + e.getMessage());
        }catch(AssertionError e){
            System.out.println(
                "Assertion ERROR in SetOfSolutionsTest." +
                "testSetOfSolutionsConstructor\n" +
                "    should not get here.\n" +
                "    error = " + e.getMessage());
        };
    }

    /**
     * Test addSolution(lex)
     */
    public void testAddASolution(){
        try{
            Identifier id1 = new Identifier("A", 1);
            Identifier id2 = new Identifier("B", 1);
            Identifier id3 = new Identifier("C", 1);

            Constant const1 = new Constant("1", 1);
            Constant const2 = new Constant("2", 1);
            Constant const3 = new Constant("3", 1);

            Identifier[] ids1 = {id1};
            Identifier[] ids2 = {id1, id2, id3};

            Constant[] sub0 = {};

            Constant[] suba = {const1};
            Constant[] subb = {const2};
            Constant[] subc = {const3};
            Constant[] subd = {const3};

	    Constant[] sub1 = {const1, const2, const3};
	    Constant[] sub2 = {const3, const2, const1};
            Constant[] sub3 = {const1, const3, const2};
            Constant[] sub4 = {const1, const3, const2};

            ExtendedSOS l = new ExtendedSOS(ids1);

            try{
                l.addSolution(null);
                assertTrue(false);
            }catch(AssertionError e){
                assertTrue(true);
            };

            try{
                l.addSolution(sub0);
                assertTrue(false);
            }catch(AssertionError e){
                assertTrue(true);
            };

            try{
                l.addSolution(sub1);
                assertTrue(false);
            }catch(AssertionError e){
                assertTrue(true);
            };

            l.addSolution(suba);
            assertTrue(l.getSolutions().size() == 1);
            assertTrue(l.getSolutions().get(0) == suba);
            l.addSolution(subb);
            assertTrue(l.getSolutions().size() == 2);
            assertTrue(l.getSolutions().get(0) == suba);
            assertTrue(l.getSolutions().get(1) == subb);
            l.addSolution(subc);
            assertTrue(l.getSolutions().size() == 3);
            assertTrue(l.getSolutions().get(0) == suba);
            assertTrue(l.getSolutions().get(1) == subb);
            assertTrue(l.getSolutions().get(2) == subc);
            l.addSolution(suba);
            assertTrue(l.getSolutions().size() == 3);
            assertTrue(l.getSolutions().get(0) == suba);
            assertTrue(l.getSolutions().get(1) == subb);
            assertTrue(l.getSolutions().get(2) == subc);
            l.addSolution(subb);
            assertTrue(l.getSolutions().size() == 3);
            assertTrue(l.getSolutions().get(0) == suba);
            assertTrue(l.getSolutions().get(1) == subb);
            assertTrue(l.getSolutions().get(2) == subc);
            l.addSolution(subc);
            assertTrue(l.getSolutions().size() == 3);
            assertTrue(l.getSolutions().get(0) == suba);
            assertTrue(l.getSolutions().get(1) == subb);
            assertTrue(l.getSolutions().get(2) == subc);

            try{
                l.addSolution(sub4);
                assertTrue(false);
            }catch(AssertionError e){
                assertTrue(true);
            };

            l = new ExtendedSOS(ids2);

            try{
                l.addSolution(suba);
                assertTrue(false);
            }catch(AssertionError e){
                assertTrue(true);
            };

            l.addSolution(sub1);
            assertTrue(l.getSolutions().size() == 1);
            assertTrue(l.getSolutions().get(0) == sub1);
            l.addSolution(sub2);
            assertTrue(l.getSolutions().size() == 2);
            assertTrue(l.getSolutions().get(0) == sub1);
            assertTrue(l.getSolutions().get(1) == sub2);
            l.addSolution(sub3);
            assertTrue(l.getSolutions().size() == 3);
            assertTrue(l.getSolutions().get(0) == sub1);
            assertTrue(l.getSolutions().get(1) == sub2);
            assertTrue(l.getSolutions().get(2) == sub3);
            l.addSolution(sub1);
            l.addSolution(sub2);
            l.addSolution(sub3);
            assertTrue(l.getSolutions().size() == 3);
            assertTrue(l.getSolutions().get(0) == sub1);
            assertTrue(l.getSolutions().get(1) == sub2);
            assertTrue(l.getSolutions().get(2) == sub3);

        }catch(ParserException e){
            System.out.println(
                "Parser ERROR in SetOfSolutionsTest." +
                "addSolution(Constant[])\n" +
                "    should not get here.\n" +
                "    error = " + e.getMessage());
        }catch(AssertionError e){
            System.out.println(
                "Assertion ERROR in SetOfSolutionsTest." +
                "addSolution(Constant[])\n" +
                "    should not get here.\n" +
                "    error = " + e.getMessage());
        };
    }

    /**
     * Test toString(lex)
     */
    public void testToString(){
        try{
            Identifier id1 = new Identifier("A", 1);
            Identifier id2 = new Identifier("B", 1);
            Identifier id3 = new Identifier("C", 1);

            Constant const1 = new Constant("1", 1);
            Constant const2 = new Constant("2", 1);
            Constant const3 = new Constant("3", 1);

            Identifier[] ids1 = {id1};
            Identifier[] ids2 = {id1, id2, id3};

            Constant[] sub0 = {};

            Constant[] suba = {const1};
            Constant[] subb = {const2};
            Constant[] subc = {const3};
            Constant[] subd = {const3};

	    Constant[] sub1 = {const1, const2, const3};
	    Constant[] sub2 = {const3, const2, const1};
            Constant[] sub3 = {const1, const3, const2};
            Constant[] sub4 = {const1, const3, const2};

            ExtendedSOS l = new ExtendedSOS(ids1);
            assertTrue(l.toString().equals(" No\n"));
            l.addSolution(suba);
            assertTrue(l.toString().equals(" Yes(1)\n  A='1'\n"));
            l.addSolution(subb);
            assertTrue(l.toString().equals(
                " Yes(2)\n  A='1'\n  A='2'\n"));
            l.addSolution(subc);
            assertTrue(l.toString().equals(
                " Yes(3)\n  A='1'\n  A='2'\n  A='3'\n"));

            l = new ExtendedSOS(ids2);
            assertTrue(l.toString().equals(" No\n"));
            l.addSolution(sub1);
            assertTrue(l.toString().equals(
               " Yes(1)\n  A='1', B='2', C='3'\n"));
            l.addSolution(sub2);
            assertTrue(l.toString().equals(
               " Yes(2)\n  A='1', B='2', C='3'\n"+
                        "  A='3', B='2', C='1'\n"));
            l.addSolution(sub3);
            assertTrue(l.toString().equals(
               " Yes(3)\n  A='1', B='2', C='3'\n"+
                        "  A='3', B='2', C='1'\n"+
                        "  A='1', B='3', C='2'\n"));

        	System.out.print( "SetOfSolutionsTest " );
        	System.out.println( "PASSED" );
        	
        }catch(ParserException e){
            System.out.println(
                "Parser ERROR in SetOfSolutionsTest." +
                "addSolution(Constant[])\n" +
                "    should not get here.\n" +
                "    error = " + e.getMessage());
        }catch(AssertionError e){
            System.out.println(
                "Assertion ERROR in SetOfSolutionsTest." +
                "addSolution(Constant[])\n" +
                "    should not get here.\n" +
                "    error = " + e.getMessage());
        };
    }

    ///////////////////
    static boolean debug = false;
    static String eoln = System.getProperty("line.separator");

    protected class ExtendedSOS extends SetOfSolutions{
        public ExtendedSOS(Identifier[] variables){
            super(variables);
        }

        public ArrayList<Constant[]> getSolutions(){
            return setOfSolutions;
        }

        public Identifier[] getVars(){
            return variables;
        }
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
     * Usage: java SetOfSolutionsTest
     */
    public static void main (String[] args)
    {
        /* junit.textui.TestRunner will write the test results to stdout. */
        junit.textui.TestRunner.run (SetOfSolutionsTest.class);

        /* junit.swingui.TestRunner will display the test results in JUnit's
           swing interface. */
        //junit.swingui.TestRunner.run (SetOfSolutionsTest.class);
    }
}
