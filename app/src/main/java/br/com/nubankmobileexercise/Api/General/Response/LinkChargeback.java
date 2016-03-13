package br.com.nubankmobileexercise.Api.General.Response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Paulo Vitor on 12/03/2016.
 */
public class LinkChargeback {

    @SerializedName("chargeback")
    private Link chargeback;

    public Link getChargeback() {
        return chargeback;
    }

    public void setChargeback(Link chargeback) {
        this.chargeback = chargeback;
    }
}
