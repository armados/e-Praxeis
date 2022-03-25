package com.armados.app.epraxeis;

public class SignerHelper {

    public static String getActiveText(boolean active) {
        // Ένδειξη για το αν η μονάδα ο υπογράφων είναι εν ενεργεία
        if (active) {
            // Ο υπογράφων είναι εν ενεργεία
            return "Εν ενεργεία υπογράφων";
        } else {
            // Ανενεργός υπογράφων
            return "Ανενεργός υπογράφων";
        }
    }

    public static String getHasOrganizationSignRightsText(boolean active) {
        // Ένδειξη για το αν ο υπογράφων έχει δικαίωμα υπογραφής σε όλες τις μονάδες του φορέα του
        if (active) {
            // Έχει δικαίωμα υπογραφής σε όλες τις μονάδες του φορέα
            return "Έχει δικαίωμα υπογραφής σε όλες τις μονάδες του φορέα που ανήκει";
        } else {
            // Δεν έχει δικαίωμα υπογραφής σε όλες τις μονάδες του φορέα
            return "";
        }
    }

    public static String getFullNameText(String lastname, String firstname) {
        return String.format("%s %s", lastname, firstname);
    }

}
