package com.zqf.food.business.set;

import android.content.Context;

import com.zqf.food.base.BaseModel;
import com.zqf.food.common.constant.SPConstant;
import com.zqf.food.util.Config;
import com.zqf.food.util.SharePreUtils;

/**
 * Created by zqf on 2019/2/21.
 */

public class SettingModel extends BaseModel implements ISettingContact.IM {

    @Override
    public String getHttpUrl(Context context) {
        return SharePreUtils.getString(context, SPConstant.SP_KEY_HTTP_URL, Config.ROOT_URL);
    }

    @Override
    public void saveHttpUrl(Context context, String url) {
        SharePreUtils.putString(context, SPConstant.SP_KEY_HTTP_URL, url);
    }


}
