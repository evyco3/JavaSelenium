package org.evy.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.evy.framework.data.ProductListingPageData;
import org.evy.framework.enums.LogType;
import org.evy.framework.pages.HomePage;
import org.evy.framework.utils.LoggerUtils;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.evy.framework.utils.AssertionUtils.assertContains;

/**
 * The {@code SelectProductTest} class contains test methods for verifying product selection functionality.
 * <p>user lands at homePage ->select product categories from productDropdown->moving to productListingPage select product By Name
 *
 * @see BaseTest
 * @see HomePage
 */
@Epic("Product Selection")
@Feature("Select Product Tests")
public class SelectProductTest extends BaseTest {

    @Test(dataProviderClass = ProductListingPageData.class, dataProvider = "productNameData")
    @Parameters({"mainCategory", "subCategory", "productName", "expectedUrl"})
    @Story("User Product Selection Scenarios")
    @Description("Tests product selection scenarios with different combinations of main category, sub category, product name, and expected URLs")
    public void testUserSelectProductByName(String mainCategory, String subCategory, String productName, String expectedUrl) {
        String actualUrl = performSelectProductByNameAndGetCurrentUrl(mainCategory, subCategory, productName);
        assertContains(actualUrl, expectedUrl, "Verify if (" + actualUrl + ") contains (" + expectedUrl + ")");
    }

    @Step("Perform product selection and get current URL for main category: {mainCategory}, sub category: {subCategory}, product name: {productName}")
    private String performSelectProductByNameAndGetCurrentUrl(String mainCategory, String subCategory, String productName) {
        try {
            return HomePage
                    .getInstance().getProductDropdownNavigator()
                    .selectProductFromDropdown(mainCategory, subCategory)
                    .selectProductByName(productName)
                    .getCurrentUrl();
        } catch (Exception e) {
            LoggerUtils.log(getClass(), LogType.ERROR, "Fail during select product process " + e);
            throw new RuntimeException("Failure during select product by name " + e);
        }
    }
}
