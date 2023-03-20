package br.com.dividends.adapter;

import br.com.dividends.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;

import static br.com.dividends.util.BasicConvertor.*;

public class ModalLineToTransaction implements LineToTransaction {
    public Transaction convert(String line) {
        String[] split = line.split(" ");
        String code = split[1];

//        if (code.equals("RENDIMENTO")) {
//            LocalDate date = convertToDate(split[0]);
//            BigDecimal value = convertToValue(split[4]);
//            Integer quantity = 1;
//            String stock = split[2];
//            return new Transaction(date, stock, quantity, value, code);
//        }

//        if (code.equals("DIVIDENDO")) {
//            LocalDate date = convertToDate(split[0]);
//            BigDecimal value = convertToValue(split[4]);
//            Integer quantity = 1;
//            String stock = split[2];
//            return new Transaction(date, stock, quantity, value, code);
//        }

        if (code.equals("JUROS")) {
            LocalDate date = convertToDate(split[0]);
            BigDecimal value = convertToValue(split[11]);
            Integer quantity = 1;
            String stock = split[5];
            return new Transaction(date, stock, quantity, value, code);
        }
        return null;
    }
}
