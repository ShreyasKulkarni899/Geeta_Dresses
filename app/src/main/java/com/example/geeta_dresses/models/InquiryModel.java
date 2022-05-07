package com.example.geeta_dresses.models;

import com.example.geeta_dresses.productsModel;

import java.util.ArrayList;

public class InquiryModel {
    private String userId;
    private String userName;
    private String customerId;
    private String tokenNumber;
    private ArrayList<productsModel> productsModelArrayList ;
    private String reason;
    private String barcode;
    private String day;
    private Boolean isEnquired;
    private Boolean isPurchased;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getTokenNumber() {
        return tokenNumber;
    }

    public void setTokenNumber(String tokenNumber) {
        this.tokenNumber = tokenNumber;
    }

    public ArrayList<productsModel> getProductsModelArrayList() {
        return productsModelArrayList;
    }

    public void setProductsModelArrayList(ArrayList<productsModel> productsModelArrayList) {
        this.productsModelArrayList = productsModelArrayList;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Boolean getEnquired() {
        return isEnquired;
    }

    public void setEnquired(Boolean enquired) {
        isEnquired = enquired;
    }

    public Boolean getPurchased() {
        return isPurchased;
    }

    public void setPurchased(Boolean purchased) {
        isPurchased = purchased;
    }

    @Override
    public String toString() {
        return "{" +
                "userId:'" + userId + '\'' +
                ", username:'" + userName + '\'' +
                ", customerId:'" + customerId + '\'' +
                ", tokenNumber:'" + tokenNumber + '\'' +
                ", product:" + productsModelArrayList +
                ", reason:'" + reason + '\'' +
                ", barcode:'" + barcode + '\'' +
                ", day:'" + day + '\'' +
                ", isEnquired:" + isEnquired +
                ", isPurchased:" + isPurchased +
                '}';
    }
}
