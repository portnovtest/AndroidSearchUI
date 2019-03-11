package com.company.searchui.pageObjects;

import com.company.searchui.utils.BrowserUtils;
import com.company.searchui.utils.CreateDriver;
import com.company.searchui.utils.Global_VARS;
import com.company.searchui.utils.JavaScriptUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Sample Base Class Page Object for Browser App
 *
 * @author phildolganov
 *
 * @param <M>
 */
public abstract class BrowserBasePO <M extends WebElement>{
    public int elementWait = Global_VARS.TIMEOUT_ELEMENT;
    public String pageTitle = "";
    WebDriver driver = CreateDriver.getInstance().getDriver();

    // constructor

    public BrowserBasePO() throws Exception {
        PageFactory.initElements(driver, this);
    }

    // abstract methods included in base class

    public abstract void setElementWait(int elementWait);
    public abstract int getElementWait();
    public abstract void setPageTitle(String pageTitle);
    public abstract String getPageTitle();

    // common WebElement locators included in base class

    @FindBy(css = "img[src*='myLogo.png']")
    @CacheLookup
    protected M companyLogo;

    @FindBy(partialLinkText = "All Rights Reserved")
    @CacheLookup
    protected M copyright;

    /**
     * getTitle - method to return the title of the current page
     *
     * @return String
     * @throws Exception
     */
    public String getTitle() throws Exception {
        WebDriver driver = CreateDriver.getInstance().getDriver();

       return driver.getTitle();
    }

    /**
     * getParagraph - method to return the paragraph using a pattern match
     *
     * @param pattern
     * @return String
     * @throws Exception
     */
    public String getParagraph(String pattern) throws Exception {
        WebDriver driver = CreateDriver.getInstance().getDriver();

        // build a dynamic locator on the fly with text pattern in paragraph
        String locator = "//p[contains(text(),'" + pattern + "') or contains(.,'" + pattern + "')]";
        return driver.findElement(By.xpath(locator)).getText();
    }

    /**
     * getCopyright - method to return the page copyright text
     *
     * @return String
     * @throws Exception
     */
    public String getCopyright() throws Exception {
        return copyright.getText();
    }

    // common base class overloaded loadPage methods

    /**
     * loadPage - method to load the page URL for the AUT
     *
     * @param pageURL
     * @param timeout
     * @throws Exception
     */
    public void loadPage(String pageURL, int timeout) throws Exception {
        WebDriver driver = CreateDriver.getInstance().getDriver();
        driver.navigate().to(pageURL);

        // wait for page download, sync. against companylogo
        JavaScriptUtils.isPageReady(driver);
        BrowserUtils.waitFor(companyLogo, timeout);
    }

    /**
     * loadPage - overloaded method to load the page URL and sync against WebElement
     *
     * @param pageURL
     * @param element
     * @throws Exception
     */
    public void loadPage(String pageURL, M element) throws Exception {
        WebDriver driver = CreateDriver.getInstance().getDriver();
        driver.navigate().to(pageURL);

        // wait for page download, sync. against element
        JavaScriptUtils.isPageReady(driver);
        BrowserUtils.waitFor(element, Global_VARS.TIMEOUT_MINUTE);
    }

    /**
     * loadPage - overloaded method to load the page URL and sync against endpoint URL
     *
     * @param pageURL
     * @param endPointUrl
     * @throws Exception
     */
    public void loadPage(String pageURL, String endPointUrl) throws Exception {
        WebDriver driver = CreateDriver.getInstance().getDriver();
        driver.navigate().to(pageURL);

        // wait for page download, sync. against endpoint URL
        JavaScriptUtils.isPageReady(driver);
        BrowserUtils.waitForURL(endPointUrl, Global_VARS.TIMEOUT_MINUTE);
    }
}
