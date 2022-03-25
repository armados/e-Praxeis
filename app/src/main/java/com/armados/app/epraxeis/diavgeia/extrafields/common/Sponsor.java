package com.armados.app.epraxeis.diavgeia.extrafields.common;

/* Στοιχεία πληρωμών αναδόχου */
public class Sponsor {
    /* Στοιχεία αναδόχου */
    private Person sponsorAFMName;
    /* Ποσό πληρωμής */
    private amountWithVAT expenseAmount;
    /* Κωδικός CPV (τιμές από Λεξικό CPV) */
    private String cpv;
    /* Κωδικός Αριθμός Εξόδου */
    private String kae;

    public Person getSponsorAFMName() {
        return sponsorAFMName;
    }

    public void setSponsorAFMName(Person sponsorAFMName) {
        this.sponsorAFMName = sponsorAFMName;
    }

    public amountWithVAT getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(amountWithVAT expenseAmountWithVAT) {
        this.expenseAmount = expenseAmountWithVAT;
    }

    public String getCpv() {
        return cpv;
    }

    public void setCpv(String cpv) {
        this.cpv = cpv;
    }

    public String getKae() {
        return kae;
    }

    public void setKae(String kae) {
        this.kae = kae;
    }
}
