package ui.pages;

import net.thucydides.core.pages.PageObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.FrameUtil;
import utils.Wait;

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
    private String frameLocator = "//iframe[@title='Cart Page']";

    public CartPage(WebDriver driver) {
        this.driver = driver;
        driver.switchTo().defaultContent();
        frame = driver.findElement(By.xpath(frameLocator));
        //FrameUtil.switchToFrame(frameLocator, driver);
        FrameUtil.swithcToIFrame( driver,frame);
        PageFactory.initElements(driver, this);
    }


    public String getProductName(String itemId) {
        List<WebElement> products = productListSection.findElements(By.xpath("//section[@data-hook='item']"));
        for (WebElement elem : products) {
            WebElement name = elem.findElement(By.xpath("//img"));
            if (name.getText().contains(itemId)) {
                return name.getText().substring(name.getText().indexOf("media/"), name.getText().indexOf(".jpg"));

            }
        }
        return null;

    }

    public void changeNumberOfProduct(String itemId, int num) {
        List<WebElement> products = productListSection.findElements(By.xpath("//section[@data-hook='item']"));
        for (WebElement elem : products) {
            WebElement name = elem.findElement(By.xpath("//img"));
            if (name.getAttribute("src").contains(itemId)) {
                elem.findElement(By.xpath("//input")).sendKeys(Keys.CONTROL);
                elem.findElement(By.xpath("//input")).sendKeys(Keys.DELETE);
                elem.findElement(By.xpath("//input")).sendKeys(String.valueOf(num));
                break;
            }
        }
    }

    public String getTotalPriceOfProduct(String itemId) {
        List<WebElement> products = productListSection.findElements(By.xpath("//section[@data-hook='item']"));
        for (WebElement elem : products) {
            WebElement name = elem.findElement(By.xpath("//img"));
            if (name.getAttribute("src").contains(itemId)) {
                BigDecimal price = new BigDecimal(elem.findElement(By.xpath("//span[@data-hook='product-total-price']")).getText().
                        replace("₴", "").replace(",", "."));
                BigDecimal quantity = new BigDecimal(elem.findElement(By.
                        xpath("//*[@data-hook='product-quantity']//input[@type='number']")).getAttribute("value"));
                BigDecimal total = price.multiply(quantity);
                return String.valueOf(total);
            }
        }
        return null;
    }

    public void removeProduct(String itemId) {
        List<WebElement> products = productListSection.findElements(By.xpath("//section[@data-hook='item']"));
        for (WebElement elem : products) {
            WebElement name = elem.findElement(By.xpath("//img"));
            if (name.getText().contains(itemId)) {
                elem.findElement(By.xpath("//button[@data-hook='remove-button']")).click();
                break;
            }
        }
    }

    public String getSubTotalAmountOfProducts() {
        String amount = subTotalAmount.getText().replace("₴", "").replace(",", ".");
        return amount;

    }

    public String getTotalSum() {
        String sum = totalSum.getText().replace("₴", "").replace(",", ".");
        return sum;

    }

    public void pressCheckoutButton() {
        checkOut.click();

    }
}
