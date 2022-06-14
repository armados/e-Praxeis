package com.armados.app.epraxeis;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class DateTimeFormatters {

    /** DD/MM/YYYY formatter for dates */
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy", new Locale("el"));

    /** DD/MM/YYYY HH:MM:SS formatter for timestamps */
    public static final SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("el"));

    /** DD/MM/YYYY HH:MM:SS formatter for timestamps */
    public static final SimpleDateFormat HEADER_DATE_FORMAT = new SimpleDateFormat("EEEE d MMMM", new Locale("el"));
}
