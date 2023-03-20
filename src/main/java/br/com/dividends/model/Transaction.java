package br.com.dividends.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {
    private final LocalDate date;
    private final String stock;
    private final Integer quantity;
    private final BigDecimal total;
    private String type;
    private final String code;

    public Transaction(LocalDate date, String stock, Integer quantity, BigDecimal total, String code) {
        this.date = date;
        this.stock = stock;
        this.quantity = quantity;
        this.total = total;
        this.code = code;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getStock() {
        return stock;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public String getType() {
        return type;
    }

    public String getCode() {
        return code;
    }

}
