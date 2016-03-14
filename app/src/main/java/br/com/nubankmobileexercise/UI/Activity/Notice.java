package br.com.nubankmobileexercise.UI.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.nubankmobileexercise.Api.General.LinksRepo;
import br.com.nubankmobileexercise.Api.General.Response.ChargeBack;
import br.com.nubankmobileexercise.Api.General.Response.LinksResponseNotice;
import br.com.nubankmobileexercise.Api.General.ServiceGenerator;
import br.com.nubankmobileexercise.BuildConfig;
import br.com.nubankmobileexercise.R;
import br.com.nubankmobileexercise.Util.Util;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Notice extends AppCompatActivity {

    private ProgressDialog progressDialog;

    private LinksResponseNotice linksNotice;
    private br.com.nubankmobileexercise.Api.General.Response.Notice notice;

    private final LinksRepo linksRepo = ServiceGenerator.createService(LinksRepo.class, BuildConfig.REST_BASE_API, 45);
    private Button btnPrimary;
    private Button btnSecondary;
    private TextView txtTitleNotice;
    private TextView txtNotice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);


        btnPrimary = (Button) findViewById(R.id.btnPrimary);
        btnSecondary = (Button) findViewById(R.id.btnSecondary);
        txtTitleNotice = (TextView) findViewById(R.id.txtTitleNotice);
        txtNotice = (TextView) findViewById(R.id.txtNotice);

        loadLinkNotice();

        btnPrimary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), Chargeback.class);
                String[] noticeUrlArray = Util.explode(notice.getLinks().getChargeback().getHref());
                String chargeback = noticeUrlArray[noticeUrlArray.length-1].toString();

                i.putExtra("chargeback", chargeback);
                startActivity(i);
            }
        });

        btnSecondary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void loadLinkNotice(){

        progressDialog = ProgressDialog.show(Notice.this, "Carregando...", "", true, true);

        linksRepo.getLinkNotice(new Callback<LinksResponseNotice>() {

            @Override
            public void success(LinksResponseNotice linksResponse, Response response) {
                linksNotice = linksResponse;
                loadNotice();
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println(error.toString());
                progressDialog.dismiss();
            }
        });
    }

    private void loadNotice(){

        String[] noticeUrlArray = Util.explode(linksNotice.getLinks().getNotice().getHref());
        String noticeUrl = noticeUrlArray[noticeUrlArray.length-1].toString();

        linksRepo.getNotice(noticeUrl, new Callback<br.com.nubankmobileexercise.Api.General.Response.Notice>() {

            @Override
            public void success(br.com.nubankmobileexercise.Api.General.Response.Notice noticeResponse, Response response) {
                notice = noticeResponse;
                txtTitleNotice.setText(notice.getTitle());
                txtNotice.setText(Html.fromHtml(notice.getDescription()));

                btnPrimary.setText(notice.getPrimary_action().getTitle());
                btnSecondary.setText(notice.getSecondary_action().getTitle());

                progressDialog.dismiss();
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println(error.toString());
                progressDialog.dismiss();
            }
        });
    }

}
