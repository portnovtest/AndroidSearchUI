package com.company;

import com.company.searchui.pageObjects.GMailLoginPO;
import com.company.searchui.utils.CreateDriver;
import com.company.searchui.utils.Global_VARS;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class DualWebdriverTest extends MyAppTest {

    @Test
    public void tc001_multiWebDriver(String rowID, String description) throws Exception {
        // create the first webdriver instance
        CreateDriver.getInstance().setDriver("chrome",Global_VARS.DEF_ENVIRONMENT,Global_VARS.DEF_PLATFORM);

        // save the first WebDriver instance
        WebDriver chromeDriver = CreateDriver.getInstance().getDriver();

        // create the second WebDriver instance
        CreateDriver.getInstance().setDriver("firefox",Global_VARS.DEF_ENVIRONMENT,Global_VARS.DEF_PLATFORM);

        // save the second WebDriver instance
        WebDriver firefoxDriver = CreateDriver.getInstance().getDriver();

        // switch back to the chrome driver
        CreateDriver.getInstance().setDriver(chromeDriver);

        // create a page object class instance that will use this driver
        GMailLoginPO gmail = new GMailLoginPO();
        gmail.login("user1","password1");

        // switch back to the firefox driver
        CreateDriver.getInstance().setDriver(firefoxDriver);

        // create a page object class instance that will use this driver
        GMailLoginPO gmail2 = new GMailLoginPO();
        gmail2.login("user2","password2");

        // test sending mail back and forth to each user via the 2 clients

        // switch back to chrome and quit driver
        CreateDriver.getInstance().setDriver(chromeDriver);
        chromeDriver.quit();

        // switch back to firefox and quit driver
        CreateDriver.getInstance().setDriver(firefoxDriver);
        firefoxDriver.quit();
    }

    // dual WebDriver and AppiumDriver testing

    @Test
    public void tc002_multiWebMobileDriver(String rowID,String description) throws Exception {
        // create the WebDriver instance
        CreateDriver.getInstance().setDriver("chrome",Global_VARS.DEF_ENVIRONMENT,Global_VARS.DEF_PLATFORM);

        // save the WebDriver instance
        WebDriver chromeDriver = CreateDriver.getInstance().getDriver();

        // create the MobileDriver instance, passing in device name
        Map<String,Object> preferences = new HashMap<>();
        preferences.put("deviceName","iPhone 6 Simulator");

        CreateDriver.getInstance().setDriver("iPhone", Global_VARS.DEF_ENVIRONMENT,
                Global_VARS.DEF_PLATFORM, preferences);

        // save the MobileDriver instance
        AppiumDriver<MobileElement> mobileDriver = CreateDriver.getInstance().getDriver(true);

        // switch back to the chrome driver
        CreateDriver.getInstance().setDriver(chromeDriver);
        // perform some actions on the WebDriver classes

        // switch back to the mobile driver
        CreateDriver.getInstance().setDriver(mobileDriver);
        // perform some actions on the MobileDriver classes

        // switch back to chrome and quit driver
        CreateDriver.getInstance().setDriver(chromeDriver);
        chromeDriver.quit();

        // switch back to iphone and quit the driver
        CreateDriver.getInstance().setDriver(mobileDriver);
        mobileDriver.quit();
    }
}
