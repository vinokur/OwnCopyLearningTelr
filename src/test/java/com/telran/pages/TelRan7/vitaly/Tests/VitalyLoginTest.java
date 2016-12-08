package com.telran.pages.TelRan7.vitaly.Tests;


import com.telran.TestNgTestBase;
import com.telran.pages.TelRan7.vitaly.Pages.VitalyDoctorRegistrationPage;
import com.telran.pages.TelRan7.vitaly.Pages.VitalyLoginPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by vitaliy on 28.11.2016.
 */
public class VitalyLoginTest extends TestNgTestBase {

    public VitalyLoginPage vitalyLoginPage;
    public VitalyDoctorRegistrationPage vitalyDoctorRegistrationPage;
    // public WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setup() {

        //  System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\geckodriver.exe");
        //  driver = new FirefoxDriver();
        vitalyLoginPage = PageFactory.initElements(driver, VitalyLoginPage.class);
        vitalyDoctorRegistrationPage = PageFactory.initElements(driver, VitalyDoctorRegistrationPage.class);
    }

    @BeforeMethod
    public void gotoLoginPage() {
        driver.get("http://dhclinicappv2stg.item-soft.co.il/Login.aspx");

    }

    @Test
    public void loginPositiveTest() {
        vitalyLoginPage.WaitUntilLoginPageIsLoaded();
        vitalyLoginPage.FillUserName("5555Doctor");
        vitalyLoginPage.FillPassword("LinkCare!!11");
        vitalyLoginPage.ClickLogIn();
        vitalyDoctorRegistrationPage.waitUntilDoctorsPageIsLoaded();
        Assert.assertTrue(vitalyDoctorRegistrationPage.isOnDoctorPage(), "We are not on doctor`s page");
        Assert.assertEquals("יציאה", vitalyDoctorRegistrationPage.getTextFromExitLink());
        AssertJUnit.assertEquals("יציאה", vitalyDoctorRegistrationPage.getTextFromExitLink());
    }


    @Test
    public void loginNegativeTest() {
        vitalyLoginPage.WaitUntilLoginPageIsLoaded();
        vitalyLoginPage.FillUserName("123");
        vitalyLoginPage.FillPassword("456");
        vitalyLoginPage.ClickLogIn();
        Assert.assertFalse(vitalyDoctorRegistrationPage.isOnDoctorPage(), "Login is passed");
    }

    @Test
    public void loginNullUserNameTest() {
        vitalyLoginPage.WaitUntilLoginPageIsLoaded();
        vitalyLoginPage.FillUserName(" ");
        vitalyLoginPage.FillPassword("LinkCare!!11");
        vitalyLoginPage.ClickLogIn();
        Assert.assertFalse(vitalyDoctorRegistrationPage.isOnDoctorPage(), "Login is passed");
    }

    @Test
    public void loginNullPasswordTest() {
        vitalyLoginPage.WaitUntilLoginPageIsLoaded();
        vitalyLoginPage.FillUserName("5555Doctor");
        vitalyLoginPage.FillPassword("");
        vitalyLoginPage.ClickLogIn();
        Assert.assertFalse(vitalyDoctorRegistrationPage.isOnDoctorPage(), "Login is passed");
    }

    @AfterClass
    public void tearDown() {
        this.driver.quit();
    }

}