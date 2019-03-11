package com.company.searchui.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PassionTeaMenuPO<M extends WebElement> extends BrowserBasePO<M> {

    public PassionTeaMenuPO() throws Exception {
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

    @FindBy(xpath = "//span[contains(text(),'Green tea originated')]")
    /*
    @FindBy(xpath = "//span[starts-with(text(), 'Green tea')]")
    @FindBy(xpath = "//span[ends-with(text(), 'dietary supplements and cosmetic items.')]")
    @FindBy(xpath = "//span[.='Grean tea is made...']") // equals; requires entire string
    @FindBy(xpath = "//span[text()='Grean tea is made...']") // equals; requires entire string
    @FindBy(xpath = "(//span)[19]")

    protected M greanTea;

    @FindBy(css = "span:contains('Green tea is made from the leaves from Camellia')") // native CSS
    @FindBy(css = "span[innerText*='Green tea is made from']") // Non-Firefox
    @FindBy(css = "span[textContent*'Green tea is made from']") // Firefox
    */
    protected M greanTea;
}
