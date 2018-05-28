package ui.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.FrameUtil;
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
    private WebElement frame;
    private String frameLocator = "//iframe[contains(@src,'/storefront/product/i-m-a-product')]";

    public ProductReviewPage(WebDriver driver) {
        this.driver = driver;
        driver.switchTo().defaultContent();
        frame = driver.findElement(By.xpath(frameLocator));
        FrameUtil.swithcToIFrame(driver,frame);
        PageFactory.initElements(driver, this);
        Wait.waitUntilAnjularRequestFinished(driver);
    }

    public void addItemToCart() {
        LOGGER.info("Adding item to the cart ...");
        addToCart.click();
        Wait.waitUntilAnjularRequestFinished(driver);
    }

    public String getProductPriceFromReview() {
        LOGGER.info("Getting product price ...");
        String price = reviewPrice.getText().replace("₴","").replace(",",".");
        return price;
    }

    public String getInfo() {
        LOGGER.info("getting info");
        String info = infoSection.findElement(By.xpath("/p")).getText();
        return info;
    }
}
