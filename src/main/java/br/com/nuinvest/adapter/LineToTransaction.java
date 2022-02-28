package br.com.nuinvest.adapter;

import br.com.nuinvest.model.Transaction;
import java.math.BigDecimal;
import java.time.LocalDate;

import static br.com.nuinvest.util.BasicConvertor.*;

public class LineToTransaction {
    public static Transaction convert(String line) {
        String[] split = line.split(";");
        String code = split[5];

        if (code.equals("200")) {
            LocalDate date = convertToDate(split[1]);
            BigDecimal value = convertToValue(split[3]);
            Integer quantity = convertToInteger(split[2].substring(14, 33));
            String stock = split[2].substring(39, 45);
            return new Transaction(date, stock, quantity, value, code);
        }
        return null;
    }
}
