package com.mindhub.homebanking.dtos;

public class LoanApplicationDTO {

    private long idLoan;
    private double amount;
    private int payments;
    private String accountNumberDestination;

    public LoanApplicationDTO(long idLoan, double amount, int payments, String accountNumberDestination) {
    this.idLoan = idLoan;
    this.amount = amount;
    this.payments = payments;
    this.accountNumberDestination = accountNumberDestination;
    }

    public long getIdLoan() {
        return idLoan;
    }

    public double getAmount() {
        return amount;
    }

    public int getPayments() {
        return payments;
    }

    public String getAccountNumberDestination() {
        return accountNumberDestination;
    }

    public int setAmount(int amount) {
        this.amount = amount;
        return amount;
    }
}
