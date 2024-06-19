package org.evy.framework.pages.account;

import io.qameta.allure.Step;
import org.evy.framework.enums.LogType;
import org.evy.framework.pages.BasePage;
import org.evy.framework.utils.LoggerUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Objects;

/**
 * The {@code LoginPage} class provides functionality for logging into the application.
 * It extends the {@link BasePage} class and utilizes WebDriver elements to interact with
 * the user interface for performing login operations.
 *
 * <p>This class includes a method to perform a login and navigate to a specified next page or stayed in the current page
 */
public class LoginPage extends BasePage {

    @FindBy(css = "#email")
    private WebElement email;
    @FindBy(css = "#pass")
    private WebElement password;
    @FindBy(css = "#send2")
    private WebElement loginBtn;
    @FindBy(css = "p.welcome-msg")
    private WebElement successLoginMsg;
    @FindBy(css = ".error-msg span")
    private WebElement failLoginMsg;

    /**
     * Performs login with the given email and password. Depending on the criteria, it waits for
     * the page title to match "My Account" before returning an instance of the specified next page class.
     */
    @Step("Perform login with email: {email} and password: {password}")
    public <T> T performLogin(String email, String password, boolean criteria, Class<T> nextPageClass) {
        try {
            sendKeys(this.email, email, "email");
            sendKeys(this.password, password, "password");
            click(loginBtn, "login button");
            if (criteria) {
                waitForPageTitleToBeEqualsTo("My Account");
                LoggerUtils.log(getClass(), LogType.INFO, "Success Login Moving to myAccountPage");
            }
            return nextPageClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            LoggerUtils.log(getClass(), LogType.ERROR, "Failed to complete login process");
            throw new RuntimeException("Failed to complete login process", e);
        }
    }

    @Step("Get login response message for operation: {operation}")
    public String getLoginResponseMessage(String operation) {
        try {
            if (Objects.equals(operation, "valid login")) {
                return getText(successLoginMsg, "success login message");
            } else {
                return getText(failLoginMsg, "fail login message");
            }
        } catch (Exception e) {
            LoggerUtils.log(getClass(), LogType.ERROR, "Error retrieving login message: " + e.getMessage());
            throw new RuntimeException("Failed to retrieving login message ",e);
        }
    }
}
