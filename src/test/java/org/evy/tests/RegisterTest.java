package org.evy.tests;

import org.evy.framework.data.RegisterData;
import org.evy.framework.enums.LogType;
import org.evy.framework.pages.HomePage;
import org.evy.framework.pages.account.RegisterPage;
import org.evy.framework.utils.AssertionUtils;
import org.evy.framework.utils.LoggerUtils;
import org.testng.annotations.Test;

import static org.evy.framework.utils.AssertionUtils.assertEquality;

public class RegisterTest extends BaseTest{

    @Test(dataProvider = "registerData",dataProviderClass = RegisterData.class)
    public void testUserRegistrationScenarios(String firstName, String lastName, String email, String password, String confirmation, String operation, String expectedResponseMsg){
        String actualResponseMsg=performRegistrationAndGetResponseMessage(firstName,lastName,email,password,confirmation,operation);
        assertEquality(actualResponseMsg,expectedResponseMsg,"Verify if ("+actualResponseMsg+") Equals to ("+expectedResponseMsg+")");
    }





    private String performRegistrationAndGetResponseMessage(String firstName,String lastName,String email,String password,String confirmation,String operation){
        try {
            return HomePage.getInstance().getAccountNavigator()
                    .navigateToRegisterPage()
                    .performRegistration(firstName,lastName,email,password,confirmation,false, RegisterPage.class)
                    .getRegistrationResponseMessage(operation);
        }catch (Exception e){
            LoggerUtils.log(getClass(), LogType.ERROR,"Error During Register Test "+e.getCause());
            throw new RuntimeException("Error During Register Test",e.getCause());
        }
    }
}
