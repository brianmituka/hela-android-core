package com.creativeconsillium.drumsforafrica.helaapp.Activity.Model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.LocalDate;

@IgnoreExtraProperties
public class MpesaMessage {
private String transactionCode;
private String amount;
private String date;
private String transactionTyp;
private String name;


    public MpesaMessage() {
    }

    public MpesaMessage(String transactionCode, String amount, String date, String transactionTyp, String name) {
        this.transactionCode = transactionCode;
        this.amount = amount;
        this.date = date;
        this.transactionTyp = transactionTyp;
        this.name = name;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public String getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getTransactionTyp() {
        return transactionTyp;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTransactionTyp(String transactionTyp) {
        this.transactionTyp = transactionTyp;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * This field is excluded from the database
     */
    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> transaction = new HashMap<>();
        transaction.put("transactionCode", transactionCode);
        transaction.put("amount", amount);
        transaction.put("date", date);
        transaction.put("transactionType", transactionTyp);
        transaction.put("name", name);

        return transaction;
    }

    @Exclude
    @Override
    public String toString() {
        return "MpesaMessage{" +
                "transactionCode='" + transactionCode + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", transactionTyp='" + transactionTyp + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
