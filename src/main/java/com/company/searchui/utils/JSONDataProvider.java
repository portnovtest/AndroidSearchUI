package com.company.searchui.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * TestNG DataProvider Class for extracting JSON data
 *
 * @author phildolganov
 *
 */
public class JSONDataProvider {
    //public static String dataFile = System.getProperty("user.dir") + "/src/test/resources/RockBands.json";
    public static String dataFile = "";
    public static String testCaseName = "NA";

    /**
     * fetchData - generic DataProvider method that extracts data by JSON key:value pairs
     *
     * @param method
     * @return Object[][]
     * @throws Exception
     */
    @DataProvider(name = "myData_JSON")
    public static Object[][] fetchData(Method method) throws Exception {
        Object rowID, description;
        Object[][] result;
        testCaseName = method.getName();
        JsonArray testData = (JsonArray) extractData_JSON(dataFile).get(method.getName());

        List<JsonObject> testDataList = new ArrayList<>();
        for (int i = 0; i < testData.size(); i++) {
            testDataList.add((JsonObject)testData.get(i));
        }
        // include tests matching this pattern only

        if (System.getProperty("includePattern") != null){
            String include = System.getProperty("includePattern");
            List<JsonObject> newList = new ArrayList<>();
            List<String> tests = Arrays.asList(include.split(",", -1));

            for (String getTest : tests) {
                for (int i = 0; i < testDataList.size(); i++) {
                    if (testDataList.get(i).toString().contains(getTest)){
                        newList.add(testDataList.get(i));
                    }
                }
            }
            // reassign testRows after filtering tests
            testDataList = newList;
        }
        // exclude tests matching this pattern only

        if (System.getProperty("excludePattern") != null){
            String exclude = System.getProperty("excludePattern");
            List<String> tests = Arrays.asList(exclude.split(",", -1));

            for (String getTest : tests) {
                // start at the end of list and work backwards so index stays in sync
                for (int i = testDataList.size()-1; i >= 0; i--) {
                    if (testDataList.get(i).toString().contains(getTest)){
                        testDataList.remove(testDataList.get(i));
                    }
                }
            }
        }

        // create object for dataprovider to return
        // added in rowID and description for later use

        try {
            result = new Object[testDataList.size()][testDataList.get(0).size()];
            for (int i = 0; i < testDataList.size(); i++) {
                rowID = testDataList.get(i).get("rowID");
                description = testDataList.get(i).get("description");
                result[i] = new Object[] {rowID, description,testDataList.get(i)};
            }
        } catch (IndexOutOfBoundsException ie) {
            result = new Object[0][0];
        }
        return result;
    }

    /**
     * extractData_JSON - method to extract JSON data from a file
     *
     * @param file (including path)
     * @return JsonObject
     * @throws Exception
     */
    public static JsonObject extractData_JSON(String file) throws Exception {
        FileReader reader = new FileReader(file);
        JsonParser jsonParser = new JsonParser();

        return (JsonObject) jsonParser.parse(reader);
    }

    /**
     * fetchData - method to get only the data that matches the filter
     *
     * @param file
     * @param filter
     * @return
     * @throws Exception
     */
    public static JsonArray fetchData(String file, String filter) throws Exception {
        JsonArray testData = (JsonArray) extractData_JSON(file).get(filter);
        return testData;
    }

}
