package com.company.searchui.utils;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TestNG TestListener Class
 *
 */
public class TestNG_Listener extends TestListenerAdapter {
    /**
     *
     * onStart - method to log data before any tests start
     * @param testContext
     */
    @Override
    public void onStart(ITestContext testContext){
        try {
            log("\nSuite Start Date: " + new SimpleDateFormat("MM.dd.yyyy.HH.mm.ss").format(new Date()) + ".log");
        } catch (Exception e){
            e.printStackTrace();
        }
        super.onStart(testContext);
    }

    /**
     * onFinish - method to log data after all tests are complete
     *
     * @param testContext
     */
    @Override
    public void onFinish(ITestContext testContext){
        try {
            log("\nTotal Passed = " + getPassedTests().size() + ", Total Failed = " + getFailedTests().size() +
                    ", Total Skipped = " + getSkippedTests().size() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onFinish(testContext);
    }

    // the following are several other methods that can be customized to log data to the console or log file

    /**
     * onTestSuccess - method to log the results if the test passes
     * 
     * @param tr
     */
    @Override
    public void onTestSuccess(ITestResult tr){
        try {
            log("***Result = PASSED\n");
            log(tr.getEndMillis(), "TEST  -> " + tr.getInstanceName() + "." + tr.getName());
            log("\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onTestSuccess(tr);
    }

    /**
     * log - method to log data to standard out or logfile
     *
     * @param date
     * @param dataLine
     * @throws Exception
     */
    public void log(long date, String dataLine) throws Exception {
        System.out.format("%s%n", String.valueOf(new Date(date)), dataLine);

        if (logFile != null){
            File_IO.writeFile(logFile, dataLine);
        }
    }

    public static String logFile = null;

    /**
     * log - overloaded method to log data to standard out or logfile
     *
     * @param dataLine
     * @throws Exception
     */
    public void log(String dataLine) throws Exception {
        System.out.format("%s%n", dataLine);

        if (logFile != null){
            File_IO.writeFile(logFile, dataLine);
        }
    }
}
