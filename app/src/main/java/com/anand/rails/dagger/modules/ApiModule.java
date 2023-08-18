package com.anand.rails.dagger.modules;

import com.anand.rails.model.CommitsApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApiModule {

    @Provides
    @Singleton
    CommitsApi providePostsApi() {
        return new CommitsApi();
    }

}
