package com.creativeconsillium.drumsforafrica.helaapp.Activity.Model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class MonthSummary {
    private String amountReceived;
    private String amountSpent;

    public MonthSummary() {
    }

    public MonthSummary(String amountReceived, String amountSpent) {
        this.amountReceived = amountReceived;
        this.amountSpent = amountSpent;
    }
    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> monthSummary = new HashMap<>();
        monthSummary.put("monthSpent", amountSpent);
        monthSummary.put("monthReceived", amountReceived);


        return monthSummary;
    }
}
