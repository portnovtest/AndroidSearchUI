package com.company;

import com.company.searchui.utils.CreateDriver;
import com.company.searchui.utils.Global_VARS;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Test Setup Base Class
 * (JavaDoc Intentionally left out)
 *
 * @author phildolganov
 *
 */
public abstract class CommonSetup {

    public static Properties testProps = new Properties();

    // abstract methods
    protected abstract void testClassSetup(ITestContext context) throws Exception;
    protected abstract void testClassTeardown(ITestContext context) throws Exception;
    protected abstract void testMethodSetup(ITestResult result) throws Exception;
    protected abstract void testMethodTeardown(ITestResult result) throws Exception;

    @Parameters({"browser","platform","environment"})
    @BeforeSuite(alwaysRun = true, enabled = true)
    protected void suiteSetup(@Optional(Global_VARS.BROWSER) String browser, @Optional(Global_VARS.PLATFORM) String platform,
                              @Optional(Global_VARS.ENVIRONMENT) String environment) throws Exception {
        Global_VARS.DEF_BROWSER = System.getProperty("browser",browser);
        Global_VARS.DEF_PLATFORM = System.getProperty("platform",platform);
        Global_VARS.DEF_ENVIRONMENT = System.getProperty("environment",environment);

        CreateDriver.getInstance().setDriver(Global_VARS.DEF_BROWSER,Global_VARS.DEF_PLATFORM,Global_VARS.DEF_ENVIRONMENT);
    }

    @AfterSuite(alwaysRun = true, enabled = true)
    protected void suiteTeardown(ITestContext context) throws Exception {

    }


    @BeforeTest(alwaysRun = true, enabled = true)
    protected void testSetup(ITestContext context) throws Exception {
        // setup driver
//        Map<String,Object> prefs = new HashMap<>();
//        prefs.put("deviceName", mobile);
//        CreateDriver.getInstance().setDriver(browser,platform,env,prefs);
    }

    @AfterTest(alwaysRun = true, enabled = true)
    protected void testTeardown(ITestContext context) throws Exception {
        CreateDriver.getInstance().closeDriver();
    }

    @BeforeClass(alwaysRun = true,enabled = true)
    protected void classSetup(ITestContext context) throws Exception {

    }

    @AfterClass(alwaysRun = true,enabled = true)
    protected void classTeardown(ITestContext context) throws Exception {

    }

    @BeforeMethod(alwaysRun = true,enabled = true)
    protected void methodSetup(ITestResult result) throws Exception {

    }

    @AfterMethod(alwaysRun = true,enabled = true)
    protected void methodTeardown(ITestResult result) throws Exception {

    }
}
