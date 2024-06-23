package org.evy.framework.pages.product;

import io.qameta.allure.Step;
import org.evy.framework.drivers.WebDriverFactory;
import org.evy.framework.enums.LogType;
import org.evy.framework.pages.BasePage;
import org.evy.framework.utils.LoggerUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductListingPage extends BasePage {

    @Step("Select product by name '{productName}'")
    public ProductPage selectProductByName(String productName){
        try{
            String productStringName=String.format("h2[class='product-name'] a[title='%s']",productName);
            WebElement productElement= WebDriverFactory.getInstance().getDriver().findElement(By.cssSelector(productStringName));
            click(productElement,productName);
            waitForPageTitleToContain(productName);
            return new ProductPage();

        }catch (Exception e){
            LoggerUtils.log(getClass(), LogType.ERROR,"Failed to select product from product pool result");
            throw new RuntimeException("Error During product selection"+e);
        }

    }
}
