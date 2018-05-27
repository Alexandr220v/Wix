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

import java.util.List;

public class ProductListTable {

    private WebDriver driver;
    public static final Logger LOGGER = Logger.getLogger(ProductListTable.class);
    @FindBy(xpath = "//div[@class='gallery']")
    private WebElement parent;
    private WebElement frame;


    public ProductListTable(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private void swithcToFrame(WebDriver driver) {
        frame = driver.findElement(By.xpath("//iframe[@id='TPASection_jh9acbfxiframe']"));
        driver.switchTo().frame(frame);
    }


    private void swithcToDefaultContent(WebDriver driver) {
       driver.switchTo().defaultContent();
    }

    public void selectItemFromGallery(String itemId) {

        swithcToFrame(driver);
        WebElement product = parent.findElement(By.xpath("//img[contains(@src,'" + itemId + "')]"));
        LOGGER.info("Selecting item " + itemId + " from gallery...");
        Actions actions = new Actions(driver);
        actions.moveToElement(product);
        actions.click().build().perform();
        swithcToDefaultContent(driver);
    }

    public Product getProductInfo(String itemId) {

        swithcToFrame(driver);
        List<WebElement> products = parent.findElements(By.xpath("//li[@data-hook='gallery-item']"));
        for (WebElement product : products) {
            WebElement prodName = product.findElement(By.xpath("//img[@class='media-item active crop']"));
            if (prodName.getAttribute("ng-src").contains(itemId)) {
                LOGGER.info("Getting info of  " + itemId + " from gallery...");
                String name = product.findElement(By.xpath("//h3")).getText();
                String price = String.valueOf(product.findElement(By.xpath("//span[@data-hook='withPrice']")).getText().
                        replace("$", "").replaceAll(" ", ""));
                String status = product.findElement(By.xpath("//span[@class='ribbon']")).getText();
                swithcToDefaultContent(driver);
                return new Product.Builder().
                        withName(name).
                        withPrice(price).
                        withStatus(status).
                        create();

            }
        }
        swithcToDefaultContent(driver);
        return null;

    }

    public int getNumberOfProducts() {
        swithcToFrame(driver);
        int size = parent.findElements(By.xpath("//li[@data-hook='gallery-item']")).size();
        swithcToDefaultContent(driver);
        return size;


    }

}