package com.learzhu.baseframework.lazyload.fragment;

import android.util.Log;

import com.google.android.material.tabs.TabLayout;
import com.learzhu.baseframework.R;
import com.learzhu.baseframework.lazyload.SubPagerAdapter;
import com.learzhu.baseframeworklibs.base.BaseFragment;
import com.learzhu.baseframeworklibs.base.BasePresenter;
import com.learzhu.baseframeworklibs.base.LazyLoadFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

/**
 * FirstLevelBFragment.java是BaseFramework的第一层的嵌套的Fragment类。
 *
 * @author Learzhu
 * @version 1.0.0 2019-07-19 17:01
 * @use
 * @update UserName 2019-07-19 17:01
 * @updateDes
 */
public class FirstLevelBFragment extends LazyLoadFragment {
    private List<BaseFragment> mFragments;
    private List<String> mTitles;

    private static final String DATA = "data";
    private String mData;

    @BindView(R.id.tablayout_sub)
    TabLayout mTabLayout;

    @BindView(R.id.viewpager_sub)
    ViewPager mViewPager;

    public static FirstLevelBFragment newInstance() {
        FirstLevelBFragment fragment = new FirstLevelBFragment();
        return fragment;
    }

    @Override
    protected void loadData() {
        Log.e("load", "1-2");
    }

    @Override
    protected BasePresenter createPresenter() {
        return new BasePresenter() {
        };
    }

    @Override
    public int getResLayoutId() {
        return R.layout.fragment_layout2;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
        mFragments = new ArrayList<>();
        mFragments.add(SecondLevelAFragment.newInstance("2-1"));
        mFragments.add(SecondLevelBFragment.newInstance());
        mFragments.add(SecondLevelCFragment.newInstance("2-3"));

        mTitles = new ArrayList<>();
        mTitles.add("2-1");
        mTitles.add("2-2");
        mTitles.add("2-3");

        SubPagerAdapter pagerAdapter = new SubPagerAdapter(getChildFragmentManager());
        pagerAdapter.setData(mFragments, mTitles);
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setOffscreenPageLimit(mTitles.size());
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void initEvent() {
    }
}
