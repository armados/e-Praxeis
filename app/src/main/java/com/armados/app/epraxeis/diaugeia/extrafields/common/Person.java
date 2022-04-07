package com.armados.app.epraxeis.diaugeia.extrafields.common;


/* Στοιχεία προσώπου στο οποίο γίνεται ανάθεση / Στοιχεία αναδόχου */
public class Person {
    /*  ΑΦΜ προσώπου / αναδόχου */
    private String afm;
    /* Τύπος ΑΦΜ προσώπου / αναδόχου (τιμές από Λεξικό Τύπων ΑΦΜ) */
    private String afmType;
    /* Κωδικός Κράτους-Μέλους της ΕΕ (τιμές από Λεξικό Κρατών-Μελών της ΕΕ) */
    private String afmCountry;
    /* Όνομα/Επωνυμία προσώπου/ Επωνυμία αναδόχου */
    private String name;
    /* Κωδικός φορέα χωρίς ΑΦΜ. Συμπληρώνεται σε περιπτώσεις φορέων χωρίς ΑΦΜ */
    private String noVATOrg;

    public String getAfm() {
        return afm;
    }

    public void setAfm(String afm) {
        this.afm = afm;
    }

    public String getAfmType() {
        return afmType;
    }

    public void setAfmType(String afmType) {
        this.afmType = afmType;
    }

    public String getAfmCountry() {
        return afmCountry;
    }

    public void setAfmCountry(String afmCountry) {
        this.afmCountry = afmCountry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNoVATOrg() {
        return noVATOrg;
    }

    public void setNoVATOrg(String noVATOrg) {
        this.noVATOrg = noVATOrg;
    }
}
