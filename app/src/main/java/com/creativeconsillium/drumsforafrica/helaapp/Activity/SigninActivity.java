package com.creativeconsillium.drumsforafrica.helaapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.creativeconsillium.drumsforafrica.helaapp.R;

public class SigninActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
    }


    /* Create an Intent that will start the Splash-Choice-Activity. */
    public void backToSplashChoice (View view) {
        Intent i = new Intent(this, SplashChoiceActivity.class);
        startActivity(i);
        this.finish();
    }


    /* Create an Intent that will start the Home-Activity. */
    public void openHome (View view) {
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
        this.finish();
    }
}
