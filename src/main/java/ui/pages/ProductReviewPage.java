package ui.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Wait;

public class ProductReviewPage {

    private WebDriver driver;
    public static final Logger LOGGER = Logger.getLogger(ProductReviewPage.class);
    @FindBy(xpath = "//*[@data-hook='add-to-cart']")
    private WebElement addToCart;
    private WebElement frame;
    private String frameLocator = "//iframe[contains(@src,'https://ecom.wix.com/storefront/product/i-m-a-product')]";

    public ProductReviewPage(WebDriver driver) {
        this.driver = driver;
        driver.switchTo().defaultContent();
        frame = driver.findElement(By.xpath(frameLocator));
        driver.switchTo().frame(frame);
        Wait.waitUntilAnjularRequestFinished(driver);
        PageFactory.initElements(driver, this);
    }

    public void addItemToCart() {
        LOGGER.info("Adding item to the cart ...");
        addToCart.click();
        Wait.waitUntilAnjularRequestFinished(driver);
    }
}
