package com.zqf.food.business.shop.bean;

import java.util.List;

/**
 * Created by v_zhaoqingfa on 2019/3/2.
 */

public class ShopItemBean {
    private int shopId;
    private String shopName;
    private int shopTypeId;
    private String shopTypeName;
    private int shopMaxNum;
    private int shopMinNum;
    private int shopGrade;
    private float shopOriginalMoney;
    private float shopRealMoney;
    private String shopSellNum;
    private String shopDiscount;
    private String shopDescribe;
    private String shopIcon;
    private List<String> shopPics;
    private List<String> shopFlavor;
    private List<ShopModelBean> shopModels;

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public int getShopTypeId() {
        return shopTypeId;
    }

    public void setShopTypeId(int shopTypeId) {
        this.shopTypeId = shopTypeId;
    }

    public String getShopTypeName() {
        return shopTypeName;
    }

    public void setShopTypeName(String shopTypeName) {
        this.shopTypeName = shopTypeName;
    }

    public int getShopMaxNum() {
        return shopMaxNum;
    }

    public void setShopMaxNum(int shopMaxNum) {
        this.shopMaxNum = shopMaxNum;
    }

    public int getShopMinNum() {
        return shopMinNum;
    }

    public void setShopMinNum(int shopMinNum) {
        this.shopMinNum = shopMinNum;
    }

    public int getShopGrade() {
        return shopGrade;
    }

    public void setShopGrade(int shopGrade) {
        this.shopGrade = shopGrade;
    }

    public float getShopOriginalMoney() {
        return shopOriginalMoney;
    }

    public void setShopOriginalMoney(float shopOriginalMoney) {
        this.shopOriginalMoney = shopOriginalMoney;
    }

    public float getShopRealMoney() {
        return shopRealMoney;
    }

    public void setShopRealMoney(float shopRealMoney) {
        this.shopRealMoney = shopRealMoney;
    }

    public String getShopSellNum() {
        return shopSellNum;
    }

    public void setShopSellNum(String shopSellNum) {
        this.shopSellNum = shopSellNum;
    }

    public String getShopDiscount() {
        return shopDiscount;
    }

    public void setShopDiscount(String shopDiscount) {
        this.shopDiscount = shopDiscount;
    }

    public String getShopDescribe() {
        return shopDescribe;
    }

    public void setShopDescribe(String shopDescribe) {
        this.shopDescribe = shopDescribe;
    }

    public String getShopIcon() {
        return shopIcon;
    }

    public void setShopIcon(String shopIcon) {
        this.shopIcon = shopIcon;
    }

    public List<String> getShopPics() {
        return shopPics;
    }

    public void setShopPics(List<String> shopPics) {
        this.shopPics = shopPics;
    }

    public List<String> getShopFlavor() {
        return shopFlavor;
    }

    public void setShopFlavor(List<String> shopFlavor) {
        this.shopFlavor = shopFlavor;
    }

    public List<ShopModelBean> getShopModels() {
        return shopModels;
    }

    public void setShopModels(List<ShopModelBean> shopModels) {
        this.shopModels = shopModels;
    }

    private int shopNum = 0;

    public int getShopNum() {
        return shopNum;
    }

    public void setShopNum(int shopNum) {
        this.shopNum = shopNum;
    }

}
