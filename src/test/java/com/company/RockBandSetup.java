package com.company;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

// use of setup/teardown methods in base class
public abstract class RockBandSetup {

    // abstract methods
    protected abstract void testMethodSetup(ITestResult result) throws Exception;

    protected abstract void testMethodTeardown(ITestResult result) throws Exception;

    // setup/teardown methods
    @BeforeSuite
    protected void suiteSetup(ITestContext context) throws Exception{

    }

    @AfterSuite
    protected void suiteTeardown(ITestContext context) throws Exception{

    }

    @BeforeClass
    protected void testClassSetup() throws Exception {

    }

    @AfterClass
    protected void testClassTeardown() throws Exception {

    }
}

