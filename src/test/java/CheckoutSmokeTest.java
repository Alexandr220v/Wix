import org.testng.Assert;
import org.testng.annotations.Test;
import ui.pages.*;
import ui.pages.widgets.CartBagWidget;
import ui.pages.widgets.CartProductListWidget;
import ui.pages.widgets.HeaderPanelWidget;
import ui.table.ProductListTable;

public class CheckoutSmokeTest extends BaseTest {


    @Test
    public void verifyMainUserFlow() throws InterruptedException {
        final String glassesId = "a9ff3b_1eec973ae9eb43f4bca9876e5d90f6fa";
        final String scarfId = "a9ff3b_ea68bd8398ac489a8e4e8b99755f96b0";
        int quantity = 3;
        HeaderPanelWidget header = new HeaderPanelWidget(driver);
        Assert.assertEquals(header.getHome().getText(), "HOME", "HOME label is not displayed");
        Assert.assertEquals(header.getContractForm().getText(), "CONTACT FORM", "CONTACT FORM label is not displayed");

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.getMailTo().isDisplayed(), "MailTo withImage is not displayed");

        homePage.clickShop();
        ProductListTable productList = new ProductListTable(driver);
        Assert.assertEquals(productList.getNumberOfProducts(), 12, "Number of products are different");
        Assert.assertEquals(productList.getProductInfo(glassesId).getPrice(), "20.00", "Price is different");

        productList.selectItemFromGallery(glassesId);
        ProductReviewPage reviewPage = new ProductReviewPage(driver);
        reviewPage.addItemToCart();
        CartProductListWidget cartProductListWidget = new CartProductListWidget(driver);
        cartProductListWidget.removeItemFromCart(glassesId);
        Assert.assertEquals(cartProductListWidget.getEmptyMessage(),"Cart is empty");
        Assert.assertTrue(cartProductListWidget.isProductPageDisabled());

        cartProductListWidget.minimizeCart();
        CartBagWidget cartBagWidget = new CartBagWidget(driver);
        Assert.assertEquals(cartBagWidget.getNumberOfProduct(),0);

        reviewPage.addItemToCart();
        cartProductListWidget.minimizeCart();
        Assert.assertEquals(cartBagWidget.getNumberOfProduct(),1);
        HeaderPanelWidget basePage = new HeaderPanelWidget(driver);
        basePage.openStore();
        cartBagWidget.openCartFromHeaderWidget();
        Assert.assertTrue(cartProductListWidget.isProductPageDisabled());
        Assert.assertEquals(cartProductListWidget.getSubTotalPrice(),"20.00");

        cartProductListWidget.minimizeCart();
        productList.selectItemFromGallery(scarfId);
        reviewPage.addItemToCart();
        Assert.assertEquals(cartBagWidget.getNumberOfProduct(), 2);
        Assert.assertTrue(cartProductListWidget.isProductPageDisabled());
        Assert.assertEquals(cartProductListWidget.getSubTotalPrice(), "60.00");
        Assert.assertTrue(cartProductListWidget.isProductPageDisabled());

        cartProductListWidget.openViewCart();
        CartPage cartPage = new CartPage(driver);
        cartPage.changeNumberOfProduct(glassesId, quantity);
        Assert.assertEquals(cartPage.getTotalPriceOfProduct(glassesId), "60.00");
        Assert.assertEquals(cartPage.getSubTotalAmountOfProducts(), "100.00");

        cartPage.removeProduct(scarfId);
        Assert.assertFalse(cartPage.isProductInCart(scarfId));
        Assert.assertEquals(cartPage.getSubTotalAmountOfProducts(), "100.00");


    }
}
