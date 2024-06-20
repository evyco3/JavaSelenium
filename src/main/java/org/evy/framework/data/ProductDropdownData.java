package org.evy.framework.data;

import org.testng.annotations.DataProvider;

public final class ProductDropdownData {

    private ProductDropdownData(){}


    @DataProvider(name = "productDropdownData")
    public static Object[][]getData(){
        return new Object[][]{
                {"Men","View All Men","men.html"},
                {"Men","Shirts","men/shirts"},
                {"Men","New Arrivals","men/new-arrivals"},
                {"Men","Tees, Knits and Polos", "men/tees-knits-and-polos"},
                {"Men","Pants & Denim","men/pants-denim"},
                {"Men","Blazers","men/blazers"}
        };
    }
}
