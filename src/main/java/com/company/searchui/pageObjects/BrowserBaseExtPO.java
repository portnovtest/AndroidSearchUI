package com.company.searchui.pageObjects;

import org.openqa.selenium.WebElement;

public class BrowserBaseExtPO <M extends WebElement> extends BrowserBasePO<M> {

    // constructor
    public BrowserBaseExtPO() throws Exception {
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

    // add table components and methods here
}

