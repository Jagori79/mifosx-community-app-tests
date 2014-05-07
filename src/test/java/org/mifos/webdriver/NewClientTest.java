package org.mifos.webdriver;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mifos.webdriver.pageobjects.CreateClientPage;

import org.mifos.webdriver.pageobjects.DashBoardHomePage;
import org.mifos.webdriver.pageobjects.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class NewClientTest {

    private static WebDriver driver;
    private static LoginPage loginPage;
    private static DashBoardHomePage dashboardHomePage;
    private static CreateClientPage clientPage;

    private String officeId;
    private String staffId;
    private String firstName;
    private String lastName;
    private String mobileNo;
    private String dateOfBirth;
    private String gender;

    public NewClientTest(
    String officeId, String staffId, String firstName, String lastName, String mobileNo, String dateOfBirth,String gender)
    {
        this.officeId = officeId;
        this.staffId = staffId;
        this.firstName = firstName;
        this.lastName = lastName ;
        this.mobileNo = mobileNo;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;


    }

    @Parameterized.Parameters
    public static Collection data(){
        return csvFileAsCollectionOfStringArrays(
                System.getProperty("user.dir") + "/src/test/resources/" + "data_driven.csv");
    }

    private static Collection<String[]> csvFileAsCollectionOfStringArrays(String csvFileName) {

        List<String[]> csvRows = new ArrayList<String[]>();
        String rawCSVRow ;
        BufferedReader csvFileReader = null;
        String delimiter="#";

        System.out.println("Reading data from " + csvFileName);
        try {
            csvFileReader = new BufferedReader(new FileReader(csvFileName));

        } catch (FileNotFoundException e) {
            System.out.println("Could not find file " + csvFileName);
            e.printStackTrace();
        }
        int rowNumber=1;
        try {

            while ((rawCSVRow=csvFileReader.readLine())!=null) {
                String delimitedItems[] = rawCSVRow.split(delimiter);
                csvRows.add(delimitedItems);
                rowNumber++;
            }

        } catch (IOException e) {
            System.out.println("Error reading row number " + rowNumber);
            e.printStackTrace();
        }

        try {
            csvFileReader.close();
        } catch (IOException e) {
            System.out.println("Error closing file " + e.getMessage());
            e.printStackTrace();
        }

        return csvRows;
    }

    @BeforeClass
    public static void setUp(){

        driver=new FirefoxDriver();
        loginPage = new LoginPage(driver);
        dashboardHomePage = new DashBoardHomePage(driver);
        clientPage = new CreateClientPage(driver);

        loginPage.get();

        loginPage.setUser("mifos");
        loginPage.setPassword("password");
        loginPage.login();

        dashboardHomePage.load();

    }

    @Test
    public void validateNewClientCreation() {

        dashboardHomePage.loadClientListingPage();
        clientPage.waitUntilLoaded();
        clientPage.fillFields(
                this.officeId,this.staffId,this.firstName,this.lastName,this.mobileNo,this.dateOfBirth,this.gender);

        clientPage.submit();
        clientPage.waitUntilPreviewPageLoaded();

        assertThat(driver.findElement(
                By.cssSelector("tr[data-ng-show='client.officeName']>td")).getText(),is(this.officeId));
        assertThat(driver.findElement(
                By.cssSelector("tr[data-ng-show='client.firstname']>td")).getText(),is(this.firstName));
        assertThat(driver.findElement(
                By.cssSelector("tr[data-ng-show='client.lastname']>td")).getText(),is(this.lastName));


    }

    @AfterClass
    public static void stopDriver(){
        driver.quit();
    }

}
