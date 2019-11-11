package com.creativeconsillium.drumsforafrica.helaapp.Activity.Model;

public class ModelHomeSummaryMain {

    private String homeSummaryYear, homeSummaryAction, homeSummaryReceivedAmount, homeSummarySpentAmount;

    public ModelHomeSummaryMain(String homeSummaryYear, String homeSummaryAction, String homeSummaryReceivedAmount, String homeSummarySpentAmount) {
        this.homeSummaryYear = homeSummaryYear;
        this.homeSummaryAction = homeSummaryAction;
        this.homeSummaryReceivedAmount = homeSummaryReceivedAmount;
        this.homeSummarySpentAmount = homeSummarySpentAmount;
    }

    public String getHomeSummaryYear() { return homeSummaryYear; }
    public String getHomeSummaryAction() { return homeSummaryAction; }
    public String getHomeSummaryReceivedAmount() { return homeSummaryReceivedAmount; }
    public String getHomeSummarySpentAmount() { return homeSummarySpentAmount; }


}
