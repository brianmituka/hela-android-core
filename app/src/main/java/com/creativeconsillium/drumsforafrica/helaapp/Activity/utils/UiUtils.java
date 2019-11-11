package com.creativeconsillium.drumsforafrica.helaapp.Activity.utils;

import android.app.Activity;
import android.app.ProgressDialog;

public class UiUtils {
     static ProgressDialog progressDialog;

    public static void showDialog(String text, Activity activity){
        progressDialog = ProgressDialog.show(activity, "", text, true);
    }
    public static void hideDialog(){
        progressDialog.dismiss();
    }
}
