package br.com.nubankmobileexercise.Api.General;

import br.com.nubankmobileexercise.Api.General.Response.LinksResponse;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by Paulo on 10/03/2016.
 */
public interface LinksRepo {

    @GET("/")
    void getLinkNotice(Callback<LinksResponse> cb);

}
