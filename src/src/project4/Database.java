//REQUIRED CLASS
package project4;

import java.io.File;	
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import project1.*;
import project2.*;

public class Database
{
	private DatalogProgram datalog;
	List <Scheme> schemelist;
	List <Rule> rulelist;
	List <Fact> factlist;
	List <Relation> relations;
	private List <Query> querylist;
	List <Relation> answers;
	
	  public List<Relation> getRelations(){return relations;}
      public List<Relation> getAnswers(){return answers;}
      public List<Rule> getRuleList(){return rulelist;}
      public void setAnswers(List<Relation> list){answers = list;}
      public void removeCommas(List<Rule> rules)
	   {
		   List ruleList = rules;
		   for (int i=0;i<ruleList.size();i++)
			{
				project2.Parameter curr = (project2.Parameter)RuleList.getRules().get(i);
				if (curr.toString().equals(","))
						{
							ruleList.remove(i);
						}
			}
	   }
	public Database(File input)
	{
		DatalogProgram data=null;
		try
		{
			data = new DatalogProgram(input);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	rulelist = new ArrayList<Rule> (data.RulesListP4());
	//removeCommas(rulelist);
	schemelist = new ArrayList<Scheme>(data.SchemesListP4());
	factlist = new ArrayList<Fact> (data.FactsListP4());
	querylist = new ArrayList<Query> (data.QueriesListP4());
	relations = new ArrayList<Relation> ();
	answers = new ArrayList<Relation> ();

	for (int i=0; i < schemelist.size(); i++ )
	{
		relations.add(new Relation(schemelist.get(i)));
		for (int j = 0; j < factlist.size(); j++)
		{
			relations.get(i).addTuples(factlist.get(j));
		}
	}
	
/*	for (int i=0; i < schemelist.size(); i++ )
		{
			relations.add(new Relation(schemelist.get(i)));
			for (int j = 0; j < rulelist.size(); j++)
			{
				relations.get(i).addTuples(rulelist.get(j));
			}
		}
	*/	
	queriesEvaluate();
	}
	
	public void queriesEvaluate()
	{
		answers.clear();
		for (int i=0;i<querylist.size();i++)
		{
			answers.add(evaluate(querylist.get(i)));
		}
	}

	public Relation evaluate(Query q)
	{
		Relation tempRelation=new Relation();
		for (int i=0; i < relations.size() ; i++ )
		{
			//tempRelation = relations.get(i);
			if (q.getQuery().getName().equals(relations.get(i).getName()))
			{
				tempRelation = new Relation(relations.get(i));
				tempRelation.rename(q.getQuery().getSchema(), q);
				tempRelation.select();
				tempRelation.project();
				return tempRelation;
			}
		}
		return tempRelation;
	}
	 public String toString(){
         String returnString = "";
         for(int i=0;i<answers.size();i++){
                         returnString += answers.get(i).toString()+"\n"; 
         }
         return returnString;
}
}
