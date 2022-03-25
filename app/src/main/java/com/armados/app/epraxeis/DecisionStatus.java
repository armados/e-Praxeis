package com.armados.app.epraxeis;

public class DecisionStatus {
    public static final String PUBLISHED = "PUBLISHED"; //Αναρτημένη και σε ισχύ
    public static final String PENDING_REVOCATION = "PENDING_REVOCATION"; //Εκκρεμεί αίτημα ανάκλησης
    public static final String REVOKED = "REVOKED"; //Ανακληθείσα
    public static final String SUBMITTED = "SUBMITTED"; //σε πράξεις που έχουν υποβληθεί αλλά δεν τους έχει αποδοθεί ΑΔΑ, η τιμή αυτού του πεδίου θα είναι SUBMITTED
}
