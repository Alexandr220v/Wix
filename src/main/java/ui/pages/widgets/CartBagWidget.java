package ui.pages.widgets;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Wait;

public class CartBagWidget {


    protected WebDriver driver;
    public static final Logger LOGGER = Logger.getLogger(CartBagWidget.class);
    @FindBy(xpath = "//a[@id='cart-widget-button']")
    private WebElement cartButton;
    private WebElement frame;
    String frameLocator = "//iframe[@id='comp-jh9acbuwiframe']";

    public CartBagWidget(WebDriver driver) {
        this.driver = driver;
        driver.switchTo().defaultContent();
        frame = driver.findElement(By.xpath(frameLocator));
        driver.switchTo().frame(frame);
        PageFactory.initElements(driver, this);
    }

    public void openCartWidget() {
        LOGGER.info("Opening cart widget ...");
        cartButton.click();
        Wait.waitUntilAnjularRequestFinished(driver);
    }

    public String getNumberOfProduct () {
       return cartButton.getText();
    }
}
