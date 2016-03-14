package br.com.nubankmobileexercise.UI.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import br.com.nubankmobileexercise.Adapter.CardViewReasonDetails;
import br.com.nubankmobileexercise.Api.General.LinksRepo;
import br.com.nubankmobileexercise.Api.General.Response.ChargeBack;
import br.com.nubankmobileexercise.Api.General.Response.LinksResponseNotice;
import br.com.nubankmobileexercise.Api.General.Response.MessageResponse;
import br.com.nubankmobileexercise.Api.General.Response.ReasonDetails;
import br.com.nubankmobileexercise.Api.General.ServiceGenerator;
import br.com.nubankmobileexercise.BuildConfig;
import br.com.nubankmobileexercise.R;
import br.com.nubankmobileexercise.UI.Fragment.DialogFragmentSuccess;
import br.com.nubankmobileexercise.Util.Util;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class Chargeback extends AppCompatActivity {

    private ProgressDialog progressDialog;

    private Button btnCancelar;
    private Button btnContestar;
    private TextView txtTitle;
    private ImageView icoStatus;
    private TextView txtStatus;
    private EditText textHint;

    private ChargeBack chargeBack;

    private List<ReasonDetails> details;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private final LinksRepo linksRepo = ServiceGenerator.createService(LinksRepo.class, BuildConfig.REST_BASE_API, 45);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chargeback);

        btnContestar    = (Button) findViewById(R.id.btnContestar);
        btnCancelar     = (Button) findViewById(R.id.btnCancelar);

        txtTitle        = (TextView) findViewById(R.id.txtTitle);
        icoStatus       = (ImageView) findViewById(R.id.icoStatus);
        txtStatus       = (TextView) findViewById(R.id.txtStatus);
        textHint        = (EditText) findViewById(R.id.textHint);

        icoStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String action = null;

                if(chargeBack.isAutoblock()){
                    String[] noticeUrlArray = Util.explode(chargeBack.getLinks().getUnblockcard().getHref());
                    action = noticeUrlArray[noticeUrlArray.length-1].toString();
                }else{
                    String[] noticeUrlArray = Util.explode(chargeBack.getLinks().getBlockcard().getHref());
                    action = noticeUrlArray[noticeUrlArray.length-1].toString();
                }
                postAction(action);
            }
        });

        /*
        switchCartao.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    txtCartao.setTextColor(getResources().getColor(R.color.green));
                    System.out.println("Cartão em mãos: true");
                }else {
                    txtCartao.setTextColor(getResources().getColor(R.color.black));
                    System.out.println("Cartão em mãos: false");
                }
            }
        });
        switchEstab.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    txtEstab.setTextColor(getResources().getColor(R.color.green));
                    System.out.println("Conhece o estabelecimento: true");
                }else {
                    txtEstab.setTextColor(getResources().getColor(R.color.black));
                    System.out.println("Conhece o estabelecimento: false");
                }
            }
        });
        */

        btnContestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSuccessDialog();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent i = getIntent();

        if( i != null){
            String chargeback = i.getStringExtra("chargeback");
            if(chargeback != null && !chargeback.equals("")){

                mRecyclerView  = (RecyclerView) findViewById(R.id.listDetails);
                mRecyclerView.setHasFixedSize(true);

                details = new ArrayList<>();

                loadChargeback(chargeback);

                mLayoutManager = new LinearLayoutManager(this);
                mRecyclerView.setLayoutManager(mLayoutManager);


            }
        }

    }

    private void loadChargeback(String chargebackUrl){

        progressDialog = ProgressDialog.show(Chargeback.this, "Carregando...", "", true, true);

        linksRepo.getChargeback(chargebackUrl, new Callback<ChargeBack>() {

            @Override
            public void success(ChargeBack chargeBackResponse, Response response) {
                chargeBack = chargeBackResponse;
                txtTitle.setText(chargeBack.getTitle());

                checkBlockCard();

                textHint.setHint(Html.fromHtml(chargeBack.getCommenthint()));

                if (chargeBackResponse != null && chargeBackResponse.getReason_details().size() > 0) {
                    for (ReasonDetails r : chargeBackResponse.getReason_details()) {
                        details.add(r);
                    }

                    mAdapter = new CardViewReasonDetails(details);
                    mRecyclerView.setAdapter(mAdapter);

                }

                progressDialog.dismiss();
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println(error.toString());
                progressDialog.dismiss();
            }
        });
    }


    private void postAction(final String actionUrl) {

        linksRepo.postAction(actionUrl, "", new Callback<MessageResponse>() {

            @Override
            public void success(MessageResponse messageResponse, Response response) {
                if (messageResponse.getStatus().equalsIgnoreCase("Ok")) {
                    if (actionUrl.equals("card_unblock")) {
                        chargeBack.setAutoblock(false);
                    } else {
                        chargeBack.setAutoblock(true);
                    }
                    checkBlockCard();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println(error.toString());

            }
        });
    }

    private void checkBlockCard(){
        if(chargeBack.isAutoblock()){
            icoStatus.setImageDrawable(getResources().getDrawable(R.drawable.ic_chargeback_lock));
            txtStatus.setText(getResources().getString(R.string.block));
        }else{
            icoStatus.setImageDrawable(getResources().getDrawable(R.drawable.ic_chargeback_unlock));
            txtStatus.setText(getResources().getString(R.string.unblock));
        }
    }

    public void showSuccessDialog() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        DialogFragmentSuccess newFragment = new DialogFragmentSuccess();

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

        transaction.add(android.R.id.content, newFragment)
                .addToBackStack(null).commit();
    }
}
