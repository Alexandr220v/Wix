package ui.pages.widgets;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Wait;

import java.util.List;

public class CartListWidget {

    private WebDriver driver;
    public static final Logger LOGGER = Logger.getLogger(CartListWidget.class);
    @FindBy(id = "cart-widget-close")
    private WebElement closeCart;
    @FindBy(xpath = "//*[@class='button-primary is-button-wide']")
    private WebElement viewCart;
    @FindBy(xpath = "//*[@class='cart-content']")
    private WebElement cartContent;
    @FindBy(xpath = "//button[@class='remove-item']")
    private WebElement removeItem;
    @FindBy(xpath = "//div[@data-hook='cart-widget-total']")
    private WebElement subTotalAmount;
    @FindBy(xpath = "//section[@class='cart-content']//span[@data-hook='user-free-text']")
    private WebElement cartEmptyMessage;
    @FindBy(xpath = "//li[@data-hook='cart-widget-item']")
    private WebElement productList;

    private WebElement frame;
    private String frameLocator = "//iframe[contains(@src,'https://ecom.wix.com/storefront/cartwidgetPopup')]";

    public CartListWidget(WebDriver driver) {
        this.driver = driver;
        driver.switchTo().defaultContent();
        frame = driver.findElement(By.xpath(frameLocator));
        driver.switchTo().frame(frame);
        PageFactory.initElements(driver, this);
    }


    public void removeItemFromCart(String item) {
        List<WebElement> rows = cartContent.findElements(By.xpath("//li[@data-hook='cart-widget-item']"));
        for (WebElement row : rows) {
            if (row.findElement(By.xpath("//img[contains(@src,'" + item + "')]")) != null) {
                LOGGER.info("Removing " + item + " from cart...");
                Actions actions = new Actions(driver);
                actions.moveToElement(row.findElement(By.xpath("//button[@class='remove-item']"))).click().build().perform();
                Wait.waitUntilAnjularRequestFinished(driver);
                LOGGER.info("Product " + item + " should be removed");
                break;
            }
        }
    }

    public void minimizeCart() {
        LOGGER.info("Minimizing cart...");

            closeCart.click();
        Wait.waitUntilAnjularRequestFinished(driver);
    }

    public void openViewCart() {
        LOGGER.info("View cart opening...");
        viewCart.click();
       Wait.waitFotAjaxIsFinished(driver);
    }

    public String getSubTotalPrice() {
        String price = subTotalAmount.getText().replace("₴", "").replace(",", ".");
        return price;
    }

    public boolean isProductInCartList(String itemId) {
        List<WebElement> products = productList.findElements(By.xpath("//img"));
        return products.contains(itemId);
    }

    public boolean isProductPageDisabled() {
        WebElement backDrop = driver.findElement(By.xpath("//div[@data-hook='cart-widget-backdrop']"));
        return backDrop.isDisplayed();
    }

    public String getEmptyMessage() {

        return cartEmptyMessage.getText();
    }

}
