package com.creativeconsillium.drumsforafrica.helaapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.creativeconsillium.drumsforafrica.helaapp.Activity.utils.FirebaseUtils;
import com.creativeconsillium.drumsforafrica.helaapp.R;

public class ResetPasswordActivity extends AppCompatActivity {
private   EditText email;
private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        final EditText email = (EditText) findViewById(R.id.reset);
        Button button = (Button) findViewById(R.id.btnReset);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUtils.resetPassword(ResetPasswordActivity.this,email);

            }
        });
    }


}
