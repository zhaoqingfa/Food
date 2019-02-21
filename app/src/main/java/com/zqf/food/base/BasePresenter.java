package com.zqf.food.base;

import android.annotation.SuppressLint;
import android.support.v4.util.Preconditions;

import com.zqf.food.base.interfaced.IModel;
import com.zqf.food.base.interfaced.IPresenter;
import com.zqf.food.base.interfaced.IView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by zqf on 2018/11/23.
 */

public abstract class BasePresenter<V extends IView, M extends IModel> implements IPresenter {
    protected V view;
    protected M model;
    // 将所有正在处理的Subscription都添加到CompositeSubscription中
    protected CompositeDisposable mCompositeDisposable;

    @SuppressLint("RestrictedApi")
    public BasePresenter(V view) {
        Preconditions.checkNotNull(view, IView.class.getName() + "%s cannot be null");
        this.view = view;
        this.model = getModel();
        init();
    }

    public abstract M getModel();

    @Override
    public void init() {

    }

    @Override
    public void destroy() {
        unDisposable();
        view = null;
        if (model != null) {
            model.destroy();
            model = null;
        }
        mCompositeDisposable = null;
    }

    /**
     * 将Disposable添加
     * @param subscription
     */
    public void addDisposable(Disposable subscription) {
        // csb 如果解绑了的话添加 sb 需要新的实例否则绑定时无效的
        if (mCompositeDisposable == null || mCompositeDisposable.isDisposed()) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }

    /**
     * 在界面退出等需要解绑观察者的情况下调用此方法统一解绑，防止Rx造成的内存泄漏
     */
    public void unDisposable() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }
}
