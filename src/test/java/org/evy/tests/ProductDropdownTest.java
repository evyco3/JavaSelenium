package org.evy.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.evy.framework.data.ProductDropdownData;
import org.evy.framework.enums.LogType;
import org.evy.framework.pages.HomePage;
import org.evy.framework.utils.LoggerUtils;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.evy.framework.utils.AssertionUtils.assertContains;

/**
 * The {@code ProductDropdownTest} class contains test methods for verifying product selection functionality
 * from dropdown menus. This class uses TestNG for test execution and Allure annotations for detailed reporting.
 *
 * <p>It includes scenarios for selecting products from main and subcategories and verifying the current URL.
 *
 * <p>The tests navigate from the homepage -> clicking the main category dropdown -> selecting the subcategory,
 * and verify the current URL.
 *
 * @see BaseTest
 * @see HomePage
 */
@Epic("Product Navigation")
@Feature("Product Dropdown Tests")
public class ProductDropdownTest extends BaseTest {

    @Test(dataProvider="productDropdownData",dataProviderClass = ProductDropdownData.class)
    @Parameters({"mainCategory", "subCategory","expectedUrl"})
    @Story("User Product Selection Scenarios")
    @Description("Tests user product selection from dropdown with different main and subcategories")
    public void testUserSelectProductFromDropdown(String mainCategory, String subCategory,String expectedUrl) {
        String actualUrl = performSelectProductFromDropdownAndVerifyCurrentUrl(mainCategory, subCategory);
        assertContains(actualUrl, expectedUrl, "Verify if the current URL contains the expected part");
    }

    @Step("Perform product selection from dropdown and verify current URL for main category: {mainCategory}, sub category: {subCategory}")
    private String performSelectProductFromDropdownAndVerifyCurrentUrl(String mainCategory, String subCategory) {
        try {
            return HomePage.getInstance().getProductDropdownNavigator()
                    .selectProductFromDropdown(mainCategory, subCategory)
                    .getCurrentUrl();
        } catch (Exception e) {
            LoggerUtils.log(getClass(), LogType.ERROR, "Fail During Perform select product " + e);
            throw new RuntimeException("Failure During Select product from dropdown ", e);
        }
    }
}
