package com.zqf.food.business.shopping;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zqf.food.R;
import com.zqf.food.business.shop.Shopping;
import com.zqf.food.common.widgets.ShopSelectView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by v_zhaoqingfa on 2019/2/22.
 */

public class ShoppingAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<Shopping> shopBeans;
    private ShoppingAdapterCallBack callBack;

    public ShoppingAdapter(Context context) {
        this.mContext = context;
        shopBeans = new ArrayList<>();
    }

    public void updateDate(List<Shopping> Shopping) {
        if (shopBeans != null) {
            this.shopBeans = Shopping;
            notifyDataSetChanged();
        }
    }

    public void setCallBack(ShoppingAdapterCallBack callBack) {
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_view_shopping, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
        Shopping shopBean = shopBeans.get(i);
        if (shopBean != null) {
            holder.shopName.setText(TextUtils.isEmpty(shopBean.getShopName()) ? mContext
                    .getResources().getString(R.string.no_shop_name) : shopBean.getShopName());
            float money = shopBean.getShopRealMoney() * shopBean.getShopNum();
            holder.shopMoney.setText(money + "");
            holder.selectView.updateData(shopBean.getShopNum(),
                    shopBean.getShopMaxNum(), shopBean.getShopMinNum());
            holder.selectView.setCallBack(new ShopSelectView.ShopSelectCallBack() {
                @Override
                public void clickAdd() {
                    if (callBack != null) {
                        callBack.add(i);
                    }
                }

                @Override
                public void clickDown() {
                    if (callBack != null) {
                        callBack.down(i);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return shopBeans.size();
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {
        TextView shopName;
        TextView shopMoney;
        ShopSelectView selectView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            shopName = itemView.findViewById(R.id.mtv_shop_name);
            shopMoney = itemView.findViewById(R.id.mtv_money);
            selectView = itemView.findViewById(R.id.mssv);
        }
    }

    public interface ShoppingAdapterCallBack {
        void add(int index);

        void down(int index);
    }
}
