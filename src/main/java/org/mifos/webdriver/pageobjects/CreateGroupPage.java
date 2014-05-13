package org.mifos.webdriver.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by pranathb on 13/05/14.
 */
public class CreateGroupPage {
    private final WebDriver driver;


    public CreateGroupPage(WebDriver aDriver) {
        driver=aDriver;
    }

    public void waitUntilLoaded() {

        new WebDriverWait(driver,10).until(
                ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector("select#officeId")));
    }
    public void fillFields(String officeId, String groupName, String staffId,String addClient,
                           String activationDate, String externalId, String submittedOn)
    {

        WebElement officeSelect = driver.findElement(By.cssSelector("select#officeId"));
        Select officeIdOption = new Select(officeSelect);
//        officeIdOption.selectByValue(officeId);
        officeIdOption.selectByVisibleText("Home Office");

        WebElement name = driver.findElement(By.cssSelector("input#name"));
        name.sendKeys(groupName);

        WebElement staffSelect = driver.findElement(By.cssSelector("select#staffId"));
        Select staffIdOption = new Select(staffSelect);
        staffIdOption.selectByValue(staffId);

        //include here code to add client

        driver.findElement(By.cssSelector("input[type='checkbox']")).click();

        WebElement activationDatePicker = driver.findElement(By.cssSelector("input[id='activationDate']"));
        activationDatePicker.clear();
        activationDatePicker.sendKeys(activationDate);

        WebElement externalID = driver.findElement(By.cssSelector("input#externalId"));
        externalID.sendKeys(externalId);

        WebElement submittedDatePicker = driver.findElement(By.cssSelector("input[id='submittedon']"));
        submittedDatePicker.sendKeys(submittedOn);

    }
    public void submit() {
        WebElement submitButton = driver.findElement(By.cssSelector("button#save"));
        submitButton.submit();
    }

    public void waitUntilPreviewPageLoaded(){

        new WebDriverWait(driver,10).until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy((By.cssSelector("ul.breadcrumb"))));


    }

}
