package Automation.qa;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
 
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
 
import Automation.qa.LoginPage;
 
public class ParamterAmazon {
    WebDriver driver;
    WebDriverWait wait;
    ExtentReports extent;
    ExtentTest test;
    LoginPage loginPage;
    List<Object[]> loginDataList = new ArrayList<>();
    
 
    @BeforeClass
    public void setup() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
 
        String path = System.getProperty("user.dir") + "//reports/index1.html";
 
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(path);
        sparkReporter.config().setDocumentTitle("Amazon Automation Report");
        sparkReporter.config().setReportName("Amazon Test Result");
        sparkReporter.config().setTheme(Theme.STANDARD);
 
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Tester", "Sanika");
 
        test = extent.createTest("AmazonAutomationTest");
 
        loginPage = new LoginPage(driver);
        
        excelRead();
    }
    
    public void excelRead() {
        try {
            FileInputStream fis = new FileInputStream("C:\\Users\\kthakur\\OneDrive - Planit Test Management Solutions Pty Ltd\\Documents\\AmazonLogin.xlsx");
            XSSFWorkbook wk = new XSSFWorkbook(fis);
            XSSFSheet sh = wk.getSheetAt(0);
 
            for (int i = 1; i <= sh.getLastRowNum(); i++) {
                XSSFRow row = sh.getRow(i);
                String username = row.getCell(1).getRawValue();
                String password = row.getCell(2).getStringCellValue();
                loginDataList.add(new Object[]{username, password});
            }
 
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    @DataProvider(name = "loginData")
    public Object[][] getData() {
        return loginDataList.toArray(new Object[0][]);
    }
 
    @DataProvider(name = "searchData")
    public Object[][] getSearchData() {
        return new Object[][] {
            { "TV" },
            { "Shoes" },
            { "Mobile" }
            // Add more search queries as needed
        };
    }
 
    @Test(priority = 1)
    public void navigateToAmazon() {
        ExtentTest t1 = extent.createTest("Navigate to page.");
 
        t1.info("Navigating to Amazon");
        driver.get("https://www.amazon.in/?");
 
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.titleContains("Online Shopping"));
        String title = driver.getTitle();
        Reporter.log(title, true);
        Assert.assertTrue(title.contains("Online Shopping"), "Title does not contain 'Amazon'");
        t1.pass("Navigated to Amazon successfully");
    }
 
    @Test(priority = 2, dataProvider = "loginData")
    public void loginToAmazon(String username, String password) throws InterruptedException {
        loginPage.clickAccountList();
        loginPage.enterEmail(username);
        loginPage.clickContinue();
        loginPage.enterPassword(password);
        loginPage.clickSignIn();
 
        WebElement accountElement = driver.findElement(By.id("nav-link-accountList-nav-line-1"));
        Assert.assertTrue(accountElement.isDisplayed(), "Login failed");
    }
 
    @Test(priority = 3, dataProvider = "searchData")
    public void searchForItem(String searchQuery) throws InterruptedException {
        test.info("Searching for " + searchQuery);
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.clear();
        searchBox.sendKeys(searchQuery);
        searchBox.submit();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,400)");
        Assert.assertTrue(driver.getTitle().contains(searchQuery), "Search results page title does not contain '" + searchQuery + "'");
        test.pass("Searched for " + searchQuery + " successfully");
    }
 
    @Test(priority = 4)
    public void addPhoneToCart() throws InterruptedException {
        Thread.sleep(3000);
        test.info("Adding first phone to cart");
        driver.findElement(By.xpath(
                "//*[@id=\"search\"]/div[1]/div[1]/div/span[1]/div[1]/div[3]/div/div/div/div/span/div/div/div/div[2]/div/div/div[1]/h2"))
                .click();
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,400)");
        driver.findElement(By.xpath(
                "/html/body/div[4]/div/div[3]/div[8]/div[8]/div/div[1]/div/div[1]/div/div/div[2]/div/div[2]/div/form/div/div/div[37]/div[1]/span/span/span/input")).click();
        test.pass("Added phone to cart successfully");
    }
 
    @Test(priority = 5)
    public void proceed() {
        test.info("Proceeding to cart");
        driver.findElement(
                By.xpath("//*[@id=\"attach-sidesheet-checkout-button\"]/span/input"))
                .click();
        test.pass("Clicked on 'Proceed to cart' button successfully");
    }
 
    @Test(priority = 6)
    public void proceedToBuy() {
        test.info("Proceeding to buy");
        driver.findElement(By.name("proceedToRetailCheckout")).click();
        test.pass("Clicked on 'Proceed to buy' button successfully");
    }
 
    @AfterClass
    public void tearDown() {
        driver.quit();
        extent.flush();
    }
}
