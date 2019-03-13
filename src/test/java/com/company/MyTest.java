package com.company;

import com.company.searchui.pageObjects.MyPageObject;
import com.company.searchui.utils.JSONDataProvider;
import com.google.gson.JsonObject;
import org.testng.annotations.Test;

public class MyTest {


    public void tc001_myTestcase() throws Exception {
        //...
        MyPageObject pageObj = new MyPageObject();
        pageObj.getCancel();
        //...
    }

    /**
     * tc002_appFeatureAction - test method to demonstrate @Test DP Annotation
     *
     * @param data
     * @throws Exception
     */
    @Test(dataProvider = "myData_JSON", dataProviderClass = JSONDataProvider.class)
    public void tc002_appFeatureAction(JsonObject data) throws Exception {

    }
}
