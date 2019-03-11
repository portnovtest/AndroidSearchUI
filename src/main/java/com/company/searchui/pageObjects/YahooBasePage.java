package com.company.searchui.pageObjects;

import com.company.searchui.utils.CreateDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// Yahoo Base Class
public abstract class YahooBasePage <M extends WebElement>{
    // constructor
    public YahooBasePage() throws Exception {
        WebDriver driver = CreateDriver.getInstance().getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "uh-logo")
    @CacheLookup
    protected M yahooLogo;

    //...
}
