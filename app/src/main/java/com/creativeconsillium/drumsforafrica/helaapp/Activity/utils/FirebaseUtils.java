package com.creativeconsillium.drumsforafrica.helaapp.Activity.utils;

import android.app.Activity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;

public class FirebaseUtils {
    public static FirebaseAuth firebaseAuth;

    public static void createAccount(final Activity activity, EditText email, EditText password){
        System.out.println("Hotooooooo");
        if (!isEmailAndPasswordValid(email, password)){
            return;
        }else {
            //verify email, passwords etc
            firebaseAuth = FirebaseAuth.getInstance();
            firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){

                        Toast.makeText(activity, "Account successfully created",
                                Toast.LENGTH_SHORT).show();

                    }else{
                        System.out.println("ERROR::" + task.getException());
                        Toast.makeText(activity, "Account creation failed",
                                Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

    }
    public static boolean isEmailAndPasswordValid(EditText email, EditText password){
       boolean isValid = true;
        String emailString = email.getText().toString();
        if (TextUtils.isEmpty(emailString)) {
            email.setError("Required.");
            isValid = false;
        } else {
            email.setError(null);
        }

        String passwordString = password.getText().toString();
        if (TextUtils.isEmpty(passwordString)) {
            password.setError("Required.");
            isValid = false;
        } else {
            password.setError(null);
        }


       return isValid;
    }
    public static boolean isLoggedIn(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser == null){
            return false;
        }else {
            return true;
        }
    }
    public static void saveUserDetails (){

    }

}
