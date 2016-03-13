package br.com.nubankmobileexercise.UI.Activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.nubankmobileexercise.R;
import br.com.nubankmobileexercise.UI.Fragment.DialogFragmentSuccess;

public class Success extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        showDialog();
    }


    public void showDialog() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        DialogFragmentSuccess newFragment = new DialogFragmentSuccess();

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

        transaction.add(android.R.id.content, newFragment)
                .addToBackStack(null).commit();
    }
}
