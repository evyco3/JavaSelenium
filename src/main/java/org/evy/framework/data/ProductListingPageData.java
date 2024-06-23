package org.evy.framework.data;

import org.testng.annotations.DataProvider;

public final class ProductListingPageData {

    private ProductListingPageData(){}


    @DataProvider(name = "productNameData")
    public static Object[][]getData(){
        return new Object[][]{
                {"Men","Shirts","Plaid Cotton Shirt","plaid-cotton-shirt"},
                {"Men","Shirts","Slim fit Dobby Oxford Shirt","slim-fit-dobby-oxford-shirt"},
                {"Men","Shirts","French Cuff Cotton Twill Oxford","french-cuff-cotton-twill-oxford"}

        };
    }
}
