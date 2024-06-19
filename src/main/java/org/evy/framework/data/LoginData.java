package org.evy.framework.data;

import org.testng.annotations.DataProvider;

public final class LoginData {

    private LoginData(){}

    @DataProvider(name = "loginData" ,parallel = true)
    public static Object[][]getData(){
        return new Object[][]{
                {"evy@user.co.il","Password123","valid login","WELCOME"},
                {"evy@user.co.il","wrongPassword","invalid login","Invalid login or password."},
                {"wrongEmail@co.il","Password123","invalid login","Invalid login or password."}


        };
    }
}
