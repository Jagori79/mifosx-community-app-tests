package org.mifos.webdriver.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CreateClientPage {
    private final WebDriver driver;


    public CreateClientPage(WebDriver aDriver) {
        driver=aDriver;
    }

    public void waitUntilLoaded() {

        new WebDriverWait(driver,10).until(
                ExpectedConditions.textToBePresentInElement(
                        By.cssSelector("select#officeId > option[selected='selected']"),"Head Office"));
    }

    public void fillFields(String officeId, String staffId, String firstName, String lastName, String mobileNo, String dateOfBirth, String gender)
    {

        WebElement officeSelect = driver.findElement(By.cssSelector("select#officeId"));
        Select officeIdOption = new Select(officeSelect);
        officeIdOption.selectByVisibleText(officeId);

        WebElement staffSelect = driver.findElement(By.cssSelector("select#staffId"));
        Select staffIdOption = new Select(staffSelect);
        staffIdOption.selectByVisibleText(staffId);

        WebElement firstNameInput = driver.findElement(By.cssSelector("input[name='firstname']"));
        firstNameInput.sendKeys(firstName);

        WebElement lastNameInput = driver.findElement(By.cssSelector("input[name='lastname']"));
        lastNameInput.sendKeys(lastName);

        WebElement mobileNumberInput = driver.findElement(By.cssSelector("input[name='mobileNo']"));
        mobileNumberInput.sendKeys(mobileNo);

        WebElement dateOfBirthInput = driver.findElement(By.cssSelector("input[name='dateofbirth']"));
        dateOfBirthInput.sendKeys(dateOfBirth);

        WebElement genderSelect = driver.findElement(By.cssSelector("select#genderId"));
        Select genderOption = new Select(genderSelect);
        genderOption.selectByVisibleText(gender);
    }

    public void submit() {
        WebElement submitButton = driver.findElement(By.cssSelector("button#save"));
        submitButton.submit();
    }


    public void waitUntilPreviewPageLoaded(){

        new WebDriverWait(driver,10).until(
                ExpectedConditions.presenceOfElementLocated(By.cssSelector("li[heading='Clientinfo']>a.ng-binding")));


    }
}
