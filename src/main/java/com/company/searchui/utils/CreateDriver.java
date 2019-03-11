package com.company.searchui.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Selenium Singleton Class
 *
 * @author phildolganov
 *
 */
@SuppressWarnings("varargs")
public class CreateDriver {
    // local variables
    private static CreateDriver instance = null;
    private String browserHandle = null;
    private static final int IMPLICIT_TIMEOUT = 0;

    private ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
    private ThreadLocal<AppiumDriver<MobileElement>> mobileDriver = new ThreadLocal<>();
    private ThreadLocal<String> sessionId = new ThreadLocal<>();
    private ThreadLocal<String> sessionBrowser = new ThreadLocal<>();
    private ThreadLocal<String> sessionPlatform = new ThreadLocal<>();
    private ThreadLocal<String> sessionVersion = new ThreadLocal<>();
    private String getEnv = null;

    private Properties driverProps = new Properties();
    private static final String propertyFile = new File("../myProject/com/path/selenium.properties").getAbsolutePath();

    // constructor
    private CreateDriver() {
    }

    /**
     * getInstance method to retreive active driver instance
     *
     * @return CreateDriver
     */
    public static CreateDriver getInstance() {
        if (instance == null) {
            instance = new CreateDriver();
        }
        return instance;
    }

    /**
     * setDriver method
     * @param browser
     * @param platform
     * @param environment
     * @param optPreferences
     * @throws Exception
     */
    @SafeVarargs
    public final void setDriver(String browser,
                                String platform,
                                String environment,
                                Map<String, Object>...
                                            optPreferences) throws Exception {
        DesiredCapabilities caps = null;
        // load properties from file...
        driverProps.load(new FileInputStream(propertyFile));

        String localHub = "http://127.0.0.1:4723/wd/hub";
        String getPlatform = null;

        switch (browser){
            case "firefox":
                caps = DesiredCapabilities.firefox();

                FirefoxOptions ffOpts = new FirefoxOptions();
                FirefoxProfile ffProfile = new FirefoxProfile();
                ffProfile.setPreference("browser.autofocus", true);

                caps.setCapability(FirefoxDriver.PROFILE, ffProfile);
                caps.setCapability("marionette", true);
               // System.setProperty("webdriver.gecko.driver", "gecko_driver_windows_path/geckodriver.exe");

                if (optPreferences.length > 0){
                    processFFProfile(ffProfile, optPreferences);
                }

                // for each browser instance
                if (environment.equalsIgnoreCase("remote")){
                    // Set up the Selenium Grid capabilities...
                    String remoteHubURL = "http://mygrid-hub.companyname.com:4444/wd/hub";

                    caps.setCapability("browserName", browser);
                    caps.setCapability("version", caps.getVersion());
                    caps.setCapability("platform", platform);
                    // unique user-specified name
                    caps.setCapability("applicationName",platform + "-" + browser);
                    webDriver.set(new RemoteWebDriver(new URL(remoteHubURL), caps));
                } else if (environment.equalsIgnoreCase("saucelabs")){
                    // setup the Selenium Grid capabilities...
                    String remoteHubURL = "http://SAUCE_USERNAME:SAUCE_ACCESS_KEY@ondemand.saucelabs.com:80/wd/hub";
                    caps.setCapability("screenResolution", "1920x1080");
                    caps.setCapability("recordVideo", false);
                    caps.setCapability("tunnelIdentifier", System.getProperty("TUNNEL_IDENTIFIER"));
                } else if (environment.equalsIgnoreCase("local")){
                    if (platform.toLowerCase().contains("windows")){
                        System.setProperty("webdriver.gecko.driver", driverProps.getProperty("gecko.driver.windows.path"));
                    }
                    webDriver.set(new FirefoxDriver(caps));
                }
                //Selenium 3.7.x
                //webDriver.set(new FirefoxDriver(ffOpts.merge(caps)));

                break;
            case "chrome":
                caps = DesiredCapabilities.chrome();

                ChromeOptions chOptions = new ChromeOptions();
                Map<String,Object> chromePrefs = new HashMap<>();

                chromePrefs.put("credentials_enable_service", false);
                chOptions.setExperimentalOption("prefs", chromePrefs);
                chOptions.addArguments("--disable-plugins", "--disable-extensions", "--disable-popup-blocking");

                caps.setCapability(ChromeOptions.CAPABILITY, chOptions);
                caps.setCapability("applicationCacheEnabled", false);
                System.setProperty("webdriver.chrome.driver", "chrome_driver_windows_path/chromedriver.exe");

                if (optPreferences.length > 0){
                    processCHOptions(chOptions, optPreferences);
                }

                webDriver.set(new ChromeDriver(caps));

                // for each browser instance
                if (environment.equalsIgnoreCase("remote")){
                    // Set up the Selenium Grid capabilities...
                    String remoteHubURL = "http://mygrid-hub.companyname.com:4444/wd/hub";

                    caps.setCapability("browserName", browser);
                    caps.setCapability("version", caps.getVersion());
                    caps.setCapability("platform", platform);
                    // unique user-specified name
                    caps.setCapability("applicationName",platform + "-" + browser);
                    webDriver.set(new RemoteWebDriver(new URL(remoteHubURL), caps));
                } else if (environment.equalsIgnoreCase("saucelabs")){
                    // setup the Selenium Grid capabilities...
                    String remoteHubURL = "http://SAUCE_USERNAME:SAUCE_ACCESS_KEY@ondemand.saucelabs.com:80/wd/hub";
                    caps.setCapability("screenResolution", "1920x1080");
                    caps.setCapability("recordVideo", false);
                    caps.setCapability("tunnelIdentifier", System.getProperty("TUNNEL_IDENTIFIER"));
                }
                // Selenium 3.7.x
                // webDriver.set(new ChromeDriver(chOptions.merge(caps)));

                break;
            case "internet explorer":
                caps = DesiredCapabilities.internetExplorer();

                InternetExplorerOptions ieOpts = new InternetExplorerOptions();
                ieOpts.requireWindowFocus();

                ieOpts.merge(caps);
                caps.setCapability("requireWindowFocus", true);
                System.setProperty("webdriver.ie.driver", "ie_driver_windows_path/IEDriverServer.exe");

                if (optPreferences.length > 0){
                    processDesiredCaps(caps, optPreferences);
                }

                webDriver.set(new InternetExplorerDriver(caps));

                // for each browser instance
                if (environment.equalsIgnoreCase("remote")){
                    // Set up the Selenium Grid capabilities...
                    String remoteHubURL = "http://mygrid-hub.companyname.com:4444/wd/hub";

                    caps.setCapability("browserName", browser);
                    caps.setCapability("version", caps.getVersion());
                    caps.setCapability("platform", platform);
                    // unique user-specified name
                    caps.setCapability("applicationName",platform + "-" + browser);
                    webDriver.set(new RemoteWebDriver(new URL(remoteHubURL), caps));
                } else if (environment.equalsIgnoreCase("saucelabs")){
                    // setup the Selenium Grid capabilities...
                    String remoteHubURL = "http://SAUCE_USERNAME:SAUCE_ACCESS_KEY@ondemand.saucelabs.com:80/wd/hub";
                    caps.setCapability("screenResolution", "1920x1080");
                    caps.setCapability("recordVideo", false);
                    caps.setCapability("tunnelIdentifier", System.getProperty("TUNNEL_IDENTIFIER"));
                }
                // Selenium 3.7.x
                //webDriver.set(new InternetExplorerDriver(ieOpts.merge(caps)));

                break;
            case "safari":
                caps = DesiredCapabilities.safari();

                SafariOptions safariOpts = new SafariOptions();
                safariOpts.setUseTechnologyPreview(false);
                // safariOpts.setUseCleanSession(true);
                caps.setCapability(SafariOptions.CAPABILITY,safariOpts);
                caps.setCapability("autoAcceptAlerts", true);
                webDriver.set(new SafariDriver(caps));

                // for each browser instance
                if (environment.equalsIgnoreCase("remote")){
                    // Set up the Selenium Grid capabilities...
                    String remoteHubURL = "http://mygrid-hub.companyname.com:4444/wd/hub";

                    caps.setCapability("browserName", browser);
                    caps.setCapability("version", caps.getVersion());
                    caps.setCapability("platform", platform);
                    // unique user-specified name
                    caps.setCapability("applicationName",platform + "-" + browser);
                    webDriver.set(new RemoteWebDriver(new URL(remoteHubURL), caps));
                } else if (environment.equalsIgnoreCase("saucelabs")){
                    // setup the Selenium Grid capabilities...
                    String remoteHubURL = "http://SAUCE_USERNAME:SAUCE_ACCESS_KEY@ondemand.saucelabs.com:80/wd/hub";
                    caps.setCapability("screenResolution", "1920x1080");
                    caps.setCapability("recordVideo", false);
                    caps.setCapability("tunnelIdentifier", System.getProperty("TUNNEL_IDENTIFIER"));
                }
                // Selenium 3.7.x
                // webDriver.set(new SafariDriver(safariOpts.merge(caps)));

                break;
            case "microsoftEdge":
                caps = DesiredCapabilities.edge();

                EdgeOptions edgeOpts = new EdgeOptions();
                edgeOpts.setPageLoadStrategy("normal");

                // caps.setCapability(EdgeOptions.CAPABILITY, edgeOpts);
                caps.setCapability("requireWindowFocus", true);

                webDriver.set(new EdgeDriver(caps));

                // for each browser instance
                if (environment.equalsIgnoreCase("remote")){
                    // Set up the Selenium Grid capabilities...
                    String remoteHubURL = "http://mygrid-hub.companyname.com:4444/wd/hub";

                    caps.setCapability("browserName", browser);
                    caps.setCapability("version", caps.getVersion());
                    caps.setCapability("platform", platform);
                    // unique user-specified name
                    caps.setCapability("applicationName",platform + "-" + browser);
                    webDriver.set(new RemoteWebDriver(new URL(remoteHubURL), caps));
                } else if (environment.equalsIgnoreCase("saucelabs")){
                    // setup the Selenium Grid capabilities...
                    String remoteHubURL = "http://SAUCE_USERNAME:SAUCE_ACCESS_KEY@ondemand.saucelabs.com:80/wd/hub";
                    caps.setCapability("screenResolution", "1920x1080");
                    caps.setCapability("recordVideo", false);
                    caps.setCapability("tunnelIdentifier", System.getProperty("TUNNEL_IDENTIFIER"));
                }
                // Selenium 3.7.x
                // webDriver.set(new EdgeDriver(edgeOpts.merge(caps)));

                break;
            case "iphone":
            case "ipad":
                if (browser.equalsIgnoreCase("ipad")){
                    caps = DesiredCapabilities.ipad();
                } else {
                    caps = DesiredCapabilities.iphone();
                }

                caps.setCapability("appName", "https://myapp.com/myApp.zip");
                caps.setCapability("udid", "12345678"); // physical device
                caps.setCapability("device", "iPhone"); // or iPad

                mobileDriver.set(new IOSDriver<>(new URL(localHub), caps));

                // for each mobile device instance
                if (environment.equalsIgnoreCase("remote")){
                    // Set up theSelenium Grid capabilities...
                    String remoteHubURL = "http://mygrid-hub.companyname.com:4444/wd/hub";
                    caps.setCapability("browserName", browser);
                    caps.setCapability("platform", platform);

                    // unique user-specified name
                    caps.setCapability("applicationName", platform + "-" + browser);
                    if (browser.contains("iphone")){
                        mobileDriver.set(new IOSDriver<>(new URL(remoteHubURL), caps));
                    } else {
                        mobileDriver.set(new AndroidDriver<>(new URL(remoteHubURL), caps));
                    }
                } else if (environment.equalsIgnoreCase("saucelabs")){
                    // setup the Selenium Grid capabilities...
                    String remoteHubURL = "http://SAUCE_USERNAME:SAUCE_ACCESS_KEY@ondemand.saucelabs.com:80/wd/hub";
                    caps.setCapability("screenResolution", "1920x1080");
                    caps.setCapability("recordVideo", false);
                    caps.setCapability("tunnelIdentifier", System.getProperty("TUNNEL_IDENTIFIER"));
                }

                break;
            case "android":
                caps = DesiredCapabilities.android();

                caps.setCapability("appName", "https://myapp.com/myApp.apk");
                caps.setCapability("udid","12345678"); // physical device
                caps.setCapability("device", "Android");

                mobileDriver.set(new AndroidDriver<>(new URL(localHub), caps));

                // for each mobile device instance
                if (environment.equalsIgnoreCase("remote")){
                    // Set up theSelenium Grid capabilities...
                    String remoteHubURL = "http://mygrid-hub.companyname.com:4444/wd/hub";
                    caps.setCapability("browserName", browser);
                    caps.setCapability("platform", platform);

                    // unique user-specified name
                    caps.setCapability("applicationName", platform + "-" + browser);
                    if (browser.contains("iphone")){
                        mobileDriver.set(new IOSDriver<>(new URL(remoteHubURL), caps));
                    } else {
                        mobileDriver.set(new AndroidDriver<>(new URL(remoteHubURL), caps));
                    }
                } else if (environment.equalsIgnoreCase("saucelabs")){
                    // setup the Selenium Grid capabilities...
                    String remoteHubURL = "http://SAUCE_USERNAME:SAUCE_ACCESS_KEY@ondemand.saucelabs.com:80/wd/hub";
                    caps.setCapability("screenResolution", "1920x1080");
                    caps.setCapability("recordVideo", false);
                    caps.setCapability("tunnelIdentifier", System.getProperty("TUNNEL_IDENTIFIER"));
                }

                break;
        }
    }

