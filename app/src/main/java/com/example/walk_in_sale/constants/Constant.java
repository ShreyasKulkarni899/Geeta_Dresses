package com.example.walk_in_sale.constants;

public class Constant {

    final String URL = "http://3.109.59.248:";
    final String PORT = "8080";
    final  String USER_LOGIN = "/user/login";
    final  String GET_TOKEN = "/counter/getCurrentValue?sequenceName=tokenNumber";
    final String UPDATE_TOKEN = "/token/";

    public String getUPDATE_TOKEN() {
        return UPDATE_TOKEN;
    }

    public String getURL() {
        return URL;
    }

    public String getPORT() {
        return PORT;
    }

    public String getUSER_LOGIN() {
        return USER_LOGIN;
    }

    public String getGET_TOKEN() {
        return GET_TOKEN;
    }
}
