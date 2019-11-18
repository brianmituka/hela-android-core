package com.creativeconsillium.drumsforafrica.helaapp.Activity.Model;

public class ModelBudgets {

    private String budgetCategory;
    private String budgetNumber;
    private String budgetSpentAmount;
    private String budgetRemainingAmount;
    private String budgetType;

    public ModelBudgets() {
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


}
