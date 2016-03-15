package br.com.nubankmobileexercise.Api.General.Request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Paulo Vitor on 14/03/2016.
 */
public class ReasonDetailsRequest {
    @SerializedName("id")
    private String id;

    @SerializedName("response")
    private boolean response;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }
}
