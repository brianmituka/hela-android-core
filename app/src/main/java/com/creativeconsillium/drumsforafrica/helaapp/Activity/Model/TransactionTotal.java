package com.creativeconsillium.drumsforafrica.helaapp.Activity.Model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class TransactionTotal {
    String monthSpent;
    String monthReceived;
    String totalSpent;
    String totalReceived;

    public TransactionTotal() {
    }

    public TransactionTotal(String monthSpent, String monthReceived, String totalSpent, String totalReceived) {
        this.monthSpent = monthSpent;
        this.monthReceived = monthReceived;
        this.totalSpent = totalSpent;
        this.totalReceived = totalReceived;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> transactionSummary = new HashMap<>();
        transactionSummary.put("monthSpent", monthSpent);
        transactionSummary.put("monthReceived", monthReceived);
        transactionSummary.put("totalSpent", totalSpent);
        transactionSummary.put("totalReceived", totalReceived);

        return transactionSummary;
    }
}
