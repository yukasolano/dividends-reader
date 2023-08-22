package br.com.dividends;

import br.com.dividends.adapter.NuInvestLineToTransaction;
import br.com.dividends.model.Report;
import br.com.dividends.model.ReportMonthly;
import br.com.dividends.model.Transaction;
import br.com.dividends.reader.TransactionsReader;

import java.io.*;
import java.util.*;


public class NuInvestDividendsReader {
    public static void main(String[] args) {
        List<Transaction> transactions = TransactionsReader.read("Extrato_2023-07-29.csv", new NuInvestLineToTransaction());
        Report.buildReport(transactions).print();
        //ReportMonthly.buildReport(transactions).print();
    }
}
