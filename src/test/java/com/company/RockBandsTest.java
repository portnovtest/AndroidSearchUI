package com.company;

import com.company.searchui.utils.JSONDataProvider;
import com.company.searchui.utils.RockBands;
import com.company.searchui.utils.RockBandsBuilder;
import com.google.gson.JsonObject;
import org.testng.annotations.Test;

public class RockBandsTest {

    @Test(dataProvider = "myData_JSON", dataProviderClass = JSONDataProvider.class)
    public void tc001_getBandInfo(JsonObject testData) throws Exception {
        // fetch object data and pass into Java object...
        RockBands rockBands = new RockBands(testData);

        // vertical format output:
        System.out.println("\nName = " + rockBands.getName() +
                "\nYear = " + rockBands.getYear() +
                "\nSong = " + rockBands.getSong() +
                "\nVocals = " + rockBands.getMembers().get("Vocals") +
                "\nBass = " + rockBands.getMembers().get("Bass") +
                "\nGuitar = " + rockBands.getMembers().get("Guitar") +
                "\nDrums = " + rockBands.getMembers().get("Drums"));

        // print out the JSONObject data extracted from file
        // Horizontal format
        System.out.println(rockBands.toString());
    }

    @Test(dataProvider = "myData_JSON", dataProviderClass = JSONDataProvider.class)
    public void tc002_getBandInfo(JsonObject testData) throws Exception {
        // fetch object data and pass into Java object...
        RockBandsBuilder rockBands = new RockBandsBuilder.Builder()
                .name(testData.get("name").toString())
                .year(testData.get("year").toString())
                .song(testData.get("song").toString())
                .members((JsonObject) testData.get("members"))
                .build();
        // print out the JSONObject data extracted from file
        System.out.println(rockBands.toString());
    }

}
