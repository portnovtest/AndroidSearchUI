package com.company.searchui.pageObjects;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserPO<M extends WebElement> {
    public UserPO() throws Exception {
    }

    @FindBy(id = "cancel")
    protected M cancel;

    public void createUser(JSONObject user) throws Exception{

    }

    public void verifyUser(String user) throws AssertionError {

    }

    public WebElement getCancel() {
        return cancel;
    }
}
