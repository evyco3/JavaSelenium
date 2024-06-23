package org.evy.framework.pages;

import io.qameta.allure.Step;
import org.evy.framework.drivers.WebDriverFactory;
import org.evy.framework.enums.LogType;
import org.evy.framework.utils.ActionUtils;
import org.evy.framework.utils.LoggerUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
/**
 * The {@code BasePage} class serves as a foundation for all page classes in the framework.
 * It provides common web interaction methods such as clicking, sending keys, waiting for elements,
 * and retrieving text. These methods utilize WebDriver and Selenium's support classes to perform actions.
 * The class also handles logging and exception management to ensure robust and maintainable test automation.
 *
 * <p>This class is designed to be extended by specific page classes, which can then leverage the
 * provided methods to interact with web elements and perform actions.
 *
 * <p>Key functionalities include:
 * <ul>
 *     <li>Waiting for elements to be visible</li>
 *     <li>Sending keys to elements</li>
 *     <li>Clicking on elements</li>
 *     <li>Retrieving text from elements</li>
 *     <li>Waiting for page titles</li>
 *     <li>Moving to elements</li>
 * </ul>
 *
 * <p>The constructor initializes the WebDriver and uses PageFactory to initialize web elements.
 */

public class BasePage {
    private final WebDriver driver;

    public BasePage() {
        this.driver = WebDriverFactory.getInstance().getDriver();
        PageFactory.initElements(driver, this);
    }

    @Step("Wait for element to be visible")
    protected WebElement waitForElementToBeVisible(WebElement element) {
        try {
            Wait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(30))
                    .pollingEvery(Duration.ofSeconds(2))
                    .ignoring(NoSuchElementException.class)
                    .ignoring(StaleElementReferenceException.class);
            return wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            LoggerUtils.log(getClass(), LogType.ERROR, "Element is not visible after waiting");
            throw new RuntimeException("Element not visible after waiting", e.getCause());
        }
    }

    @Step("Send keys '{value}' to element '{elementName}'")
    protected void sendKeys(WebElement element, String value, String elementName) {
        ActionUtils.execAction(getClass(), () ->
                        waitForElementToBeVisible(element).sendKeys(value),
                "Sent keys to " + elementName + ": " + value,
                "Failed to send keys to " + elementName
        );
    }

    @Step("Click on element '{elementName}'")
    protected void click(WebElement element, String elementName) {
        ActionUtils.execAction(getClass(),
                () -> {
                    WebElement visibleElement = waitForElementToBeVisible(element);
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    js.executeScript("arguments[0].click();", visibleElement);
                },
                "Clicked on " + elementName,
                "Unable to click on " + elementName);
    }

    @Step("Get text from element '{elementName}'")
    protected String getText(WebElement element, String elementName) {
        return ActionUtils.execFunction(getClass(),
                () -> waitForElementToBeVisible(element).getText(),
                "Retrieved text from " + elementName,
                "Unable to retrieve text from " + elementName);
    }

    @Step("Wait for page title to be '{pageTitle}'")
    protected void waitForPageTitleToBeEqualsTo(String pageTitle) {
        ActionUtils.execAction(getClass(), () ->
                        new WebDriverWait(WebDriverFactory.getInstance().getDriver(), Duration.ofSeconds(10))
                                .until(ExpectedConditions.titleIs(pageTitle)),
                "Page Title: " + pageTitle,
                "Page Title: " + driver.getTitle() + " Not Equals to " + pageTitle
        );
    }

    @Step("Wait for page title to contain '{pageTitle}'")
    protected void waitForPageTitleToContain(String pageTitle) {
        ActionUtils.execAction(getClass(), () ->
                        new WebDriverWait(WebDriverFactory.getInstance().getDriver(), Duration.ofSeconds(10))
                                .until(ExpectedConditions.titleContains(pageTitle)),
                "Page Title Contains: " + pageTitle,
                "Page Title do not contains " + pageTitle
        );
    }

    @Step("Get current URL")
    public String getCurrentUrl() {
        return ActionUtils.execFunction(getClass(), driver::getCurrentUrl,
                "Success to retrieve current URL",
                "Failed to retrieve URL"
        );
    }

    @Step("Move to element '{elementName}'")
    protected void moveTo(WebElement element, String elementName) {
        ActionUtils.execAction(getClass(), () -> {
                    Actions actions = new Actions(driver);
                    actions.moveToElement(element).perform();
                },
                "Move to " + elementName,
                "Failed to move to " + elementName
        );
    }
}
