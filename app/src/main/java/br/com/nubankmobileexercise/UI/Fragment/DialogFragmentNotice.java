package br.com.nubankmobileexercise.UI.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import br.com.nubankmobileexercise.Api.General.LinksRepo;
import br.com.nubankmobileexercise.Api.General.Response.LinksResponse;
import br.com.nubankmobileexercise.Api.General.ServiceGenerator;
import br.com.nubankmobileexercise.BuildConfig;
import br.com.nubankmobileexercise.R;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Paulo Vitor on 09/03/2016.
 */
public class DialogFragmentNotice extends DialogFragment {

    private Button btnPrimary;
    private Button btnSecondary;
    private LinksResponse linkRequest;
    private List<String> listLink;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dialog_notice, container, false);
        // Inflate the layout to use as dialog or embedded fragment

        linkRequest = new LinksResponse();
        listLink = new ArrayList<>();

        LinksRepo linksRepo = ServiceGenerator.createService(LinksRepo.class, BuildConfig.REST_BASE_API, 45);

        linksRepo.getLinkNotice(new Callback<LinksResponse>(){

            @Override
            public void success(LinksResponse linksResponse, Response response) {
                System.out.println(response.toString());
                System.out.println(linksResponse.toString());
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println(error.toString());
            }
        });

        btnPrimary = (Button) view.findViewById(R.id.btnPrimary);
        btnSecondary = (Button) view.findViewById(R.id.btnSecondary);

        btnSecondary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }
}
