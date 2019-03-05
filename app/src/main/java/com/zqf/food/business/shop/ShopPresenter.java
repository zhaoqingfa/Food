package com.zqf.food.business.shop;

import android.text.TextUtils;

import com.zqf.food.base.BasePresenter;
import com.zqf.food.business.shop.bean.ShopDataBean;
import com.zqf.food.business.shop.bean.ShopItemBean;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by v_zhaoqingfa on 2019/2/21.
 */

public class ShopPresenter extends BasePresenter<IShopContact.IV, IShopContact.IM> {
    private Map<String, Shop> selectMap;

    public ShopPresenter(IShopContact.IV view) {
        super(view);
        selectMap = new LinkedHashMap();
    }

    @Override
    public IShopContact.IM getModel() {
        return new ShopModel();
    }

    public void getData() {
        List<ShopDataBean> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ShopDataBean bean = new ShopDataBean();
            bean.setShopTypeName("这个是测试" + i);
            bean.setShopTypeDescribe("这个东西非常好吃" + i);
            List<ShopItemBean> itemBeans = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                ShopItemBean itemBean = new ShopItemBean();
                itemBean.setShopName("菜品" + j);
                itemBeans.add(itemBean);
            }
            bean.setShopItemBeans(itemBeans);
            list.add(bean);
        }
        mView.updateData(list);
    }

    /**
     * 重新组合数据
     * @param shopDataBeans
     * @return
     */
    public List<ShopItemBean> recombineData(List<ShopDataBean> shopDataBeans) {
        List<ShopItemBean> shopItemBeans = new ArrayList<>();
        if (shopDataBeans != null) {
            for (int i = 0; i < shopDataBeans.size(); i++) {
                ShopDataBean dataBean = shopDataBeans.get(i);
                if (dataBean != null) {
                    ShopItemBean bean = new ShopItemBean();
                    bean.setShopId(-1);
                    bean.setShopTypeId(dataBean.getShopTypeId());
                    bean.setShopTypeName(dataBean.getShopTypeName());
                    bean.setShopDescribe(dataBean.getShopTypeDescribe());
                    shopItemBeans.add(bean);
                    shopItemBeans.addAll(dataBean.getShopItemBeans());
                }
            }
        }
        return shopItemBeans;
    }

    /**
     * 设置选中的类型
     * @param shopDataBeans
     * @param position
     */
    public void setSelectType(List<ShopDataBean> shopDataBeans, int position) {
        if (shopDataBeans != null) {
            for (int i = 0; i < shopDataBeans.size(); i++) {
                ShopDataBean bean = shopDataBeans.get(i);
                if (bean != null) {
                    if (i == position) {
                        bean.setSelect(true);
                    } else {
                        bean.setSelect(false);
                    }
                }
            }
        }
    }

    public void clearSelectMap() {
        if (selectMap != null) {
            selectMap.clear();
        }
    }

    public Shop removeSelectMap(String key) {
        if (selectMap != null && selectMap.containsKey(key)) {
            return selectMap.remove(key);
        }
        return null;
    }

    public void addSelectMap(ShopItemBean itemBean) {
        if (itemBean != null) {

        }
    }

    public void downSelectMap(ShopItemBean itemBean) {

    }

    public boolean isEmptyMap() {
        if (selectMap != null && selectMap.size() > 0) {
            return false;
        }
        return true;
    }


}
