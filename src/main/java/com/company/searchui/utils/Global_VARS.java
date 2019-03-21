package com.company.searchui.utils;

import java.io.File;

/**
 * Global Variable Class
 *
 * @author phildolganov
 *
 */
public class Global_VARS {
    // target app defaults
    public static final String BROWSER = "firefox";
    public static final String PLATFORM = "Windows 10";
    public static final String ENVIRONMENT = "local";
    public static final String MOBILE = "iPhone 6 Plus";
    public static String DEF_BROWSER = null;
    public static String DEF_PLATFORM = null;
    public static String DEF_ENVIRONMENT = null;
    public static String PROPS_PATH = null;

    // driver class defaults
    public static String propFile = "../myPath/selenium.props";
    public static final String SE_PROPS = new File(propFile).getAbsolutePath();

    // test output path defaults
    public static final String TEST_OUTPUT_PATH = "testOutput/";
    public static final String LOG_FILE_PATH = TEST_OUTPUT_PATH + "Logs/";
    public static final String REPORT_PATH = TEST_OUTPUT_PATH + "Reports/";
    public static final String BITMAP_PATH = TEST_OUTPUT_PATH + "Bitmaps";

    // timeout defaults
    public static final int TIMEOUT_MINUTE = 60;
    public static final int TIMEOUT_SECOND = 1;
    public static final int TIOMOUT_ZERO = 0;
    public static final int TIMEOUT_ELEMENT = 10;

    // property file path
    public static final String EXCEPTION_MESSAGES = "/Users/phildolganov/IdeaProjects/AndroidSearchUI/src/test/resources/exception.properties";
    public static final String TEST_PROPS_PATH = "/Users/phildolganov/IdeaProjects/AndroidSearchUI/src/test/resources/propertyFile.properties";

    // user defaults
    public static String DEFAULT_URL = null;
    public static String DEFAULT_USR = null;
    public static String DEFAULT_PWD = null;

}
