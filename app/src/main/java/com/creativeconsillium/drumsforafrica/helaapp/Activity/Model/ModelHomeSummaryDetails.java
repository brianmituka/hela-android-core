package com.creativeconsillium.drumsforafrica.helaapp.Activity.Model;

public class ModelHomeSummaryDetails {

    private String homeSummaryDetailMonth, homeSummaryDetailReceived, homeSummaryDetailSpent;

    public ModelHomeSummaryDetails(String homeSummaryDetailMonth, String homeSummaryDetailReceived, String homeSummaryDetailSpent) {
        this.homeSummaryDetailMonth = homeSummaryDetailMonth;
        this.homeSummaryDetailReceived = homeSummaryDetailReceived;
        this.homeSummaryDetailSpent = homeSummaryDetailSpent;
    }

    public String getHomeSummaryDetailMonth() { return homeSummaryDetailMonth; }
    public String getHomeSummaryDetailReceived() { return homeSummaryDetailReceived; }
    public String getHomeSummaryDetailSpent() { return homeSummaryDetailSpent; }


}
