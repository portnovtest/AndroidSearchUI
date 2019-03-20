package com.company;

import com.company.searchui.pageObjects.GMailLoginPO;
import com.company.searchui.utils.BrowserUtils;
import com.company.searchui.utils.Global_VARS;
import com.company.searchui.utils.JSONDataProvider;
import com.google.gson.JsonObject;
import org.testng.annotations.Test;

public class GmailLoginTest {
    // this method follows the Selenium Page Object Model
    @Test(groups = "LOGIN", dataProvider = "myData_JSON", dataProviderClass = JSONDataProvider.class, enabled = true)
    public void tc001_loginCreds(String rowID, String description, JsonObject testData) throws Exception {
//        String email = testData.get("email").toString();
////        String password = testData.get("password").toString();
////        String title = testData.get("title").toString();

        String user, password, getMessage;

        // Login to app, verify page title, logout of app
        GMailLoginPO gmail = new GMailLoginPO();

        // test the login or credentials error
        user = testData.get("username").toString();
        password = testData.get("password").toString();

        if(testData.get("error") == null){
            gmail.login(user,password);
            gmail.signOut();
        } else {
            getMessage = BrowserUtils.lookupMessage(Global_VARS.EXCEPTION_MESSAGES, testData.get("error").toString());
              gmail.login(user, password, getMessage);
              // if messages contain dynamic data, pass it in using .replace()
        //    gmail.login(user, password, getMessage.replace("{USER}",Global_VARS.DEFAULT_USER));
        }
//        gmail.login(email, password);
//        gmail.verifyTitle(title);
//        gmail.signOut();
    }
}
