package com.creativeconsillium.drumsforafrica.helaapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.creativeconsillium.drumsforafrica.helaapp.R;

public class SignupBudgetChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_budget_choice);
    }

    /* Create an Intent that will start the Budget-Amount-Activity. */
    public void openBudgetAmount (View view) {
        Intent i = new Intent(this, SignupBudgetAmountActivity.class);
        startActivity(i);
        this.finish();
    }


    /* Create an Intent that will start the Create-Account-Activity. */
    public void backToCreateAccount (View view) {
        Intent i = new Intent(this, CreateAccountActivity.class);
        startActivity(i);
        this.finish();
    }
}

