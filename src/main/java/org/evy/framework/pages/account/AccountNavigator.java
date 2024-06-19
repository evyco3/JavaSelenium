package org.evy.framework.pages.account;

import org.evy.framework.enums.LogType;
import org.evy.framework.pages.BasePage;
import org.evy.framework.utils.LoggerUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * The {@code AccountNavigator} class provides navigation functionality to various account-related pages
 * within the application. It extends the {@link BasePage} class and utilizes WebDriver elements to
 * interact with the user interface.
 *
 * <p>This class includes methods to navigate to the login, register, and logout pages by interacting
 * with the account dropdown menu. Each navigation action is logged for debugging and tracking purposes.
 *
 * <p>Additional navigation methods can be added to this class to handle other account-related pages,
 * such as "My Cart", "My Account", or "Checkout" pages, enhancing the navigation capabilities of the
 * application.
 */

 public class AccountNavigator extends BasePage {

    @FindBy(css = "a[data-target-element='#header-account']")
    private WebElement accountDropdownBtn;
    @FindBy(css = "a[title='Log In']")
    private WebElement loginBtn;
    @FindBy(css = "a[title='Register']")
    private WebElement registerBtn;
    @FindBy(css = "a[title='Log Out']")
    private WebElement logoutBtn;

    private void navigateAndLogToPage(WebElement element,String elementName,String pageTitle,String pageClassName){
        try{
            click(accountDropdownBtn,"account dropdown button");
            click(element,elementName);
            waitForPageTitleToBeEqualsTo(pageTitle);
            LoggerUtils.log(getClass(),LogType.INFO,"Navigate to "+pageClassName);

        }catch (Exception e){
            LoggerUtils.log(getClass(), LogType.ERROR,"Failed to Navigate to "+pageClassName);
            throw new RuntimeException("Failed to navigate to "+pageClassName,e.getCause());
        }
    }

    public LoginPage navigateToLoginPage(){
        navigateAndLogToPage(loginBtn,"login button","Customer Login","loginPage");
        return new LoginPage();
    }

    public RegisterPage navigateToRegisterPage(){
        navigateAndLogToPage(registerBtn,"register button","Create New Customer Account","registerPage");
        return new RegisterPage();
    }

    public BasePage navigateToLogoutPage(){
        navigateAndLogToPage(logoutBtn,"logout button","Tealium Ecommerce Demo","logoutPage");
        return this;
    }
}
