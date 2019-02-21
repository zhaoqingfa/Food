package com.zqf.food.business.main;

import com.zqf.food.base.BasePresenter;

/**
 * Created by zqf on 2019/2/20.
 */

public class MainPresenter extends BasePresenter<IMainContact.IV, IMainContact.IM> {

    public MainPresenter(IMainContact.IV view) {
        super(view);
    }

    @Override
    public IMainContact.IM getModel() {
        return new MainModel();
    }
}
