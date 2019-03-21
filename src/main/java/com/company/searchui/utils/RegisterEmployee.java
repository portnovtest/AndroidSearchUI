package com.company.searchui.utils;


import org.json.simple.JSONObject;

/**
 * Sample Register Employee Java Object
 *
 * @author phildolganov
 *
 */
public class RegisterEmployee {
    private String id;
    private JSONObject address;
    private JSONObject phone;

    public RegisterEmployee(JSONObject object) throws Exception {
        setId(object.get("id").toString());
        setAddress((JSONObject)object.get("address"));
        setPhone((JSONObject)object.get("phone"));
    }

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }

    public void setAddress(JSONObject address){
        this.address = address;
    }

    public JSONObject getAddress(){
        return address;
    }

    public void setPhone(JSONObject phone){
        this.phone = phone;
    }

    public JSONObject getPhone(){
        return phone;
    }
}
