package com.creativeconsillium.drumsforafrica.helaapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.creativeconsillium.drumsforafrica.helaapp.Activity.Model.User;
import com.creativeconsillium.drumsforafrica.helaapp.Activity.utils.FirebaseUtils;
import com.creativeconsillium.drumsforafrica.helaapp.Activity.utils.UiUtils;
import com.creativeconsillium.drumsforafrica.helaapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateAccountActivity extends AppCompatActivity {
    private static final String TAG = HomeActivity.class.getSimpleName();
    private EditText name;
    private EditText phone;
    private EditText email;
    private EditText password;
    private Button btnSignupSave;
    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        btnSignupSave = (Button) findViewById(R.id.btnSignupSave);
        name = (EditText) findViewById(R.id.editText);
        phone = (EditText) findViewById(R.id.editText2);
        email = (EditText) findViewById(R.id.editText4);
        password = (EditText) findViewById(R.id.editText5);
        btnSignupSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseUtils.createAccount(CreateAccountActivity.this,email, password);
                //UiUtils.hideDialog();
                //openBudgetChoice();
            }
        });
    }
    /* Create an Intent that will start the Choose-Budget-Activity. */
    public void openBudgetChoice () {
        Intent i = new Intent(this, SignupBudgetChoiceActivity.class);
        startActivity(i);
        this.finish();
    }
    /* Create an Intent that will start the Splash-Choice-Activity. */
    public void backToSplashChoice (View v) {
        Intent i = new Intent(this, SplashChoiceActivity.class);
        startActivity(i);
        this.finish();
    }
}
