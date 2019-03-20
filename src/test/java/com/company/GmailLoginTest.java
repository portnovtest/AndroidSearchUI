package com.company;

import com.company.searchui.pageObjects.GMailLoginPO;
import com.company.searchui.utils.JSONDataProvider;
import com.google.gson.JsonObject;
import org.testng.annotations.Test;

public class GmailLoginTest {
    // this method follows the Selenium Page Object Model
    @Test(dataProvider = "myData_JSON", dataProviderClass = JSONDataProvider.class)
    public void tc001_loginCreds(String rowID, String description, JsonObject testData) throws Exception {
        String email = testData.get("email").toString();
        String password = testData.get("password").toString();
        String title = testData.get("title").toString();

        // Login to app, verify page title, logout of app
        GMailLoginPO gmail = new GMailLoginPO();

        gmail.login(email, password);
        gmail.verifyTitle(title);
        gmail.signOut();

    }
}
