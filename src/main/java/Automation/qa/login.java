package Automation.qa;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

//if we r not giving any priorites to non static metod this method executes alphabetically 
//and if we r giving pripority they will run according to priorties
//using (pripority) for giving priority
//u can give descripytion also 

import org.testng.annotations.Test;

public class login {
	
	@BeforeTest
	public void loginToApplication()
	{
		System.out.println("Before test login to application");
	}
	
	@AfterTest
	public void logoutFromApplication()
	{
		System.out.println("After test Logout from application");
	}
	
	@BeforeMethod
	public void c()
	{
		System.out.println("Before method here ");
	}
	
	
	@AfterMethod
	public void d()
	{
	System.out.println("After method here ");	
	}
	
	
	@Test(priority=2, description="This is login test code")
	public void a()
	{
		System.out.println("Test 1");
	}
	
	@Test(priority=2)
	public void b()
	{
		System.out.println("test 2");
	}

}
