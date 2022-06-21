package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.Client;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

public class ClientDTO {
    long id;
    private String firstName;
    private String lastName;
    private String mail;
    public Set<AccountDTO> accounts;

    private Set<ClientLoanDTO> loans= new HashSet<>();

    private Set<CardsDTO> cards = new HashSet<>();


    public ClientDTO (){}
    public ClientDTO (Client client){
    this.id = client.getId();
    this.firstName = client.getFirstName();
    this.lastName = client.getLastName();
    this.mail = client.getMail();
    this.accounts = client.getAccounts().stream().map(account ->new AccountDTO(account)).collect(toSet());
    this.loans = client.getClientLoans().stream().map(loan -> new ClientLoanDTO(loan)).collect(Collectors.toSet());
    this.cards = client.getCards().stream().map(card -> new CardsDTO(card)).collect(Collectors.toSet());
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMail() {
        return mail;
    }

    public Set<AccountDTO> getAccounts() {
        return accounts;
    }

    public Set<ClientLoanDTO> getLoans() {
        return loans;
    }

    public Set<CardsDTO> getCards() {
        return cards;
    }
}
