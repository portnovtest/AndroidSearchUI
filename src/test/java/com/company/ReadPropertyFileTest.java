package com.company;

import com.google.gson.JsonObject;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.testng.Assert.assertEquals;

public class ReadPropertyFileTest {

    @Test
    public void tc001_readPropertyFile(String rowID, String description, JsonObject testData)
            throws IOException, AssertionError {
        Properties seleniumProps = new Properties();
        String propFile = testData.get("propFile").toString();
        String expRevision = testData.get("revision").toString();

        seleniumProps.load(new FileInputStream(propFile));
        assertEquals(seleniumProps.getProperty("selenium.revision"), expRevision, "Verify Selenium Revision");
    }
}
