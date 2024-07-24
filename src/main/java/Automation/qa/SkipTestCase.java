package Automation.qa;

import org.testng.SkipException;
import org.testng.annotations.Test;
//3 ways u can skip the test cases

public class SkipTestCase {
	
	boolean data=true; // just to check 3rd way skip test case
	
	
	@Test(enabled=false)  //1st way to skip the tset
	public void skipTest1()
	{
		System.out.println("Skipping 1 test cases");
	}
	
	@Test
	public void skipTest2()
	{
		System.out.println("Skipping 2 test cases forecfully");
		//2nd way to skip the test case throw the exception
		throw new SkipException("Skipping this test using exception");
	}
	
	
	@Test
	public void skipTest3()
	{
		System.out.println("Skipping 3 test cases based on condition");
		if(data==true)  // 3rd way give the condition just to check 
		{
			System.out.println("Execute the test");
		}
		else
		{
			System.out.println("do not execute further step");
			throw new SkipException("do not execute further step");
		}
	}
	

}
