package com.zqf.food.business.shop;

import android.text.TextUtils;

import com.zqf.food.base.BasePresenter;
import com.zqf.food.business.shop.bean.ShopDataBean;
import com.zqf.food.business.shop.bean.ShopItemBean;
import com.zqf.food.business.shop.bean.ShopModelBean;
import com.zqf.food.util.ToolUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by v_zhaoqingfa on 2019/2/21.
 */

public class ShopPresenter extends BasePresenter<IShopContact.IV, IShopContact.IM> {
    private Map<String, Shopping> selectMap;
    private List<ShopDataBean> mList;

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
                itemBean.setShopDescribe("这个是哪个啥菜" +j);
                itemBean.setShopDiscount("7.5折");
                itemBean.setShopMaxNum(10);
                itemBean.setShopMinNum(0);
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

    public Shopping removeSelectMap(String key) {
        if (selectMap != null && selectMap.containsKey(key)) {
            return selectMap.remove(key);
        }
        return null;
    }

    public void addSelectMap(ShopItemBean itemBean, int indexModel, int indexFlavor) {
        if (itemBean == null) {
            return;
        }
        String key = "";
        ShopModelBean shopModelBean = null;
        List<ShopModelBean> shopModels = itemBean.getShopModels();
        if (indexModel >= 0 && shopModels != null && shopModels
                .size() > indexModel && shopModels.get(indexModel) != null) {
            shopModelBean = shopModels.get(indexModel);
            key = ToolUtil.getSelectKey(itemBean.getShopId(), shopModelBean.getShopModelId());
        } else {
            key = ToolUtil.getSelectKey(itemBean.getShopId(), -1);
        }
        if (TextUtils.isEmpty(key)) {
            return;
        }
        // 判断这个key是否加入购物车
        if (selectMap.containsKey(key)) {
            // 只需要将数量加一
            Shopping shopping = selectMap.get(key);
            int shopNum = shopping.getShopNum();
            if (shopNum >= itemBean.getShopMaxNum()) {
                return;
            }
            shopping.setShopNum(shopNum + 1);
        } else {
            // 第一次加入购物车
            Shopping shopping = new Shopping();
            shopping.setShopId(itemBean.getShopId());
            shopping.setShopName(itemBean.getShopName());
            shopping.setShopTypeId(itemBean.getShopTypeId());
            shopping.setShopTypeName(itemBean.getShopTypeName());
            shopping.setShopDiscount(itemBean.getShopDiscount());
            shopping.setShopMaxNum(itemBean.getShopMaxNum());
            int minNum = itemBean.getShopMinNum();
            shopping.setShopMinNum(minNum);
            if (minNum >= 1) {
                shopping.setShopNum(minNum);
                itemBean.setShopNum(minNum);
            } else {
                shopping.setShopNum(1);
                itemBean.setShopNum(1);
            }
            if (shopModelBean != null) {
                shopping.setShopModelId(shopModelBean.getShopModelId());
                shopping.setShopModelName(shopModelBean.getShopModelName());
                shopping.setShopRealMoney(shopModelBean.getShopRealMoney());
                shopping.setShopOriginalMoney(shopModelBean.getShopOriginalMoney());
            } else {
                shopping.setShopRealMoney(itemBean.getShopRealMoney());
                shopping.setShopOriginalMoney(itemBean.getShopOriginalMoney());
            }
            List<String> shopFlavor = itemBean.getShopFlavor();
            if (indexFlavor >= 0 && shopFlavor != null && shopFlavor.size() > indexFlavor) {
                shopping.setShopFlavor(shopFlavor.get(indexFlavor));
            }
            selectMap.put(key, shopping);
        }
        updateSelectData();
    }

    public void downSelectMap(ShopItemBean itemBean) {
        if (itemBean == null) {
            return;
        }
//        String key = "";
//        ShopModelBean shopModelBean = null;
//        List<ShopModelBean> shopModels = itemBean.getShopModels();
//        if (indexModel >= 0 && shopModels != null && shopModels
//                .size() > indexModel && shopModels.get(indexModel) != null) {
//            shopModelBean = shopModels.get(indexModel);
//            key = ToolUtil.getSelectKey(itemBean.getShopId(), shopModelBean.getShopModelId());
//        } else {
//            key = ToolUtil.getSelectKey(itemBean.getShopId(), -1);
//        }
//        if (TextUtils.isEmpty(key)) {
//            return;
//        }

    }

    private void updateSelectData() {
        mView.updateSelectData(selectMap);
    }

    public boolean isNeedSelect(ShopItemBean itemBean) {
        if (itemBean != null) {
            List<ShopModelBean> shopModels = itemBean.getShopModels();
            if (shopModels != null && shopModels.size() > 0) {
                return true;
            }

            List<String> shopFlavor = itemBean.getShopFlavor();
            if (shopFlavor != null && shopFlavor.size() > 0) {
                if (!selectMap.containsKey(ToolUtil
                        .getSelectKey(itemBean.getShopId(), -1))) {
                    return true;
                }
            }
        }
        return false;
    }


    public boolean isEmptyMap() {
        if (selectMap != null && selectMap.size() > 0) {
            return false;
        }
        return true;
    }


}