    public void setBrowserHandle(String browserHandle) {
        this.browserHandle = browserHandle;
    }

    /**
     * overloaded setDriver method to switch driver to specific WebDriver
     * if running concurrent drivers
     *
     * @param driver WebDriver instance to switch to
     */
    public void setDriver(WebDriver driver){
        webDriver.set(driver);
        sessionId.set(((RemoteWebDriver) webDriver.get()).getCapabilities().getBrowserName());
        sessionPlatform.set(((RemoteWebDriver) webDriver.get()).getCapabilities().getPlatform().toString());
        setBrowserHandle(getDriver().getWindowHandle());
    }

    /**
     * overloaded setDriver method to switch driver to specific AppiumDriver
     * if running concurrent drivers
     * @param driver
     */
    public void setDriver(AppiumDriver<MobileElement> driver){
        mobileDriver.set(driver);
        sessionId.set(mobileDriver.get().getSessionId().toString());
        sessionBrowser.set(mobileDriver.get().getCapabilities().getBrowserName());
        sessionPlatform.set(mobileDriver.get().getCapabilities().getPlatform().toString());
    }

    /**
     * getDriver method will retrieve the active WebDriver
     *
     * @return WebDriver
     */
    public WebDriver getDriver(){
        return webDriver.get();
    }

