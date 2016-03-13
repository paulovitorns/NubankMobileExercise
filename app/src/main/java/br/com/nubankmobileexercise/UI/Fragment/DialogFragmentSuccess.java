package br.com.nubankmobileexercise.UI.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import br.com.nubankmobileexercise.R;

/**
 * Created by Paulo Vitor on 12/03/2016.
 */
public class DialogFragmentSuccess extends DialogFragment {

    private Button btnClose;

    public DialogFragmentSuccess(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dialog_success, container, false);
        // Inflate the layout to use as dialog or embedded fragment
        btnClose = (Button) view.findViewById(R.id.btnClose);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                getActivity().finish();
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
