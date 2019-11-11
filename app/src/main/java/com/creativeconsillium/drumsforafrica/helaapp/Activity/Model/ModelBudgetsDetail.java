package com.creativeconsillium.drumsforafrica.helaapp.Activity.Model;

public class ModelBudgetsDetail {

    private String transactionSpentDate, transactionSpentBudget, transactionSpentAmount, transactionSpentPerson,
            transactionSpentType;

    public ModelBudgetsDetail(String transactionSpentDate, String transactionSpentBudget, String transactionSpentAmount,
                              String transactionSpentPerson, String transactionSpentType) {
        this.transactionSpentDate = transactionSpentDate;
        this.transactionSpentBudget = transactionSpentBudget;
        this.transactionSpentAmount = transactionSpentAmount;
        this.transactionSpentPerson = transactionSpentPerson;
        this.transactionSpentType = transactionSpentType;
    }

    public String getTransactionSpentDate() { return transactionSpentDate; }
    public String getTransactionSpentBudget() { return transactionSpentBudget; }
    public String getTransactionSpentAmount() { return transactionSpentAmount; }
    public String getTransactionSpentPerson() { return transactionSpentPerson; }
    public String getTransactionSpentType() { return transactionSpentType; }


}
