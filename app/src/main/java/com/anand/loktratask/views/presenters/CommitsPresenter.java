package com.anand.loktratask.views.presenters;

import com.anand.loktratask.events.CommitsEvent;
import com.anand.loktratask.events.ErrorEvent;
import com.anand.loktratask.model.CommitsApi;
import com.anand.loktratask.model.pojo.CommitObject;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CommitsPresenter {

    private CommitsApi commitsApi;

    @Inject
    public CommitsPresenter(CommitsApi commitsApi) {
        this.commitsApi = commitsApi;
    }

    public void loadPostsFromAPI() {
        commitsApi.getCommistsObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<CommitObject>>() {
                    @Override
                    public void onNext(List<CommitObject> newPosts) {
                        EventBus.getDefault().post(new CommitsEvent(newPosts));
                    }

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        EventBus.getDefault().post(new ErrorEvent());
                    }
                });
    }
}
