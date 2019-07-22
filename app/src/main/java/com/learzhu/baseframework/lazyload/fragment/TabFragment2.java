package com.learzhu.baseframework.lazyload.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.learzhu.baseframework.R;
import com.learzhu.baseframeworklibs.base.BaseFragment;
import com.learzhu.baseframeworklibs.base.BasePresenter;
import com.learzhu.baseframeworklibs.base.LazyLoadFragment;

import androidx.fragment.app.FragmentManager;
import butterknife.OnClick;

public class TabFragment2 extends LazyLoadFragment {
    private static final String DATA = "data";

    private String data;

    private BaseFragment[] fragments;
    private FragmentManager fragmentManager;
    private int lastIndex;

    @OnClick({R.id.tv_tab0, R.id.tv_tab1, R.id.tv_tab2})
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
        }
    }

    public TabFragment2() {
        // Required empty public constructor
    }

    public static TabFragment2 newInstance(String data) {
        TabFragment2 fragment = new TabFragment2();
        Bundle args = new Bundle();
        args.putString(DATA, data);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void initData() {
        if (getArguments() != null) {
            data = getArguments().getString(DATA);
        }
        fragmentManager = getChildFragmentManager();
        fragments = new BaseFragment[3];
        fragments[0] = TabFragment.newInstance("3-1");
        fragments[1] = TabFragment.newInstance("3-2");
        fragments[2] = TabFragment.newInstance("3-3");

        fragmentManager.beginTransaction()
                .add(R.id.fl_container, fragments[0], "3-1")
                .add(R.id.fl_container, fragments[1], "3-2")
                .add(R.id.fl_container, fragments[2], "3-3")
                .hide(fragments[0])
                .hide(fragments[1])
                .hide(fragments[2])
                .show(fragments[0])
                .commit();
        lastIndex = 0;
    }

    @Override
    public void initEvent() {
    }

    @Override
    public int getResLayoutId() {
        return R.layout.fragment_layout4;
    }

    @Override
    public void initView() {

    }

    private void switchTab(int currentIndex) {
        if (currentIndex == lastIndex) {
            return;
        }
        fragmentManager.beginTransaction()
                .hide(fragments[lastIndex])
                .show(fragments[currentIndex])
                .commit();
        lastIndex = currentIndex;
    }

    @Override
    protected void loadData() {
        Log.e("load", data);
    }

    @Override
    protected boolean isNeedReload() {
        return true;
    }

    @Override
    protected BasePresenter createPresenter() {
        return new BasePresenter() {
        };
    }
}
