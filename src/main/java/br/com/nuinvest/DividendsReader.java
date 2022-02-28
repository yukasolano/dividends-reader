package br.com.nuinvest;

import br.com.nuinvest.model.Report;
import br.com.nuinvest.model.Transaction;
import br.com.nuinvest.reader.TransactionsReader;

import java.io.*;
import java.util.*;


public class DividendsReader {
    public static void main(String[] args) throws IOException {
        List<Transaction> transactions = TransactionsReader.read("file-test.csv");
        Report.buildReport(transactions).print();
    }
}
