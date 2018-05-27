package ui.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui.table.ProductListTable;
import utils.Wait;

public class HomePage {

    protected final WebDriver driver;
    public static final Logger LOGGER = Logger.getLogger(HomePage.class);

    @FindBy(className = "ImageButton_1")
    private WebElement mailTo;
    @FindBy(id = "comp-jhalo8eilink")
    private WebElement shop;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToShop() {
        LOGGER.info("Waiting until page home page is loading...");
        Wait.waitElementIsVisible(driver,By.id("comp-jhalo8eilink"));
        LOGGER.info("Clicking on shop button...");
        shop.click();
        Wait.waitUntilAnjularRequestFinished(driver);
        Wait.waitFotAjaxIsFinished(driver);
    }

    public WebElement getMailTo() {
        return mailTo;
    }
}
