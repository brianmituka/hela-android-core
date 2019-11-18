package com.creativeconsillium.drumsforafrica.helaapp.Activity.utils;

import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;

public class BudgetUtils {
    //Create a budget
    //delete aa budget
    //edit budget
    //database reference to push to firebase

    public static DatabaseReference getBudgetReference (){
        DatabaseReference budgetReference = FirebaseUtils.createDatabaseRef("budgets");
        return budgetReference;
    }

    public static void createAndSaveBudget(EditText budgetName, EditText budgetAmount){

    }
    public static void editBudget(String id, EditText budgetName, String budgetType ){}
    public static void deleteBudget(String id){}
}
