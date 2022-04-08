package testproject3;


import junit.framework.TestCase;

import java.util.HashSet;
import java.util.Iterator;

import project3.lex.Constant;
import project3.lex.Identifier;
import project3.lex.TokenType;
import project3.three.ParserException;
import project3.three.Location;
import project3.three.QueryVariableInformation;

/**
 * QueryVariableInformationTest is a JUnit test for class QueryVariableInformation
 */
public class QueryVariableInformationTest
    extends TestCase
{
    /**
     * Constructs a test case for the test specified in the name argument.
     */
    public QueryVariableInformationTest (String name)
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
     * Test QueryVariableInformation(lex)
     */
    public void testQueryVariableInformationConstructor(){
        QueryVariableInformation v = new QueryVariableInformation();
        assertTrue(v.getVariables().length == 0);
        assertTrue(v.getSolution().length == 0);
    }

    /**
     * Test setValueFor(Identifier, Constant) and getValueFor(Identifier)
     */
    public void testSetAndGetValue(){
        try{
            Identifier var1 = new Identifier("A", 1);
            Identifier var2 = new Identifier("B", 1);
            Identifier var3 = new Identifier("C", 1);

            Constant const1 = new Constant("A", 1);
            Constant const2 = new Constant("B", 1);
            Constant const3 = new Constant("C", 1);
            
            QueryVariableInformation v = new QueryVariableInformation();
            assertTrue(v.getVariables().length == 0);

            try{
                v.setValueFor(null, const1);
                assertFalse(true);
            }catch(AssertionError e){
                assertTrue(true);
            };

            try{
                v.setValueFor(var1, null);
                assertFalse(true);
            }catch(AssertionError e){
                assertTrue(true);
            };

            try{
                v.setValueFor(null, null);
                assertFalse(true);
            }catch(AssertionError e){
                assertTrue(true);
            };

            v.setValueFor(var1, const1);
            assertTrue(v.getVariables().length == 1);
            Constant[] substitutions = v.getSolution();
            Identifier[] variables = v.getVariables();
            assertTrue(variables.length == 1);
            assertTrue(substitutions.length == 1);
            assertTrue(variables[0] == var1);
            assertTrue(substitutions[0] == const1);

            assertTrue(v.getValueFor(var2) == null);

            Iterator<Identifier> iter = v.getVariableIterator();
            assertTrue(iter.hasNext());
            assertTrue(iter.next() == var1);
            assertFalse(iter.hasNext());

            v.setValueFor(var1, const2);
            assertTrue(v.getVariables().length == 1);
            substitutions = v.getSolution();
            variables = v.getVariables();
            assertTrue(variables.length == 1);
            assertTrue(substitutions.length == 1);
            assertTrue(variables[0] == var1);
            assertTrue(substitutions[0] == const2);
            assertTrue(v.getValueFor(var1).equals(const2));

            assertTrue(v.getValueFor(var2) == null);

            try{
                v.getValueFor(null);
                assertFalse(true);
            }catch(AssertionError e){
                assertTrue(true);
            };

            v.setValueFor(var2, const2);
            assertTrue(v.getVariables().length == 2);
            substitutions = v.getSolution();
            variables = v.getVariables();
            assertTrue(variables.length == 2);
            assertTrue(substitutions.length == 2);
            assertTrue(variables[0] == var1 || variables[0] == var2);
            assertTrue(variables[1] == var1 || variables[1] == var2);
            assertTrue(substitutions[0] == const2);
            assertTrue(substitutions[1] == const2);
            assertTrue(v.getValueFor(var1) == const2);
            assertTrue(v.getValueFor(var2) == const2);

            HashSet<Identifier> expectedSet = new HashSet<Identifier>();
            expectedSet.add(var1);
            expectedSet.add(var2);
            HashSet<Identifier> vars = new HashSet<Identifier>();
            iter = v.getVariableIterator();
            while(iter.hasNext()){
                vars.add(iter.next());
            };
            assertTrue(vars.size() == expectedSet.size());
            boolean same = true;
            Iterator<Identifier> itr = expectedSet.iterator();
            while(itr.hasNext() && same){
                same = vars.contains(itr.next());
            };
            assertTrue(same);

            assertTrue(v.getValueFor(var3) == null);

            v.setValueFor(var2, const3);
            assertTrue(v.getVariables().length == 2);
            substitutions = v.getSolution();
            variables = v.getVariables();
            assertTrue(variables.length == 2);
            assertTrue(substitutions.length == 2);
            assertTrue(variables[0] == var1 || variables[0] == var2);
            assertTrue(variables[1] == var1 || variables[1] == var2);
            assertTrue(substitutions[0] == const2||substitutions[0] == const3);
            assertTrue(substitutions[1] == const2||substitutions[1] == const3);
            assertTrue(v.getValueFor(var1) == const2);
            assertTrue(v.getValueFor(var2) == const3);

            assertTrue(v.getValueFor(var3) == null);

            v.setValueFor(var1, const1);
            v.setValueFor(var2, const2);
            v.setValueFor(var3, const3);
            assertTrue(v.getVariables().length == 3);
            substitutions = v.getSolution();
            variables = v.getVariables();
            assertTrue(variables.length == 3);
            assertTrue(substitutions.length == 3);
            assertTrue(variables[0] == var1 ||
                       variables[0] == var2 ||
                       variables[0] == var3);
            assertTrue(variables[1] == var1 ||
                       variables[1] == var2 ||
                       variables[1] == var3);
            assertTrue(variables[2] == var1 ||
                       variables[2] == var2 ||
                       variables[2] == var3);
            assertTrue(substitutions[0] == const1 ||
                       substitutions[0] == const2 ||
                       substitutions[0] == const3);
            assertTrue(substitutions[1] == const1 ||
                       substitutions[1] == const2 ||
                       substitutions[1] == const3);
            assertTrue(substitutions[2] == const1 ||
                       substitutions[2] == const2 ||
                       substitutions[2] == const3);
            assertTrue(v.getValueFor(var1) == const1);
            assertTrue(v.getValueFor(var2) == const2);
            assertTrue(v.getValueFor(var3) == const3);

            expectedSet.add(var3);
            vars = new HashSet<Identifier>();
            iter = v.getVariableIterator();
            while(iter.hasNext()){
                vars.add(iter.next());
            };
            assertTrue(vars.size() == expectedSet.size());
            same = true;
            itr = expectedSet.iterator();
            while(itr.hasNext() && same){
                same = vars.contains(itr.next());
            };
            assertTrue(same);
            
        	System.out.print( "QueryVariableInformationTest " );
        	System.out.println( "PASSED" );
        	
        }catch(ParserException e){
             System.out.println(
                 "ERROR: in QueryVariableInformationTest::testSetAnd" +
                 "GetValue()\n" +
                 e.getMessage());
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

   /*
     * Uncomment this variable declaration and add any necessary initialization
     * arguments for it in the setUp() method.
     */

    /**
     * Utility main method. This will the test cases defined in this class.
     *
     * Usage: java QueryVariableInformationTest
     */
    public static void main (String[] args)
    {
        /* junit.textui.TestRunner will write the test results to stdout. */
        junit.textui.TestRunner.run (QueryVariableInformationTest.class);

        /* junit.swingui.TestRunner will display the test results in JUnit's
           swing interface. */
        //junit.swingui.TestRunner.run (QueryVariableInformationTest.class);
    }
}
