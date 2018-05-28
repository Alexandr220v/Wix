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
    String frameID = "comp-jh9acbuwiframe";

    public CartBagWidget(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openCartFromHeaderWidget() {
        driver.switchTo().frame(frameID);
        LOGGER.info("Opening cart widget ...");
        cartButton.click();
        Wait.waitFotAjaxIsFinished(driver);
        driver.switchTo().defaultContent();
    }

    public int getNumberOfProduct () {
        driver.switchTo().frame(frameID);
        int number = Integer.parseInt(cartButton.getText());
        driver.switchTo().defaultContent();
        return number;
    }
}
