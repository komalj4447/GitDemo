package Automation.qa;

import org.testng.annotations.Test;


@Test(groups="user")//if u want executed all methods then do this ,it will get executed


//steps-> right click->run as-> configuration->group(browser)->select(regression,bvt,any u want to perform), apply->, finish
//only those test cases will executed which u selected 
public class GroupTestCase {
	
	@Test(priority=1,groups="Regression")
	public void test1()
	{
		System.out.println("Test1 is here");
		
	}
	@Test(priority=2,groups="Regression")
	
	public void test2()
	{
		System.out.println("Test2 is here");
		
	}
	
   @Test(groups={"Regression","bvt"})
	
	public void test3()
	{
		System.out.println("Tes3 is here");
		
	}
   
   @Test(groups="bvt")
	
	public void test4()
	{
		System.out.println("Tes4 is here");
		
	}
	

}
