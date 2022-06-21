package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.CardColor;
import com.mindhub.homebanking.models.CardType;

import java.time.LocalDate;


public class CardsDTO {

    private long id;

    private CardType type;

    private CardColor color;

    private String number;

    private int cvv;

    private LocalDate creationCard;

    private LocalDate expiresCard;

    private String cardHolder;

    private boolean disable;


    public CardsDTO () {}

    public CardsDTO (Card card){
        this.id = card.getId();
        this.type = card.getType();
        this.color = card.getColor();
        this.number = card.getNumber();
        this.cvv = card.getCvv();
        this.creationCard = card.getcreationCard();
        this.expiresCard = card.getexpiresCard();
        this.cardHolder = card.getCardHolder();
        this.disable = card.isDisable();
    }

    public long getId() {
        return id;
    }

    public CardType getType() {
        return type;
    }

    public CardColor getColor() {
        return color;
    }

    public String getNumber() {
        return number;
    }

    public int getCvv() {
        return cvv;
    }

    public LocalDate getcreationCard() {
        return creationCard;
    }

    public LocalDate getexpiresCard() {
        return expiresCard;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }
}
