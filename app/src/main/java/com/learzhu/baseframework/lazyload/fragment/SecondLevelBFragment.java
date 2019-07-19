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
 * FirstLevelBFragment.java是BaseFramework的嵌套的Fragment类。
 *
 * @author Learzhu
 * @version 1.0.0 2019-07-19 17:01
 * @use
 * @update UserName 2019-07-19 17:01
 * @updateDes
 */
public class SecondLevelBFragment extends LazyLoadFragment {
    private List<BaseFragment> mFragments;
    private List<String> mTitles;

    @BindView(R.id.tablayout_sub)
    TabLayout mTabLayout;

    @BindView(R.id.viewpager_sub)
    ViewPager mViewPager;

    public static SecondLevelBFragment newInstance() {
        SecondLevelBFragment fragment = new SecondLevelBFragment();
        return fragment;
    }

    @Override
    protected void loadData() {
        Log.e("load", "2-2");
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
        mFragments.add(ThirdLevelFragment.newInstance("3-1"));
        mFragments.add(ThirdLevelFragment.newInstance("3-2"));
        mFragments.add(ThirdLevelFragment.newInstance("3-3"));

        mTitles = new ArrayList<>();
        mTitles.add("3-1");
        mTitles.add("3-2");
        mTitles.add("3-3");

        SubPagerAdapter pagerAdapter = new SubPagerAdapter(getChildFragmentManager());
        pagerAdapter.setData(mFragments, mTitles);
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setOffscreenPageLimit(mTitles.size() - 1);
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

    @Override
    protected boolean isNeedReload() {
        return true;
    }
}
