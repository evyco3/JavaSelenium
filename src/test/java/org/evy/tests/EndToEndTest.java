package org.evy.tests;

import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.evy.framework.enums.LogType;
import org.evy.framework.pages.HomePage;
import org.evy.framework.utils.LoggerUtils;
import org.testng.annotations.Test;

/**
 * The {@code EndToEndTest} class contains test methods for performing an end-to-end user journey.
 * This class uses TestNG for test execution and Allure annotations for detailed reporting.
 *
 * <p>It includes scenarios from user registration, product selection, to order placement and verification.
 *
 * <p>The tests navigate through various pages and perform actions to complete an end-to-end scenario.
 * @see BaseTest
 * @see HomePage
 */
@Epic("User Journey")
@Feature("End-to-End Tests")
public class EndToEndTest extends BaseTest {

    private final Faker faker = new Faker();
    String pw = faker.internet().password(8, 14, true, true);

    @Test
    @Story("Complete User Journey")
    @Description("Performs an end-to-end user journey including registration, product selection, and order placement")
    public void testUserPerformEndToEnd() {
        String message = performEndToEnd();
        System.out.println(message);
    }

    @Step("Perform end-to-end user journey")
    private String performEndToEnd() {
        try {
            return HomePage.getInstance().getAccountNavigator()
                    .navigateToRegisterPage()
                    .performRegistration(
                            faker.name().firstName(),
                            faker.name().lastName(),
                            faker.internet().emailAddress(),
                            pw, pw, true, HomePage.class
                    )
                    .getProductDropdownNavigator()
                    .selectProductFromDropdown("Men", "Shirts")
                    .selectProductByName("Plaid Cotton Shirt")
                    .selectProductColor("Charcoal")
                    .selectProductSize("S")
                    .selectProductQuantity("2")
                    .clickAddToCartButton()
                    .proceedToCheckoutPage()
                    .setBillingInformation(
                            faker.name().firstName(),
                            faker.name().lastName(),
                            faker.address().streetAddress(),
                            faker.address().city(),
                            faker.address().zipCode(),
                            "Israel",
                            faker.phoneNumber().phoneNumber()
                    )
                    .proceedToPaymentPage()
                    .proceedToOrderViewPage()
                    .getSuccessOrderMessage();
        } catch (Exception e) {
            LoggerUtils.log(getClass(), LogType.ERROR, "Error during end-to-end test process"+ e);
            throw new RuntimeException("Error during end-to-end test process", e);
        }
    }
}
