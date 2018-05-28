package steps;

import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import ui.pages.HomePage;
import ui.pages.widgets.HeaderPanelWidget;
import ui.table.ProductListTable;

public class BaseSteps {
    HomePage homePage;
    HeaderPanelWidget panelWidget;
    ProductListTable productList;

    @Step
    public void openProductList(String glassesId) {
        homePage.clickShop();
        Assert.assertEquals("Number of products is different", productList.getNumberOfProducts(), 12);
        Assert.assertEquals("Price is different", productList.getProductInfo(glassesId).getPrice(), "20.00");
    }

    @Step
    public void checkHeadersText() {

        String homeText = panelWidget.getHome().getText();
        String contactForm = panelWidget.getContractForm().getText();
        Assert.assertEquals("HOME label is not displayed", "HOME", homeText);
        Assert.assertEquals("CONTACT FORM label is not displayed", "CONTACT FORM", contactForm);

    }
}

