package ui.pages;

import net.thucydides.core.pages.PageObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.math.BigDecimal;
import java.util.List;

public class CartPage extends PageObject {

    private WebDriver driver;
    public static final Logger LOGGER = Logger.getLogger(CartPage.class);
    @FindBy(xpath = "//div[@data-hook='item-list']")
    private WebElement itemsTable;
    @FindBy(xpath = "//*[@data-hook='product-quantity']//input[@type='number']")
    private WebElement productQuantity;
    @FindBy(xpath = "//span[@data-hook='product-withPrice']")
    private WebElement productPrice;
    @FindBy(xpath = "//span[@data-hook='product-total-withPrice']")
    private WebElement productTotalPrice;
    @FindBy(xpath = "//button[@data-hook='remove-button']")
    private WebElement removeItem;
    @FindBy(xpath = "//dd[@data-hook='subtotal-text']")
    private WebElement subTotal;
    @FindBy(xpath = "//section[@data-hook='total-area']")
    private WebElement totalSection;
    @FindBy(xpath = "//button[@data-hook='checkout-button-button']")
    private WebElement checkOut;
    @FindBy(xpath = "//div[@data-hook='cart-content']")
    private WebElement productListSection;
    @FindBy(xpath = "//dd[@data-hook='subtotal-text']")
    private WebElement subTotalAmount;
    @FindBy(id = "total-sum")
    private WebElement totalSum;
    private WebElement frame;
    private String frameID = "TPAMultiSection_jh9acbtyiframe";

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public String getProductName(String itemId) {
        driver.switchTo().frame(frameID);
        List<WebElement> products = productListSection.findElements(By.xpath("//section[@data-hook='item']"));
        for (WebElement elem : products) {
            WebElement name = elem.findElement(By.xpath("//img"));
            if (name.getAttribute("src").contains(itemId)) {
                String productName = name.getText().substring(name.getText().indexOf("media/"), name.getText().indexOf(".jpg"));
                driver.switchTo().defaultContent();
                return productName;

            }
        }
        driver.switchTo().defaultContent();
        return null;

    }

    public boolean isProductInCart(String itemId) {
        driver.switchTo().frame(frameID);
        List<WebElement> products = productListSection.findElements(By.xpath("//section[@data-hook='item']//img"));
        boolean isInCart = products.contains(itemId);
        driver.switchTo().defaultContent();
        return isInCart;

    }

    public void changeNumberOfProduct(String itemId, int num) {
        driver.switchTo().frame(frameID);
        List<WebElement> products = productListSection.findElements(By.xpath("//section[@data-hook='item']"));
        for (WebElement elem : products) {
            WebElement name = elem.findElement(By.xpath("//img"));
            if (name.getAttribute("src").contains(itemId)) {
                elem.findElement(By.xpath("//input")).sendKeys(Keys.CONTROL);
                elem.findElement(By.xpath("//input")).sendKeys(Keys.DELETE);
                elem.findElement(By.xpath("//input")).sendKeys(String.valueOf(num));
                driver.switchTo().defaultContent();
                break;
            }
        }
        driver.switchTo().defaultContent();
    }

    public String getTotalPriceOfProduct(String itemId) {
        driver.switchTo().frame(frameID);
        List<WebElement> products = productListSection.findElements(By.xpath("//section[@data-hook='item']"));
        for (WebElement elem : products) {
            WebElement name = elem.findElement(By.xpath("//img"));
            if (name.getAttribute("src").contains(itemId)) {
                BigDecimal price = new BigDecimal(elem.findElement(By.xpath("//span[@data-hook='product-total-price']")).getText().
                        replace("₴", "").replace(",", "."));
                driver.switchTo().defaultContent();
                return String.valueOf(price);
            }
        }
        driver.switchTo().defaultContent();
        return null;
    }

    public void removeProduct(String itemId) {
        driver.switchTo().frame(frameID);
        LOGGER.info("Removing product from cart ...");
        List<WebElement> products = productListSection.findElements(By.xpath("//section[@data-hook='item']"));
        for (WebElement elem : products) {
            WebElement name = elem.findElement(By.xpath("//img"));
            if (name.getAttribute("src").contains(itemId)) {
                elem.findElement(By.xpath("//button[@data-hook='remove-button']")).click();
                LOGGER.info("Product should be removed from cart ...");
                break;
            }
        }
        driver.switchTo().defaultContent();
    }

    public String getSubTotalAmountOfProducts() {
        driver.switchTo().frame(frameID);
        String amount = subTotalAmount.getText().replace("₴", "").replace(",", ".");
        driver.switchTo().defaultContent();
        return amount;

    }

    public String getTotalSum() {
        driver.switchTo().frame(frameID);
        String sum = totalSum.getText().replace("₴", "").replace(",", ".");
        driver.switchTo().defaultContent();
        return sum;

    }

    public void pressCheckoutButton() {
        driver.switchTo().frame(frameID);
        checkOut.click();
        driver.switchTo().defaultContent();

    }
}
