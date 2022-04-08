//KEEP THIS FILE IN THE SRC DIRECTORY WITH NO PACKAGE
//DO NOT CHANGE THIS FILE

import project1.Project1;
import project2.Project2;
import project3.three.Project3;import project4.Project4;
import project5.Project5;

public class Driver
{
   public static void main(String args[]){
      switch( args.length ){
         case 0:
			case 1:
				error();
            break;
         default:
				int projectNumber = 0;
				try{
					projectNumber = Integer.parseInt( args[0] );
				}
				catch(Exception e){
					error();
					return;
				}
				execute( projectNumber , args );
	     }
   }

	private static void error(){
		System.out.println("Usage Error: java Driver # inputfile");
		System.out.println("# represents a digit from 1 to 5");
	}

	private static void execute( int projectNumber , String[] args ){
		String[] newArgs = new String[ args.length-1 ];
		for( int i = 1 ; i < args.length ; i++ ){
			newArgs[i-1] = args[i];
		}
		switch( projectNumber ){
			case 1:
            System.out.print( Project1.body(newArgs) );
            break;
			case 2:
            System.out.print( Project2.body(newArgs) );
            break;
			case 3:
            System.out.print( Project3.body(newArgs) );
            break;
			case 4:
            System.out.print( Project4.body(newArgs) );
            break;
			case 5:
            System.out.print( Project5.body(newArgs) );
            break;
			default:
				error();
				break;
		}
	}
}
