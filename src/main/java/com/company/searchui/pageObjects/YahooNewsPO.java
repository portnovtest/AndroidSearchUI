package com.company.searchui.pageObjects;

import org.openqa.selenium.WebElement;

import static org.testng.Assert.assertEquals;

// Yahoo News Subclass
public class YahooNewsPO<M extends WebElement> extends YahooBasePage<M> {

    public YahooNewsPO() throws Exception {
        super();
    }
    public void verifyYahooLogo(String expHref) throws Exception {
        String actHref = yahooLogo.getAttribute("href");
        assertEquals(actHref, expHref, "Verify Yahoo Logo HERE");
    }
}
