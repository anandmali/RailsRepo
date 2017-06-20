
package com.anand.loktratask.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tree {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("sha")
    @Expose
    private String sha;

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

}
