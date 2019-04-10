package com.learzhu.baseframework.test.demo;

import android.content.Context;
import android.widget.Toast;

import javax.inject.Inject;

/**
 * LoginPresenter.java是液总汇的类。
 *
 * @author Learzhu
 * @version 2.0.0 2019-04-08 14:29
 * @update Learzhu 2019-04-08 14:29
 * @updateDes
 * @include {@link }
 * @used {@link }
 */
public class LoginPresenter {
    ICommonView mICommonView;

    @Inject
    public LoginPresenter(ICommonView ICommonView) {
        this.mICommonView = ICommonView;
    }

    public void login(User user) {
        Context mContext = mICommonView.getContext();
        Toast.makeText(mContext, "login---", Toast.LENGTH_SHORT).show();
    }
}
