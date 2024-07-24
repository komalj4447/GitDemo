package Automation.qa;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

//assert is a tool class
//present asseration method with a more natural parameter order, the order is always actual value, expected value 

public class asseration {
	
	@Test
	public void verify()
	{
		String expectedTitle="Electronics, Cars, Fashion, Collectibles & More | eBay";

		WebDriver d = new ChromeDriver();
		d.manage().window().maximize();
		d.get("https://www.ebay.com/");
		
		String actualTitle = d.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
	}

}