    /**
     * getDriver method will retrieve the active AppiumDriver
     * @param mobile boolean parameter
     * @return AppiumDriver
     */
    public AppiumDriver<MobileElement> getDriver(boolean mobile){
        return mobileDriver.get();
    }

    /**
     * getCurrentDriver method will retrieve the active WebDriver or AppiumDriver
     *
     * @return WebDriver
     */
    public WebDriver getCurrentDriver() throws Exception {
        if (getInstance().getSessionBrowser().contains("iphone") ||
            getInstance().getSessionBrowser().contains("ipad") ||
                getInstance().getSessionBrowser().contains("android")){
            return getInstance().getDriver(true);
        } else {
            return getInstance().getDriver();
        }
    }

    /**
     * driverWait method pauses the driver in seconds
     *
     * @param seconds to pause
     */
    public void driverWait(long seconds){
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(seconds));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * driverRefresh method reloads the current browser page
     */
    public void driverRefresh() throws Exception {
        getCurrentDriver().navigate().refresh();
    }

    /**
     * closeDriver method quits the current active driver
     */
    public void closeDriver(){
        try {
            getCurrentDriver().quit();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * getSessionId method togets the browser or mobile id
     * of the active session
     *
     * @return String
     */
    public String getSessionId() {
        return sessionId.get();
    }

    /**
     * getSessionBrowser method gets the browser or mobile type
     * of the active session
     *
     * @return String
     */
    public String getSessionBrowser() {
        return sessionBrowser.get();
    }

    /**
     * getSessionVersion method gets the browser or mobile version
     * of the active session
     *
     * @return String
     */
    public String getSessionVersion() {
        return sessionVersion.get();
    }

    /**
     * getSessionPlatform method gets the browser or mobile platform
     * of the active session
     *
     * @return String
     */
    public String getSessionPlatform() {
        return sessionPlatform.get();
    }

    /**
     * setPreference method builds the map to pass into the driver
     *
     * @return prefsMap
     */
    public Map<String,Object> setPreferences(){
        Map<String,Object> prefsMap = new HashMap<>();
        List<String> allPrefs = Arrays.asList(System.getProperty("browserPrefs").split(",", -1));
        // Extract the key/value pairs and pass to map...
        for (String getPref : allPrefs) {
            prefsMap.put(getPref.split(":")[0], getPref.split(":")[1]);
        }
        return prefsMap;
    }

    /**
     * Process Desired Capabilities method to override default browser
     * or mobile driver behavior
     *
     * @param caps - the DesiredCapabilities object
     * @param options - the key: value pair map
     * @throws Exception
     */
    private void processDesiredCaps(DesiredCapabilities caps, Map<String,Object>[] options) throws Exception {
        for (int i = 0; i < options.length; i++) {
            Object[] keys = options[i].keySet().toArray();
            Object[] values = options[i].values().toArray();

            for (int j = 0; j < keys.length; j++) {
                if (values[j] instanceof Integer){
                    caps.setCapability(keys[j].toString(), (int)values[j]);
                } else if (values[j] instanceof Boolean){
                    caps.setCapability(keys[j].toString(), (boolean)values[j]);
                } else if (isStringInt(values[j].toString())){
                    caps.setCapability(keys[j].toString(), Integer.valueOf(values[j].toString()));
                } else if (Boolean.parseBoolean(values[j].toString())){
                    caps.setCapability(keys[j].toString(), Boolean.valueOf(values[j].toString()));
                } else {
                    caps.setCapability(keys[j].toString(), values[j].toString());
                }
            }
        }
    }

    /**
     * Process Firefox Profile Preferences method to override default
     * browser driver behavior
     *
     * @param profile - the FirefoxProfile object
     * @param options - the key: value pair map
     * @throws Exception
     */
    private void processFFProfile(FirefoxProfile profile, Map<String,Object>[] options) throws Exception {
        for (int i = 0; i < options.length; i++) {
            Object[] keys = options[i].keySet().toArray();
            Object[] values = options[i].values().toArray();

            // same as Desired Caps except the following difference
            for (int j = 0; j < keys.length; j++) {
                if (values[j] instanceof Integer){
                    profile.setPreference(keys[j].toString(), (int) values[j]);
                } else if (values[j] instanceof Boolean){
                    profile.setPreference(keys[j].toString(), (boolean)values[j]);
                } else if (isStringInt(values[j].toString())){
                    profile.setPreference(keys[j].toString(), Integer.valueOf(values[j].toString()));
                } else if (Boolean.parseBoolean(values[j].toString())){
                    profile.setPreference(keys[j].toString(), Boolean.valueOf(values[j].toString()));
                } else {
                    profile.setPreference(keys[j].toString(), values[j].toString());
                }
            }
        }
    }

    /**
     * Process Chrome Options method to override default browser driver behavior
     *
     * @param chOptions - the ChromeOptions object
     * @param options - the key: value pair map
     * @throws Exception
     */
    private void processCHOptions(ChromeOptions chOptions, Map<String,Object>[] options) throws Exception {
        for (int i = 0; i < options.length; i++) {
            Object[] keys = options[i].keySet().toArray();
            Object[] values = options[i].values().toArray();

            // same as Desired Caps except the following difference
            for (int j = 0; j < keys.length; j++) {
                if (values[j] instanceof Integer){
                    values[j] = (int) values[j];
                    chOptions.setExperimentalOption("prefs", options[i]);
                } else if (values[j] instanceof Boolean){
                    chOptions.setExperimentalOption(keys[j].toString(), (boolean)values[j]);
                } else if (isStringInt(values[j].toString())){
                    chOptions.setExperimentalOption(keys[j].toString(), Integer.valueOf(values[j].toString()));
                } else if (Boolean.parseBoolean(values[j].toString())){
                    chOptions.setExperimentalOption(keys[j].toString(), Boolean.valueOf(values[j].toString()));
                } else {
                    chOptions.setExperimentalOption(keys[j].toString(), values[j].toString());
                }
            }
        }
    }

    /**
     * method to check if the String is an integer or not
     *
     * @param s string
     * @return boolean
     */
    public static boolean isStringInt(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException | NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
}
