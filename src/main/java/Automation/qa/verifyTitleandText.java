package Automation.qa;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class verifyTitleandText {
	
	@Test
	public void Verify()
	{
		String expectedTitle="Electronics, Cars, Fashion, Collectibles & More | eBay";
		String expectedText="Search";

		WebDriver d = new ChromeDriver();
		d.manage().window().maximize();
		d.get("https://www.ebay.com/");
		
		String actualTitle = d.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
		String actualtext= d.findElement(By.xpath("//*[@id=\"gh-btn\"]")).getAttribute("value");
		Assert.assertEquals(actualtext, expectedText, "Text verification failed");
	}

}
