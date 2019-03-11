package com.company.searchui.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

/**
 * Selenium Page Object Template
 *
 * @author phildolganov
 *
 * @param <M>
 */
public class TemplatePO<M extends WebElement> extends BrowserBasePO<M> {
    // local variables go here
    // TODO:

    // constructor
    public TemplatePO() throws Exception {
        super();
    }

    // abstract methods
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

    // page objects
    @FindBy(id = "")
    @CacheLookup
    protected M element1;

    // class methods

    /**
     * myMethod method
     *
     * @param arg1
     * @throws Exception
     */
    public void myMethod(String arg1) throws Exception {
        // TODO:
    }
}
