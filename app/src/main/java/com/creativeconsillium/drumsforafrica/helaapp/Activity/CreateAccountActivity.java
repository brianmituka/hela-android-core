package com.creativeconsillium.drumsforafrica.helaapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.creativeconsillium.drumsforafrica.helaapp.R;

public class CreateAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }


    /* Create an Intent that will start the Choose-Budget-Activity. */
    public void openBudgetChoice (View view) {
        Intent i = new Intent(this, SignupBudgetChoiceActivity.class);
        startActivity(i);
        this.finish();
    }


    /* Create an Intent that will start the Splash-Choice-Activity. */
    public void backToSplashChoice (View view) {
        Intent i = new Intent(this, SplashChoiceActivity.class);
        startActivity(i);
        this.finish();
    }
}
