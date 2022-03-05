package com.example.walk_in_sale.models;

import java.util.ArrayList;
import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

@RealmClass
public class Order extends RealmObject {
    @PrimaryKey
    private long orderNo;

    private byte flag = 0;
    // Store
    private String storeName;
    private String storeId;

    // Employee
    private String orderedByName;
    private String employeeId;

    // Supplier
    private String supplierName;
    private String supplierId;

    private Date orderDate;
    private String orderStatus;
    private  Date estimatedDeliveryDate;

    // Transport
    private String transportId;

    private String transportName;
    private String attachmentUrl;

    // Product List
    // private ArrayList<Product> products;

    private  long totalPrimaryQty;
    private long totalSecondaryQty;
    private long totalTertiaryQty;
    private long totalGrossPurchase;
    private long totalPurchaseDiscount;
    private long totalNetPurchase;
    private long otherCharges;

    // Purchase GST
    private String purchaseGST;

    private long totalAmount;
    private boolean orderDeleteStatus;

    private Date timeStamp;





}
