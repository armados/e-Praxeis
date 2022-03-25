package com.armados.app.epraxeis;

public class OrganizationHelper {

    static public String getStatusText(String status) {

        switch (status) {
            case OrganizationStatus.ACTIVE:
                // Ενεργός και ενταγμένος στη Δι@ύγεια
                return "Ενεργός φορέας";
            case OrganizationStatus.INACTIVE:
                // Ενταγμένος στη Δι@ύγεια αλλά δεν είναι πλέον σε ισχύ
                return "Ανενεργός φορέας";
            case OrganizationStatus.PENDING:
                // Εκκρεμεί η ενεργοποίησή του και η ένταξή του στη Δι@ύγεια
                return "Εκκρεμεί";
            default:
                return null;
        }
    }

}
