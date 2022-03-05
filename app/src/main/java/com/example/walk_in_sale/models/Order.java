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

    public long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(long orderNo) {
        this.orderNo = orderNo;
    }

    public byte getFlag() {
        return flag;
    }

    public void setFlag(byte flag) {
        this.flag = flag;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getOrderedByName() {
        return orderedByName;
    }

    public void setOrderedByName(String orderedByName) {
        this.orderedByName = orderedByName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    public void setEstimatedDeliveryDate(Date estimatedDeliveryDate) {
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }

    public String getTransportId() {
        return transportId;
    }

    public void setTransportId(String transportId) {
        this.transportId = transportId;
    }

    public String getTransportName() {
        return transportName;
    }

    public void setTransportName(String transportName) {
        this.transportName = transportName;
    }

    public String getAttachmentUrl() {
        return attachmentUrl;
    }

    public void setAttachmentUrl(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl;
    }

    public long getTotalPrimaryQty() {
        return totalPrimaryQty;
    }

    public void setTotalPrimaryQty(long totalPrimaryQty) {
        this.totalPrimaryQty = totalPrimaryQty;
    }

    public long getTotalSecondaryQty() {
        return totalSecondaryQty;
    }

    public void setTotalSecondaryQty(long totalSecondaryQty) {
        this.totalSecondaryQty = totalSecondaryQty;
    }

    public long getTotalTertiaryQty() {
        return totalTertiaryQty;
    }

    public void setTotalTertiaryQty(long totalTertiaryQty) {
        this.totalTertiaryQty = totalTertiaryQty;
    }

    public long getTotalGrossPurchase() {
        return totalGrossPurchase;
    }

    public void setTotalGrossPurchase(long totalGrossPurchase) {
        this.totalGrossPurchase = totalGrossPurchase;
    }

    public long getTotalPurchaseDiscount() {
        return totalPurchaseDiscount;
    }

    public void setTotalPurchaseDiscount(long totalPurchaseDiscount) {
        this.totalPurchaseDiscount = totalPurchaseDiscount;
    }

    public long getTotalNetPurchase() {
        return totalNetPurchase;
    }

    public void setTotalNetPurchase(long totalNetPurchase) {
        this.totalNetPurchase = totalNetPurchase;
    }

    public long getOtherCharges() {
        return otherCharges;
    }

    public void setOtherCharges(long otherCharges) {
        this.otherCharges = otherCharges;
    }

    public String getPurchaseGST() {
        return purchaseGST;
    }

    public void setPurchaseGST(String purchaseGST) {
        this.purchaseGST = purchaseGST;
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public boolean isOrderDeleteStatus() {
        return orderDeleteStatus;
    }

    public void setOrderDeleteStatus(boolean orderDeleteStatus) {
        this.orderDeleteStatus = orderDeleteStatus;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}
