package ui.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage {

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
    @FindBy(xpath = "//dd[@data-hook='estimated-shipping']")
    private WebElement shipping;
    @FindBy(xpath = "//dd[@data-hook='estimated-total']")
    private WebElement totalAmount;
    @FindBy(xpath = "//button[@data-hook='checkout-button-button']")
    private WebElement checkOut;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }


    public void changeQuantityOfItemTo(String item, int quantity) {
        List<WebElement> rows = itemsTable.findElements(By.xpath("//section[@data-hook='item']"));
        for (WebElement row : rows) {
            if (row.findElement(By.xpath("//img[contains(@src,'" + item + "')]")).isDisplayed()) {
                row.findElement(By.xpath("//*[@data-hook='product-quantity']//input[@type='number']")).
                        sendKeys(String.valueOf(quantity));
                break;
            }
        }
    }

    public void removeItemFromCart(String item) {
            List<WebElement> rows = itemsTable.findElements(By.xpath("//section[@data-hook='item']"));
            for (WebElement row : rows) {
                if (row.findElement(By.xpath("//img[contains(@src,'"+ item + "')]")).isDisplayed()) {
                    row.findElement(By.xpath("//button[@data-hook='remove-button']")).click();
                    break;
                }
        }
    }
}
