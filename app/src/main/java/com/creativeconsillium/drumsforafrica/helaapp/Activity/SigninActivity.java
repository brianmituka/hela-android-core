package com.creativeconsillium.drumsforafrica.helaapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.creativeconsillium.drumsforafrica.helaapp.Activity.utils.FirebaseUtils;
import com.creativeconsillium.drumsforafrica.helaapp.R;

public class SigninActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        email = (EditText) findViewById(R.id.editText8);
        password = (EditText) findViewById(R.id.editText9);
        button = (Button) findViewById(R.id.button4);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUtils.loginIntoApp(SigninActivity.this,email, password);
                openHome();
            }
        });

    }





    /* Create an Intent that will start the Splash-Choice-Activity. */
    public void backToSplashChoice (View view) {
        Intent i = new Intent(this, SplashChoiceActivity.class);
        startActivity(i);
        this.finish();
    }


    /* Create an Intent that will start the Home-Activity. */
    public void openHome () {
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
        this.finish();
    }
}
