package com.company.searchui.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Selenium JavaScript Executor Utility Class
 *
 */
public class JavaScriptUtils {

    // constructor
    public JavaScriptUtils() {
    }

    /**
     * execute - generic method to execute a non-parameterized JS command
     *
     * @param command
     */
    public static void execute(String command) {
        WebDriver driver = CreateDriver.getInstance().getDriver();

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript(command);
    }

    /**
     * execute - overloaded method to execute a JS command on WebElement
     *
     * @param command
     * @param element
     */
    public static void execute(String command, WebElement element) {
        WebDriver driver = CreateDriver.getInstance().getDriver();

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript(command, element);
    }

    /**
     * click - method to execute a JavaScript click event
     *
     * @param element
     */
    public static void click(WebElement element) {
        WebDriver driver = CreateDriver.getInstance().getDriver();

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", element);
    }

    /**
     * click - overloaded method to execute a JavaScript click event using By
     *
     * @param by
     */
    public static void click(By by) {
        WebDriver driver = CreateDriver.getInstance().getDriver();
        WebElement element = driver.findElement(by);

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", element);
    }

    /**
     * sendKeys - method to execute a JavaScript value event
     *
     * @param keys
     * @param element
     */
    public static void sendKeys(String keys, WebElement element){
        WebDriver driver = CreateDriver.getInstance().getDriver();

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].value='" + keys + "';", element);
    }

    /**
     * isPageReady - method to verify that a page has completely rendered
     *
     * @param driver
     * @return boolean
     */
    public static boolean isPageReady(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        return js.executeScript("return document.readyState").equals("complete");
    }

    /**
     * isAjaxReady - method to verify that an ajax control has rendered
     *
     * @param driver
     * @return boolean
     */
    public static boolean isAjaxReady(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        return (boolean) js.executeScript("return jQuery.active == 0");
    }

    /**
     * setFocusById - method to set focus to web element found by Id
     *
     * @param driver
     * @param id
     */
    public static void setFocusById(WebDriver driver, String id){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("document.getElementById('" + id + "')[0].focus()");
    }

    /**
     * scrollByPixel - method that scrolls the web page to the specific number of pixels
     *
     * @param x_pixels number at x-axis, it moves to the left if number is positive, and to the right if number is negative
     * @param y_pixels number at x-axis, it moves down if the number is positive, and up if number is negative
     */
    public static void scrollByPixel(int x_pixels, int y_pixels){
        WebDriver driver = CreateDriver.getInstance().getDriver();

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(" + x_pixels + "," + y_pixels + ")");
    }

    /**
     * scrollByVisibleElement - method that scrolls the page until the element is visible on the current page
     *
     * @param element locator on web page
     * @param below pass true if the object you are scrolling to is beneath current location, false - if above current location
     */
    public static void scrollIntoView(WebElement element, boolean below) {
        WebDriver driver = CreateDriver.getInstance().getDriver();

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView(" + below + ");", element);
    }

    /**
     * scrollToPageBottom - method that scrolls 'till the end of the page
     *
     */
    public static void scrollToPageBottom(){
        WebDriver driver = CreateDriver.getInstance().getDriver();

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    /**
     * horizontalScroll - method that scrolls the page horizontally until web element is in full view
     *
     * @param element
     */
    public static void horizontalScroll(WebElement element){
        WebDriver driver = CreateDriver.getInstance().getDriver();

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    /**
     * setStyleVisibilityById - method to make web element visible
     *
     * @param id
     */
    public static void setStyleVisibilityById(String id){
        WebDriver driver = CreateDriver.getInstance().getDriver();

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("document.getElementById('" + id + "').style.visibility = 'visible';");
    }

    /**
     * setStyleBlockById method to make web element invisible
     *
     * @param id
     */
    public static void setStyleBlockById(String id){
        WebDriver driver = CreateDriver.getInstance().getDriver();

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("document.getElementById('" + id + "').style.display = 'block';");
    }

    /**
     * setStyleBlockById method to make web element invisible
     *
     * @param className
     */
    public static void setStyleBlockByClassName(String className){
        WebDriver driver = CreateDriver.getInstance().getDriver();

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("document.getElementByClassName('" + className + "').style.display = 'block';");
    }
}
