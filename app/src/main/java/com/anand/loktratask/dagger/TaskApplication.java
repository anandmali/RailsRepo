package com.anand.loktratask.dagger;

import android.app.Application;

import com.anand.loktratask.dagger.components.AppComponent;
import com.anand.loktratask.dagger.components.DaggerAppComponent;
import com.anand.loktratask.dagger.modules.ApiModule;

public class TaskApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        //Build dagger components
        appComponent = DaggerAppComponent.builder()
                .apiModule(new ApiModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
