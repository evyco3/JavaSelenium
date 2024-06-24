package org.evy.framework.pages.checkout;

import org.evy.framework.pages.BasePage;
import org.evy.framework.utils.LoggerUtils;
import org.evy.framework.enums.LogType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import io.qameta.allure.Step;

public class PaymentPage extends BasePage {

    @FindBy(css = "button[onclick='payment.save()']")
    private WebElement proceedToOrderViewBtn;

    @Step("Proceed to order view page")
    public OrderViewPage proceedToOrderViewPage() {
        try {
            click(this.proceedToOrderViewBtn, "proceed to order view page");
            LoggerUtils.log(getClass(), LogType.INFO, "Proceeded to order view page");
            return new OrderViewPage();
        } catch (Exception e) {
            LoggerUtils.log(getClass(), LogType.ERROR, "Failed to proceed to order view page"+e);
            throw new RuntimeException("Failed to proceed to order view page", e);
        }
    }
}
