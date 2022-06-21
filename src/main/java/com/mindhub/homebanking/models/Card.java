package com.mindhub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="cliente_id")
    private Client cliente;

    private CardType type;
    private CardColor color;
    private String number;
    private int cvv;
    private LocalDate creationCard;
    private LocalDate expiresCard;
    private String cardHolder;
    private boolean disable;

    public Card () {}

    public Card (CardType type, CardColor color, String number, int cvv, LocalDate creationCard,LocalDate  expiresCard,Client cliente,boolean disable){
        this.type = type;
        this.color = color;
        this.number = number;
        this.cvv = cvv;
        this.creationCard = creationCard;
        this.expiresCard = expiresCard;
        this.cardHolder = cliente.getFullName();
        this.cliente = cliente;
        this.disable = disable;
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

    public Client getCliente() {
        return cliente;
    }

    public void setCliente(Client cliente) {
        this.cliente = cliente;
    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public CardColor getColor() {
        return color;
    }

    public void setColor(CardColor color) {
        this.color = color;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public LocalDate getcreationCard() {
        return creationCard;
    }

    public void setcreationCard(LocalDate creationCard) {
        this.creationCard = creationCard;
    }

    public LocalDate getexpiresCard() {
        return expiresCard;
    }

    public void setexpiresCard(LocalDate expiresCard) {
        this.expiresCard = expiresCard;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public void setCard(Client client) {
    }


}
