package com.anand.loktratask.dagger.components;

import com.anand.loktratask.dagger.modules.ApiModule;
import com.anand.loktratask.dagger.modules.UtilModule;
import com.anand.loktratask.views.activities.MainActivity;
import com.anand.loktratask.views.activities.MapRoutingActivity;
import com.anand.loktratask.views.activities.RailsCommitActivity;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {ApiModule.class, UtilModule.class})
@Singleton
public interface AppComponent {
    void inject(MainActivity activity);
    void inject(RailsCommitActivity railsCommitActivity);
    void inject(MapRoutingActivity mapRoutingActivity);
}
