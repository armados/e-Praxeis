package com.armados.app.epraxeis;

public class DecisionHelper {

    static public String getStatusText(final String status) {
        switch (status) {
            case DecisionStatus.PUBLISHED:
                return "Αναρτηθείσα";
            case DecisionStatus.PENDING_REVOCATION:
                return "Εκκρεμεί αίτημα ανάκλησης";
            case DecisionStatus.REVOKED:
                return "Ανακληθείσα";
            case DecisionStatus.SUBMITTED:
                return "Έχει καταχωρηθεί";
            default:
                return null;
        }
    }
}
