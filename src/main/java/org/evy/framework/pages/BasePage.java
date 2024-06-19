package org.evy.framework.pages;

import org.evy.framework.drivers.WebDriverFactory;
import org.evy.framework.enums.LogType;
import org.evy.framework.utils.ActionUtils;
import org.evy.framework.utils.LoggerUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class BasePage {
    private final WebDriver driver;


    public BasePage(){
        this.driver= WebDriverFactory.getInstance().getDriver();
        PageFactory.initElements(driver,this);
    }

    private WebElement waitForElementToBeVisible(WebElement element) {
        try {
            Wait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(30))
                    .pollingEvery(Duration.ofSeconds(2))
                    .ignoring(NoSuchElementException.class)
                    .ignoring(StaleElementReferenceException.class);
            return wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            LoggerUtils.log(getClass(), LogType.ERROR,"Element is not visible after waiting");
            throw new RuntimeException("Element not visible after waiting", e.getCause());
        }
    }

    protected void sendKeys(WebElement element,String value,String elementName){
        ActionUtils.execAction(getClass(),()->
                waitForElementToBeVisible(element).sendKeys(value),
                "sent keys to "+elementName+":"+value,
                "Failed to send keys to "+elementName
                );
    }

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

    protected String getText(WebElement element, String elementName) {
        return ActionUtils.execFunction(getClass(),
                () -> waitForElementToBeVisible(element).getText(),
                "Retrieved text from " + elementName,
                "Unable to retrieve text from " + elementName);
    }




}
