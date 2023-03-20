package br.com.dividends;

import br.com.dividends.adapter.ModalLineToTransaction;
import br.com.dividends.model.Report;
import br.com.dividends.model.Transaction;
import br.com.dividends.reader.TransactionsReader;

import java.util.List;

public class ModalDividendsReader {
    public static void main(String[] args) {
        List<Transaction> transactions = TransactionsReader.read("modal.txt", new ModalLineToTransaction());
        Report.buildReport(transactions).print();
    }
}
