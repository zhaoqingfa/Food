package com.zqf.food.business.launcher;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;

import com.zqf.food.business.main.MainActivity;
import com.zqf.food.R;
import com.zqf.food.base.BaseActivity;
import com.zqf.food.base.interfaced.IPresenter;

public class LauncherActivity extends BaseActivity {
    private static final long TIME = 2 * 1000;

    private Handler mHandler;
    private Runnable mRunnable;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_launcher;
    }

    @Override
    protected void initData() {
        mHandler = new Handler(Looper.myLooper());
        mRunnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LauncherActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
        mHandler.postDelayed(mRunnable, TIME);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null && mRunnable != null) {
            mHandler.removeCallbacks(mRunnable);
            mRunnable = null;
            mHandler = null;
        }
    }

    @Override
    public IPresenter initPresenter() {
        return null;
    }
}
