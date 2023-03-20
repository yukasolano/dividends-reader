package br.com.dividends.adapter;

import br.com.dividends.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;

import static br.com.dividends.util.BasicConvertor.*;

public class NuInvestLineToTransaction implements LineToTransaction {
    public Transaction convert(String line) {
        String[] split = line.split(";");
        String code = split[5];

        if (code.equals("200")) {
            LocalDate date = convertToDate(split[1]);
            BigDecimal value = convertBrlToValue(split[3]);
            Integer quantity = convertToInteger(split[2].substring(14, 33));
            String stock = split[2].substring(33, 46).replace("ACOES ", "").trim();
            return new Transaction(date, stock, quantity, value, code);
        }
        return null;
    }
}
