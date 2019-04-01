
package com.learzhu.baseframeworklibs.interfaces;

import android.app.Activity;

/**
 * FragmentPresenter.java是BaseFramework的Fragment的逻辑接口类。
 *
 * @author Learzhu
 * @version 1.0.0 2019-03-27 10:19
 * @use
 * @update UserName 2019-03-27 10:19
 * @updateDes * @use implements FragmentPresenter
 * * @warn 对象必须是Fragment
 */
public interface IFragmentIUiInit extends IUiInit {

    static final String ARGUMENT_ID = "ARGUMENT_ID";
    static final String ARGUMENT_USER_ID = "ARGUMENT_USER_ID";

    static final int RESULT_OK = Activity.RESULT_OK;
    static final int RESULT_CANCELED = Activity.RESULT_CANCELED;
}