package com.learzhu.baseframework.lazyload.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.learzhu.baseframework.R;
import com.learzhu.baseframework.lazyload.fragment.TabFragment;
import com.learzhu.baseframework.lazyload.fragment.TabFragment1;
import com.learzhu.baseframeworklibs.base.BaseActivity;
import com.learzhu.baseframeworklibs.base.BaseFragment;
import com.learzhu.baseframeworklibs.base.BasePresenter;

import androidx.fragment.app.FragmentManager;
import butterknife.OnClick;

public class TabActivity extends BaseActivity {

    private BaseFragment[] mBaseFragments;
    private FragmentManager mFragmentManager;
    private int mLastIndex;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, TabActivity.class);
        context.startActivity(intent);
    }

    @OnClick({R.id.tv_tab0, R.id.tv_tab1, R.id.tv_tab2, R.id.tv_tab3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_tab0:
                switchTab(0);
                break;
            case R.id.tv_tab1:
                switchTab(1);
                break;
            case R.id.tv_tab2:
                switchTab(2);
                break;
            case R.id.tv_tab3:
                switchTab(3);
                break;
        }
    }

    @Override
    protected BasePresenter createPresenter() {
        return new BasePresenter() {
        };
    }

    @Override
    public int getResLayoutId() {
        return R.layout.activity_tab;
    }

    @Override
    public void initView() {
        mFragmentManager = getSupportFragmentManager();
        mBaseFragments = new BaseFragment[4];
        mBaseFragments[0] = TabFragment.newInstance("1-1");
        mBaseFragments[1] = TabFragment1.newInstance("1-2");
        mBaseFragments[2] = TabFragment.newInstance("1-3");
        mBaseFragments[3] = TabFragment.newInstance("1-4");
        mFragmentManager.beginTransaction()
                .add(R.id.fl_container, mBaseFragments[0], "1-1")
                .add(R.id.fl_container, mBaseFragments[1], "1-2")
                .add(R.id.fl_container, mBaseFragments[2], "1-3")
                .add(R.id.fl_container, mBaseFragments[3], "1-4")
                .hide(mBaseFragments[0])
                .hide(mBaseFragments[1])
                .hide(mBaseFragments[2])
                .hide(mBaseFragments[3])
                .show(mBaseFragments[0])
                .commit();
        mLastIndex = 0;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

    /**
     * switchFragment
     *
     * @param currentIndex
     */
    private void switchTab(int currentIndex) {
        if (currentIndex == mLastIndex) {
            return;
        }
        mFragmentManager.beginTransaction()
                .hide(mBaseFragments[mLastIndex])
                .show(mBaseFragments[currentIndex])
                .commit();
        mLastIndex = currentIndex;
    }
}
