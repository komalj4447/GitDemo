package Automation.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

//It does not proceed further if we use asseration , so thats the reason we would be needsing soft asseration 

public class softAssert {
	
	@Test
	public void Verify()
	
	{
		
		SoftAssert sa = new SoftAssert();// we need to create obj of soft assert
		//here i am giving wrong title just to failed a asseration(eg:that eeee)
		String expectedTitle="Electronics, eeee Cars, Fashion, Collectibles & More | eBay";
		String expectedText="Search";

		WebDriver d = new ChromeDriver();
		d.manage().window().maximize();
		d.get("https://www.ebay.com/");
		
		String actualTitle = d.getTitle();
		System.out.println("Verifing title");
		//assertion is stuck here becz of failed 

		sa.assertEquals(actualTitle, expectedTitle); //code continue becaz we use soft assertion variable here
		String actualtext= d.findElement(By.xpath("//*[@id=\"gh-btn\"]")).getAttribute("value"); //he stop to proceed further code thats y we need to use soft assertion
		sa.assertEquals(actualtext, expectedText, "Text verification failed");
		System.out.println("Closing browser");
		d.close();
		sa.assertAll();
	}
	
	
	
	

}
