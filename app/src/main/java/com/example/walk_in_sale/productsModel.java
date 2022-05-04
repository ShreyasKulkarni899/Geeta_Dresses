package com.example.walk_in_sale;

public class productsModel {

    private String product_name;
    private String product_qty;
    private String product_prize;



    // Constructor
    public productsModel(String product_name, String product_qty,String product_prize) {
        this.product_name = product_name;
        this.product_qty = product_qty;
        this.product_prize = product_prize;

    }

    // Getter and Setter
    public String getproduct_name() {
        return product_name;
    }

    public void setproduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getproduct_qty() {
        return product_qty;
    }

    public void setproduct_qty(String product_qty) {
        this.product_qty = product_qty;
    }

    public  String getProduct_prize(){ return  product_prize;}

    public void setProduct_prize(String product_prize){this.product_prize = product_prize;}
}
