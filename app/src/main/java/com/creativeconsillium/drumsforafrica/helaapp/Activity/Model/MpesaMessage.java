package com.creativeconsillium.drumsforafrica.helaapp.Activity.Model;

import java.math.BigDecimal;
import org.joda.time.LocalDate;

public class MpesaMessage {
private String transactionCode;
private BigDecimal amount;
private LocalDate date;
private String transactionTyp;

    public MpesaMessage() {
    }

    public MpesaMessage(String transactionCode, BigDecimal amount, LocalDate date, String transactionTyp) {
        this.transactionCode = transactionCode;
        this.amount = amount;
        this.date = date;
        this.transactionTyp = transactionTyp;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTransactionTyp() {
        return transactionTyp;
    }

    public void setTransactionTyp(String transactionTyp) {
        this.transactionTyp = transactionTyp;
    }
}
