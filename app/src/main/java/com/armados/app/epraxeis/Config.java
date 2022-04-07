package com.armados.app.epraxeis;

public final class Config {
    // Διάρκεια προβολής της οθόνης υποδοχής σε μιλιδευτερόλεπτα
    public static final int SPLASH_DISPLAY_LENGTH = 1700;

    // Διεύθυνση BASE_URL του API της Διαύγειας
    public static final String API_BASE_URL = "https://diavgeia.gov.gr/opendata/";

    // Για μη εγγεγραμμένους χρήστες το μέγιστο μέγεθος της σελίδας είναι 500 αποτελέσματα
    public static final int SEARCH_PAGE_SIZE = 100;

    // Στοιχεία της βάσης δεδομένων ROOM της εφαρμογής
    public static final String DATABASE_NAME = "db_watchlist";
    public static final String TABLE_NAME_STORE_DICTIONARY = "table_store_dictionary";
    public static final String TABLE_NAME_FAVORITES = "table_name_follow_list";

    // Κωδικοί λεξικών της Διαύγειας
    public static final String DICTIONARY_ORG_CATEGORY = "ORG_CATEGORY";
    public static final String DICTIONARY_ORG_UNIT_CATEGORY = "ORG_UNIT_CATEGORY";
    public static final String DICTIONARY_CURRENCIES = "CURRENCY";
    public static final String DICTIONARY_CPV = "CPV";

    // Κωδικοί αναφοράς για τη λίστα με τους φορείς και των τύπων πράξεων
    public static final String DECISION_TYPES = "DECISION_TYPES";
    public static final String ORGANIZATIONS = "ORG_LIST";
}
