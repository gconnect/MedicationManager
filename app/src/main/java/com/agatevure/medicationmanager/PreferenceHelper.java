package com.agatevure.medicationmanager;

import android.content.Context;
import android.content.SharedPreferences;



public class PreferenceHelper {

    //The constant whose value is the key
    public static final String SPLASH_IS_INVISIBLE = "splash_is_invisible";

    private static PreferenceHelper sInstance;

    private Context mContext;

    private SharedPreferences preferences;

    private PreferenceHelper() {

    }

    public static PreferenceHelper getInstance() {
        if (sInstance ==null) {
            sInstance = new PreferenceHelper();
        }
        return sInstance;
    }

    public void init(Context context) {
        this.mContext = context;
        preferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE);
    }
    // helps to transfer data from checkbox and interacts with SplashScreen
    public void putBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }
    // helps to transfer data from checkbox and interacts with SplashScreen
    public boolean getBoolean(String key) {
        return preferences.getBoolean(key, false);
    }
}