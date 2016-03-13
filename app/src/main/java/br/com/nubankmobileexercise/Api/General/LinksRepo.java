package br.com.nubankmobileexercise.Api.General;

import br.com.nubankmobileexercise.Api.General.Response.LinksResponseNotice;
import br.com.nubankmobileexercise.Api.General.Response.Notice;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Paulo on 10/03/2016.
 */
public interface LinksRepo {

    @GET("/")
    void getLinkNotice(Callback<LinksResponseNotice> cb);

    @GET("/{url}")
    void getNotice(@Path("url") String url, Callback<Notice> cb);

}
