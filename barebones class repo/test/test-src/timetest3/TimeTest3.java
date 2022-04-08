package timetest3;

import junit.framework.TestCase;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import project3.three.Project3;

public class TimeTest3 extends util.Utility
{
    public void test1(){
        startTime = System.currentTimeMillis();
        String[] args = { inputpath + "/i01.txt" };
        String s = Project3.body( args );
        assertTrue( same( s , answerspath + "/a01.txt" ) );
        endTime = System.currentTimeMillis();
        time = endTime - startTime;
        totalTime += time;
        printTestTime( 1 );
    }

    public void test2(){
        startTime = System.currentTimeMillis();
        String[] args = { inputpath + "/i02.txt" };
        String s = Project3.body( args );
        assertTrue( same( s , answerspath + "/a02.txt") );
        endTime = System.currentTimeMillis();
        time = endTime - startTime;
        totalTime += time;
        printTestTime( 2 );
    }

    public void test3(){
        startTime = System.currentTimeMillis();
        String[] args = { inputpath + "/i03.txt" };
        String s = Project3.body( args );
        assertTrue( same( s , answerspath + "/a03.txt" ) );
        endTime = System.currentTimeMillis();
        time = endTime - startTime;
        totalTime += time;
        printTestTime( 3 );
    }

    public void test4(){
        startTime = System.currentTimeMillis();
        String[] args = { inputpath + "/i04.txt" };
        String s = Project3.body( args );
        assertTrue( same( s , answerspath + "/a04.txt" ) );
        endTime = System.currentTimeMillis();
        time = endTime - startTime;
        totalTime += time;
        printTestTime( 4 );
    }

    public void test5(){
        startTime = System.currentTimeMillis();
        String[] args = { inputpath + "/i05.txt" };
        String s = Project3.body( args );
        assertTrue( same( s , answerspath + "/a05.txt" ) );
        endTime = System.currentTimeMillis();
        time = endTime - startTime;
        totalTime += time;
        printTestTime( 5 );
    }

    public void test6(){
        startTime = System.currentTimeMillis();
        String[] args = { inputpath + "/i06.txt" };
        String s = Project3.body( args );
        assertTrue( same( s , answerspath + "/a06.txt" ) );
        endTime = System.currentTimeMillis();
        time = endTime - startTime;
        totalTime += time;
        printTestTime( 6 );
    }

    public void test7(){
        startTime = System.currentTimeMillis();
        String[] args = { inputpath + "/i07.txt" };
        String s = Project3.body( args );
        assertTrue( same( s , answerspath + "/a07.txt" ) );
        endTime = System.currentTimeMillis();
        time = endTime - startTime;
        totalTime += time;
        printTestTime( 7 );
    }

    public void test8(){
        startTime = System.currentTimeMillis();
        String[] args = { inputpath + "/i08.txt" };
        String s = Project3.body( args );
        assertTrue( same( s , answerspath + "/a08.txt" ) );
        endTime = System.currentTimeMillis();
        time = endTime - startTime;
        totalTime += time;
        printTestTime( 8 );
    }

    public void test9(){
        startTime = System.currentTimeMillis();
        String[] args = { inputpath + "/i09.txt" };
        String s = Project3.body( args );
        assertTrue( same( s , answerspath + "/a09.txt" ) );
        endTime = System.currentTimeMillis();
        time = endTime - startTime;
        totalTime += time;
        printTestTime( 9 );
    }

    public void test10(){
        startTime = System.currentTimeMillis();
        String[] args = { inputpath + "/i10.txt" };
        String s = Project3.body( args );
        assertTrue( same( s , answerspath + "/a10.txt" ) );
        endTime = System.currentTimeMillis();
        time = endTime - startTime;
        totalTime += time;
        printTestTime( 10 );
    }

    public void test11(){
        startTime = System.currentTimeMillis();
        String[] args = { inputpath + "/i11.txt" };
        String s = Project3.body( args );
        assertTrue( same( s , answerspath + "/a11.txt" ) );
        endTime = System.currentTimeMillis();
        time = endTime - startTime;
        totalTime += time;
        printTestTime( 11 );
    }

    public void test12(){
        startTime = System.currentTimeMillis();
        String[] args = { inputpath + "/i12.txt" };
        String s = Project3.body( args );
        assertTrue( same( s , answerspath + "/a12.txt" ) );
        endTime = System.currentTimeMillis();
        time = endTime - startTime;
        totalTime += time;
        printTestTime( 12 );
    }

    public void test13(){
        startTime = System.currentTimeMillis();
        String[] args = { inputpath + "/i13.txt" };
        String s = Project3.body( args );
        assertTrue( same( s , answerspath + "/a13.txt" ) );
        endTime = System.currentTimeMillis();
        time = endTime - startTime;
        totalTime += time;
        printTestTime( 13 );
    }

    public void test14(){
        startTime = System.currentTimeMillis();
        String[] args = { inputpath + "/i14.txt" };
        String s = Project3.body( args );
        assertTrue( same( s , answerspath + "/a14.txt" ) );
        endTime = System.currentTimeMillis();
        time = endTime - startTime;
        totalTime += time;
        printTestTime( 14 );
    }

    public void test15(){
        startTime = System.currentTimeMillis();
        String[] args = {inputpath + "/i15.txt"};
        String s = Project3.body( args );
        assertTrue( same( s , answerspath + "/a15.txt") );
        endTime = System.currentTimeMillis();
        time = endTime - startTime;
        totalTime += time;
        printTestTime( 15 );
    }

    public void test16(){
        startTime = System.currentTimeMillis();
        String[] args = { inputpath + "/i16.txt" };
        String s = Project3.body( args );
        assertTrue( same( s , answerspath + "/a16.txt" ) );
        endTime = System.currentTimeMillis();
        time = endTime - startTime;
        totalTime += time;
        printTestTime( 16 );
        
        printTotalTime();
    }

    protected void setUp(){}
    protected void tearDown(){}
}
