package com.creativeconsillium.drumsforafrica.helaapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.creativeconsillium.drumsforafrica.helaapp.R;

public class SignupBudgetAmountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_budget_amount);
    }

    /* Create an Intent that will start the Budget-Amount-Activity. */
    public void openBudgetRepeat (View view) {
        Intent i = new Intent(this, SignupBudgetRepeatActivity.class);
        startActivity(i);
        this.finish();
    }


    /* Create an Intent that will start the Choose-Budget-Activity. */
    public void backToSignupBudgetChoice (View view) {
        Intent i = new Intent(this, SignupBudgetChoiceActivity.class);
        startActivity(i);
        this.finish();
    }
}

