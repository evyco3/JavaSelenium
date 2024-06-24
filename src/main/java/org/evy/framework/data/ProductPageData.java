package org.evy.framework.data;

import org.testng.annotations.DataProvider;

public final class ProductPageData {


    @DataProvider(name = "productColor")
    public static Object[][]getColorData(){
        return new Object[][]{
                {"Charcoal","was added to your shopping cart"},
                {"Khaki","was added to your shopping cart"},
                {"Red","was added to your shopping cart"},
                {"Royal Blue","was added to your shopping cart"}
        };
    }

    @DataProvider(name = "productSize")
    public static Object[][]getSizeData(){
        return new Object[][]{
                {"xs","was added to your shopping cart"},
                {"s","was added to your shopping cart"},
                {"m","was added to your shopping cart"},
                {"l","was added to your shopping cart"},
                {"xl","was added to your shopping cart"}

        };
    }

    @DataProvider(name = "quantity")
    public static Object[][]getQuantityData(){
        return new Object[][]{
                {"-1","Failure"},
                {"0","Failure"},
                {"1","was added to your shopping cart"}
        };
    }
}
