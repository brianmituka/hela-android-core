package com.creativeconsillium.drumsforafrica.helaapp.Activity.Model;

import java.util.ArrayList;

/**
 * Model class for Home Summary data.
 *
 * Created by Edward N. Ndukui,
 * on Thursday, 31st/October/2019,
 * at 6:23PM.
 */
public class ModelHomeSummary {

    private String sYear;

    private double dAmountReceived;
    private double dAmountSpent;

    private ArrayList<ModelHomeSummaryBody> arylHomeSummaryBodyItems;


    public ModelHomeSummary() {}


    public String getsYear() {
        return sYear;
    }

    public void setsYear(String sYear) {
        this.sYear = sYear;
    }

    public double getdAmountReceived() {
        return dAmountReceived;
    }

    public void setdAmountReceived(double dAmountReceived) {
        this.dAmountReceived = dAmountReceived;
    }

    public double getdAmountSpent() {
        return dAmountSpent;
    }

    public void setdAmountSpent(double dAmountSpent) {
        this.dAmountSpent = dAmountSpent;
    }

    public ArrayList<ModelHomeSummaryBody> getArylHomeSummaryBodyItems() {
        return arylHomeSummaryBodyItems;
    }

    public void setArylHomeSummaryBodyItems(ArrayList<ModelHomeSummaryBody> arylHomeSummaryBodyItems) {
        this.arylHomeSummaryBodyItems = arylHomeSummaryBodyItems;
    }

}
