package com.company.searchui.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Browser Utility Class
 *
 * @author phildolganov
 *
 */
public class BrowserUtils {

    /**
     * waitFor method to wait up to a designated period before throwing exception (static locator)
     * @param element
     * @param timer
     * @throws Exception
     */
    public static void waitFor(WebElement element, int timer) throws Exception {
        WebDriver driver = CreateDriver.getInstance().getDriver();

        // Wait for the static element to appear
        WebDriverWait exists = new WebDriverWait(driver, timer);
        exists.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
    }

    /**
     * overloaded waitFor method to wait up to a designated period before throwing exception (dynamic locator)
     *
     * @param by
     * @param timer
     * @throws Exception
     */
    public static void waitFor(By by, int timer) throws Exception {
        WebDriver driver = CreateDriver.getInstance().getDriver();

        // wait for the dynamic element to appear
        WebDriverWait exists = new WebDriverWait(driver, timer);

        // examples: By.id(id), By.name(name), By.xpath(locator), By.cssSelector(css)
        exists.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(by)));
    }

    /**
     * waitForGone method to wait up to a designated period before throwing exception if element still exists
     *
     * @param by
     * @param timer
     * @throws Exception
     */
    public static void waitForGone(By by, int timer) throws Exception {
        WebDriver driver = CreateDriver.getInstance().getDriver();

        // wait for dynamic element to disappear
        WebDriverWait exists = new WebDriverWait(driver, timer);

        // examples: By.id(id), By.name(name), By.xpath(locator), By.cssSelector(css)
        exists.until(ExpectedConditions.refreshed(ExpectedConditions.invisibilityOfElementLocated(by)));
    }

    /**
     * waitForURL method to wait up to a designated period before throwing exception if URL is not found
     *
     * @param url
     * @param seconds
     * @throws Exception
     */
    public static void waitForURL(String url, int seconds) throws Exception {
        WebDriver driver = CreateDriver.getInstance().getDriver();
        WebDriverWait exists = new WebDriverWait(driver, seconds);
        exists.until(ExpectedConditions.refreshed(ExpectedConditions.urlContains(url)));
    }

    /**
     * waitFor method to wait up to a designated period before throwing exception if Title is not found
     *
     * @param title
     * @param timer
     * @throws Exception
     */
    public static void waitFor(String title, int timer) throws Exception {
        WebDriver driver = CreateDriver.getInstance().getCurrentDriver();
        WebDriverWait exists = new WebDriverWait(driver, timer);

        exists.until(ExpectedConditions.refreshed(ExpectedConditions.titleContains(title)));
    }

    /**
     * elementExists - wrapper around the WebDriverWait method to return true or false
     *
     * @param element
     * @param timer
     * @return
     */
    public static boolean elementExists(WebElement element, int timer){
        try {
            WebDriver driver = CreateDriver.getInstance().getCurrentDriver();
            WebDriverWait exists = new WebDriverWait(driver, timer);
            exists.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * lookupMessage - method to retrieve error messages using code
     *
     * @param propFilePath - the property file including path
     * @param code - the confirmation or error code
     * @return String
     * @throws Exception
     */
    public static String lookupMessage(String propFilePath, String code) throws Exception {
        Properties props = new Properties();
        props.load(new FileInputStream(propFilePath));
        String getMsg = props.getProperty(code,null);

        if (getMsg != null){
            return getMsg;
        } else {
            throw new Exception("ERROR: The Code '" + code + "' was not found!");
        }
    }
}
