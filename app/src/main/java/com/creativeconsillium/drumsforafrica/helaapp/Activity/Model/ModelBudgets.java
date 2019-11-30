package com.creativeconsillium.drumsforafrica.helaapp.Activity.Model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class ModelBudgets {

    private String budgetCategory;
    private String budgetNumber;
    private String budgetSpentAmount;
    private String budgetRemainingAmount;
    private String budgetType;
    private String budgetName;
    private boolean isRecurring;
    private String budgetAmount;

    public ModelBudgets() {
    }


    public ModelBudgets(String budgetName, boolean isRecurring, String budgetAmount) {
        this.budgetName = budgetName;
        this.isRecurring = isRecurring;
        this.budgetAmount = budgetAmount;
    }

    public ModelBudgets(String budgetCategory, String budgetNumber, String budgetSpentAmount, String budgetRemainingAmount) {
        this.budgetCategory = budgetCategory;
        this.budgetNumber = budgetNumber;
        this.budgetSpentAmount = budgetSpentAmount;
        this.budgetRemainingAmount = budgetRemainingAmount;
    }

    public String getBudgetCategory() { return budgetCategory; }
    public String getBudgetNumber() { return budgetNumber; }
    public String getBudgetSpentAmount() { return budgetSpentAmount; }
    public String getBudgetRemainingAmount() { return budgetRemainingAmount; }
    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> budget = new HashMap<>();
        budget.put("budgetName", budgetName);
        budget.put("budgetAmount", budgetAmount);
        budget.put("isRecurring", isRecurring);


        return budget;
    }


}
