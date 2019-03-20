package com.company.searchui.utils;

import com.google.gson.JsonObject;

/**
 * Sample Register Employee Java Object
 *
 * @author phildolganov
 *
 */
public class RegisterEmployee {
    private String id;
    private JsonObject address;
    private JsonObject phone;

    public RegisterEmployee(JsonObject object) throws Exception {
        setId(object.get("id").toString());
        setAddress((JsonObject)object.get("address"));
        setPhone((JsonObject)object.get("phone"));
    }

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }

    public void setAddress(JsonObject address){
        this.address = address;
    }

    public JsonObject getAddress(){
        return address;
    }

    public void setPhone(JsonObject phone){
        this.phone = phone;
    }

    public JsonObject getPhone(){
        return phone;
    }
}
