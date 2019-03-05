package com.zqf.food.business.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zqf.food.R;
import com.zqf.food.base.BaseActivity;
import com.zqf.food.business.set.SettingActivity;
import com.zqf.food.business.shop.ShopActivity;
import com.zqf.food.common.log.LogUtil;

public class MainActivity extends BaseActivity<MainPresenter>
        implements IMainContact.IV, View.OnClickListener {

    private TextView orderFoodView;
    private TextView addFoodView;
    private TextView paymentView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainPresenter initPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void initView() {
        super.initView();
        orderFoodView = findViewById(R.id.mtv_order_food);
        addFoodView = findViewById(R.id.mtv_add_food);
        paymentView = findViewById(R.id.mtv_payment);
    }

    @Override
    protected void initData() {
        super.initData();

    }

    @Override
    protected void initListener() {
        super.initListener();
        orderFoodView.setOnClickListener(this);
        addFoodView.setOnClickListener(this);
        paymentView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mtv_order_food:
                Intent intent = new Intent(MainActivity.this, ShopActivity.class);
                startActivity(intent);
                break;
            case R.id.mtv_add_food:
                break;
            case R.id.mtv_payment:
                break;
            default:
                break;
        }
    }
}
