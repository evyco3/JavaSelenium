package org.evy.framework.data;

import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;

public final class RegisterData {

    private static final Faker faker=new Faker();

    private RegisterData(){}

    private static String getFirstName(){
        return faker.name().firstName();
    }

    private static  String getLastName(){
        return faker.name().lastName();
    }

    private static String getEmail(){
        return faker.internet().emailAddress();
    }

    private static String getPassword(){
        return faker.internet().password(7,14,true);
    }

    @DataProvider(name = "registerData",parallel = true)
    public static Object[][]getData(){
        String pw=getPassword();
        return new Object[][]{
                {getFirstName(),getLastName(),getEmail(),pw,pw,"valid registration","Thank you for registering with Tealium Ecommerce."},
                {getFirstName(),getLastName(),"evy@co.ul",pw,pw,"invalid email format","\"Email\" is not a valid hostname."},
                {getFirstName(),getLastName(),"evy@user.co.il","invalid email in use","There is already an account with this email address. If you are sure that it is your email address, "},
                {getFirstName(),getLastName(),getEmail(),"password123","password","invalid passwords mismatch","Please make sure your passwords match."},
                {getFirstName(),getLastName(),getEmail(),"pw123","pw123","invalid passwords length","Please enter more characters or clean leading or trailing spaces."}
        };
    }
}
