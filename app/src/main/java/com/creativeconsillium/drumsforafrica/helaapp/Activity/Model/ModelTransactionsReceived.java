package com.creativeconsillium.drumsforafrica.helaapp.Activity.Model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class ModelTransactionsReceived {

    private String transactionDate, transactionAmount, transactionPerson, transactionType;

    public ModelTransactionsReceived() {
    }

    public ModelTransactionsReceived(String transactionDate, String transactionAmount, String transactionPerson, String transactionType) {
        this.transactionDate = transactionDate;
        this.transactionAmount = transactionAmount;
        this.transactionPerson = transactionPerson;
        this.transactionType = transactionType;
    }

    public String getTransactionDate() { return transactionDate; }
    public String getTransactionAmount() { return transactionAmount; }
    public String getTransactionPerson() { return transactionPerson; }
    public String getTransactionType() { return transactionType; }




}
