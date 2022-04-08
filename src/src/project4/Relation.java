//REQUIRED CLASS
package project4;

import java.util.ArrayList; 
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import project1.*;
import project2.*;

public class Relation
{
	private Scheme scheme;
	private Set<Tuple> tuples;
	
	
	public Relation(Scheme scheme)
	{
		this.scheme = scheme;
		tuples = new TreeSet();
	}
    public Relation getRelation(){return this;}
	
    public String getName()
	{
		return scheme.getName();
	}
    
    public void addTuple(Tuple tuple){

        tuples.add(new Tuple(tuple));

}
	public boolean addTuples(Rule rule) {
		// TODO Auto-generated method stub
		if (!scheme.getName().equals(rule))
			return false;
	tuples.add(new Tuple((List<String>) rule));
	return true;
	}

	public boolean addTuples(Fact fact)
	{
		if (!scheme.getName().equals(fact.getName()))
				return false;
		tuples.add(new Tuple(fact.getList()));
		return true;
	}
	public boolean addAllTuples(Set<Tuple> tuples){

        this.tuples=tuples;

        return true;

}
	
	public Relation() {
		// TODO Auto-generated constructor stub
	}
	
	
	  public Relation(Relation relation){
          scheme = new Scheme(relation.getScheme());
          tuples = new TreeSet();
          for(Tuple tuple:relation.getTuples()){
                          tuples.add(new Tuple(tuple));
          }
}
	  
	   public Scheme getScheme(){return scheme;}
	   public Set<Tuple> getTuples(){return tuples;}
	   public void removeCommas(List<Parameter> schema)
	   {
		   List schemaList = schema;
		   for (int i=0;i<schemaList.size();i++)
			{
				project2.Parameter curr = (project2.Parameter)schemaList.get(i);
				if (curr.toString().equals(","))
						{
							schemaList.remove(i);
						}
			}
	   }
	   public void rename(List<Parameter> schema, Query q){
			this.scheme.setSchema(schema);
			this.scheme.setName(q.getName());
			removeCommas(schema);			
		}
	
	  public void select(){
          //narrow down the tuples to the matching ones                               
          List<Tuple> delete = new ArrayList();
          Iterator<Tuple> iter = tuples.iterator();
          while (iter.hasNext()){
                          Tuple tuple = iter.next();
                          for(int i=0;i<tuple.size();i++){
                        	  //if condition is to test if schema element of query is a constant go inside
                        	  //then if schema element and tuple element are not equal flag for deletion
                                          if(((Parameter)scheme.getSchema().get(i)).getId()==null){
                                                          if(!((Parameter)scheme.getSchema().get(i)).getValue().equals(tuple.get(i)))
                                                          {
                                                                          delete.add(tuple);
                                                          }
                                          }            		
                          }
          }
          for(int i=0;i<delete.size();i++){
                          tuples.remove(delete.get(i));      
          }
          if (delete.size()==0)
          {
        	  specialSelect();
          }
}
private void specialSelect() {
	
		// TODO Auto-generated method stub
	List<Tuple> keepIdentifier = new ArrayList();
	 List<Tuple> delete = new ArrayList();
     Iterator<Tuple> iter = tuples.iterator();
     while (iter.hasNext()){
                     Tuple tuple = iter.next();
                     for(int i=0;i<tuple.size()-1;i++){
                    	 recursionThroughscheme((Parameter)scheme.getSchema().get(i));
                    	 if (((Parameter)scheme.getSchema().get(i)).getId() != null)
                    	 {
                                     if(((Parameter)scheme.getSchema().get(i)).getId().equals(((Parameter)scheme.getSchema().get(i+1)).getId())){
                                    	 if (!keepIdentifier.contains((Parameter)scheme.getSchema().get(i)))
                                    		// keepIdentifier.add(i,(Tuple) scheme.getSchema().get(i));
                                    		 if (tuple.get(i) != null)
                                                     if(!tuple.get(i).equals(tuple.get(i+1)))
                                                     {
                                                                     delete.add(tuple);
                                                     }
                                     } 
                     }
                                                		
                     }
    }
     for(int i=0;i<delete.size();i++){
                     tuples.remove(delete.get(i));  
     }
	}
	  
