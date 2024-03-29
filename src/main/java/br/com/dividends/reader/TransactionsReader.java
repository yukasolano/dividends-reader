package br.com.dividends.reader;

import br.com.dividends.adapter.LineToTransaction;
import br.com.dividends.model.Transaction;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class TransactionsReader {

    public static List<Transaction> read(String filename, LineToTransaction adapter) {
        List<Transaction> transactions = new ArrayList<>();
        try {
            FileInputStream inputStream = new FileInputStream(filename);

            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String line;
            int count = 0;
            List<String> remains = new ArrayList<>();
            while ((line = br.readLine()) != null) {

                if (count > 1) {
                    Transaction transaction = adapter.convert(line);
                    if (transaction != null) {
                        transactions.add(transaction);
                    } else {
                        remains.add(line);
                    }
                }
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}
