package ui.pages.widgets;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Wait;

import java.util.List;

public class HeaderPanelWidget {

    protected  WebDriver driver;
    public static final Logger LOGGER = Logger.getLogger(HeaderPanelWidget.class);
    @FindBy(id = "comp-jh99ppou0label")
    private WebElement home;
    @FindBy(id = "comp-jh99ppou1label")
    private WebElement contractForm;
    @FindBy(id = "comp-jh9ace6uavatar")
    private WebElement login;
    @FindBy(id = "comp-jh99ppou2label")
    private WebElement stores;
    @FindBy(id = "cart-widget-button")
    private WebElement cartButton;
    private WebDriver frame;

    public HeaderPanelWidget(WebDriver driver) {
        this.driver= driver;
        PageFactory.initElements(driver,this);
        Wait.waitFotAjaxIsFinished(driver);
        Wait.waitUntilAnjularRequestFinished(driver);
    }


    public void openStore() {
        LOGGER.info("Opening store ...");
        stores.click();
        Wait.waitUntilAnjularRequestFinished(driver);
    }

    public WebElement getHome() {
        return home;
    }

    public WebElement getContractForm() {
        return contractForm;
    }

    public WebElement getLogin() {
        return login;
    }

    public String geHeaderButtonColor(String label) {
        List<WebElement> headers = driver.findElements(By.cssSelector(".ddm1repeaterButtonlabel"));
        for (WebElement header : headers) {
         if (header.getText().equals(label)) {
             return header.getCssValue("color");
         }
        }
        return null;
    }
}
