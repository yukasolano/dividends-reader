package br.com.nuinvest.model;

import com.sun.corba.se.impl.util.RepositoryIdCache;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class Report {

    private Map<String, BigDecimal> report = new TreeMap<>();

    public Report(Map<String, BigDecimal> report) {
        this.report = report;
    }

    public static Report buildReport(List<Transaction> transactions) {
        Map<String, Set<Transaction>> byStock = transactions.stream()
                .collect(groupingBy(Transaction::getStock, TreeMap::new, Collectors.toSet()));

        Map<String, BigDecimal> report = new TreeMap<>();

        byStock.forEach((k, v) -> {
            BigDecimal sum = v.stream().map(Transaction::getTotal)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            report.put(k, sum);
        });
        return new Report(report);
    }

    public void print() {
        report.forEach((k, v) -> System.out.printf("%s\t %s%n", k, v));
    }
}
