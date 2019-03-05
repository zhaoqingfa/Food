package com.zqf.food.common.widgets;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zqf.food.R;

/**
 * Created by v_zhaoqingfa on 2019/3/2.
 */

public class ShopSelectView extends LinearLayout implements View.OnClickListener {

    private TextView numView;
    private ImageView addView;
    private ImageView downView;
    private ShopSelectCallBack mCallBack;

    public ShopSelectView(Context context) {
        super(context);
        init(context);
    }

    public ShopSelectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ShopSelectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_shop_select, this);
        downView = findViewById(R.id.miv_down);
        addView = findViewById(R.id.miv_add);
        numView = findViewById(R.id.mtv_num);
        setClickable(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.miv_add:
                if (mCallBack != null) {
                    mCallBack.clickAdd();
                }
                break;
            case R.id.miv_down:
                if (mCallBack != null) {
                    mCallBack.clickDown();
                }
                break;
            default:
                break;
        }
    }

    public void setCallBack(ShopSelectCallBack callBack) {
        mCallBack = callBack;
    }

    public void updateData(int num, int maxNum, int minNum) {
        numView.setText(num + "");
        downView.setVisibility(VISIBLE);
        addView.setVisibility(VISIBLE);
        numView.setVisibility(VISIBLE);
        if (num <= 0) {
            numView.setVisibility(INVISIBLE);
            downView.setVisibility(INVISIBLE);
        }
        if (num <= minNum) {
            downView.setVisibility(INVISIBLE);
        }
        if (num >= maxNum) {
            addView.setVisibility(INVISIBLE);
        }
    }

    public interface ShopSelectCallBack {
        void clickAdd();
        void clickDown();
    }
}
