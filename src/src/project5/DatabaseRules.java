package project5; 


import project1.*;
import project2.*;
import project4.*;

import java.util.List;
import java.util.ArrayList;
import java.io.File;

public class DatabaseRules extends Database {
	private int count;
	private List<Relation> relations;
	
	public String toString() {
		System.out.println("Schemes populated after " + count + " passes through the Rules.");
		//System.out.println("");
		return super.toString();
	}

	public int numberOfTuples() {
		int returnInt = 0;
		for (Relation rel:relations) {
			returnInt += rel.getTuples().size();
		}
		return returnInt;
	}

	//this is the time when all rules have been translated and its tuples been added to tupleList tuples, now we query the database
	public void execute() {
		super.queriesEvaluate();
	}

	public DatabaseRules(File file) {
		super(file);
	}

	public void rules() {
		int initialRelationsTuplesSize;
		Relation leftSide;
		Relation rightSideJoined;
		List<Rule> rules;
		List<Relation> answers;
		List<Relation> rightSide;
		count=0;
		rules = super.getRuleList();	
		relations = super.getRelations();
		answers = super.getAnswers();
		
		do {
			//numberOfTuples() gets the number of tuples from the relations. This number becomes the number of loops program had to go through.
			initialRelationsTuplesSize = numberOfTuples();
			for (int i=0;i<rules.size();i++) {
				//leftSide - Gets the left side of the rule, which is a new relation
				leftSide = new Relation(new Scheme((Predicate)rules.get(i).getLeft()));
				//System.out.println("Left Side: "+leftSide);
				 
				//Is an arrayList formed of the right hand side of the rules, they become queries that database evaluates to see if
				//tuples from newly formed joined relation can be added into the tempRelation variable which in turns return itself 
				//to database variable answers consisting of relations.
				rightSide = new ArrayList();
				Query test = new Query((Predicate)rules
						.get(i).getRight().getList().get(0));
				//makes a list of all relations on the right hand side scheme and its tuples from rules.get(i)
				for (int j=0;j<rules.get(i).getRight().getList().size();j++) {
					rightSide.add(evaluate(new Query((Predicate)rules.get(i).getRight().getList().get(j))));
				}
				//join the relations from right hand side. puts the new relation at position 0
				for (int j=0;j<rightSide.size();j++) {
					rightSide.set(0,rightSide.get(0).join(rightSide.get(j)));
				}
				//gets position 0 of rightSide, which is the joined relation and assigns it to relation variable rightSideJoined
				rightSideJoined = rightSide.get(0);
				//clear right side
				rightSide.clear();
				//project the new joined relation rightSideJoined
				rightSideJoined.project(leftSide.getScheme().getSchema());
				//rename the new joined relation rightSideJoined
				rightSideJoined.rename(leftSide.getScheme().getName(),leftSide.getScheme().getSchema());
				//loop through relations and check name of relations and rightSideJoined when equal union the tuples to the tupleList tuples
				for(int k=0;k<relations.size();k++){
					if (relations.get(k).getScheme().getName().equals(rightSideJoined.getScheme().getName())) relations.get(k).union(rightSideJoined);
				}
				rightSideJoined=null;
			}
			count++;
			//the while loop Is to verify: did I add anything to my tuples? If initialRelationsTuplesSize and numberOfTuples() are different than it means you added more 
			//tuples, than keep going again through the loop until initialRelationsTuplesSize and numberOfTuples are equal in size meaning you did not 
			//add anything to the tuples, in other words you exhausted all possibilities of tuples possible in the relations to be 
			//tested against database. in other words: "The fixed-point algorithm terminates when an iteration of the rule expression set does not 
			//union a new tuple to any relation in the database."
		} while (initialRelationsTuplesSize != numberOfTuples());
	}
}
