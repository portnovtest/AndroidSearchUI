package com.company.searchui.utils;
// using the rock bands JSON data we introduced earlier, create a JSONObject w/ the required field values

import org.json.simple.JSONObject;

/**
 * Sample JSONObject Class
 *
 * @author phildolganov
 *
 */
public class RockBands {
    private String name, year, song;
    private JSONObject members;

    // the constructor requires the JSONObject when instantiated

    public RockBands(JSONObject object) {
        setName(object.get("name").toString());
        setYear(object.get("year").toString());
        setSong(object.get("song").toString());
        setMembers((JSONObject) object.get("members"));
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

    public void setMembers(JSONObject members) {
        this.members = members;
    }

    public JSONObject getMembers() {
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
