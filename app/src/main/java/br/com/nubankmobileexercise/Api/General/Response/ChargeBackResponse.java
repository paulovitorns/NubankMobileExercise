package br.com.nubankmobileexercise.Api.General.Response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Paulo Vitor on 10/03/2016.
 */
public class ChargeBackResponse {

    @SerializedName("comment_hint")
    private String commenthint;

    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("autoblock")
    private boolean autoblock;

    @SerializedName("reason_details")
    private List<ReasonDetails> reason_details;

    @SerializedName("links")
    private LinksResponseChargeback links;

    public String getCommenthint() {
        return commenthint;
    }

    public void setCommenthint(String commenthint) {
        this.commenthint = commenthint;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isAutoblock() {
        return autoblock;
    }

    public void setAutoblock(boolean autoblock) {
        this.autoblock = autoblock;
    }

    public List<ReasonDetails> getReason_details() {
        return reason_details;
    }

    public void setReason_details(List<ReasonDetails> reason_details) {
        this.reason_details = reason_details;
    }

    public LinksResponseChargeback getLinks() {
        return links;
    }

    public void setLinks(LinksResponseChargeback links) {
        this.links = links;
    }

}
