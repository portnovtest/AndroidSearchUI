package com.company.searchui.pageObjects;

import com.company.searchui.utils.CreateDriver;
import com.company.searchui.utils.Global_VARS;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Sample Base Class Page Object for Browser App
 *
 * @author phildolganov
 *
 * @param <M>
 */
public abstract class MobileBasePO <M extends MobileElement> {
    public int elementWait = Global_VARS.TIMEOUT_ELEMENT;
    public String pageTitle = "";
    AppiumDriver<MobileElement> driver = CreateDriver.getInstance().getDriver(true);

    // constructor
    public MobileBasePO() throws Exception {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    // abstract methods included in base class

    public abstract void setElementWait(int elementWait);
    public abstract int getElementWait();
    public abstract void setPageTitle(String pageTitle);
    public abstract String getPageTitle();

    // common MobileElement locators included in base class

    @AndroidFindBy(className = "myLogo")
    @iOSXCUITFindBy(className = "myLogo")
    protected M companylogo;

    @AndroidFindBy(id = "title")
    @iOSXCUITFindBy(xpath = "//*[@name = 'title']")
    protected M title;


}
