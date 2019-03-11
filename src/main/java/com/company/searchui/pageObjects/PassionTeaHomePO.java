package com.company.searchui.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PassionTeaHomePO<M extends WebElement> extends BrowserBasePO<M> {

    // local variables go here
    // TODO:

    public PassionTeaHomePO() throws Exception {
        super();
    }

    @Override
    public void setElementWait(int elementWait) {

    }

    @Override
    public int getElementWait() {
        return 0;
    }

    @Override
    public void setPageTitle(String pageTitle) {

    }

    @Override
    public String getPageTitle() {
        return null;
    }

    @FindBy(xpath = "//a[@href='welcome.html']")
   // @FindBy(css = "a[href='welcome.html']")
    protected M welcome;

    //or

    /*
    @FindBy(xpath = "//a[@data-title='Welcome']")
    @FindBy(css = "a[data-title='Welcome']")
    protected M welcome;

    //or

    @FindBy(xpath = "//a[contains(@data-pageid, '247216')]") // contains
    @FindBy(css = "a[data-pageid*='247216']") // contains
    @FindBy(css = "a[data-pageid$='247216']") // ends-with
    protected M welcome;

    //or

    @FindBy(xpath = "//a[@data-url='welcome.html']") // equals
    @FindBy(css = "a[data-url^='welcome']") // starts-with
    protected M welcome;

    //or

    @FindBy(xpath = "//a[.='Welcome']") // equals
    @FindBy(css = "a:contains('Welcome')") // contains; subject to CSS version of browser
    protected M welcome;
    */

}
