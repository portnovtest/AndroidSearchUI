package com.company.searchui.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class MyPageObject<M extends WebElement> {
   // ...

    @FindBy(id = "Cancel")
    @CacheLookup
    protected M cancel;

    // getter method in Page Object class

    /**
     * getCancel method
     *
     * @return WebElement
     * @throws Exception
     */
    public M getCancel() throws Exception {
        return cancel;
    }

    // Implicit exception handling

    // create a method to retrieve the text from an element on a page
    @FindBy(id = "submit")
    protected M submit;

    public String getText(WebElement element) throws Exception {
        return element.getText();
    }
    // use the method



}
