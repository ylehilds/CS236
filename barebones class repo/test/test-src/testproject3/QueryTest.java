package testproject3;

import answerParser.*;
import junit.framework.TestCase;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import project3.lex.*;
import project3.three.*;
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
         * should be qaced in the setUp() method instead.
         */
    }

    //=========================================================================
    //Test Constructors
    //=========================================================================



    /**
     * Test Lex constructor.
     */
    public void testLexConstructor(){
        try{
        	//System.out.print( "QueryTest.testLexConstructor " );
        	
        	Identifier A = new Identifier("A", 1);
            Identifier B = new Identifier("B", 1);
            Identifier C = new Identifier("C", 1);

            Identifier[] idSetA = {A};
            Identifier[] idSetAB = {A,B};
            Identifier[] idSetABC = {A,B,C};

            Location location00= new Location(0,0);
            Location location01= new Location(0,1);
            Location location02= new Location(0,2);
            Location location10= new Location(1,0);
            Location location11= new Location(1,1);
            Location location12= new Location(1,2);
            Location location20= new Location(2,0);
            Location location21= new Location(2,1);
            Location location22= new Location(2,2);

            ExtendedQuery q = createQuery("A('A')?");
            QueryVariableInformation vinfo = q.getVariableInformation();
            Identifier[] variables = vinfo.getVariables();
            assertTrue(variables.length == 0);

            q = createQuery("A(A)?");
            vinfo = q.getVariableInformation();
            assertTrue(sameAs(idSetA, vinfo.getVariables()));
            Location[] locs1 = {location00};
            checkLocations(vinfo.getLocationsFor(A), locs1);

            q = createQuery("A(A, B, C)?");
            vinfo = q.getVariableInformation();
            assertTrue(sameAs(idSetABC, vinfo.getVariables()));
            Location[] locs2 = {location00};
            checkLocations(vinfo.getLocationsFor(A), locs2);
            Location[] locs3 = {location01};
            checkLocations(vinfo.getLocationsFor(B), locs3);
            Location[] locs4 = {location02};
            checkLocations(vinfo.getLocationsFor(C), locs4);
            
        	//System.out.println( "PASSED" );
        	
        }catch(ParserException e){
             System.out.println(
                 "ERROR: in QueryTest::testLexConstructor()\n" +
                 e.getMessage());
        };
    }

    /**
     * Test toString.
     */
    public void testToString(){
        try{
        	//System.out.print( "QueryTest.testToString " );
        	
        	Identifier A = new Identifier("A", 1);
            Identifier B = new Identifier("B", 1);
            Identifier C = new Identifier("C", 1);

            Constant c1  = new Constant("1", 1);;
            Constant c2  = new Constant("2", 1);;

            ExtendedQuery q1 = createQuery("A('1')?");
            assertTrue(q1.toString().equals("A('1')? No\n"));

            StringReader sr = new StringReader("A(A)?");
            Lex lex = new Lex(sr);
            ExtendedQuery q2 = new ExtendedQuery(lex);

            q1 = createQuery("A(A)?");
            assertTrue(q1.toString().equals("A(A)? No\n"));
            assertTrue(q2.toString().equals("A(A)? No\n"));
            q1.setVariableToValue(A, c1);
            q2.setVariableToValue(A, c1);
            assertTrue(q1.toString().equals("A(A)? No\n"));
            assertTrue(q2.toString().equals("A('1')? No\n"));

            q1 = createQuery("A(A,B,C)?");
            sr = new StringReader("A(A,B,C)?");
            lex = new Lex(sr);
            q2 = new ExtendedQuery(lex);

            assertTrue(q1.toString().equals("A(A,B,C)? No\n"));
            assertTrue(q2.toString().equals("A(A,B,C)? No\n"));
            q1.setVariableToValue(A, c1);
            q2.setVariableToValue(A, c1);
            assertTrue(q1.toString().equals("A(A,B,C)? No\n"));
            assertTrue(q2.toString().equals("A('1',B,C)? No\n"));
            q1.setVariableToValue(B, c2);
            q2.setVariableToValue(B, c2);
            assertTrue(q1.toString().equals("A(A,B,C)? No\n"));
            assertTrue(q2.toString().equals("A('1','2',C)? No\n"));
            q1.setVariableToValue(C, c2);
            q2.setVariableToValue(C, c2);
            assertTrue(q1.toString().equals("A(A,B,C)? No\n"));
            assertTrue(q2.toString().equals("A('1','2','2')? No\n"));

            q1 = createQuery("A(A,B,A)?");
            sr = new StringReader("A(A,B,A)?");
            lex = new Lex(sr);
            q2 = new ExtendedQuery(lex);

            assertTrue(q1.toString().equals("A(A,B,A)? No\n"));
            assertTrue(q2.toString().equals("A(A,B,A)? No\n"));
            q1.setVariableToValue(A, c1);
            q2.setVariableToValue(A, c1);
            assertTrue(q1.toString().equals("A(A,B,A)? No\n"));
            assertTrue(q2.toString().equals("A('1',B,'1')? No\n"));

        	//System.out.println( "PASSED" );
            
        }catch(ParserException e){
             System.out.println(
                 "ERROR: in QueryTest::testSetVariableToValue(" +
                 "Identifier,Constant)\n" +
                 e.getMessage());
        };
    }


    /**
     * Test setVariableToValue(Identifier, Constant).
     */
    public void testSetVariableToValue(){
        try{
        	//System.out.print( "QueryTest.testSetVariableToValue " );
        	
            Identifier A = new Identifier("A", 1);
            Identifier B = new Identifier("B", 1);
            Identifier C = new Identifier("C", 1);

            Constant c1  = new Constant("1", 1);;
            Constant c2  = new Constant("2", 1);;

            ExtendedQuery q1 = createQuery("A('A')?");
            q1.setVariableToValue(A, c1);

            q1 = createQuery("A(A)?");
            assertTrue(q1.getPredicate(0).toString().equals("A(A)"));
            q1.setVariableToValue(B, c1);
            assertTrue(q1.getPredicate(0).toString().equals("A(A)"));
            q1.setVariableToValue(A, c1);
            assertTrue(q1.getPredicate(0).toString().equals("A('1')"));
            assertTrue(q1.getVariableInformation().getValueFor(A).equals(c1));

            q1 = createQuery("A(A,B,C)?");
            assertTrue(q1.getPredicate(0).toString().equals("A(A,B,C)"));
            q1.setVariableToValue(A, c1);
            q1.setVariableToValue(B, c2);
            assertTrue(q1.getPredicate(0).toString().equals(
                "A('1','2',C)"));
            q1.setVariableToValue(C, c2);
            assertTrue(q1.getPredicate(0).toString().equals(
                "A('1','2','2')"));
            assertTrue(q1.getVariableInformation().getValueFor(A).equals(c1));
            assertTrue(q1.getVariableInformation().getValueFor(B).equals(c2));
            assertTrue(q1.getVariableInformation().getValueFor(C).equals(c2));

            q1 = createQuery("A(A,B,A)?");
            assertTrue(q1.getPredicate(0).toString().equals("A(A,B,A)"));
            q1.setVariableToValue(A, c1);
            assertTrue(q1.getPredicate(0).toString().equals(
                "A('1',B,'1')"));
            assertTrue(q1.getVariableInformation().getValueFor(A).equals(c1));
            assertTrue(q1.getVariableInformation().getValueFor(B) == null);
            
        	//System.out.println( "PASSED" );
            
        }catch(ParserException e){
             System.out.println(
                 "ERROR: in QueryTest::testSetVariableToValue(" +
                 "Identifier,Constant)\n" +
                 e.getMessage());
        };
    }

    /**
     * Test evaluate() and recurse() for facts.
     */
    public void testEvaluateAndRecurseForFacts(){
        try{
        	//System.out.print( "QueryTest.testEvaluateAndRecurseForFacts " );
        	
            DatalogProgram dp;
            Query q;
            StringBuffer strBuffer;
            Answers ans1;
            Answers ans2;
            
            dp = createDatalogProgram(
                "Schemes: A(A)\n" +
                "Facts:\n" +
                "Rules:\n" +
                "Queries:\n" +
                "    A('1')?" 
              );
            Project3.datalogProgram = dp;
            q = dp.getQueryList().getQuery(0);
            strBuffer = new StringBuffer();
            q.evaluate(strBuffer);
            ans1 = new Answers(strBuffer.toString());
            ans2 = new Answers(
                "A('1')? No\n\n");
            assertTrue(ans1.equals(ans2));

            dp = createDatalogProgram(
                "Schemes: A(A)\n" +
                "Facts:\n" +
                "    B('1').\n" +
                "Rules:\n" +
                "Queries:\n" +
                "    A('1')?" 
              );
            Project3.datalogProgram = dp;
            q = dp.getQueryList().getQuery(0);
            strBuffer = new StringBuffer();
            q.evaluate(strBuffer);
            ans1 = new Answers(strBuffer.toString());
            ans2 = new Answers(
                "A('1')? No\n");
            assertTrue(ans1.equals(ans2));


            dp = createDatalogProgram(
                "Schemes: A(A)\n" +
                "Facts:\n" +
                "    A('1').\n" +
                "Rules:\n" +
                "Queries:\n" +
                "    A('1')?" 
              );
            Project3.datalogProgram = dp;
            q = dp.getQueryList().getQuery(0);
            strBuffer = new StringBuffer();
            q.evaluate(strBuffer);
            ans1 = new Answers(strBuffer.toString());
            ans2 = new Answers(
                "A('1')? Yes\n");
            assertTrue(ans1.equals(ans2));

            dp = createDatalogProgram(
                "Schemes: A(A)\n" +
                "Facts:\n" +
                "    A('2').\n" +
                "    A('3').\n" +
                "    A('1').\n" +
                "Rules:\n" +
                "Queries:\n" +
                "    A('1')?" 
              );
            Project3.datalogProgram = dp;
            q = dp.getQueryList().getQuery(0);
            strBuffer = new StringBuffer();
            q.evaluate(strBuffer);
            ans1 = new Answers(strBuffer.toString());
            ans2 = new Answers(
                "A('1')? Yes\n");
            assertTrue(ans1.equals(ans2));

            dp = createDatalogProgram(
                "Schemes: A(A)\n" +
                "Facts:\n" +
                "    A('3').\n" +
                "    A('1').\n" +
                "    A('2').\n" +
                "Rules:\n" +
                "Queries:\n" +
                "    A('1')?" 
              );
            Project3.datalogProgram = dp;
            q = dp.getQueryList().getQuery(0);
            strBuffer = new StringBuffer();
            q.evaluate(strBuffer);
            ans1 = new Answers(strBuffer.toString());
            ans2 = new Answers(
                "A('1')? Yes\n");
            assertTrue(ans1.equals(ans2));

            dp = createDatalogProgram(
                "Schemes: A(A)\n" +
                "Facts:\n" +
                "    A('1').\n" +
                "    A('3').\n" +
                "    A('2').\n" +
                "Rules:\n" +
                "Queries:\n" +
                "    A('1')?" 
              );
            Project3.datalogProgram = dp;
            q = dp.getQueryList().getQuery(0);
            strBuffer = new StringBuffer();
            q.evaluate(strBuffer);
            ans1 = new Answers(strBuffer.toString());
            ans2 = new Answers(
                "A('1')? Yes\n");
            assertTrue(ans1.equals(ans2));

            dp = createDatalogProgram(
                "Schemes: A(A)\n" +
                "Facts:\n" +
                "    C('1').\n" +
                "    D('1').\n" +
                "    B('1').\n" +
                "    A('1').\n" +
                "Rules:\n" +
                "Queries:\n" +
                "    A('1')?" 
              );
            Project3.datalogProgram = dp;
            q = dp.getQueryList().getQuery(0);
            strBuffer = new StringBuffer();
            q.evaluate(strBuffer);
            ans1 = new Answers(strBuffer.toString());
            ans2 = new Answers(
                "A('1')? Yes\n");
            assertTrue(ans1.equals(ans2));

            dp = createDatalogProgram(
                "Schemes: A(A)\n" +
                "Facts:\n" +
                "    A('1').\n" +
                "Rules:\n" +
                "Queries:\n" +
                "    A(A)?" 
              );
            Project3.datalogProgram = dp;
            q = dp.getQueryList().getQuery(0);
            strBuffer = new StringBuffer();
            q.evaluate(strBuffer);
            ans1 = new Answers(strBuffer.toString());
            ans2 = new Answers(
              "A(A)? Yes\n  A = '1'\n\n");
            assertTrue(ans1.equals(ans2));

            dp = createDatalogProgram(
                "Schemes: A(A)\n" +
                "Facts:\n" +
                "    A('1').\n" +
                "    A('2').\n" +
                "    A('3').\n" +
                "    B('3').\n" +
                "    C('2').\n" +
                "Rules:\n" +
                "Queries:\n" +
                "    A(A)?" 
              );
            Project3.datalogProgram = dp;
            q = dp.getQueryList().getQuery(0);
            strBuffer = new StringBuffer();
            q.evaluate(strBuffer);
            ans1 = new Answers(strBuffer.toString());
            ans2 = new Answers(
              "A(A)? Yes\n  A = '3'\n  A = '1'\n  A = '2'\n");
            assertTrue(ans1.equals(ans2));

            dp = createDatalogProgram(
                "Schemes: A(X,Y)\n" +
                "Facts:\n" +
                "    A('1','2').\n" +
                "    A('2','2').\n" +
                "    A('3','3').\n" +
                "    B('3').\n" +
                "    C('2').\n" +
                "Rules:\n" +
                "Queries:\n" +
                "    A(A,B)?" 
              );

            Project3.datalogProgram = dp;
            q = dp.getQueryList().getQuery(0);
            strBuffer = new StringBuffer();
            q.evaluate(strBuffer);
            ans1 = new Answers(strBuffer.toString());
            ans2 = new Answers(
              "A(A,B)? Yes\n" +
              "  B = '3', A = '3'\n" +
              "  B = '2', A = '1'\n" +
              "  B = '2', A = '2'\n\n");
            assertTrue(ans1.equals(ans2));

            dp = createDatalogProgram(
                "Schemes: A(X,Y)\n" +
                "Facts:\n" +
                "    A('1','2').\n" +
                "    A('2','2').\n" +
                "    A('3','3').\n" +
                "    B('3').\n" +
                "    C('2').\n" +
                "Rules:\n" +
                "Queries:\n" +
                "    A(A,'2')?" 
              );
            Project3.datalogProgram = dp;
            q = dp.getQueryList().getQuery(0);
            strBuffer = new StringBuffer();
            q.evaluate(strBuffer);
            ans1 = new Answers(strBuffer.toString());
            ans2 = new Answers(
              "A(A,'2')? Yes\n" +
              "  A = '1'\n" +
              "  A = '2'\n\n");
            assertTrue(ans1.equals(ans2));

            dp = createDatalogProgram(
                "Schemes: A(X,Y)\n" +
                "Facts:\n" +
                "    A('1','2').\n" +
                "    A('2','2').\n" +
                "    A('3','3').\n" +
                "    B('3').\n" +
                "    C('2').\n" +
                "Rules:\n" +
                "Queries:\n" +
                "    A('1','2')?" 
              );
            Project3.datalogProgram = dp;
            q = dp.getQueryList().getQuery(0);
            strBuffer = new StringBuffer();
            q.evaluate(strBuffer);
            ans1 = new Answers(strBuffer.toString());
            ans2 = new Answers(
              "A('1','2')? Yes\n\n");
            assertTrue(ans1.equals(ans2));
            //Testing Rules

            dp = createDatalogProgram(
                "Schemes: A(X,Y)\n" +
                "Facts:\n" +
                "    A('1','2').\n" +
                "    A('2','2').\n" +
                "    A('3','3').\n" +
                "    B('3').\n" +
                "    C('2').\n" +
                "Rules:\n" +
                "Queries:\n" +
                "    A('1','2')?" 
              );
            Project3.datalogProgram = dp;
            q = dp.getQueryList().getQuery(0);
            strBuffer = new StringBuffer();
            q.evaluate(strBuffer);
            ans1 = new Answers(strBuffer.toString());
            ans2 = new Answers(
              "A('1','2')? Yes\n\n");
            assertTrue(ans1.equals(ans2));
            
        	//System.out.println( "PASSED" );
            
        }catch(ParserException e){
             System.out.println(
                 "ParserException ERROR: in QueryTest::" +
                 "testEvaluateAndRecurseForFacts()\n" +
                 e.getMessage());
        }catch(Exception e){
             System.out.println(
                 "Exception ERROR: in " +
                 "QueryTest::testEvaluateAndRecurseForFacts()\n" +
                 e.getMessage());
        };
    }


    /**
     * Test evaluate() and recurse() for rules.
     */
    public void testEvaluateAndRecurseForRules(){
    	System.out.println( "QueryTest (26 parts)" );
        try{
            DatalogProgram dp;
            Query q;
            StringBuffer strBuffer;
            Answers ans1;
            Answers ans2;

            
        	System.out.print( "\t1 " );
            dp = createDatalogProgram(
                "Schemes: A(X,Y)\n" +
                "         B(X)\n"   +
                "         C(X)\n"   +
                "         E(X)\n"   +
                "Facts:\n" +
                "    A('1','2','3').\n" +
                "    A('2','2').\n" +
                "    A('3','3').\n" +
                "    B('1').\n" +
                "    B('2').\n" +
                "    B('3').\n" +
                "    C('1').\n" +
                "    C('2').\n" +
                "    C('3').\n" +
                "Rules:\n" +
                "    E(X) :- B(X).\n" +
                "Queries:\n" +
                "    R('1')?" 
              );
            Project3.datalogProgram = dp;
            q = dp.getQueryList().getQuery(0);
            strBuffer = new StringBuffer();
            q.evaluate(strBuffer);
            ans1 = new Answers(strBuffer.toString());
            ans2 = new Answers(
              "R('1')? No\n\n");
            assertTrue(ans1.equals(ans2));
        	System.out.println( "PASSED" );
            
            
        	System.out.print( "\t2 ");
            dp = createDatalogProgram(
                "Schemes: A(X,Y)\n" +
                "         B(X)\n"   +
                "         C(X)\n"   +
                "         E(X)\n"   +
                "Facts:\n" +
                "    A('1','2','3').\n" +
                "    A('2','2').\n" +
                "    A('3','3').\n" +
                "    B('1').\n" +
                "    B('2').\n" +
                "    B('3').\n" +
                "    C('1').\n" +
                "    C('2').\n" +
                "    C('3').\n" +
                "Rules:\n" +
                "    E(X) :- R(X).\n" +
                "Queries:\n" +
                "    R(A)?" 
              );
            Project3.datalogProgram = dp;
            q = dp.getQueryList().getQuery(0);
            strBuffer = new StringBuffer();
            q.evaluate(strBuffer);
            ans1 = new Answers(strBuffer.toString());
            ans2 = new Answers(
              "R(A)? No\n\n");
            assertTrue(ans1.equals(ans2));
        	System.out.println( "PASSED" );
            
        	System.out.print( "\t3 ");
            dp = createDatalogProgram(
                "Schemes: A(X,Y)\n" +
                "         B(X)\n"   +
                "         C(X)\n"   +
                "         E(X)\n"   +
                "Facts:\n" +
                "    A('1','2','3').\n" +
                "    A('2','2').\n" +
                "    A('3','3').\n" +
                "    B('1').\n" +
                "    B('2').\n" +
                "    B('3').\n" +
                "    C('1').\n" +
                "    C('2').\n" +
                "    C('3').\n" +
                "Rules:\n" +
                "    R(X) :- E(X).\n" +
                "Queries:\n" +
                "    R('1')?" 
              );
            Project3.datalogProgram = dp;
            q = dp.getQueryList().getQuery(0);
            strBuffer = new StringBuffer();
            q.evaluate(strBuffer);
            ans1 = new Answers(strBuffer.toString());
            ans2 = new Answers(
              "R('1')? No\n\n");
            assertTrue(ans1.equals(ans2));
        	System.out.println( "PASSED" );
            
        	System.out.print( "\t4 ");
            dp = createDatalogProgram(
                "Schemes: A(X,Y)\n" +
                "         B(X)\n"   +
                "         C(X)\n"   +
                "         E(X)\n"   +
                "Facts:\n" +
                "    A('1','2','3').\n" +
                "    A('2','2').\n" +
                "    A('3','3').\n" +
                "    B('1').\n" +
                "    B('2').\n" +
                "    B('3').\n" +
                "    C('1').\n" +
                "    C('2').\n" +
                "    C('3').\n" +
                "Rules:\n" +
                "    R(X) :- E(X).\n" +
                "Queries:\n" +
                "    R(X)?" 
              );
            Project3.datalogProgram = dp;
            q = dp.getQueryList().getQuery(0);
            strBuffer = new StringBuffer();
            q.evaluate(strBuffer);
            ans1 = new Answers(strBuffer.toString());
            ans2 = new Answers(
              "R(X)? No\n\n");
            assertTrue(ans1.equals(ans2));
            System.out.println( "PASSED" );
            
        	System.out.print( "\t5 ");
            dp = createDatalogProgram(
                "Schemes: A(X,Y)\n" +
                "         B(X)\n"   +
                "         C(X)\n"   +
                "         E(X)\n"   +
                "Facts:\n" +
                "    A('1','2','3').\n" +
                "    A('2','2').\n" +
                "    A('3','3').\n" +
                "    B('1').\n" +
                "    B('2').\n" +
                "    B('3').\n" +
                "    C('1').\n" +
                "    C('2').\n" +
                "    C('3').\n" +
                "Rules:\n" +
                "    R(X) :- B(X).\n" +
                "Queries:\n" +
                "    R('1')?" 
              );
            Project3.datalogProgram = dp;
            q = dp.getQueryList().getQuery(0);
            strBuffer = new StringBuffer();
            q.evaluate(strBuffer);
            ans1 = new Answers(strBuffer.toString());
            ans2 = new Answers(
              "R('1')? Yes\n\n");
            assertTrue(ans1.equals(ans2));
            System.out.println( "PASSED" );

        	System.out.print( "\t6 ");
            dp = createDatalogProgram(
                "Schemes: A(X,Y)\n" +
                "         B(X)\n"   +
                "         C(X)\n"   +
                "         E(X)\n"   +
                "Facts:\n" +
                "    A('1','2','3').\n" +
                "    A('2','2').\n" +
                "    A('3','3').\n" +
                "    B('1').\n" +
                "    B('2').\n" +
                "    B('3').\n" +
                "    C('1').\n" +
                "    C('2').\n" +
                "    C('3').\n" +
                "Rules:\n" +
                "    R(X) :- B(X).\n" +
                "Queries:\n" +
                "    R('0')?" 
              );
            Project3.datalogProgram = dp;
            q = dp.getQueryList().getQuery(0);
            strBuffer = new StringBuffer();
            q.evaluate(strBuffer);
            ans1 = new Answers(strBuffer.toString());
            ans2 = new Answers(
              "R('0')? No\n\n");
            assertTrue(ans1.equals(ans2));
            System.out.println( "PASSED" );

        	System.out.print( "\t7 ");
            dp = createDatalogProgram(
                "Schemes: A(X,Y)\n" +
                "         B(X)\n"   +
                "         C(X)\n"   +
                "         E(X)\n"   +
                "         R(X)\n"   +
                "Facts:\n" +
                "    A('1','2','3').\n" +
                "    A('2','2').\n" +
                "    A('3','3').\n" +
                "    B('1').\n" +
                "    B('2').\n" +
                "    B('3').\n" +
                "    C('1').\n" +
                "    C('2').\n" +
                "    C('3').\n" +
                "Rules:\n" +
                "    R(X) :- B(X).\n" +
                "Queries:\n" +
                "    R(A)?" 
              );
            Project3.datalogProgram = dp;
            q = dp.getQueryList().getQuery(0);
            strBuffer = new StringBuffer();
            q.evaluate(strBuffer);
            ans1 = new Answers(strBuffer.toString());
            ans2 = new Answers(
              "R(A)? Yes\n" +
              "  A = '3'\n" +
              "  A = '1'\n" +
              "  A = '2'\n\n");
            assertTrue(ans1.equals(ans2));
            System.out.println( "PASSED" );

        	System.out.print( "\t8 ");
            dp = createDatalogProgram(
                "Schemes: A(X,Y)\n" +
                "         B(X)\n"   +
                "         C(X)\n"   +
                "         E(X)\n"   +
                "         R(X)\n"   +
                "Facts:\n" +
                "    A('1','2','3').\n" +
                "    A('2','2').\n" +
                "    A('3','3').\n" +
                "    B('1').\n" +
                "    B('2').\n" +
                "    B('3').\n" +
                "    C('1').\n" +
                "    C('2').\n" +
                "    C('3').\n" +
                "Rules:\n" +
                "    R(X) :- B(X),C(X).\n" +
                "Queries:\n" +
                "    R('a')?" 
              );
            Project3.datalogProgram = dp;
            q = dp.getQueryList().getQuery(0);
            strBuffer = new StringBuffer();
            q.evaluate(strBuffer);
            ans1 = new Answers(strBuffer.toString());
            ans2 = new Answers(
              "R('a')? No\n\n"); 
            assertTrue(ans1.equals(ans2));
            System.out.println( "PASSED" );

        	System.out.print( "\t9 ");
            dp = createDatalogProgram(
                "Schemes: A(X,Y)\n" +
                "         B(X)\n"   +
                "         C(X)\n"   +
                "         E(X)\n"   +
                "         R(X)\n"   +
                "Facts:\n" +
                "    A('1','2','3').\n" +
                "    A('2','2').\n" +
                "    A('3','3').\n" +
                "    B('1').\n" +
                "    B('2').\n" +
                "    B('3').\n" +
                "    C('1').\n" +
                "    C('2').\n" +
                "    C('3').\n" +
                "Rules:\n" +
                "    R(X) :- B(X),C(X).\n" +
                "Queries:\n" +
                "    R('2')?" 
              );
            Project3.datalogProgram = dp;
            q = dp.getQueryList().getQuery(0);
            strBuffer = new StringBuffer();
            q.evaluate(strBuffer);
            ans1 = new Answers(strBuffer.toString());
            ans2 = new Answers(
              "R('2')? Yes\n\n"); 
            assertTrue(ans1.equals(ans2));
            System.out.println( "PASSED" );

        	System.out.print( "\t10 ");
            dp = createDatalogProgram(
                "Schemes: A(X,Y)\n" +
                "         B(X)\n"   +
                "         C(X)\n"   +
                "         E(X)\n"   +
                "         R(X)\n"   +
                "Facts:\n" +
                "    A('1','2','3').\n" +
                "    A('2','2').\n" +
                "    A('3','3').\n" +
                "    B('1').\n" +
                "    B('2').\n" +
                "    B('3').\n" +
                "    C('1').\n" +
                "    C('2').\n" +
                "    C('3').\n" +
                "Rules:\n" +
                "    R(X) :- B(X),E(X).\n" +
                "Queries:\n" +
                "    R('2')?" 
              );
            Project3.datalogProgram = dp;
            q = dp.getQueryList().getQuery(0);
            strBuffer = new StringBuffer();
            q.evaluate(strBuffer);
            ans1 = new Answers(strBuffer.toString());
            ans2 = new Answers(
              "R('2')? No\n\n"); 
            assertTrue(ans1.equals(ans2));
            System.out.println( "PASSED" );

        	System.out.print( "\t11 ");
            dp = createDatalogProgram(
                "Schemes: A(X,Y)\n" +
                "         B(X)\n"   +
                "         C(X)\n"   +
                "         E(X)\n"   +
                "         R(X)\n"   +
                "Facts:\n" +
                "    A('1','2','3').\n" +
                "    A('2','2').\n" +
                "    A('3','3').\n" +
                "    B('1').\n" +
                "    B('2').\n" +
                "    B('3').\n" +
                "    C('1').\n" +
                "    C('2').\n" +
                "    C('3').\n" +
                "Rules:\n" +
                "    R(X) :- E(X),B(X).\n" +
                "Queries:\n" +
                "    R('2')?" 
              );
            Project3.datalogProgram = dp;
            q = dp.getQueryList().getQuery(0);
            strBuffer = new StringBuffer();
            q.evaluate(strBuffer);
            ans1 = new Answers(strBuffer.toString());
            ans2 = new Answers(
              "R('2')? No\n\n"); 
            assertTrue(ans1.equals(ans2));
            System.out.println( "PASSED" );

        	System.out.print( "\t12 ");
            dp = createDatalogProgram(
                "Schemes: A(X,Y)\n" +
                "         B(X)\n"   +
                "         C(X)\n"   +
                "         E(X)\n"   +
                "         R(X)\n"   +
                "Facts:\n" +
                "    A('1','2').\n" +
                "    A('2','2').\n" +
                "    A('3','3').\n" +
                "    B('1').\n" +
                "    B('2').\n" +
                "    B('3').\n" +
                "    C('1').\n" +
                "    C('2').\n" +
                "    C('3').\n" +
                "Rules:\n" +
                "    R(X) :- B(X),C(X).\n" +
                "Queries:\n" +
                "    R(A)?" 
              );
            Project3.datalogProgram = dp;
            q = dp.getQueryList().getQuery(0);
            strBuffer = new StringBuffer();
            q.evaluate(strBuffer);
            ans1 = new Answers(strBuffer.toString());
            ans2 = new Answers(
              "R(A)? Yes\n" +
              "  A = '3'\n" +
              "  A = '1'\n" +
              "  A = '2'\n\n");
            assertTrue(ans1.equals(ans2));
            System.out.println( "PASSED" );
            
        	System.out.print( "\t13 ");
            dp = createDatalogProgram(
                "Schemes: A(X,Y)\n" +
                "         B(X)\n"   +
                "         C(X)\n"   +
                "         E(X)\n"   +
                "         R(X)\n"   +
                "Facts:\n" +
                "    A('1','2').\n" +
                "    A('2','2').\n" +
                "    A('3','3').\n" +
                "    B('1').\n" +
                "    B('2').\n" +
                "    B('3').\n" +
                "    C('1').\n" +
                "    C('2').\n" +
                "    C('3').\n" +
                "Rules:\n" +
                "    R(X) :- B(X),E(X).\n" +
                "Queries:\n" +
                "    R(A)?" 
              );
            Project3.datalogProgram = dp;
            q = dp.getQueryList().getQuery(0);
            strBuffer = new StringBuffer();
            q.evaluate(strBuffer);
            ans1 = new Answers(strBuffer.toString());
            ans2 = new Answers(
              "R(A)? No\n\n");
            assertTrue(ans1.equals(ans2));
            System.out.println( "PASSED" );

        	System.out.print( "\t14 ");
            dp = createDatalogProgram(
                "Schemes: A(X,Y)\n" +
                "         B(X)\n"   +
                "         C(X)\n"   +
                "         E(X)\n"   +
                "         R(X)\n"   +
                "Facts:\n" +
                "    A('1','2').\n" +
                "    A('2','2').\n" +
                "    A('3','3').\n" +
                "    B('1').\n" +
                "    B('2').\n" +
                "    B('3').\n" +
                "    C('1').\n" +
                "    C('2').\n" +
                "    C('3').\n" +
                "Rules:\n" +
                "    R(X) :- E(X),B(X).\n" +
                "Queries:\n" +
                "    R(A)?" 
              );
            Project3.datalogProgram = dp;
            q = dp.getQueryList().getQuery(0);
            strBuffer = new StringBuffer();
            q.evaluate(strBuffer);
            ans1 = new Answers(strBuffer.toString());
            ans2 = new Answers(
              "R(A)? No\n\n");
            assertTrue(ans1.equals(ans2));
            System.out.println( "PASSED" );

        	System.out.print( "\t15 ");
            dp = createDatalogProgram(
                "Schemes: A(X,Y)\n" +
                "         B(X)\n"   +
                "         C(X)\n"   +
                "         E(X)\n"   +
                "         R(X)\n"   +
                "Facts:\n" +
                "    A('1','2').\n" +
                "    A('2','2').\n" +
                "    A('3','3').\n" +
                "    B('1').\n" +
                "    B('2').\n" +
                "    B('3').\n" +
                "    C('2').\n" +
                "    C('3').\n" +
                "    C('4').\n" +
                "Rules:\n" +
                "    R(X) :- B(X).\n" +
                "    R(X) :- C(X).\n" +
                "Queries:\n" +
                "    R('4')?" 
              );
            Project3.datalogProgram = dp;
            q = dp.getQueryList().getQuery(0);
            strBuffer = new StringBuffer();
            q.evaluate(strBuffer);
            ans1 = new Answers(strBuffer.toString());
            ans2 = new Answers(
              "R('4')? Yes\n\n");
            assertTrue(ans1.equals(ans2));
            System.out.println( "PASSED" );

        	System.out.print( "\t16 ");
            dp = createDatalogProgram(
                "Schemes: A(X,Y)\n" +
                "         B(X)\n"   +
                "         C(X)\n"   +
                "         E(X)\n"   +
                "         R(X)\n"   +
                "Facts:\n" +
                "    A('1','2').\n" +
                "    A('2','2').\n" +
                "    A('3','3').\n" +
                "    B('1').\n" +
                "    B('2').\n" +
                "    B('3').\n" +
                "    C('2').\n" +
                "    C('3').\n" +
                "    C('4').\n" +
                "Rules:\n" +
                "    R(X) :- B(X).\n" +
                "    R(X) :- C(X).\n" +
                "Queries:\n" +
                "    R(X)?" 
              );
            Project3.datalogProgram = dp;
            q = dp.getQueryList().getQuery(0);
            strBuffer = new StringBuffer();
            q.evaluate(strBuffer);
            ans1 = new Answers(strBuffer.toString());
            ans2 = new Answers(
              "R(X)? Yes\n" +
              "  X = '4'\n"+
              "  X = '3'\n"+
              "  X = '1'\n"+
              "  X = '2'\n\n");
            assertTrue(ans1.equals(ans2));
            System.out.println( "PASSED" );

        	System.out.print( "\t17 ");
            dp = createDatalogProgram(
                "Schemes: A(X,Y)\n" +
                "         B(X)\n"   +
                "         C(X)\n"   +
                "         E(X)\n"   +
                "         R(X)\n"   +
                "Facts:\n" +
                "    A('1','2').\n" +
                "    A('2','2').\n" +
                "    A('3','3').\n" +
                "    B('1').\n" +
                "    B('2').\n" +
                "    B('3').\n" +
                "    C('2').\n" +
                "    C('3').\n" +
                "    C('4').\n" +
                "Rules:\n" +
                "    R(X) :- A(X,Y).\n" +
                "Queries:\n" +
                "    R(X)?" 
              );
            Project3.datalogProgram = dp;
            q = dp.getQueryList().getQuery(0);
            strBuffer = new StringBuffer();
            q.evaluate(strBuffer);
            ans1 = new Answers(strBuffer.toString());
            ans2 = new Answers(
              "R(X)? Yes\n" +
              "  X = '3'\n"+
              "  X = '1'\n"+
              "  X = '2'\n\n");
            assertTrue(ans1.equals(ans2));
            System.out.println( "PASSED" );

        	System.out.print( "\t18 ");
            dp = createDatalogProgram(
                "Schemes: A(X,Y)\n" +
                "         B(X)\n"   +
                "         C(X)\n"   +
                "         E(X)\n"   +
                "         R(X)\n"   +
                "Facts:\n" +
                "    A('1','2').\n" +
                "    A('2','2').\n" +
                "    A('3','3').\n" +
                "    B('1').\n" +
                "    B('2').\n" +
                "    B('3').\n" +
                "    C('2').\n" +
                "    C('3').\n" +
                "    C('4').\n" +
                "Rules:\n" +
                "    R(X) :- A(X,X).\n" +
                "Queries:\n" +
                "    R(X)?" 
              );
            Project3.datalogProgram = dp;
            q = dp.getQueryList().getQuery(0);
            strBuffer = new StringBuffer();
            q.evaluate(strBuffer);
            ans1 = new Answers(strBuffer.toString());
            ans2 = new Answers(
              "R(X)? Yes\n" +
              "  X = '3'\n" +
              "  X = '2'\n\n");
            assertTrue(ans1.equals(ans2));
            System.out.println( "PASSED" );

        	System.out.print( "\t19 ");
            dp = createDatalogProgram(
                "Schemes: A(X,Y)\n" +
                "         B(X)\n"   +
                "         C(X)\n"   +
                "         E(X)\n"   +
                "         R(X)\n"   +
                "Facts:\n" +
                "    A('1','2').\n" +
                "    A('2','2').\n" +
                "    A('3','3').\n" +
                "    B('1').\n" +
                "    B('2').\n" +
                "    B('3').\n" +
                "    C('3').\n" +
                "    C('4').\n" +
                "    C('5').\n" +
                "Rules:\n" +
                "    R(X) :- A(X,X),C(X).\n" +
                "Queries:\n" +
                "    R(X)?" 
              );
            Project3.datalogProgram = dp;
            q = dp.getQueryList().getQuery(0);
            strBuffer = new StringBuffer();
            q.evaluate(strBuffer);
            ans1 = new Answers(strBuffer.toString());
            ans2 = new Answers(
              "R(X)? Yes\n" +
              "  X = '3'\n\n");
            assertTrue(ans1.equals(ans2));
            System.out.println( "PASSED" );

        	System.out.print( "\t20 ");
            dp = createDatalogProgram(
                "Schemes: A(X,Y)\n" +
                "         B(X)\n"   +
                "         C(X)\n"   +
                "         E(X)\n"   +
                "         R(X)\n"   +
                "Facts:\n" +
                "    A('1','2').\n" +
                "    A('2','2').\n" +
                "    A('3','3').\n" +
                "    B('1').\n" +
                "    B('2').\n" +
                "    B('3').\n" +
                "    C('2').\n" +
                "    C('3').\n" +
                "    C('4').\n" +
                "Rules:\n" +
                "    R(X) :- A(X,Y),C(Y).\n" +
                "Queries:\n" +
                "    R(X)?" 
              );
            Project3.datalogProgram = dp;
            q = dp.getQueryList().getQuery(0);
            strBuffer = new StringBuffer();
            q.evaluate(strBuffer);
            ans1 = new Answers(strBuffer.toString());
            ans2 = new Answers(
              "R(X)? Yes\n" +
              "  X = '3'\n"+
              "  X = '1'\n"+
              "  X = '2'\n\n");
            assertTrue(ans1.equals(ans2));
            System.out.println( "PASSED" );

        	System.out.print( "\t21 ");
            dp = createDatalogProgram(
                "Schemes: A(X,Y)\n" +
                "         B(X)\n"   +
                "         C(X)\n"   +
                "         E(X)\n"   +
                "         R(X)\n"   +
                "Facts:\n" +
                "    A('1','2').\n" +
                "    A('2','2').\n" +
                "    A('3','3').\n" +
                "    B('1').\n" +
                "    B('2').\n" +
                "    B('3').\n" +
                "    C('2').\n" +
                "    C('3').\n" +
                "    C('4').\n" +
                "Rules:\n" +
                "    R(X) :- A(Y,X),C(Y).\n" +
                "Queries:\n" +
                "    R(X)?" 
              );
            Project3.datalogProgram = dp;
            q = dp.getQueryList().getQuery(0);
            strBuffer = new StringBuffer();
            q.evaluate(strBuffer);
            ans1 = new Answers(strBuffer.toString());
            ans2 = new Answers(
              "R(X)? Yes\n" +
              "  X = '3'\n"+
              "  X = '2'\n\n");
            assertTrue(ans1.equals(ans2));
            System.out.println( "PASSED" );

        	System.out.print( "\t22 ");
            dp = createDatalogProgram(
                "Schemes: A(X,Y)\n" +
                "         B(X)\n"   +
                "         C(X)\n"   +
                "         E(X)\n"   +
                "         R(X)\n"   +
                "Facts:\n" +
                "    A('1','2').\n" +
                "    A('2','2').\n" +
                "    A('3','3').\n" +
                "    B('1').\n" +
                "    B('2').\n" +
                "    B('3').\n" +
                "    C('2').\n" +
                "    C('3').\n" +
                "    C('4').\n" +
                "Rules:\n" +
                "    R(X) :- A(Y,X),C(Y).\n" +
                "    R(X) :- B(X), C(X).\n" +
                "Queries:\n" +
                "    R(X)?" 
              );
            Project3.datalogProgram = dp;
            q = dp.getQueryList().getQuery(0);
            strBuffer = new StringBuffer();
            q.evaluate(strBuffer);
            ans1 = new Answers(strBuffer.toString());
            ans2 = new Answers(
              "R(X)? Yes\n" +
              "  X = '3'\n"+
              "  X = '2'\n\n");
            assertTrue(ans1.equals(ans2));
            System.out.println( "PASSED" );

        	System.out.print( "\t23 ");
            dp = createDatalogProgram(
                "Schemes: A(X,Y)\n" +
                "         B(X)\n"   +
                "         C(X)\n"   +
                "         E(X)\n"   +
                "         R(X,Y)\n"   +
                "Facts:\n" +
                "    A('1','2').\n" +
                "    A('2','2').\n" +
                "    A('3','3').\n" +
                "    B('1').\n" +
                "    B('2').\n" +
                "    B('3').\n" +
                "    C('2').\n" +
                "    C('3').\n" +
                "    C('4').\n" +
                "Rules:\n" +
                "    R(X,Y) :- A(X,Y).\n" +
                "    A(A,B) :- A(B,A).\n" +
                "Queries:\n" +
                "    R(X,Y)?" 
              );
            Project3.datalogProgram = dp;
            q = dp.getQueryList().getQuery(0);
            strBuffer = new StringBuffer();
            q.evaluate(strBuffer);
            ans1 = new Answers(strBuffer.toString());
            ans2 = new Answers(
              "R(X,Y)? Yes\n" +
              "  X = '3', Y = '3'\n"+
              "  X = '1', Y = '2'\n"+
              "  X = '2', Y = '1'\n"+
              "  X = '2', Y = '2'\n\n");
            assertTrue(ans1.equals(ans2));
            System.out.println( "PASSED" );

        	System.out.print( "\t24 ");
            dp = createDatalogProgram(
                "Schemes: Parent(X,Y)\n" +
                "         Ancestor(X,Y)\n"   +
                "Facts:\n" +
                "    Parent('1','2').\n" +
                "    Parent('1','3').\n" +
                "    Parent('2','4').\n" +
                "    Parent('2','5').\n" +
                "    Parent('5','6').\n" + 
                "Rules:\n" +
                "    Ancestor(X,Y) :- Parent(X,Y).\n" +
                "    Ancestor(A,B) :- Ancestor(A,C),Parent(C,B).\n" +
                "Queries:\n" +
                "    Ancestor('1',Y)?" 
              );
            Project3.datalogProgram = dp;
            q = dp.getQueryList().getQuery(0);
            strBuffer = new StringBuffer();
            q.evaluate(strBuffer);
            ans1 = new Answers(strBuffer.toString());
            ans2 = new Answers(
              "Ancestor('1',Y)? Yes\n" +
              "  Y = '4'\n"+
              "  Y = '6'\n"+
              "  Y = '5'\n"+
              "  Y = '3'\n"+
              "  Y = '2'\n\n");
            assertTrue(ans1.equals(ans2));
            System.out.println( "PASSED" );

        	System.out.print( "\t25 ");
            dp = createDatalogProgram(
                "Schemes: Parent(X,Y)\n" +
                "         Ancestor(X,Y)\n"   +
                "Facts:\n" +
                "    Parent('1','2').\n" +
                "    Parent('7','2').\n" +
                "    Parent('8','2').\n" +
                "    Parent('1','3').\n" +
                "    Parent('2','4').\n" +
                "    Parent('2','5').\n" +
                "    Parent('9','5').\n" +
                "    Parent('5','6').\n" + 
                "    Parent('3','10').\n" + 
                "Rules:\n" +
                "    Ancestor(X,Y) :- Parent(X,Y).\n" +
                "    Ancestor(A,B) :- Ancestor(A,C),Parent(C,B).\n" +
                "Queries:\n" +
                "    Ancestor(X,'6')?" 
              );
            Project3.datalogProgram = dp;
            q = dp.getQueryList().getQuery(0);
            strBuffer = new StringBuffer();
            q.evaluate(strBuffer);
            ans1 = new Answers(strBuffer.toString());
            ans2 = new Answers(
              "Ancestor(X,'6')? Yes\n" +
              "  X = '5'\n"+
              "  X = '8'\n"+
              "  X = '1'\n"+
              "  X = '7'\n"+
              "  X = '2'\n"+
              "  X = '9'\n\n");
            assertTrue(ans1.equals(ans2));
            System.out.println( "PASSED" );

        	System.out.print( "\t26 ");
            dp = createDatalogProgram(
                "Schemes: Parent(X,Y)\n" +
                "         Ancestor(X,Y)\n"   +
                "Facts:\n" +
                "    Parent('1','2').\n" +
                "    Parent('1','3').\n" +
                "    Parent('2','4').\n" +
                "    Parent('2','5').\n" +
                "    Parent('5','6').\n" + 
                "Rules:\n" +
                "    Ancestor(X,Y) :- Parent(X,Y).\n" +
                "    Ancestor(A,B) :- Ancestor(A,C),Parent(C,B).\n" +
                "Queries:\n" +
                "    Ancestor(X,Y)?" 
              );
            Project3.datalogProgram = dp;
            q = dp.getQueryList().getQuery(0);
            strBuffer = new StringBuffer();
            q.evaluate(strBuffer);
            ans1 = new Answers(strBuffer.toString());
            ans2 = new Answers(
              "Ancestor(X,Y)? Yes\n" +
              "  X = '5', Y = '6'\n"+
              "  X = '1', Y = '4'\n"+
              "  X = '1', Y = '6'\n"+
              "  X = '1', Y = '5'\n"+
              "  X = '1', Y = '3'\n"+
              "  X = '1', Y = '2'\n"+
              "  X = '2', Y = '4'\n"+
              "  X = '2', Y = '6'\n"+
              "  X = '2', Y = '5'\n\n");
            assertTrue(ans1.equals(ans2));
            System.out.println( "PASSED" );
        	
        }catch(ParserException e){
             System.out.println(
                 "ParserException ERROR: in QueryTest::" +
                 "testEvaluateAndRecurseForFacts()\n" +
                 e.getMessage());
        }catch(Exception e){
             System.out.println(
                 "Exception ERROR: in " +
                 "QueryTest::testEvaluateAndRecurseForFacts()\n" +
                 e.getMessage());
        };

    }


    ///////////////////
    static boolean debug = false;
    static String eoln = System.getProperty("line.separator");

    private class ExtendedQuery extends Query{
        public ExtendedQuery(Lex lex)throws ParserException{
            super(lex);
        };

        public QueryVariableInformation getVariableInformation(){
            return (QueryVariableInformation)variableInformation;
        };

        public boolean equals(Object o){
            boolean result = o != null && o instanceof ExtendedQuery;
            if(result){
                ExtendedQuery epl = (ExtendedQuery)o;
                Iterator<Node> nodeIter1 = nodes.iterator();
                Iterator<Node> nodeIter2 = epl.getNodes().iterator();
                while(nodeIter1.hasNext() && nodeIter2.hasNext() && result){
                    Predicate p1 = (Predicate)nodeIter1.next();
                    Predicate p2 = (Predicate)nodeIter2.next();
                    result = p1.equals(p2);
                };
                result = result && 
                         same(getVariableInformation(),
                              epl.getVariableInformation());
            };
            return result;
        };

        public Predicate getPredicate(int i){
            return (Predicate)nodes.get(i);
        }

        public void setCopy(PredicateList predicateList){
            copy = predicateList;
        };

        public PredicateList getPredicateList(){
            return copy;
        };
    }

    private boolean same(QueryVariableInformation vinfo1,
                         QueryVariableInformation vinfo2)
    {
        boolean result = true;
        Iterator<Identifier> viter1 = vinfo1.getVariableIterator();
        Iterator<Identifier> viter2 = vinfo2.getVariableIterator();
        while(viter1.hasNext() && viter2.hasNext() && result){
            Identifier variable1 = viter1.next();
            Identifier variable2 = viter2.next();
            result = variable1.equals(variable2); 
            if(result){
                Iterator<Location> locIter1 = vinfo1.getLocationsFor(variable1);
                Iterator<Location> locIter2 = vinfo1.getLocationsFor(variable2);
                while(locIter1.hasNext() && locIter2.hasNext() && result){
                    result = locIter1.next().equals(locIter2.next());
                };
                result = result && !locIter1.hasNext() && !locIter2.hasNext();
            };
        };
        result = result && !viter1.hasNext() && !viter2.hasNext();
        return result;
    };

    private ExtendedQuery createQuery(String s)
        throws ParserException
    {
        StringReader sr = new StringReader(s);
        Lex lex = new Lex(sr);
        ExtendedQuery result = new ExtendedQuery(lex);
        PredicateList pl = new PredicateList(result);
        result.setCopy(pl);
        return result;
    }

    private DatalogProgram createDatalogProgram(String s)
        throws ParserException, Exception
    {
        StringReader sr = new StringReader(s);
        Lex lex = new Lex(sr);
        return new DatalogProgram(lex);
    }

    private boolean sameAs(Identifier[] idSet, Identifier[] variables){
        boolean result = idSet.length == variables.length;
        for(int i = 0; i < idSet.length - 1 && result; i++){
            for(int j = i+1; j < idSet.length && result; j++){
                result = !idSet[i].equals(idSet[j]);
            }
        };
        for(int i = 0; i < idSet.length && result; i++){
            boolean answer = false;
            for(int j = 0; j < variables.length && !answer; j++){
                answer = idSet[i].equals(variables[j]);
            };
            result = answer;
        };
        return result;
    };

    private boolean checkLocations(Iterator<Location> locations,
                                   Location[] locs){
        boolean result = true;
        int count = 0;
        while(locations.hasNext() && result){
            Location l = locations.next();
            boolean found = false;
            for(int i = 0; i < locs.length && !found; i++){
                found = l.equals(locs[i]);
            };
            result = found;
            count++; 
        };
        result = result && count == locs.length;
        return result;
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
