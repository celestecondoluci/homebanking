package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.AccountType;

import java.time.LocalDateTime;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class AccountDTO {
    private long id;
    private String number;
    private LocalDateTime creationDate;
    private double balance;

    private boolean disable;

    private AccountType type;

   //private Client cliente;
    private Set<TransactionsDTO> transactions;
    public AccountDTO (){}
    public AccountDTO (Account account){
        this.id = account.getId();
        this.number = account.getNumber();
        this.creationDate = account.getCreationDate();
        this.balance = account.getBalance();
        //this.cliente = account.getCliente();
        this.transactions = account.getTransactions().stream().map(transaction ->new TransactionsDTO(transaction)).collect(toSet());
        this.disable = account.isDisable();
        this.type = account.getType();
    }

    public AccountType getType() {
        return type;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }


    public LocalDateTime getCreationDate() {
        return creationDate;
    }


    public double getBalance() {
        return balance;
    }

   // public Client getCliente() {return cliente;}

    public Set<TransactionsDTO> getTransactions() {
        return transactions;
    }

}
