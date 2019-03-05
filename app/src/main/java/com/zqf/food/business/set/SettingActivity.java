package com.zqf.food.business.set;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zqf.food.R;
import com.zqf.food.base.BaseActivity;

public class SettingActivity extends BaseActivity<SettingPresenter>
        implements ISettingContact.IV, View.OnClickListener {

    private EditText inputUrlView;
    private Button confirmView;

    private String httpUrl;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public SettingPresenter initPresenter() {
        return new SettingPresenter(this);
    }

    @Override
    protected void initView() {
        super.initView();
        inputUrlView = findViewById(R.id.met_input_url);
        confirmView = findViewById(R.id.mb_confirm);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.getHttpUrl();
    }

    @Override
    protected void initListener() {
        super.initListener();
        confirmView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mb_confirm:
                String msg = mPresenter.saveHttpUrl(httpUrl, inputUrlView.getText().toString());
                if (!TextUtils.isEmpty(msg)) {
                    Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                } else {
                    finish();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void setHttpUrl(String url) {
        httpUrl = url;
        if (!TextUtils.isEmpty(httpUrl)) {
            inputUrlView.setText(httpUrl);
            inputUrlView.setSelection(httpUrl.length());
        }
    }
}
