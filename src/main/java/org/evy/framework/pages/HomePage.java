package org.evy.framework.pages;

import org.evy.framework.pages.account.AccountNavigator;

public class HomePage extends BasePage{

    public static HomePage getInstance(){
        return new HomePage();
    }

    public AccountNavigator getAccountNavigator(){
        return new AccountNavigator();
    }
}
