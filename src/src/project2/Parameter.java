//REQUIRED CLASS
package project2;

import project1.*;

import java.util.List;

public class Parameter {
	private String name;
	private String value;
	//private List list;
	
	public Parameter(String name, String value)
	{
		this.name = name;
		this.value = value;
	}
//	public Parameter(String name, List list)
//	{
//		this.name = name;
//		this.list = list;
//	}
	
	public String toString()
	{
		if(name==null)
		{
			return "'"+value+"'";
		}
		else 
		{
			return name;
		}
	}

	public Object getId() {
		return name;
	}

	public Object getValue() {
		return value;
	}

}

