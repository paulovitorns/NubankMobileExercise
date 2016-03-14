package br.com.nubankmobileexercise.Api.General.Response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Paulo Vitor on 10/03/2016.
 */
public class Notice {

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("primary_action")
    private Action primary_action;

    @SerializedName("secondary_action")
    private Action secondary_action;

    @SerializedName("links")
    private LinkChargeback links;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Action getPrimary_action() {
        return primary_action;
    }

    public void setPrimary_action(Action primary_action) {
        this.primary_action = primary_action;
    }

    public Action getSecondary_action() {
        return secondary_action;
    }

    public void setSecondary_action(Action secondary_action) {
        this.secondary_action = secondary_action;
    }

    public LinkChargeback getLinks() {
        return links;
    }

    public void setLinks(LinkChargeback links) {
        this.links = links;
    }
}
