package br.com.nubankmobileexercise.Api.General.Response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Paulo on 10/03/2016.
 */
public class LinksResponseChargeback {

    @SerializedName("links")
    private LinkChargeback links;

    public LinkChargeback getLinks() {
        return links;
    }

    public void setLinks(LinkChargeback links) {
        this.links = links;
    }
}
