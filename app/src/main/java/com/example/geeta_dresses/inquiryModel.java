package com.example.geeta_dresses;

public class inquiryModel {
    private String inquiry_no;
    private String inquiry_user;
    private String inquiry_day;
    private String[] product_list;
    private Boolean isInquired;
    private Boolean isPurchased;
    private Boolean isManger;


    //constructor
    public inquiryModel(String inquiry_no,String inquiry_user, String inquiry_day, String[] product_list, Boolean isInquired,Boolean isPurchased ,Boolean isManger){
        this.inquiry_no = inquiry_no;
        this.inquiry_user = inquiry_user;
        this.inquiry_day = inquiry_day;
        this.product_list = product_list;
        this.isInquired = isInquired;
        this.isPurchased = isPurchased;
        this.isManger = isManger;
    }

    public String getInquiry_no() { return inquiry_no;    }

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

    public String[] getProduct_list() { return product_list;  }

    public void setProduct_list(String[] product_list) { this.product_list = product_list;  }

    public Boolean getInquired() {    return isInquired;    }

    public void setInquired(Boolean inquired) {     isInquired = inquired;    }

    public Boolean getPurchased() {    return isPurchased;   }

    public void setPurchased(Boolean purchased) {      isPurchased = purchased;    }

    public Boolean getManger() {        return isManger;    }

    public void setManger(Boolean manger) {        isManger = manger;    }
}
