//REQUIRED CLASS
package project4;

import java.util.ArrayList;
import java.util.List;

import project1.*;
import project2.*;

public class Tuple extends ArrayList<String> implements Comparable 
{
	public Tuple (List <String> tuple)
	{
		super (tuple);
	}

	

	public int compareTo(Object arg0) {
		Tuple tuple = (Tuple) arg0;
		int returnInt =0;
		for(int i=0; i<this.size();i++)
		{
			returnInt = this.get(i).compareTo(tuple.get(i));
			if (returnInt !=0)
				break;
		}
		return returnInt;
	}

	
	
	
}
