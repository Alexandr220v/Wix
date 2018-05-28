package ui.table;

import entities.Product;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.FrameUtil;
import utils.Wait;

import java.util.List;

public class ProductListTable {

    private WebDriver driver;
    public static final Logger LOGGER = Logger.getLogger(ProductListTable.class);
    @FindBy(xpath = "//div[@class='gallery']")
    private WebElement parent;
    private WebElement frame;
    private final By frameLocator = By.xpath("//iframe[contains(@src,'storefront/gallery')]");
    private final By spinner = By.className("loader-circle loader-circle-small");


    public ProductListTable(WebDriver driver) {
        this.driver = driver;
        driver.switchTo().defaultContent();
        frame = driver.findElement(frameLocator);
        FrameUtil.swithcToIFrame(driver, frame);
        PageFactory.initElements(driver, this);
    }

    public void selectItemFromGallery(String itemId) {
        WebElement product = parent.findElement(By.xpath("//img[contains(@src,'" + itemId + "')]"));
        LOGGER.info("Selecting item " + itemId + " from gallery...");
        Actions actions = new Actions(driver);
        actions.moveToElement(product).click().build().perform();

    }

    public Product getProductInfo(String itemId) {

        By path = By.xpath("//ancestor::div[@data-hook='image-link']");
        WebElement product = parent.findElement(By.xpath("//img[contains(@src,'" + itemId + "')]"));
        LOGGER.info("Getting info of  " + itemId + " from gallery...");
        String name = product.findElement(path).findElement(By.xpath("//following::h3")).getText();
        String price = String.valueOf(product.findElement(path).findElement(By.
                xpath("//following::span[@data-hook='price']")).getText().
                replace("₴", "").replace(",", "."));
        String status = product.findElement(path).findElement(By.xpath("/following::product-ribbon")).getText();
        return new Product.Builder().
                withName(name).
                withPrice(price).
                withStatus(status).
                create();
    }

    public int getNumberOfProducts() {
        int size = parent.findElements(By.xpath("//li[@data-hook='gallery-item']")).size();
        return size;


    }

}