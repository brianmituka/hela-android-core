package com.creativeconsillium.drumsforafrica.helaapp.Activity.utils;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.creativeconsillium.drumsforafrica.helaapp.Activity.CreateAccountActivity;
import com.creativeconsillium.drumsforafrica.helaapp.Activity.Model.User;
import com.creativeconsillium.drumsforafrica.helaapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class FirebaseUtils {

    public static FirebaseAuth firebaseAuth;


    //   Initialize database and access write-location
    public static DatabaseReference createOrGetDatabaseRef(String node) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(node);
        return reference;
    }


    public static void createAccount(final Activity activity, EditText email, EditText password, EditText name, final EditText phoneNumber) {
        final String nameString = name.getText().toString();
        UiUtils.showDialog("Creating account", activity);
        if (!isEmailAndPasswordValid(email, password)) {
            UiUtils.hideDialog();
            return;
        } else {
            //verify email, passwords etc
            firebaseAuth = FirebaseAuth.getInstance();
            firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        System.out.println("Results::::" + task.getResult());
                        updateUserName(nameString);
                        saveUserInformation(phoneNumber);

                        UiUtils.hideDialog();
                        Toast.makeText(activity, "Account successfully created",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Log.e(TAG, "ERROR " + task.getException().getMessage());
                        String errorText = task.getException().getMessage();
                        UiUtils.hideDialog();
                        Toast.makeText(activity, errorText,
                                Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

    }

    public static boolean isEmailAndPasswordValid(EditText email, EditText password) {
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

    //Save additional user
    public static void saveUserInformation(EditText phoneNumber) {
        String userID = getCurrentUser().getUid();
        String phoneNumberString = phoneNumber.getText().toString();
        Log.i(TAG, "Saving " + getCurrentUser().getDisplayName() + "Phone Number");
        User user = new User(phoneNumberString);
        DatabaseReference userDetailsReference = createOrGetDatabaseRef("user_details");

        userDetailsReference.child(userID).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.i(TAG, "SuccessFully saved " + getCurrentUser().getDisplayName() + "Phone Number");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i(TAG, "Saving Failed For " + getCurrentUser().getDisplayName() + "Phone Number");
                return;
            }
        });

        //Call firebase database and store the phoneNumber in the user_details table together
        // the userID

    }

    public static void updateUserName(String name) {
        final FirebaseUser user = getCurrentUser();
        UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                .setDisplayName(name).build();
        user.updateProfile(profileChangeRequest).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.i(TAG, "Username Added " + user.getDisplayName());
                } else {
                    Log.i(TAG, "ERROR::: " + task.getException().getMessage());
                }
            }
        });

    }


    //get current User
    public static FirebaseUser getCurrentUser() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Log.i(TAG, "EMAIL IS" + user.getEmail());
        return user;
    }

    //Update user profile
    public static void updateUserProfile(final Activity activity, EditText name, EditText phone, EditText email){
        String nameString  = name.getText().toString();
        String phoneNumberString = phone.getText().toString();
        String emailString = email.getText().toString();
        UiUtils.showDialog("Updating changes", activity);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
//        firebaseUser.updateProfile()


    }


    public static boolean isLoggedIn() {
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser == null) {
            return false;
        } else {
            System.out.println("CurrentUser >>" + firebaseUser.getDisplayName());
            return true;
        }
    }

    public static void loginIntoApp(final Activity activity, EditText email, EditText password) {

        String emailString = email.getText().toString();
        String passwordString = password.getText().toString();
//        UiUtils.showDialog("Signing In", activity);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(emailString, passwordString).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                UiUtils.hideDialog();
                   ;
                    Toast.makeText(activity, "Login successful ",
                            Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "Login successful");

                }else {
                //    UiUtils.hideDialog();
                    String errorText = task.getException().getMessage();
//                    UiUtils.hideDialog();
                    Toast.makeText(activity, errorText,
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public static void resetPassword(final Activity activity, EditText email) {
        UiUtils.showDialog("Sending Password reset email", activity);
        String emailString = email.getText().toString();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.sendPasswordResetEmail(emailString)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                    UiUtils.hideDialog();

                    Toast.makeText(activity, "An email has been sent to your email ",
                            Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "Password reset successful");

                }else {
                    UiUtils.hideDialog();
                    String errorText = task.getException().getMessage();
                    UiUtils.hideDialog();
                    Toast.makeText(activity, errorText,
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}