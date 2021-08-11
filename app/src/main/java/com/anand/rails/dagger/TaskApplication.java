package com.anand.rails.dagger;

import android.app.Application;

import com.anand.rails.dagger.components.AppComponent;
import com.anand.rails.dagger.components.DaggerAppComponent;
import com.anand.rails.dagger.modules.ApiModule;
import com.anand.rails.dagger.modules.UtilModule;

public class TaskApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        //Build dagger components
        appComponent = DaggerAppComponent.builder()
                .apiModule(new ApiModule())
                .utilModule(new UtilModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
