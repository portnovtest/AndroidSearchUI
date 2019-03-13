package com.company.searchui.pageObjects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ScratchTonesHomePO<M extends MobileElement> extends MobileBasePO<M> {
    public ScratchTonesHomePO() throws Exception {
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

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='My ScratchTones']")
    protected M myScratchTones;

    //or

    /*
    @iOSFindBy(xpath = "//XCUIElementTypeButton[@label='My ScratchTones']")
    protected M myScratchTones;

    //or

    @iOSFindBy(xpath = "//*[@value='1']")
    protected M myScratchTones;

    //or

    @iOSFindBy(xpath = "//XCUIElementTypeTabBar[1]/XCUIElementTypeButton[1]")
    protected M myScratchTones;
    */
}
