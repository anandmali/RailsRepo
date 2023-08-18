package com.anand.rails.model;

import com.anand.rails.model.pojo.CommitObject;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public class CommitsApi {

    private interface CommitsService {
        @GET("/repos/{owner}/{repo}/commits")
        Observable<List<CommitObject>> getPostsList(
                @Path("owner") String rails,
                @Path("repo") String s);
    }

    private Observable<List<CommitObject>> postsObservable = new Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(CommitsService.class).getPostsList("rails", "rails").cache();

    public Observable<List<CommitObject>> getCommistsObservable() {
        return postsObservable;
    }

}
