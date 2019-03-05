package com.zqf.food.business.set;

import android.content.Context;

import com.zqf.food.base.interfaced.IModel;
import com.zqf.food.base.interfaced.IView;

/**
 * Created by zqf on 2018/11/24.
 */

public interface ISettingContact {
    interface IV extends IView {
        void setHttpUrl(String url);
    }

    interface IM extends IModel {
        String getHttpUrl(Context context);

        void saveHttpUrl(Context context, String url);
    }
}
