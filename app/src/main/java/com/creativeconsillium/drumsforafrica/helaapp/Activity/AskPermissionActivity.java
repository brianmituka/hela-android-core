package com.creativeconsillium.drumsforafrica.helaapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.creativeconsillium.drumsforafrica.helaapp.Activity.utils.PermissionsUtils;
import com.creativeconsillium.drumsforafrica.helaapp.Activity.utils.SmsUtils;
import com.creativeconsillium.drumsforafrica.helaapp.R;

public class AskPermissionActivity extends AppCompatActivity {

    Button askPermissionButton;

    PermissionsUtils permissionsUtils = new PermissionsUtils();
    SmsUtils smsUtils = new SmsUtils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_permission);

    }

    @Override
    public void onResume(){
        super.onResume();
        askPermissionButton = (Button) findViewById(R.id.buttonpermissions);
        askPermissionButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                permissionsUtils.requestForPermissions(AskPermissionActivity.this);


            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        switch (requestCode) {
            case PermissionsUtils.APP_PERMISSIONS_REQUEST_CODE : {
                if (grantResults.length == 0){
                    break;
                }
                for (int i = 0; i < PermissionsUtils.permissions.length; i++) {
                    String permission = PermissionsUtils.permissions[i];
                    System.out.println(permissions[i] + " :: " + grantResults[i]);
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        boolean showRationale = ActivityCompat.shouldShowRequestPermissionRationale(AskPermissionActivity.this, permission);
                        if (!showRationale) {
                            System.out.println("Denied + Do Not Ask : " + permissions[i]);

                        }
                    }else {
                       goToSplashChoiceActivity();
                    }


                }
            }
        }
    }
    void goToSplashChoiceActivity () {
        Intent splashChoiceIntent = new Intent(AskPermissionActivity.this, SplashChoiceActivity.class);
        AskPermissionActivity.this.startActivity(splashChoiceIntent);
        AskPermissionActivity.this.finish();
    }


}
