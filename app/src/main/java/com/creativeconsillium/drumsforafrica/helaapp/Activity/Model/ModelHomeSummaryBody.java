package com.creativeconsillium.drumsforafrica.helaapp.Activity.Model;

import java.math.BigDecimal;

/**
 * Model class for Home Summary - Body section data.
 *
 * Created by Edward N. Ndukui,
 * on Thursday, 31st/October/2019,
 * at 6:43PM.
 */
public class ModelHomeSummaryBody {

    private String sMonthName;

    private BigDecimal dAmountReceived;
    private BigDecimal dAmountSpent;


    public ModelHomeSummaryBody() {}


    public String getsMonthName() {
        return sMonthName;
    }

    public void setsMonthName(String sMonthName) {
        this.sMonthName = sMonthName;
    }

    public BigDecimal getdAmountReceived() {
        return dAmountReceived;
    }

    public void setdAmountReceived(BigDecimal dAmountReceived) {
        this.dAmountReceived = dAmountReceived;
    }

    public BigDecimal getdAmountSpent() {
        return dAmountSpent;
    }

    public void setdAmountSpent(BigDecimal dAmountSpent) {
        this.dAmountSpent = dAmountSpent;
    }
}
