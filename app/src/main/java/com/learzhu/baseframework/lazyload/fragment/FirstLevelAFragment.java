package com.learzhu.baseframework.lazyload.fragment;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.learzhu.baseframework.R;
import com.learzhu.baseframeworklibs.base.BasePresenter;
import com.learzhu.baseframeworklibs.base.LazyLoadFragment;

import butterknife.BindView;

/**
 * FirstLevelAFragment.java是BaseFramework的第一层的Fragment类。
 *
 * @author Learzhu
 * @version 1.0.0 2019-07-19 17:39
 * @use
 * @update UserName 2019-07-19 17:39
 * @updateDes
 */
public class FirstLevelAFragment extends LazyLoadFragment {
    private static final String DATA = "data";
    private String mData;

    @BindView(R.id.tv_data)
    TextView dataTv;

    public static FirstLevelAFragment newInstance(String data) {
        FirstLevelAFragment fragment = new FirstLevelAFragment();
        Bundle args = new Bundle();
        args.putString(DATA, data);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void loadData() {
        Log.e("load", "1-1");
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
            mData = getArguments().getString(DATA);
        }
        dataTv.setText("content:" + mData);
    }

    @Override
    public void initEvent() {
    }

    @Override
    protected boolean isNeedReload() {
        return true;
    }
}
