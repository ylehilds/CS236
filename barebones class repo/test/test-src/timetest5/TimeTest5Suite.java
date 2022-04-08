package timetest5;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TimeTest5Suite {
	
    public static Test suite() {


        TestSuite suite = new TestSuite();
	
        suite.addTestSuite(TimeTest5.class);
        
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

