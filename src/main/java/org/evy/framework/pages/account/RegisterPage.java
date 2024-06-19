package org.evy.framework.pages.account;

import io.qameta.allure.Step;
import org.evy.framework.enums.LogType;
import org.evy.framework.pages.BasePage;
import org.evy.framework.utils.LoggerUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * This class represents the registration page of the application and provides methods to perform user registration
 * and retrieve registration response messages.
 * It extends the BasePage class and uses the Page Object Model pattern for interacting with web elements.
 */

public class RegisterPage extends BasePage {

    @FindBy(css = "#firstname")
    private WebElement firstName;
    @FindBy(css = "#lastname")
    private WebElement lastName;
    @FindBy(css = "#email_address")
    private WebElement email;
    @FindBy(css = "#password")
    private WebElement password;
    @FindBy(css = "#confirmation")
    private WebElement confirmation;
    @FindBy(css = "button[title*='Reg']")
    private WebElement registerBtn;
    @FindBy(css = ".success-msg span")
    private WebElement successRegistrationMsg;
    @FindBy(css = ".error-msg>ul>li:first-of-type span")
    private WebElement invalidEmailErrorsMsg;
    @FindBy(css = "#advice-validate-cpassword-confirmation")
    private WebElement invalidMismatchPasswordsMsg;
    @FindBy(css = "#advice-validate-password-password")
    private WebElement invalidTooShortPasswordsMsg;

    @Step("Perform registration with firstName: {firstName}, lastName: {lastName}, email: {email}, password: {password}, confirmation: {confirmation}")
    public <T>T performRegistration(String firstName,String lastName,String email,String password,String confirmation,boolean criteria,Class<T>nextPageClass){
        try{
            sendKeys(this.firstName,firstName,"firstName");
            sendKeys(this.lastName,lastName,"lastName");
            sendKeys(this.email,email,"email");
            sendKeys(this.password,password,"password");
            sendKeys(this.confirmation,confirmation,"confirmation");
            click(registerBtn,"registration button");

            if(criteria){
                waitForPageTitleToBeEqualsTo("Tealium Ecommerce Demo");
                LoggerUtils.log(getClass(), LogType.INFO,"Success Registration Moving to HomePage");
            }

            return nextPageClass.getDeclaredConstructor().newInstance();

        }catch (Exception e){
            LoggerUtils.log(getClass(),LogType.ERROR,"Failed Registration "+e.getCause());
            throw new RuntimeException("Failed to Register to site ",e.getCause());
        }

    }

    @Step("Get registration response message for operation: {operation}")

    public String getRegistrationResponseMessage(String operation){
        return switch (operation){
            case "valid registration"->getText(successRegistrationMsg,"success registration message");
            case "invalid email format"->getText(invalidEmailErrorsMsg,"invalid email format message");
            case "invalid email in use"->getText(invalidEmailErrorsMsg,"invalid email in use");
            case "invalid passwords mismatch"->getText(invalidMismatchPasswordsMsg,"invalid mismatch passwords message");
            case "invalid passwords length"->getText(invalidTooShortPasswordsMsg,"invalid passwords length message");
            default -> throw new IllegalStateException("Unexpected value: " + operation);
        };
    }
}
