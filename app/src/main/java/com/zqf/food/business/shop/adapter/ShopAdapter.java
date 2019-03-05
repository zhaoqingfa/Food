package com.zqf.food.business.shop.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.zqf.food.R;
import com.zqf.food.business.shop.bean.ShopDataBean;
import com.zqf.food.business.shop.bean.ShopItemBean;
import com.zqf.food.common.widgets.ShopSelectView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by v_zhaoqingfa on 2019/3/2.
 */

public class ShopAdapter extends RecyclerView.Adapter {
    private static final int SHOP_TYPE = 1;
    private static final int SHOP_LIST = 2;

    private Context mContext;
    private List<ShopItemBean> mList;
    private ShopCallBack mCallBack;

    public ShopAdapter(Context context) {
        mContext = context;
        mList = new ArrayList<>();
    }

    public void updateData(List<ShopItemBean> shopItemBeans) {
        if (shopItemBeans != null) {
            mList = shopItemBeans;
            notifyDataSetChanged();
        }
    }

    public List<ShopItemBean> getData() {
        return mList;
    }

    public void setCallBack(ShopCallBack callBack) {
        mCallBack = callBack;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == SHOP_TYPE) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_shop1, viewGroup, false);
            return new FirstViewHolder(view);
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_shop2, viewGroup, false);
            return new SecondViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        if (viewHolder instanceof FirstViewHolder) {
            FirstViewHolder firstHolder = (FirstViewHolder) viewHolder;
            if (firstHolder != null) {
                ShopItemBean itemBean = mList.get(i);
                if (itemBean != null && -1 == itemBean.getShopId()) {
                    firstHolder.typeName.setText(TextUtils.isEmpty(itemBean.getShopTypeName()) ? mContext
                            .getResources().getString(R.string.no_shop_type_name) : itemBean.getShopTypeName());
                    if (TextUtils.isEmpty(itemBean.getShopDescribe())) {
                        firstHolder.typeDescribe.setVisibility(View.GONE);
                    } else {
                        firstHolder.typeDescribe.setVisibility(View.VISIBLE);
                        firstHolder.typeDescribe.setText(itemBean.getShopDescribe());
                    }
                }
            }
        } else {
            SecondViewHolder secondHolder = (SecondViewHolder) viewHolder;
            if (secondHolder != null) {
                final ShopItemBean bean = mList.get(i);
                if (bean != null && -1 != bean.getShopId()) {
                    secondHolder.shopName.setText(TextUtils.isEmpty(bean.getShopName()) ? mContext
                            .getResources().getString(R.string.no_shop_name) : bean.getShopName());
                    RequestOptions options = new RequestOptions()
                            .placeholder(R.mipmap.ic_launcher);
                    Glide.with(mContext)
                            .load(bean.getShopIcon()) //加载地址
                            .apply(options)//加载未完成时显示占位图
                            .into(secondHolder.shopIcon);//显示的位置

                    if (TextUtils.isEmpty(bean.getShopDescribe())) {
                        secondHolder.shopDescribe.setVisibility(View.GONE);
                    } else {
                        secondHolder.shopDescribe.setVisibility(View.VISIBLE);
                        secondHolder.shopDescribe.setText(bean.getShopDescribe());
                    }
                    if (TextUtils.isEmpty(bean.getShopSellNum())) {
                        secondHolder.shopSell.setVisibility(View.GONE);
                    } else {
                        secondHolder.shopSell.setVisibility(View.VISIBLE);
                        secondHolder.shopSell.setText(bean.getShopSellNum());
                    }
                    if (TextUtils.isEmpty(bean.getShopDiscount())) {
                        secondHolder.shopDiscount.setVisibility(View.GONE);
                    } else {
                        secondHolder.shopDiscount.setVisibility(View.VISIBLE);
                        secondHolder.shopDiscount.setText(bean.getShopDiscount());
                    }

                    secondHolder.shopRealMoney.setText("" + bean.getShopRealMoney());
                    float original = bean.getShopOriginalMoney();
                    if (original <= 0 || original <= bean.getShopRealMoney()) {
                        secondHolder.shopOriginalMoney.setVisibility(View.GONE);
                    } else {
                        secondHolder.shopOriginalMoney.setVisibility(View.VISIBLE);
                        secondHolder.shopOriginalMoney.setText("" + original);
                        // 删除线
                        secondHolder.shopOriginalMoney.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                    }

                    secondHolder.shopSelect.updateData(bean.getShopNum(),
                            bean.getShopMaxNum(), bean.getShopMinNum());
                    secondHolder.shopSelect.setCallBack(new ShopSelectView.ShopSelectCallBack() {
                        @Override
                        public void clickAdd() {
                            if (mCallBack != null) {
                                mCallBack.onClickAdd(bean, i);
                            }
                        }

                        @Override
                        public void clickDown() {
                            if (mCallBack != null) {
                                mCallBack.onClickDown(bean, i);
                            }
                        }
                    });
                    secondHolder.item.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mCallBack != null) {
                                mCallBack.onClickItem(bean, i);
                            }
                        }
                    });
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mList.size() > position) {
            ShopItemBean itemBean = mList.get(position);
            if (itemBean != null && -1 == itemBean.getShopId()) {
                return SHOP_TYPE;
            }
        }
        return SHOP_LIST;
    }

    private static class FirstViewHolder extends RecyclerView.ViewHolder {

        TextView typeName;
        TextView typeDescribe;

        public FirstViewHolder(@NonNull View itemView) {
            super(itemView);
            typeName = itemView.findViewById(R.id.mtv_shop_type_name);
            typeDescribe = itemView.findViewById(R.id.mtv_shop_type_describe);
        }
    }
    private static class SecondViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout item;
        TextView shopName;
        TextView shopDescribe;
        TextView shopSell;
        TextView shopDiscount;
        TextView shopRealMoney;
        TextView shopOriginalMoney;
        ImageView shopIcon;
        ShopSelectView shopSelect;

        public SecondViewHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.mrl_item);
            shopName = itemView.findViewById(R.id.mtv_shop_name);
            shopDescribe = itemView.findViewById(R.id.mtv_shop_describe);
            shopSell = itemView.findViewById(R.id.mtv_shop_sell);
            shopDiscount = itemView.findViewById(R.id.mtv_shop_discount);
            shopRealMoney = itemView.findViewById(R.id.mtv_real_money);
            shopOriginalMoney = itemView.findViewById(R.id.mtv_original_money);
            shopIcon = itemView.findViewById(R.id.miv_shop_icon);
            shopSelect = itemView.findViewById(R.id.mssv);
        }
    }

    public interface ShopCallBack {
        void onClickAdd(ShopItemBean shopItemBean, int position);
        void onClickDown(ShopItemBean shopItemBean, int position);
        void onClickItem(ShopItemBean shopItemBean, int position);
    }
}
