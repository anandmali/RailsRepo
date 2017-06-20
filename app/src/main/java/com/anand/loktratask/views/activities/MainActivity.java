package com.anand.loktratask.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.anand.loktratask.R;
import com.anand.loktratask.dagger.TaskApplication;
import com.anand.loktratask.views.presenters.MainPresenter;
import com.anand.loktratask.views.screen_contracts.MainScreen;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
        implements
        MainScreen {

    @Inject
    MainPresenter mainPresenter;

    @BindView(R.id.btnLocationTrack) Button _btnLocationTrack;
    @BindView(R.id.btnRepoCommits) Button _btnRepoCommits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((TaskApplication) getApplication()).getAppComponent().inject(this);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btnRepoCommits)
    public void showRailsCommits(Button button) {
        mainPresenter.OnShowCommitsButtonClick(this);
    }

    @OnClick(R.id.btnLocationTrack)
    public void showMapTracker(Button button) {
        mainPresenter.OnShowMapButtonClick(this);
    }

    @Override
    public void launchCommitsActivity() {
        startActivity(new Intent(this, RailsCommitActivity.class));
    }

    @Override
    public void launchMapActivity() {
        startActivity(new Intent(this, MapRoutingActivity.class));
    }
}
