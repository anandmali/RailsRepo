package com.anand.rails.views.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.anand.rails.R;
import com.anand.rails.dagger.TaskApplication;
import com.anand.rails.events.CommitsEvent;
import com.anand.rails.events.ErrorEvent;
import com.anand.rails.views.adapters.CommitsListAdapter;
import com.anand.rails.views.decorators.DividerItemDecoration;
import com.anand.rails.views.presenters.CommitsPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RailsCommitActivity extends AppCompatActivity {

    private CommitsListAdapter adapter;

    @Inject CommitsPresenter commitsPresenter;

    @BindView(R.id.recyclerViewCommits) RecyclerView _recyclerViewCommits;
    @BindView(R.id.txtViewError) TextView _txtViewError;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commit);

        ((TaskApplication) getApplication()).getAppComponent().inject(this);
        ButterKnife.bind(this);

        //Set list
        initRecyclerView();

        //Fetch rails commits by api
        commitsPresenter.loadPostsFromAPI();

    }

    public void initRecyclerView() {
        _recyclerViewCommits.setHasFixedSize(true);
        _recyclerViewCommits.setLayoutManager(new LinearLayoutManager(_recyclerViewCommits.getContext()));
        _recyclerViewCommits.setItemAnimator(new DefaultItemAnimator());
        _recyclerViewCommits.addItemDecoration(new DividerItemDecoration(_recyclerViewCommits.getContext(),
                DividerItemDecoration.VERTICAL_LIST));
        adapter = new CommitsListAdapter();
        _recyclerViewCommits.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode =  ThreadMode.MAIN)
    public void onEventMainThread(CommitsEvent commitsEvent) {
        adapter.addPosts(commitsEvent.getCommits());
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(ErrorEvent errorEvent) {
        showError();
    }

    private void hideError() {
        _recyclerViewCommits.setVisibility(View.VISIBLE);
        _txtViewError.setVisibility(View.GONE);
    }

    private void showError() {
        _recyclerViewCommits.setVisibility(View.GONE);
        _txtViewError.setVisibility(View.VISIBLE);
    }

}
