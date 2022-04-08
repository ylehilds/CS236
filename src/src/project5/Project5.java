//REQUIRED CLASS - KEEP THIS FILE IN THE 'project5' DIRECTORY WITH 'package project5'
//IMPLEMENT THE 'body' METHOD. MAKE AN INSTANCE OF 'Database' and return an ouput string
//DO NOT ADD METHODS

package project5;

import java.io.File;

import project1.*;
import project2.*;
import project4.*;
import java.io.IOException;

public class Project5
{
	public static String body(String[] args)
	{		
//System.out.println("Implement the Project5.body");
		String output = "";
		//String data = new Project4().body(args);
		//creates a new databaseRule instantiated by database class.
		DatabaseRules database = new DatabaseRules(new Project4().body(args));
		database.rules();
		database.execute();
		output += database.toString();
		output += "Done!";
		return output;
	}
}
