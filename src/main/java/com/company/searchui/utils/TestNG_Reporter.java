package com.company.searchui.utils;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;

import java.util.List;

/**
 * TestNG_Reporter Class
 *
 * Note: This report relies on the TestNG Suite XML file structure
 *
 * @author phildolganov
 *
 */
public class TestNG_Reporter implements IReporter {

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDir) {

        for (ISuite suite : suites) {
            // the report is entirely customizable from here users can pull in results from ISuiteResult
            // and ITestResult to output to a file, console or use a third-party API for HTML reporting
        }
    }
}
