package br.com.dividends.model;


import java.util.*;

public class ReportMonthly {

    private final List<Transaction> report;

    public ReportMonthly(List<Transaction> report) {
        this.report = report;
    }

    public static ReportMonthly buildReport(List<Transaction> transactions) {

        transactions.sort(Comparator.comparing(Transaction::getStock));
        return new ReportMonthly(transactions);
    }

    public void print() {
        report.forEach(i -> System.out.printf("%s\t %s \t %s%n", i.getStock(), i.getQuantity(), i.getTotal()));
    }
}
