package com.learzhu.baseframeworklibs.base;

/**
 * IBaseView.java是BaseFramework的http回调的通用类。
 *
 * @author Learzhu
 * @version 1.0.0 2019-03-27 11:11
 * @use
 * @update UserName 2019-03-27 11:11
 * @updateDes
 */

public interface IBaseView {
    void showLoading();

    void hideLoading();

    void requestFailed(String tips);
}
