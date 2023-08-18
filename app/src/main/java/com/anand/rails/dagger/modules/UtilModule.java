package com.anand.rails.dagger.modules;

import com.anand.rails.utils.PermissionCheck;

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
