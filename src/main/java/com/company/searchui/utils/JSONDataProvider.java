package com.company.searchui.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * TestNG DataProvider Class for extracting JSON data
 *
 * @author phildolganov
 *
 */
public class JSONDataProvider {
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
        // include Filter Placeholder

        // exclude Filter Placeholder

        // create object for dataprovider to return
        // add in rowID and description for later use

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
