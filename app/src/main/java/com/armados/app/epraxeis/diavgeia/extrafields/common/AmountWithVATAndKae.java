package com.armados.app.epraxeis.diavgeia.extrafields.common;

/* Συνολικό ποσό με Κωδικό Αριθμό Εξόδου */
public class AmountWithVATAndKae {
    /* Κωδικός Αριθμός Εξόδου */
    private String kae;
    /* Συνολικό ποσό */
    private amountWithVAT amountWithVAT;

    public String getKae() {
        return kae;
    }

    public void setKae(String kae) {
        this.kae = kae;
    }

    public amountWithVAT getAmountWithVAT() {
        return amountWithVAT;
    }

    public void setAmountWithVAT(amountWithVAT amountWithVAT) {
        this.amountWithVAT = amountWithVAT;
    }
}
