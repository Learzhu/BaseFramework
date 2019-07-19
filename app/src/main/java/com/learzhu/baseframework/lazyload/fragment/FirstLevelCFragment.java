package com.learzhu.baseframework.lazyload.fragment;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.learzhu.baseframework.R;
import com.learzhu.baseframeworklibs.base.BasePresenter;
import com.learzhu.baseframeworklibs.base.LazyLoadFragment;

import butterknife.BindView;

/**
 * FirstLevelAFragment.java是液总汇的类。
 *
 * @author Learzhu
 * @version 2.0.0 2019-07-19 15:02
 * @update Learzhu 2019-07-19 15:02
 * @updateDes
 * @include {@link }
 * @used {@link }
 */
public class FirstLevelCFragment extends LazyLoadFragment {
    private static final String DATA = "data";
    private String mData;

    @BindView(R.id.tv_data)
    TextView dataTv;

    public static FirstLevelCFragment newInstance(String data) {
        FirstLevelCFragment fragment = new FirstLevelCFragment();
        Bundle args = new Bundle();
        args.putString(DATA, data);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void loadData() {
        Log.e("load", "1-3");
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
