package com.learzhu.baseframework.lazyload.activity;

import android.content.Context;
import android.content.Intent;

import com.google.android.material.tabs.TabLayout;
import com.learzhu.baseframework.R;
import com.learzhu.baseframework.lazyload.fragment.FirstLevelAFragment;
import com.learzhu.baseframework.lazyload.SubPagerAdapter;
import com.learzhu.baseframework.lazyload.fragment.FirstLevelBFragment;
import com.learzhu.baseframework.lazyload.fragment.FirstLevelCFragment;
import com.learzhu.baseframeworklibs.base.BaseActivity;
import com.learzhu.baseframeworklibs.base.BaseFragment;
import com.learzhu.baseframeworklibs.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

/**
 * LazyLoadActivity.java是BaseFramework的懒加载Fragment的demo类。
 *
 * @author Learzhu
 * @version 1.0.0 2019-07-19 17:41
 * @use
 * @update UserName 2019-07-19 17:41
 * @updateDes
 */
public class LazyLoadActivity extends BaseActivity {
    private List<BaseFragment> mFragments;
    private List<String> mTitles;

    @BindView(R.id.tablayout)
    TabLayout mTabLayout;

    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, LazyLoadActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getResLayoutId() {
        return R.layout.activity_lazy_load;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
        mFragments = new ArrayList<>();
        mFragments.add(FirstLevelAFragment.newInstance("1-1"));
        mFragments.add(FirstLevelBFragment.newInstance());
        mFragments.add(FirstLevelCFragment.newInstance("1-3"));

        mTitles = new ArrayList<>();
        mTitles.add("1-1");
        mTitles.add("1-2");
        mTitles.add("1-3");
    }

    @Override
    public void initEvent() {
        SubPagerAdapter pagerAdapter = new SubPagerAdapter(getSupportFragmentManager());
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
    protected BasePresenter createPresenter() {
        return new BasePresenter() {
        };
    }
}
