package com.zqf.food.business.set;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zqf.food.R;
import com.zqf.food.base.BaseActivity;
import com.zqf.food.base.interfaced.IPresenter;

public class SettingActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public IPresenter initPresenter() {
        return null;
    }
}
