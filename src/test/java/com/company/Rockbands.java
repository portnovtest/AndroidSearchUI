package com.company;

import org.json.simple.JSONArray;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

// use of @Override to override setup/teardown methods
public class Rockbands extends RockBandSetup {

    // implement abstract methods
    @BeforeMethod
    protected void testMethodSetup(ITestResult result) throws Exception {

    }

    @AfterMethod
    protected void testMethodTeardown(ITestResult result) throws Exception {

    }

    // overridden inherited methods
    @Override
    @BeforeClass
    protected void testClassSetup() throws Exception {

    }

    @Override
    @AfterClass
    protected void testClassTeardown() throws Exception {

    }
}
