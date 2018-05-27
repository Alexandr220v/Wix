import org.testng.Assert;
import org.testng.annotations.Test;
import ui.pages.*;
import ui.pages.widgets.CartBagWidget;
import ui.pages.widgets.CartListWidget;
import ui.pages.widgets.HeaderPanelWidget;
import ui.table.ProductListTable;

public class SmokeTest extends BaseTest {

    @Test
    public void verifyMainUserFlow () throws InterruptedException {
        final String glassesId = "a9ff3b_1eec973ae9eb43f4bca9876e5d90f6fa";
        final String scarfId = "a9ff3b_ea68bd8398ac489a8e4e8b99755f96b0";
        int quantity = 3;
        HeaderPanelWidget header = new HeaderPanelWidget(driver);
        Assert.assertEquals(header.getHome().getText() , "HOME","HOME label is not displayed");
        Assert.assertEquals(header.getContractForm().getText() , "CONTACT FORM","CONTACT FORM label is not displayed");

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.getMailTo().isDisplayed() , "MailTo withImage is not displayed");

        homePage.goToShop();
        ProductListTable productList = new ProductListTable(driver);
        Thread.sleep(5000);
        Assert.assertEquals(productList.getNumberOfProducts() , 12,"Number of products are different");
        Assert.assertEquals(productList.getProductInfo(glassesId).getPrice() , "20,00","Price is different");
        Assert.assertEquals(header.geHeaderButtonColor("Stores"), "#7FCCF7");

        productList.selectItemFromGallery(glassesId);
        ProductReviewPage reviewPage = new ProductReviewPage(driver);
        reviewPage.addItemToCart();
        CartListWidget listItemsPage = new CartListWidget(driver);
        listItemsPage.removeItemFromCart(glassesId);
        listItemsPage.minimizeCart();
        HeaderPanelWidget basePage = new HeaderPanelWidget(driver);
        basePage.openStore();
        CartBagWidget bagPage = new CartBagWidget(driver);
        bagPage.openCartWidget();
        listItemsPage.minimizeCart();
        productList.selectItemFromGallery(scarfId);
        reviewPage.addItemToCart();
        listItemsPage.openViewCart();
        CartPage cartPage = new CartPage(driver);
        cartPage.changeQuantityOfItemTo(glassesId,quantity);
        cartPage.removeItemFromCart(scarfId);





    }
}
