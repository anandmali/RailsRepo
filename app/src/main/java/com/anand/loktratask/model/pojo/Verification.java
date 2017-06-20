
package com.anand.loktratask.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Verification {

    @SerializedName("verified")
    @Expose
    private Boolean verified;
    @SerializedName("reason")
    @Expose
    private String reason;
    @SerializedName("signature")
    @Expose
    private String signature;
    @SerializedName("payload")
    @Expose
    private String payload;

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

}
