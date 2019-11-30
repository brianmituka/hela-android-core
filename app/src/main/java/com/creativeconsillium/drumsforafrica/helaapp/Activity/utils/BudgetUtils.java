package com.creativeconsillium.drumsforafrica.helaapp.Activity.utils;

import android.app.Activity;
import android.util.Log;
import android.widget.EditText;

import com.creativeconsillium.drumsforafrica.helaapp.Activity.Model.ModelBudgets;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

import androidx.annotation.NonNull;

public class BudgetUtils {
    //Create a budget
    //delete aa budget
    //edit budget
    //database reference to push to firebase
    public static String TAG = BudgetUtils.class.getSimpleName();

    public static DatabaseReference getUserBudgetReference (){
        String userId = FirebaseUtils.getCurrentUser().getUid();
        DatabaseReference budgetReference = FirebaseUtils.createOrGetDatabaseRef("budgets").child(userId);
        return budgetReference;
    }

    public static void createAndSaveBudget(ModelBudgets budget){
        final Map<String, Object> budgetValue = budget.toMap();

        getUserBudgetReference().push().updateChildren(budgetValue).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Log.i(TAG, "Budget Uploaded successfully " + budgetValue);
                }else {
                    Log.i(TAG, "An Error Occured " + task.getException());
                }

            }
        });
    }

    public static void getUserBudgets(){
        getUserBudgetReference().limitToFirst(20).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()){
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()) {

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public static void editBudget(String id, EditText budgetName, String budgetType ){}
    public static void deleteBudget(String id){}
}
