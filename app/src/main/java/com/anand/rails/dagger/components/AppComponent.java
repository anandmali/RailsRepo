package com.anand.rails.dagger.components;

import com.anand.rails.dagger.modules.ApiModule;
import com.anand.rails.dagger.modules.UtilModule;
import com.anand.rails.views.activities.MainActivity;
import com.anand.rails.views.activities.MapRoutingActivity;
import com.anand.rails.views.activities.RailsCommitActivity;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {ApiModule.class, UtilModule.class})
@Singleton
public interface AppComponent {
    void inject(MainActivity activity);
    void inject(RailsCommitActivity railsCommitActivity);
    void inject(MapRoutingActivity mapRoutingActivity);
}
