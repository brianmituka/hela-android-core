package com.creativeconsillium.drumsforafrica.helaapp.Activity.utils;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.creativeconsillium.drumsforafrica.helaapp.Activity.CreateAccountActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import androidx.annotation.NonNull;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class FirebaseUtils {
    public static FirebaseAuth firebaseAuth;

    public static void createAccount(final Activity activity, EditText email, EditText password, EditText name, EditText mobile){
        System.out.println("Hotooooooo");
        final String nameString = name.getText().toString();
        UiUtils.showDialog("Creating account", activity);
        if (!isEmailAndPasswordValid(email, password)){
            UiUtils.hideDialog();
            return;
        }else {
            //verify email, passwords etc
            firebaseAuth = FirebaseAuth.getInstance();
            firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        System.out.println("Results::::" + task.getResult());
                        updateUserName(nameString);

                        UiUtils.hideDialog();
                        Toast.makeText(activity, "Account successfully created",
                                Toast.LENGTH_SHORT).show();

                    }else{
                        System.out.println("ERROR::" + task.getException());
                        UiUtils.hideDialog();
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
    public static void saveUserInformation(EditText phoneNumber, String UserId){
        String phoneNumberString = phoneNumber.getText().toString();
        //Call firebase database and store the phoneNumber in the user_details table together
        // the userID

    }

    public static void updateUserName(String name){
        final FirebaseUser user = getCurrentUser();
        UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                .setDisplayName(name).build();
        user.updateProfile(profileChangeRequest).addOnCompleteListener( new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "Username Added " +  user.getDisplayName());
                } else {
                    Log.i(TAG,  "ERROR::: " + task.getException().getMessage());
                }
            }
        });

    }

    //get current User
    public static FirebaseUser getCurrentUser(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Log.i(TAG, "EMAIL IS" + user.getEmail());
        return user;
    }

    public static boolean isLoggedIn(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser == null){
            return false;
        }else {
            System.out.println("CurrentUser >>" + firebaseUser);
            return true;
        }
    }
    public static void saveUserDetails (){

    }

}
