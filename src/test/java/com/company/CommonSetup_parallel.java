package com.company;

import com.company.searchui.utils.CreateDriver;
import com.company.searchui.utils.Global_VARS;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public abstract class CommonSetup_parallel {

    private static Properties configProps = new Properties();  // ??? Ad hocked this line
  protected ParallelProps configProps_parallel = new ParallelProps(configProps);

  @Parameters({"browser","platform","environment","propertyFile"})
    @BeforeTest(alwaysRun = true, enabled = true)
    protected void testSetup(String browser, String platform, String environment,
                             String propertyFile, ITestContext context)throws Exception {
      configProps_parallel.load(new FileInputStream(Global_VARS.PROPS_PATH +
              System.getProperty("propertyFile",propertyFile)));
      Global_VARS.DEF_BROWSER = System.getProperty("browser",browser);
      Global_VARS.DEF_PLATFORM = System.getProperty("platform",platform);
      Global_VARS.DEF_ENVIRONMENT = System.getProperty("environment",environment);
      Map<String,Object> setBrowserPrefs = new HashMap<>();
      if (Global_VARS.DEF_PLATFORM.equals("android")){
          CreateDriver.getInstance().setDriver(Global_VARS.DEF_BROWSER,
                  Global_VARS.DEF_ENVIRONMENT,Global_VARS.DEF_PLATFORM,setBrowserPrefs);
      } else {
          CreateDriver.getInstance().setDriver(Global_VARS.DEF_BROWSER,
                  Global_VARS.DEF_ENVIRONMENT,Global_VARS.DEF_PLATFORM);
      }
  }
}
