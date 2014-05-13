package org.mifos.webdriver;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mifos.webdriver.pageobjects.CreateGroupPage;
import org.mifos.webdriver.pageobjects.DashBoardHomePage;
import org.mifos.webdriver.pageobjects.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Collection;

import static org.mifos.webdriver.pageobjects.CSVtoStringArray.csvFileAsCollectionOfStringArrays;

/**
 * Created by pranathb on 13/05/14.
 */
@RunWith(Parameterized.class)
public class NewGroupTest {
    private static WebDriver driver;
    private static LoginPage loginPage;
    private static DashBoardHomePage dashboardHomePage;
    private static CreateGroupPage groupPage;

    private String officeId;
    private String groupName;
    private String staffId;
    private String addClient;
    private String activationDate;
    private String externalId;
    private String submittedOn;

    public NewGroupTest(String officeId, String groupName, String staffId,String addClient,
                        String activationDate, String externalId, String submittedOn)
    {
        this.officeId = officeId;
        this.groupName = groupName;
        this.staffId = staffId;
        this.addClient = addClient ;
        this.activationDate = activationDate;
        this.externalId = externalId;
        this.submittedOn = submittedOn;
    }

    @Parameterized.Parameters
    public static Collection data(){
        return csvFileAsCollectionOfStringArrays(
                System.getProperty("user.dir") + "/src/test/resources/" + "createGroupData.csv");
    }

    @BeforeClass
    public static void setUp(){

        driver=new FirefoxDriver();
        loginPage = new LoginPage(driver);
        dashboardHomePage = new DashBoardHomePage(driver);
        groupPage = new CreateGroupPage(driver);

        loginPage.get();

        loginPage.setUser("mifos");
        loginPage.setPassword("password");
        loginPage.login();

        dashboardHomePage.load();

    }

    @Test
    public void validateNewGroupCreation() {

        dashboardHomePage.loadGroupListingPage();
        groupPage.waitUntilLoaded();
        groupPage.fillFields(this.officeId, this.groupName, this.staffId, this.addClient, this.activationDate,
                this.externalId, this.submittedOn);
        groupPage.submit();
        groupPage.waitUntilPreviewPageLoaded();

        Assert.assertNotNull(driver.findElement(
                By.cssSelector("tr[data-ng-show='group.name']>td")).getText());

    }

//    @AfterClass
//    public static void stopDriver(){
//        driver.quit();
//    }
}
