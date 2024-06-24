package org.evy.framework.pages.checkout;

import org.evy.framework.drivers.WebDriverFactory;
import org.evy.framework.enums.LogType;
import org.evy.framework.pages.BasePage;
import org.evy.framework.utils.LoggerUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import io.qameta.allure.Step;

public class ShipmentPage extends BasePage {

    @FindBy(css = "#s_method_flatrate_flatrate")
    private WebElement flatRateShip;

    @FindBy(css = "button[onClick='shippingMethod.save()']")
    private WebElement proceedToPaymentPageBtn;

    @Step("Proceed to payment page")
    public PaymentPage proceedToPaymentPage() {
        try {
            click(this.flatRateShip, "flat rate shipment method");
            click(this.proceedToPaymentPageBtn, "proceed to payment page");
            waitForElementToBeVisible(WebDriverFactory.getInstance().getDriver().findElement(By.cssSelector("label[for='p_method_cashondelivery']")));
            LoggerUtils.log(getClass(), LogType.INFO, "Proceeded to payment page");
            return new PaymentPage();
        } catch (Exception e) {
            LoggerUtils.log(getClass(), LogType.ERROR, "Failed to proceed to payment page"+e);
            throw new RuntimeException("Failed to proceed to payment page", e);
        }
    }
}
