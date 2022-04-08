package util;import answerParser.*;import junit.framework.*;
import java.io.*;
import project3.three.*;

public abstract class Utility extends TestCase
{    static protected long startTime = -1;    static protected long endTime = -1;    static protected long totalTime = 0;    static protected long time = 0;		static protected String inputpath = "test/inputs";	static protected String answerspath = "test/answers";	static protected void printTestHeader( int testNumber ){		System.out.println( "******************** TEST" + testNumber + " ********************" );		System.out.println( " " );	}    static private void printTime(String str , long time ){        System.out.print( str );        System.out.print( time / 1000.0 );        System.out.println( " sec" );    }    static protected void printTestTime( int testNumber ){    	printTime( "Total time for test" + testNumber + " = " , time );    }        static protected void printTotalTime(){    	System.out.println( " " );        printTime( "Total Time = " , totalTime );    }    
	static protected boolean same( String studentOutput , String fileName ){
		return same( studentOutput , fileName , false );
    }	static protected boolean sameWithPrint( String studentOutput , String fileName ){		return same( studentOutput , fileName , true );	}	static private boolean debug = false;		static private boolean same( String studentOutput , String fileName , boolean print ){		String expectedResult = getContentsOf(fileName);        Answers ans1 = new Answers(studentOutput);        Answers ans2 = new Answers(expectedResult);        if(print) System.out.println( "********** STUDENT OUTPUT **********\n" + studentOutput + "\n " );        if(print) System.out.println( "********** EXPECTED RESULT **********\n" + expectedResult + "\n " );        if(debug) System.out.println( "********** ANSWER 1 **********\n" + ans1 + "\n " );        if(debug) System.out.println( "********** ANSWER 2 **********\n" + ans1 + "\n " );        return ans1.equals(ans2);	}
    static private String getContentsOf(String fileName) {
    	StringBuffer contents = new StringBuffer();
    	BufferedReader input = null;
    	try {
    		//use buffering
    		//this implementation reads one line at a time
    		input = new BufferedReader(new FileReader(fileName));
    		String line = null; //not declared within while loop
    		while (( line = input.readLine()) != null){
    			contents.append(line);
    			contents.append(System.getProperty("line.separator"));
    		}
    	} catch (FileNotFoundException ex) {
    		ex.printStackTrace();
    	} catch (IOException ex){
    		ex.printStackTrace();
    	} finally {
    		try {
    			if (input!= null) {
    				//flush and close both "input" and its underlying FileReader
    				input.close();
    			}
    		} catch (IOException ex) {
    			ex.printStackTrace();
    		}
    	}
    	return contents.toString();
	}
}
