package com.ggtf.supermarket.models;

/**
 * Created by ggtf at 2015/9/29
 * Author:ggtf
 * Time:2015/9/29
 * Email:15170069952@163.com
 * ProjectName:JPushDemo
 */

/**
 * 购物车条目对象
 */
public class CarItem {
//    商品ID
    private long productId;
//    商品名称
    private String productName;
//    商品单价
    private float price;
//    商品数量
    private int count;
//    是否被勾选
    private boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
