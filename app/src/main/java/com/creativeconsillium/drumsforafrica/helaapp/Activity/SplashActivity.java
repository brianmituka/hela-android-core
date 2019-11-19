package com.creativeconsillium.drumsforafrica.helaapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.creativeconsillium.drumsforafrica.helaapp.Activity.utils.FirebaseUtils;
import com.creativeconsillium.drumsforafrica.helaapp.Activity.utils.PermissionsUtils;
import com.creativeconsillium.drumsforafrica.helaapp.Activity.utils.SmsUtils;
import com.creativeconsillium.drumsforafrica.helaapp.R;

public class SplashActivity extends AppCompatActivity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 2000;
    PermissionsUtils permissionsUtils = new PermissionsUtils();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Boolean permissions = permissionsUtils.checkPermissions(SplashActivity.this);
        System.out.println(">>>>>>> " + permissions);
    if (permissions && !FirebaseUtils.isLoggedIn()){
        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashActivity.this, SplashChoiceActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);

    } else if (permissions && FirebaseUtils.isLoggedIn()){

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                SmsUtils.getMpesaMessages(getApplicationContext());
                openHome();
            }
        }, SPLASH_DISPLAY_LENGTH);


    } else {
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                goToPermissionsActivity();
            }
        }, SPLASH_DISPLAY_LENGTH);

    }



    }
   void goToPermissionsActivity () {
       Intent permissionsIntent = new Intent(SplashActivity.this, AskPermissionActivity.class);
       SplashActivity.this.startActivity(permissionsIntent);
       SplashActivity.this.finish();
   }

    public void openHome () {
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
        this.finish();
    }


}
