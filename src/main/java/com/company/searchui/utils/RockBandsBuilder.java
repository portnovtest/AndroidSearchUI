package com.company.searchui.utils;


import org.json.simple.JSONObject;

/**
 * Sample JSONObject Class
 *
 * @author phildolganov
 *
 */
public class RockBandsBuilder {
    public String name, year, song;
    public JSONObject members;

    /**
     * Builder interface
     */
    public static class Builder {
        private String name, year, song;
        private JSONObject members;

        public Builder() {
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder year(String year) {
            this.year = year;
            return this;
        }

        public Builder song(String song) {
            this.song = song;
            return this;
        }

        public Builder members(JSONObject members) {
            this.members = members;
            return this;
        }

        public RockBandsBuilder build(){
            RockBandsBuilder rockBands = new RockBandsBuilder(this);
            return rockBands;
        }
    }

    public RockBandsBuilder(Builder builder) {
        this.name = builder.name;
        this.year = builder.year;
        this.song = builder.song;
        this.members = builder.members;
    }

   public RockBandsBuilder(RockBandsBuilder rockBands){
        this.name = rockBands.name;
        this.year = rockBands.year;
        this.song = rockBands.song;
        this.members = rockBands.members;
   }

    @Override
    public String toString() {
        return "RockBandsBuilder {" +
                "name = '" + name + "'" +
                ", year = '" + year + "'" +
                ", song = '" + song + "'" +
                ", members = " + members + "}";
    }
}
