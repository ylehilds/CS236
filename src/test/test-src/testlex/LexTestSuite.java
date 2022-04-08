package testlex;

import junit.framework.Test;
import junit.framework.TestSuite;

public class LexTestSuite {
	
    public static Test suite() {


        TestSuite suite = new TestSuite();
	
        suite.addTestSuite(TokenTest.class);
        suite.addTestSuite(ConstantTest.class);
        suite.addTestSuite(IdentifierTest.class);
        suite.addTestSuite(FileTest.class);
        suite.addTestSuite(LexTest.class);

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

