package com.anand.loktratask.dagger.modules;

import com.anand.loktratask.model.CommitsApi;

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
