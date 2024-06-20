package org.evy.framework.pages.product;

import io.qameta.allure.Step;
import org.evy.framework.drivers.WebDriverFactory;
import org.evy.framework.enums.LogType;
import org.evy.framework.pages.BasePage;
import org.evy.framework.utils.LoggerUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * The {@code ProductDropdownNavigator} class provides functionality for selecting products
 * from the dropdown menu. It extends the {@link BasePage} class and utilizes WebDriver elements
 * to interact with the user interface for performing product selection operations.
 */
public class ProductDropdownNavigator extends BasePage {

    /**
     * Selects a product category from the dropdown menu.
     *
     * @param mainCategory the main category to select
     * @param subCategory  the subcategory to select, if any
     * @return an instance of ProductListingPage
     */
    @Step("Select product from dropdown with main category: {mainCategory} and sub category: {subCategory}")
    public ProductListingPage selectProductFromDropdown(String mainCategory, String subCategory) {
        try {
            String mainElementXPath = String.format("//nav[@id='nav']/ol/li/a[normalize-space()='%s']", mainCategory);
            WebElement mainElement = WebDriverFactory.getInstance().getDriver().findElement(By.xpath(mainElementXPath));
            performCategorySelection(mainElement, mainCategory, subCategory);
            LoggerUtils.log(getClass(), LogType.INFO, "Success selecting product from dropdown");
            waitForPageTitle();
            return new ProductListingPage();
        } catch (Exception e) {
            LoggerUtils.log(getClass(), LogType.ERROR, "Failed to select product from dropdown " + e);
            throw new RuntimeException("Error during selection of product from dropdown", e);
        }
    }

    @Step("Perform category selection with main element: {mainCategory} and sub category: {subCategory}")
    private void performCategorySelection(WebElement mainElement, String mainCategory, String subCategory) {
        if (subCategory.isEmpty()) {
            click(mainElement, mainCategory);
        } else {
            String subElementXPath = String.format("//nav[@id='nav']/ol/li/a[normalize-space()='%s']/parent::li//ul//a[normalize-space()='%s']", mainCategory, subCategory);
            WebElement subElement = WebDriverFactory.getInstance().getDriver().findElement(By.xpath(subElementXPath));
            moveTo(mainElement, mainCategory);
            click(subElement, subCategory);
        }
    }

    @Step("Wait for page title to be visible")
    private void waitForPageTitle() {
        waitForElementToBeVisible(WebDriverFactory.getInstance().getDriver().findElement(By.cssSelector(".page-title.category-title")));
    }
}
