package org.evy.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.evy.framework.enums.LogType;
import org.evy.framework.utils.LoggerUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;


public class BaseTest {

    @Test
    public void exampleTest(){
        LoggerUtils.log(getClass(), LogType.INFO,"Test start...");
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        WebDriver driver=new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://ecommerce.tealiumdemo.com/");
        String title=driver.getTitle();
        Assert.assertEquals(title,"Tealium Ecommerce Demo");
        LoggerUtils.log(getClass(),LogType.INFO,"Test End...");
        driver.quit();
    }
}
