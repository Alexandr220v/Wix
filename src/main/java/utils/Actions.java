package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;

public class Actions {

    public static void click(WebElement element){
        try{
            element.click();
        }
        catch (StaleElementReferenceException exception){
            element.click();
        }
    }

    public static void click(WebDriver driver, By locator){

        click(driver.findElement(locator));
    }

    public static String getText(WebElement element){
        try{
            return element.getText();
        }
        catch (StaleElementReferenceException exception){
            return element.getText();
        }
    }

    public static String getText(WebDriver driver, By locator){
        return getText(driver.findElement(locator));
    }

    public static String getText(WebElement parent, By locator){
        return getText(parent.findElement(locator));
    }

    public static String getValue(WebElement element){
        try{
            return element.getAttribute("value");
        }
        catch (StaleElementReferenceException exception){
            return element.getAttribute("value");
        }
    }

    public static String getValue(WebDriver driver, By locator){
        return getValue(driver.findElement(locator));
    }

    public static void setValue(WebElement element, String value){
        try{
            element.clear();
            element.sendKeys(value);
        }
        catch (StaleElementReferenceException exception){
            element.clear();
            element.sendKeys(value);
        }
    }

    public static void setValue(WebDriver driver, By locator, String value){
        setValue(driver.findElement(locator), value);
    }

    public static void scrollIntoView(WebDriver driver, WebElement element){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void scrollIntoView(WebDriver driver, By locator){
        scrollIntoView(driver, driver.findElement(locator));
    }

}
