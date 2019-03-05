package com.zqf.food.business.shop;

import com.zqf.food.base.interfaced.IModel;
import com.zqf.food.base.interfaced.IView;
import com.zqf.food.business.shop.bean.ShopDataBean;

import java.util.List;
import java.util.Map;

/**
 * Created by zqf on 2018/11/24.
 */

public interface IShopContact {
    interface IV extends IView {
        void updateData(List<ShopDataBean> shopDataBeans);

        void updateSelectData(Map<String, Shopping> selectMap);


    }

    interface IM extends IModel {

    }
}
