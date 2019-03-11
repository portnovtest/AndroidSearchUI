package com.company.searchui.pageObjects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;

public class ScratchTonesRecorderPO<M extends MobileElement> extends MobileBasePO<M> {

    public ScratchTonesRecorderPO() throws Exception {
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

    @iOSFindBy(xpath = "//XCUIElementTypeButton[@name='Start']")
    protected M start;

    @iOSFindBy(xpath = "//XCUIElementTypeButton[@name='record active']")
    protected M recordTrack1;

    @iOSFindBy(xpath = "//XCUIElementTypeButton[@name='off unselected']")
    protected M offTrack1;

    @iOSFindBy(xpath = "(//XCUIElementTypeButton[@name='play unselected'])[1]")
    protected M playTrack1;

    @iOSFindBy(xpath = "(//XCUIElementTypeSlider)[1]")
    protected M balanceTrack1;

    @iOSFindBy(xpath = "(//XCUIElementTypeSlider)[2]")
    protected M volumeTrack1;
}
