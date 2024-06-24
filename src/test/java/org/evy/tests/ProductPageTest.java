package org.evy.tests;

import org.evy.framework.data.ProductPageData;
import org.evy.framework.enums.LogType;
import org.evy.framework.pages.HomePage;
import org.evy.framework.utils.AssertionUtils;
import org.evy.framework.utils.LoggerUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ProductPageTest extends BaseTest {

    @Test(dataProviderClass = ProductPageData.class, dataProvider = "productColor")
    @Parameters({"color", "expectedMessage"})
    public void testUserSelectProductColor(String color, String expectedResponseMessage) {
        String actualResponseMessage = performSelectProductColor(color);
        AssertionUtils.assertContains(actualResponseMessage, expectedResponseMessage, "Verify if (" + actualResponseMessage + ") is Equals to (" + expectedResponseMessage + ")");
    }

    @Test(dataProviderClass = ProductPageData.class,dataProvider = "productSize")
    @Parameters({"size","expectedMessage"})
    public void testUserSelectProductSize(String size,String expectedResponseMessage){
        String actualResponseMessage=performSelectProductSize(size);
        AssertionUtils.assertContains(actualResponseMessage, expectedResponseMessage, "Verify if (" + actualResponseMessage + ") is Equals to (" + expectedResponseMessage + ")");
    }





    private String performSelectProductColor(String color) {
        try {
            return HomePage
                    .getInstance().getProductDropdownNavigator()
                    .selectProductFromDropdown("Men", "Shirts")
                    .selectProductByName("Plaid Cotton Shirt")
                    .selectProductColor(color)
                    .selectProductSize("s")
                    .clickAddToCartButton()
                    .getProductAddToCartSuccessMessage();
        } catch (Exception e) {
            LoggerUtils.log(getClass(), LogType.ERROR, "Fail to select  product color " + e);
            throw new RuntimeException("Failure during select product color", e);
        }
    }

    private String performSelectProductSize(String size) {
        try {
            return HomePage
                    .getInstance().getProductDropdownNavigator()
                    .selectProductFromDropdown("Men", "Shirts")
                    .selectProductByName("Plaid Cotton Shirt")
                    .selectProductColor("Charcoal")
                    .selectProductSize(size)
                    .clickAddToCartButton()
                    .getProductAddToCartSuccessMessage();
        } catch (Exception e) {
            LoggerUtils.log(getClass(), LogType.ERROR, "Fail to select  product color " + e);
            throw new RuntimeException("Failure during select product color", e);
        }
    }

   

}
