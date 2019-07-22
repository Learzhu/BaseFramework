package com.learzhu.baseframework.lazyload.fragment;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.learzhu.baseframework.R;
import com.learzhu.baseframeworklibs.base.BasePresenter;
import com.learzhu.baseframeworklibs.base.LazyLoadFragment;

import butterknife.BindView;

/**
 * TabFragment.java是BaseFramework的Tab切换的Fragment类。
 *
 * @author Learzhu
 * @version 1.0.0 2019-07-22 11:55
 * @use
 * @update UserName 2019-07-22 11:55
 * @updateDes
 */
public class TabFragment extends LazyLoadFragment {
    private static final String DATA = "data";
    @BindView(R.id.tv_data)
    TextView dataTv;

    private String data;

    public static TabFragment newInstance(String data) {
        Bundle args = new Bundle();
        TabFragment fragment = new TabFragment();
        args.putString(DATA, data);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void loadData() {
        Log.e("load", data);
    }

    @Override
    protected BasePresenter createPresenter() {
        return new BasePresenter() {
        };
    }

    @Override
    public int getResLayoutId() {
        return R.layout.fragment_layout1;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        if (getArguments() != null) {
            data = getArguments().getString(DATA);
        }
        dataTv.setText("content:" + data);
    }

    @Override
    public void initEvent() {
    }

    @Override
    public void tryLoadData() {
        super.tryLoadData();
    }
}
