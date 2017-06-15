package test;

import elements.DashboardPage;
import elements.LoginPage;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.*;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by saman on 5/18/17.
 */
public class OverallSetup {

    public WebDriver myWebDriver;

    public String MYURL = "http://admin.qa.yurstore.net";

    Logger myLogger_setup = Logger.getLogger("Setup");

    @BeforeMethod
    public void setup(){
        PropertyConfigurator.configure("src/log4j.properties");
        //System.setProperty("webdriver.chrome.driver", "./WebDrivers/chromedriver");
        //System.setProperty("webdriver.gecko.driver", "./WebDrivers/geckodriver");
        System.setProperty("webdriver.opera.driver", "/WebDrivers/operadriver");

        //myWebDriver = new ChromeDriver();
        //myWebDriver = new FirefoxDriver();
        myWebDriver = new OperaDriver();
        myLogger_setup.info("Driver Defined");
        myWebDriver.manage().window().maximize();
        myLogger_setup.info("Window maximized, now getting the URL");
        myWebDriver.get(MYURL);
        myLogger_setup.info("Login-page opened successfully");
    }

    @AfterMethod
    public void teardown(){
        myLogger_setup.info("All test completed, quitting the driver ...");
        myWebDriver.quit();
    }

    public void checkIfLoggedIn() {
        DashboardPage dashboardPageObj = new DashboardPage(myWebDriver);
        Assert.assertEquals(dashboardPageObj.getNameOfTheUserLoggedIn().getText(), "Yurstore Superadmin");
        myLogger_setup.info("Login Successful!");
    }

    public void loginTotheModule() {
        LoginPage loginPageobj = new LoginPage(myWebDriver);
        loginPageobj.setEmailInput("superadmin@yurstore.com");
        loginPageobj.setPasswrdInput("123admin@");
        myLogger_setup.info("Email and Password entered");
        loginPageobj.clickLoginBtn();
    }

    public void assertThis(WebElement elementToAssert, String actualValue){
        Assert.assertEquals(actualValue, elementToAssert.getText());
        myLogger_setup.info("Expected Value: "+actualValue+"\n"+"Actual Value: "+elementToAssert.getText());
    }

    public void waitfor(WebElement elementToWait){
        WebDriverWait waitforoption = new WebDriverWait(myWebDriver, 8);
        waitforoption.until(ExpectedConditions.visibilityOf(elementToWait));
    }

    public void waitForThisTobeClickable(WebElement elementTowait){
        WebDriverWait waitForOption = new WebDriverWait(myWebDriver,5);
        waitForOption.until(ExpectedConditions.elementToBeClickable(elementTowait));
        elementTowait.click();
    }

    public void waitforThisToDissapear(WebElement elementToWait){
        WebDriverWait waitforoption = new WebDriverWait(myWebDriver, 5);
        waitforoption.until(ExpectedConditions.invisibilityOf(elementToWait));
    }

    public void toggleClickAdvance(WebElement elementtoToggle){
        Actions forToggleAction = new Actions(myWebDriver);
        forToggleAction.moveToElement(elementtoToggle).click();
    }

    public void waitabit(){
        myWebDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void scrollUp(){
        JavascriptExecutor jse = (JavascriptExecutor)myWebDriver;
        jse.executeScript("window.scrollBy(0,500)", "scrolling up");
    }

    public void toggleClick(WebElement elementToToggle){
        Actions forToggleAction = new Actions(myWebDriver);
        forToggleAction.moveToElement(elementToToggle).perform();
        forToggleAction.click().build().perform();
        //forToggleAction.clickAndHold();
        //forToggleAction.release();
    }

    public void assertContains(WebElement elementToAssert, String requiredText){
        Assert.assertTrue(elementToAssert.getText().contains(requiredText));
        //myLogger_setup.info("Assertion successful!");
        myLogger_setup.info("Assersion results: \n"+"\tExpected Value: "+requiredText+"\n\t"+"Actual Value: "+elementToAssert.getText());
    }

    public void selectWebelement(WebElement webelementToSelect, String selectionValue){
        Select select = new Select(webelementToSelect);
        select.selectByVisibleText(selectionValue);
    }

    public void waitabit(WebElement elementTowait){

        Wait waitForelement = new FluentWait(myWebDriver).withTimeout(10, SECONDS).pollingEvery(5, SECONDS).ignoring(NoSuchElementException.class);

    }

    public void waitForVisibility(WebElement elementToWait){
        WebDriverWait waitForElements = new WebDriverWait(myWebDriver,5);
        waitForElements.until(ExpectedConditions.visibilityOf(elementToWait));
    }

    public void logoUpload() throws FindFailed {
        Pattern download = new Pattern("/home/saman/Documents/elements/downloads.png");
        Pattern logo = new Pattern("/home/saman/Documents/elements/logo.png");
        Pattern open = new Pattern("/home/saman/Documents/elements/open.png");

        Screen screenSource = new Screen();
        screenSource.setAutoWaitTimeout(30);
        screenSource.click(download);
        screenSource.click(logo);
        screenSource.click(open);

    }

    public void practiceJs(WebElement paramWebelement){
        JavascriptExecutor myJsExecutor = (JavascriptExecutor)myWebDriver;
        myJsExecutor.executeScript("arguements[2].setAttribute('maxlength', '20')",paramWebelement);
                //js.executeScript("arguments[0].setAttribute('attr', '10')",element)
    }

}
