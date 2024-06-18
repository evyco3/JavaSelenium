package org.evy.framework.drivers;

import org.evy.framework.enums.BrowserType;
import org.evy.framework.enums.LogType;
import org.evy.framework.utils.LoggerUtils;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

/**
 * This class is responsible for managing the creation, configuration, and destruction of WebDriver instances.
 * It provides a single instance of WebDriverFactory using the Singleton design pattern and utilizes ThreadLocal
 * to ensure thread safety and maintain a separate WebDriver instance for each thread.
 */
public final class WebDriverFactory {


    private final static ThreadLocal<WebDriver>drivers_threads=new ThreadLocal<>();

    private static final WebDriverFactory instance=new WebDriverFactory();

    private WebDriverFactory(){}

    public static WebDriverFactory getInstance(){
        return instance;
    }


    public void initDriver() {
        try {
            WebDriver driver = BrowserManager.getInstance().getBrowser(BrowserType.CHROME);
            if (driver != null) {
                drivers_threads.set(driver);
                LoggerUtils.log(WebDriverFactory.class, LogType.INFO, "Initialize Driver");
                configureDriver(driver);
            } else {
                LoggerUtils.log(WebDriverFactory.class, LogType.ERROR, "Failed to create WebDriver instance");
                throw new RuntimeException("Unable to initialize Driver.. WebDriver instance is null");
            }
        } catch (Exception e) {
            LoggerUtils.log(WebDriverFactory.class, LogType.ERROR, "Error During initialization driver");
            throw new RuntimeException("Unable to initialize Driver..", e);
        }
    }

    public void quitDriver() {
        try {
            WebDriver driver = drivers_threads.get();
            if (driver != null) {
                driver.quit();
                drivers_threads.remove();
                LoggerUtils.log(WebDriverFactory.class, LogType.INFO, "Quiting Driver");
            } else {
                LoggerUtils.log(WebDriverFactory.class, LogType.WARN, "WebDriver instance is null. Skipping quit operation.");
            }
        } catch (Exception e) {
            LoggerUtils.log(WebDriverFactory.class, LogType.ERROR, "Error during quiting driver");
            throw new RuntimeException("Unable to quit Driver ..", e.getCause());
        }
    }

    public WebDriver getDriver(){
        return drivers_threads.get();
    }


    private static void configureDriver(WebDriver driver){
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.get("https://ecommerce.tealiumdemo.com/");
    }


}
