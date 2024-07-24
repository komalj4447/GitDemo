package Automation.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class Demo {
	
	
	@Test
	public void run() throws InterruptedException
	{
		WebDriver d = new ChromeDriver();
	d.get("https://www.facebook.com/");
	Thread.sleep(3000);
	d.findElement(By.name("email")).sendKeys("komal");
	d.findElement(By.id("pass")).sendKeys("123");
	}
	
	
	

}
