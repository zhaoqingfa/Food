package com.zqf.food.base;


import com.zqf.food.base.interfaced.IModel;

/**
 * Created by zqf on 2018/11/25.
 */

public abstract class BaseModel implements IModel {
    public BaseModel() {
        init();
    }

    @Override
    public void init() {

    }

    @Override
    public void destroy() {

    }
}
