package br.com.nubankmobileexercise.Api.General.Response;

import com.google.gson.annotations.SerializedName;

import java.util.IllegalFormatException;
import java.util.List;

/**
 * Created by Paulo on 10/03/2016.
 */
public class LinksResponseNotice {

    @SerializedName("links")
    private LinkNotice links;

    public LinksResponseNotice(){

    }

    public LinksResponseNotice(LinkNotice linkNotice){
        if(linkNotice == null)
            throw new IllegalArgumentException("O objeto linkNotice não pode ser nulo");

        this.links = linkNotice;
    }

    public LinkNotice getLinks() {
        return links;
    }

    public void setLinks(LinkNotice links) {
        this.links = links;
    }
}
