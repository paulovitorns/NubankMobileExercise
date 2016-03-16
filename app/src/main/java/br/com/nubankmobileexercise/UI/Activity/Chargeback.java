package br.com.nubankmobileexercise.UI.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import br.com.nubankmobileexercise.Adapter.CardViewReasonDetails;
import br.com.nubankmobileexercise.Api.General.LinksRepo;
import br.com.nubankmobileexercise.Api.General.Request.ChargebackRequest;
import br.com.nubankmobileexercise.Api.General.Response.ChargeBackResponse;
import br.com.nubankmobileexercise.Api.General.Response.MessageResponse;
import br.com.nubankmobileexercise.Api.General.Response.ReasonDetails;
import br.com.nubankmobileexercise.Api.General.ServiceGenerator;
import br.com.nubankmobileexercise.BuildConfig;
import br.com.nubankmobileexercise.R;
import br.com.nubankmobileexercise.UI.Fragment.DialogFragmentNotConnected;
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

    private ChargeBackResponse chargeBackResponse;
    private ChargebackRequest chargebackRequest;

    private List<ReasonDetails> details;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private final LinksRepo linksRepo = ServiceGenerator.createService(LinksRepo.class, BuildConfig.REST_BASE_API, 45);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chargeback);

        if(!Util.isNetworkAvailable()){
            showNotConnectedDialog();
        }

        btnContestar    = (Button) findViewById(R.id.btnContestar);
        btnCancelar     = (Button) findViewById(R.id.btnCancelar);

        txtTitle        = (TextView) findViewById(R.id.txtTitle);
        icoStatus       = (ImageView) findViewById(R.id.icoStatus);
        txtStatus       = (TextView) findViewById(R.id.txtStatus);
        textHint        = (EditText) findViewById(R.id.textHint);

        chargebackRequest = new ChargebackRequest();
        chargeBackResponse = null;

        icoStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String action = null;

                if(chargeBackResponse.isAutoblock()){
                    progressDialog = ProgressDialog.show(Chargeback.this, "Desbloqueio de cartão", "Estamos processando a sua requisição", true, true);
                    String[] noticeUrlArray = Util.explode(chargeBackResponse.getLinks().getUnblockcard().getHref());
                    action = noticeUrlArray[noticeUrlArray.length-1].toString();
                }else{
                    progressDialog = ProgressDialog.show(Chargeback.this, "Bloqueio de cartão", "Estamos processando a sua requisição", true, true);
                    String[] noticeUrlArray = Util.explode(chargeBackResponse.getLinks().getBlockcard().getHref());
                    action = noticeUrlArray[noticeUrlArray.length-1].toString();
                }
                postAction(action);
            }
        });


        btnContestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(!textHint.getText().toString().equals("")){
                    chargebackRequest.setComment(textHint.getText().toString());

                    postChargeBack(chargebackRequest);
                }else{
                    Toast.makeText(getApplicationContext(), "Por favor, informe mais detalhes sobre a sua compra", Toast.LENGTH_SHORT).show();
                }

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

        progressDialog = ProgressDialog.show(Chargeback.this, "Carregando...", "Estamos carregando a sua tela de contestação", true, true);

        linksRepo.getChargeback(chargebackUrl, new Callback<ChargeBackResponse>() {

            @Override
            public void success(ChargeBackResponse chargeback, Response response) {
                chargeBackResponse = chargeback;

                txtTitle.setText(chargeBackResponse.getTitle());
                checkBlockCard();

                if (chargeBackResponse.isAutoblock()) {
                    String[] noticeUrlArray = Util.explode(chargeBackResponse.getLinks().getBlockcard().getHref());
                    String action = noticeUrlArray[noticeUrlArray.length - 1].toString();
                    postAction(action);
                }


                textHint.setHint(Html.fromHtml(chargeBackResponse.getCommenthint()));

                if (chargeBackResponse != null && chargeBackResponse.getReason_details().size() > 0) {
                    for (ReasonDetails r : chargeBackResponse.getReason_details()) {
                        details.add(r);
                    }

                    mAdapter = new CardViewReasonDetails(details, chargebackRequest, chargeBackResponse);
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
                        chargeBackResponse.setAutoblock(false);
                    } else {
                        chargeBackResponse.setAutoblock(true);
                    }
                    checkBlockCard();

                    //Seto novamente os toggleButton para o status atual do cartão.
                    mAdapter = new CardViewReasonDetails(details, chargebackRequest, chargeBackResponse);
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

    private void postChargeBack(ChargebackRequest chargerequest) {

        progressDialog = ProgressDialog.show(Chargeback.this, "Enviando contestação", "", true, true);

        String[] noticeUrlArray = Util.explode(chargeBackResponse.getLinks().getSelf().getHref());
        String action = noticeUrlArray[noticeUrlArray.length-1].toString();

        linksRepo.postChargeBack(action, chargerequest, new Callback<MessageResponse>() {

            @Override
            public void success(MessageResponse messageResponse, Response response) {
                if (messageResponse.getStatus().equalsIgnoreCase("Ok")) {
                    showSuccessDialog();
                }

                progressDialog.dismiss();
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println(error.toString());
                if(error.getResponse().getReason() != null){
                    Toast.makeText(Chargeback.this, "Você precisa detalhar o que aconteceu com a sua compra.", Toast.LENGTH_LONG).show();
                }

                progressDialog.dismiss();
            }
        });
    }

    private void checkBlockCard(){
        if(chargeBackResponse.isAutoblock()){
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


    public void showNotConnectedDialog() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        DialogFragmentNotConnected newFragment = new DialogFragmentNotConnected();

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

        transaction.add(android.R.id.content, newFragment)
                .addToBackStack(null).commit();
    }
}
