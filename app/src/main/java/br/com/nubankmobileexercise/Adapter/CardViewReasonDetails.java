package br.com.nubankmobileexercise.Adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

import br.com.nubankmobileexercise.Api.General.Request.ChargebackRequest;
import br.com.nubankmobileexercise.Api.General.Request.ReasonDetailsRequest;
import br.com.nubankmobileexercise.Api.General.Response.ChargeBackResponse;
import br.com.nubankmobileexercise.Api.General.Response.ReasonDetails;
import br.com.nubankmobileexercise.R;

/**
 * Created by Paulo on 14/03/2016.
 */
public class CardViewReasonDetails extends RecyclerView.Adapter<CardViewReasonDetails.ViewHolder> {

    private ChargebackRequest chargebackRequest;
    private ChargeBackResponse chargeback;
    private List<ReasonDetailsRequest> reasonDetailsRequests;
    private List<ReasonDetails> detailsList;

    public List<ReasonDetails> getDetailsList() {
        return detailsList;
    }

    public CardViewReasonDetails(List<ReasonDetails> reasonDetailses,
                                 ChargebackRequest chargebackRequest,
                                 ChargeBackResponse chargeBackResponse){
        this.detailsList = reasonDetailses;
        this.chargebackRequest = chargebackRequest;
        this.chargeback = chargeBackResponse;

        reasonDetailsRequests = new ArrayList<>();
    }

    @Override
    public CardViewReasonDetails.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_row, null);
        return new ViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final int pos = position;
        ReasonDetailsRequest reasonInit = new ReasonDetailsRequest();

        ReasonDetails detail = detailsList.get(pos);

        holder.txtLabel.setText(detail.getTitle());

        holder.toggleButon.setTextOn(detail.getId());
        holder.toggleButon.setTextOff(detail.getId());

        //Insiro valores default na lista
        reasonInit.setId(detail.getId());

        if(!chargeback.isAutoblock()){
            holder.txtLabel.setTextColor(Color.parseColor("#222222"));
            holder.toggleButon.setChecked(false);
            reasonInit.setResponse(false);
        }else{
            holder.txtLabel.setTextColor(Color.parseColor("#417505"));
            holder.toggleButon.setChecked(true);
            reasonInit.setResponse(true);
        }

        reasonDetailsRequests.add(reasonInit);
        chargebackRequest.setReason_details(reasonDetailsRequests);

        //Pego as opções caso o cliente escolha entre verdadeiro ou falso
        holder.toggleButon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                String idReasonDetails = holder.toggleButon.getTextOn().toString();
                ReasonDetailsRequest reason = new ReasonDetailsRequest();

                if (isChecked) {
                    holder.txtLabel.setTextColor(Color.parseColor("#417505"));
                    System.out.println(idReasonDetails + ": " + isChecked);
                }else {
                    holder.txtLabel.setTextColor(Color.parseColor("#222222"));
                    System.out.println(idReasonDetails + ": " + isChecked);
                }
                reason.setId(idReasonDetails);
                reason.setResponse(isChecked);

                if(reasonDetailsRequests.size() > 0){
                    for (int i = 0; i < reasonDetailsRequests.size(); i++) {
                        if(reasonDetailsRequests.get(i).getId().equals(reason.getId())) {
                            reasonDetailsRequests.remove(i);
                        }
                    }
                    reasonDetailsRequests.add(reason);
                }else{
                    reasonDetailsRequests.add(reason);
                }

                chargebackRequest.setReason_details(reasonDetailsRequests);

            }
        });
    }

    @Override
    public int getItemCount() {
        return detailsList != null ? detailsList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtLabel;
        private ToggleButton toggleButon;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);

            txtLabel    = (TextView) itemLayoutView.findViewById(R.id.txtLabel);
            toggleButon = (ToggleButton) itemLayoutView.findViewById(R.id.toggleButon);

        }

    }

}
