package com.company;

import com.company.searchui.pageObjects.UserPO;
import com.company.searchui.utils.ImageCapture;
import com.google.gson.JsonObject;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class CreateUserTest {

    // this test method cleans up in the method
    @Test
    public void tc001_createUser(String rowID, String description, JsonObject testData){
        UserPO user = null;

        // attempt to create a new user
        try {
            user = new UserPO();
            user.createUser(testData);
            user.verifyUser(testData.get("username").toString());

        } // trap and throw exception to console
        catch (Exception e) {
            e.printStackTrace();
        } // call getter method in UserPO class to get cancel element
        finally {
            user.getCancel().click();
        }
    }
    // this method aborts and lets the teardown cleanup
    @Test
    public void tc002_createUser(String rowID,String description,JsonObject testData) throws Exception {
        UserPO user = new UserPO();

        // attempt to create a new user
        user.createUser(testData);

        // verify user was created
        user.verifyUser(testData.get("username").toString());
    }

    @AfterMethod
    public void testMethodTeardown2(ITestResult result) throws Exception {
        if (!result.isSuccess()){
            ImageCapture.screenShot(result);
            new UserPO().getCancel().click();
        }
    }
}
