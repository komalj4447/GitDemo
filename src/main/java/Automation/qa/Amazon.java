package Automation.qa;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;



public class Amazon {
	
	
	WebDriver driver;
    ExtentSparkReporter Reporter;
    ExtentReports extent;
    ExtentTest test;             
    JavascriptExecutor jse;    //for scrolling
    WebDriverWait wait;        //explicit wait

    @BeforeTest
    public void setUp() {
       
    	String path=System.getProperty("user.dir")+ "\\reports\\index.html";
        Reporter = new ExtentSparkReporter(path);        
        Reporter.config().setDocumentTitle("Automation Test Report");
        Reporter.config().setReportName("Amazon Test Report");
        Reporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(Reporter);
        extent.setSystemInfo("Host Name", "Localhost");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User Name", "Test User");
    }

    @Test
    public void searchAndAddToCart() throws InterruptedException {
        test = extent.createTest("searchAndAddToCart", "Test to search a mobile and add to cart on Amazon");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
        
        
        test.info("Navigate to Amzon");
        driver.get("https://www.amazon.in/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.titleContains("Online Shopping"));
        test.pass( "Navigated to Amazon successfully");
        
         
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox")));
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        
        searchBox.sendKeys("TV");
        searchBox.submit();
        searchBox.clear();
        
        searchBox.sendKeys("Refridgter");
        searchBox.click();
        

        searchBox.clear();

       // searchBox.sendKeys(Keys.ENTER); // Press Enter to perform the search
       



        WebElement searchBox1 = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox1.sendKeys("Mobile");
        test.log(Status.INFO, "Entered 'Mobile' in search box");

        WebElement searchButton = driver.findElement(By.id("nav-search-submit-button"));
        searchButton.click();
        test.log(Status.INFO, "Clicked on search button");
        
      

        WebElement firstResult = driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[1]/div[1]/div[3]/div/div/div/div/span/div/div/div/div[2]/div/div/div[1]/h2/a/span"));
        firstResult.click();
        test.log(Status.INFO, "Clicked on the first search result");
        
       
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
       
        
        jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,300)");
        
        
        
        
        WebElement addToCartButton = driver.findElement(By.xpath("/html/body/div[2]/div/div[5]/div[3]/div[1]/div[4]/div/div[1]/div/div[1]/div/div/div[2]/div/div[2]/div/form/div/div/div[38]/div[1]/span/span/span/input"));
        addToCartButton.click();
        test.log(Status.INFO, "Clicked on 'Add to Cart' button");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[8]/div[3]/div[3]/div/div[1]/div[3]/div[1]/div[2]/div[3]/form/span")));
        

        WebElement cartButton = driver.findElement(By.xpath("/html/body/div[8]/div[3]/div[3]/div/div[1]/div[3]/div[1]/div[2]/div[3]/form/span"));
        cartButton.click();
        test.log(Status.INFO, "Clicked on the cart button");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"sc-buy-box-ptc-button\"]/span/input")));
        
             
 
        WebElement checkoutButton = driver.findElement(By.xpath("//*[@id=\"sc-buy-box-ptc-button\"]/span/input"));
        Assert.assertNotNull(checkoutButton);
        test.log(Status.PASS, "Verified the presence of checkout button");
    }

    @AfterMethod
   /* public void getResult(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Test Case Failed is " + result.getName());
            test.log(Status.FAIL, "Test Case Failed is " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "Test Case Skipped is " + result.getName());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test Case Passed is " + result.getName());
        }
    }*/

    @AfterTest
    public void tearDown() {
        driver.quit();
        extent.flush();
    }
}



