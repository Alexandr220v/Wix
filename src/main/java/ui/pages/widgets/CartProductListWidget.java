package ui.pages.widgets;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Wait;

import java.util.List;

public class CartProductListWidget {

    private WebDriver driver;
    public static final Logger LOGGER = Logger.getLogger(CartProductListWidget.class);
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
    private By cartWidgetHidden = By.xpath("//div[@class='minicart']");
    private By cartWidgetActive = By.xpath("//div[@class='minicart active']");
    private String frameLocator = "//iframe[contains(@src,'https://ecom.wix.com/storefront/cartwidgetPopup')]";

    public CartProductListWidget(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void removeItemFromCart(String item) throws InterruptedException {
        driver.switchTo().frame(driver.findElement(By.xpath(frameLocator)));
        LOGGER.info("Removing " + item + " from cart...");
        WebElement productToremove = cartContent.findElement(By.xpath("//li[@data-hook='cart-widget-item']" +
                "//img[contains(@src,'" + item + "')]" +
                "/ancestor::li[@data-hook='cart-widget-item']//button"));
        Actions actions = new Actions(driver);
        actions.moveToElement(productToremove);
        actions.click().build().perform();
        Wait.waitFotAjaxIsFinished(driver);
        LOGGER.info("Product " + item + " should be removed from cart");
        driver.switchTo().defaultContent();
    }

    public void minimizeCart() {
        driver.switchTo().frame(driver.findElement(By.xpath(frameLocator)));
        LOGGER.info("Minimizing cart...");
        Wait.waitElementIsPresent(driver, cartWidgetActive);
        closeCart.click();
        Wait.waitFotAjaxIsFinished(driver);
        Wait.waitElementIsNotPresent(driver, cartWidgetActive);
        driver.switchTo().defaultContent();
    }

    public void openViewCart() {
        driver.switchTo().frame(driver.findElement(By.xpath(frameLocator)));
        LOGGER.info("View cart opening...");
        viewCart.click();
        Wait.waitFotAjaxIsFinished(driver);
        driver.switchTo().defaultContent();
    }

    public String getSubTotalPrice() {
        driver.switchTo().frame(driver.findElement(By.xpath(frameLocator)));
        String price = subTotalAmount.getText().replace("₴", "").replace(",", ".");
        driver.switchTo().defaultContent();
        return price;
    }

    public boolean isProductInCartList(String itemId) {
        driver.switchTo().frame(driver.findElement(By.xpath(frameLocator)));
        List<WebElement> products = productList.findElements(By.xpath("//img"));
        boolean isItemPresented = products.contains(itemId);
        driver.switchTo().defaultContent();
        return isItemPresented;
    }

    public boolean isProductPageDisabled() {
        driver.switchTo().frame(driver.findElement(By.xpath(frameLocator)));
        WebElement backDrop = driver.findElement(By.xpath("//div[@data-hook='cart-widget-backdrop']"));
        boolean isProductPageDisabled = backDrop.isDisplayed();
        driver.switchTo().defaultContent();
        return isProductPageDisabled;
    }

    public String getEmptyMessage() {
        driver.switchTo().frame(driver.findElement(By.xpath(frameLocator)));
        String message = cartEmptyMessage.getText();
        driver.switchTo().defaultContent();
        return message;
    }

}
