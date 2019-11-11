package com.creativeconsillium.drumsforafrica.helaapp.Activity.Model;

public class ModelTransactionsDate {

    private String transaction_date;

    private boolean boolIsCurrentDate;


    public ModelTransactionsDate(String transaction_date, boolean isCurrentDate) {

        this.transaction_date = transaction_date;

        this.boolIsCurrentDate = isCurrentDate;

    }


    public String getTransactionDate() {
        return transaction_date;
    }

    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }

    public boolean isBoolIsCurrentDate() {
        return boolIsCurrentDate;
    }

    public void setBoolIsCurrentDate(boolean boolIsCurrentDate) {
        this.boolIsCurrentDate = boolIsCurrentDate;
    }
}
