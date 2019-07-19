package com.learzhu.baseframeworklibs.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.learzhu.baseframeworklibs.R;
import com.learzhu.baseframeworklibs.interfaces.IFragmentIUiInit;
import com.learzhu.baseframeworklibs.utils.LogUtil;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * BaseFragment.java是BaseFramework的基础Fragment类。
 *
 * @author Learzhu
 * @version 1.0.0 2019-03-27 10:06
 * @use
 * @update UserName 2019-03-27 10:06
 * @updateDes * @use extends BaseFragment, 具体参考.DemoFragment
 * @des 通过继承可获取或使用 里面创建的 组件 和 方法
 * * @see #context
 * * @see #view
 * * @see #onCreateView
 * * @see #setContentView
 * * @see #runUiThread
 * * @see #runThread
 * * @see #onDestroy
 */
public abstract class BaseFragment<V, T extends BasePresenter<V>> extends Fragment implements IFragmentIUiInit {
    private static final String TAG = "BaseFragment";

    /**
     * 添加该Fragment的Activity
     *
     * @warn 不能在子类中创建
     */
    protected BaseActivity mContext = null;
    /**
     * 该Fragment全局视图
     *
     * @must 非abstract子类的onCreateView中return view;
     * @warn 不能在子类中创建
     */
    protected View mRootView = null;
    /**
     * 布局解释器
     *
     * @warn 不能在子类中创建
     */
    protected LayoutInflater mInflater = null;
    /**
     * 添加这个Fragment视图的布局
     *
     * @warn 不能在子类中创建
     */
    @Nullable
    protected ViewGroup container = null;

    private boolean isAlive = false;
    private boolean isRunning = false;

    /**
     * Presenter对象
     */
    protected T mPresenter;

    /**
     * ButterKnife解绑
     */
    protected Unbinder mUnbinder;

    /**
     * 设置Fragment的title用于在Fragment切换的时候使用
     */
    private String fragmentTitle;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    /**
     * @must 在非abstract子类的onCreateView中super.onCreateView且return view;
     */
    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = (BaseActivity) getActivity();
        isAlive = true;
        this.mInflater = inflater;
        this.container = container;

