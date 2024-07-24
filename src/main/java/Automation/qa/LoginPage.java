package Automation.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
 
public class LoginPage {
    WebDriver driver;
 
    By accountList = By.id("nav-link-accountList");
    By emailField = By.id("ap_email");
    By continueButton = By.id("continue");
    By passwordField = By.id("ap_password");
    By signInButton = By.id("signInSubmit");
 
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
 
    public void clickAccountList() {
        driver.findElement(accountList).click();
    }
 
    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }
 
    public void clickContinue() {
        driver.findElement(continueButton).click();
    }
 
    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }
 
    public void clickSignIn() {
        driver.findElement(signInButton).click();
    }
}

