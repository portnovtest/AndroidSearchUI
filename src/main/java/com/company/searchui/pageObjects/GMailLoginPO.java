package com.company.searchui.pageObjects;

import com.company.searchui.utils.CreateDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.testng.Assert.assertEquals;

public class GMailLoginPO<M extends WebElement> {

    public GMailLoginPO() throws Exception {
    }

    @FindBy(id = "identifierID")
    protected M email;

    @FindBy(name = "password")
    protected M password;

    @FindBy(xpath = "//span[.='next']")
    protected M next;

    @FindBy(xpath = "//a[.='Sign out']")
    protected M signOut;

    private WebElement submit;

    // use of static WebElement name in method
    public void login(String email, String password) throws Exception {
        this.email.sendKeys(email); // static WebElement name
        next.click();
        this.password.sendKeys(password); // static WebElement name
        next.click();
    }

    // use of static WebElement name passed in as method parameter
    public void login(WebElement username, String email, String password) throws Exception{
        username.sendKeys(email); // static WebElement name passed as parameter
        this.password.sendKeys(password); // static WebElement name
        submit.click();
    }

    public void login(String username, String email, String password) throws Exception{
        this.email.sendKeys(email); // static WebElement name
        this.password.sendKeys(password); // static WebElement name
        submit.click();
    }

    public void verifyTitle(String title) throws AssertionError {
        WebDriver driver = CreateDriver.getInstance().getDriver();
        assertEquals(driver.getTitle(), title, "Verify " + title);
    }

    public void signOut() throws Exception {
        signOut.click();
    }
}
