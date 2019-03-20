package com.company;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

/**
 * Test Class Method
 *
 * @author phildolganov
 *
 */
public class MyAppTest extends MyCommonSetup {

    // implemented abstract methods
    @Override
    @BeforeClass(alwaysRun = true, enabled = true)
    protected void testClassSetup(ITestContext ctxt) throws Exception {

    }

    @Override
    @AfterClass(alwaysRun = true,enabled = true)
    protected void testClassTeardown(ITestContext ctxt) throws Exception {

    }

    @Override
    @BeforeMethod(alwaysRun = true,enabled = true)
    protected void testMethodSetup(ITestResult rslt) throws Exception {

    }

    @Override
    @AfterMethod(alwaysRun = true,enabled = true)
    protected void testMethodTeardown(ITestResult rslt) throws Exception {

    }

    // these methods override the Superclass methods
    @Override
    @BeforeClass(alwaysRun = true,enabled = true)
    protected void classSetup(ITestContext ctxt) throws Exception {

    }

    @Override
    @AfterClass(alwaysRun = true,enabled = true)
    protected void classTeardown(ITestContext ctxt) throws Exception {

    }

    @Override
    @BeforeMethod(alwaysRun = true,enabled = true)
    protected void methodSetup(ITestResult rslt) throws Exception {

    }

    @Override
    @AfterMethod(alwaysRun = true,enabled = true)
    protected void methodTeardown(ITestResult rslt) throws Exception {

    }
}
