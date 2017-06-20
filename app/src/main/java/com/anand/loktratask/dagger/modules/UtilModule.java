package com.anand.loktratask.dagger.modules;

import com.anand.loktratask.utils.PermissionCheck;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UtilModule {

    @Provides
    @Singleton
    PermissionCheck providePermissionCheck() {
        return new PermissionCheck();
    }
}
