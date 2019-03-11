package com.company.searchui.pageObjects;

import org.openqa.selenium.WebElement;

public class GMailLoginPO {

    private WebElement email;
    private WebElement password;
    private WebElement submit;

    // use of static WebElement name in method
    public void login(String email, String password) throws Exception {
        this.email.sendKeys(email); // static WebElement name
        this.password.sendKeys(password); // static WebElement name
        submit.click();
    }

    // use of static WebElement name passed in as method parameter
    public void login(WebElement username, String email, String password) throws Exception{
        username.sendKeys(email); // static WebElement name passed as parameter
        this.password.sendKeys(password); // static WebElement name
        submit.click();

    }
}
