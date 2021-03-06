package com.telran.pages.TelRan7.vinokurPagesClasses;

import com.telran.LogLog4j;
import com.telran.pages.Page;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/**
 * Created by Vinokur on 24.11.2016.
 */
public class VinokurLoginPage extends Page {
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());


    //fields
    @FindBy(id = "MainContent_LoginUser_UserName")
    WebElement usernameField;
    @FindBy(id = "MainContent_LoginUser_Password")
    WebElement passwordField;
    @FindBy(id = "MainContent_LoginUser_LoginButton")
    WebElement LoginButton;
    @FindBy(id = "Top1_HeadLoginView_DisplayName")
    WebElement DoctorLink;
    @FindBy(id = "Top1_HeadLoginStatus")
    WebElement ExitLink;
    @FindBy(id = "Top1_HeadLoginView_DisplayName")
    WebElement DoctorNameLink;

    public VinokurLoginPage(WebDriver driver) {
        super(driver);
        this.PAGE_URL = "http://dhclinicappv2stg.item-soft.co.il/Login.aspx";
        PageFactory.initElements(driver, this);
    }

//methods
    public void FillUsername(String username) {
        setElementText(usernameField, username);
        Log.info("Filling username field with " + username);
    }

    public void FillPassword(String password) {
        setElementText(passwordField, password);
        Log.info("Filling password = "+password);
    }

    public void ClickOnLogin() {
        clickElement(LoginButton);
        Log.info("login button click");
    }

    public void WaitUntilLoginPageIsLoaded() {
        waitUntilIsLoadedCustomTime(LoginButton, 10);
        Log.info("Waiting until Login page is loaded");
    }

    public void WaitUntilDoctorPageIsLoaded (){
        waitUntilIsLoadedCustomTime(DoctorLink, 10);
        Log.info("Waiting until Doctor page is loaded");
    }

    public void Login(String username, String password) {
        WaitUntilLoginPageIsLoaded();
        FillUsername(username);
        FillPassword(password);
        ClickOnLogin();
    }

    public String GetTextFromExitLink(){
        String TextFromExitLink = ExitLink.getText();
        Log.info("TextFromExitLink = " + TextFromExitLink);
        return TextFromExitLink;

    }

    public boolean IsOnDoctorPage(){
        return exists(DoctorNameLink);
    }

}

/*
testng.xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd" >
<suite name="Tests" verbose="5">
  <test name="Test1">
    <groups>
      <run>
        <include name="positive"/>
        <exclude name="negative"/>
      </run>
    </groups>

    <classes>
      <class name="com.telran.TestTelRan7.VinokurTestsClasses.VinokurLoginTest"/>
      <class name="com.telran.TestTelRan7.VinokurTestsClasses.VinokurRegisteredDoctorTest"/>
    </classes>
  </test>
</suite>
 */