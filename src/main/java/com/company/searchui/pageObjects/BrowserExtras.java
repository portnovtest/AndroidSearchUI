package com.company.searchui.pageObjects;

import org.openqa.selenium.WebElement;

/**
 * Interface to implement by classes requiring BrowserExtras methods
 *
 * @author phildolganov
 */
public interface BrowserExtras {
    // methods to implement in subclasses
    public void setElementWait(int elementWait);
    public int getElementWait();
    public void setPageTitle(String pageTitle);
    public String getPageTitle();
}

