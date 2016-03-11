package br.com.nubankmobileexercise.Api.General.Response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Paulo Vitor on 10/03/2016.
 */
public class Action {

    @SerializedName("title")
    private String title;

    @SerializedName("action")
    private String action;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
