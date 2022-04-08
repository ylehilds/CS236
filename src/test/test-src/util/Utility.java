package util;
import java.io.*;
import project3.three.*;

public abstract class Utility extends TestCase
{
	static protected boolean same( String studentOutput , String fileName ){
		return same( studentOutput , fileName , false );
    }
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