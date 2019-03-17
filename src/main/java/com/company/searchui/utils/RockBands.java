package com.company.searchui.utils;
// using the rock bands JSON data we introduced earlier, create a JSONObject w/ the required field values

import com.google.gson.JsonObject;

/**
 * Sample JSONObject Class
 *
 * @author phildolganov
 *
 */
public class RockBands {
    private String name, year, song;
    private JsonObject members;

    // the constructor requires the JSONObject when instantiated

    public RockBands(JsonObject object) {
        setName(object.get("name").toString());
        setYear(object.get("year").toString());
        setSong(object.get("song").toString());
        setMembers((JsonObject) object.get("members"));
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getYear() {
        return this.year;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getSong() {
        return this.song;
    }

    public void setMembers(JsonObject members) {
        this.members = members;
    }

    public JsonObject getMembers() {
        return this.members;
    }

    @Override
    public String toString() {
        return "Rockbands {" +
                "name = '" + name + "'" +
                ", year = '" + year + "'" +
                ", song = '" + song + "'" +
                ", members = " + members + "}";
    }
}
