package br.com.nubankmobileexercise.Adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.List;

import br.com.nubankmobileexercise.Api.General.Response.ReasonDetails;
import br.com.nubankmobileexercise.R;

/**
 * Created by Paulo on 14/03/2016.
 */
public class CardViewReasonDetails extends RecyclerView.Adapter<CardViewReasonDetails.ViewHolder> {

    private List<ReasonDetails> detailsList;

    public List<ReasonDetails> getDetailsList() {
        return detailsList;
    }

    public CardViewReasonDetails(List<ReasonDetails> reasonDetailses){
        this.detailsList = reasonDetailses;
    }

    @Override
    public CardViewReasonDetails.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_row, null);
        return new ViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final int pos = position;

        holder.txtLabel.setText(detailsList.get(pos).getTitle());

        holder.toggleButon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    holder.txtLabel.setTextColor(Color.parseColor("#417505"));
                    System.out.println("Cartão em mãos: true");
                }else {
                    holder.txtLabel.setTextColor(Color.parseColor("#222222"));
                    System.out.println("Cartão em mãos: false");
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
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
