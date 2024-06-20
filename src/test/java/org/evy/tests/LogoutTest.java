package org.evy.tests;

import io.qameta.allure.*;
import org.evy.framework.config.ConfigManager;
import org.evy.framework.enums.LogType;
import org.evy.framework.pages.HomePage;
import org.evy.framework.utils.LoggerUtils;
import org.testng.annotations.Test;

import static org.evy.framework.utils.AssertionUtils.assertContains;

/**
 * The {@code LogoutTest} class contains test methods for verifying user logout functionality.
 * This class uses TestNG for test execution and Allure annotations for detailed reporting.
 *
 * <p>It includes a scenario for a valid logout attempt, checking if the actual URL matches the expected URL after logout.
 *
 * <p>The tests navigate from the homepage clicking account dropdown -> clicking the login link button -> landing in the login page,
 * perform the login action, and verify the logout process and resulting URL.
 * @see BaseTest
 * @see HomePage
 */
@Epic("User Authentication")
@Feature("Logout Test")
public class LogoutTest extends BaseTest {

    @Test
    @Story("User logout")
    @Description("This test validates the user logout process and checks  if user logout send the user page back to the homePage")
    public void testUserLogout() {
        String actualUrl = performLoginLogoutAndGetCurrentUrl(ConfigManager.get().email(), ConfigManager.get().password());
        assertContains(actualUrl, "Tealium Ecommerce Demo", "Verify if " + actualUrl + " Contains The expected Url");
    }

    @Step("Perform login, then logout, and get the current URL for email: {email}, password: {password}")
    private String performLoginLogoutAndGetCurrentUrl(String email, String password) {
        try {
            return HomePage.getInstance().getAccountNavigator()
                    .navigateToLoginPage()
                    .performLogin(email, password, true, HomePage.class)
                    .getAccountNavigator()
                    .navigateToLogoutPage()
                    .getCurrentUrl();
        } catch (Exception e) {
            LoggerUtils.log(getClass(), LogType.ERROR, "Error during logout Test " + e);
            throw new RuntimeException("Logout Test Failed", e);
        }
    }
}