	  private void recursionThroughscheme(Parameter parameter) {
		  
	// TODO Auto-generated method stub
	
}
	public void project(){
          //remove columns that are constants
		  //remove columns that scheme has repeated identifiers
		  //and delete repeated columns so printout does not print same value for an identifier twice or more.
          List  keep = new ArrayList();
          Iterator<Tuple> iter = tuples.iterator();
          Tuple tuple = null;
          //System.out.println("Tuples: "+tuples);
          if (iter.hasNext()){
                          tuple = iter.next();
                          for(int i=0;i<tuple.size();i++){
                 //       	  if(((Parameter)scheme.getSchema().get(i)).getId()!=null){
                 //                 keep.add((Parameter)scheme.getSchema().get(i));//tuple.set(i,null);
                 // }
                        	  if(((Parameter)scheme.getSchema().get(i)).getId()!=null){
                                          if(!(keep.contains(((Parameter) scheme.getSchema().get(i)).getId().toString()))){
                                        	// if (!keep.contains((Parameter)scheme.getSchema().get(i)))
                                                          keep.add(((Parameter)scheme.getSchema().get(i)).getId().toString());//tuple.set(i,null);
                                          }
                                                         
                                                           //if(((Parameter)scheme.getSchema().get(i)).getId().equals(((Parameter)scheme.getSchema().get(i+1)).getId()))
                                                        	 //  if (keep.contains((Parameter)scheme.getSchema().get(i+1)))
                                                       		 //  keep.remove((Parameter)scheme.getSchema().get(i+1));
                                                        	 // keep.remove((Parameter)scheme.getSchema().get(i));
                                                         
                                          }                   
                          }
          }
         
        //  System.out.println("Keep: "+keep);
          project(keep);
}
public void project(List keeping) //maps the schema from left-hand side to tuples on the new joined relation
{  
	//List KeepTrackOfRepeatedColumns = new ArrayList();
	List flag = new ArrayList();
	Tuple tuple = null;
	Set<Tuple> newTupleList = new TreeSet();
	Iterator<Tuple> iter = tuples.iterator();
	if (iter.hasNext())
	{
	  while (iter.hasNext())
	  {
		  List KeepTrackOfRepeatedColumns = new ArrayList();
		  List<String> newTuple = new ArrayList(); 
		   tuple = iter.next();
		    int size = tuple.size();
		   List ThelistOfDeletedStrings = new ArrayList();
		  for(int j=0;j<keeping.size();j++)
		  {
		      for(int i=0;i<size;i++)
		      {
		    	  if(((Parameter)scheme.getSchema().get(i)).getId()!=null)
		    	  {
				      if(((Parameter)scheme.getSchema().get(i)).toString().equals((keeping.get(j)).toString()))
				      {
				    	  if (!KeepTrackOfRepeatedColumns.contains(((Parameter)scheme.getSchema().get(i)).toString()))
				    	  {
				    		  KeepTrackOfRepeatedColumns.add(((Parameter)scheme.getSchema().get(i)).toString());
				    	  	  newTuple.add(tuple.get(i));
				    	  }
				         // else if (KeepTrackOfRepeatedColumns.contains(((Parameter)scheme.getSchema().get(i)).toString()) && !(newTuple.contains(tuple.get(i))))
				          //{}
				    	  else  
						  {
						  		flag.add(i);
								ThelistOfDeletedStrings.add(tuple.get(i));
							  }
				      }
				      else 
				      {
				    	 //newTuple.add(tuple.get(i));
				      }
				  }  
				  else
				  {
					  if (!ThelistOfDeletedStrings.contains(tuple.get(i)))
							  {
						  		flag.add(i);
								ThelistOfDeletedStrings.add(tuple.get(i));
							  }
				  }
			  }
		  }	
		  for (int i=0; i< ThelistOfDeletedStrings.size(); i++)
		  {
			  tuple.remove(flag.get(i));
		  }
		newTupleList.add(new Tuple(newTuple)); 
	}
	iter.remove();
	}
	tuples = newTupleList;
}


public Relation join(Relation B){

    //Get the scheme
	/*
	 *  •	create two hash maps, first iteration is with itself, second iteration and so forth is with next rule on the right.
	
	  */
	 

    Relation A = this;

    Map<String,String> MapA = new HashMap();

    Map<String,String> MapB = new HashMap();

   

    Scheme tempScheme = createScheme(A.getScheme(),B.getScheme());

    Relation tempRelation = new Relation(tempScheme);

    Set<Tuple> tempTuples = new TreeSet();

    List<String> tempList = new ArrayList();

   
    for(Tuple tupleA:A.getTuples()){

    	/*
    	 * Maps the tuples to identifiers
			iterate though the scheme and the tuple list and map.put(scheme.value(i), tuple.value(i))
    	 */
                    MapA = createMap(A.getScheme(),tupleA);

   

                    for(Tuple tupleB:B.getTuples()){

                                    MapB = createMap(B.getScheme(),tupleB);

                                    tempList = new ArrayList();

                                   

                                    if (selectMaps(tempList,tempScheme, MapA, MapB))

                                                    tempTuples.add(new Tuple(tempList));//add this complete tuple

                    }

    }

    tempRelation.addAllTuples(tempTuples);

    return tempRelation;

}

private boolean selectMaps(  List<String> tempList,Scheme tempScheme,Map<String, String> MapA,Map<String, String> MapB){

    for(int s=0; s < tempScheme.getSchema().size();s++){

                    //This Checks to see if the item in the schema is a constant, if so it is added

                    //to the tuple and it breaks so it can go through the next schema item.

                    if((tempScheme.getSchema().get(s).toString()).contains("'")){

                                    tempList.add(tempScheme.getSchema().get(s).toString());

                                    continue;

                    }                                                                             

                    /*
                     * * *select according to duplicate schema attributes ***
                     * Also if tuple is in one map and not on the other, then add it to tempList.
						Whenever you don’t add to the tempList then get out of the loop.
						Do a project with the new list that you came up with, which consists of schema elements, tempList, keep these tuples.
						 
                     */
                    if(MapA.containsKey(((Parameter)tempScheme.getSchema().get(s)).getId()) &&

                                     !MapB.containsKey(((Parameter)tempScheme.getSchema().get(s)).getId())){

                                                    tempList.add(MapA.get(((Parameter)tempScheme.getSchema().get(s)).getId()));}

                                    else if(!MapA.containsKey(((Parameter)tempScheme.getSchema().get(s)).getId()) &&

                                                     MapB.containsKey(((Parameter)tempScheme.getSchema().get(s)).getId())){

                                                    tempList.add(MapB.get(((Parameter)tempScheme.getSchema().get(s)).toString()));}

                                    else if(MapA.get(((Parameter)tempScheme.getSchema().get(s)).toString())

                                                    .equals(MapB.get(((Parameter)tempScheme.getSchema().get(s)).toString()))){

                                                    tempList.add(MapA.get(((Parameter)tempScheme.getSchema().get(s)).getId()));}

                                    else return false;

    }

    return true;

}

private Map<String,String> createMap(Scheme scheme,Tuple tuple){

    //iterate though the scheme and the tuple list and map.put(scheme.value(i), tuple.value(i))

    Map map = new HashMap();

    int offset=0;

    for(int i=0; i<scheme.getSchema().size(); i++){

                    if(((Parameter)scheme.getSchema().get(i)).getId()!=null)

                    map.put(((Parameter)scheme.getSchema().get(i)).getId(),tuple.get(i-offset));

                    else offset++;

    }

    return map;

}

private Scheme createScheme(Scheme A,Scheme B){

    //iterate through both lists and add them all to the set
	/*
	 	*	createScheme(Scheme A,Scheme B)
		•	creates a tempScheme with the scheme of both relations.
		•	Get schema of relation 1, adds to tempScheme. Go through 2nd relation if tempScheme does not have a specific schema element then add to tempScheme. The reason for this is to avoid the duplicates.

	 */

    List<Parameter> tempScheme = new ArrayList();

    for(int i=0;i<A.getSchema().size();i++){

                    tempScheme.add((Parameter)A.getSchema().get(i));

    }

    for(int i=0;i<B.getSchema().size();i++){

                    if(!tempScheme.contains((Parameter)B.getSchema().get(i)))

                                    tempScheme.add((Parameter)B.getSchema().get(i));

    }

    /*System.out.println("Scheme List: "+tempScheme);

    System.out.println("Scheme A: "+ A);

    System.out.println("Scheme B: "+ B);*/

    return new Scheme(A.getName(),new ArrayList(tempScheme));

}


/*
 * essentially adds all the tuples that were added through the newly formed joined relation
 */
public void union(Relation rel){

    for(Tuple tuple:rel.getTuples()){

                    addTuple(tuple);

    }

}








public String toString(){
	

    String returnString="";
    returnString += scheme.toString().replace("  ","")+"? ";
    if (tuples.size()<=0)
                    returnString+="No";
    else returnString+="Yes("+tuples.size()+")";
    List<Tuple> delete = new ArrayList();
    Iterator<Tuple> iter = tuples.iterator();
    
    while (iter.hasNext()){
    List checkForDoubleVariable = new ArrayList();
    List checkdoublescheme = new ArrayList();
    boolean flag = true;
    Tuple tuple = iter.next();
    if (tuple.size()>0)
    returnString+="\n  ";      
    int count=0;
                    //System.out.println("Scheme: " + scheme.getSchema());
                    //System.out.println("Tuple: " + tuple);
                    int m=0;
                    for(int t=0; t<scheme.getSchema().size(); t++){
                        if(((Parameter)scheme.getSchema().get(t)).getId()!=null){
                            if(t==0 || count==0) count++;
                            else if (!iter.hasNext() && tuple.size()<scheme.getSchema().size() && tuple.size()+1<t)
                            	{
                            	   	continue;
                            	}
                            else if (tuple.size()>1 && !checkdoublescheme.contains(((Parameter)scheme.getSchema().get(t)).toString()))  
                            	returnString+=", ";
                            //System.out.println(" M: " + m);   
                            if (tuple.size()>1)
                            {
                            	if (!checkdoublescheme.contains(((Parameter)scheme.getSchema().get(t)).toString()))
                            	{
                            		checkdoublescheme.add(((Parameter)scheme.getSchema().get(t)).toString());	
		                            returnString+=scheme.getSchema().get(t).toString()+"='"+tuple.get(m)+"'"; 
                            	}
                            	else continue;
                            }
                            else if (tuple.size()==1 && flag == true)
                            {
                            	 returnString+=scheme.getSchema().get(t).toString()+"='"+tuple.get(m)+"'";
                            	 flag = false;
                            }	
}
                        else if (((Parameter)scheme.getSchema().get(t)).getId()!=null){
                        	m++;
                        }
                        if (((Parameter)scheme.getSchema().get(t)).getId()!=null){
                        	m++;
                        }
                        }
                    }
    
	return returnString;
}
public void rename(String name,List<Parameter> scheme){
	this.scheme.setName(name);
	this.scheme.setSchema(scheme);
}

}

//A relation contains a scheme and a set/list of tuples
//tuples will be considered valid cases

 