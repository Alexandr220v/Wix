package utils;

import com.google.common.base.Function;
import com.paulhammant.ngwebdriver.NgWebDriver;
import org.junit.Assert;
import org.omg.CORBA.TIMEOUT;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import  com.paulhammant.*;

public class Wait   {

    public static final long TIMEOUT = 10;

    public static void waitElementIsVisible(WebDriver driver, By locator){
        WebDriverWait webDriverWait = new WebDriverWait(driver, TIMEOUT);
        webDriverWait.
                until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitElementIsNotPresent(WebDriver driver, By locator){
        WebDriverWait webDriverWait = new WebDriverWait(driver, TIMEOUT);
        webDriverWait.
                until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static void waitElementIsClickable(WebDriver driver, By locator){
        WebDriverWait webDriverWait = new WebDriverWait(driver, TIMEOUT);
        webDriverWait.
                until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitElementIsPresent(WebDriver driver, By locator){
        WebDriverWait webDriverWait = new WebDriverWait(driver, TIMEOUT);
        webDriverWait.
                until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static void waitUntilAnjularRequestFinished(WebDriver driver) {
        final String script = "var callback = arguments[arguments.length - 1];\n" +
                "var rootSelector = \'body\';\n" +
                "var el = document.querySelector(rootSelector);\n" +
                "\n" +
                "try {\n" +
                "    if (angular) {\n" +
                "        window.angular.getTestability(el).whenStable(callback);\n" +
                "    }\n" +
                "    else {\n" +
                "        callback();\n" +
                "    }\n" +
                "} catch (err) {\n" +
                "    callback(err.message);\n" +
                "}";

        ((JavascriptExecutor) driver).executeAsyncScript(script, new Object[0]);
    }

    public static void waitFotAjaxIsFinished(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("return (typeof jQuery != 'undefined') ? jQuery.active == 0 : true;");

    }


}
