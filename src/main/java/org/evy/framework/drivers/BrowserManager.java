package org.evy.framework.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.evy.framework.enums.BrowserType;
import org.evy.framework.enums.LogType;
import org.evy.framework.utils.LoggerUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.EnumMap;
import java.util.function.Supplier;

/**
 * Manages WebDriver instances for different browser types.
 * This class uses a Singleton pattern to ensure only one instance exists.
 * Browser instances are lazily initialized and managed based on BrowserType enums.
 * Supported browsers include Chrome, Firefox, Edge, Opera, Internet Explorer, and Safari.
 * WebDriverManager is used for automatic setup and management of driver versions.
*/

public final class BrowserManager {
    private static final BrowserManager instance=new BrowserManager();

    private static final EnumMap<BrowserType, Supplier<WebDriver>>browserMap=new EnumMap<>(BrowserType.class);

    static{
        browserMap.put(BrowserType.CHROME,BrowserManager::getChromeDriver);
        browserMap.put(BrowserType.FIREFOX,BrowserManager::getFirefoxDriver);
        browserMap.put(BrowserType.EDGE,BrowserManager::getEdgeDriver);
        browserMap.put(BrowserType.OPERA,BrowserManager::getOperaDriver);
        browserMap.put(BrowserType.EXPLORER,BrowserManager::getExplorerDriver);
        browserMap.put(BrowserType.SAFARI,BrowserManager::getSafariDriver);

    }

    private BrowserManager(){}

     static BrowserManager getInstance() {
        return instance;
    }

     public WebDriver getBrowser(BrowserType browserType) {
        try {
            Supplier<WebDriver> supplier = browserMap.get(browserType);
            if (supplier == null) {
                throw new IllegalAccessException("Unsupported browser type: " + browserType);
            }
            LoggerUtils.log(BrowserManager.class, LogType.INFO, "Initializing browser type: " + browserType);
            return supplier.get();
        } catch (Exception e) {
            LoggerUtils.log(BrowserManager.class, LogType.ERROR, "Failed to initialize browser type: " + browserType);
            throw new RuntimeException("Failed to initialize browser type: " + browserType, e);
        }
    }





    private static WebDriver getChromeDriver(){
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    private static WebDriver getFirefoxDriver(){
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }

    private static  WebDriver getEdgeDriver(){
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    }

    private static WebDriver getExplorerDriver(){
        WebDriverManager.iedriver().setup();
        return new InternetExplorerDriver();
    }

    private static WebDriver getOperaDriver(){
        WebDriverManager.operadriver().setup();
        return null;
    }

    private static WebDriver getSafariDriver(){
        WebDriverManager.safaridriver().setup();
        return new SafariDriver();
    }


}
