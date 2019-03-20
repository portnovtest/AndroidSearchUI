package com.company;

import com.company.searchui.utils.JSONDataProvider;
import com.company.searchui.utils.RockBands;
import com.company.searchui.utils.RockBandsBuilder;
import com.google.gson.JsonObject;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

/**
 * Rock Bands Test1 Class(JavaDoc left out)
 *
 * @author phildolganov
 *
 */
public class RockBandsTest1 {
    // local vars
    public static final String DATA_FILE = System.getProperty("user.dir") + "/src/test/resources/RockBands.json";

   // setup/teardown methods go here
   @BeforeClass(alwaysRun = true,enabled = true)
    protected void testClassSetup() throws Exception {
       // set data file...
       JSONDataProvider.dataFile = DATA_FILE;
   }

   @AfterClass(alwaysRun = true,enabled = true)
    protected void testClassTeardown() throws Exception {

   }

   @BeforeMethod(alwaysRun = true,enabled = true)
    protected void testMethodSetup(ITestResult rslt) throws Exception {

   }

   @AfterMethod(alwaysRun = true,enabled = true)
    protected void testMethodTeardown(ITestResult rslt) throws Exception {

   }

   /*
   // test methods
    @Test(groups = {"POSITIVE", "NEGATIVE", "BOUNDARY", "LIMIT", "SMOKETEST", "REGRESSION"},
            dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class, enabled = true,
            alwaysRun = true, priority = 1)
    public void tc001_getBandInfo(String rowID, String description, JsonObject testData) throws Exception {
       
    }
    */

   // test method using Java POJO class object
    @Test(groups = {"REGRESSION"},dataProvider = "myData_JSON",dataProviderClass = JSONDataProvider.class,enabled = true)
   public void tc002_getBandInfo(String rowID,String description,JsonObject testData)throws Exception {
        // fetch object data and pass into Java object
        RockBands rockBands = new RockBands(testData);

        // print the key:value pairs
        System.out.println(rockBands.toString() + "\n");
   }

   // test method using Java Builder class object
    @Test(groups = {"REGRESSION"},dataProvider = "myData_JSON",dataProviderClass = JSONDataProvider.class,enabled = true)
    public void tc001_getBandInfo(String rowID,String description,JsonObject testData) throws Exception {

        // fetch object data and pass into Java object
        RockBandsBuilder rockBands = new RockBandsBuilder.Builder()
                .name(testData.get("name").toString())
                .year(testData.get("year").toString())
                .song(testData.get("song").toString())
                .members((JsonObject) testData.get("members"))
                .build();
        // print the key:value pairs
        System.out.println(rockBands.toString() + "\n");
    }
}
