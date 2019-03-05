package com.zqf.food.business.set;

import android.content.Context;
import android.text.TextUtils;

import com.zqf.food.R;
import com.zqf.food.base.BasePresenter;

/**
 * Created by zqf on 2019/2/21.
 */

public class SettingPresenter extends BasePresenter<ISettingContact.IV, ISettingContact.IM> {
    public SettingPresenter(ISettingContact.IV view) {
        super(view);
    }

    @Override
    public ISettingContact.IM getModel() {
        return new SettingModel();
    }

    public void getHttpUrl() {
        String httpUrl = mModel.getHttpUrl(mContext);
        mView.setHttpUrl(httpUrl);
    }

    public String saveHttpUrl(String oldUrl, String newUrl) {
        if (TextUtils.isEmpty(newUrl)) {
            return mContext.getResources().getString(R.string.set_save_data_null);
        }

        if (TextUtils.isEmpty(oldUrl)) {
            mModel.saveHttpUrl((Context) mView, newUrl);
        } else {
            if (!newUrl.equals(oldUrl)) {
                mModel.saveHttpUrl(mContext, newUrl);
            } else {
                return mContext.getResources().getString(R.string.set_save_data_no_change);
            }
        }
        return null;
    }
}
