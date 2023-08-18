package com.anand.rails.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CommitObject {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("sha")
    @Expose
    private String sha;
    @SerializedName("html_url")
    @Expose
    private String htmlUrl;
    @SerializedName("comments_url")
    @Expose
    private String commentsUrl;
    @SerializedName("commit")
    @Expose
    private Commit commit;
    @SerializedName("author")
    @Expose
    private AuthorUrl author;
    @SerializedName("committer")
    @Expose
    private CommitterUrl committer;
    @SerializedName("parents")
    @Expose
    private List<Parent> parents = null;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getCommentsUrl() {
        return commentsUrl;
    }

    public void setCommentsUrl(String commentsUrl) {
        this.commentsUrl = commentsUrl;
    }

    public Commit getCommit() {
        return commit;
    }

    public void setCommit(Commit commit) {
        this.commit = commit;
    }

    public AuthorUrl getAuthorUrl() {
        return author;
    }

    public void setAuthorUrl(AuthorUrl author) {
        this.author = author;
    }

    public CommitterUrl getCommitterUrl() {
        return committer;
    }

    public void setCommitterUrl(CommitterUrl committer) {
        this.committer = committer;
    }

    public List<Parent> getParents() {
        return parents;
    }

    public void setParents(List<Parent> parents) {
        this.parents = parents;
    }

}
