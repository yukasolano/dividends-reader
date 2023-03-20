package br.com.dividends.adapter;

import br.com.dividends.model.Transaction;

public interface LineToTransaction {
    Transaction convert(String line);
}
