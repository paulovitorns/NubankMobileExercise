package br.com.nubankmobileexercise.UI.Activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import br.com.nubankmobileexercise.R;
import br.com.nubankmobileexercise.UI.Fragment.DialogFragmentSuccess;


public class Chargeback extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private Button btnCancelar;
    private Button btnContestar;
    private ToggleButton switchCartao;
    private ToggleButton switchEstab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chargeback);

        btnContestar    = (Button) findViewById(R.id.btnContestar);
        btnCancelar     = (Button) findViewById(R.id.btnCancelar);
        switchCartao    = (ToggleButton) findViewById(R.id.switchCartao);
        switchEstab     = (ToggleButton) findViewById(R.id.switchEstab);

        switchCartao.setOnCheckedChangeListener(this);
        switchEstab.setOnCheckedChangeListener(this);

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

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        // TODO Auto-generated method stub
        if (isChecked)
           System.out.println("Change: true");
        else
            System.out.println("Change: false");
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
