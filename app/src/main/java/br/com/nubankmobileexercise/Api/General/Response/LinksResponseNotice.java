package br.com.nubankmobileexercise.Api.General.Response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Paulo on 10/03/2016.
 */
public class LinksResponseNotice {

    @SerializedName("links")
    private LinkNotice links;

    public LinkNotice getLinks() {
        return links;
    }

    public void setLinks(LinkNotice links) {
        this.links = links;
    }
}
