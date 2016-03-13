package br.com.nubankmobileexercise.Api.General.Response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Paulo Vitor on 12/03/2016.
 */
public class LinkNotice {

    @SerializedName("notice")
    private Link notice;

    public Link getNotice() {
        return notice;
    }

    public void setNotice(Link notice) {
        this.notice = notice;
    }
}
