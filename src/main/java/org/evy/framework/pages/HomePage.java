package org.evy.framework.pages;

import io.qameta.allure.Step;
import org.evy.framework.pages.account.AccountNavigator;
import org.evy.framework.pages.product.ProductDropdownNavigator;

public class HomePage extends BasePage {

    @Step("Get instance of HomePage")
    public static HomePage getInstance() {
        return new HomePage();
    }

    @Step("Get AccountNavigator from HomePage")
    public AccountNavigator getAccountNavigator() {
        return new AccountNavigator();
    }

    @Step("Get ProductDropdownNavigator from HomePage")
    public ProductDropdownNavigator getProductDropdownNavigator() {
        return new ProductDropdownNavigator();
    }
}
