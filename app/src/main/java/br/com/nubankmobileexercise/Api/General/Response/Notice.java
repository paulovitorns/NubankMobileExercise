package br.com.nubankmobileexercise.Api.General.Response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Paulo Vitor on 10/03/2016.
 */
public class Notice {

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("primary_action")
    private List<Action> primary_action;

    @SerializedName("secondary_action")
    private List<Action> secondary_action;

    @SerializedName("links")
    private LinksResponse links;

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

    public List<Action> getPrimary_action() {
        return primary_action;
    }

    public void setPrimary_action(List<Action> primary_action) {
        this.primary_action = primary_action;
    }

    public List<Action> getSecondary_action() {
        return secondary_action;
    }

    public void setSecondary_action(List<Action> secondary_action) {
        this.secondary_action = secondary_action;
    }

    public LinksResponse getLinks() {
        return links;
    }

    public void setLinks(LinksResponse links) {
        this.links = links;
    }
}
