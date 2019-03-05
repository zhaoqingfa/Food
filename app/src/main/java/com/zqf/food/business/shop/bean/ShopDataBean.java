package com.zqf.food.business.shop.bean;

import java.util.List;

/**
 * Created by v_zhaoqingfa on 2019/3/2.
 */

public class ShopDataBean {
    private int shopTypeId;
    private String shopTypeName;
    private String shopTypeIcon;
    private String shopTypeDescribe;
    private List<ShopItemBean> shopItemBeans;

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

    public String getShopTypeIcon() {
        return shopTypeIcon;
    }

    public void setShopTypeIcon(String shopTypeIcon) {
        this.shopTypeIcon = shopTypeIcon;
    }

    public String getShopTypeDescribe() {
        return shopTypeDescribe;
    }

    public void setShopTypeDescribe(String shopTypeDescribe) {
        this.shopTypeDescribe = shopTypeDescribe;
    }

    public List<ShopItemBean> getShopItemBeans() {
        return shopItemBeans;
    }

    public void setShopItemBeans(List<ShopItemBean> shopItemBeans) {
        this.shopItemBeans = shopItemBeans;
    }


    private boolean isSelect = false;
    private int selectNum = 0;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public int getSelectNum() {
        return selectNum;
    }

    public void setSelectNum(int selectNum) {
        this.selectNum = selectNum;
    }
}
