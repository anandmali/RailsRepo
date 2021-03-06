package com.anand.rails.events;

import com.anand.rails.model.pojo.CommitObject;

import java.util.List;

public class CommitsEvent {

    private List<CommitObject> commits;

    public CommitsEvent(List<CommitObject> commits) {
        this.commits = commits;
    }

    public List<CommitObject> getCommits() {
        return commits;
    }
}
