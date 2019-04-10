package com.learzhu.baseframework.test.demo;

import dagger.Module;
import dagger.Provides;

/**
 * CommonModule.java是液总汇的类。
 *
 * @author Learzhu
 * @version 2.0.0 2019-04-08 14:35
 * @update Learzhu 2019-04-08 14:35
 * @updateDes
 * @include {@link }
 * @used {@link }
 */
@Module
public class CommonModule {
    private ICommonView mICommonView;

    public CommonModule(ICommonView ICommonView) {
        mICommonView = ICommonView;
    }

    @Provides
    @ActivityScope
    public ICommonView provideIcommonView() {
        return this.mICommonView;
    }
}
