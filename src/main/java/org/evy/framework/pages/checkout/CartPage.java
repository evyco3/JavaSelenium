package org.evy.framework.pages.checkout;

import org.evy.framework.enums.LogType;
import org.evy.framework.pages.BasePage;
import org.evy.framework.utils.LoggerUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {
    @FindBy(css = ".success-msg span")
    private WebElement productAddToCartSuccessMsg;
    @FindBy(css = "li.method-checkout-cart-methods-onepage-bottom>button")
    private WebElement proceedToCheckPageBtn;




    public String getProductAddToCartSuccessMessage(){
        return getText(this.productAddToCartSuccessMsg,"success add product to cart message");
    }

    public CheckoutPage proceedToCheckoutPage(){
      try{
          click(this.proceedToCheckPageBtn,"proceed to checkoutPage");
          waitForPageTitleToBeEqualsTo("Checkout");
          LoggerUtils.log(getClass(), LogType.INFO,"Proceed to checkoutPage");
          return new CheckoutPage();
      }catch (Exception e){
          LoggerUtils.log(getClass(),LogType.ERROR,"Failed to proceed to checkoutPage"+e);
          throw new RuntimeException("Failure during proceed to checkoutPage",e);
      }
    }
}
