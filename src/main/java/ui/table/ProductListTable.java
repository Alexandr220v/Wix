package ui.table;

import entities.Product;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Wait;

public class ProductListTable {

    private WebDriver driver;
    public static final Logger LOGGER = Logger.getLogger(ProductListTable.class);
    @FindBy(xpath = "//div[@class='gallery']")
    private WebElement parent;
    private WebElement frame;
    private final String frameID = "TPASection_jh9acbfxiframe";
    private final By spinner = By.className("loader-circle loader-circle-small");


    public ProductListTable(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectItemFromGallery(String itemId) {
        driver.switchTo().frame(frameID);
        WebElement product = parent.findElement(By.xpath("//img[contains(@src,'" + itemId + "')]"));
        LOGGER.info("Selecting item " + itemId + " from gallery...");
        Actions actions = new Actions(driver);
        actions.moveToElement(product).click().build().perform();
        Wait.waitFotAjaxIsFinished(driver);
        driver.switchTo().defaultContent();

    }

    public Product getProductInfo(String itemId) {
        driver.switchTo().frame(frameID);
        By path = By.xpath("//ancestor::div[@data-hook='image-link']");
        LOGGER.info("Getting name of  " + itemId + " from gallery...");
        String name = parent.findElement(By.xpath("//img[contains(@src,'" + itemId + "')]" +
                "//ancestor::div[@data-hook='image-link']" +
                "//following::h3")).getText();
        LOGGER.info("Getting price of  " + itemId + " from gallery...");
        String price = String.valueOf(parent.findElement(By.xpath("//img[contains(@src,'" + itemId + "')]" +
                "//ancestor::div[@data-hook='image-link']" +
                "//following::span[@data-hook='price']")).getText().
                replace("₴", "").replace(",", "."));
        driver.switchTo().defaultContent();
        return new Product.Builder().
                withName(name).
                withPrice(price).
                create();
    }

    public int getNumberOfProducts() {
        driver.switchTo().frame(frameID);
        int size = parent.findElements(By.xpath("//li[@data-hook='gallery-item']")).size();
        driver.switchTo().defaultContent();
        return size;

    }

}