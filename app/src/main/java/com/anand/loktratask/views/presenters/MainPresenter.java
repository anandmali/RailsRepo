package com.anand.loktratask.views.presenters;

import com.anand.loktratask.views.screen_contracts.MainScreen;

import javax.inject.Inject;

public class MainPresenter {

    @Inject
    public MainPresenter() {}

    public void OnShowCommitsButtonClick(MainScreen mainScreen) {
        mainScreen.launchCommitsActivity();
    }

    public void OnShowMapButtonClick(MainScreen mainScreen) {
        mainScreen.launchMapActivity();
    }
}
