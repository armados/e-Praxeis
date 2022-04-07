package com.armados.app.epraxeis.diaugeia.extrafields.common;

/* Στοιχεία αναδόχου με Κωδικό Αριθμό Εξόδου και Συνολικό ποσό */
public class AmountWithKae {
    /*  ΑΦΜ / Επωνυμία αναδόχου */
    private Person sponsorAFMName;
    /* Κωδικός Αριθμός Εξόδου */
    private String kae;
    /* Συνολικό ποσό */
    private double amountWithVAT;
    /* Υπόλοιπο διαθέσιμης πίστωσης */
    private double kaeCreditRemainder;
    /* Υπόλοιπο ΚΑΕ/ΑΛΕ */
    private double kaeBudgetRemainder;

    public Person getSponsorAFMName() {
        return sponsorAFMName;
    }

    public void setSponsorAFMName(Person sponsorAFMName) {
        this.sponsorAFMName = sponsorAFMName;
    }

    public String getKae() {
        return kae;
    }

    public void setKae(String kae) {
        this.kae = kae;
    }

    public double getAmountWithVAT() {
        return amountWithVAT;
    }

    public void setAmountWithVAT(double amountWithVAT) {
        this.amountWithVAT = amountWithVAT;
    }

    public double getKaeCreditRemainder() {
        return kaeCreditRemainder;
    }

    public void setKaeCreditRemainder(double kaeCreditRemainder) {
        this.kaeCreditRemainder = kaeCreditRemainder;
    }

    public double getKaeBudgetRemainder() {
        return kaeBudgetRemainder;
    }

    public void setKaeBudgetRemainder(double kaeBudgetRemainder) {
        this.kaeBudgetRemainder = kaeBudgetRemainder;
    }
}
