package com.example.geeta_dresses;

import java.io.Serializable;

public class productsModel implements Serializable {

    private String product_name;
    private String product_qty;
    private String product_prize;
    private String product_id;



    // Constructor
    public productsModel(String product_name, String product_qty,String product_prize,String product_id) {
        this.product_name = product_name;
        this.product_qty = product_qty;
        this.product_prize = product_prize;
        this.product_id =product_id;

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

    public  String getProduct_id(){ return  product_id;}

    public void setProduct_id(String product_prize){this.product_id = product_id;}

    @Override
    public String toString() {
        return "{" +
                "productName:'" + product_name + '\'' +
                ", quantity:'" + product_qty + '\'' +
                ", price:'" + product_prize + '\'' +
                ", productId:'" + product_id + '\'' +
                '}';
    }
}
