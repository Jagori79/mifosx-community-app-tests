package org.mifos.webdriver.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver;
    private String baseUrl = "http://localhost:9000";

    public LoginPage(WebDriver aDriver) {
        driver=aDriver;
    }

    public void get() {

//        driver.navigate().to( baseUrl + "/?baseApiUrl=https://demo.openmf.org#/" );
        driver.navigate().to( "https://demo.openmf.org/beta/#/" );
        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Mifos X Client"));
    }

    public void setUser(String userName) {
        WebElement userNameElement = driver.findElement(By.id("uid"));
        userNameElement.sendKeys(userName);

    }

    public void setPassword(String password) {
        WebElement userPasswordElement = driver.findElement(By.id("pwd"));
        userPasswordElement.sendKeys(password);
    }

    public void login() {
        WebElement signInButton = driver.findElement(By.id("login-button"));
        signInButton.click();
    }
}
