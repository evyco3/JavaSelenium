package org.evy.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.evy.framework.data.RegisterData;
import org.evy.framework.enums.LogType;
import org.evy.framework.pages.HomePage;
import org.evy.framework.pages.account.RegisterPage;
import org.evy.framework.utils.LoggerUtils;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.evy.framework.utils.AssertionUtils.assertEquality;

/**
 * This class contains test cases for user registration scenarios.
 * It uses the TestNG framework for running tests and Allure for reporting.
 * Each test method is designed to validate different registration scenarios
 * by providing various sets of user data and verifying the expected responses.
 *
 *  <p>The tests navigate from the homepage clicking account dropdown->clicking the register link button -> landing in register page,
 *  perform the registration action, and verify the registration response message.
 *  @see BaseTest
 *  @see HomePage
 *  @see RegisterPage
 *
 */
@Epic("User Authentication")
@Feature("Register Tests")
public class RegisterTest extends BaseTest {

    @Test(dataProvider = "registerData", dataProviderClass = RegisterData.class)
    @Parameters({"firstName", "lastName", "email", "password", "confirmation", "operation", "expectedResponseMsg"})
    @Story("User Registration Scenarios")
    @Description("This test validates the user registration process with different sets of data and checks the response messages.")
    public void testUserRegistrationScenarios(String firstName, String lastName, String email, String password, String confirmation, String operation, String expectedResponseMsg) {
        String actualResponseMsg = performRegistrationAndGetResponseMessage(firstName, lastName, email, password, confirmation, operation);
        assertEquality(actualResponseMsg, expectedResponseMsg, "Verify if (" + actualResponseMsg + ") equals to (" + expectedResponseMsg + ")");
    }

    @Step("Perform registration and get response message for firstName: {firstName}, lastName: {lastName}, email: {email}, password: {password}, confirmation: {confirmation}, operation: {operation}")
    private String performRegistrationAndGetResponseMessage(String firstName, String lastName, String email, String password, String confirmation, String operation) {
        try {
            return HomePage.getInstance().getAccountNavigator()
                    .navigateToRegisterPage()
                    .performRegistration(firstName, lastName, email, password, confirmation, false, RegisterPage.class)
                    .getRegistrationResponseMessage(operation);
        } catch (Exception e) {
            LoggerUtils.log(getClass(), LogType.ERROR, "Error During Register Test " + e);
            throw new RuntimeException("Error During Register Test", e);
        }
    }
}
