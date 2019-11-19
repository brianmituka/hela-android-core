package com.creativeconsillium.drumsforafrica.helaapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.creativeconsillium.drumsforafrica.helaapp.Activity.utils.FirebaseUtils;
import com.creativeconsillium.drumsforafrica.helaapp.R;

public class SettingsProfileActivity extends AppCompatActivity {
    private EditText name;
    private EditText phone;
    private EditText email;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        name = (EditText) findViewById(R.id.editText);
        phone = (EditText) findViewById(R.id.editText2);
        email = (EditText) findViewById(R.id.editText4);
        button = (Button) findViewById(R.id.button7);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseUtils.updateUserProfile(SettingsProfileActivity.this,name,phone,email);
                //UiUtils.hideDialog();
                //openBudgetChoice();
            }
        });
    }
}