        LogUtil.e(TAG, "onCreateView()  " + mRootView + " mUnbinder " + mUnbinder + "\n" +
                " mContext: " + mContext + " getContext: " + getContext() + "\n" + "this: " + this);
        //创建Presenter
        mPresenter = createPresenter();
        mPresenter.attachView((V) this);
        if (mRootView == null && mContext != null) {
            // rootView=inflater.inflate(R.layout.tab_fragment, null);
            mRootView = inflater.inflate(getResLayoutId(), container, false);
            mUnbinder = ButterKnife.bind(this, mRootView);
            initView();
            initEvent();
            initData();
        } else if (mRootView != null) {
            //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (parent != null) {
                parent.removeView(mRootView);
            }
            // TODO: 2017/10/10 暂时为了避免replace 替换崩溃的方式
            mUnbinder = ButterKnife.bind(this, mRootView);
            LogUtil.e(TAG, "onCreateView: " + mRootView + " mUnbinder" + mUnbinder);
        }
        return mRootView;
    }

    /**
     * 设置界面布局
     *
     * @param layoutResID
     * @warn 最多调用一次
     * @use 在onCreateView后调用
     */
    public void setContentView(int layoutResID) {
        setContentView(mInflater.inflate(layoutResID, container, false));
    }

    /**
     * 设置界面布局
     *
     * @param v
     * @warn 最多调用一次
     * @use 在onCreateView后调用
     */
    public void setContentView(View v) {
        setContentView(v, null);
    }

    /**
     * 设置界面布局
     *
     * @param v
     * @param params
     * @warn 最多调用一次
     * @use 在onCreateView后调用
     */
    public void setContentView(View v, ViewGroup.LayoutParams params) {
        mRootView = v;
    }

    /**
     * 可用于 打开activity与fragment，fragment与fragment之间的通讯（传值）等
     */
    protected Bundle argument = null;
    /**
     * 可用于 打开activity以及activity之间的通讯（传值）等；一些通讯相关基本操作（打电话、发短信等）
     */
    protected Intent intent = null;

    /**
     * 通过id查找并获取控件，使用时不需要强转
     *
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    public <V extends View> V findView(int id) {
        return (V) mRootView.findViewById(id);
    }

    /**
     * 通过id查找并获取控件，并setOnClickListener
     *
     * @param id
     * @param l
     * @return
     */
    public <V extends View> V findView(int id, OnClickListener l) {
        V v = findView(id);
        v.setOnClickListener(l);
        return v;
    }

    /**
     * 通过id查找并获取控件，使用时不需要强转
     *
     * @param id
     * @return
     * @warn 调用前必须调用setContentView
     */
    public <V extends View> V findViewById(int id) {
        return findView(id);
    }

    /**
     * 通过id查找并获取控件，并setOnClickListener
     *
     * @param id
     * @param l
     * @return
     */
    public <V extends View> V findViewById(int id, OnClickListener l) {
        return findView(id, l);
    }


    public Intent getIntent() {
        return mContext.getIntent();
    }

    //运行线程<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * 在UI线程中运行，建议用这个方法代替runOnUiThread
     *
     * @param action
     */
    public final void runUiThread(Runnable action) {
        if (isAlive() == false) {
            LogUtil.w(TAG, "runUiThread  isAlive() == false >> return;");
            return;
        }
        mContext.runUiThread(action);
    }

    /**
     * 运行线程
     *
     * @param name
     * @param runnable
     * @return
     */
    public final Handler runThread(String name, Runnable runnable) {
        if (isAlive() == false) {
            LogUtil.w(TAG, "runThread  isAlive() == false >> return null;");
            return null;
        }
        return mContext.runThread(name + hashCode(), runnable);//name, runnable);同一Activity出现多个同名Fragment可能会出错
    }

    //运行线程>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //进度弹窗<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * 展示加载进度条,无标题
     *
     * @param stringResId
     */
    public void showProgressDialog(int stringResId) {
        if (isAlive() == false) {
            LogUtil.w(TAG, "showProgressDialog  isAlive() == false >> return;");
            return;
        }
        mContext.showProgressDialog(mContext.getResources().getString(stringResId));
    }

    /**
     * 展示加载进度条,无标题
     *
     * @param dialogMessage
     */
    public void showProgressDialog(String dialogMessage) {
        if (isAlive() == false) {
            LogUtil.w(TAG, "showProgressDialog  isAlive() == false >> return;");
            return;
        }
        mContext.showProgressDialog(dialogMessage);
    }

    /**
     * 展示加载进度条
     *
     * @param dialogTitle   标题
     * @param dialogMessage 信息
     */
    public void showProgressDialog(String dialogTitle, String dialogMessage) {
        if (isAlive() == false) {
            LogUtil.w(TAG, "showProgressDialog  isAlive() == false >> return;");
            return;
        }
        mContext.showProgressDialog(dialogTitle, dialogMessage);
    }

    /**
     * 隐藏加载进度
     */
    public void dismissProgressDialog() {
        if (isAlive() == false) {
            LogUtil.w(TAG, "dismissProgressDialog  isAlive() == false >> return;");
            return;
        }
        mContext.dismissProgressDialog();
    }
    //进度弹窗>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //启动Activity<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * 打开新的Activity，向左滑入效果
     *
     * @param intent
     */
    public void toActivity(Intent intent) {
        toActivity(intent, true);
    }

    /**
     * 打开新的Activity
     *
     * @param intent
     * @param showAnimation
     */
    public void toActivity(Intent intent, boolean showAnimation) {
        toActivity(intent, -1, showAnimation);
    }

    /**
     * 打开新的Activity，向左滑入效果
     *
     * @param intent
     * @param requestCode
     */
    public void toActivity(Intent intent, int requestCode) {
        toActivity(intent, requestCode, true);
    }

    protected abstract T createPresenter();

    /**
     * 打开新的Activity
     *
     * @param intent
     * @param requestCode
     * @param showAnimation
     */
    public void toActivity(final Intent intent, final int requestCode, final boolean showAnimation) {
        runUiThread(new Runnable() {
            @Override
            public void run() {
                if (intent == null) {
                    LogUtil.w(TAG, "toActivity  intent == null >> return;");
                    return;
                }
                //fragment中使用context.startActivity会导致在fragment中不能正常接收onActivityResult
                if (requestCode < 0) {
                    startActivity(intent);
                } else {
                    startActivityForResult(intent, requestCode);
                }
                if (showAnimation) {
                    mContext.overridePendingTransition(R.anim.right_push_in, R.anim.hold);
                } else {
                    mContext.overridePendingTransition(R.anim.null_anim, R.anim.null_anim);
                }
            }
        });
    }
    //启动Activity>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    //show short toast<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * 快捷显示short toast方法，需要long toast就用 Toast.makeText(string, Toast.LENGTH_LONG).show(); ---不常用所以这个类里不写
     *
     * @param stringResId
     */
    public void showShortToast(int stringResId) {
        if (isAlive() == false) {
            LogUtil.w(TAG, "showProgressDialog  isAlive() == false >> return;");
            return;
        }
        mContext.showShortToast(stringResId);
    }

    /**
     * 快捷显示short toast方法，需要long toast就用 Toast.makeText(string, Toast.LENGTH_LONG).show(); ---不常用所以这个类里不写
     *
     * @param string
     */
    public void showShortToast(String string) {
        if (isAlive() == false) {
            LogUtil.w(TAG, "showProgressDialog  isAlive() == false >> return;");
            return;
        }
        mContext.showShortToast(string);
    }

    /**
     * 快捷显示short toast方法，需要long toast就用 Toast.makeText(string, Toast.LENGTH_LONG).show(); ---不常用所以这个类里不写
     *
     * @param string
     * @param isForceDismissProgressDialog
     */
    public void showShortToast(String string, boolean isForceDismissProgressDialog) {
        if (isAlive() == false) {
            LogUtil.w(TAG, "showProgressDialog  isAlive() == false >> return;");
            return;
        }
        mContext.showShortToast(string, isForceDismissProgressDialog);
    }
    //show short toast>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    @Override
    public final boolean isAlive() {
        return isAlive && mContext != null;// & ! isRemoving();导致finish，onDestroy内runUiThread不可用
    }

    @Override
    public final boolean isRunning() {
        return isRunning & isAlive();
    }

    @Override
    public void onResume() {
        LogUtil.d(TAG, "\n onResume <<<<<<<<<<<<<<<<<<<<<<<");
        super.onResume();
        isRunning = true;
        LogUtil.d(TAG, "onResume >>>>>>>>>>>>>>>>>>>>>>>>\n");
    }

    @Override
    public void onPause() {
        LogUtil.d(TAG, "\n onPause <<<<<<<<<<<<<<<<<<<<<<<");
        super.onPause();
        isRunning = false;
        LogUtil.d(TAG, "onPause >>>>>>>>>>>>>>>>>>>>>>>>\n");
    }

    //基地址发生改变之后 重新创建Presenter
    public void reCreatePresenter() {
        mPresenter = createPresenter();
        mPresenter.attachView((V) this);
    }

    @Override
    public void onDestroyView() {
        //避免在Fragment生命周期结束的时候接受到当前的图片出现异常
        //避免 activity的Destory的空指针
        //当 Glide.with() 中传入的 Activity 或 Fragment 实例销毁时，Glide 会自动取消加载并回收资源。
//        if (mRootView != null) {
////            Glide.clear(mRootView);
//            Glide.with(mContext).clear(mRootView);
//        }
        super.onDestroyView();
        LogUtil.e(TAG, "onDestroyView: " + mRootView + " mUnbinder " + mUnbinder + "\n" +
                " mContext: " + mContext + " getContext: " + getContext() + "\n" + "this: " + this);
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        if (mPresenter != null && mPresenter.isViewAttached()) {
            mPresenter.detachView();
        }
    }

    /**
     * 销毁并回收内存
     *
     * @warn 子类如果要使用这个方法内用到的变量，应重写onDestroy方法并在super.onDestroy();前操作
     */
    @Override
    public void onDestroy() {
        LogUtil.d(TAG, "\n onDestroy <<<<<<<<<<<<<<<<<<<<<<<");
        dismissProgressDialog();
        if (mRootView != null) {
            try {
                mRootView.destroyDrawingCache();
            } catch (Exception e) {
                LogUtil.w(TAG, "onDestroy  try { view.destroyDrawingCache();" +
                        " >> } catch (Exception e) {\n" + e.getMessage());
            }
        }

        isAlive = false;
        isRunning = false;
        super.onDestroy();
        mRootView = null;
        mInflater = null;
        container = null;
        intent = null;
        argument = null;
        mContext = null;
        LogUtil.d(TAG, "onDestroy >>>>>>>>>>>>>>>>>>>>>>>>\n");
    }
}