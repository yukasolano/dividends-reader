package br.com.nuinvest.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BasicConvertor {
    public static LocalDate convertToDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dateString, formatter);
    }

    public static BigDecimal convertToValue(String valueString) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');
        String pattern = "#,##0.0#";
        DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
        decimalFormat.setParseBigDecimal(true);
        try {
            return (BigDecimal) decimalFormat.parse(valueString);
        } catch (ParseException e) {
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }

    public static Integer convertToInteger(String valueString) {
        return Integer.valueOf(valueString.trim().replace(".", ""));
    }
}
