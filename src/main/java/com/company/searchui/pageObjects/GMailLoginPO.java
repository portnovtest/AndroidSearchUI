package com.company.searchui.pageObjects;

import com.company.searchui.utils.BrowserUtils;
import com.company.searchui.utils.CreateDriver;
import com.company.searchui.utils.Global_VARS;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

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

    // 'OR' locators
    @FindBy(css = "a[href*='Account Page'], a[href*='Go To Account']")
    protected M goToAccountCss;

    @FindBy(xpath = "//a[contains(@href,'Account Page') or contains(@href,'Go To Account')]")
    protected M goToAccountXpath;

    // wildcarded id locators
    @FindBy(css = "input[id*='password']")
    protected M passwordCSS;

    @FindBy(xpath = "//input[contains(@id,'password')]")
    protected M passwordXpath;

    // wildcarded text locators (native CSS, Non-Firefox, Firefox
    @FindBy(css = "a:contains('Copyright'), a[innerText*='Copyright'], a[textContent*='Copyright']")
    protected M copyrightCSS;

    @FindBy(xpath = "//a[contains(text(),'Copyright')]")
    protected M copyrightXpath;

    // wildcarded element locators
    @FindBy(css = "*[class*='submit']")
    protected M submitCSS;

    @FindBy(xpath = "//*[contains(@class,'submit')]")
    protected M submitXpath;

    // index locators
    @FindBy(css = "div.footer:nth-child(1)")
    protected M footerCSS;

    @FindBy(xpath = "(//button[@class='save'])[2]")
    protected M footerXpath;

    // conditional code - locators
    @FindBy(css = "input[id='myUser']")
    protected M myUser;

    @FindBy(css = "select[id='mySelectUser']")
    protected M mySelectUser;

    // conditional code - page object class method
    public void myLogin(String user, String password) throws Exception {
        if (BrowserUtils.exists(mySelectUser, Global_VARS.TIMEOUT_SECOND)){
            new Select(mySelectUser).selectByVisibleText(user);
        } else {
            myUser.sendKeys(user);
        }
    }

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
