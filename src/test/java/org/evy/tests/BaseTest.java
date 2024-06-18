package org.evy.tests;


import org.evy.framework.drivers.WebDriverFactory;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class BaseTest {

    @BeforeMethod
    public void setup(){
        WebDriverFactory.getInstance().initDriver();
    }

    @AfterMethod
    public void tearDown(){
        WebDriverFactory.getInstance().quitDriver();
    }
}
