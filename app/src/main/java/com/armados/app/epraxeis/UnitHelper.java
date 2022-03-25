package com.armados.app.epraxeis;

public class UnitHelper {

    static public String getActiveText(final boolean active) {

        if (active) {
            // Ενεργή μονάδα
            return "Ενεργή μονάδα";
        } else {
            // Ανενεργή μονάδα
            return "Ανενεργή μονάδα";
        }
    }

}
