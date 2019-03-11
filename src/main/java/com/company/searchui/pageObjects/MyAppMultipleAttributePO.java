package com.company.searchui.pageObjects;

import com.company.searchui.utils.CreateDriver;
import com.company.searchui.utils.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.company.searchui.utils.BrowserUtils.waitForGone;
import static org.testng.Assert.assertEquals;

public class MyAppMultipleAttributePO<M extends WebElement> extends BrowserBasePO<M> {
    public MyAppMultipleAttributePO() throws Exception {
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

    // Or conditions
    // xpath
    @FindBy(xpath = "//img[@src='myLogo.png' or @src='myLogo.svg']")

    // css
   // @FindBy(css = "div[id*='progressBar'], a[@id*='progressBar', i[id*='progressBar']")
    protected M logo;

    // And conditions
    // Xpath
    @FindBy(xpath = "//div[contains(@class, 'header') and contains(text(), 'label')]")

    // css
   // @FindBy(css = "input[id*='email'][name='username']")
    protected M someElement;

    // Parent, child, sibling, relatives:

    // ancestor
    @FindBy(xpath = "//input[@id='myID']/ancestor::div/span")
    protected M ancestor;

    // descendant
    @FindBy(xpath = "//*[@id='myModal']/descendant::h2")
    protected M descendant;

    // following
    @FindBy(xpath = "//div[starts-with(text(),'title')]/following::i[@class='icon-close']")
    protected M following;

    // following-sibling
    @FindBy(xpath = "//div[.='label']/following-sibling::div[@class='myGraphic']")
    protected M followingSibling;

    // preceding
    @FindBy(xpath = "//a[contains(text(),'myID')]/preceding::input[@class='myCheckbox']")
    protected M preceding;

    // preceding-sibling
    @FindBy(xpath = "//div[.='label']/preceding-sibling::div[@class='myGraphic']")
    protected M precedingSibling;

    // Using Dynamic Locators In Methods

    public void verifyLabel(String pattern, String label)throws Exception {
        WebDriver driver = CreateDriver.getInstance().getDriver();
        String locator = "//label[contains(text(),'" + pattern + "')]";

        assertEquals(driver.findElement(By.xpath(locator)).getText(),label);
    }

    public void verifyLabelAndControl(String pattern, String label) throws Exception {
        WebDriver driver = CreateDriver.getInstance().getDriver();
        String locator = "//label[contains(text(),'" + pattern + "')]/following::div[@class='help-text']";

        assertEquals(driver.findElement(By.xpath(locator)).getText(), label);
    }

    // build locator on the fly for an unpredictable element, that being a set of dialogs, and it uses an index as part of the locator

    public void cleanup() {
        String locator = "(//i[@class='icon-close'])[";
        WebDriver driver = CreateDriver.getInstance().getDriver();

        for (int i = 10; i > 0; i--) {
            WebElement element = null;
            try {
                element = driver.findElement(By.xpath(locator + i + "])"));

                if (BrowserUtils.elementExists(element, 0)){
                    element.click();
                    waitForGone(By.xpath(locator + i + "]"), 1);
                }
            } catch (Exception e) {
               // do nothing, just trap it...
            }
        }
    }
}
