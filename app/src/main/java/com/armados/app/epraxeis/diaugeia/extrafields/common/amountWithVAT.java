package com.armados.app.epraxeis.diaugeia.extrafields.common;

import java.io.Serializable;

/* Συνολικό ποσό */
public class amountWithVAT implements Serializable {
    /* Ποσό με ΦΠΑ */
    private double amount;
    /* Κωδικός νομισματικής μονάδας */
    private String currency;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
