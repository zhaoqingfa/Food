package com.zqf.food.business.shop.adapter;

import android.content.Context;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by v_zhaoqingfa on 2019/3/2.
 */

public class ShopTypeAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<ShopDataBean> mList;
    private ShopTypeCallBack mCallBack;

    public ShopTypeAdapter(Context context) {
        mContext = context;
        mList = new ArrayList<>();
    }

    public void updateData(List<ShopDataBean> shopDataBeans) {
        if (shopDataBeans != null) {
            mList = shopDataBeans;
            notifyDataSetChanged();
        }
    }

    public List<ShopDataBean> getData() {
        return mList;
    }

    public void setCallBack(ShopTypeCallBack callBack) {
        mCallBack = callBack;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_shop_type, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        final ViewHolder holder = (ViewHolder) viewHolder;
        final ShopDataBean bean = mList.get(i);
        if (bean != null) {
            holder.mTypeName.setText(bean.getShopTypeName());

            String url = bean.getShopTypeIcon();
            if (TextUtils.isEmpty(url)) {
                holder.mIcon.setVisibility(View.GONE);
            } else {
                holder.mIcon.setVisibility(View.VISIBLE);
                RequestOptions options = new RequestOptions()
                        .placeholder(R.mipmap.ic_launcher);
//                        .diskCacheStrategy(DiskCacheStrategy.NONE);
                Glide.with(mContext)
                        .load(bean.getShopTypeIcon()) //加载地址
                        .apply(options)//加载未完成时显示占位图
                        .into(holder.mIcon);//显示的位置
            }

            if (bean.getSelectNum() <= 0) {
                holder.selectNum.setVisibility(View.GONE);
            } else {
                holder.selectNum.setVisibility(View.VISIBLE);
                holder.selectNum.setText(bean.getSelectNum() + "");
            }

            if (bean.isSelect()) {
                holder.mTypeName.setTextColor(mContext.getResources().getColor(R.color.color_common_text));
                holder.mItem.setBackgroundColor(mContext.getResources().getColor(R.color.color_common_white));
            } else {
                holder.mTypeName.setTextColor(mContext.getResources().getColor(R.color.color_common_text2));
                holder.mItem.setBackgroundColor(mContext.getResources().getColor(R.color.color_common_background));
            }
            holder.mItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mCallBack != null) {
                        mCallBack.clickItem(i);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout mItem;
        ImageView mIcon;
        TextView mTypeName;
        TextView selectNum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mItem = itemView.findViewById(R.id.mrl_item);
            mIcon = itemView.findViewById(R.id.miv_icon);
            mTypeName = itemView.findViewById(R.id.mtv_shop_type_name);
            selectNum = itemView.findViewById(R.id.mtv_num);
        }
    }

    public interface ShopTypeCallBack {
        void clickItem(int position);
    }
}
