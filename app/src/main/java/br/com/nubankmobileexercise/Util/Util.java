package br.com.nubankmobileexercise.Util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import br.com.nubankmobileexercise.nubankapplication.NubankMobileExerciseApplication;

/**
 * Created by Paulo Vitor on 12/03/2016.
 */
public class Util {

    public static String[] explode(String string){
        String[] split = string.split("/");
        return split;
    }


    public static boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) NubankMobileExerciseApplication.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
