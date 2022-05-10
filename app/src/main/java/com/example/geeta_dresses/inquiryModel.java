package com.example.geeta_dresses;

public class inquiryModel {
    private String inquiry_no;
    private String inquiry_user;
    private String inquiry_day;

    //constructor
    public inquiryModel(String inquiry_no,String inquiry_user, String inquiry_day){
        this.inquiry_no = inquiry_no;
        this.inquiry_user = inquiry_user;
        this.inquiry_day = inquiry_day;
    }

    public String getInquiry_no() {
        return inquiry_no;
    }

    public void setInquiry_no(String inquiry_no) {
        this.inquiry_no = inquiry_no;
    }

    public String getInquiry_user() {
        return inquiry_user;
    }

    public void setInquiry_user(String inquiry_user) {
        this.inquiry_user = inquiry_user;
    }

    public String getInquiry_day() {
        return inquiry_day;
    }

    public void setInquiry_day(String inquiry_day) {
        this.inquiry_day = inquiry_day;
    }
}
