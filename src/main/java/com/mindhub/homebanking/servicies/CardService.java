package com.mindhub.homebanking.servicies;

import com.mindhub.homebanking.dtos.CardsDTO;
import com.mindhub.homebanking.models.Card;

import java.util.List;

public interface CardService {
    List<CardsDTO> getCardDTO();

    public CardsDTO getCardDTOid(Long id);

    void saveCard (Card card);

    Card findById(Long id);

}
