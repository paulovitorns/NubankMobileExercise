package br.com.nubankmobileexercise.Api.General.Request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import br.com.nubankmobileexercise.Api.General.Response.LinksResponseChargeback;
import br.com.nubankmobileexercise.Api.General.Response.ReasonDetails;

/**
 * Created by Paulo Vitor on 10/03/2016.
 */
public class ChargebackRequest {

    @SerializedName("comment")
    private String comment;

    @SerializedName("reason_details")
    private List<ReasonDetailsRequest> reason_details;

    public ChargebackRequest(){

    }

    public ChargebackRequest(String comment, List<ReasonDetailsRequest> reason_details){

        if( ( comment.equals("") || comment == null ) || ( reason_details == null ) )
            throw new IllegalArgumentException("O comentário e os detalhes não podem ser nulos.");

        this.comment        = comment;
        this.reason_details = reason_details;

    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<ReasonDetailsRequest> getReason_details() {
        return reason_details;
    }

    public void setReason_details(List<ReasonDetailsRequest> reason_details) {
        this.reason_details = reason_details;
    }
}
