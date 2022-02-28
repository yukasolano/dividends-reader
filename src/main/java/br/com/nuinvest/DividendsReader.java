package br.com.nuinvest;

import br.com.nuinvest.model.Transaction;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class DividendsReader {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("file-test.csv");
        FileInputStream inputStream = new FileInputStream(file);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line;
            int count = 0;
            List<Transaction> transactions = new ArrayList<>();
            List<String> remains = new ArrayList<>();
            while ((line = br.readLine()) != null) {

                if (count > 1) {
                    String[] split = line.split(";");
                    String code = split[5];

                    if (code.equals("200")) {
                        LocalDate date = convertToDate(split[1]);
                        BigDecimal value = convertToValue(split[3]);
                        Integer quantity = convertToInteger(split[2].substring(14, 33));
                        String stock = split[2].substring(39, 45);
                        Transaction transaction = new Transaction(date, stock, quantity, value, code);
                        transactions.add(transaction);
                    } else {
                        remains.add(line);
                    }
                }
                count++;
            }

            Map<String, Set<Transaction>> byStock = transactions.stream()
                    .collect(groupingBy(Transaction::getStock, TreeMap::new, Collectors.toSet()));


            byStock.forEach((k, v) -> {
                BigDecimal sum = v.stream().map(Transaction::getTotal)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                DecimalFormat df = new DecimalFormat("####.00");
                System.out.printf("%s\t %s%n", k, df.format(sum));
            });
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

    private static LocalDate convertToDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dateString, formatter);
    }

    private static BigDecimal convertToValue(String valueString) throws ParseException {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');
        String pattern = "#,##0.0#";
        DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
        decimalFormat.setParseBigDecimal(true);
        return (BigDecimal) decimalFormat.parse(valueString);
    }

    private static Integer convertToInteger(String valueString) throws ParseException {
        return Integer.valueOf(valueString.trim().replace(".", ""));
    }
}
