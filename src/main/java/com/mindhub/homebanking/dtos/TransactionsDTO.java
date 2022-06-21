package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.Transactions;
import com.mindhub.homebanking.models.TransactionsType;

import java.time.LocalDateTime;

public class TransactionsDTO {
    private long id;
    private double amount;
    private String description;
    private LocalDateTime date;
    private TransactionsType type;

    private double oldBalance;
    private double newBalance;
    private boolean disable;

    public TransactionsDTO (){}

    public  TransactionsDTO (Transactions transactions){
        this.id = transactions.getId();
        this.amount = transactions.getAmount();
        this.description = transactions.getDescription();
        this.date = transactions.getDate();
        this.type = transactions.getType();
        this.oldBalance = transactions.getOldBalance();
        this.newBalance = transactions.getNewBalance();
        this.disable = transactions.isDisable();
    }

    public long getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getOldBalance() {
        return oldBalance;
    }

    public TransactionsType getType() {
        return type;
    }

    public void setType(TransactionsType type) {
        this.type = type;
    }

    public double getNewBalance() {
        return newBalance;
    }

    public boolean isDisable() {
        return disable;
    }
}
