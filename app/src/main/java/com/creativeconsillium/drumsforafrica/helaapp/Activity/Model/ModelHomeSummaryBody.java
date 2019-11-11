package com.creativeconsillium.drumsforafrica.helaapp.Activity.Model;

/**
 * Model class for Home Summary - Body section data.
 *
 * Created by Edward N. Ndukui,
 * on Thursday, 31st/October/2019,
 * at 6:43PM.
 */
public class ModelHomeSummaryBody {

    private String sMonthName;

    private double dAmountReceived;
    private double dAmountSpent;


    public ModelHomeSummaryBody() {}


    public String getsMonthName() {
        return sMonthName;
    }

    public void setsMonthName(String sMonthName) {
        this.sMonthName = sMonthName;
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

}
