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
private  String message;

    public MpesaMessage() {
    }

    public MpesaMessage(String transactionCode, String amount, String date, String transactionTyp) {
        this.transactionCode = transactionCode;
        this.amount = amount;
        this.date = date;
        this.transactionTyp = transactionTyp;
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
                ", message='" + message + '\'' +
                '}';
    }
}
