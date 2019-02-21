package com.zqf.food.business.set;

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
        return null;
    }
}
