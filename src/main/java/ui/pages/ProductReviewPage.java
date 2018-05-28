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
    @FindBy(xpath = "//span[@data-hook='product-price']")
    private WebElement reviewPrice;
    @FindBy(xpath = "//li[@class='info-section active']")
    private WebElement infoSection;
    private By cartWidgetActive = By.xpath("//div[@class='minicart active']");
    private String frameID = "TPAMultiSection_jh9acbtniframe";

    public ProductReviewPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void addItemToCart() {
        driver.switchTo().frame(frameID);
        LOGGER.info("Adding item to the cart ...");
        addToCart.click();
        Wait.waitUntilAnjularRequestFinished(driver);
        Wait.waitFotAjaxIsFinished(driver);
        driver.switchTo().defaultContent();
    }

    public String getProductPriceFromReview() {
        driver.switchTo().frame(frameID);
        LOGGER.info("Getting product price ...");
        String price = reviewPrice.getText().replace("₴","").replace(",",".");
        driver.switchTo().defaultContent();
        return price;
    }

    public String getInfo() {
        driver.switchTo().frame(frameID);
        LOGGER.info("getting info");
        String info = infoSection.findElement(By.xpath("/p")).getText();
        driver.switchTo().defaultContent();
        return info;
    }
}
