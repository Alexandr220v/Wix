package steps;

import net.thucydides.core.annotations.Step;
import ui.pages.CartPage;
import ui.pages.widgets.CartBagWidget;
import ui.pages.widgets.CartListWidget;

public class CartSteps {

    CartPage cartPage;
    CartBagWidget cartBagWidget;
    CartListWidget cartListWidget;

    @Step
    public void checkProductName(String name) {
        cartPage.getProductName(name);
    }

}
