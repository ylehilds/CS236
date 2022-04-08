package testlex;

import junit.framework.TestCase;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;

import project3.lex.File;

/**
 * FileTest is a JUnit test for File
 */
public class FileTest
    extends TestCase
{
    /**
     * Constructs a test case for the test specified in the name argument.
     */
    public FileTest (String name)
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

    static private String inputpath = "test/test-src/testlex";
    /**
     * Test Constructor: File(String).  Also tests front() and getLineNumber().
     */
    public void testStringConstructor(){
        file = new File( inputpath + "/in1.txt" );
        assertTrue(file.front() == File.EOF);
        assertTrue(file.getLineNumber() == 1);

        file = new File( inputpath + "/in2.txt" );
        assertTrue(file.front() == 'a');
        assertTrue(file.getLineNumber() == 1);

        file = new File( inputpath + "/in3.txt" );
        assertTrue(file.front() == 'a');
        assertTrue(file.getLineNumber() == 1);
    };

    /**
     * Test Constructor: File(StringReader).  Also tests front() and
     * getLineNumber().
     */
    public void testStringReaderConstructor(){
        stringReader = new StringReader("");
        file  = new File(stringReader);
        assertTrue(file.front() == File.EOF);
        assertTrue(file.getLineNumber() == 1);

        stringReader = new StringReader("a");
        file  = new File(stringReader);
        assertTrue(file.front() == 'a');
        assertTrue(file.getLineNumber() == 1);

        stringReader = new StringReader("ab\nc");
        file  = new File(stringReader);
        assertTrue(file.front() == 'a');
        assertTrue(file.getLineNumber() == 1);
    }

    /**
     * Tests popFront()
     */
    public void testPopFront(){
        stringReader = new StringReader("");
        file  = new File(stringReader);
        file.popFront();
        assertTrue(file.front() == File.EOF);
        assertTrue(file.getLineNumber() == 1);
        file.popFront();
        assertTrue(file.front() == File.EOF);
        assertTrue(file.getLineNumber() == 1);

        stringReader = new StringReader("a");
        file  = new File(stringReader);
        assertTrue(file.front() == 'a');
        assertTrue(file.getLineNumber() == 1);
        file.popFront();
        assertTrue(file.front() == File.EOF);
        assertTrue(file.getLineNumber() == 1);
        file.popFront();
        assertTrue(file.front() == File.EOF);
        assertTrue(file.getLineNumber() == 1);

        stringReader = new StringReader("ab\nc");
        file  = new File(stringReader);
        assertTrue(file.front() == 'a');
        assertTrue(file.getLineNumber() == 1);
        file.popFront();
        assertTrue(file.front() == 'b');
        assertTrue(file.getLineNumber() == 1);
        file.popFront();
        assertTrue(file.front() == '\n');
        assertTrue(file.getLineNumber() == 1);
        file.popFront();
        if(file.front() == '\r'){
            assertTrue(file.getLineNumber() == 2);
            file.popFront();
        };
        assertTrue(file.front() == 'c');
        assertTrue(file.getLineNumber() == 2);
        file.popFront();
        assertTrue(file.front() == File.EOF);
        assertTrue(file.getLineNumber() == 2);
    };

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
     * Uncomment this file declaration and add any necessary initialization
     * arguments for it in the setUp() method.
     */
     private File file;
     private StringReader stringReader;

    /**
     * Utility main method. This will the test cases defined in this class.
     *
     * Usage: java Project2Test
     */
    public static void main (String[] args)
    {
        /* junit.textui.TestRunner will write the test results to stdout. */
        junit.textui.TestRunner.run (FileTest.class);

        /* junit.swingui.TestRunner will display the test results in JUnit's
           swing interface. */
        //junit.swingui.TestRunner.run (Project2Test.class);
    }
}
