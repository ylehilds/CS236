//REQUIRED CLASS - KEEP THIS FILE IN THE 'project4' DIRECTORY WITH 'package project4'
//IMPLEMENT THE 'body' METHOD. MAKE AN INSTANCE OF 'Database' and return an ouput string
//DO NOT ADD METHODS

package project4;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import project2.*;
public class Project4
{
	public static File body(String[] args)
	{
		//String output = "";
		File file = new File (args[0]);
		//DatalogProgram data=null;
		//try
		//{
		//	data = new DatalogProgram(file);
		//} catch (IOException e)
		//{
		//	e.printStackTrace();
		//}
	
		//Database database = new Database(data);
		//output = database.toString();
//aftge3r you create the database you will need to evaluate the queries against the database
		// then spill it out on the screen on the return.
		return file;
	}
}
