package com.learzhu.baseframeworklibs.base;


import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * BasePresenter.java是BaseFramework的基础的Presenter类。
 *
 * @author Learzhu
 * @version 1.0.0 2019-03-27 11:10
 * @use
 * @update UserName 2019-03-27 11:10
 * @updateDes
 */
public abstract class BasePresenter<V> {
    /**
     * View接口弱引用
     */
    protected Reference<V> mViewRef;
    protected V mView;

    public void attachView(V view) {
        //这里用弱引用避免内存泄漏
        mViewRef = new WeakReference<V>(view);
        mView = mViewRef.get();
    }

    /**
     * 获取View
     * 拿到view（activity）的对象引用
     *
     * @return V(Fragment 、 Activity)
     */
    protected V getView() {
        if (mViewRef == null) {
            return null;
        }
        return mViewRef.get();
    }

    /**
     * 用于判断当前的View是否已经销毁避免异步回调异常
     */
    protected boolean isActive() {
        if (getView() == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 判断是否与View建立了关联
     *
     * @return
     */
    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    /**
     * 解除关联
     */
    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }
}
