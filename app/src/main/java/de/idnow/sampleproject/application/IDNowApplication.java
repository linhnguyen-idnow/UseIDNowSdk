package de.idnow.sampleproject.application;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

/**
 * Created by nfqlinhnguyen on 12/26/17.
 */

public class IDNowApplication extends MultiDexApplication {
    private static IDNowApplication mInstance;

    public static Context getAppContext() {
        return mInstance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
}
