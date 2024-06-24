package org.evy.framework.pages.checkout;

import org.evy.framework.drivers.WebDriverFactory;
import org.evy.framework.enums.LogType;
import org.evy.framework.pages.BasePage;
import org.evy.framework.utils.LoggerUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.logging.Logger;

public class CheckoutPage extends BasePage {

    @FindBy(css = "input[id='billing:firstname']")
    private WebElement firstName;
    @FindBy(css = "input[id='billing:lastname']")
    private WebElement lastName;
    @FindBy(css = "input[id='billing:street1']")
    private WebElement address;
    @FindBy(css = "input[id='billing:city']")
    private WebElement city;
    @FindBy(css = "input[id='billing:postcode']")
    private WebElement zipcode;
    @FindBy(css = "select[id='billing:country_id']")
    private WebElement country;
    @FindBy(css = "input[id='billing:telephone']")
    private WebElement telephone;
    @FindBy(css = "button[onClick='billing.save()']")
    private WebElement continueBtn;



    public ShipmentPage setBillingInformation(String firstName,String lastName,String address,String city,String zipcode,String country,String telephone){
        try{
            sendKeys(this.firstName,firstName,"firstName");
            sendKeys(this.lastName,lastName,"lastName");
            sendKeys(this.address,address,"address");
            sendKeys(this.city,city,"city");
            sendKeys(this.zipcode,zipcode,"zipcode");
            selectByVisibleText(this.country,country,"country");
            sendKeys(this.telephone,telephone,"telephone");
            click(this.continueBtn,"continue button");
            waitForElementToBeVisible(WebDriverFactory.getInstance().getDriver().findElement(By.xpath("//dt[normalize-space()='Flat Rate']")));
            LoggerUtils.log(getClass(),LogType.INFO,"Success Filling info, Moving to shipmentPage");
            return new ShipmentPage();

        }catch (Exception e){
            LoggerUtils.log(getClass(), LogType.ERROR,"Failed to setBilling information & Failed"+e);
            throw new RuntimeException("Failure During filling information ",e);

        }

    }


}
