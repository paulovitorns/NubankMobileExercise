package br.com.nubankmobileexercise.nubankapplication;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

/**
 * Created by Paulo on 10/03/2016.
 */
public class NubankMobileExerciseApplication extends MultiDexApplication {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
    }


}
