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
    private String budgetName;
    private boolean isRecurring;
    private String budgetAmount;
    private String budgetMonth;

    public ModelBudgets() {
    }

    public ModelBudgets(String budgetCategory, String budgetNumber, String budgetSpentAmount, String budgetRemainingAmount, boolean isRecurring, String budgetAmount, String budgetMonth) {
        this.budgetCategory = budgetCategory;
        this.budgetNumber = budgetNumber;
        this.budgetSpentAmount = budgetSpentAmount;
        this.budgetRemainingAmount = budgetRemainingAmount;
        this.isRecurring = isRecurring;
        this.budgetAmount = budgetAmount;
        this.budgetMonth = budgetMonth;
    }

    public ModelBudgets(String budgetName, boolean isRecurring, String budgetAmount, String budgetNumber, String budgetRemainingAmount) {
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

    public String getBudgetName() {
        return budgetName;
    }

    public String getBudgetMonth() {
        return budgetMonth;
    }

    public String getBudgetAmount() {
        return budgetAmount;
    }

    public void setBudgetCategory(String budgetCategory) {
        this.budgetCategory = budgetCategory;
    }

    public void setBudgetNumber(String budgetNumber) {
        this.budgetNumber = budgetNumber;
    }

    public void setBudgetSpentAmount(String budgetSpentAmount) {
        this.budgetSpentAmount = budgetSpentAmount;
    }

    public void setBudgetRemainingAmount(String budgetRemainingAmount) {
        this.budgetRemainingAmount = budgetRemainingAmount;
    }

    public void setBudgetName(String budgetName) {
        this.budgetName = budgetName;
    }

    public boolean isRecurring() {
        return isRecurring;
    }

    public void setRecurring(boolean recurring) {
        isRecurring = recurring;
    }

    public void setBudgetAmount(String budgetAmount) {
        this.budgetAmount = budgetAmount;
    }

    public void setBudgetMonth(String budgetMonth) {
        this.budgetMonth = budgetMonth;
    }

    public String getBudgetCategory() { return budgetCategory; }
    public String getBudgetNumber() { return budgetNumber; }
    public String getBudgetSpentAmount() { return budgetSpentAmount; }
    public String getBudgetRemainingAmount() { return budgetRemainingAmount; }
    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> budget = new HashMap<>();
        budget.put("budgetCategory", budgetCategory);
        budget.put("budgetAmount", budgetAmount);
        budget.put("isRecurring", isRecurring);
        budget.put("budgetMonth", budgetMonth);
        budget.put("budgetSpentAmount", budgetSpentAmount);
        budget.put("budgetRemainingAmount", budgetRemainingAmount);
        budget.put("budgetNumber", budgetNumber);
        return budget;
    }


}
