

import org.testng.annotations.Test;


import static org.testng.Assert.assertEquals;

import java.util.Arrays;

import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;


public class Test1234 {
Variant11 main;
@BeforeTest
void setup() {
    main = new Variant11();
}
@Test
public void MassTest(){
	Variant11 main_object=new Variant11();
	assertEquals(new int[]{1, 2, 3}, new int[]{1, 2, 3});
}
////////////////////////////////////////////BeginTest///////////////////////////////////////////////////////////////////////////
@Test
public void begintest() {
	assertEquals(main.begintask(1,1), main.new ClassForBeginTask(2.0,0.0,1.0,1.0,1.0));
	assertEquals(main.begintask(3, 7),main.new ClassForBeginTask(10.0,-4.0,21.0,0.42857142857142855,2.3333333333333335));
	assertEquals(main.begintask(2, 7),main.new ClassForBeginTask(9.0,-5.0,14.0,0.2857142857142857,3.5));
}
@DataProvider
public Object[][] begintaskprovider(){
	return new Object[][] {{4,0},{0,3},{0,0}};
}
@Test(dataProvider="begintaskprovider",expectedExceptions = AssertionError.class)
public void negative_begin_test(double a,double b) {
	main.begintask(a, b);
}
//////////////////////////////////////////////IntegerTask/////////////////////////////////////////////////////////////////////////
@Test
public void integertest() {
	assertEquals(main.integerNumbersTask(329),main.new ClassForIntegerNumberTask(14,54));
	assertEquals(main.integerNumbersTask(457),main.new ClassForIntegerNumberTask(16,140));
}
@DataProvider
public Object[][] integer_negative_result_test(){
	return new Object[][] {{99},{2000},{1000}};
}
@Test(dataProvider="integer_negative_result_test",expectedExceptions = AssertionError.class)
public void negative_integer_task(int k) {
	main.integerNumbersTask(k);
}
//////////////////////////////////////////BooleanTest//////////////////////////////////////////////////////////////////////
@DataProvider 
public Object[][] boolean_task_data_provider(){
	return new Object[][] {{-9,3,true},{7,2,false},{7,8,false}};
}
@Test(dataProvider="boolean_task_data_provider")
public void boolean_test(int a,int b ,boolean res) {
	assertEquals(new Variant11().booleantask(a,b), res);
}
////////////////////////////////////////IfTest////////////////////////////////////////////////////////////////////////////////
@DataProvider
public Object[][] if_task_data_provider(){
	return new Object[][] {{7,9,9},{-10,10,10},{2,2,0}};
}
@Test(dataProvider="if_task_data_provider")
public void if_test(int a,int b,int res) {
	assertEquals(new Variant11().iftask(a,b),main.new ClassForIfTask(res,res));
}
//////////////////////////////////////////CaseTest///////////////////////////////////////////////////////////////////
@DataProvider
public Object[][] caseprovider(){
	return new Object[][] {{'S', 1, -1,'S'},{'S', 2, -1,'Z'}};
}
@Test(dataProvider="caseprovider")
public void casetest(char letter,int N1,int N2,char resletter) {
	assertEquals(main.casetask(letter, N1, N2),resletter);
}
@DataProvider
public Object[][] case_negative_test_provider(){
	return new Object[][] {{'M', 1, -1},{'N', 3, -1,},{'N',1,-3}};
}
@Test(dataProvider="case_negative_test_provider",expectedExceptions = AssertionError.class)
public void negative_case_test(char S,int N1,int N2) {
	main.casetask(S, N1, N2);
}
/////////////////////////////////////////////ForTest//////////////////////////////////////////////////////////////////////
@Test
public void for_test() {
	assertEquals(main.fortask(5),35);
	assertEquals(main.fortask(10),145);
}
@DataProvider
public Object[][] negative_for_test_data_provider(){
	return new Object[][] {{0},{-1},{-1000}};
}
@Test(dataProvider="negative_for_test_data_provider",expectedExceptions = AssertionError.class)
public void negative_for_test(int k) {
	main.fortask(k);
}
///////////////////////////////////////WhileTest////////////////////////////////////////////////////////////////////////////////
 @Test
 public void while_task() {
	 assertEquals(main.whiletask(25),8);
	 assertEquals(main.whiletask(100),15);
 }
 @DataProvider
 public Object[][] negative_while_task_provider(){
	 return new Object[][] {{0},{-1},{-100}};
 }
 @Test(dataProvider="negative_while_task_provider",expectedExceptions = AssertionError.class)
 public void negative_while_task(int k) {
	 main.whiletask(k);
}
//////////////////////////////////////////////////////ArrayTest/////////////////////////////////////////////////////////////////////////
 @Test
	public void arraytest() {
		assertEquals(main.arraytask(new int[] {1,2,3,4,5},5),1);
		assertEquals(main.arraytask(new int[] {3,10,-2,6,5,10,11,2,3,4},10) ,2);
	}
////////////////////////////////////////MatrixTest///////////////////////////////////////////////////////////////////////////////////////////
 @Test
 public void matrixtest() {
		int[][] mas=new int[4][4];
		int[][] mas2=new int[][] { 
				{11,12,3,4},
				{15,16,7,8},
				{9,10,1,2},
				{13,14,5,6}
		};
		
		int count=1;
		for(int i=0;i<mas.length;i++) {
			for(int j=0;j<mas[i].length;j++) {
				mas[i][j]=count;
				count++;
			}
		}
		//assertEquals(main.matrixtask(mas, 4, 4),mas2);
		Assert.assertTrue(Arrays.deepEquals(main.matrixtask(mas, 4, 4), mas2));
	}
 @DataProvider 
 public Object[][] negative_matrix_test_data_provider(){
	int[][] mas=new int[10][10];
	for(int i=0;i<mas.length;i++) {
		for(int j=0;j<mas[i].length;j++) {
			mas[i][j]=0;
		}
	}
	return new Object[][] {{mas,5,6},{mas,7,9}};
 }
 @Test(dataProvider="negative_matrix_test_data_provider",expectedExceptions = AssertionError.class)
 public void negative_matrix_test(int[][] a,int m,int n) {
	 main.matrixtask(a, n, m);
 }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	
	
	
	
	
	
	@Test
	public void arraytest() {
		assertEquals(main.arraytask(new int[] {1,2,3,4,5},5),1);
		assertEquals(main.arraytask(new int[] {3,10,-2,6,5,10,11,2,3,4},10) ,2);
	}
	public void matrixtest() {
		int[][] mas=new int[4][4];
		int[][] mas2=new int[][] { 
				{11,12,3,4},
				{15,16,7,8},
				{9,10,1,2},
				{13,14,3,4}
		};
		int count=1;
		for(int i=0;i<mas.length;i++) {
			for(int j=0;j<mas[i].length;j++) {
				mas[i][j]=count;
			}
		}
		assertEquals(main.matrixtask(mas, 4, 4),mas2);
		
		
	}
	*/
	
	
	
}
