package org.evy.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.evy.framework.data.LoginData;
import org.evy.framework.enums.LogType;
import org.evy.framework.pages.HomePage;
import org.evy.framework.pages.account.LoginPage;
import org.evy.framework.utils.AssertionUtils;
import org.evy.framework.utils.LoggerUtils;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.evy.framework.utils.AssertionUtils.assertEquality;


/**
 * The {@code LoginTest} class contains test methods for verifying user login functionality.
 * This class uses TestNG for test execution and Allure annotations for detailed reporting.
 *
 * <p>It includes scenarios for valid and invalid login attempts, checking if the actual response
 * matches the expected response.
 *
 * <p>The tests navigate from the homepage clicking account dropdown->clicking the login link button and than landing in login page,
 * perform the login action, and verify the login response message.
 * @see BaseTest
 * @see HomePage
 * @see LoginPage
 */



@Epic("User Authentication")
@Feature("Login Tests")
public class LoginTest extends BaseTest{

    @Test(dataProvider = "loginData", dataProviderClass = LoginData.class)
    @Parameters({"email", "password", "operation", "expectedResult"})
    @Story("User Login Scenarios")
    @Description("Tests user login scenarios with different combinations of email, password, and expected results")
    public void testUserLoginScenarios(String email, String password, String operation, String expectedMsg){
        String actualMsg=performLoginAndGetResponseMessage(email,password,operation);
        assertEquality(actualMsg,expectedMsg,"Verify if ("+actualMsg+") Equals To ("+expectedMsg+")");
    }



    private String performLoginAndGetResponseMessage(String email,String password,String operation){
        try{
            return HomePage.getInstance().getAccountNavigator()
                    .navigateToLoginPage()
                    .performLogin(email,password,false, LoginPage.class)
                    .getLoginResponseMessage(operation);
        }catch (Exception e){
            LoggerUtils.log(getClass(), LogType.ERROR,"Error During login test process "+e.getCause());
            throw new RuntimeException("Error During login test process ",e.getCause());
        }
    }
}
