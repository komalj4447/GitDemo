package Automation.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class NavedProj {

	
	@Test
	public void chechBox(){
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
	
		
		driver.get("https://www.lg4all.com/POD/NGSI_CustomerBiddingInput.aspx?ReturnUrl=%2fpod%2f%3fCode%3dIN049271001H&Code=IN049271001H");
	    driver.findElement(By.name("ctl00$ContentPlaceHolder1$gvNGSIDetails$ctl02$chkSelect")).click();
	    driver.findElement(By.id("ContentPlaceHolder1_btnSave")).submit();
	
	}
	
	

}
