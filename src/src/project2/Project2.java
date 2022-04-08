//REQUIRED CLASS - KEEP THIS FILE IN THE 'project2' DIRECTORY WITH 'package project2'
//IMPLEMENT THE 'body' METHOD. MAKE AN INSTANCE OF 'DatalogProgram' and return an ouput string
//DO NOT ADD METHODS

package project2;

import java.io.File;
import java.io.IOException;

public class Project2
{
	public static String body(String[] args)
	{
	File file = new File (args[0]);
	DatalogProgram data=null;
	try
	{
		data = new DatalogProgram(file);	
	} catch (IOException e)
	{
		e.printStackTrace();
	}
//System.out.println("Implement the Project2.body");
//		String output = "";
//		output+=data;
//		return output;
	return "";
	}
}
