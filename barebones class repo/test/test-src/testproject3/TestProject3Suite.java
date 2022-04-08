package testproject3;

import junit.framework.*;

public class TestProject3Suite {
	
    public static Test suite() {


        TestSuite suite = new TestSuite();
	
      suite.addTestSuite(LocationTest.class);
      suite.addTestSuite(LocationsTest.class);
      suite.addTestSuite(LocationsAndValueTest.class);
      suite.addTestSuite(VariableInformationTest.class);
      suite.addTestSuite(QueryVariableInformationTest.class);
      suite.addTestSuite(SetOfSolutionsTest.class);
      suite.addTestSuite(FactListTest.class);
      suite.addTestSuite(HeadTest.class);
      suite.addTestSuite(PredicateListTest.class);
      //The folling is commented out because it takes a long time.  When
        //we are finished we will uncomment it.
      suite.addTestSuite(QueryTest.class);
      suite.addTestSuite(QueryListTest.class);
      suite.addTestSuite(RuleTest.class);
      suite.addTestSuite(PredicateListTest.class);
      suite.addTestSuite(DatalogProgramTest.class);
      suite.addTestSuite(TestProject3.class);
        
        // Another example test suite of tests.
        // 
        //suite.addTest(CreditCardTestSuite.suite());
        return suite;
    }

    /**
     * Runs the test suite using the textual runner.
     */
    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}

