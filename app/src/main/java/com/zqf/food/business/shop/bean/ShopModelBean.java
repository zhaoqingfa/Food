package com.zqf.food.business.shop.bean;

/**
 * Created by v_zhaoqingfa on 2019/3/2.
 */

public class ShopModelBean {
    private int shopModelId;
    private String shopModelName;
    private float shopOriginalMoney;
    private float shopRealMoney;

    public int getShopModelId() {
        return shopModelId;
    }

    public void setShopModelId(int shopModelId) {
        this.shopModelId = shopModelId;
    }

    public String getShopModelName() {
        return shopModelName;
    }

    public void setShopModelName(String shopModelName) {
        this.shopModelName = shopModelName;
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
}
