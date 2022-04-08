package testproject3;import util.*;
import junit.framework.*;
import java.io.*;
import project3.three.*;

public class TestProject3 extends Utility
{
    public void test1(){    	printTestHeader( 1 );
        String[] args = { inputpath + "/i01.txt" };
        String s = Project3.body( args );
        assertTrue( sameWithPrint( s , answerspath + "/a01.txt" ) );
    }

    public void test2(){    	printTestHeader( 2 );
        String[] args = { inputpath + "/i02.txt" };
        String s = Project3.body( args );
        assertTrue( sameWithPrint( s , answerspath + "/a02.txt" ) );
    }

    public void test3(){    	printTestHeader( 3 );
        String[] args = { inputpath + "/i03.txt" };
        String s = Project3.body( args );
        assertTrue( sameWithPrint( s , answerspath + "/a03.txt" ) );
    }

    public void test4(){    	printTestHeader( 4 );
        String[] args = { inputpath + "/i04.txt" };
        String s = Project3.body( args );
        assertTrue( sameWithPrint( s , answerspath + "/a04.txt" ) );
    }

    public void test5(){    	printTestHeader( 5 );
        String[] args = { inputpath + "/i05.txt" };
        String s = Project3.body( args );
        assertTrue( sameWithPrint( s , answerspath + "/a05.txt" ) );
    }

    public void test6(){    	printTestHeader( 6 );
        String[] args = { inputpath + "/i06.txt" };
        String s = Project3.body( args );
        assertTrue( sameWithPrint( s , answerspath + "/a06.txt" ) );
    }

    public void test7(){    	printTestHeader( 7 );
        String[] args = { inputpath + "/i07.txt" };
        String s = Project3.body( args );
        assertTrue( sameWithPrint( s , answerspath + "/a07.txt" ) );
    }

    public void test8(){    	printTestHeader( 8 );
        String[] args = { inputpath + "/i08.txt" };
        String s = Project3.body( args );
        assertTrue( sameWithPrint( s , answerspath + "/a08.txt" ) );
    }

    public void test9(){    	printTestHeader( 9 );
        String[] args = { inputpath + "/i09.txt" };
        String s = Project3.body( args );
        assertTrue( sameWithPrint( s , answerspath + "/a09.txt" ) );
    }

    public void test10(){    	printTestHeader( 10 );
        String[] args = { inputpath + "/i10.txt" };
        String s = Project3.body( args );
        assertTrue( sameWithPrint( s , answerspath + "/a10.txt" ) );
    }

    public void test11(){    	printTestHeader( 11 );
        String[] args = { inputpath + "/i11.txt" };
        String s = Project3.body( args );
        assertTrue( sameWithPrint( s , answerspath + "/a11.txt" ) );
    }

    public void test12(){    	printTestHeader( 12 );
        String[] args = { inputpath + "/i12.txt" };
        String s = Project3.body( args );
        assertTrue( sameWithPrint( s , answerspath + "/a12.txt" ) );
    }

    public void test13(){    	printTestHeader( 13 );
        String[] args = { inputpath + "/i13.txt" };
        String s = Project3.body( args );
        assertTrue( sameWithPrint( s , answerspath + "/a13.txt" ) );
    }

    public void test14(){    	printTestHeader( 14 );
        String[] args = { inputpath + "/i14.txt" };
        String s = Project3.body( args );
        assertTrue( sameWithPrint( s , answerspath + "/a14.txt" ) );
    }

    public void test15(){    	printTestHeader( 15 );
        String[] args = { inputpath + "/i15.txt" };
        String s = Project3.body( args );
        assertTrue( sameWithPrint( s , answerspath + "/a15.txt" ) );
    }

    public void test16(){    	printTestHeader( 16 );
        String[] args = { inputpath + "/i16.txt" };
        String s = Project3.body( args );
        assertTrue( sameWithPrint( s , answerspath + "/a16.txt" ) );    }

    protected void setUp(){}
    protected void tearDown(){}
}