package com.company.searchui.pageObjects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;

public class UseScratchTonesPO<M extends MobileElement> extends MobileBasePO<M> {
    public UseScratchTonesPO() throws Exception {
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

    @iOSFindBy(xpath = "//XCUIElementTypeStaticText[starts-with(@name, '1. Connect your device')]")
//    @iOSFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name, '1. Connect your device')]")
//    @iOSFindBy(xpath = "//XCUIElementTypeStaticText[ends-with(@name, 'list.')]") // did not work for some reason
//    @iOSFindBy(xpath = "//XCUIElementTypeStaticText[contains(text(), 'Connect your device')]") // did not work for some reason
//    @iOSFindBy(xpath = "//XCUIElementTypeStaticText[.='1. Connect your device to a computer, launch iTunes,...']") // did not work for some reason
    protected M instructionsTextOne;

}
