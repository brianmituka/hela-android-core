package com.creativeconsillium.drumsforafrica.helaapp.Activity.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class PreferenceUtils {
    public static final String MPESA_MESSAGES_SYNCED = "mpesa.synced";
    public static String TAG = PreferenceUtils.class.getSimpleName();

    public static SharedPreferences getSharedPreference(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void saveBooleanSharedPreference(Context context, String key, boolean value){
        Log.i(TAG, "Setting value for " + key + "as " + value);
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putBoolean(key, value);
        editor.apply();
    }
    public static boolean isMpesaSynced(Context context){
        return getSharedPreference(context).getBoolean("mpesa.synced", false);
    }
}
