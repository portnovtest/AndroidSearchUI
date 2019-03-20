package com.company;

import com.company.searchui.utils.CreateDriver;
import com.company.searchui.utils.Global_VARS;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Test Setup Base Class
 * (JavaDoc Intentionally left out)
 *
 * @author phildolganov
 *
 */
public abstract class MyCommonSetup {

    // abstract methods
    protected abstract void testClassSetup(ITestContext context) throws Exception;
    protected abstract void testClassTeardown(ITestContext context) throws Exception;
    protected abstract void testMethodSetup(ITestResult result) throws Exception;
    protected abstract void testMethodTeardown(ITestResult result) throws Exception;

    @BeforeSuite(alwaysRun = true, enabled = true)
    protected void suiteSetup(ITestContext context) throws Exception {

    }

    @AfterSuite(alwaysRun = true, enabled = true)
    protected void suiteTeardown(ITestContext context) throws Exception {

    }

    @Parameters({"browser","platform","environment","mobile"})
    @BeforeTest(alwaysRun = true, enabled = true)
    protected void testSetup(@Optional(Global_VARS.BROWSER) String browser, @Optional(Global_VARS.PLATFORM) String platform,
                             @Optional(Global_VARS.ENVIRONMENT) String env, @Optional(Global_VARS.MOBILE) String mobile,
                             ITestContext context) throws Exception {
        // setup driver
        Map<String,Object> prefs = new HashMap<>();
        prefs.put("deviceName", mobile);
        CreateDriver.getInstance().setDriver(browser,platform,env,prefs);
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
