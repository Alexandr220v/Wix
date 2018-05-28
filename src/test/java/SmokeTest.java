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
//        Assert.assertEquals(header.getHome().getText() , "HOME","HOME label is not displayed");
  //      Assert.assertEquals(header.getContractForm().getText() , "CONTACT FORM","CONTACT FORM label is not displayed");

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.getMailTo().isDisplayed() , "MailTo withImage is not displayed");

        homePage.clickShop();
        ProductListTable productList = new ProductListTable(driver);
       // Assert.assertEquals(productList.getNumberOfProducts() , 12,"Number of products are different");
        // Assert.assertEquals(productList.getProductInfo(glassesId).getPrice() , "20.00","Price is different");

        productList.selectItemFromGallery(glassesId);
        ProductReviewPage reviewPage = new ProductReviewPage(driver);
        reviewPage.addItemToCart();
        CartListWidget listItemsPage = new CartListWidget(driver);
        listItemsPage.removeItemFromCart(glassesId);
        listItemsPage.minimizeCart();
        ProductReviewPage reviewPage3 = new ProductReviewPage(driver);
        reviewPage3.addItemToCart();
        CartListWidget listItemsPage3 = new CartListWidget(driver);
        listItemsPage3.minimizeCart();
        HeaderPanelWidget basePage = new HeaderPanelWidget(driver);
        basePage.openStore();
        CartBagWidget bagPage = new CartBagWidget(driver);
        bagPage.openCartWidget();
        CartListWidget listItemsPage1 = new CartListWidget(driver);
        listItemsPage1.minimizeCart();
        ProductListTable productList1 = new ProductListTable(driver);
        productList1.selectItemFromGallery(scarfId);
        ProductReviewPage reviewPage1 = new ProductReviewPage(driver);
        reviewPage1.addItemToCart();
       // Assert.assertEquals(bagPage.getNumberOfProduct(),1);
       // Assert.assertTrue(listItemsPage.isProductPageDisabled());
        CartListWidget listItemsPage2 = new CartListWidget(driver);
        listItemsPage2.openViewCart();
       // Assert.assertEquals(listItemsPage.getSubTotalPrice(),"20.00");
        //Assert.assertTrue(listItemsPage.isProductPageDisabled());
        CartPage cartPage = new CartPage(driver);
        cartPage.changeNumberOfProduct(glassesId,quantity);
     //  Assert.assertEquals(cartPage.getTotalPriceOfProduct(glassesId),"100");
       // Assert.assertEquals(cartPage.getSubTotalAmountOfProducts(),"120");
        cartPage.removeProduct(scarfId);
      //  Assert.assertEquals(cartPage.getProductName(scarfId),null);
       // Assert.assertEquals(cartPage.getSubTotalAmountOfProducts(),"100");





    }
}
