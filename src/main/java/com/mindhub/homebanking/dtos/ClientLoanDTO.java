package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.ClientLoan;

public class ClientLoanDTO {
        private long idClientLoan;
        private long idLoan;
        private String name;
        private int amount, payment;

        private double percentage;
        //private Client client;


        public ClientLoanDTO (){}

        public ClientLoanDTO(ClientLoan clientLoan){
            this.idClientLoan = clientLoan.getId();
            this.idLoan = clientLoan.getLoan().getId();
            this.name = clientLoan.getLoan().getName();
            this.amount = clientLoan.getAmount();
            this.payment = clientLoan.getPayments();
            //this.client = clientLoan.getClient();
            this.percentage = clientLoan.getPercentage();

        }

    public double getPercentage() {
        return percentage;
    }

    public long getIdClientLoan() {
        return idClientLoan;
    }

    public long getIdLoan() {
        return idLoan;
    }


    public int getAmount() {
        return amount;
    }


    public int getPayment() {
        return payment;
    }


    //public Client getClient() {
     //   return client;
    //}

    public String getName() {
        return name;
    }


}

