package com.example.walk_in_sale.constants;

public class Constant {

    final String URL = "http://3.109.59.248:";
    final String PORT = "8080";
    final  String USER_LOGIN = "/user/login";
    final  String GET_TOKEN = "/counter/getCurrentValue?sequenceName=tokenNumber";
    final String UPDATE_TOKEN = "/token/";
    final String GET_TOKEN_DETAILS = "/token?tokenNumber=";

    public String getGET_TOKEN_DETAILS() {
        return GET_TOKEN_DETAILS;
    }

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
