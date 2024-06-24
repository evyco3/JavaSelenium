package org.evy.framework.pages.checkout;

import org.evy.framework.enums.LogType;
import org.evy.framework.pages.BasePage;
import org.evy.framework.utils.LoggerUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import io.qameta.allure.Step;

public class OrderViewPage extends BasePage {

    @FindBy(css = "button[onClick='review.save();']")
    private WebElement placeOrderBtn;

    @FindBy(css = "h2.sub-title")
    private WebElement successOrderMsg;

    @Step("Get success order message")
    public String getSuccessOrderMessage() {
        try {
            click(this.placeOrderBtn, "place order button");
            LoggerUtils.log(getClass(), LogType.INFO, "Order has been placed successfully");
            return getText(this.successOrderMsg, "success order message");
        } catch (Exception e) {
            LoggerUtils.log(getClass(), LogType.ERROR, "Failed to place order"+e);
            throw new RuntimeException("Failed to place order", e);
        }
    }
}
