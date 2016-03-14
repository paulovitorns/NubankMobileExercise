package br.com.nubankmobileexercise.Api.General;

import br.com.nubankmobileexercise.Api.General.Response.ChargeBack;
import br.com.nubankmobileexercise.Api.General.Response.LinksResponseNotice;
import br.com.nubankmobileexercise.Api.General.Response.MessageResponse;
import br.com.nubankmobileexercise.Api.General.Response.Notice;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Paulo on 10/03/2016.
 */
public interface LinksRepo {

    @GET("/")
    void getLinkNotice(Callback<LinksResponseNotice> cb);

    @GET("/{url}")
    void getNotice(@Path("url") String url, Callback<Notice> cb);

    @GET("/{chargeback}")
    void getChargeback(@Path("chargeback") String chargeback, Callback<ChargeBack> cb);

    @FormUrlEncoded
    @POST("/{action}")
    void postAction(@Path("action") String action,  @Field("Field") String str, Callback<MessageResponse> cb);

}
