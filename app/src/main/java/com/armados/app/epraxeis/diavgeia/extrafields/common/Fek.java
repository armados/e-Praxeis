package com.armados.app.epraxeis.diavgeia.extrafields.common;

import java.io.Serializable;

/* Στοιχεία ΦΕΚ */
public class Fek  implements Serializable {
    /* Αρ. ΦΕΚ */
    private String aa;
    /* Τεύχος */
    private String issue;
    /* Έτος έκδοσης */
    private int issueyear;

    public String getAa() {
        return aa;
    }

    public void setAa(String aa) {
        this.aa = aa;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public int getIssueyear() {
        return issueyear;
    }

    public void setIssueyear(int issueyear) {
        this.issueyear = issueyear;
    }
}
