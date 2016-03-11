package br.com.nubankmobileexercise.Api.General.Response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Paulo Vitor on 10/03/2016.
 */
public class ReasonDetails {

    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

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
}
