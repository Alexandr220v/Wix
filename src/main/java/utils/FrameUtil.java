package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FrameUtil {

    static String currentFrame = null;
// make this currentFrame as global

 /*   public  static void switchToIFrame(String iframe, WebDriver driver) {
        if ((null != iframe) && (!"".equals(iframe))) {
            if (!iframe.equals(currentFrame)) {
                switchToFrame(iframe,driver);
                currentFrame = iframe;
            }
        } else {
            currentFrame = "";
            driver.switchTo().defaultContent();
        }
    }

    public  static void switchToFrame(String frame, WebDriver driver) {
        driver.switchTo().defaultContent();
        driver.switchTo().frame(frame);
    }*/

    public static void swithcToFrame(WebDriver driver, WebElement frame) {
        driver.switchTo().frame(frame);
    }
}
