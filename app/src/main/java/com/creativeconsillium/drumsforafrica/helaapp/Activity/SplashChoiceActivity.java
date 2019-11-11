package com.creativeconsillium.drumsforafrica.helaapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.creativeconsillium.drumsforafrica.helaapp.R;

public class SplashChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_choice);
    }


    /* Create an Intent that will start the Signin-Activity. */
    public void openSignin (View view) {
        Intent i = new Intent(this, SigninActivity.class);
        startActivity(i);
        this.finish();
    }



    /* Create an Intent that will start the Create-Account-Activity. */
    public void openCreateAccount (View view) {
        Intent i = new Intent(this, CreateAccountActivity.class);
        startActivity(i);
        this.finish();
    }
}
