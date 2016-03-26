package br.com.nubankmobileexercise.Api.General.Response;

import android.util.Patterns;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Paulo Vitor on 10/03/2016.
 */
public class Link {
    @SerializedName("href")
    private String href;

    public Link(){

    }

    public Link(String href){
        if(href.equals("") || href == null)
            throw new IllegalArgumentException("O valor link não pode ser nulo");

        this.href = href;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public boolean isValidUrl() {

        return Patterns.WEB_URL.matcher(this.href).matches();
    }
}
