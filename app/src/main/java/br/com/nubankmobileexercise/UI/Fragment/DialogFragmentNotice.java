package br.com.nubankmobileexercise.UI.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import br.com.nubankmobileexercise.R;

/**
 * Created by Paulo Vitor on 09/03/2016.
 */
public class DialogFragmentNotice extends DialogFragment {

    private Button btnPrimary;
    private Button btnSecondary;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dialog_notice, container, false);
        // Inflate the layout to use as dialog or embedded fragment

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
