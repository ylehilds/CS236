package testparser;

import junit.framework.Test;
import junit.framework.TestSuite;

public class ParserTestSuite {
	
    public static Test suite() {


        TestSuite suite = new TestSuite();
	
        suite.addTestSuite(ParserExceptionTest.class);
        suite.addTestSuite(NodeTest.class);
        suite.addTestSuite(ListTest.class);
        suite.addTestSuite(DomainTest.class);
        suite.addTestSuite(DatalogSegmentTest.class);
        suite.addTestSuite(NamedListTest.class);
        suite.addTestSuite(FactTest.class);
        suite.addTestSuite(SchemeTest.class);
        suite.addTestSuite(PredicateTest.class);
        suite.addTestSuite(HeadTest.class);
        suite.addTestSuite(QueryTest.class);
        suite.addTestSuite(PredicateListTest.class);
        suite.addTestSuite(RuleTest.class);
        suite.addTestSuite(SchemeListTest.class);
        suite.addTestSuite(FactListTest.class);
        suite.addTestSuite(QueryListTest.class);
        suite.addTestSuite(RuleListTest.class);
        suite.addTestSuite(DatalogProgramTest.class);

        //
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

