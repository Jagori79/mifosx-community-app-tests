package org.mifos.webdriver.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashBoardHomePage {
    private final WebDriver driver;

    public DashBoardHomePage(WebDriver aDriver) {
        driver=aDriver;
    }

    public void load() {
        new WebDriverWait(driver,10).until(
                ExpectedConditions.textToBePresentInElementLocated(
                        By.cssSelector("h3.paddedleft > strong.ng-binding"), "MifosX Dash Home")
        );

    }

    public void loadClientListingPage() {
        WebElement clientDropdown = new WebDriverWait(driver,10).until(
                ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[data-toggle='dropdown']>i.icon-group")));
        clientDropdown.click();

        driver.findElement(By.cssSelector("a.ng-binding[href='#/clients']")).click();

        WebElement createClientLink = new WebDriverWait(driver,10).until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='#/createclient'][class ~='btn']"))
        );

        createClientLink.click();


    }
}
