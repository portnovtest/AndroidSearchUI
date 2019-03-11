package com.company.searchui.pageObjects;

import org.openqa.selenium.WebElement;

// subclass signature
public class MyAppSubClassPO<M extends WebElement> extends BrowserBasePO<M> implements BrowserExtras {

    public MyAppSubClassPO() throws Exception {
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
}
