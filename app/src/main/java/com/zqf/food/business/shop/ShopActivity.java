package com.zqf.food.business.shop;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zqf.food.R;
import com.zqf.food.base.BaseActivity;
import com.zqf.food.business.shop.adapter.ShopAdapter;
import com.zqf.food.business.shop.adapter.ShopTypeAdapter;
import com.zqf.food.business.shop.bean.ShopDataBean;
import com.zqf.food.business.shop.bean.ShopItemBean;
import com.zqf.food.business.shopping.ShoppingView;
import com.zqf.food.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShopActivity extends BaseActivity<ShopPresenter>
        implements IShopContact.IV, View.OnClickListener {

    private RecyclerView mTypeListView;
    private RecyclerView mListView;
    private LinearLayout mShadowView;
    private ShoppingView mShoppingView;
    private RelativeLayout mBottomView;
    private TextView mOriginalPriceView;
    private TextView mRealPriceView;
    private TextView mSettlementView;
    private TextView mShoppingNumView;

    private ShopAdapter shopAdapter;
    private LinearLayoutManager linearLayoutManager;
    private ShopTypeAdapter shopTypeAdapter;
    private List<ShopDataBean> mList;
    // 目标项是否在最后一个可见项之后
    private boolean mShouldScroll;
    // 记录目标项位置
    private int mToPosition;
    // 记录上次滑动的item位置
    private int oldPosition = 0;



    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop;
    }

    @Override
    public ShopPresenter initPresenter() {
        return new ShopPresenter(this);
    }

    @Override
    protected void initView() {
        super.initView();
        mTypeListView = findViewById(R.id.mrv_type_list);
        mListView = findViewById(R.id.mrv_list);
        mShadowView = findViewById(R.id.mll_shadow);
        mShoppingView = findViewById(R.id.msv);
        mBottomView = findViewById(R.id.mrl_bottom);
        mOriginalPriceView = findViewById(R.id.mtv_original_price);
        mRealPriceView = findViewById(R.id.mtv_real_price);
        mSettlementView = findViewById(R.id.mtv_go_to_settlement);
        mShoppingNumView = findViewById(R.id.mtv_shopping_num);

        shopAdapter = new ShopAdapter(this);
        linearLayoutManager = new LinearLayoutManager(this);
        mListView.setLayoutManager(linearLayoutManager);
        mListView.setAdapter(shopAdapter);
        mListView.addOnScrollListener(getOnScrollListener());
        shopAdapter.setCallBack(getShopCallBack());
        shopTypeAdapter = new ShopTypeAdapter(this);
        mTypeListView.setLayoutManager(new LinearLayoutManager(this));
        mTypeListView.setAdapter(shopTypeAdapter);
        shopTypeAdapter.setCallBack(getShopTypeCallBack());
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.getData();
    }

    @Override
    protected void initListener() {
        super.initListener();
        mBottomView.setOnClickListener(this);
        mSettlementView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mrl_bottom:
                if (mPresenter.isEmptyMap()) {
                    mShoppingView.setVisibility(View.GONE);
                } else {
                    mShoppingView.setVisibility(View.VISIBLE);
                    // TODO
//                    mShoppingView.updateDate();
                }
                break;
            case R.id.mtv_go_to_settlement:
                if (mPresenter.isEmptyMap()) {
                    ToastUtil.showShortToast(mContext, getResources().getString(R.string.no_selected_shop));
                    return;
                }
                // 弹出确认对话框
                // 下订单，显示loading 结束后finish。

                break;
            default:
                break;
        }
    }

    private ShopAdapter.ShopCallBack getShopCallBack() {
        return new ShopAdapter.ShopCallBack() {
            @Override
            public void onClickAdd(ShopItemBean shopItemBean, int position) {
                mPresenter.addSelectMap(shopItemBean);
            }

            @Override
            public void onClickDown(ShopItemBean shopItemBean, int position) {
                mPresenter.downSelectMap(shopItemBean);
            }

            @Override
            public void onClickItem(ShopItemBean shopItemBean, int position) {
                ToastUtil.showShortToast(mContext, "后期加上展示详情");
            }
        };
    }

    private ShopTypeAdapter.ShopTypeCallBack getShopTypeCallBack() {
        return new ShopTypeAdapter.ShopTypeCallBack() {
            @Override
            public void clickItem(int position) {
//                setSelectType(position);
//                shopTypeAdapter.updateData(mList);
//                isClickType = true;
                // 滑动到对应位置
                int pos = 0;
                for (int i = 0; i < position; i++) {
                    ShopDataBean bean = mList.get(i);
                    if (bean != null && bean.getShopItemBeans() != null) {
                        pos += bean.getShopItemBeans().size();
                    }
                    pos += 1;
                }
//                mListView.scrollToPosition(pos);
                smoothMoveToPosition(mListView, pos);
            }
        };
    }

    private RecyclerView.OnScrollListener getOnScrollListener() {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (mShouldScroll && RecyclerView.SCROLL_STATE_IDLE == newState) {
                    mShouldScroll = false;
                    smoothMoveToPosition(mListView, mToPosition);
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.e("==============", "huadong");
                int firstPosition = recyclerView.getChildLayoutPosition(recyclerView.getChildAt(0));
                int pos = firstPosition - oldPosition;
                oldPosition = firstPosition;
                if (pos == 0) {
                    // 顶部item没有变化
                    return;
                } else {
                    int total = 0;
                    for (int i = 0; i < mList.size(); i++) {
                        ShopDataBean bean = mList.get(i);
                        total += 1;
                        if (bean != null && bean.getShopItemBeans() != null) {
                            if (total <= firstPosition + 1 && firstPosition + 1 <= total
                                    + bean.getShopItemBeans().size()) {
                                mPresenter.setSelectType(mList, i);
                                shopTypeAdapter.updateData(mList);
                                break;
                            }
                            total += bean.getShopItemBeans().size();
                        }
                    }
                }
            }
        };
    }

    private void smoothMoveToPosition(RecyclerView mRecyclerView, final int position) {
        // 第一个可见位置
        int firstItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(0));
        // 最后一个可见位置
        int lastItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(mRecyclerView.getChildCount() - 1));
        if (position < firstItem) {
            // 第一种可能:跳转位置在第一个可见位置之前，使用smoothScrollToPosition
            mRecyclerView.smoothScrollToPosition(position);
        } else if (position <= lastItem) {
            // 第二种可能:跳转位置在第一个可见位置之后，最后一个可见项之前
            int movePosition = position - firstItem;
            if (movePosition >= 0 && movePosition < mRecyclerView.getChildCount()) {
                int top = mRecyclerView.getChildAt(movePosition).getTop();
                // smoothScrollToPosition 不会有效果，此时调用smoothScrollBy来滑动到指定位置
                mRecyclerView.smoothScrollBy(0, top);
            }
        } else {
            // 第三种可能:跳转位置在最后可见项之后，则先调用smoothScrollToPosition将要跳转的位置滚动到可见位置
            // 再通过onScrollStateChanged控制再次调用smoothMoveToPosition，执行上一个判断中的方法
            mRecyclerView.smoothScrollToPosition(position);
            mToPosition = position;
            mShouldScroll = true;
        }
    }

    @Override
    public void updateData(List<ShopDataBean> shopDataBeans) {
        mList = shopDataBeans;
        mPresenter.setSelectType(mList, 0);
        shopTypeAdapter.updateData(mList);
        shopAdapter.updateData(mPresenter.recombineData(mList));
    }

    @Override
    public void updateSelectData(Map<String, Shop> selectMap) {

    }

    private void setSettlementView(boolean isClick) {
        mSettlementView.setClickable(isClick);
        if (isClick) {
            mSettlementView.setTextColor(getResources().getColor(R.color.color_common_white));
            mSettlementView.setBackgroundColor(getResources().getColor(R.color.colorFF5CCF78));
        } else {
            mSettlementView.setTextColor(getResources().getColor(R.color.colorFF7C7C7D));
            mSettlementView.setBackgroundColor(getResources().getColor(R.color.colorFF535257));
        }
    }

    private void setRealPriceView(float money) {
        if (money < 0) {
            mRealPriceView.setTextColor(getResources().getColor(R.color.colorFF7C7C7D));
            mRealPriceView.setText(getResources().getString(R.string.no_selected_shop));
        } else {
            mRealPriceView.setTextColor(getResources().getColor(R.color.color_common_white));
            mRealPriceView.setText(getResources().getString(R.string.money_mark) + money);
        }
    }

    private void setOriginalPriceView(float money) {
        if (money < 0) {
            mOriginalPriceView.setVisibility(View.GONE);
        } else {
            mOriginalPriceView.setVisibility(View.VISIBLE);
        }
    }

    private void setShoppingNumView(int num) {
        if (num <= 0) {
            mShoppingNumView.setVisibility(View.GONE);
        } else {
            mShoppingNumView.setVisibility(View.VISIBLE);
            mShoppingNumView.setText("" + num);
        }
    }
}
