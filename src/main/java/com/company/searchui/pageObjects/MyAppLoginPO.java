package com.company.searchui.pageObjects;

import com.company.searchui.utils.BrowserUtils;
import com.company.searchui.utils.Global_VARS;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import static org.testng.Assert.assertEquals;

/**
 * Login Page Object
 *
 * @author phildolganov
 * @param <M>
 */
public class MyAppLoginPO<M extends WebElement> extends BrowserBasePO<M> {
    // local vars
    private String PAGE_TITLE = "Login Page Title";

    // constructor
    public MyAppLoginPO() throws Exception {
        setPageTitle(PAGE_TITLE);
    }

    // page objects
    @FindBy(id = "username")
    @CacheLookup
    protected M username;

    @FindBy(id = "password")
    @CacheLookup
    protected M password;

    @FindBy(id = "submit")
    @CacheLookup
    protected M submit;

    @FindBy(id = "myApp_exception")
    protected M error;

    // abstract methods
    @Override
    public void setElementWait(int elementWait) {
        this.elementWait = elementWait;
    }

    @Override
    public int getElementWait() {
        return this.elementWait;
    }

    @Override
    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    @Override
    public String getPageTitle() {
        return this.pageTitle;
    }

    // common methods
    public void login(String username, String password) throws Exception {
        if (!this.username.getAttribute("value").equals("")) {
            this.username.clear();
        }

        this.username.sendKeys(username);

        if (!this.password.getAttribute("value").equals("")){
            this.password.clear();
        }

        this.password.sendKeys(password);
        submit.click();

        // exception handling
        if (BrowserUtils.elementExists(error, Global_VARS.TIMEOUT_SECOND)) {
            String getError = error.getText();
            throw new Exception("Login Failed with error = " + getError);
        }

        // wait for the home page to appear
        BrowserUtils.waitFor(new MyAppHomePO<>().getPageTitle(), getElementWait());
    }

    public String getText(WebElement element) throws Exception {
        return element.getText();
    }

    public void verifyText(WebElement element, String expText) throws AssertionError {
        assertEquals(element.getText(),expText, "Verify Submit Button Text");
    }
}
