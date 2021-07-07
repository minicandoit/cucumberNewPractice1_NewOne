package com.interview.step_definitions;

import com.interview.pages.JavaScriptErrorPage;
import com.interview.utilities.ConfigurationReader;
import com.interview.utilities.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


public class JavaScriptError_stepDefinition {
    JavaScriptErrorPage javaScriptErrorPage=new JavaScriptErrorPage();
    private final static Logger LOGGER=Logger.getLogger(JavaScriptError_stepDefinition.class.getName());
    @When("user is on the JavaScript Error page")
    public void user_is_on_the_java_script_error_page() {
        String url= ConfigurationReader.getProperty("baseUrl");
        Driver.getDriver().get(url+"/javascript_error");
    }



    @Then("asserts that the page contains error")
    public void asserts_that_the_page_contains_error() {

        Assert.assertTrue(javaScriptErrorPage.text.getText().contains("error"));

//Assert.assertTrue(isThereJSErrorOnThePage()); if I uncomment  the message below is not printing but the assertion is passing

        LogEntries logEntries = Driver.getDriver().manage().logs().get(LogType.BROWSER);
        for (LogEntry entry : logEntries) {
            System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
//
//       Assert.assertTrue(entry.getMessage().contains("Cannot read property 'xyz' of undefined"));
//            System.out.println("entry.getMessage() = " + entry.getMessage());

            if(entry.getMessage().contains("Cannot read property 'xyz' of undefined")){
                System.out.println("entry.getMessage() = " + entry.getMessage());
                Assert.assertTrue(entry.getMessage().contains("Cannot read property 'xyz' of undefined"));
            }
        }

//
//        Driver.getDriver().manage().logs().get(LogType.BROWSER);
//
//        LogEntries entries = Driver.getDriver().manage().logs().get(LogType.BROWSER);
//        entries.filter(Level.ALL);
//        for (LogEntry entry : entries) {
//            System.out.println("entry = " + entry);
//
//        }
//
//




    }

    private void printJavaScriptErrors(){
        LogEntries logEntries = Driver.getDriver().manage().logs().get(LogType.BROWSER);
        for (LogEntry entry : logEntries) {
            System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
        }
    }




    public boolean isThereJSErrorOnThePage() {
//        WebDriver driver = Driver.getDriver();
//        DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
//        LoggingPreferences logPrefs = new LoggingPreferences();
//        logPrefs.enable(LogType.BROWSER, Level.ALL);
//        desiredCapabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
//        driver = new ChromeDriver(desiredCapabilities);


        Set<String> errorStrings = new HashSet<>();
        errorStrings.add("SyntaxError");
        errorStrings.add("EvalError");
        errorStrings.add("ReferenceError");
        errorStrings.add("RangeError");
        errorStrings.add("TypeError");
        errorStrings.add("URIError");
        errorStrings.add("Cannot read property 'xyz' of undefined");
        LogEntries logEntries = Driver.getDriver().manage().logs().get(LogType.BROWSER);
        for (LogEntry logEntry : logEntries) {
            for (String errorString : errorStrings) {
                if (logEntry.getMessage().contains(errorString)) {
                    LOGGER.config("Java Script error has been detected:");
                    LOGGER.config(new Date(logEntry.getTimestamp()) + " " + logEntry.getLevel() + " " + logEntry.getMessage());
                    return true;
                }
            }
        }
        return false;
    }



}
