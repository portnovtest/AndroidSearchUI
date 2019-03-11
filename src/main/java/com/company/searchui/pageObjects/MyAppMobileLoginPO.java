package com.company.searchui.pageObjects;

import io.appium.java_client.MobileElement;

/**
 * Mobile Login Page Object
 *
 * @author phildolganov
 * @param <M>
 */
public class MyAppMobileLoginPO<M extends MobileElement> extends MobileBasePO<M> {
    private int elementWait = 60;
    private String PAGE_TITLE = "Login Page Title";

    // constructor
    public MyAppMobileLoginPO() throws Exception {
        setPageTitle(PAGE_TITLE);
    }

    // login page methods

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
}
