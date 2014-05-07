package org.mifos.webdriver;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AddClientTest {
    public static WebDriver driver;

    @BeforeClass
    public static void setUp(){
        driver=new FirefoxDriver();
        driver.navigate().to("http://localhost:9000/?baseApiUrl=https://demo.openmf.org");
    }

    @Test
    public void loginClient(){

        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Mifos X Client"));
        assertThat(driver.getTitle(),is("Mifos X Client")) ;

        WebElement userName = driver.findElement(By.id("uid"));
        WebElement userPassword = driver.findElement(By.id("pwd"));
        WebElement signInButton = driver.findElement(By.id("login-button"));

        userName.sendKeys("mifos");
        userPassword.sendKeys("password");

        signInButton.click();

        WebElement clientDropdown=new WebDriverWait(driver,20).until(ExpectedConditions.
                            elementToBeClickable(By.cssSelector("a.dropdown-toggle i.icon-group")));


        clientDropdown.click();

        WebElement dropdownElement = driver.findElement(By.cssSelector("ul#swatch-menu li a.ng-binding"));
        dropdownElement.click();

    }

    @AfterClass
    public static void tearDown(){
        driver.quit();
    }


}
