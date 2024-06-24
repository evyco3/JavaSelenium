package org.evy.framework.pages.product;

import org.evy.framework.drivers.WebDriverFactory;
import org.evy.framework.enums.LogType;
import org.evy.framework.pages.BasePage;
import org.evy.framework.pages.checkout.CartPage;
import org.evy.framework.utils.LoggerUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;

public class ProductPage extends BasePage {

    @FindBy(css = "div.add-to-cart-buttons>button")
    private WebElement addToCartBtn;

    @FindBy(css = "input[id='qty']")
    private WebElement quantity;

    @Step("Select product color '{color}'")
    public ProductPage selectProductColor(String color) {
        try {
            String colorStringValue = String.format("ul[id='configurable_swatch_color'] img[alt='%s']", color);
            WebElement colorElement = WebDriverFactory.getInstance().getDriver().findElement(By.cssSelector(colorStringValue));
            click(colorElement, color);
            LoggerUtils.log(getClass(),LogType.INFO,"Selected product color: " + color);
        } catch (Exception e) {
            LoggerUtils.log(getClass(),LogType.ERROR,"Failed to select product color: " + color+e);
            throw new RuntimeException("Failed to select product color: " + color, e);
        }
        return this;
    }

    @Step("Select product size '{size}'")
    public ProductPage selectProductSize(String size) {
        try {
            String sizeStringValue = String.format("ul[id='configurable_swatch_size'] a[name='%s']", size);
            WebElement sizeElement = WebDriverFactory.getInstance().getDriver().findElement(By.cssSelector(sizeStringValue));
            click(sizeElement, size);
            LoggerUtils.log(getClass(),LogType.INFO,"Selected product size: " + size);
        } catch (Exception e) {
            LoggerUtils.log(getClass(),LogType.ERROR,"Failed to select product size: " + size+ e);
            throw new RuntimeException("Failed to select product size: " + size, e);
        }
        return this;
    }

    @Step("Select product quantity '{quantity}'")
    public ProductPage selectProductQuantity(String quantity) {
        try {
            WebDriver driver = WebDriverFactory.getInstance().getDriver();
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].value = '';", this.quantity);  // Clear the quantity field
            jsExecutor.executeScript("arguments[0].value = arguments[1];", this.quantity, quantity);  // Set the quantity field
            LoggerUtils.log(getClass(),LogType.INFO,"Selected product quantity: " + quantity);
        } catch (Exception e) {
            LoggerUtils.log(getClass(), LogType.ERROR,"Failed to select product quantity: " + quantity+e);
            throw new RuntimeException("Failed to select product quantity: " + quantity, e);
        }
        return this;
    }

    @Step("Click 'Add to Cart' button")
    public CartPage clickAddToCartButton() {
        try {
            click(this.addToCartBtn, "add to cart button");
            waitForPageTitleToBeEqualsTo("Shopping Cart");
            LoggerUtils.log(getClass(),LogType.INFO,"Clicked 'Add to Cart' button and navigated to Shopping Cart page");
            return new CartPage();
        } catch (Exception e) {
            LoggerUtils.log(getClass(),LogType.ERROR,"Failed to click 'Add to Cart' button"+e);
            throw new RuntimeException("Failed to click 'Add to Cart' button", e);
        }
    }
}
