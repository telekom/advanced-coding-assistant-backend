package com.telekom.ai4coding.chatbot.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * DoubleFormatter is a utility class for formatting double values to strings
 * with a specific decimal format.
 *
 * This class ensures that double values are always formatted with a period ('.')
 * as the decimal separator, regardless of the system's locale settings.
 *
 * The formatter is configured to always display two decimal places.
 *
 * Usage example:
 * double value = 3.14159;
 * String formattedValue = DoubleFormatter.format(value); // Returns "3.14"
 */
public class DoubleFormatter {
    private static final DecimalFormat df;

    static {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        symbols.setDecimalSeparator('.');
        df = new DecimalFormat("0.00", symbols);
        df.setMinimumFractionDigits(2);
        df.setMaximumFractionDigits(2);
    }

    public static String format(double value) {
        return df.format(value);
    }
}
