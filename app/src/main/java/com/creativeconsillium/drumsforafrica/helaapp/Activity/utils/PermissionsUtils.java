package com.creativeconsillium.drumsforafrica.helaapp.Activity.utils;


import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;


import com.creativeconsillium.drumsforafrica.helaapp.Activity.AskPermissionActivity;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionsUtils {
    private boolean isPermissionGranted;
    public static final int APP_PERMISSIONS_REQUEST_CODE = 1;
    public static String[] permissions = new String[] {
            Manifest.permission.READ_SMS,
            Manifest.permission.INTERNET,
            Manifest.permission.RECEIVE_SMS
    };

    public Boolean checkPermissions(Activity activity){
        isPermissionGranted = true;
        System.out.println("XXXXXX Perns " + permissions.toString());
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(activity, permissions[i])
                    != PackageManager.PERMISSION_GRANTED) {
                System.out.println("Permission Denied:::" + permissions[i]);
                isPermissionGranted = false;
            }else{
                System.out.println("Permission allowed:: " + permissions[i]);
            }

        }

        System.out.println(isPermissionGranted);

        return isPermissionGranted;
    }

    public void requestForPermissions(Activity activity){
        ActivityCompat.requestPermissions(activity, PermissionsUtils.permissions, PermissionsUtils.APP_PERMISSIONS_REQUEST_CODE);
    }

}
