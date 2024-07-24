package Automation.qa;


import java.time.Duration;
import java.util.ArrayList;
 
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
 
//import static org.testng.Assert.assertTrue;
 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
 
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class AmazonAutomation {
	
	WebDriver driver;
	WebDriverWait wait;
	ExtentReports extent;
	ExtentTest test;
 
	@BeforeClass
	public void setup() throws InterruptedException
	{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
 
		String path = System.getProperty("user.dir") + "//reports2/index2.html";
 
		// Initialize ExtentReports with ExtentSparkReporter
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(path);
		sparkReporter.config().setDocumentTitle("Amazon Automation Report");
		sparkReporter.config().setReportName("Amazon Test Result");
		sparkReporter.config().setTheme(Theme.STANDARD);
 
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Tester", "Komal");
 
		test = extent.createTest("AmazonAutomationTest");
	}
 
	@Test(priority = 1)
	public void navigateToAmazon() {
		
		ExtentTest t1 = extent.createTest("Navigate to Amazon");
	
 
		t1.info("Navigating to Amazon");
		driver.get("https://www.amazon.in/?");
		
		//explicit wait
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.titleContains("Online Shopping"));
		String title = driver.getTitle();
		Reporter.log(title,true);
		//System.out.println(title);
		Assert.assertTrue(title.contains("Online Shopping "), "Title does not contain 'Amazon'");
		t1.pass("Navigated to Amazon successfully");
	}
 
	@Test(priority = 2)
	public void loginToAmazon() throws InterruptedException {
		
		ExtentTest t2 = extent.createTest("Logging Amazon");
		
		t2.info("Logging into Amazon");
		driver.findElement(By.id("nav-link-accountList")).click();
		driver.findElement(By.id("ap_email")).sendKeys("9518937126");
		driver.findElement(By.id("continue")).click();
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.elementToBeClickable(By.id("ap_password")));
	        
		driver.findElement(By.id("ap_password")).sendKeys("Sanika@07");
		driver.findElement(By.id("signInSubmit")).click();
 
		// Verify login by checking for an element that's only present when logged in
		WebElement accountElement = driver.findElement(By.id("nav-link-accountList-nav-line-1"));
		Assert.assertTrue(accountElement.isDisplayed(), "Login failed");
		t2.pass("Logged into Amazon successfully");
	}
 
	@Test(priority = 3)
	public void searchForTV() throws InterruptedException {
		test.info("Searching for TV");
		WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
		searchBox.sendKeys("TV");
		searchBox.submit();
		// wait for results to load and print the title
		//Thread.sleep(2000);
		Assert.assertTrue(driver.getTitle().contains("TV"), "Search results page title does not contain 'TV'");
		test.pass("Searched for TV successfully");
	}
 
	@Test(priority = 4)
	public void searchForShoes() throws InterruptedException {
		test.info("Searching for Shoes");
		WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
		searchBox.clear();
		searchBox.sendKeys("Shoes");
		searchBox.submit();
 
		// wait for results to load and print the title
		//Thread.sleep(2000);
		Assert.assertTrue(driver.getTitle().contains("Shoes"), "Search results page title does not contain 'Shoes'");
		Reporter.log("hello", false);
		test.pass("Searched for Shoes successfully");
	}
 
	@Test(priority = 5)
	public void searchForPhone() throws InterruptedException {
		test.info("Searching for Phone");
		WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
		searchBox.clear();
		searchBox.sendKeys("Mobile");
		searchBox.submit();
        //scroll
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,400)");
 
		// wait for results to load and print the title
		//Thread.sleep(2000);
		Assert.assertTrue(driver.getTitle().contains("Mobile"), "Search results page title does not contain 'Phone'");
		test.pass("Searched for Phone successfully");
	}
 
	@Test(priority = 6)
	public void addPhoneToCart() throws InterruptedException {
		Thread.sleep(3000);
		test.info("Adding first phone to cart");
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[1]/div/span[1]/div[1]/div[3]/div/div/div/div/span/div/div/div/div[2]/div/div/div[1]/h2/a/span")).click();
		//Thread.sleep(3000);
 
		// Switch to the new tab if a new tab is opened
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
 
		// scroll
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,400)");
		// Thread.sleep(5000);
		driver.findElement(By.xpath("/html/body/div[4]/div/div[3]/div[8]/div[8]/div/div[1]/div/div[1]/div/div/div[2]/div/div[2]/div/form/div/div/div[37]/div[1]/span/span/span/input")).click();
 
		
		test.pass("Added phone to cart successfully");
	}
 
 
	@Test(priority = 7)
	public void proceed() {
		test.info("proceed to cart");
		// Click on "Proceed to cart" button
		driver.findElement(
				By.xpath("/html/body/div[10]/div[3]/div[3]/div/div[1]/div[3]/div[1]/div[2]/div[3]/form/span/span/input"))
				.click();
		test.pass("Clicked on 'Proceed to cart' button successfully");
	}
 
	@Test(priority = 8)
	public void proceedToBuy() {
		test.info("proceed to buy");
		// Click on "Proceed to buy" button
		driver.findElement(By.name("proceedToRetailCheckout")).click();
		test.pass("Clicked on 'Proceed to buy' button successfully");
	}
 
	@AfterClass
	public void tearDown() {
 
		// Close the browser
		driver.quit();
		// Generate the report
		extent.flush();
	}
	
	
	

}
