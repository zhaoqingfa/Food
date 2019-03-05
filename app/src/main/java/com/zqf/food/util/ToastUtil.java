package com.zqf.food.util;

import android.content.Context;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.zqf.food.MainApplication;
import com.zqf.food.R;
import com.zqf.food.common.manager.ActivityManager;

/**
 * Created by v_zhaoqingfa on 2019/3/5.
 */

public class ToastUtil {
    public static final int LENGTH_LONG = 1;
    public static final int LENGTH_SHORT = 0;

    private static android.widget.Toast mToast;
    private static String mLastMessage;
    private static long mLastTime;

    private ToastUtil() {
    }

    public static synchronized android.widget.Toast makeText(Context context, CharSequence text, int duration) {
        View view;
        if (mToast == null) {
            mToast = new android.widget.Toast(context);
            LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflate.inflate(R.layout.view_transient_notification, null);
            mToast.setView(view);
        } else {
            view = mToast.getView();
        }
        TextView message = (TextView) view.findViewById(R.id.message);
        message.setText(text);
        mToast.setDuration(duration);
        return mToast;
    }

    public static android.widget.Toast makeText(Context context, @StringRes int resId, int duration) {
        return makeText(context, context.getString(resId), duration);
    }

    public static void showShortToast(final Context context, final String message) {
        if (ActivityManager.getInstance().isForeground()) {
            ActivityManager.getInstance().getCurrentActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    makeText(context, message, LENGTH_SHORT).show();
                }
            });
        }
    }

    public static void showShortToast(final String message) {
        showShortToast(MainApplication.getInstance().getContext(), message);
    }

    public static void showLongToast(final Context context, final String message) {
        if (ActivityManager.getInstance().isForeground()) {
            ActivityManager.getInstance().getCurrentActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    makeText(context, message, LENGTH_LONG).show();
                }
            });
        }
    }

    public static void showLongToast(final String message) {
        showLongToast(MainApplication.getInstance().getContext(), message);
    }

    /**
     * 过滤toast内容和点击时常
     * 内容相同并且触发时间较短则不显示
     * @param message
     */
    public static void showFilterToast(String message) {
        long currentTime = System.currentTimeMillis();
        if (ActivityManager.getInstance().isForeground()) {
            if (message.equals(mLastMessage) && currentTime - mLastTime < 3000) {
                return;
            }
            showShortToast(message);
            mLastMessage = message;
            mLastTime = currentTime;
        }
    }

    public static void cancleToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }
}
